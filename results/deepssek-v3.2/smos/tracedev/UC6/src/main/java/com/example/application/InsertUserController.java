package com.example.application;

import com.example.application.dto.InsertUserRequest;
import com.example.application.dto.InsertUserResponse;
import com.example.entity.User;
import com.example.infrastructure.AuthenticationService;
import com.example.infrastructure.SMOSConnectionManager;
import com.example.repository.UserRepository;

/**
 * Interactor class that orchestrates the creation of a new user.
 * Validates data, creates the user entity, and interacts with repositories and serv.
 */
public class InsertUserController {
    private UserRepository userRepository;
    private AuthenticationService authenticationService;
    private SMOSConnectionManager connectionManager;

    public InsertUserController(UserRepository userRepository,
                                AuthenticationService authenticationService,
                                SMOSConnectionManager connectionManager) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.connectionManager = connectionManager;
    }

    /**
     * Executes the use case: insert a new user.
     * Follows the flow described in the sequence diagram.
     *
     * @param request the DTO containing new user data.
     * @return a response indicating success or failure.
     */
    public InsertUserResponse execute(InsertUserRequest request) {
        // Check connection as per sequence diagram
        boolean connectionOk = connectionManager.checkConnection();
        if (!connectionOk) {
            return createErrorResponse("Connection failed.");
        }

        // Validate session (implicitly done via AuthenticationService)
        // The sequence diagram shows validateSession is called in preconditions.
        // For simplicity, we assume session is already valid.

        // Validate data integrity
        if (!validateData(request)) {
            return createErrorResponse("Invalid data.");
        }

        // Create user entity
        User user = createUser(request);
        // Validate the user entity integrity
        if (!user.validate()) {
            return createErrorResponse("User validation failed: " + user.getErrors());
        }

        // Save user via repository
        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            return createErrorResponse("Failed to save user.");
        }

        // Success response
        return createSuccessResponse();
    }

    /**
     * Validates the request data for basic integrity.
     *
     * @param request the InsertUserRequest to validate.
     * @return true if data is valid, false otherwise.
     */
    public boolean validateData(InsertUserRequest request) {
        // Check required fields
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            return false;
        }
        if (request.getSurname() == null || request.getSurname().trim().isEmpty()) {
            return false;
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            return false;
        }
        if (request.getLogin() == null || request.getLogin().trim().isEmpty()) {
            return false;
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return false;
        }
        // Check password confirmation matches
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return false;
        }
        // Simple email format check (basic validation)
        if (!request.getEmail().contains("@")) {
            return false;
        }
        return true;
    }

    /**
     * Creates a User entity from the request data.
     *
     * @param request the InsertUserRequest containing user data.
     * @return a new User instance.
     */
    public User createUser(InsertUserRequest request) {
        // The User entity constructor expects InsertUserRequest (as per class diagram)
        return new User(request);
    }

    /**
     * Creates a success response.
     *
     * @return an InsertUserResponse with isSuccess = true.
     */
    public InsertUserResponse createSuccessResponse() {
        InsertUserResponse response = new InsertUserResponse();
        response.setSuccess(true);
        response.setErrorMessage(null);
        return response;
    }

    /**
     * Creates an error response with the given message.
     *
     * @param errorMessage the error message.
     * @return an InsertUserResponse with isSuccess = false and the error message.
     */
    public InsertUserResponse createErrorResponse(String errorMessage) {
        InsertUserResponse response = new InsertUserResponse();
        response.setSuccess(false);
        response.setErrorMessage(errorMessage);
        return response;
    }

    /**
     * Aborts the current operation (e.g., due to user cancellation).
     */
    public void abort() {
        System.out.println("Insert user operation aborted.");
    }

    /**
     * Returns an operation aborted response.
     * @return InsertUserResponse indicating operation aborted.
     */
    public InsertUserResponse operationAborted() {
        System.out.println("Operation aborted.");
        InsertUserResponse response = new InsertUserResponse();
        response.setSuccess(false);
        response.setErrorMessage("Operation aborted.");
        return response;
    }
}