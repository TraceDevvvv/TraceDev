package com.example.actor;

import com.example.controller.RegistrationController;
import com.example.dto.RegistrationRequestDTO;
import com.example.dto.ResponseDTO;
import com.example.component.RegistrationForm;

/**
 * Represents a guest user who can register for an account.
 * Corresponds to the GuestUser class in the class diagram.
 */
public class GuestUser {
    private String userId;
    private String name;
    private String email;

    // Constructor
    public GuestUser(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Registers a new account.
     * This method initiates the registration process.
     * Sequence diagram messages m1, m5, m8, m17, m32 originate from GuestUser.
     */
    public void register() {
        // In a full implementation, this would coordinate with a RegistrationController.
        System.out.println("GuestUser " + name + " initiated registration.");
    }

    /**
     * Enables the logging feature (sequence diagram message m1).
     * @param controller the RegistrationController
     */
    public void enableLoggingFeature(RegistrationController controller) {
        controller.enableLogging();
    }

    /**
     * Requests the registration form (sequence diagram message m5).
     * @param controller the RegistrationController
     * @return the registration form
     */
    public RegistrationForm requestRegistrationForm(RegistrationController controller) {
        return controller.showRegistrationForm();
    }

    /**
     * Fills and submits the registration form (sequence diagram message m8).
     * @param controller the RegistrationController
     * @param registrationRequest the registration request DTO
     * @return the response DTO
     */
    public ResponseDTO fillAndSubmitForm(RegistrationController controller, RegistrationRequestDTO registrationRequest) {
        return controller.handleFormSubmission(registrationRequest);
    }

    /**
     * Confirms the operation (sequence diagram message m17).
     * @param controller the RegistrationController
     * @param confirmationId the confirmation ID
     * @return the response DTO
     */
    public ResponseDTO confirmOperation(RegistrationController controller, String confirmationId) {
        return controller.confirmRegistration(confirmationId);
    }

    /**
     * Cancels the registration (sequence diagram message m32).
     * @param controller the RegistrationController
     * @return the response DTO
     */
    public ResponseDTO cancelRegistration(RegistrationController controller) {
        return controller.cancelRegistration();
    }
}