package com.example.service;

import com.example.model.User;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for handling login operations.
 * Handles incorrect login data securely.
 */
public class LoginService {
    // Simulating a user database. In real app, use a secure database.
    private Map<String, User> userDatabase;

    public LoginService() {
        userDatabase = new HashMap<>();
        // Adding a dummy user for demonstration.
        userDatabase.put("john_doe", new User("john_doe", "securePass123"));
    }

    /**
     * Validates login credentials.
     * @param username The entered username.
     * @param password The entered password.
     * @return true if login is successful, false otherwise.
     */
    public boolean validateLogin(String username, String password) {
        User user = userDatabase.get(username);
        // Validate without exposing which part is wrong.
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }
        return true;
    }

    /**
     * Simulates the flow of handling incorrect login.
     * This method follows the use case steps.
     * @param username The entered username.
     * @param password The entered password.
     * @return A message indicating the result.
     */
    public String handleIncorrectLogin(String username, String password) {
        // Step 1: System not that the data entered for the login is not valid.
        boolean isValid = validateLogin(username, password);
        if (isValid) {
            return "Login successful!";
        }

        // Step 2: System asks for confirmation of the reading.
        String notification = "Invalid login credentials. Please confirm you have read this notification.";
        // In a real UI, you would wait for user input. Here we simulate.
        System.out.println(notification);

        // Step 3: User confirms the reading of the notification.
        // Simulating confirmation by assuming user confirms.
        boolean userConfirmed = true; // This would be user input in a real app.

        if (!userConfirmed) {
            return "User did not confirm. Cannot proceed.";
        }

        // Step 4: System recovers the previous state.
        recoverPreviousState();

        // Exit condition: The system returns control to the user interaction.
        return "System recovered to previous state. You may try again.";
    }

    /**
     * Recovers the system to the previous state (e.g., clear sensitive temporary data).
     * This method should ensure no sensitive information is exposed.
     */
    private void recoverPreviousState() {
        // Clear any sensitive data from memory or session.
        // For example, clear any cached passwords or tokens.
        System.out.println("System state recovered. Sensitive data cleared.");
    }

    /**
     * Adds a user to the database for testing.
     * @param username The username.
     * @param password The password.
     */
    public void addUser(String username, String password) {
        userDatabase.put(username, new User(username, password));
    }
}