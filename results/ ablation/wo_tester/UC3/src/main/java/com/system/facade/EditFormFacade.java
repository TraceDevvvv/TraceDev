package com.system.facade;

import com.system.controller.EditCulturalGoodController;
import com.system.domain.CulturalGood;
import com.system.ui.EditForm;
import com.system.command.ChangeCulturalGoodCommand;
import com.system.validation.ValidationResult;
import java.util.Map;

/**
 * Facade simplifying the edit process for the agency operator.
 */
public class EditFormFacade {
    private EditCulturalGoodController controller;
    private EditForm form;

    public EditFormFacade(EditCulturalGoodController controller) {
        this.controller = controller;
    }

    /**
     * Starts the edit process for a given cultural good.
     */
    public void startEditProcess(String culturalGoodId) {
        // Load cultural good
        CulturalGood culturalGood = controller.loadCulturalGood(culturalGoodId);
        if (culturalGood == null) {
            System.out.println("Cultural good not found.");
            return;
        }

        // Verify agency is active (assuming agency ID is known; here we hardcode for demo)
        boolean agencyActive = controller.verifyAgencyActive("AG001");
        if (!agencyActive) {
            System.out.println("Agency is not active. Edit not allowed.");
            return;
        }

        // Display form
        form = controller.displayEditForm(culturalGood);
        System.out.println("Form displayed for editing.");
    }

    /**
     * Submits the edit process with the provided form data.
     * @return true if successful, false otherwise.
     */
    public boolean submitEditProcess(Map<String, Object> formData) {
        if (form == null) {
            System.out.println("No active form. Start edit process first.");
            return false;
        }

        // Update form data
        form.editFormData(formData);
        System.out.println("Form updated.");

        // Create and execute command using constructor matching sequence diagram
        ChangeCulturalGoodCommand command = new ChangeCulturalGoodCommand(controller, form, formData);
        boolean success = command.execute();

        if (success) {
            System.out.println("Success message with controls locked.");
        } else {
            System.out.println("Edit process failed.");
        }
        return success;
    }

    /**
     * Submits the edit process with explicit culturalGoodId (for sequence diagram).
     */
    public boolean submitEditProcess(String culturalGoodId, Map<String, Object> formData) {
        // Create command using sequence diagram constructor
        ChangeCulturalGoodCommand command = new ChangeCulturalGoodCommand(controller, culturalGoodId, formData);
        boolean success = command.execute();

        if (success) {
            System.out.println("Success message with controls locked.");
        } else {
            System.out.println("Edit process failed.");
        }
        return success;
    }

    /**
     * Cancels the ongoing edit process.
     */
    public void cancelEditProcess() {
        if (form != null) {
            form.unlockControls();
        }
        System.out.println("Operation cancelled.");
    }

    /**
     * Displays confirmation request to the user (simulated).
     */
    public void displayConfirmationRequest(String message) {
        System.out.println("Display confirmation request: " + message);
    }

    /**
     * Called when the operator confirms the operation.
     */
    public boolean confirmOperation() {
        // In a real UI, this would trigger the actual save.
        // For this simulation, we assume the command handles it.
        System.out.println("Operator confirmed the operation.");
        return true;
    }

    /**
     * Called when the operator cancels the operation.
     */
    public void cancelOperation() {
        if (form != null) {
            form.unlockControls();
        }
        System.out.println("Operator cancelled the operation.");
    }

    /**
     * Displays validation errors to the user.
     */
    public void displayValidationErrors(ValidationResult result) {
        System.out.println("Display validation errors: " + result.getErrors());
    }
}