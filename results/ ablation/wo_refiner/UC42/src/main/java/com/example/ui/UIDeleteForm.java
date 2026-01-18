package com.example.ui;

import com.example.enums.DayOfWeek;
import com.example.serv.AuthenticationService;
import com.example.serv.MenuService;
import java.util.logging.Logger;

/**
 * <<Boundary>> stereotype.
 * User interface for delete menu functionality.
 */
public class UIDeleteForm {
    private static final Logger LOGGER = Logger.getLogger(UIDeleteForm.class.getName());
    private MenuService menuService;
    private AuthenticationService authService;
    private DayOfWeek selectedDay;

    public UIDeleteForm(MenuService menuService, AuthenticationService authService) {
        this.menuService = menuService;
        this.authService = authService;
    }

    /**
     * Displays available days for selection.
     */
    public void displayDays() {
        LOGGER.info("Displaying days of week for selection.");
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day);
        }
    }

    /**
     * Returns the currently selected day.
     */
    public DayOfWeek getSelectedDay() {
        return selectedDay;
    }

    /**
     * Shows confirmation dialog (triggers UI).
     */
    public void showConfirmationDialog() {
        LOGGER.info("Showing confirmation dialog for day: " + selectedDay);
    }

    /**
     * Shows success message.
     */
    public void showSuccessMessage() {
        LOGGER.info("Menu deleted successfully!");
        System.out.println("Success: Menu deleted.");
    }

    /**
     * Shows error message.
     */
    public void showErrorMessage() {
        LOGGER.warning("Error occurred during deletion.");
        System.out.println("Error: Deletion failed.");
    }

    /**
     * Requests confirmation from user via UI.
     */
    public void requestConfirmation(DayOfWeek day) {
        this.selectedDay = day;
        menuService.requestDeletionConfirmation(day);
        showConfirmationDialog();
    }

    /**
     * Enables delete functionality after authentication.
     */
    public void enableDeleteFunctionality() {
        if (authService.isAuthenticated(authService.getCurrentUser())) {
            LOGGER.info("Delete functionality enabled.");
        } else {
            LOGGER.severe("User not authenticated.");
            throw new SecurityException("User not authenticated.");
        }
    }

    /**
     * Submits form with selected day.
     */
    public void submitForm(DayOfWeek day) {
        this.selectedDay = day;
        requestConfirmation(day);
    }

    /**
     * Confirms operation (called by user).
     */
    public void confirmOperation() {
        menuService.confirmDeletion(selectedDay, true);
    }

    /**
     * Cancels operation (called by user).
     */
    public void cancelOperation() {
        menuService.cancelPendingDeletion();
        System.out.println("Operation cancelled.");
    }

    /**
     * Notifies success to user.
     */
    public void notifySuccess() {
        showSuccessMessage();
    }

    /**
     * Notifies failure to user.
     */
    public void notifyFailure() {
        showErrorMessage();
    }

    /**
     * Notifies cancellation to user.
     */
    public void notifyCancellation() {
        System.out.println("Operation cancelled by user.");
    }

    /**
     * Displays days form (as per sequence diagram).
     */
    public void displayDaysForm() {
        displayDays();
    }

    /**
     * Selects day (as per sequence diagram).
     */
    public void selectDay(DayOfWeek day) {
        this.selectedDay = day;
        LOGGER.info("Day selected: " + day);
    }

    /**
     * Deletes menu (as per sequence diagram).
     */
    public void deleteMenu(DayOfWeek day) {
        LOGGER.info("UI calling deleteMenu for " + day);
        menuService.deleteMenu(day);
    }

    /**
     * Displays confirmation (as per sequence diagram).
     */
    public void displayConfirmation() {
        showConfirmationDialog();
    }
}