package com.example.presentation;

import com.example.domain.DayOfWeek;

/**
 * View for deleting a daily menu.
 * Disforms and interacts with the operator.
 */
public class DeleteDailyMenuView {
    private DeleteDailyMenuController controller;

    public DeleteDailyMenuView(DeleteDailyMenuController controller) {
        this.controller = controller;
    }

    /**
     * Enables delete functionality (called by operator).
     */
    public void enableDeleteFunctionality() {
        System.out.println("Delete functionality enabled.");
        controller.handleDeleteRequest();
    }

    /**
     * Displays the week selection form (7 days).
     * Corresponds to message m4: Shows 7-day selection form.
     */
    public void displayWeekSelectionForm() {
        System.out.println("Displaying week selection form (Monday to Sunday).");
    }

    /**
     * Submits the selected day to the controller.
     */
    public void submitDaySelection(DayOfWeek day) {
        System.out.println("Submitting selected day: " + day);
        controller.handleDeleteRequest(day);
    }

    /**
     * Shows a confirmation dialog.
     * Corresponds to message m12: Shows confirmation popup.
     */
    public void showConfirmationDialog() {
        System.out.println("Show confirmation dialog: Are you sure you want to delete?");
    }

    /**
     * Shows a success message.
     * Corresponds to message m22: "Daily menu successfully deleted".
     */
    public void showSuccessMessage() {
        System.out.println("Daily menu successfully deleted.");
    }

    /**
     * Shows an error message.
     * Corresponds to message m26: Shows error message and m34: "Failed to delete: Connection to server interrupted".
     */
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Shows a cancellation message.
     * Corresponds to message m24: Operation cancelled.
     */
    public void showCancellationMessage() {
        System.out.println("Operation cancelled.");
    }

    // Methods triggered by operator actions (simulating UI events)
    public void selectDay(DayOfWeek day) {
        System.out.println("Operator selected day: " + day);
    }

    public void submitForm() {
        System.out.println("Operator submitted the form.");
        // In a real UI, this would call submitDaySelection with the selected day
        // For simplicity, we assume the selected day is passed separately.
    }

    public void confirmDeletion() {
        System.out.println("Operator confirmed deletion.");
        controller.proceedWithDeletion();
    }

    public void cancelDeletion() {
        System.out.println("Operator cancelled deletion.");
        controller.notifyCancellation();
    }

    /**
     * Shows 7-day selection form as per message m4.
     */
    public void shows7DaySelectionForm() {
        displayWeekSelectionForm();
    }

    /**
     * Shows confirmation popup as per message m12.
     */
    public void showsConfirmationPopup() {
        showConfirmationDialog();
    }

    /**
     * Displays "Daily menu successfully deleted" as per message m22.
     */
    public void showsDailyMenuSuccessfullyDeleted() {
        showSuccessMessage();
    }

    /**
     * Shows operation cancelled as per message m24.
     */
    public void showsOperationCancelled() {
        showCancellationMessage();
    }

    /**
     * Shows error message as per message m26 and m34.
     */
    public void showsErrorMessage() {
        showErrorMessage("Failed to delete: Connection to server interrupted");
    }
}