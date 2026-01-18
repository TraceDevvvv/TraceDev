package com.example.presentation;

/**
 * Represents the boundary (UI) component for Address Management.
 * This class simulates user interaction and acts as the entry point
 * for the address management use case.
 */
public class AddressManagementUI {
    private AddressController addressController;
    private String currentUserId; // Simulate a logged-in user

    /**
     * Constructs the AddressManagementUI with a given controller.
     * @param addressController The controller responsible for handling UI actions.
     * @param currentUserId The ID of the currently logged-in user.
     */
    public AddressManagementUI(AddressController addressController, String currentUserId) {
        this.addressController = addressController;
        this.currentUserId = currentUserId;
        System.out.println("[AddressManagementUI] Initialized for user: " + currentUserId);
    }

    /**
     * Simulates showing the Address Management screen.
     * In a real UI, this would render the screen elements.
     */
    public void showAddressManagementScreen() {
        System.out.println("\n--- Address Management UI Screen ---");
        System.out.println("Welcome, " + currentUserId + "!");
        System.out.println("Click the 'Address Management' button to view addresses.");
        // In a real UI, this would involve rendering UI components
    }

    /**
     * Simulates the user clicking the "Address Management" button.
     * This triggers the main use case flow by calling the controller.
     */
    public void onAddressManagementClicked() {
        // Sequence Diagram Step: m1 - Administrator -> UI : clicks "Address Management"
        System.out.println("\n[AddressManagementUI] Administrator clicks 'Address Management' button.");
        // Sequence Diagram Step: m2 - Note on UI : Entry Condition Met: Admin logged in, button clicked.
        System.out.println("Note: Entry Condition Met: User '" + currentUserId + "' is logged in.");
        // Sequence Diagram Step: m3 - UI -> AddressController : displayAddressList(userId)
        addressController.displayAddressList(currentUserId);
        // Sequence Diagram Step: m20 - UI --> Administrator : displays address list
        // Sequence Diagram Step: m28 - UI --> Administrator : shows error message
        // The actual display (list or error) is handled by AddressView,
        // and its completion is simulated by the return messages m19 and m27 from AddressView.
        // This final print simulates the UI showing the result to the Administrator.
        System.out.println("[AddressManagementUI] UI operation finished, result shown to Administrator.");
    }
}