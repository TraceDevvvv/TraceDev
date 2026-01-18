package com.example.app.service;

import com.example.app.dto.UserDTO;
import com.example.app.exception.ConnectionError;
import com.example.app.model.User;
import com.example.app.repository.IUserRepository;

/**
 * Application Service layer for User-related operations.
 * It orchestrates business logic and interacts with the domain layer and data access layer.
 * Encapsulates business logic for user detail retrieval as noted in the Sequence Diagram.
 */
public class UserApplicationService {
    private final IUserRepository userRepository;

    /**
     * Constructs a UserApplicationService with a dependency on IUserRepository.
     * @param userRepository The repository to use for user data access.
     */
    public UserApplicationService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves user details and transforms them into a UserDTO.
     * This method ensures accurate and up-to-date data retrieval (R8).
     * @param selectedUserId The ID of the user whose details are requested.
     * @return A UserDTO containing the details of the requested user, or null if user not found.
     * @throws ConnectionError If there's an issue connecting to the data source.
     */
    public UserDTO getUserDetails(String selectedUserId) throws ConnectionError {
        System.out.println("UserApplicationService: Attempting to get user details for ID: " + selectedUserId);
        try {
            // Call to data access layer (UserRepository) to fetch the User entity
            User user = userRepository.findById(selectedUserId); // R8: Provides accurate and up-to-date user data.
            if (user == null) {
                System.out.println("UserApplicationService: User with ID " + selectedUserId + " not found.");
                return null; // Return null if user is not found
            }
            // Creates UserDTO from User object to transfer data securely.
            return new UserDTO(user);
        } catch (ConnectionError e) {
            System.err.println("UserApplicationService: Caught ConnectionError - " + e.getMessage());
            // Re-throw ConnectionError as specified in the sequence diagram.
            throw e;
        }
    }
}