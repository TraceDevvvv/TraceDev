import java.util.Scanner;
/**
 * Handles the user editing process including validation and error handling.
 * Simulates the administrator workflow according to the use case.
 */
public class UserEditor {
    private UserDataStore dataStore;
    private String currentAdminId;
    private boolean isLoggedIn;
    public UserEditor() {
        dataStore = new UserDataStore();
        isLoggedIn = false;
    }
    /**
     * Simulates administrator login. In a real system, this would involve proper authentication.
     * For simulation purposes, we accept any non-empty admin ID.
     * @param adminId The administrator ID.
     * @return true if login successful, false otherwise.
     */
    public boolean login(String adminId) {
        if (adminId != null && !adminId.trim().isEmpty()) {
            currentAdminId = adminId;
            isLoggedIn = true;
            return true;
        }
        return false;
    }
    /**
     * Displays user details (simulates "viewdetTailsente" use case).
     * @param userId The ID of the user whose details to display.
     * @return User object if found, null otherwise.
     */
    public User viewUserDetails(String userId) {
        if (!isLoggedIn) {
            System.out.println("Error: Administrator not logged in.");
            return null;
        }
        User user = dataStore.getUser(userId);
        if (user != null) {
            System.out.println("\n=== User Details ===");
            System.out.println(user.toString());
        } else {
            System.out.println("Error: User with ID '" + userId + "' not found.");
        }
        return user;
    }
    /**
     * Validates the edited user data using DataValidator class.
     * @param name The name to validate.
     * @param email The email to validate.
     * @return true if data is valid, false otherwise.
     */
    private boolean validateData(String name, String email) {
        if (!DataValidator.isValidName(name)) {
            System.out.println("Error: Name must be 2-100 characters and contain only letters, spaces, hyphens, apostrophes, or periods.");
            return false;
        }
        if (!DataValidator.isValidEmail(email)) {
            System.out.println("Error: Invalid email format. Please use standard email format (e.g., user@example.com).");
            return false;
        }
        return true;
    }
    /**
     * Handles the edit user workflow.
     * 1. User clicks "Edit" button (simulated by method call)
     * 2. System validates data entered
     * 3. Updates user if valid, triggers error handling if invalid
     * @param userId The ID of the user to edit.
     * @param newName The new name for the user.
     * @param newEmail The new email for the user.
     */
    public void editUser(String userId, String newName, String newEmail) {
        if (!isLoggedIn) {
            System.out.println("Error: Administrator not logged in.");
            return;
        }
        // Check if user exists
        User user = dataStore.getUser(userId);
        if (user == null) {
            System.out.println("Error: User with ID '" + userId + "' not found.");
            triggerErrorCase("UserNotFound");
            return;
        }
        // Validate the new data
        if (!validateData(newName, newEmail)) {
            System.out.println("Error: Invalid data entered.");
            triggerErrorCase("Errodati");
            return;
        }
        // Update the user
        boolean success = dataStore.updateUser(userId, newName, newEmail);
        if (success) {
            System.out.println("Success: User '" + userId + "' has been modified.");
            // Simulate notification of successful modification
            notifyDataModified(userId);
        } else {
            System.out.println("Error: Failed to update user.");
        }
    }
    /**
     * Triggers the error case ("Errodati") as specified in the use case.
     * The administrator interrupts the connection operation to the SMOS server.
     * @param errorType The type of error encountered.
     */
    private void triggerErrorCase(String errorType) {
        System.out.println("Activating error case: " + errorType);
        System.out.println("The administrator interrupts the connection operation to the SMOS server.");
        // In a real system, this would involve proper error handling and connection termination
    }
    /**
     * Simulates notification of data modification.
     * @param userId The ID of the user that was modified.
     */
    private void notifyDataModified(String userId) {
        System.out.println("Notification: User '" + userId + "' data has been successfully modified.");
    }
    /**
     * Simulates administrator logout and connection interruption.
     */
    public void logout() {
        if (isLoggedIn) {
            System.out.println("Administrator '" + currentAdminId + "' has logged out.");
            System.out.println("Connection to SMOS server interrupted.");
            isLoggedIn = false;
            currentAdminId = null;
        }
    }
}