package com.example.culturalassets.view;

import com.example.culturalassets.dto.CulturalAssetDetailsDTO;
import java.text.SimpleDateFormat;

/**
 * Represents the view component for displaying cultural asset details.
 * This class is responsible for presenting data to the user and
 * showing error messages.
 */
public class CulturalAssetView {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Displays the details of a cultural asset to the user.
     * @param details The CulturalAssetDetailsDTO containing the data to display.
     */
    public void displayAssetDetails(CulturalAssetDetailsDTO details) {
        System.out.println("\nView: --- CULTURAL ASSET DETAILS ---");
        System.out.println("ID: " + details.getId());
        System.out.println("Name: " + details.getName());
        System.out.println("Description: " + details.getDescription());
        System.out.println("Location: " + details.getLocation());
        System.out.println("Type: " + details.getType());
        System.out.println("Last Updated: " + DATE_FORMAT.format(details.getLastUpdated()));
        System.out.println("View: ------------------------------");
        // Simulate output to Agency Operator
        System.out.println("AO: displayDetails() - Details shown to operator.");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message string to show.
     */
    public void showErrorMessage(String message) {
        System.err.println("\nView: !!! ERROR !!!");
        System.err.println("View: " + message);
        System.err.println("View: !!!           !!!");
        // Simulate output to Agency Operator
        System.err.println("AO: displayError() - Error shown to operator.");
    }
}