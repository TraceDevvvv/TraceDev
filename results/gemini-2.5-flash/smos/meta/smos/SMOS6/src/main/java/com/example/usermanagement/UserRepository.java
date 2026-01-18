package com.example.usermanagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Manages the storage and retrieval of User objects, simulating a database.
 * This class provides methods to add new users and check for existing logins.
 * For simplicity, it uses an in-memory HashMap to store user data.
 * In a real-world application, this would interact with a persistent database.
 */
public class UserRepository {

    // A static map to simulate a database table for users.
    // The key is the user's unique login, and the value is the User object.
    // Using static ensures a single instance of the "database" across the application.
    private static final Map<String, User> users = new HashMap<>();

    /**
     * Adds a new user to the repository.
     * Before calling this, it's expected that the user data has been validated
     * for format and completeness by UserValidator.
     * This method specifically handles the uniqueness of the login.
     *
     * @param user The User object to be added.
     * @return true if the user was added successfully (login was not taken), false otherwise.
     */
    public static boolean addUser(User user) {
        // Check if a user with the same login already exists in our simulated database.
        if (users.containsKey(user.getLogin())) {
            // If the login is already taken, we cannot add this user.
            return false;
        }
        // If the login is unique, add the user to the map.
        users.put(user.getLogin(), user);
        return true; // User added successfully.
    }

    /**
     * Checks if a given login is already taken by an existing user in the repository.
     * This is crucial for ensuring unique user accounts.
     *
     * @param login The login string to check for existence.
     * @return true if the login is already in use, false otherwise.
     */
    public static boolean isLoginTaken(String login) {
        return users.containsKey(login);
    }

    /**
     * Retrieves a user by their login.
     * This method is useful for operations like user authentication or profile retrieval.
     *
     * @param login The login of the user to retrieve.
     * @return An Optional containing the User object if found, or an empty Optional if no user
     *         with the given login exists.
     */
    public static Optional<User> getUserByLogin(String login) {
        // Using Optional to handle cases where the user might not be found gracefully.
        return Optional.ofNullable(users.get(login));
    }

    /**
     * Clears all users from the repository.
     * This method is primarily for testing purposes or for resetting the simulated database state.
     */
    public static void clearUsers() {
        users.clear();
    }

    /**
     * Returns the current number of users stored in the repository.
     *
     * @return The count of users currently in the simulated database.
     */
    public static int getUserCount() {
        return users.size();
    }
}