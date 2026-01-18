package com.example.ui;

import com.example.dto.JustificationViewDTO;

/**
 * Boundary class representing the user interface for justifications.
 */
public class JustificationUI {
    /**
     * Displays the justifications in the UI.
     * According to sequence diagram notes, this includes:
     * 1. Showing all absences
     * 2. Selecting justified absences
     * 3. Displaying in GREEN based on status field
     * 4. Selecting absences to justify
     * 5. Displaying in RED based on status field
     * @param viewDTO the data to display
     */
    public void displayJustifications(JustificationViewDTO viewDTO) {
        if (viewDTO == null) {
            System.out.println("No justification data to display.");
            return;
        }

        System.out.println("=== JUSTIFIED ABSENCES (GREEN) ===");
        for (var absence : viewDTO.getJustifiedAbsences()) {
            System.out.println("GREEN: " + absence);
        }

        System.out.println("=== UNJUSTIFIED ABSENCES (RED) ===");
        for (var absence : viewDTO.getUnjustifiedAbsences()) {
            System.out.println("RED: " + absence);
        }
    }

    /**
     * Simulates the internal display logic described in sequence diagram note m20.
     */
    public void handleInternalDisplayLogic(JustificationViewDTO viewDTO) {
        // Showing all absences (Step 1)
        System.out.println("Step 1: Showing all absences");
        if (viewDTO != null) {
            System.out.println("Total justified: " + viewDTO.getJustifiedAbsences().size());
            System.out.println("Total unjustified: " + viewDTO.getUnjustifiedAbsences().size());
        }

        // Selecting justified absences (Step 2)
        System.out.println("Step 2: Selecting justified absences");

        // Displaying in GREEN (Step 3)
        System.out.println("Step 3: Displaying justified absences in GREEN");

        // Selecting absences to justify (Step 4)
        System.out.println("Step 4: Selecting absences to justify");

        // Displaying in RED (Step 5)
        System.out.println("Step 5: Displaying unjustified absences in RED");
    }

    /**
     * Method corresponding to sequence message m21: Displays colored justification list.
     * @param viewDTO the data to display
     */
    public void displaysColoredJustificationList(JustificationViewDTO viewDTO) {
        displayJustifications(viewDTO);
    }

    /**
     * Method corresponding to sequence return m24: Error: Registration not completed.
     */
    public void returnErrorRegistrationNotCompleted() {
        System.out.println("Error: Registration not completed");
    }

    /**
     * Method corresponding to sequence return m25: Error: Administrator login required.
     */
    public void returnErrorAdministratorLoginRequired() {
        System.out.println("Error: Administrator login required");
    }
}