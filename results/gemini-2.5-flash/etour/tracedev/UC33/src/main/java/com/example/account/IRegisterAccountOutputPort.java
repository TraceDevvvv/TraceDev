package com.example.account;

/**
 * Interface for the output port of the Register Account use case.
 * Defines how the use case communicates results (success, error, confirmation request)
 * back to the presenting layer (e.g., a presenter or view).
 */
public interface IRegisterAccountOutputPort {
    /**
     * Presents a successful registration response.
     *
     * @param response The data transfer object containing details of the successful registration.
     */
    void presentSuccess(RegistrationResponseDTO response);

    /**
     * Presents an error message.
     *
     * @param errorDetails A string containing details about the error that occurred.
     */
    void presentError(String errorDetails);

    /**
     * Requests confirmation from the user for the registration.
     * This is typically used for a two-step confirmation process.
     *
     * @param confirmationDetails A string message to display to the user for confirmation.
     */
    void presentConfirmationRequest(String confirmationDetails);
}