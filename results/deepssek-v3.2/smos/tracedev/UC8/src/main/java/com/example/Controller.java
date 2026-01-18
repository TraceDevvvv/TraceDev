package com.example;

import java.util.Map;

/**
 * Controller matching sequence diagram participant "Controller".
 */
public class Controller {
    private UserControllerImpl userController;
    private AuthenticationService authService;

    public Controller(UserControllerImpl userController, AuthenticationService authService) {
        this.userController = userController;
        this.authService = authService;
    }

    /**
     * Verify admin access (message m8).
     */
    public boolean verifyAdminAccess() {
        return userController.verifyAdminAccess();
    }

    /**
     * Construct EditUserCommand from Request (message m10).
     */
    public EditUserCommand constructEditUserCommandFromRequest(EditUserRequest request) {
        EditUserDTO dto = new EditUserDTO(
            request.getUserId(),
            null,
            null,
            request.getChangedFields()
        );
        return new EditUserCommand(request.getUserId(), dto);
    }

    /**
     * Create failed EditUserResponse (message m22).
     */
    public EditUserResponse createFailedEditUserResponse(String message, java.util.List<String> errors) {
        return EditUserResponse.failure(message, errors);
    }

    /**
     * Create successful EditUserResponse (message m49).
     */
    public EditUserResponse createSuccessfulEditUserResponse(String message) {
        return EditUserResponse.success(message);
    }

    /**
     * Operation cancelled (message m27).
     */
    public String operationCancelled() {
        userController.cancelOperation();
        return "operation cancelled";
    }
}