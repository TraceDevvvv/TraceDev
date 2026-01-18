package com.example.view;

import com.example.dto.SiteDetailsDTO;

/**
 * View for displaying site details and error messages.
 * Added to satisfy requirement Flow of Events (Step 3) and Exit Conditions.
 */
public class SiteDetailsView {
    /**
     * Displays site details.
     * @param dto the site details DTO
     */
    public void displaySiteDetails(SiteDetailsDTO dto) {
        System.out.println("=== Site Details ===");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Description: " + dto.getDescription());
        System.out.println("Location: " + dto.getLocation());
        System.out.println("Historical Period: " + dto.getHistoricalPeriod());
        System.out.println("Rating: " + dto.getRating());
        System.out.println("Opening Hours: " + dto.getOpeningHours());
        System.out.println("Admission Fee: " + dto.getAdmissionFee());
        System.out.println("===================");
        // Return site details displayed (sequence diagram m18)
    }

    /**
     * Displays an error message.
     * @param error the error message
     */
    public void displayErrorMessage(String error) {
        System.out.println("ERROR: " + error);
        // Return error message displayed (sequence diagram m23)
    }
}