package com.example;

import java.util.List;

/**
 * The presentation layer, responsible for interacting with the user (Admin).
 * Delegates requests to AddressService and AuthenticationService.
 * Handles displaying messages and errors, including connection errors (REQ-10).
 */
public class UserInterface {
    private AddressService addressService;
    private AuthenticationService authenticationService;
    private boolean isLoggedIn = false; // Simulates user login status (REQ-3)

    /**
     * Constructs a UserInterface with dependencies.
     *
     * @param addressService The application service for address operations.
     * @param authenticationService The service for user authentication.
     */
    public UserInterface(AddressService addressService, AuthenticationService authenticationService) {
        this.addressService = addressService;
        this.authenticationService = authenticationService;
    }

    /**
     * Simulates the Admin logging in, as per REQ-3.
     * @return true if login was successful, false otherwise.
     */
    public boolean performLogin() {
        System.out.println("\nUI: Admin trying to log in...");
        boolean success = authenticationService.login();
        if (success) {
            isLoggedIn = true;
            System.out.println("UI: Admin is now logged in.");
        } else {
            System.out.println("UI: Login failed.");
        }
        return isLoggedIn;
    }

    /**
     * Displays the detailed information of a specific address.
     * This method is part of the entry conditions (REQ-4) for the delete address use case.
     *
     * @param address The address object to display.
     */
    public void displayAddressDetails(Address address) {
        System.out.println("\nUI: Displaying Address Details (REQ-4):");
        if (address != null) {
            System.out.println(address.toString());
        } else {
            System.out.println("Address not found.");
        }
    }

    /**
     * Initiates the request to delete an address from the user.
     * This method encapsulates the sequence diagram's interaction from Admin to UI to Service.
     *
     * @param addressId The ID of the address to delete.
     */
    public void requestDeleteAddress(String addressId) {
        if (!isLoggedIn) {
            System.out.println("\nUI: Cannot proceed. Admin not logged in. Please log in first (REQ-3).");
            return;
        }

        System.out.println("\nUI: Admin requests to delete address with ID: " + addressId);
        try {
            boolean deleted = addressService.deleteAddress(addressId);
            if (deleted) {
                System.out.println("UI: Address " + addressId + " deleted successfully. (POST-CONDITION: An address IS deleted (REQ-9))");
                // Post-deletion: refresh the list of addresses
                List<Address> updatedList = addressService.queryAllAddresses();
                displayUpdatedAddressList(updatedList);
            } else {
                displayErrorMessage("Unable to delete the address " + addressId + ", delete the associated classes and try again.");
            }
        } catch (ConnectionFailedException e) {
            // REQ-10: Catch connection exception from service and handle it
            handleConnectionError(e);
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("UI Error: " + message);
    }

    /**
     * Displays an updated list of addresses to the user.
     *
     * @param addresses The list of addresses to display.
     */
    public void displayUpdatedAddressList(List<Address> addresses) {
        System.out.println("\nUI: Displaying Updated Address List:");
        if (addresses.isEmpty()) {
            System.out.println("No addresses available.");
            return;
        }
        for (Address address : addresses) {
            System.out.println(address.toString());
        }
    }

    /**
     * Handles connection-related errors, displaying an appropriate message to the Admin (REQ-10).
     *
     * @param exception The ConnectionFailedException that occurred.
     */
    public void handleConnectionError(ConnectionFailedException exception) {
        System.err.println("UI: Handling Connection Error (REQ-10):");
        displayErrorMessage("System error: Unable to connect to data store. Please try again later. Details: " + exception.getMessage());
    }
}