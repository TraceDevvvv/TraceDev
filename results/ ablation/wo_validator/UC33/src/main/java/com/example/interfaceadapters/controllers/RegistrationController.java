package com.example.interfaceadapters.controllers;

import com.example.application.usecases.RegistrationInteractor;
import com.example.application.interfaces.IErrorHandler;
import com.example.application.dto.RegistrationRequest;
import com.example.application.dto.RegistrationResponse;
import com.example.interfaceadapters.ui.RegistrationForm;

/**
 * Controller that handles registration requests from the UI (or API).
 */
public class RegistrationController {
    private RegistrationInteractor interactor;
    private IErrorHandler errorHandler;
    private RegistrationForm form;
    private boolean loggingEnabled = false;

    public RegistrationController(RegistrationInteractor interactor, IErrorHandler errorHandler) {
        this.interactor = interactor;
        this.errorHandler = errorHandler;
        this.form = new RegistrationForm();
    }

    public void enableLogging() {
        this.loggingEnabled = true;
        System.out.println("Logging enabled in RegistrationController.");
    }

    public RegistrationForm showRegistrationForm() {
        form.display();
        return form;
    }

    /**
     * Registers a user based on the request.
     * @param request The registration request.
     * @return The registration response.
     */
    public RegistrationResponse registerUser(RegistrationRequest request) {
        if (loggingEnabled) {
            System.out.println("Registering user: " + request.getUsername());
        }
        try {
            RegistrationResponse response = interactor.execute(request);
            if (!response.isSuccess() && errorHandler != null) {
                // In this flow, error handling is already done by interactor, but we can still log.
                if (loggingEnabled) {
                    System.out.println("Registration failed: " + response.getMessage());
                }
            }
            return response;
        } catch (Exception e) {
            if (errorHandler != null) {
                // Convert exception to ValidationResult for error handler.
                // This is an assumption, as the diagram shows error handling via ValidationResult.
                com.example.application.dto.ValidationResult error =
                    new com.example.application.dto.ValidationResult(false, e.getMessage());
                errorHandler.handle(error);
            }
            return new RegistrationResponse(false, "Unexpected error: " + e.getMessage(), null);
        }
    }
}