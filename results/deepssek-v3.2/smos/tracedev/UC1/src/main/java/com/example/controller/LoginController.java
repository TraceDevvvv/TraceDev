package com.example.controller;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.usecase.LoginUseCase;
import com.example.connection.ServerConnection;
import com.example.manager.SessionManager;

/**
 * Controller handling login requests.
 * Coordinates between UI and use case interactor.
 */
public class LoginController {
    private LoginUseCase loginUseCase;
    private ServerConnection serverConnection;
    private SessionManager sessionManager;

    public LoginController(LoginUseCase loginUseCase,
                           ServerConnection serverConnection,
                           SessionManager sessionManager) {
        this.loginUseCase = loginUseCase;
        this.serverConnection = serverConnection;
        this.sessionManager = sessionManager;
    }

    /**
     * Handles a login request from the UI.
     * Implements the flow from the sequence diagram.
     */
    public LoginResponse handleLoginRequest(LoginRequest request) {
        // Check server connection before proceeding (Exit Condition)
        if (!serverConnection.checkConnection()) {
            return new LoginResponse(false, "Server connection error. Please try later.", null);
        }

        // Delegate to the use case interactor
        LoginResponse response = loginUseCase.execute(request);

        // Note: In the sequence diagram, the controller just returns the response.
        return response;
    }

    public void createConnectionErrorResponse() {
        // This method is called when a connection error occurs.
        // In the sequence diagram, m8 is a self-message on Controller.
        // It can be implemented to prepare an error response.
        // For simplicity, we'll just log or set internal state.
        System.out.println("Controller creating connection error response.");
    }
}