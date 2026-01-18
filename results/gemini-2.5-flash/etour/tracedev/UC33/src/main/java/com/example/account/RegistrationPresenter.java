package com.example.account;

import java.util.Arrays;
import java.util.List;

/**
 * The presenter acts as the output adapter for the Register Account use case.
 * It receives responses from the use case and formats them for display by the view (RegistrationForm).
 */
public class RegistrationPresenter implements IRegisterAccountOutputPort {
    private final RegistrationForm view;

    /**
     * Constructor for RegistrationPresenter.
     *
     * @param view The RegistrationForm instance that this presenter will update.
     */
    public RegistrationPresenter(RegistrationForm view) {
        this.view = view;
    }

    /**
     * Presents a successful registration response to the view.
     *
     * @param response The data transfer object containing details of the successful registration.
     */
    @Override
    public void presentSuccess(RegistrationResponseDTO response) {
        if (response.isSuccess()) {
            view.showMessage(response.getMessage() + " Account ID: " + response.getAccountId());
        } else {
            // Should ideally not happen if response.isSuccess() is true
            view.showMessage("Unexpected success response with error: " + response.getMessage());
        }
    }

    /**
     * Presents an error message to the view.
     * If the error details contain a list of errors (e.g., from validation), it tries to parse and display them.
     *
     * @param errorDetails A string containing details about the error that occurred.
     */
    @Override
    public void presentError(String errorDetails) {
        // Attempt to parse validation errors if present
        if (errorDetails.startsWith("Validation failed: ")) {
            String validationErrorsString = errorDetails.substring("Validation failed: ".length());
            List<String> errors = Arrays.asList(validationErrorsString.split(", "));
            view.displayErrors(errors);
        } else {
            view.showMessage("Error: " + errorDetails);
        }
    }

    /**
     * Presents a confirmation request to the view, which then asks the user.
     * This method directly corresponds to message `m11` in the sequence diagram.
     *
     * @param confirmationDetails A string message to display to the user for confirmation.
     */
    @Override
    public void presentConfirmationRequest(String confirmationDetails) {
        // REQ-010: Modified to use askConfirmation (renamed from requestConfirmation)
        // m11: Presenter calls askConfirmation on Form.
        // The Form's askConfirmation method handles user input (m12, m13).
        // The boolean return from askConfirmation is not directly handled here as this is a void method,
        // and the SystemInitializer will manage the subsequent actions (calling controller.confirmRegistration/cancelRegistration).
        view.askConfirmation(confirmationDetails);
    }
}