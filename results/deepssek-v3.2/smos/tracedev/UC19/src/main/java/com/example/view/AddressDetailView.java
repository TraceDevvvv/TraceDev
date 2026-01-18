package com.example.view;

import com.example.dto.AddressDetailDTO;
import com.example.controller.ViewAddressDetailsController;
import java.util.HashMap;
import java.util.Map;

/**
 * View component for displaying address details.
 * Interacts with Administrator (actor) and is updated by ViewAddressDetailsController.
 */
public class AddressDetailView {
    private ViewAddressDetailsController controller;

    public void setController(ViewAddressDetailsController controller) {
        this.controller = controller;
    }

    /**
     * Displays address details to the user.
     * Sequence: Called by ViewAddressDetailsController with the fetched DTO.
     */
    public void displayAddressDetails(AddressDetailDTO dto) {
        // Render address name
        renderAddressName(dto.getName());
        // Prepare details map and render
        Map<String, String> details = new HashMap<>();
        details.put("Street", dto.getStreet());
        details.put("City", dto.getCity());
        details.put("Country", dto.getCountry());
        details.put("Postal Code", dto.getPostalCode());
        details.put("Created", dto.getCreatedDate().toString());
        details.put("Last Modified", dto.getLastModifiedDate().toString());
        renderAddressDetails(details);
        // In a real UI, this would update the GUI.
        System.out.println("Address details displayed successfully.");
    }

    /**
     * Renders the address name.
     * Sequence: Called internally by displayAddressDetails.
     */
    protected void renderAddressName(String name) {
        System.out.println("Address Name: " + name);
    }

    /**
     * Renders the address details from a map.
     * Sequence: Called internally by displayAddressDetails.
     */
    protected void renderAddressDetails(Map<String, String> details) {
        System.out.println("Address Details:");
        for (Map.Entry<String, String> entry : details.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Shows an error message to the user.
     * Sequence: Called by ViewAddressDetailsController in case of errors.
     */
    public void showErrorMessage(String message) {
        System.err.println("Error: " + message);
        // In a real UI, this would show a dialog or alert.
    }

    // Added method to handle the message from sequence diagram: m1 -> clicks ShowAddressDetailsButton
    // This method is triggered by the actor (Administrator) clicking the button.
    public void clicksShowAddressDetailsButton(Long addressId) {
        if (controller != null) {
            controller.clicksShowAddressDetailsButton(addressId);
        } else {
            showErrorMessage("Controller not set.");
        }
    }

    // Added method to represent the return message m12: displays address details
    // This is already covered by displayAddressDetails method, but added for traceability.
    public void displaysAddressDetails(AddressDetailDTO dto) {
        displayAddressDetails(dto);
    }

    // Added method to represent the return messages m17, m19, m21: displays error message
    // This is already covered by showErrorMessage method, but added for traceability.
    public void displaysErrorMessage(String message) {
        showErrorMessage(message);
    }
}