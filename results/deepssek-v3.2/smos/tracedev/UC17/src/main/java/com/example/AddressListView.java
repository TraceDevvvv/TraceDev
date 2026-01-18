package com.example;

import java.util.List;

/**
 * View component for displaying address list.
 */
public class AddressListView {
    private AddressListViewModel viewModel;
    private AddressListUseCaseController controller;

    // Constructor
    public AddressListView(AddressListViewModel viewModel, AddressListUseCaseController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
    }

    /**
     * Initializes the view.
     */
    public void initialize() {
        System.out.println("AddressListView: Initialized.");
    }

    /**
     * Called when the administrator clicks the address management button.
     */
    public void onAddressManagementButtonClicked() {
        try {
            // Validate user role via controller
            boolean isValid = controller.validateUserRole(new User("username", "role"));
            if (!isValid) {
                System.out.println("AddressListView: Access denied. User role invalid.");
                return;
            }

            // Load addresses via ViewModel
            viewModel.loadAddresses();

            // Get addresses and display
            List<Address> addresses = viewModel.getAddresses();
            displayAddresses(addresses);

        } catch (SecurityException e) {
            System.out.println("AddressListView: Error - " + e.getMessage());
        }
    }

    /**
     * Displays the list of addresses.
     */
    public void displayAddresses(List<Address> addresses) {
        System.out.println("=== Address List ===");
        for (Address addr : addresses) {
            System.out.println(addr);
        }
        System.out.println("====================");
    }
}