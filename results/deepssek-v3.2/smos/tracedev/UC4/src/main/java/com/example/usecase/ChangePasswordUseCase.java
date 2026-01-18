package com.example.usecase;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.PasswordService;
import com.example.dto.ChangePasswordRequest;
import com.example.dto.ChangePasswordResponse;

/**
 * Orchestrates the secure and reliable password change process.
 * Implements all validation steps and business logic.
 */
public class ChangePasswordUseCase {
    private UserRepository userRepository;
    private PasswordService passwordService;

    public ChangePasswordUseCase(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    /**
     * Executes the password change use case.
     *
     * @param request the change password request DTO
     * @return a response indicating success or failure with a message
     */
    public ChangePasswordResponse execute(ChangePasswordRequest request) {
        System.out.println("ChangePasswordUseCase.execute called for user: " + request.getUsername());

        // Step 1: Validate request data
        validateRequestData(request);

        // Step 1: Validate non-empty fields
        if (request.getUsername() == null || request.getUsername().trim().isEmpty() ||
            request.getOldPassword() == null || request.getOldPassword().trim().isEmpty() ||
            request.getNewPassword() == null || request.getNewPassword().trim().isEmpty() ||
            request.getConfirmPassword() == null || request.getConfirmPassword().trim().isEmpty()) {
            return new ChangePasswordResponse(false, "All fields are required.");
        }

        // Step 2: Validate new password strength requirements
        if (!passwordService.validatePassword(request.getNewPassword())) {
            return new ChangePasswordResponse(false, "New password does not meet strength requirements.");
        }

        // Step 3: Validate confirmPassword matches newPassword
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return new ChangePasswordResponse(false, "New password and confirmation do not match.");
        }

        // Step 4: Retrieve user from repository
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            return new ChangePasswordResponse(false, "User not found.");
        }

        // Step 5: Verify old password matches current hash
        boolean oldPasswordCorrect = passwordService.verifyPassword(request.getOldPassword(), user.getPasswordHash());
        if (!oldPasswordCorrect) {
            return new ChangePasswordResponse(false, "Old password is incorrect.");
        }

        // Step 6: Validate new password (already done above, but double-check)
        // This step is redundant but kept per sequence diagram.
        boolean newPasswordValid = passwordService.validatePassword(request.getNewPassword());
        if (!newPasswordValid) {
            return new ChangePasswordResponse(false, "New password requirements not met.");
        }

        // Step 7: Hash the new password
        String newPasswordHash = passwordService.hashPassword(request.getNewPassword());

        // Step 8: Update user entity
        user.setPasswordHash(newPasswordHash);
        user.changePassword(request.getOldPassword(), request.getNewPassword());

        // Step 9: Persist the updated user
        userRepository.updateUser(user);

        // Step 10: Return success response
        return new ChangePasswordResponse(true, "Password changed successfully.");
    }

    /**
     * Validates the request data.
     *
     * @param request the change password request
     */
    public void validateRequestData(ChangePasswordRequest request) {
        System.out.println("Validating request data.");
        // Validation logic is in execute method
    }
}