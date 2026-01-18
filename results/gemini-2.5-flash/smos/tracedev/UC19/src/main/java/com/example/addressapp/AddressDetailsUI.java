package com.example.addressapp;

/**
 * Represents the User Interface for displaying address details.
 * <<View>> component.
 */
public class AddressDetailsUI {

    /**
     * Displays the detailed information of an address to the user.
     * @param dto The AddressDetailsDTO containing the information to display.
     */
    public void displayAddressDetails(AddressDetailsDTO dto) {
        System.out.println("\nAddressDetailsUI: --- Displaying Address Details ---");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Street: " + dto.getStreet());
        System.out.println("City: " + dto.getCity());
        System.out.println("Postal Code: " + dto.getPostalCode());
        System.out.println("Country: " + dto.getCountry());
        System.out.println("------------------------------------");
        System.out.println("AddressDetailsUI: Address details displayed to Administrator.");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.err.println("\nAddressDetailsUI: --- Error ---");
        System.err.println("Message: " + message);
        System.err.println("-------------------");
        System.err.println("AddressDetailsUI: Error message displayed to Administrator.");
    }
}