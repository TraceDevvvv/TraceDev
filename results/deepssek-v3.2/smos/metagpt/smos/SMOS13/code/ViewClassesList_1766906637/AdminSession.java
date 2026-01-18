import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * AdminSession class handles administrator authentication, session management,
 * and user permissions. It ensures secure session handling with timeout
 * and proper resource cleanup.
 */
public class AdminSession {
    
    // Session properties
    private String sessionId;
    private String username;
    private boolean authenticated;
    private LocalDateTime loginTime;
    private LocalDateTime lastActivityTime;
    private int sessionTimeoutMinutes;
    
    // Constants
    private static final int DEFAULT_SESSION_TIMEOUT = 30; // 30 minutes
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123"; // In real system, use secure password hashing
    
    /**
     * Constructor for creating an AdminSession with credentials.
     * Automatically attempts authentication.
     * 
     * @param username The username provided for authentication
     * @param password The password provided for authentication
     */
    public AdminSession(String username, String password) {
        this.sessionId = generateSessionId();
        this.username = (username != null) ? username.trim() : "";
        this.authenticated = false;
        this.loginTime = LocalDateTime.now();
        this.lastActivityTime = this.loginTime;
        this.sessionTimeoutMinutes = DEFAULT_SESSION_TIMEOUT;
        
        // Attempt authentication
        authenticate(username, password);
    }
    
    /**
     * Authenticates the user with provided credentials.
     * In a real system, this would connect to an authentication service.
     * 
     * @param username The username to authenticate
     * @param password The password to verify
     * @return true if authentication successful, false otherwise
     */
    private boolean authenticate(String username, String password) {
        // Validate input parameters
        if (username == null || password == null) {
            System.out.println("Authentication failed: Username or password is null");
            return false;
        }
        
        String trimmedUsername = username.trim();
        String trimmedPassword = password.trim();
        
        // Check for empty credentials
        if (trimmedUsername.isEmpty() || trimmedPassword.isEmpty()) {
            System.out.println("Authentication failed: Username or password is empty");
            return false;
        }
        
        // In a real system, this would:
        // 1. Hash the password
        // 2. Query the user database
        // 3. Verify against stored credentials
        // 4. Check user role/privileges
        
        // For simulation, we'll use hardcoded admin credentials
        if (trimmedUsername.equals(ADMIN_USERNAME) && trimmedPassword.equals(ADMIN_PASSWORD)) {
            this.authenticated = true;
            this.username = trimmedUsername;
            updateLastActivityTime();
            System.out.println("Authentication successful for user: " + trimmedUsername);
            return true;
        } else {
            System.out.println("Authentication failed: Invalid credentials for user: " + trimmedUsername);
            return false;
        }
    }
    
