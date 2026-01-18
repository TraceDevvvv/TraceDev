package com.example;

import com.example.enums.DayOfWeek;
import com.example.serv.AuthenticationService;
import com.example.ui.UIDeleteForm;

/**
 * Represents a restaurant operator actor.
 */
public class RestaurantOperator {
    public String userId;
    public String name;
    private UIDeleteForm uiDeleteForm;
    private AuthenticationService authService;

    public RestaurantOperator(String userId, String name, UIDeleteForm uiDeleteForm, AuthenticationService authService) {
        this.userId = userId;
        this.name = name;
        this.uiDeleteForm = uiDeleteForm;
        this.authService = authService;
    }

    /**
     * Checks if the operator is authenticated.
     */
    public boolean isAuthenticated() {
        return authService.isAuthenticated(userId);
    }

    /**
     * Requests deletion of a menu for a specific day.
     */
    public void requestDeleteMenu(DayOfWeek day) {
        if (!isAuthenticated()) {
            System.out.println("Operator not authenticated.");
            return;
        }
        uiDeleteForm.enableDeleteFunctionality();
        uiDeleteForm.submitForm(day);
    }

    /**
     * Confirms the operation (triggered by user in UI).
     */
    public boolean confirmOperation() {
        uiDeleteForm.confirmOperation();
        return true;
    }

    /**
     * Cancels the operation (triggered by user in UI).
     */
    public void cancelOperation() {
        uiDeleteForm.cancelOperation();
    }

    /**
     * Displays days form (as per sequence diagram).
     */
    public void displayDaysForm() {
        uiDeleteForm.displayDaysForm();
    }

    /**
     * Selects day (as per sequence diagram).
     */
    public void selectDay(DayOfWeek day) {
        uiDeleteForm.selectDay(day);
    }

    /**
     * Displays confirmation (as per sequence diagram).
     */
    public void displayConfirmation() {
        uiDeleteForm.displayConfirmation();
    }
}