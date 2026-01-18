package com.school;

/**
 * Boundary class for the Class form UI.
 * Requirement REQ-002 (UI boundary class)
 */
public class ClassFormUI {
    private InsertClassController controller;

    public ClassFormUI(InsertClassController controller) {
        this.controller = controller;
    }

    /**
     * Displays an empty form for entering class details.
     * Called from ClassListUI when "New Class" button is clicked.
     */
    public void displayForm() {
        System.out.println("ClassFormUI: Displaying empty form (name, address, academic year).");
    }

    /**
     * Submits the form data to the controller.
     * @param formData the DTO containing form input.
     */
    public void submitForm(ClassFormDTO formData) {
        System.out.println("ClassFormUI: Submitting form data.");
        InsertionResult result = controller.insertNewClass(formData);
        handleResult(result);
    }

    /**
     * Handles the result from the controller.
     * @param result the insertion result.
     */
    private void handleResult(InsertionResult result) {
        switch (result) {
            case SUCCESS:
                showSuccess("Class inserted successfully");
                break;
            case VALIDATION_ERROR:
                // The controller will have already handled error details via handleError call.
                // This is a fallback.
                showError("VALIDATION_ERROR");
                break;
            case PERSISTENCE_ERROR:
                showError("PERSISTENCE_ERROR");
                break;
            case NETWORK_ERROR:
                showError("Network error - please try again");
                break;
            case USER_CANCELLED:
                System.out.println("ClassFormUI: Operation cancelled");
                break;
            default:
                showError("UNKNOWN_ERROR");
        }
    }

    public void showSuccess(String message) {
        System.out.println("ClassFormUI: Success - " + message);
    }

    public void showError(String errorType) {
        System.out.println("ClassFormUI: Error - " + errorType);
    }

    /**
     * Simulates a cancel button click from the administrator.
     * Called when the user clicks "Cancel" in the UI.
     */
    public void simulateCancel() {
        System.out.println("ClassFormUI: Administrator clicked Cancel.");
        InsertionResult result = controller.cancelInsertion();
        if (result == InsertionResult.USER_CANCELLED) {
            System.out.println("ClassFormUI: Shows 'Operation cancelled'");
        }
    }
}