package com.example.interfaceadapters.ui;

import com.example.application.dto.RegistrationRequest;

/**
 * Represents a registration form in the UI.
 */
public class RegistrationForm {
    private RegistrationRequest collectedData;

    public void display() {
        System.out.println("Registration form displayed.");
    }

    public RegistrationRequest collectData() {
        // Assumption: in a real UI, this would collect data from user input.
        // For simulation, we return the data previously set via fillOutForm.
        return collectedData;
    }

    public boolean showConfirmation(RegistrationRequest data) {
        // Simulate user confirmation.
        System.out.println("Confirmation shown for user: " + data.getUsername());
        return true; // assume user confirms
    }

    // Methods for simulation of UI actions.
    public void fillOutForm() {
        // Simulate filling form with dummy data.
        collectedData = new RegistrationRequest("john_doe", "password123", "john@example.com", true);
        System.out.println("Form filled out.");
    }

    public void submitForm() {
        System.out.println("Form submitted.");
    }

    public void cancel() {
        System.out.println("Operation cancelled.");
    }
}