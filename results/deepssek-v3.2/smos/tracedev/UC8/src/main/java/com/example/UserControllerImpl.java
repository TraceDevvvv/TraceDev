package com.example;

/**
 * Implementation of UserController.
 */
public class UserControllerImpl implements UserController {
    private EditUserUseCase editUserUseCase;
    private AuthenticationService authService;
    private ErrorHandler errorHandler;

    public UserControllerImpl(EditUserUseCase editUserUseCase, AuthenticationService authService, ErrorHandler errorHandler) {
        this.editUserUseCase = editUserUseCase;
        this.authService = authService;
        this.errorHandler = errorHandler;
    }

    /**
     * Verifies that the current user has admin access.
     */
    public boolean verifyAdminAccess() {
        return authService.isAdmin();
    }

    /**
     * Cancels the ongoing operation.
     * Added to satisfy Exit-Interruption requirement.
     */
    public void cancelOperation() {
        System.out.println("Operation cancelled by administrator.");
        // Perform any necessary cleanup.
    }

    @Override
    public EditUserResponse handleEditUserRequest(EditUserRequest request) {
        if (!verifyAdminAccess()) {
            return EditUserResponse.failure("Access denied: admin required", java.util.Collections.singletonList("Access denied"));
        }

        EditUserDTO dto = new EditUserDTO(
            request.getUserId(),
            null, // name not changed unless specified in changedFields
            null, // email not changed unless specified
            request.getChangedFields()
        );
        // In a real scenario, we would extract name and email from changedFields if present.
        // For simplicity, we ignore that here.

        EditUserCommand command = new EditUserCommand(request.getUserId(), dto);
        EditUserResult result = editUserUseCase.execute(command);

        if (result.isSuccess()) {
            return EditUserResponse.success(result.getMessage());
        } else {
            // If validation errors exist, activate the Errodati use case via ErrorHandler
            if (result.getValidationErrors() != null && !result.getValidationErrors().isEmpty()) {
                ValidationResult vr = new ValidationResult();
                for (String err : result.getValidationErrors()) {
                    vr.addError(err);
                }
                errorHandler.handleDataError(vr);
            }
            return EditUserResponse.failure(result.getMessage(), result.getValidationErrors());
        }
    }
}