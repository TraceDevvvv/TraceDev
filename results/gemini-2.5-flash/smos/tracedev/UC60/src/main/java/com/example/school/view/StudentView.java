package com.example.school.view;

import com.example.school.model.ClassRegistryDTO;
import com.example.school.service.SecurityService;

import java.util.List;

/**
 * Represents the view component responsible for displaying student information
 * and error messages to the user.
 */
public class StudentView {

    private final SecurityService securityService;

    /**
     * Constructs a StudentView with a given SecurityService.
     *
     * @param securityService The security service to use for ensuring secure rendering.
     */
    public StudentView(SecurityService securityService) {
        this.securityService = securityService;
    }

    /**
     * Renders (displays) the provided list of class registry DTOs.
     * This method includes a call to the SecurityService for secure rendering.
     *
     * @param classRegistry A list of {@link ClassRegistryDTO} to be displayed.
     */
    public void renderClassRegistry(List<ClassRegistryDTO> classRegistry) {
        System.out.println("\n--- Student Class Registry ---");

        // SD: View -> SecSvc : ensureSecureRender(classRegistryDTOs)
        securityService.ensureSecureRender(classRegistry);

        if (classRegistry == null || classRegistry.isEmpty()) {
            System.out.println("No class registry data available to display.");
            return;
        }

        System.out.println("Displaying " + classRegistry.size() + " entries:");
        for (ClassRegistryDTO dto : classRegistry) {
            System.out.println("  Date: " + dto.getDate() +
                               ", Absences: " + dto.getAbsences() +
                               ", Delays: " + dto.getDelays() +
                               ", Notes: " + dto.getDisciplinaryNotes() +
                               ", Justification: " + dto.getJustification());
        }
        System.out.println("-----------------------------\n");
        // Implicit return of "rendering complete" (m20) happens when this method finishes.
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to show.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!\n");
        // Implicit return of "error message displayed" (m25) happens when this method finishes.
    }
}