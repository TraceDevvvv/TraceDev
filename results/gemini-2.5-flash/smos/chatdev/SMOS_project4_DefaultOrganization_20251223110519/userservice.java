'''
Simulates a user service for managing user accounts and their passwords.
This class mimics backend operations like fetching user data and updating passwords.
For simplicity, user data is stored in memory. In a real system, this would interact with a database.
'''
package chatdev.service; // Added package for organization
import chatdev.user.User; // Import the User class from its package
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class UserService {
    // A simple in-memory store for users. Key: username, Value: User object.
    private Map<String, User> users;
    // Simulates the currently logged-in user.
    private User currentUser;
    // For simulating random failures (e.g., "connection interruption to the smos server").
    private Random random;
    /**
     * Constructs a new UserService and initializes with some dummy user data.
     */
    public UserService() {
        users = new HashMap<>();
        // Add a dummy user for testing the change password functionality.
        // The User constructor now hashes the password internally.
        users.put("testuser", new User("testuser", "oldPassword123"));
        random = new Random();
    }
    /**
     * Authenticates a user by checking the provided plain text password against the stored hashed password.
     * This method is for simulating login and setting the current user.
     * @param username The username to authenticate.
     * @param plainTextPassword The plain text password to authenticate.
     * @return The User object if authentication is successful, null otherwise.
     */
    public User authenticate(String username, String plainTextPassword) {
        User user = users.get(username);
        // Use the checkPassword method which hashes the input password before comparison.
        if (user != null && user.checkPassword(plainTextPassword)) {
            this.currentUser = user; // Set as logged-in user
            return user;
        }
        return null;
    }
    /**
     * Retrieves the currently logged-in user.
     * @return The User object for the logged-in user, or null if no user is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }
    /**
     * Sets the current user. This is primarily for testing or simulating login.
     * @param currentUser The user to set as currently logged in.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    /**
     * Attempts to change the password for the given user.
     * It validates the old password, new password, and confirmation, then updates the user's password.
     *
     * @param user The user whose password is to be changed.
     * @param oldPlainTextPassword The current (old) password provided by the user in plain text.
     * @param newPlainTextPassword The new password the user wants to set in plain text.
     * @param confirmNewPlainTextPassword The confirmation of the new password in plain text.
     * @return A status message indicating success or the reason for failure.
     */
    public String changePassword(User user, String oldPlainTextPassword, String newPlainTextPassword, String confirmNewPlainTextPassword) {
        // Simulate a "connection interruption."
        // This simulates a random failure that might occur in a real-world scenario.
        if (random.nextInt(100) < 5) { // 5% chance of simulated failure
            return "Connection interruption to the server. Please try again.";
        }
        // Precondition: User is logged in (checked by verifying `user` is not null).
        if (user == null) {
            return "Error: No user is currently logged in.";
        }
        // Validate old password against the stored hashed password.
        if (!user.checkPassword(oldPlainTextPassword)) {
            return "Error: Old password does not match.";
        }
        // Validate new password and confirm new password match.
        if (!newPlainTextPassword.equals(confirmNewPlainTextPassword)) {
            return "Error: New password and confirmation do not match.";
        }
        // Validate new password is not empty.
        if (newPlainTextPassword.trim().isEmpty()) {
            return "Error: New password cannot be empty.";
        }
        // Validate new password is not the same as the old password.
        // Use the checkPassword method for comparing with the current password.
        if (user.checkPassword(newPlainTextPassword)) {
            return "Error: New password cannot be the same as the old password.";
        }
        // If all validations pass, update the password.
        // The setPassword method in User will now hash the new password before storing.
        user.setPassword(newPlainTextPassword);
        // In a real application, you'd persist this change to a database.
        return "Success: Your password has been changed!";
    }
}