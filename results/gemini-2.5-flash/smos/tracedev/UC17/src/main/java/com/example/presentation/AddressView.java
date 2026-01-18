package com.example.presentation;

import com.example.dto.AddressDTO;
import com.example.dto.AddressListDTO;

/**
 * Represents the view component responsible for displaying address-related information.
 * It's a passive view that receives DTOs and renders them, or displays error messages.
 */
public class AddressView {

    public AddressView() {
        System.out.println("[AddressView] Initialized.");
    }

    /**
     * Renders a list of addresses to the user.
     * @param addressList The AddressListDTO containing addresses to display.
     */
    public void renderAddressList(AddressListDTO addressList) {
        System.out.println("\n--- Address List Display ---");
        if (addressList == null || addressList.getAddresses().isEmpty()) {
            System.out.println("No addresses to display.");
        } else {
            for (AddressDTO address : addressList.getAddresses()) {
                System.out.println("ID: " + address.id);
                System.out.println("  Street: " + address.street);
                System.out.println("  City: " + address.city);
                System.out.println("  Postal Code: " + address.postalCode);
                System.out.println("  Country: " + address.country);
                System.out.println("--------------------");
            }
        }
        System.out.println("--- End Address List ---\n");
        // Sequence Diagram Step: m19 - View --> UI : rendered
        System.out.println("[AddressView] Rendered address list (Return: rendered).");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!\n");
        // Sequence Diagram Step: m27 - View --> UI : error message rendered
        System.err.println("[AddressView] Rendered error message (Return: error message rendered).");
    }
}