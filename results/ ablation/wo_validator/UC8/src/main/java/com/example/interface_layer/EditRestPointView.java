package com.example.interface_layer;

import com.example.application_layer.EditRestPointController;
import com.example.application_layer.EditRestPointResult;
import com.example.application_layer.EditRestPointCommand;
import java.util.List;
import java.util.Map;

/**
 * Interface Layer: View component for editing a Rest Point.
 * Responsible for displaying UI, capturing user interactions,
 * and delegating to the controller.
 */
public class EditRestPointView {
    private EditRestPointController controller;
    private RestPointDTO currentFormData;
    private boolean formControlsBlocked = false;

    public EditRestPointView(EditRestPointController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of rest points (from previous use case).
     * @param restPoints List of RestPointDTO objects.
     */
    public void displayRestPointList(List<RestPointDTO> restPoints) {
        // Implementation would update UI with list of rest points.
        System.out.println("Displaying rest point list with " + restPoints.size() + " items.");
    }

    /**
     * Shows the form for editing a rest point with initial data.
     * @param restPointData DTO containing rest point data.
     */
    public void showRestPointForm(RestPointDTO restPointData) {
        this.currentFormData = restPointData;
        System.out.println("Showing form for rest point: " + restPointData.name);
    }

    /**
     * Blocks form controls to prevent multiple submissions (Quality Requirement).
     */
    public void blockFormControls() {
        this.formControlsBlocked = true;
        System.out.println("Form controls blocked.");
    }

    /**
     * Shows a confirmation dialog before saving changes.
     */
    public void showConfirmationDialog() {
        System.out.println("Display confirmation dialog: Are you sure you want to save changes?");
    }

    /**
     * Displays validation errors on the form.
     * @param errors Map of field names to error messages.
     */
    public void showValidationErrors(Map<String, String> errors) {
        System.err.println("Validation errors:");
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            System.err.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Shows an error message to the user.
     * @param message Error message.
     */
    public void showErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Closes the form and cleans up.
     */
    public void closeForm() {
        System.out.println("Closing form.");
        this.currentFormData = null;
        this.formControlsBlocked = false;
    }

    // Simulates user actions
    public void onSelectRestPoint(String restPointId) {
        System.out.println("User selected rest point with ID: " + restPointId);
        RestPointDTO dto = controller.loadRestPointData(restPointId);
        if (dto != null) {
            showRestPointForm(dto);
        }
    }

    public void onActivateEditFunction(String restPointId) {
        System.out.println("User activated edit function for ID: " + restPointId);
        // In a real scenario, this might create a command with only the ID.
        // For simplicity, we call execute with a command containing only ID.
        EditRestPointCommand command = new EditRestPointCommand();
        command.restPointId = restPointId;
        EditRestPointResult result = controller.execute(command);
        if (result.success) {
            // This is actually handled in the sequence diagram via loadRestPointData.
            // We'll assume the data is loaded separately.
        } else {
            showErrorMessage(result.message);
        }
    }

    public void onModifyFormData(String field, String value) {
        if (formControlsBlocked) {
            showErrorMessage("Operation in progress");
            return;
        }
        System.out.println("User modified field " + field + " to " + value);
        // Update internal form data.
        if (currentFormData != null) {
            switch (field) {
                case "name": currentFormData.name = value; break;
                case "location": currentFormData.location = value; break;
                case "capacity": currentFormData.capacity = Integer.parseInt(value); break;
                // For amenities, assume a simple comma-separated list.
                case "amenities": currentFormData.amenities = List.of(value.split(",")); break;
                case "status": currentFormData.status = value; break;
                default: System.err.println("Unknown field: " + field);
            }
        }
    }

    public void onSubmitForm() {
        if (formControlsBlocked) {
            showErrorMessage("Operation in progress");
            return;
        }
        System.out.println("User submitted form.");
        if (currentFormData == null) {
            showErrorMessage("No data to submit.");
            return;
        }
        // Build command from current form data.
        EditRestPointCommand command = new EditRestPointCommand();
        command.restPointId = currentFormData.id;
        command.name = currentFormData.name;
        command.location = currentFormData.location;
        command.capacity = currentFormData.capacity;
        command.amenities = currentFormData.amenities;
        command.status = currentFormData.status;
        EditRestPointResult result = controller.execute(command);
        if (result.success && result.validationErrors.isEmpty()) {
            // Validation passed: show confirmation.
            showConfirmationDialog();
        } else {
            showValidationErrors(result.validationErrors);
        }
    }

    public void onConfirmOperation() {
        System.out.println("User confirmed operation.");
        // The actual persistence is triggered by the controller after confirmation.
        // For simplicity, we call a method on the controller.
        if (currentFormData == null) return;
        EditRestPointCommand command = new EditRestPointCommand();
        command.restPointId = currentFormData.id;
        command.name = currentFormData.name;
        command.location = currentFormData.location;
        command.capacity = currentFormData.capacity;
        command.amenities = currentFormData.amenities;
        command.status = currentFormData.status;
        blockFormControls();
        EditRestPointResult result = controller.confirmAndPersist(command);
        if (result.success) {
            System.out.println("Changes saved successfully.");
            closeForm();
        } else {
            showErrorMessage(result.message);
        }
    }

    public void onCancelOperation() {
        System.out.println("User cancelled operation.");
        clearForm();
        closeForm();
    }

    private void clearForm() {
        System.out.println("Clearing form data.");
        currentFormData = null;
    }

    // Inner DTO class to replace the missing infrastructure layer dependency
    public static class RestPointDTO {
        public String id;
        public String name;
        public String location;
        public int capacity;
        public List<String> amenities;
        public String status;
    }
}