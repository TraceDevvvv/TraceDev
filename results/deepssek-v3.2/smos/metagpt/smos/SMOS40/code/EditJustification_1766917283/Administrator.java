import java.util.HashMap;
import java.util.Map;

/**
 * Represents an administrator user in the system.
 * Handles authentication, authorization, and user session management.
 */
public class Administrator {
    private String username;
    private String fullName;
    private String role;
    private boolean isLoggedIn;
    private String sessionId;
    private static final Map<String, String> VALID_USERS = new HashMap<>();
    
    // Static initialization block for valid admin users
    static {
        VALID_USERS.put("admin1", "AdminPass123!");
        VALID_USERS.put("superadmin", "SuperPass456!");
        VALID_USERS.put("sysadmin", "SystemPass789!");
    }
    
    /**
     * Constructor for creating an administrator.
     * 
     * @param username The administrator's username
     * @param fullName The administrator's full name
     */
    public Administrator(String username, String fullName) {
        this.username = username;
        this.fullName = fullName;
        this.role = "ADMINISTRATOR";
        this.isLoggedIn = false;
        this.sessionId = null;
    }
    
    /**
     * Attempts to log in the administrator.
     * Validates credentials and creates a session if successful.
     * 
     * @return true if login successful, false otherwise
     */
    public boolean login() {
        // Check if already logged in
        if (isLoggedIn) {
            System.out.println("User " + username + " is already logged in.");
            return true;
        }
        
        // Validate username exists in system
        if (!VALID_USERS.containsKey(username)) {
            System.out.println("✗ Login failed: Username not found.");
            return false;
        }
        
        // Simulate password prompt - in real application, this would come from secure input
        System.out.println("\n--- Administrator Login ---");
        System.out.print("Enter password for " + username + ": ");
        
        // For demonstration, we'll simulate password validation
        // In a real application, this would use secure password hashing
        String simulatedPassword = getSimulatedPasswordInput();
        
        // Validate password (in real system, compare hashed passwords)
        String storedPassword = VALID_USERS.get(username);
        if (storedPassword.equals(simulatedPassword)) {
            // Generate session ID
            sessionId = generateSessionId();
            isLoggedIn = true;
            
            // Log successful login
            System.out.println("✓ Login successful for " + fullName + " (" + username + ")");
            System.out.println("Session ID: " + sessionId);
            System.out.println("Role: " + role);
            return true;
        } else {
            System.out.println("✗ Login failed: Invalid password.");
            return false;
        }
    }
    
    /**
     * Logs out the administrator from the system.
     * Clears session information and updates login status.
     */
    public void logout() {
        if (isLoggedIn) {
            System.out.println("Logging out " + username + "...");
            isLoggedIn = false;
            sessionId = null;
            System.out.println("✓ Logout successful.");
        } else {
            System.out.println("No active session to logout.");
        }
    }
    
    /**
     * Checks if the administrator has permission to edit justifications.
     * 
     * @param justificationId The ID of the justification to edit
     * @return true if administrator has edit permissions
     */
    public boolean canEditJustification(int justificationId) {
        if (!isLoggedIn) {
            System.out.println("Permission denied: User not logged in.");
            return false;
        }
        
        // All administrators can edit any justification
        // In a more complex system, this might check specific permissions
        System.out.println("✓ Edit permission granted for justification #" + justificationId);
        return true;
    }
    
    /**
     * Validates if the administrator is currently logged in.
     * 
     * @return true if logged in and session is valid
     */
    public boolean isLoggedIn() {
        // Additional session validation could be added here
        return isLoggedIn;
    }
    
    /**
     * Gets the administrator's username.
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gets the administrator's full name.
     * 
     * @return full name
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * Gets the administrator's role.
     * 
     * @return role
     */
    public String getRole() {
        return role;
    }
    
    /**
     * Gets the current session ID.
     * 
     * @return session ID if logged in, null otherwise
     */
    public String getSessionId() {
        return sessionId;
    }
    
    /**
     * Simulates receiving password input.
     * In a real application, this would use secure input handling.
     * 
     * @return simulated password input
     */
    private String getSimulatedPasswordInput() {
        // For demonstration purposes, return the correct password from stored users
        // In a real application, this would capture actual user input
        return VALID_USERS.get(username);
    }
    
    /**
     * Generates a unique session ID for the administrator.
     * In a real application, this would use a secure random generator.
     * 
     * @return generated session ID
     */
    private String generateSessionId() {
        long timestamp = System.currentTimeMillis();
        int random = (int)(Math.random() * 10000);
        return "SESS-" + username.toUpperCase() + "-" + timestamp + "-" + random;
    }
    
    /**
     * Validates the administrator's session.
     * 
     * @return true if session is valid and not expired
     */
    public boolean validateSession() {
        if (!isLoggedIn || sessionId == null) {
            return false;
        }
        
        // In a real system, this would check session expiration
        // and validate against session store
        return true;
    }
    
    /**
     * Checks if the administrator has the required role for an operation.
     * 
     * @param requiredRole The required role
     * @return true if administrator has the role or higher privileges
     */
    public boolean hasRole(String requiredRole) {
        if (!isLoggedIn) {
            return false;
        }
        
        // Simple role hierarchy: SUPERADMIN > ADMINISTRATOR > USER
        Map<String, Integer> roleHierarchy = new HashMap<>();
        roleHierarchy.put("USER", 1);
        roleHierarchy.put("ADMINISTRATOR", 2);
        roleHierarchy.put("SUPERADMIN", 3);
        
        Integer currentRank = roleHierarchy.get(role.toUpperCase());
        Integer requiredRank = roleHierarchy.get(requiredRole.toUpperCase());
        
        if (currentRank == null || requiredRank == null) {
            return false;
        }
        
        return currentRank >= requiredRank;
    }
    
    /**
     * Returns a string representation of the administrator.
     */
    @Override
    public String toString() {
        return String.format("Administrator{username='%s', fullName='%s', role='%s', loggedIn=%s}", 
                           username, fullName, role, isLoggedIn);
    }
    
    /**
     * Displays administrator information.
     */
    public void displayInfo() {
        System.out.println("\n--- Administrator Information ---");
        System.out.println("Username: " + username);
        System.out.println("Full Name: " + fullName);
        System.out.println("Role: " + role);
        System.out.println("Logged In: " + (isLoggedIn ? "Yes" : "No"));
        if (isLoggedIn) {
            System.out.println("Session ID: " + sessionId);
        }
        System.out.println("---------------------------------");
    }
}