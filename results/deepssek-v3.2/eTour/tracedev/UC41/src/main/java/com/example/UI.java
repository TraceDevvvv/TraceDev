package com.example;

/**
 * UI class representing the User Interface.
 * This class corresponds to the UI participant in the sequence diagram.
 */
public class UI {

    /**
     * Activates menu editing (message m3).
     */
    public void activateMenuEditing() {
        System.out.println("Menu editing activated.");
    }

    /**
     * Displays form with seven days (message m6).
     * @param weeklyFormData The weekly form data.
     */
    public void displayFormWithSevenDays(WeeklyFormData weeklyFormData) {
        System.out.println("Displaying form with seven days.");
    }

    /**
     * Prompts for submission (message m10).
     */
    public void promptForSubmission() {
        System.out.println("Please submit your selection.");
    }

    /**
     * Displays form with menu data (message m16).
     * @param dayMenuForm The day menu form.
     */
    public void displayFormWithMenuData(DayMenuForm dayMenuForm) {
        System.out.println("Displaying form with menu data for day: " + dayMenuForm.getSelectedDay());
    }

    /**
     * Displays connection error (message m23 and m46).
     */
    public void displayConnectionError() {
        System.out.println("Connection error displayed.");
    }

    /**
     * Displays errors (message m53).
     * @param errorMessages The error messages.
     */
    public void displayErrors(java.util.List<String> errorMessages) {
        System.out.println("Displaying errors:");
        for (String error : errorMessages) {
            System.out.println(" - " + error);
        }
    }

    /**
     * Notifies successful modification (message m40).
     */
    public void notifySuccessfulModification() {
        System.out.println("Menu modification successful.");
    }

    /**
     * Notifies operation cancelled (message m58).
     */
    public void notifyOperationCancelled() {
        System.out.println("Operation cancelled.");
    }

    /**
     * Edits menu items (message m24).
     */
    public void editMenuItems() {
        System.out.println("Editing menu items.");
    }

    /**
     * Submits edited form (message m25).
     */
    public void submitEditedForm() {
        System.out.println("Submitting edited form.");
    }

    /**
     * Confirms operation (message m32).
     * @return true if confirmed.
     */
    public boolean confirmOperation() {
        System.out.println("Operation confirmed.");
        return true;
    }

    /**
     * Confirmation received (message m33).
     */
    public void confirmationReceived() {
        System.out.println("Confirmation received.");
    }

    /**
     * Selects day of week (message m7).
     * @param dayOfWeek The selected day.
     */
    public void selectDayOfWeek(String dayOfWeek) {
        System.out.println("Selected day: " + dayOfWeek);
    }

    /**
     * Submits day selection (message m11).
     * @param dayOfWeek The selected day.
     */
    public void submitDaySelection(String dayOfWeek) {
        System.out.println("Day selection submitted: " + dayOfWeek);
    }
}