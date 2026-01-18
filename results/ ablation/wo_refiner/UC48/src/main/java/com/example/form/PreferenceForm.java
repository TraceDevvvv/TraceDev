package com.example.form;

import com.example.model.GenericPreference;
import com.example.service.PreferenceCommandService;
import com.example.service.PreferenceQueryService;
import com.example.service.NotificationService;
import java.util.Map;
import java.util.HashMap;

/**
 * Form for displaying and editing preferences.
 */
public class PreferenceForm {
    private Map<String, String> fields;
    private PreferenceQueryService queryService;
    private PreferenceCommandService commandService;
    private NotificationService notificationService;
    private String confirmationId;

    public PreferenceForm(PreferenceQueryService queryService, PreferenceCommandService commandService, NotificationService notificationService) {
        this.fields = new HashMap<>();
        this.queryService = queryService;
        this.commandService = commandService;
        this.notificationService = notificationService;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    /**
     * Displays the form with current preference data.
     * Linked to requirement 6,7 and sequence message m4.
     */
    public void displayForm(GenericPreference preferenceData) {
        if (preferenceData != null) {
            this.fields = preferenceData.getDetails();
        }
        System.out.println("PreferenceForm: Displaying form with fields " + fields);
    }

    /**
     * Uploads preferences data to the form.
     * Added to satisfy requirement 7.
     */
    public void uploadPreferences(Map<String, String> data) {
        this.fields = data;
        System.out.println("PreferenceForm: Uploaded preferences " + data);
    }

    /**
     * Edits preference fields.
     * Added to satisfy requirement 7,8.
     */
    public void editPreferenceFields(Map<String, String> data) {
        fields.putAll(data);
        System.out.println("PreferenceForm: Edited fields " + data);
    }

    /**
     * Submits the form data.
     * Linked to requirement 8 and sequence message m20.
     */
    public void submitForm(Map<String, String> data) {
        System.out.println("PreferenceForm: Submitting form with data " + data);
        // Generate confirmationId per m22 (self-call)
        confirmationId = generateConfirmationId();
        // Request confirmation m21 (to tourist) is simulated by returning the confirmationId.
    }

    /**
     * Requests confirmation from the user.
     * Added to satisfy requirement 9,10 and sequence message m21.
     */
    public String requestConfirmation() {
        confirmationId = generateConfirmationId();
        System.out.println("PreferenceForm: Requesting confirmation with ID " + confirmationId);
        return confirmationId;
    }

    /**
     * Generates confirmation ID (self-call m22).
     */
    private String generateConfirmationId() {
        return "confirm_" + System.currentTimeMillis();
    }

    /**
     * Confirms operation with confirmationId.
     * Linked to sequence message m23.
     */
    public void confirmOperation(String confirmationId) {
        System.out.println("PreferenceForm: Confirm operation with ID " + confirmationId);
        // Delegate to command service
        commandService.confirmModification(confirmationId);
    }

    /**
     * Cancels an operation.
     * Added to satisfy requirement 13.
     */
    public void cancelOperation(String operationId) {
        System.out.println("PreferenceForm: Cancelling operation " + operationId);
        commandService.cancelModification(operationId);
    }

    /**
     * Displays connection error message.
     * Linked to sequence message m14.
     */
    public void displayConnectionError(String errorMessage) {
        System.out.println("PreferenceForm: Display connection error - " + errorMessage);
        notificationService.sendErrorNotification(fields.get("touristId"), errorMessage);
    }

    /**
     * Displays current preferences in form.
     * Linked to sequence message m18.
     */
    public void displayCurrentPreferences(GenericPreference data) {
        System.out.println("PreferenceForm: Display current preferences in form");
        displayForm(data);
    }

    /**
     * Displays validation error.
     * Linked to sequence message m47.
     */
    public void displayValidationError(String error) {
        System.out.println("PreferenceForm: Display validation error - " + error);
    }

    /**
     * Displays success message.
     * Linked to sequence message m43.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("PreferenceForm: Display success message - " + message);
    }

    /**
     * Operation cancelled message.
     * Linked to sequence message m51.
     */
    public void displayOperationCancelled(String message) {
        System.out.println("PreferenceForm: Operation cancelled - " + message);
    }
}