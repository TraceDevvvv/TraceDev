package com.example.controller;

import com.example.service.LoginService;
import com.example.handler.ErrorHandler;
import com.example.state.StateManager;
import com.example.model.FormState;
import com.example.view.LoginForm;

/**
 * Controller for login flow, orchestrates interactions between form, service, and error handler.
 */
public class LoginController {
    private LoginService loginService;
    private ErrorHandler errorHandler;
    private StateManager stateManager;

    /**
     * Constructs a LoginController with dependencies.
     * @param service the login service
     * @param handler the error handler
     * @param stateMgr the state manager
     */
    public LoginController(LoginService service, ErrorHandler handler, StateManager stateMgr) {
        this.loginService = service;
        this.errorHandler = handler;
        this.stateManager = stateMgr;
    }

    /**
     * Processes a login request.
     * Implements the sequence: saveState -> authenticate -> handle error if fails.
     * @param username the entered username
     * @param password the entered password
     * @return true if login successful, false otherwise
     */
    public boolean handleLoginRequest(String username, String password) {
        // Create a form state (as per Flow-4) and save via StateManager.
        FormState formState = new FormState(username, password);
        stateManager.saveState(formState);

        // Authenticate via service.
        boolean authenticated = loginService.authenticate(username, password);
        if (!authenticated) {
            handleLoginError();
            return false;
        }
        return true;
    }

    /**
     * Handles login error as per sequence diagram.
     * Added to fix inconsistency identified in audit.
     */
    public void handleLoginError() {
        // Notify user via error handler.
        errorHandler.notifyUser("Invalid credentials");
        // ErrorHandler will call displayErrorMessage and showErrorConfirmationDialog on the form.
        // Recovery is handled within the error handler flow.
    }

    /**
     * Sets the login form for error handling.
     */
    public void setLoginForm(LoginForm form) {
        // Pass form to error handler if needed
    }
}