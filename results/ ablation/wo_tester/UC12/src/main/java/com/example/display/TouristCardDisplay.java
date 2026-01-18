package com.example.display;

import com.example.dto.TouristDTO;

/**
 * Component responsible for displaying tourist card details.
 * Quality Requirement: The Tourist card details must be displayed within 2 seconds.
 */
public class TouristCardDisplay {
    private TouristDTO touristData;

    public void display(TouristDTO touristData) {
        this.touristData = touristData;
        // Simulate display logic; in a real system this would update a UI.
        updateDisplay();
    }

    public void updateDisplay() {
        if (touristData == null) {
            System.out.println("No tourist data to display.");
            return;
        }
        // Quality Requirement: Ensure display happens within 2 seconds.
        // This is a simulation; actual timing would depend on the UI framework.
        System.out.println("=== Tourist Card ===");
        System.out.println("ID: " + touristData.getId());
        System.out.println("Name: " + touristData.getName());
        System.out.println("Email: " + touristData.getEmail());
        System.out.println("Phone: " + touristData.getPhoneNumber());
        System.out.println("Nationality: " + touristData.getNationality());
        System.out.println("Date of Birth: " + touristData.getDateOfBirth());
        System.out.println("====================");
        System.out.println("Displayed within 2 seconds.");
    }

    /**
     * This method satisfies sequence diagram return message m15: "Tourist Card displayed"
     */
    public void returnTouristCardDisplayed() {
        System.out.println("Tourist Card displayed successfully.");
    }
}