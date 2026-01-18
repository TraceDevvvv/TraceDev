package com.example.addressapp.presentation;

import com.example.addressapp.dto.AddressFormDTO;
import com.example.addressapp.usecase.EnterAddressUseCaseController;
import com.example.addressapp.usecase.AuthenticationService;
import com.example.addressapp.usecase.ViewingAddressesUseCaseController;
import com.example.addressapp.infrastructure.SMOSConnectionException;
import com.example.addressapp.service.AddressService;

/**
 * Presentation layer controller for Address-related UI interactions.
 * This class handles user input, orchestrates the application layer (use cases),
 * and updates the view.
 */
public class AddressController {
    private final EnterAddressUseCaseController enterAddressUseCaseController; // Dependency on the use case
    private final AddressView addressView; // Dependency on the view
    private final AddressService addressService; // Dependency for creating addresses after validation
    private final AuthenticationService authenticationService; // Dependency for checking login status
    private final ViewingAddressesUseCaseController viewingAddressesUseCaseController; // Dependency for checking previous state

    /**
     * Constructs an AddressController with its dependencies.
     *
     * @param useCase The controller for the "Enter Address" use case.
     * @param view The view component responsible for displaying UI.
     * @param service The service layer for address operations.
     * @param authService The authentication service to check login status.
     * @param viewAddrCtrl The controller for viewing addresses to check its state.
     */
    public AddressController(EnterAddressUseCaseController useCase,
                             AddressView view,
                             AddressService service,
                             AuthenticationService authService,
                             ViewingAddressesUseCaseController viewAddrCtrl) {
        this.enterAddressUseCaseController = useCase;
        this.addressView = view;
        this.addressService = service;
        this.authenticationService = authService;
        this.viewingAddressesUseCaseController = viewAddrCtrl;
    }

    /**
     * Handles the event when the "New Address" button is clicked.
     * This method acts as the entry point for the "Enter Address" flow from the UI.
     * It will perform initial checks (like authentication and previous state) before showing the form.
     */
    public void onNewAddressButtonClick() {
        System.out.println("\n[Controller] 'New Address' button clicked.");

        // EC1: Check if user is logged in (from Class Diagram relationship)
        if (!authenticationService.isLoggedIn()) {
            addressView.displayErrorMessage("User is not logged in. Please log in first.");
            return; // Stop flow if not logged in
        }

        // EC2: Check if viewing addresses use case was executed (from Class Diagram relationship)
        if (!viewingAddressesUseCaseController.isExecuted()) {
            addressView.displayErrorMessage("Viewing addresses use case not executed. Please view addresses first.");
            return; // Stop flow if pre-condition not met
        }

        // If entry conditions are met, show the form
        addressView.showNewAddressForm();
    }

    /**
     * Handles the event when the "Save Address" button is clicked.
     * This method orchestrates the validation and persistence of the address data.
     *
     * @param formData The data collected from the address form.
     */
    public void onSaveAddressButtonClick(AddressFormDTO formData) {
        System.out.println("\n[Controller] 'Save Address' button clicked with data: " + formData.addressName);

        // Delegate to the EnterAddressUseCaseController for initial validation
        if (enterAddressUseCaseController.execute(formData)) {
            // Data is valid, proceed to create address via AddressService
            try {
                // The service handles the persistence and can throw connection exceptions
                addressService.createAddress(formData.addressName);
                addressView.displaySuccessMessage("Address entered successfully: " + formData.addressName);
            } catch (SMOSConnectionException e) {
                // Handle infrastructure-level errors
                addressView.displayErrorMessage("Connection data error to the SMOS server: " + e.getMessage());
            } catch (Exception e) {
                // Catch any other unexpected errors
                addressView.displayErrorMessage("An unexpected error occurred while saving the address: " + e.getMessage());
            }
        } else {
            // Data is invalid, ErrodatiUseCase has already been activated by EnterAddressUseCaseController
            addressView.displayErrorMessage("Invalid address data. Please correct.");
        }
    }

    /**
     * Handles the event when the "Cancel" button is clicked. (XC3)
     * Displays a confirmation dialog to the user before acknowledging cancellation.
     */
    public void onCancelButtonClick() {
        System.out.println("\n[Controller] 'Cancel' button clicked.");
        // Display a confirmation dialog to the user.
        // For simulation, we assume user confirms 'yes' to cancellation if 'yes' is typed.
        if (addressView.displayConfirmationDialog("Are you sure you want to cancel the operation?")) {
            System.out.println("[Controller] Operation cancelled by user.");
            // In a real application, this might lead to navigating back to a previous screen
            // or clearing the form.
        } else {
            System.out.println("[Controller] Cancellation aborted by user. Continuing with form.");
            // User decided not to cancel, perhaps return to the form.
        }
    }
}