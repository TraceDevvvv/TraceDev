package com.example.presentation;

import com.example.application.EditPreferencesUseCaseController;
import com.example.dto.PreferencesDTO;
import java.util.Scanner;

/**
 * Presentation layer form for editing preferences.
 * Simulates a simple console UI.
 */
public class EditPreferencesForm {
    private EditPreferencesUseCaseController controller;
    private Scanner scanner = new Scanner(System.in);
    private String userId;

    /**
     * Constructor.
     * @param controller the application controller
     */
    public EditPreferencesForm(EditPreferencesUseCaseController controller) {
        this.controller = controller;
    }

    /**
     * Simulates accessing edit preferences functionality (Step 1).
     * This method initiates the flow.
     * @param userId the user identifier
     */
    public void accessEditPreferences(String userId) {
        this.userId = userId;
        System.out.println("Tourist accesses edit preferences functionality.");
        // Step 2: handleEditPreferencesRequest(userId)
        PreferencesDTO dto = controller.handleEditPreferencesRequest(userId);
        if (dto == null) {
            // Error already displayed via controller
            return;
        }
        // Step 2.4: displayForm(preferencesDto)
        displayForm(dto);
    }

    /**
     * Displays the form with current preferences (Step 2.4).
     * @param preferencesDto the current preferences DTO
     */
    public void displayForm(PreferencesDTO preferencesDto) {
        System.out.println("Displaying form with current preferences:");
        System.out.println("Language: " + preferencesDto.getLanguage());
        System.out.println("Currency: " + preferencesDto.getCurrency());
        System.out.println("Notification Enabled: " + preferencesDto.isNotificationEnabled());
        System.out.println("Dietary Restrictions: " + String.join(", ", preferencesDto.getDietaryRestrictions()));
        System.out.println();

        // Simulate user interaction
        System.out.println("Do you want to edit fields? (yes/no)");
        String answer = scanner.nextLine();
        if ("yes".equalsIgnoreCase(answer)) {
            // Step 3: Edit form fields
            editFormFields(preferencesDto);
        } else {
            System.out.println("No changes made. Exiting.");
            // Exit Condition: No changes made
        }
    }

    /**
     * Simulates editing form fields (Step 3).
     * @param dto the DTO to edit
     */
    private void editFormFields(PreferencesDTO dto) {
        System.out.println("Editing fields...");
        System.out.print("New language: ");
        dto.setLanguage(scanner.nextLine());
        System.out.print("New currency: ");
        dto.setCurrency(scanner.nextLine());
        System.out.print("Enable notifications? (true/false): ");
        dto.setNotificationEnabled(Boolean.parseBoolean(scanner.nextLine()));
        System.out.print("Dietary restrictions (comma separated): ");
        String restrictions = scanner.nextLine();
        dto.setDietaryRestrictions(java.util.Arrays.asList(restrictions.split(",")));
        System.out.println("Form updated.");
        // Step 4: Submit form
        submitForm(dto);
    }

    /**
     * Submits the form (Step 4).
     * @param dto the updated DTO
     */
    private void submitForm(PreferencesDTO dto) {
        System.out.println("Submitting form...");
        // Step 4.2: handleEditPreferencesRequest(userId, updatedDto)
        controller.handleEditPreferencesRequest(userId, dto);

        // Step 5: Request confirmation via controller
        boolean confirmed = controller.showConfirmationPrompt("Confirm changes?");
        if (confirmed) {
            System.out.println("User confirmed.");
            // Step 6.1: Proceed with save
            controller.proceedWithSave(userId, dto);
        } else {
            System.out.println("User cancelled.");
            // Step: Cancel operation
            cancelOperation();
        }
    }

    /**
     * Cancels the operation (mapped from sequence diagram message m47).
     */
    public void cancelOperation() {
        System.out.println("Operation cancelled.");
        controller.cancelOperation();
    }

    /**
     * Displays error message to tourist (mapped from sequence diagram messages m10, m40, m55).
     * @param message the error message
     */
    public void displayErrorNotification(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Displays success notification to tourist (mapped from sequence diagram message m46).
     * @param message the success message
     */
    public void displaySuccessNotification(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Shows confirmation prompt to tourist (mapped from sequence diagram message m30).
     * @param message the prompt message
     * @return true if confirmed
     */
    public boolean showConfirmationPrompt(String message) {
        System.out.print(message + " (yes/no): ");
        String answer = scanner.nextLine();
        return "yes".equalsIgnoreCase(answer);
    }

    /**
     * Retrieves form data (mapped from sequence diagram message m24).
     * @return the PreferencesDTO with edited values (stub)
     */
    public PreferencesDTO getFormData() {
        // In a real UI, this would read from form fields.
        // For simulation, return a default DTO.
        return new PreferencesDTO();
    }
}