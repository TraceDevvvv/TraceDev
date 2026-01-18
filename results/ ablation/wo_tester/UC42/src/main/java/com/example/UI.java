package com.example;

/**
 * UI (Week Form) class representing the boundary for week selection.
 * Implements interactions with PRO and Controller as per sequence diagram.
 */
public class UI {

    /**
     * Enables delete menu functionality (triggered by PRO).
     * Corresponds to message m3 in sequence diagram.
     */
    public void enableDeleteMenuFunctionality() {
        System.out.println("UI: Delete menu functionality enabled.");
    }

    /**
     * Renders the week selection form (called by Controller).
     * Corresponds to message m5 in sequence diagram.
     */
    public void renderWeekSelectionForm() {
        System.out.println("UI: Rendering week selection form.");
        displayFormWithSevenDays();
    }

    /**
     * Displays form with seven days (return to PRO).
     * Corresponds to message m6 in sequence diagram.
     */
    public void displayFormWithSevenDays() {
        System.out.println("UI: Displaying form with seven days.");
    }

    /**
     * Handles selection of a day of the week (triggered by PRO).
     * Corresponds to message m7 in sequence diagram.
     */
    public void selectDayOfTheWeek(String day) {
        System.out.println("UI: Day selected: " + day);
        // This would typically call Controller method to process selection
    }

    /**
     * Displays error message to PRO.
     * Corresponds to messages m16 and m35 in sequence diagram.
     */
    public void displayErrorMessage(String message) {
        System.out.println("UI Error: " + message);
    }

    /**
     * Displays confirmation dialog to PRO.
     * Corresponds to message m25 in sequence diagram.
     */
    public void displayConfirmationDialog(String message) {
        System.out.println("UI Confirmation: " + message);
        showConfirmationDialog(message);
    }

    /**
     * Shows confirmation dialog (called by Controller).
     * Corresponds to message m24 in sequence diagram.
     */
    public void showConfirmationDialog(String message) {
        System.out.println("UI: Showing confirmation dialog - " + message);
    }

    /**
     * Handles confirm operation from PRO.
     * Corresponds to message m26 in sequence diagram.
     */
    public void confirmOperation() {
        System.out.println("UI: Operation confirmed.");
    }

    /**
     * Displays success message to PRO.
     * Corresponds to message m44 in sequence diagram.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("UI Success: " + message);
    }

    /**
     * Handles cancel operation from PRO.
     * Corresponds to message m47 in sequence diagram.
     */
    public void cancelOperation() {
        System.out.println("UI: Operation cancelled.");
        operationCancelled();
    }

    /**
     * Confirms cancellation to PRO.
     * Corresponds to message m51 in sequence diagram.
     */
    public void operationCancelled() {
        System.out.println("UI: Operation cancelled notification sent.");
    }

    /**
     * Shows error notification (called by Controller).
     * Corresponds to messages m15 and m34 in sequence diagram.
     */
    public void showErrorNotification(String message) {
        System.out.println("UI Error Notification: " + message);
        displayErrorMessage(message);
    }

    /**
     * Shows success notification (called by Controller).
     * Corresponds to message m43 in sequence diagram.
     */
    public void showSuccessNotification(String message) {
        System.out.println("UI Success Notification: " + message);
        displaySuccessMessage(message);
    }

    /**
     * Shows cancellation confirmed (called by Controller).
     * Corresponds to message m49 in sequence diagram.
     */
    public void showCancellationConfirmed() {
        System.out.println("UI: Cancellation confirmed.");
        operationCancelled();
    }
}