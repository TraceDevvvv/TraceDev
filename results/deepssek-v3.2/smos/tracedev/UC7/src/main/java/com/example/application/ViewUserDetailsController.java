package com.example.application;

import com.example.domain.User;
import com.example.infrastructure.RepositoryException;
import com.example.infrastructure.SMOSConnectionException;
import com.example.infrastructure.UserRepository;

/**
 * Use Case Controller for viewing user details.
 * Orchestrates retrieval of a single user and creation of details DTO.
 */
public class ViewUserDetailsController {
    private UserRepository userRepository;

    public ViewUserDetailsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Executes the use case: retrieve user by ID and map to details DTO.
     * Performance requirement: target < 2 seconds.
     * @param userId the ID of the user to retrieve
     * @return UserDetailsDTO containing user details
     * @throws RepositoryException if repository operation fails
     * @throws SMOSConnectionException if connection to SMOS server is interrupted
     */
    public UserDetailsDTO execute(String userId) throws RepositoryException {
        User user = userRepository.findById(userId);
        // Create DTO from Domain Object (as per sequence diagram)
        return new UserDetailsDTO(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getCell(),
                user.getLogin(),
                null); // Password is not retrieved from User for security
    }
}