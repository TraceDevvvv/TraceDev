package presentation;

import dto.PointOfRestDTO;

/**
 * Presentation Layer: View component for editing a Point of Rest.
 * Handles displaying the form and user interaction, then delegates to the controller.
 */
public class EditPointOfRestView {
    private EditPointOfRestController controller;

    // Constructor for dependency injection of the controller
    public EditPointOfRestView(EditPointOfRestController controller) {
        this.controller = controller;
        // The view would typically register itself with the controller or be passed to it.
        // For simplicity, we assume the controller is ready to receive calls.
    }

    /**
     * Simulates the user selecting a Point of Rest.
     * Delegates to the controller to fetch details.
     * @param id The ID of the Point of Rest to select.
     */
    public void selectPointOfRest(String id) {
        System.out.println("\n[View] Agency Operator selects Point of Rest with ID: " + id);
        // Interaction: View -> Controller: selectPoint(id)
        controller.selectPoint(id);
    }

    /**
     * Displays the form with the provided PointOfRestDTO data.
     * @param dto The DTO containing Point of Rest details to display.
     */
    public void displayForm(PointOfRestDTO dto) {
        System.out.println("[View] Displaying Point of Rest form:");
        System.out.println("  ID: " + dto.id);
        System.out.println("  Name: " + dto.name);
        System.out.println("  Address: " + dto.address);
        System.out.println("  Description: " + dto.description);
        System.out.println("  Status: " + dto.status);
        System.out.println("[View] Form ready for editing.");
    }

    /**
     * Simulates the user submitting updated form data.
     * Delegates to the controller.
     * @param updatedDTO The DTO with updated Point of Rest details.
     */
    public void submitFormData(PointOfRestDTO updatedDTO) {
        System.out.println("\n[View] Agency Operator submits form data.");
        // Interaction: View -> Controller: submitFormData(updatedDTO)
        controller.submitFormData(updatedDTO);
    }

    /**
     * Displays a confirmation dialog to the user.
     */
    public void showConfirmation() {
        System.out.println("[View] Displaying confirmation: 'Are you sure you want to save changes?'");
        // In a real UI, this would show a dialog.
    }

    /**
     * Displays a success message to the user.
     * @param message The success message to show.
     */
    public void showSuccess(String message) {
        System.out.println("[View] SUCCESS: " + message);
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to show.
     */
    public void showError(String message) {
        System.err.println("[View] ERROR: " + message);
    }

    /**
     * Blocks input controls on the form.
     */
    public void blockInputs() {
        System.out.println("[View] Blocking form inputs...");
    }

    /**
     * Unblocks input controls on the form.
     */
    public void unblockInputs() {
        System.out.println("[View] Unblocking form inputs.");
    }

    /**
     * Simulates the user confirming changes.
     * Delegates to the controller.
     */
    public void confirmChanges() {
        System.out.println("[View] Agency Operator confirms changes.");
        // Interaction: View -> Controller: confirmChanges()
        controller.confirmChanges();
    }

    /**
     * Simulates the user canceling changes.
     * Delegates to the controller.
     */
    public void cancelChanges() {
        System.out.println("[View] Agency Operator cancels changes.");
        // Interaction: View -> Controller: cancelChanges()
        controller.cancelChanges();
    }

    /**
     * Navigates the user back to the previous screen.
     * Added to satisfy requirement R11.
     */
    public void navigateToPreviousScreen() {
        System.out.println("[View] Navigating back to the previous screen.");
    }
}