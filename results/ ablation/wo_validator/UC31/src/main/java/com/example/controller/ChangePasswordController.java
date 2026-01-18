package com.example.controller;

import com.example.dto.PasswordChangeDTO;
import com.example.dto.UserDto;
import com.example.repository.IUserRepository;
import com.example.repository.DatabaseException;
import com.example.utility.PasswordValidator;
import com.example.value.OperationResult;
import com.example.value.ValidationResult;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Controller for password change operation.
 */
public class ChangePasswordController {
    private PasswordValidator passwordValidator;
    private IUserRepository userRepository;

    // Constructor with dependency injection
    public ChangePasswordController(PasswordValidator passwordValidator, IUserRepository userRepository) {
        this.passwordValidator = passwordValidator;
        this.userRepository = userRepository;
    }

    /**
     * Main method to change password as per sequence diagram.
     * @param dto the PasswordChangeDTO containing user input
     * @return OperationResult indicating success or failure
     */
    public OperationResult changePassword(PasswordChangeDTO dto) {
        // Step 1: Validate password input
        ValidationResult validationResult = passwordValidator.validatePassword(
                dto.getOldPassword(),
                dto.getNewPassword(),
                dto.getConfirmPassword()
        );

        if (!validationResult.isValid()) {
            // Validation failed -> return failure result
            return createFailureResult(validationResult.getErrorMessage());
        }

        // Step 2: Fetch user from repository
        UserDto user = userRepository.findByUsername(dto.getUsername());
        if (user == null) {
            return createFailureResult("User not found");
        }

        // Step 3: Verify old password matches stored hash
        if (!verifyOldPassword(dto.getOldPassword(), user.getPasswordHash())) {
            return createFailureResult("Old password is incorrect");
        }

        // Step 4: Generate hash for new password
        String newPasswordHash = generatePasswordHash(dto.getNewPassword());

        // Step 5: Update password in repository
        try {
            boolean updated = userRepository.updatePassword(dto.getUsername(), newPasswordHash);
            if (!updated) {
                return createFailureResult("Failed to update password");
            }
        } catch (DatabaseException e) {
            // Connection interrupted or other database error
            return createFailureResult(e.getMessage());
        }

        // Success
        return createSuccessResult();
    }

    /**
     * Verifies that the provided old password matches the stored hash.
     * Assumption: Stored hash is SHA-256 for simplicity.
     */
    private boolean verifyOldPassword(String oldPassword, String storedHash) {
        String hashedInput = hashPassword(oldPassword);
        return hashedInput.equals(storedHash);
    }

    /**
     * Generates a hash for the new password.
     * Assumption: Using SHA-256 for password hashing.
     */
    protected String generatePasswordHash(String password) {
        return hashPassword(password);
    }

    // Helper method to hash a password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Fallback to plain text if hashing fails (should not happen)
            e.printStackTrace();
            return password;
        }
    }

    /**
     * Creates a success operation result.
     */
    private OperationResult createSuccessResult() {
        return new OperationResult(true, "Password changed successfully");
    }

    /**
     * Creates a failure operation result.
     */
    private OperationResult createFailureResult(String message) {
        return new OperationResult(false, message);
    }
}