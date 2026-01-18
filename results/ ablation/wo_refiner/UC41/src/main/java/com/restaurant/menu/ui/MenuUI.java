package com.restaurant.menu.ui;

import com.restaurant.menu.dto.MenuDTO;
import com.restaurant.menu.dto.WeekMenuDTO;
import com.restaurant.menu.validation.ValidationResult;
import com.restaurant.menu.exception.ErrorResponse;

/**
 * Boundary class representing the user interface for menu operations.
 */
public class MenuUI {
    /**
     * Starts the modify menu use case (requirement R5).
     */
    public void startModifyMenuUseCase() {
        System.out.println("Starting Modify Menu Use Case...");
    }

    /**
     * Activates menu editing (requirement R7).
     */
    public void activateMenuEditing() {
        System.out.println("Menu editing activated.");
    }

    public void selectDay(String dayOfWeek) {
        System.out.println("Day selected: " + dayOfWeek);
    }

    public void editMenuData(MenuDTO menuDTO) {
        System.out.println("Editing menu data for day: " + menuDTO.getDayOfWeek());
    }

    public void cancelOperation() {
        System.out.println("Operation cancelled by user.");
    }

    /**
     * Displays week form with days (requirement R7).
     */
    public void displayWeekFormWithDays(WeekMenuDTO weekMenuDTO) {
        System.out.println("Displaying week menu form with " + weekMenuDTO.getDays().size() + " days.");
    }

    public void displayMenuForm(MenuDTO menuDTO) {
        System.out.println("Displaying menu form for " + menuDTO.getDayOfWeek());
    }

    public void showConfirmationDialog() {
        System.out.println("Show confirmation dialog.");
    }

    /**
     * Final confirmation (requirement R12).
     */
    public void finalConfirmation() {
        System.out.println("Final confirmation received.");
    }

    public void showErrors(ValidationResult validationResult) {
        System.out.println("Validation errors: " + validationResult.getErrors());
    }

    /**
     * Shows success notification (requirement R14).
     */
    public void showSuccessNotification() {
        System.out.println("Menu modification successful!");
    }

    /**
     * Shows connection error (requirement R16).
     */
    public void showConnectionError() {
        System.err.println("Connection error: unable to reach server.");
    }

    /**
     * Handles error response (requirement R16).
     */
    public void handleErrorResponse(ErrorResponse response) {
        System.err.println("Error " + response.getStatusCode() + ": " + response.getMessage());
        showConnectionError();
    }
}