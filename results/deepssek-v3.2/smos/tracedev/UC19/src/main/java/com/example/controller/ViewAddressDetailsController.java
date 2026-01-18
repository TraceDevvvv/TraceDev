package com.example.controller;

import com.example.dto.AddressDetailDTO;
import com.example.repository.AddressRepository;
import com.example.view.AddressDetailView;
import com.example.session.SessionManager;

/**
 * Controller for viewing address details.
 * Entry Conditions:
 * 1. System IS ViewingTheAddressList (tracked by currentView)
 * 2. User HAS Performed use case ViewingLenchIndirizzi (checked via SessionManager)
 */
public class ViewAddressDetailsController {
    private AddressRepository addressRepository;
    private AddressDetailView addressDetailView;
    private SessionManager sessionManager;
    private String currentView; // Tracks system state for entry condition: ViewingTheAddressList

    // Constructor
    public ViewAddressDetailsController(AddressRepository addressRepository,
                                       AddressDetailView addressDetailView,
                                       SessionManager sessionManager) {
        this.addressRepository = addressRepository;
        this.addressDetailView = addressDetailView;
        this.sessionManager = sessionManager;
        this.currentView = "ViewingTheAddressList"; // Assuming we start in this state
    }

    /**
     * Shows address details for the given addressId.
     * Sequence: Called by AddressDetailView when user clicks ShowAddressDetailsButton.
     */
    public void showAddressDetails(Long addressId) {
        // Check entry condition: System IS ViewingTheAddressList
        if (!"ViewingTheAddressList".equals(currentView)) {
            // System not in the correct state; log and show error (assumed behavior)
            addressDetailView.showErrorMessage("System is not in the correct state.");
            return;
        }

        // Validate user session (checks entry condition: user performed ViewingLenchIndirizzi)
        boolean validSession = validateUserSession();
        if (!validSession) {
            // Session invalid, show error as per sequence diagram
            addressDetailView.showErrorMessage("Session expired");
            return;
        }

        // Check if user is logged in
        if (!sessionManager.isLoggedIn()) {
            addressDetailView.showErrorMessage("Please login first");
            return;
        }

        // Fetch address details from repository
        AddressDetailDTO dto = addressRepository.findById(addressId);
        if (dto != null) {
            // Log the access
            logAccess();
            // Display address details
            addressDetailView.displayAddressDetails(dto);
        } else {
            // Repository returned null (e.g., connection error handled internally)
            // The error message is already shown by the repository/controller in sequence diagram.
            // For safety, we show a generic error.
            addressDetailView.showErrorMessage("Failed to retrieve address details.");
        }
    }

    /**
     * Validates user session and checks if the user has performed the required use case.
     * @return true if session is valid and entry condition met, false otherwise.
     */
    private boolean validateUserSession() {
        // This method checks if the user has performed ViewingLenchIndirizzi.
        // For simplicity, we assume SessionManager tracks the previous use case.
        // If previous use case is not "ViewingLenchIndirizzi", we consider session invalid.
        String previousUseCase = sessionManager.getPreviousUseCase(); // Assumed method
        return "ViewingLenchIndirizzi".equals(previousUseCase);
    }

    /**
     * Logs access to address details.
     */
    private void logAccess() {
        // In a real system, this would log to an audit system.
        System.out.println("Access logged for address details.");
    }

    // Getter and setter for currentView (optional)
    public String getCurrentView() {
        return currentView;
    }

    public void setCurrentView(String currentView) {
        this.currentView = currentView;
    }

    // Added method to handle the message from sequence diagram: m1 -> clicks ShowAddressDetailsButton
    // This method is called by the AddressDetailView when the button is clicked.
    public void clicksShowAddressDetailsButton(Long addressId) {
        showAddressDetails(addressId);
    }
}
