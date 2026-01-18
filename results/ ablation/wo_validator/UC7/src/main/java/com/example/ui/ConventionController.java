
package com.example.ui;

import com.example.domain.ConventionActivationCommand;
import com.example.domain.ConventionActivationInteractor;
import com.example.domain.ConventionActivationResult;

/**
 * Controller (driving adapter) for handling convention activation requests.
 */
public class ConventionController {

    private final ConventionActivationInteractor activationInteractor;
    private final ActivationForm activationForm;

    public ConventionController(ConventionActivationInteractor activationInteractor, ActivationForm activationForm) {
        this.activationInteractor = activationInteractor;
        this.activationForm = activationForm;
    }

    /**
     * Handles the activation request as per sequence diagram.
     * @param conventionId the ID of the convention to activate
     * @param operatorId the ID of the agency operator
     * @return HTTP response with activation result
     */
    public ConventionActivationResult activateConvention(String conventionId, String operatorId) {
        // First call: load data and display form
        ConventionActivationCommand initialCommand = new ConventionActivationCommand(conventionId, operatorId);
        ConventionActivationResult initialResult = activationInteractor.execute(initialCommand);

        // Step 3: Display form (through controller)
        activationForm.displayForm(initialResult.getDataForForm());

        // Steps 4 & 5: Operator checks data and decides (simulated via form)
        // In a real scenario, the form would be shown to the user and confirmation would be collected asynchronously.
        // For simplicity, we assume the form's confirmation is obtained synchronously here.
        boolean confirmed = activationForm.getConfirmation();

        if (confirmed) {
            // Step 6 & 7: Operator confirmed, process activation
            ConventionActivationCommand confirmedCommand = new ConventionActivationCommand(conventionId, operatorId, true);
            ConventionActivationResult finalResult = activationInteractor.execute(confirmedCommand);
            return finalResult;
        } else {
            // Operator cancelled
            return new ConventionActivationResult(false, "Activation cancelled by operator", null, null);
        }
    }
}
