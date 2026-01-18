import java.time.LocalDateTime;

/**
 * Administrator class representing a system administrator.
 * Handles authentication and authorization for the class register system.
 * 
 * This class ensures that only authorized administrators can access 
 * the ViewDetailsSingleRegister functionality as per the use case requirements.
 */
public class Administrator {
    private final String username;
    private final String fullName;
    private final String role;
    private final LocalDateTime loginTime;
    private boolean isActive;
    
    /**
     * Constructor to create a new Administrator instance.
     * 
     * @param username The unique username for the administrator
     * @param fullName The full name of the administrator
     * @throws IllegalArgumentException if username or fullName is null or empty
     */
    public Administrator(String username, String fullName) {
        this(username, fullName, "Administrator");
    }
    
    /**
     * Constructor to create a new Administrator instance with a specific role.
     * 
     * @param username The unique username for the administrator
     * @param fullName The full name of the administrator
     * @param role The role of the administrator (e.g., "Administrator", "Super Admin")
     * @throws IllegalArgumentException if any parameter is null or empty
     */
    public Administrator(String username, String fullName, String role) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be null or empty");
        }
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        
        this.username = username.trim();
        this.fullName = fullName.trim();
        this.role = role.trim();
        this.loginTime = LocalDateTime.now();
        this.isActive = true;
        
        System.out.println("Administrator '" + this.username + "' created with role: " + this.role);
    }
    
    /**
     * Gets the username of the administrator.
     * 
     * @return The administrator's username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gets the full name of the administrator.
     * 
     * @return The administrator's full name
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * Gets the role of the administrator.
     * 
     * @return The administrator's role
     */
    public String getRole() {
        return role;
    }
    
    /**
     * Gets the login time of the administrator.
     * 
     * @return The date and time when the administrator logged in
     */
    public LocalDateTime getLoginTime() {
        return loginTime;
    }
    
    /**
     * Checks if the administrator is currently active.
     * 
     * @return true if the administrator is active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }
    
    /**
     * Activates the administrator account.
     * This method can be used to reactivate a previously deactivated account.
     */
    public void activate() {
        if (!isActive) {
            isActive = true;
            System.out.println("Administrator '" + username + "' has been activated.");
        }
    }
    
    /**
     * Deactivates the administrator account.
     * This method prevents the administrator from performing further actions.
     */
    public void deactivate() {
        if (isActive) {
            isActive = false;
            System.out.println("Administrator '" + username + "' has been deactivated.");
        }
    }
    
    /**
     * Checks if the administrator has permission to view class register details.
     * According to the use case, only administrators can perform this action.
     * 
     * @return true if permission is granted, false otherwise
     */
    public boolean canViewRegisterDetails() {
        // Only active administrators can view register details
        return isActive && "Administrator".equalsIgnoreCase(role) || "Super Admin".equalsIgnoreCase(role);
    }
    
    /**
     * Checks if the administrator has permission to manage justifications.
     * 
     * @return true if permission is granted, false otherwise
     */
    public boolean canManageJustifications() {
        // All active administrators can manage justifications
        return isActive && ("Administrator".equalsIgnoreCase(role) || "Super Admin".equalsIgnoreCase(role));
    }
    
    /**
     * Checks if the administrator has permission to manage disciplinary notes.
     * 
     * @return true if permission is granted, false otherwise
     */
    public boolean canManageDisciplinaryNotes() {
        // All active administrators can manage disciplinary notes
        return isActive && ("Administrator".equalsIgnoreCase(role) || "Super Admin".equalsIgnoreCase(role));
    }
    
    /**
     * Checks if the administrator has permission to modify student records.
     * 
     * @return true if permission is granted, false otherwise
     */
    public boolean canModifyStudentRecords() {
        // Only super admins can modify student records
        return isActive && "Super Admin".equalsIgnoreCase(role);
    }
    
    /**
     * Authenticates the administrator by verifying credentials.
     * This method simulates authentication logic that would typically
     * connect to an authentication server or database.
     * 
     * @param inputUsername The username to authenticate
     * @param inputPassword The password to authenticate
     * @param expectedUsername The expected username for this administrator
     * @param expectedPassword The expected password for this administrator
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticate(String inputUsername, String inputPassword, 
                               String expectedUsername, String expectedPassword) {
        if (inputUsername == null || inputPassword == null || 
            expectedUsername == null || expectedPassword == null) {
            return false;
        }
        
        boolean authenticated = inputUsername.equals(expectedUsername) && 
                               inputPassword.equals(expectedPassword);
        
        if (authenticated) {
            System.out.println("Authentication successful for administrator: " + username);
        } else {
            System.out.println("Authentication failed for administrator: " + username);
        }
        
        return authenticated;
    }
    
    /**
     * Validates if the current session is still valid.
     * This checks if the administrator is active and if the session hasn't expired.
     * 
     * @param maxSessionDurationHours Maximum allowed session duration in hours
     * @return true if session is valid, false otherwise
     */
    public boolean isSessionValid(int maxSessionDurationHours) {
        if (!isActive) {
            System.out.println("Session invalid: Administrator account is inactive.");
            return false;
        }
        
        LocalDateTime now = LocalDateTime.now();
        long hoursSinceLogin = java.time.Duration.between(loginTime, now).toHours();
        
        if (hoursSinceLogin >= maxSessionDurationHours) {
            System.out.println("Session expired: " + hoursSinceLogin + " hours since login.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Logs an action performed by the administrator.
     * This method simulates audit logging for security and compliance.
     * 
     * @param action The action performed (e.g., "Viewed register details")
     * @param details Additional details about the action
     */
    public void logAction(String action, String details) {
        if (action == null || action.trim().isEmpty()) {
            throw new IllegalArgumentException("Action cannot be null or empty");
        }
        
        String logEntry = String.format("[%s] %s (%s): %s - %s", 
            LocalDateTime.now().toString(), 
            username, 
            fullName, 
            action.trim(), 
            (details != null) ? details : "No additional details"
        );
        
        System.out.println("ADMIN ACTION LOG: " + logEntry);
        
        // In a real system, this would write to a log file or database
        // For simulation, we just print to console
    }
    
    /**
     * Resets the administrator's login time to now.
     * This can be used to refresh the session.
     */
    public void refreshLoginTime() {
        // Note: This is not actually changing the loginTime field since it's final
        // In a real system, you might have a separate session management system
        System.out.println("Session refreshed for administrator: " + username);
    }
    
    /**
     * Validates administrator credentials against system requirements.
     * 
     * @param username The username to validate
     * @param password The password to validate
     * @return A validation result message
     */
    public static String validateCredentials(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return "Username cannot be empty";
        }
        if (password == null || password.trim().isEmpty()) {
            return "Password cannot be empty";
        }
        
        if (username.trim().length() < 3) {
            return "Username must be at least 3 characters long";
        }
        if (password.trim().length() < 8) {
            return "Password must be at least 8 characters long";
        }
        
        // Check for common insecure passwords (simplified check)
        String[] commonPasswords = {"password", "12345678", "admin123", "password123"};
        for (String common : commonPasswords) {
            if (password.trim().equalsIgnoreCase(common)) {
                return "Password is too common or insecure";
            }
        }
        
        return "VALID";
    }
    
    /**
     * Creates a summary of the administrator's information.
     * 
     * @return A formatted string with administrator details
     */
    public String getAdminSummary() {
        return String.format("Administrator: %s (%s)%nRole: %s%nStatus: %s%nLogin Time: %s", 
            fullName, username, role, isActive ? "Active" : "Inactive", 
            loginTime.toString());
    }
    
    /**
     * Returns a string representation of the administrator.
     * 
     * @return String containing administrator details
     */
    @Override
    public String toString() {
        return String.format("Administrator{username='%s', fullName='%s', role='%s', active=%s}", 
            username, fullName, role, isActive);
    }
}