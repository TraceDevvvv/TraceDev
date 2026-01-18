package com.example.account;

/**
 * Interface for the input port of the Register Account use case.
 * Defines the operations for initiating and confirming account registration.
 */
public interface IRegisterAccountInputPort {
    /**
     * Executes the registration process with the provided request data.
     * This typically involves validation and preparation for account creation.
     *
     * @param request The data transfer object containing registration details.
     */
    void execute(RegistrationRequestDTO request);

    /**
     * Confirms a previously initiated registration.
     * This method is called after the user has confirmed their intent to create the account.
     */
    void confirmExecute();
}