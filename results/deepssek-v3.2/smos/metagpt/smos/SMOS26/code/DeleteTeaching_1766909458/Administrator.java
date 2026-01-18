package workspace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an administrator user with authentication and permission management.
 * This class handles administrator login, permissions, and logging of actions
 * for audit purposes as required by the delete teaching use case.
 */
public class Administrator {
    private String username;
    private String passwordHash; // In a real system, passwords should be hashed
    private boolean isLoggedIn;
    private boolean hasDeletePermission;
    private boolean hasClearArchivePermission;
    private List<String> actionLog;
    
    // Default administrator credentials (for demonstration)
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin123"; // In production, use secure password hashing
    
    /**
     * Constructs a new Administrator with default credentials.
     * By default, administrators have delete permission but must log in first.
     */
    public Administrator() {
        this.username = DEFAULT_USERNAME;
        this.passwordHash = hashPassword(DEFAULT_PASSWORD); // Simple hash for demonstration
        this.isLoggedIn = false;
        this.hasDeletePermission = true; // Administrators typically have delete permission
        this.hasClearArchivePermission = true; // Administrators can clear archive
        this.actionLog = new ArrayList<>();
    }
    
    /**
     * Constructs a new Administrator with custom credentials.
     * 
     * @param username The administrator's username
     * @param password The administrator's password (will be hashed)
     */
    public Administrator(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        
        this.username = username.trim();
        this.passwordHash = hashPassword(password);
        this.isLoggedIn = false;
        this.hasDeletePermission = true;
        this.hasClearArchivePermission = true;
        this.actionLog = new ArrayList<>();
    }
    
