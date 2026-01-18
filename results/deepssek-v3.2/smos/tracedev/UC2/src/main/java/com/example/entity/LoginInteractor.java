package com.example.entity;

import com.example.port.AuthenticationFailurePort;
import com.example.port.FormDisplayPort;

/**
 * Entity that implements business logic for authentication failure.
 * Implements AuthenticationFailurePort.
 * Traceability: REQ-004 - Implements hexagonal architecture
 */
public class LoginInteractor implements AuthenticationFailurePort {

    private FormDisplayPort formDisplayPort;

    public LoginInteractor(FormDisplayPort formDisplayPort) {
        this.formDisplayPort = formDisplayPort;
    }

    /**
     * Executes the on-failure flow: displays the login form again.
     * This method is called by the controller when authentication fails.
     */
    @Override
    public void onFailure() {
        System.out.println("LoginInteractor: Authentication failure detected via port.");
        // Note m13: "Hexagonal Architecture adapter pattern implementation"
        executeOnFailure();
    }

    /**
     * Orchestrates the failure flow as per sequence diagram.
     * Calls the form display port to show the login form again.
     */
    public void executeOnFailure() {
        System.out.println("LoginInteractor: Executing on-failure flow.");
        formDisplayPort.displayLoginForm();
    }
}