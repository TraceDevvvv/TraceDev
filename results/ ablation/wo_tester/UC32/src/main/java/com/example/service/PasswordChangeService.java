package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;

/**
 * Service responsible for changing a user's password.
 */
public class PasswordChangeService {
    private UserRepository userRepository;

    public PasswordChangeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Changes the password for a given user.
     * @param userId the ID of the user
     * @param newPassword the new password (plain text)
     * @return true if password was changed successfully, false otherwise
     */
    public boolean changePassword(String userId, String newPassword) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return false;
        }
        // In a real system, hash the password before storing.
        user.setPasswordHash(newPassword);
        userRepository.save(user);
        return true;
    }
}