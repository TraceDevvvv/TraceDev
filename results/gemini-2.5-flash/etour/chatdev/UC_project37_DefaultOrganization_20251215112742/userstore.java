/*
Simulates a simple user database or storage.
*/
package com.chatdev.store;
import com.chatdev.model.User;
import java.util.HashMap;
import java.util.Map;
/**
 * Simulates a simple user database or storage.
 * In a real application, this would interact with a persistent storage like a database.
 */
public class UserStore {
    // A map to store registered users, with username as the key.
    private static final Map<String, User> users = new HashMap<>();
    /**
     * Static initializer to pre-populate some users for testing.
     * This ensures there's at least one user available for login.
     */
    static {
        registerUser("testuser", "password123");
        registerUser("admin", "adminpass");
    }
    /**
     * Registers a new user.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @return The newly registered User object, or null if the username already exists.
     */
    public static User registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return null; // Username already exists
        }
        User newUser = new User(username, password);
        users.put(username, newUser);
        return newUser;
    }
    /**
     * Finds a user by username and password.
     *
     * @param username The username to search for.
     * @param password The password to verify.
     * @return The User object if found and credentials match, otherwise null.
     */
    public static User findUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }
}