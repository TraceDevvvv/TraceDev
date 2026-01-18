package com.example.service;

import com.example.model.User;
import com.example.repository.IUserRepository;
import com.example.util.ConnectionMonitor;
import com.example.exception.ConnectionLostException;

/**
 * Service layer responsible for user-related business logic, such as changing passwords.
 * It orchestrates interactions between repositories, password hashers, and connection monitors.
 */
public class UserService {
    private IUserRepository userRepository;
    private IPasswordHasher passwordHasher;
    private ConnectionMonitor connectionMonitor;

    /**
     * Constructs a new UserService.
     *
     * @param userRepository The repository for accessing user data.
     * @param passwordHasher The service for hashing and checking passwords.
     * @param connectionMonitor The monitor for tracking external system connections.
     */
    public UserService(IUserRepository userRepository, IPasswordHasher passwordHasher, ConnectionMonitor connectionMonitor) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.connectionMonitor = connectionMonitor;
    }

    /**
     * Attempts to change a user's password.
     * This method encapsulates the business logic for verifying the old password,
     * hashing the new one, and updating the user record.
     *
     * @param userId The ID of the user whose password is to be changed.
     * @param oldPassword The user's current password (plain-text).
     * @param newPassword The user's desired new password (plain-text).
     * @return true if the password was successfully changed, false otherwise.
     */
    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        System.out.println("\n[UserService] Attempting to change password for user: " + userId);

        // Fetch user from repository
        User user;
        try {
            user = userRepository.findById(userId);
        } catch (ConnectionLostException e) {
            // REQ-001: Connection to DB fails during user retrieval
            connectionMonitor.notifyConnectionLost();
            System.err.println("[UserService] " + e.getMessage());
            return false;
        }

        if (user == null) {
            System.err.println("[UserService] User not found with ID: " + userId);
            return false;
        }

        // Verify old password
        if (!passwordHasher.checkPassword(oldPassword, user.getHashedPassword())) {
            System.err.println("[UserService] Old password is incorrect for user: " + userId);
            return false;
        }

        // Hash new password
        String newHashedPassword = passwordHasher.hashPassword(newPassword);

        // Update user's hashed password
        user.setHashedPassword(newHashedPassword);
        System.out.println("[UserService] New password hashed and set on user object.");

        // Persist updated user to repository
        boolean updateSuccess;
        try {
            updateSuccess = userRepository.update(user);
        } catch (ConnectionLostException e) {
            // REQ-001: Connection to DB fails during user update
            connectionMonitor.notifyConnectionLost();
            System.err.println("[UserService] " + e.getMessage());
            return false;
        }

        if (!updateSuccess) {
            System.err.println("[UserService] Failed to update user " + userId + " in repository.");
        }
        return updateSuccess;
    }

    /**
     * Notifies the UserService about a cancellation event.
     * This method's implementation can be expanded based on specific cancellation logic.
     */
    public void notifyCancellation() {
        System.out.println("[UserService] Operation cancellation notification received.");
        // Additional cancellation logic could be added here, e.g., releasing resources,
        // notifying connected components, or resetting state.
    }
}