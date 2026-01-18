package com.system.command;

import com.system.controller.EditCulturalGoodController;
import com.system.domain.CulturalGood;
import com.system.ui.EditForm;
import com.system.validation.ValidationResult;
import java.util.Map;

/**
 * Command pattern implementation for changing a cultural good.
 */
public class ChangeCulturalGoodCommand {
    private EditCulturalGoodController controller;
    private EditForm form;
    private String culturalGoodId;
    private Map<String, Object> formData;
    private CulturalGood originalCulturalGood;
    private CulturalGood updatedCulturalGood;

    public ChangeCulturalGoodCommand(EditCulturalGoodController controller, EditForm form, Map<String, Object> formData) {
        this.controller = controller;
        this.form = form;
        this.formData = formData;
        // Extract culturalGoodId from formData for sequence diagram compatibility
        if (formData.containsKey("id")) {
            this.culturalGoodId = (String) formData.get("id");
        }
    }

    /**
     * Alternative constructor matching sequence diagram signature.
     */
    public ChangeCulturalGoodCommand(EditCulturalGoodController controller, String culturalGoodId, Map<String, Object> formData) {
        this.controller = controller;
        this.culturalGoodId = culturalGoodId;
        this.formData = formData;
        this.form = null; // Form may be set later
    }

    /**
     * Executes the command: validates, confirms, and saves.
     */
    public boolean execute() {
        // Step 1: Validate form data
        ValidationResult result = controller.validateFormData(formData);
        if (!result.getIsValid()) {
            System.out.println("Validation failed: " + result.getErrors());
            // Unlock controls on validation error (as per sequence diagram)
            if (form != null) {
                form.unlockControls();
            }
            // Handle validation errors
            controller.handleError(new RuntimeException("Validation errors: " + result.getErrors()));
            return false;
        }

        // Step 2: Confirm transaction
        String confirmationMessage = controller.confirmTransaction();
        // In a real system, this would be shown to the user via UI.
        // For simulation, we assume confirmation is required.
        System.out.println("Confirmation required: " + confirmationMessage);
        // Simulate user confirmation (in real flow, this would come from UI)
        boolean userConfirmed = true; // Assume confirmed for simplicity

        if (!userConfirmed) {
            if (form != null) {
                form.unlockControls();
            }
            System.out.println("User cancelled the transaction.");
            return false;
        }

        // Step 3: Lock controls before saving (if form is available)
        if (form != null) {
            form.lockControls();
        }

        // Step 4: Load the cultural good, update, and save
        // Use culturalGoodId if available, otherwise try to extract from formData
        String targetId = culturalGoodId;
        if (targetId == null && formData.containsKey("id")) {
            targetId = (String) formData.get("id");
        }
        if (targetId == null) {
            System.out.println("Cultural good ID not found.");
            if (form != null) {
                form.unlockControls();
            }
            return false;
        }

        originalCulturalGood = controller.loadCulturalGood(targetId);
        if (originalCulturalGood == null) {
            System.out.println("Cultural good not found.");
            if (form != null) {
                form.unlockControls();
            }
            return false;
        }

        // Update the cultural good with form data
        updatedCulturalGood = new CulturalGood(
                originalCulturalGood.getId(),
                (String) formData.get("name"),
                (String) formData.get("description"),
                (String) formData.get("category"),
                (String) formData.get("location"),
                new java.util.Date()
        );

        boolean saved = controller.saveCulturalGood(updatedCulturalGood);
        if (!saved && form != null) {
            form.unlockControls();
        }
        return saved;
    }

    /**
     * Undo the command (rollback changes).
     */
    public void undo() {
        if (originalCulturalGood != null) {
            controller.saveCulturalGood(originalCulturalGood);
            System.out.println("Changes undone.");
        }
    }
}