    /**
     * Attempts to log in the administrator.
     * Simulates authentication by checking credentials.
     * 
     * @return true if login is successful, false otherwise
     */
    public boolean login() {
        return login(DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }
    
    /**
     * Attempts to log in the administrator with provided credentials.
     * 
     * @param inputUsername The username to authenticate
     * @param inputPassword The password to authenticate
     * @return true if login is successful, false otherwise
     */
    public boolean login(String inputUsername, String inputPassword) {
        if (inputUsername == null || inputPassword == null) {
            logAction("Login attempted with null credentials", false);
            return false;
        }
        
        // Check credentials
        boolean success = username.equals(inputUsername.trim()) && 
                         checkPassword(inputPassword, passwordHash);
        
        if (success) {
            isLoggedIn = true;
            logAction("User '" + username + "' logged in successfully", true);
            System.out.println("Administrator " + username + " is now logged in.");
        } else {
            logAction("Failed login attempt for username: " + inputUsername, false);
            System.out.println("Login failed: Invalid credentials.");
        }
        
        return success;
    }
    
    /**
     * Logs out the administrator.
     * 
     * @return true if logout is successful, false if not logged in
     */
    public boolean logout() {
        if (!isLoggedIn) {
            System.out.println("No user is currently logged in.");
            return false;
        }
        
        logAction("User '" + username + "' logged out", true);
        System.out.println("Administrator " + username + " has been logged out.");
        isLoggedIn = false;
        return true;
    }
    
    /**
     * Checks if the administrator has delete permission.
     * Note: Administrator must be logged in to exercise permissions.
     * 
     * @return true if administrator has delete permission and is logged in
     */
    public boolean hasDeletePermission() {
        if (!isLoggedIn) {
            System.out.println("Warning: Checking permissions while not logged in.");
            return false;
        }
        return hasDeletePermission;
    }
    
    /**
     * Checks if the administrator has clear archive permission.
     * Note: Administrator must be logged in to exercise permissions.
     * 
     * @return true if administrator has clear archive permission and is logged in
     */
    public boolean hasClearArchivePermission() {
        if (!isLoggedIn) {
            System.out.println("Warning: Checking permissions while not logged in.");
            return false;
        }
        return hasClearArchivePermission;
    }
    
    /**
     * Simulates a password hashing function.
     * In a real system, use a secure hashing algorithm like bcrypt, scrypt, or Argon2.
     * 
     * @param password The plain text password
     * @return A simulated hash of the password
     */
    private String hashPassword(String password) {
        // This is a simple simulation for demonstration purposes
        // In production, use: return BCrypt.hashpw(password, BCrypt.gensalt());
        return "hashed_" + password.hashCode();
    }
    
    /**
     * Checks if the provided plain text password matches the stored hash.
     * 
     * @param plainPassword The plain text password to check
     * @param storedHash The stored password hash
     * @return true if the password matches the hash, false otherwise
     */
    private boolean checkPassword(String plainPassword, String storedHash) {
        // In a real system, use: return BCrypt.checkpw(plainPassword, storedHash);
        String inputHash = hashPassword(plainPassword);
        return storedHash.equals(inputHash);
    }
    
    /**
     * Logs an administrator action for audit purposes.
     * 
     * @param actionDescription Description of the action performed
     * @param success Whether the action was successful
     */
    public void logAction(String actionDescription, boolean success) {
        String timestamp = LocalDateTime.now().toString();
        String status = success ? "SUCCESS" : "FAILURE";
        String logEntry = String.format("[%s] [%s] %s - User: %s", 
                                        timestamp, status, actionDescription, username);
        actionLog.add(logEntry);
        
        // In a real system, also write to a file or database
        System.out.println("Action logged: " + logEntry);
    }
    
    /**
     * Logs the deletion of a teaching for audit purposes.
     * 
     * @param teachingId The ID of the teaching that was deleted
     * @param courseName The name of the course that was deleted
     */
    public void logDeletion(int teachingId, String courseName) {
        if (!isLoggedIn) {
            System.out.println("Warning: Logging deletion while not logged in.");
        }
        
        String action = String.format("Deleted teaching - ID: %d, Course: %s", 
                                     teachingId, courseName);
        logAction(action, true);
    }
    
    /**
     * Logs the clearing of the archive for audit purposes.
     * 
     * @param teachingsCount The number of teachings that were cleared
     */
    public void logArchiveClear(int teachingsCount) {
        if (!isLoggedIn) {
            System.out.println("Warning: Logging archive clear while not logged in.");
        }
        
        String action = String.format("Cleared archive - Removed %d teachings", teachingsCount);
        logAction(action, true);
    }
    
    /**
     * Gets the action log for this administrator.
     * Returns a defensive copy of the action log.
     * 
     * @return List of logged actions
     */
    public List<String> getActionLog() {
        return new ArrayList<>(actionLog);
    }
    
    /**
     * Gets the username of this administrator.
     * 
     * @return The administrator's username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Checks if the administrator is currently logged in.
     * 
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * Clears the action log (for testing/reset purposes).
     * Requires administrator to be logged in.
     * 
     * @return true if the log was cleared successfully
     */
    public boolean clearActionLog() {
        if (!isLoggedIn) {
            System.out.println("Error: Cannot clear action log while not logged in.");
            return false;
        }
        
        int count = actionLog.size();
        actionLog.clear();
        logAction("Cleared action log - Removed " + count + " entries", true);
        return true;
    }
    
    /**
     * Revokes delete permission from this administrator.
     * In a real system, this would be done by a super-admin or through a role management system.
     * 
     * @return true if permission was revoked, false if already revoked
     */
    public boolean revokeDeletePermission() {
        if (!hasDeletePermission) {
            return false;
        }
        
        hasDeletePermission = false;
        logAction("Delete permission revoked", true);
        return true;
    }
    
    /**
     * Grants delete permission to this administrator.
     * 
     * @return true if permission was granted, false if already granted
     */
    public boolean grantDeletePermission() {
        if (hasDeletePermission) {
            return false;
        }
        
        hasDeletePermission = true;
        logAction("Delete permission granted", true);
        return true;
    }
    
    /**
     * Changes the administrator's password.
     * Requires the old password for verification.
     * 
     * @param oldPassword The current password
     * @param newPassword The new password
     * @return true if password was changed successfully, false otherwise
     */
    public boolean changePassword(String oldPassword, String newPassword) {
        if (!isLoggedIn) {
            System.out.println("Error: Must be logged in to change password.");
            return false;
        }
        
        if (!checkPassword(oldPassword, passwordHash)) {
            logAction("Password change failed - Incorrect old password", false);
            return false;
        }
        
        if (newPassword == null || newPassword.isEmpty()) {
            logAction("Password change failed - New password is invalid", false);
            return false;
        }
        
        // Update password hash
        passwordHash = hashPassword(newPassword);
        logAction("Password changed successfully", true);
        return true;
    }
    
    /**
     * Returns a string representation of the administrator status.
     * 
     * @return String containing administrator information
     */
    @Override
    public String toString() {
        return String.format("Administrator{username='%s', loggedIn=%s, hasDeletePermission=%s}",
                            username, isLoggedIn, hasDeletePermission);
    }
}