import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ChangePassword: A complete Java program for the ChangePassword use case.
 * This program simulates a user changing their password in a system.
 * It includes validation, edge case handling, and simulates connection to a server.
 * 
 * Main classes:
 * 1. User: Represents a registered user with username and password.
 * 2. PasswordChangeService: Handles password change logic and validation.
 * 3. ChangePassword: Main class with console interface.
 */
class User {
    private String username;
    private String password;
    
    /**
     * Constructor to create a new user with given credentials.
     * @param username The user's username
     * @param password The user's current password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}

/**
 * Service class that handles password change operations.
 * Validates inputs, checks old password, ensures new password meets requirements,
 * and simulates server connection for password update.
 */
class PasswordChangeService {
    // Simulating a simple in-memory user database
    private static Map<String, User> userDatabase = new HashMap<>();
    
    // Initialize with some test users
    static {
        userDatabase.put("john_doe", new User("john_doe", "oldPassword123"));
        userDatabase.put("jane_smith", new User("jane_smith", "securePass456"));
    }
    
    /**
     * Attempts to change a user's password after validating all requirements.
     * 
     * @param username The username of the logged-in user
     * @param oldPassword The user's current password
     * @param newPassword The desired new password
     * @param confirmNewPassword Confirmation of the new password
     * @return A message indicating success or failure
     */
    public String changePassword(String username, String oldPassword, 
                                 String newPassword, String confirmNewPassword) {
        
        // Edge case: Check if user exists in the system
        if (!userDatabase.containsKey(username)) {
            return "Error: User not found. Please log in again.";
        }
        
        User user = userDatabase.get(username);
        
        // Edge case: Verify old password matches
        if (!user.getPassword().equals(oldPassword)) {
            return "Error: Old password is incorrect.";
        }
        
        // Edge case: Check if new password is the same as old password
        if (oldPassword.equals(newPassword)) {
            return "Error: New password cannot be the same as old password.";
        }
        
        // Edge case: Validate new password meets minimum requirements
        if (newPassword == null || newPassword.length() < 8) {
            return "Error: New password must be at least 8 characters long.";
        }
        
        // Edge case: Check for password confirmation match
        if (!newPassword.equals(confirmNewPassword)) {
            return "Error: New passwords do not match.";
        }
        
        // Edge case: Check password complexity (optional but good practice)
        if (!isPasswordStrong(newPassword)) {
            return "Warning: Password is weak. Consider using uppercase, lowercase, numbers, and special characters.";
        }
        
        // Simulate server connection for password change
        try {
            // This simulates the connection to SMOS server mentioned in postconditions
            boolean connectionSuccessful = simulateServerConnection();
            
            if (!connectionSuccessful) {
                return "Error: Connection to server interrupted. Password not changed.";
            }
            
            // Update password in the database
            user.setPassword(newPassword);
            userDatabase.put(username, user);
            
            return "Success: Password has been changed successfully!";
            
        } catch (Exception e) {
            // Handle any unexpected errors during password change
            return "Error: An unexpected error occurred. Please try again later.";
        }
    }
    
    /**
     * Validates password strength (optional but recommended).
     * Checks for minimum length and character variety.
     * 
     * @param password The password to validate
     * @return true if password is strong enough, false otherwise
     */
    private boolean isPasswordStrong(String password) {
        if (password.length() < 8) return false;
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        
        return hasUpper && hasLower && hasDigit;
    }
    
    /**
     * Simulates connection to SMOS server as mentioned in postconditions.
     * In a real application, this would make an actual API call.
     * 
     * @return true if connection is successful, false if interrupted
     */
    private boolean simulateServerConnection() {
        // Simulating a 90% success rate for server connection
        // In real application, this would be an actual network call
        return Math.random() > 0.1; // 90% chance of success
    }
    
    /**
     * Utility method to get a user by username (for testing purposes)
     */
    public User getUser(String username) {
        return userDatabase.get(username);
    }
}

/**
 * Main class that provides console interface for the ChangePassword use case.
 * Simulates the user interaction flow described in the use case.
 */
public class ChangePassword {
    private static Scanner scanner = new Scanner(System.in);
    private static PasswordChangeService passwordService = new PasswordChangeService();
    
    /**
     * Main method - entry point of the program
     */
    public static void main(String[] args) {
        System.out.println("=== Change Password System ===");
        System.out.println("Precondition: User is logged in to the system");
        
        // For demonstration, we'll use a logged-in user
        String loggedInUser = "john_doe";
        System.out.println("Logged in as: " + loggedInUser);
        System.out.println();
        
        // Step 1: System displays the change password form
        displayPasswordChangeForm(loggedInUser);
        
        scanner.close();
    }
    
    /**
     * Displays the password change form and handles user input.
     * This corresponds to System step 1 in the use case.
     * 
     * @param username The logged-in user's username
     */
    private static void displayPasswordChangeForm(String username) {
        System.out.println("=== Change Password Form ===");
        System.out.println("Please fill out the following fields:");
        
        // Get old password input
        System.out.print("Old Password: ");
        String oldPassword = scanner.nextLine();
        
        // Get new password input
        System.out.print("New Password (min 8 characters): ");
        String newPassword = scanner.nextLine();
        
        // Get confirmation of new password
        System.out.print("Confirm New Password: ");
        String confirmNewPassword = scanner.nextLine();
        
        System.out.println();
        System.out.println("Processing your request...");
        
        // Step 2: User submits the form (values are captured above)
        // Step 3: System confirms the password change
        String result = passwordService.changePassword(username, oldPassword, newPassword, confirmNewPassword);
        
        // Display result to user
        System.out.println(result);
        
        // Postconditions check
        if (result.startsWith("Success")) {
            System.out.println("Postcondition: User password has been changed successfully.");
            
            // Verify the password was actually changed
            User updatedUser = passwordService.getUser(username);
            if (updatedUser != null && updatedUser.getPassword().equals(newPassword)) {
                System.out.println("Verification: Password update confirmed in system.");
            }
            
            // Simulate cancellation of connection interruption (per postconditions)
            System.out.println("Connection interruption to SMOS server has been cancelled.");
        } else if (result.contains("Connection")) {
            System.out.println("Postcondition: Connection interruption occurred. Please try again.");
        }
    }
}