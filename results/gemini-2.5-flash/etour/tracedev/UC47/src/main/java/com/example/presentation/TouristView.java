package com.example.presentation;

import com.example.domain.TouristData;

/**
 * Presentation Layer: Represents the user interface view for displaying tourist information.
 * In a real application, this would interact with a UI framework (e.g., Spring MVC View, React component).
 * For this simulation, it prints messages to the console.
 */
public class TouristView {

    /**
     * Renders the tourist data onto the view.
     * Corresponds to `render(touristData: TouristData)` in the class diagram.
     * @param touristData The data to be displayed.
     */
    public void render(TouristData touristData) {
        System.out.println("\n--- Tourist Account Edit Form ---");
        if (touristData != null) {
            System.out.println("ID: " + touristData.getId());
            System.out.println("Name: " + touristData.getName());
            System.out.println("Email: " + touristData.getEmail());
            System.out.println("Phone: " + touristData.getPhoneNumber());
            System.out.println("Birth Date: " + (touristData.getBirthDate() != null ? touristData.getBirthDate().toInstant() : "N/A"));
            System.out.println("---------------------------------");
            System.out.println("Please enter new details and submit.");
        } else {
            System.out.println("No tourist data to display.");
        }
    }

    /**
     * Displays an error message to the user.
     * Corresponds to `displayError(message: String)` in the class diagram.
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.err.println("\n--- ERROR ---");
        System.err.println(message);
        System.err.println("-------------");
    }

    /**
     * Displays a success message to the user.
     * Corresponds to `displaySuccess(message: String)` in the class diagram.
     * @param message The success message to display.
     */
    public void displaySuccess(String message) {
        System.out.println("\n--- SUCCESS ---");
        System.out.println(message);
        System.out.println("---------------");
    }

    /**
     * Displays a general message to the user.
     * Corresponds to `displayMessage(message: String)` in the class diagram.
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println("\n--- MESSAGE ---");
        System.out.println(message);
        System.out.println("---------------");
    }

    /**
     * Displays a confirmation prompt to the user.
     * Corresponds to `displayConfirmationPrompt(message: String)` in the class diagram.
     * @param message The confirmation message.
     * @return true, indicating the prompt has been displayed.
     */
    public boolean displayConfirmationPrompt(String message) { // Changed return type to boolean for M38 traceability
        System.out.println("\n--- CONFIRMATION REQUIRED ---");
        System.out.println(message + " (Y/N)");
        System.out.println("-----------------------------\n");
        return true; // Indicates that the prompt was displayed, satisfying 'promptDisplayed' return message
    }
}