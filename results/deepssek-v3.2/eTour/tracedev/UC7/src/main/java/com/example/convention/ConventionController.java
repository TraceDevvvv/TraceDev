package com.example.convention;

/**
 * Controller handling convention-related requests from the user interface.
 */
public class ConventionController {
    private ConventionService conventionService;

    public ConventionController(ConventionService conventionService) {
        this.conventionService = conventionService;
    }

    /**
     * Loads convention form data for a given refreshment point.
     * @param pointId the refreshment point ID.
     * @return a DTO containing the form data, or null if not found.
     */
    public ConventionFormDTO loadConventionForm(String pointId) {
        Convention convention = conventionService.loadConventionData(pointId);
        if (convention == null) {
            return null;
        }
        return convention.toDTO();
    }

    /**
     * Confirms activation of a convention.
     * @param conventionId the ID of the convention to activate.
     * @return the result of the activation attempt.
     */
    public ActivationResult confirmActivation(String conventionId) {
        // The sequence diagram shows an alternative "Cancelled" path.
        // In this implementation, we assume confirmation is always provided.
        // In a real UI, cancellation would be handled before calling this method.
        return conventionService.activateConvention(conventionId);
    }

    /**
     * Enables the activation function for a refreshment point (message from sequence diagram: enableActivationFunction(pointId)).
     * This method corresponds to message m1: AO -> CC.
     * It loads the convention form and returns it for display.
     * @param pointId the refreshment point ID.
     * @return the convention form DTO to display.
     */
    public ConventionFormDTO enableActivationFunction(String pointId) {
        return loadConventionForm(pointId);
    }

    /**
     * Displays the convention form (message from sequence diagram: displayForm(ConventionFormDTO)).
     * This method corresponds to message m6: CC -> AO.
     * In the controller, this method would typically pass the DTO to the view.
     * For traceability, we add a method that the UI can call.
     * @param form the convention form DTO.
     */
    public void displayForm(ConventionFormDTO form) {
        // In a real application, this would trigger UI updates.
        // For now, we just log or pass through.
    }

    /**
     * Shows the activation result (message from sequence diagram: showResult(ActivationResult)).
     * This method corresponds to message m20: CC -> AO.
     * @param result the activation result.
     */
    public void showResult(ActivationResult result) {
        // In a real application, this would display the result to the user.
        // For traceability, we add this method.
    }
}