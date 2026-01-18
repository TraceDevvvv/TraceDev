package com.example.usermanagement;

import java.util.Collections;
import java.util.List;

/**
 * Provides business logic for user management, including authentication and retrieving user lists.
 * This service layer orchestrates operations between the UI/controller and the data access layer (UserRepository).
 */
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new UserService with a given UserRepository.
     *
     * @param userRepository The repository to use for accessing user data. Must not be null.
     * @throws IllegalArgumentException if the userRepository is null.
     */
    public UserService(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("UserRepository cannot be null.");
        }
        this.userRepository = userRepository;
    }

    /**
     * Checks if a given user has the 'Administrator' role.
     * This method is used to enforce access control based on user roles.
     *
     * @param user The user object to check.
     * @return true if the user's role is "Administrator" (case-insensitive), false otherwise.
     */
    public boolean isAdministrator(User user) {
        return user != null && "Administrator".equalsIgnoreCase(user.getRole());
    }

    /**
     * Retrieves a list of all users in the system, but only if the requesting user is an administrator.
     * This method implements the core logic for the "ViewUserList" use case, enforcing the
     * precondition that the user must be logged in as an administrator.
     *
     * @param requestingUser The user attempting to view the list. This user must be an administrator.
     * @return A read-only list of all User objects if the requestingUser is an administrator.
     * @throws SecurityException if the requestingUser is not an administrator, indicating unauthorized access.
     * @throws IllegalArgumentException if the requestingUser is null.
     */
    public List<User> getAllUsersForAdmin(User requestingUser) {
        // Edge case: Ensure the requesting user object is not null.
        if (requestingUser == null) {
            throw new IllegalArgumentException("Requesting user cannot be null for viewing user list.");
        }

        // Precondition check: The user is logged in as administrator.
        if (!isAdministrator(requestingUser)) {
            // If the precondition is not met, throw a SecurityException.
            throw new SecurityException("Unauthorized access: Only administrators can view the user list.");
        }

        // Event sequence: System searches for users in the archive.
        // Delegate to the UserRepository to fetch all user data.
        List<User> users = userRepository.findAllUsers();

        // Postcondition: The system displays the list of users.
        // (The actual display logic will be in a higher layer, e.g., a controller or UI).

        // Return an unmodifiable list to prevent external modification of the retrieved data,
        // ensuring data integrity.
        return Collections.unmodifiableList(users);
    }
}