    /**
     * Generates a unique session ID for tracking the session.
     * 
     * @return A unique session identifier
     */
    private String generateSessionId() {
        return "SESSION-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    /**
     * Updates the last activity time to the current time.
     * This should be called whenever the user performs an action.
     */
    public void updateLastActivityTime() {
        this.lastActivityTime = LocalDateTime.now();
    }
    
    /**
     * Checks if the current session is still valid (not timed out).
     * 
     * @return true if session is valid, false if timed out
     */
    public boolean isSessionValid() {
        if (!authenticated) {
            return false;
        }
        
        LocalDateTime now = LocalDateTime.now();
        long minutesSinceLastActivity = ChronoUnit.MINUTES.between(lastActivityTime, now);
        
        return minutesSinceLastActivity <= sessionTimeoutMinutes;
    }
    
    /**
     * Checks if the user is authenticated and session is valid.
     * 
     * @return true if user is authenticated and session is valid, false otherwise
     */
    public boolean isAuthenticated() {
        return authenticated && isSessionValid();
    }
    
    /**
     * Gets the session ID.
     * 
     * @return The session ID
     */
    public String getSessionId() {
        return sessionId;
    }
    
    /**
     * Gets the username associated with this session.
     * 
     * @return The username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gets the login time for this session.
     * 
     * @return The login time
     */
    public LocalDateTime getLoginTime() {
        return loginTime;
    }
    
    /**
     * Gets the last activity time for this session.
     * 
     * @return The last activity time
     */
    public LocalDateTime getLastActivityTime() {
        return lastActivityTime;
    }
    
    /**
     * Gets the session timeout in minutes.
     * 
     * @return Session timeout in minutes
     */
    public int getSessionTimeoutMinutes() {
        return sessionTimeoutMinutes;
    }
    
    /**
     * Sets the session timeout in minutes.
     * 
     * @param sessionTimeoutMinutes New session timeout in minutes (must be positive)
     * @throws IllegalArgumentException if timeout is not positive
     */
    public void setSessionTimeoutMinutes(int sessionTimeoutMinutes) {
        if (sessionTimeoutMinutes <= 0) {
            throw new IllegalArgumentException("Session timeout must be positive");
        }
        this.sessionTimeoutMinutes = sessionTimeoutMinutes;
    }
    
    /**
     * Gets the session duration in minutes.
     * 
     * @return Session duration in minutes
     */
    public long getSessionDurationMinutes() {
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.MINUTES.between(loginTime, now);
    }
    
    /**
     * Gets the time remaining until session timeout in minutes.
     * 
     * @return Minutes remaining until session timeout, negative if already timed out
     */
    public long getTimeRemainingMinutes() {
        if (!authenticated) {
            return 0;
        }
        
        LocalDateTime now = LocalDateTime.now();
        long minutesSinceLastActivity = ChronoUnit.MINUTES.between(lastActivityTime, now);
        
        return sessionTimeoutMinutes - minutesSinceLastActivity;
    }
    
    /**
     * Checks if the session has timed out.
     * 
     * @return true if session has timed out, false otherwise
     */
    public boolean isTimedOut() {
        return !isSessionValid();
    }
    
    /**
     * Logs out the user and invalidates the session.
     * This should be called when the user explicitly logs out or when cleaning up.
     */
    public void logout() {
        if (authenticated) {
            System.out.println("Logging out user: " + username);
            
            // Calculate session statistics
            long sessionDuration = getSessionDurationMinutes();
            System.out.println("Session duration: " + sessionDuration + " minutes");
            
            // Invalidate session
            this.authenticated = false;
            this.sessionId = "INVALIDATED-" + this.sessionId;
            
            System.out.println("Session invalidated for user: " + username);
        }
    }
    
    /**
     * Validates that the current session has admin privileges.
     * In a real system, this would check user roles in the database.
     * 
     * @return true if user has admin privileges, false otherwise
     */
    public boolean hasAdminPrivileges() {
        if (!isAuthenticated()) {
            return false;
        }
        
        // In a real system, this would query user roles from database
        // For simulation, we'll assume all authenticated users in this system are admins
        // (since this is specifically for the ViewClassesList use case for administrators)
        return true;
    }
    
    /**
     * Checks if the user can perform a specific action based on permissions.
     * This is a placeholder for a more comprehensive permission system.
     * 
     * @param action The action to check (e.g., "VIEW_CLASSES", "MANAGE_CLASSES")
     * @return true if user has permission for the action, false otherwise
     */
    public boolean hasPermission(String action) {
        if (!isAuthenticated()) {
            return false;
        }
        
        if (action == null || action.trim().isEmpty()) {
            return false;
        }
        
        // In a real system, this would check against a permission matrix or RBAC system
        // For this use case, we'll implement basic permission checks
        
        String normalizedAction = action.trim().toUpperCase();
        
        switch (normalizedAction) {
            case "VIEW_CLASSES":
            case "MANAGE_CLASSES":
            case "VIEW_REPORTS":
                return hasAdminPrivileges();
            case "EDIT_CLASSES":
            case "DELETE_CLASSES":
                // Additional security check for destructive operations
                return hasAdminPrivileges() && isHighSecuritySession();
            default:
                // Unknown action - deny by default
                return false;
        }
    }
    
    /**
     * Checks if this is a high-security session (e.g., recently authenticated).
     * 
     * @return true if session is considered high-security, false otherwise
     */
    private boolean isHighSecuritySession() {
        if (!isAuthenticated()) {
            return false;
        }
        
        // Consider session high-security if authenticated within last 5 minutes
        LocalDateTime now = LocalDateTime.now();
        long minutesSinceLogin = ChronoUnit.MINUTES.between(loginTime, now);
        
        return minutesSinceLogin <= 5;
    }
    
    /**
     * Gets a session summary for logging or display purposes.
     * 
     * @return A formatted session summary
     */
    public String getSessionSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== Session Summary ===\n");
        summary.append("Session ID: ").append(sessionId).append("\n");
        summary.append("Username: ").append(username).append("\n");
        summary.append("Authenticated: ").append(authenticated).append("\n");
        summary.append("Session Valid: ").append(isSessionValid()).append("\n");
        summary.append("Login Time: ").append(loginTime).append("\n");
        summary.append("Last Activity: ").append(lastActivityTime).append("\n");
        summary.append("Session Duration: ").append(getSessionDurationMinutes()).append(" minutes\n");
        summary.append("Time Remaining: ").append(getTimeRemainingMinutes()).append(" minutes\n");
        summary.append("Has Admin Privileges: ").append(hasAdminPrivileges()).append("\n");
        
        return summary.toString();
    }
    
    /**
     * Resets the session timeout by updating the last activity time.
     * This is useful for keeping the session alive during long operations.
     */
    public void resetSessionTimeout() {
        if (isAuthenticated()) {
            updateLastActivityTime();
            System.out.println("Session timeout reset for user: " + username);
        }
    }
    
    /**
     * Validates session and throws an exception if session is invalid.
     * 
     * @throws SessionExpiredException if session is not valid
     */
    public void validateSession() throws SessionExpiredException {
        if (!isAuthenticated()) {
            throw new SessionExpiredException("Session is not authenticated or has expired");
        }
        
        if (isTimedOut()) {
            throw new SessionExpiredException("Session has timed out due to inactivity");
        }
    }
    
    /**
     * Custom exception for session-related errors.
     */
    public static class SessionExpiredException extends Exception {
        public SessionExpiredException(String message) {
            super(message);
        }
    }
    
    /**
     * Returns a string representation of the AdminSession.
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format("AdminSession[sessionId='%s', username='%s', authenticated=%s, valid=%s]",
            sessionId, username, authenticated, isSessionValid());
    }
}