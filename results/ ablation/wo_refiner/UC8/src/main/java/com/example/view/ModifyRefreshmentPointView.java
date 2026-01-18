package com.example.view;

import com.example.controller.ModifyRefreshmentPointController;
import com.example.dto.RefreshmentPointFormDTO;

/**
 * View for modifying a refreshment point.
 */
public class ModifyRefreshmentPointView {
    private ModifyRefreshmentPointController controller;

    public ModifyRefreshmentPointView(ModifyRefreshmentPointController controller) {
        this.controller = controller;
    }

    public void displayForm(RefreshmentPointFormDTO formData) {
        // Display form with pre-filled data
        System.out.println("Displaying form with data: " + formData.getName());
    }

    public void showConfirmationDialog(String summary) {
        // Show confirmation dialog
        System.out.println("Show confirmation dialog: " + summary);
    }

    public void blockFormInputControls() {
        // Block input controls
        System.out.println("Input controls blocked");
    }

    public void showError(String message) {
        // Show error message
        System.err.println("Error: " + message);
    }

    public void showSuccess(String message, Object data) {
        // Show success message
        System.out.println("Success: " + message + ", data: " + data);
    }
}