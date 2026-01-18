package com.example.addressapp;

/**
 * Represents the User Interface for listing addresses.
 * <<View>> component.
 */
public class AddressListUI {
    private AddressController addressController;

    /**
     * Constructs an AddressListUI with a reference to the AddressController.
     * @param addressController The controller to interact with for UI actions.
     */
    public AddressListUI(AddressController addressController) {
        this.addressController = addressController;
    }

    /**
     * Simulates the event of a user clicking the "Show Details" button for an address.
     * This method initiates the sequence diagram's main flow, corresponding to message m1.
     * @param addressId The ID of the address selected by the user.
     */
    public void onShowDetailsButtonClicked(String addressId) {
        System.out.println("\nAddressListUI: User clicked 'Show Address Details' for ID: " + addressId);
        // EC2: Precondition is that Administrator has performed 'ViewingLenchIndirizzi'.
        // This is assumed by the existence of the button click event.
        // The UI component directly triggers the controller via the 'requestAddressDetails' message (m2).
        addressController.requestAddressDetails(addressId);
    }

    /**
     * A utility method to simulate the UI being presented to the Administrator,
     * fulfilling the "AddressListUI ..> Administrator : interacts" relation
     * and indirectly the EC2 precondition.
     */
    public void show() {
        System.out.println("AddressListUI: Displaying list of addresses to Administrator (simulated).");
        System.out.println("AddressListUI: User can now click 'Show Details' for an address.");
    }
}