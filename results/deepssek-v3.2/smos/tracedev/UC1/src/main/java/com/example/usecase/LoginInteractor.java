package com.example.usecase;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.repository.UserRepository;
import com.example.service.PasswordValidator;
import com.example.connection.ServerConnection;
import com.example.manager.SessionManager;
import com.example.entity.User;
import com.example.entity.Session;

/**
 * Concrete Use Case Interactor implementing the login flow.
 */
public class LoginInteractor implements LoginUseCase {
    private UserRepository userRepository;
    private PasswordValidator passwordValidator;
    private ServerConnection serverConnection;
    private SessionManager sessionManager;

    public LoginInteractor(UserRepository userRepository,
                           PasswordValidator passwordValidator,
                           ServerConnection serverConnection,
                           SessionManager sessionManager) {
        this.userRepository = userRepository;
        this.passwordValidator = passwordValidator;
        this.serverConnection = serverConnection;
        this.sessionManager = sessionManager;
    }

    @Override
    public LoginResponse execute(LoginRequest request) {
        // Check server connection before proceeding (Exit Condition)
        if (!serverConnection.checkConnection()) {
            return createErrorResponse("Server connection error. Cannot proceed.");
        }

        // Validate username length
        if (!passwordValidator.validateLength(request.getUsername())) {
            return createErrorResponse("Username too short. Must be at least 5 characters.");
        }

        // Validate password length
        if (!passwordValidator.validateLength(request.getPassword())) {
            return createErrorResponse("Password too short. Must be at least 5 characters.");
        }

        // Search user in repository (calls searchInArchive as per sequence diagram)
        User user = userRepository.searchInArchive(request.getUsername());
        if (user == null) {
            // Authentication failed: user not found
            return createErrorResponse("Authentication failed. User not found.");
        }

        // Verify password
        if (!user.verifyPassword(request.getPassword())) {
            return createErrorResponse("Authentication failed. Invalid password.");
        }

        // Create session
        Session session = sessionManager.createSession(user);
        if (session == null) {
            return createErrorResponse("Session creation failed.");
        }

        // Post-login connection verification (Exit Condition)
        if (!serverConnection.checkConnection()) {
            return createErrorResponse("Server connection lost after authentication.");
        }

        // Success
        return createSuccessResponse(user);
    }

    /**
     * Creates a success response with the user.
     */
    private LoginResponse createSuccessResponse(User user) {
        return new LoginResponse(true, "Login successful.", user);
    }

    /**
     * Creates an error response with the given message.
     */
    private LoginResponse createErrorResponse(String message) {
        return new LoginResponse(false, message, null);
    }
}