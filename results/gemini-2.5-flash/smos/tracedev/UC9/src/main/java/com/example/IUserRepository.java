package com.example;

import java.util.List;
import java.util.Optional;

/**
 * Interface for user data repository operations.
 */
public interface IUserRepository {
    /**
     * Deletes a user by their ID.
     * @param userId The ID of the user to delete.
     * @return true if the user was successfully deleted, false otherwise.
     */
    boolean deleteUser(String userId);

    /**
     * Finds all users in the repository.
     * @return A list of all users.
     */
    List<User> findAllUsers();

    /**
     * Finds a user by their ID.
     * @param userId The ID of the user to find.
     * @return An Optional containing the User if found, empty otherwise.
     */
    Optional<User> findUserById(String userId);
}