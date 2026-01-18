'''
This class acts as a mock data manager for Users.
In a real application, this would interact with a database or a remote service.
It simulates user storage, retrieval, and update operations, including validation.
'''
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays; // Import Arrays for potential role validation list
import java.util.HashSet; // Import HashSet for potential role validation set
import java.util.Set; // Import Set for potential role validation set
public class UserManager {
    // A simple in-memory storage for users, mapping ID to User objects.
    private Map<String, User> userDatabase;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    // Define allowed roles for validation (example)
    private static final Set<String> ALLOWED_ROLES = new HashSet<>(Arrays.asList("Administrator", "Standard User", "Moderator"));
    // Added field to simulate admin login status
    private boolean adminLoggedIn = true; 
    /**
     * Constructs a UserManager instance and initializes a mock user database.
     */
    public UserManager() {
        userDatabase = new HashMap<>();
        // Populate with some dummy data
        userDatabase.put("U001", new User("U001", "John Doe", "john.doe@example.com", "Administrator"));
        userDatabase.put("U002", new User("U002", "Jane Smith", "jane.smith@example.com", "Standard User"));
        userDatabase.put("U003", new User("U003", "Admin User", "admin@company.com", "Administrator"));
    }
    /**
     * Simulates checking if the current user is logged in as an administrator.
     * For this simulation, the status can be toggled externally.
     * In a real application, this would involve session management and authentication.
     * @return true if the user is an administrator, false otherwise.
     */
    public boolean isLoggedInAsAdmin() {
        // Precondition: The user is logged in as an administrator
        // In a real system, this would check the session/authentication context.
        System.out.println("DEBUG: Administrator login status checked: " + adminLoggedIn);
        return adminLoggedIn;
    }
    /**
     * Sets the simulated administrator login status.
     * This method is for demonstration purposes to toggle the admin login precondition.
     * @param status The new login status for the simulated administrator.
     */
    public void setAdminLoggedIn(boolean status) {
        this.adminLoggedIn = status;
        System.out.println("DEBUG: Admin login status set to: " + status);
    }
    /**
     * Simulates the "viewdetTailsente" (view details) use case.
     * Retrieves a user by their ID from the mock database.
     * @param userId The ID of the user to view.
     * @return The User object if found, null otherwise.
     */
    public User viewUserDetails(String userId) {
        System.out.println("DEBUG: Viewing details for user ID: " + userId);
        return userDatabase.get(userId);
    }
    /**
     * Validates the provided user data (name, email, and role).
     * @param name The user's name.
     * @param email The user's email.
     * @param role The user's role.
     * @return true if the data is valid, false otherwise.
     */
    public boolean isValidUserData(String name, String email, String role) {
        // Basic validation: Name should not be empty, email should be valid format.
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Validation Error: User name cannot be empty.");
            return false;
        }
        if (email == null || email.trim().isEmpty()) {
            System.err.println("Validation Error: User email cannot be empty.");
            return false;
        }
        // Validate email format
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            System.err.println("Validation Error: Invalid email format: " + email);
            return false;
        }
        // Validate role (added based on comments)
        if (role == null || role.trim().isEmpty()) {
            System.err.println("Validation Error: User role cannot be empty.");
            return false;
        }
        // Example of more restrictive role validation: check against a predefined set
        if (!ALLOWED_ROLES.contains(role)) {
            System.err.println("Validation Error: Invalid user role: '" + role + "'. Allowed roles are: " + ALLOWED_ROLES);
            return false;
        }
        return true;
    }
    /**
     * Updates an existing user's information in the system.
     * This method assumes validation has already passed.
     * In a real system, this would persist changes to a database.
     * @param updatedUser The User object containing the updated information.
     * @return true if the user was successfully updated, false if the user ID does not exist.
     */
    public boolean updateUser(User updatedUser) {
        if (updatedUser == null || updatedUser.getId() == null) {
            System.err.println("Update Error: Updated user object or ID cannot be null.");
            return false;
        }
        if (userDatabase.containsKey(updatedUser.getId())) {
            // In a real scenario, you'd update specific fields.
            // Here, we replace the entire object for simplicity.
            userDatabase.put(updatedUser.getId(), updatedUser);
            System.out.println("DEBUG: User updated successfully: " + updatedUser.getId());
            return true;
        } else {
            System.err.println("Update Error: User with ID " + updatedUser.getId() + " not found.");
            return false;
        }
    }
    /**
     * Simulates external system connection interruption.
     * This method prints a message to indicate the interruption as per the postcondition.
     */
    public void interruptSMOSConnection() {
        System.out.println("DEBUG: Administrator interrupts connection operation to the SMOS server.");
        // In a real application, this might involve closing network connections,
        // rolling back transactions, or logging an administrative action.
    }
}