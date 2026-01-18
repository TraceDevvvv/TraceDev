import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Operator class representing an authenticated restaurant point operator.
 * This class manages operator authentication, permissions, and session state.
 * It follows the use case requirement that the operator must be successfully
 * authenticated to the system before performing banner insertion operations.
 */
public class Operator {
    
    private String operatorId;
    private String name;
    private String email;
    private boolean authenticated;
    private Date authenticationTime;
    private Date lastActivityTime;
    private Set<String> permissions;
    private String restaurantPointId; // The restaurant point this operator manages
    private int sessionId;
    private static int nextSessionId = 1000;
    
    // Constants for permission types
    public static final String PERMISSION_INSERT_BANNER = "INSERT_BANNER";
    public static final String PERMISSION_VIEW_BANNERS = "VIEW_BANNERS";
    public static final String PERMISSION_DELETE_BANNER = "DELETE_BANNER";
    public static final String PERMISSION_MANAGE_RESTAURANT = "MANAGE_RESTAURANT";
    
    /**
     * Constructor for creating a new operator.
     * New operators are not authenticated by default.
     * 
     * @param operatorId Unique identifier for the operator
     * @param name Full name of the operator
     * @param email Email address of the operator
     * @param restaurantPointId ID of the restaurant point this operator manages
     * @throws IllegalArgumentException if operatorId, name, or email is null or empty
     */
    public Operator(String operatorId, String name, String email, String restaurantPointId) {
        if (operatorId == null || operatorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Operator ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Operator name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Operator email cannot be null or empty");
        }
        
        this.operatorId = operatorId.trim();
        this.name = name.trim();
        this.email = email.trim();
        this.restaurantPointId = restaurantPointId != null ? restaurantPointId.trim() : null;
        this.authenticated = false;
        this.authenticationTime = null;
        this.lastActivityTime = new Date(); // Set to current time on creation
        this.permissions = new HashSet<>();
        this.sessionId = -1; // No active session until authenticated
        
        // Add default permissions
        addDefaultPermissions();
    }
    
    /**
     * Simplified constructor for demo purposes.
     * Creates an operator with minimal information.
     * 
     * @param operatorId Unique identifier for the operator
     */
    public Operator(String operatorId) {
        this(operatorId, "Operator " + operatorId, operatorId + "@restaurant.com", null);
    }
    
    /**
     * Adds default permissions for a restaurant point operator.
     * In a real application, permissions would be loaded from a database or configuration.
     */
    private void addDefaultPermissions() {
        permissions.add(PERMISSION_INSERT_BANNER);
        permissions.add(PERMISSION_VIEW_BANNERS);
        permissions.add(PERMISSION_MANAGE_RESTAURANT);
        // Note: DELETE_BANNER is not a default permission
    }
    
    /**
     * Authenticates the operator with provided credentials.
     * In a real application, this would verify against a database or authentication service.
     * 
     * @param password The password to authenticate with
     * @return true if authentication successful, false otherwise
     */
    public boolean authenticate(String password) {
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Authentication failed: Password cannot be empty");
            return false;
        }
        
        // Simulate authentication (in real app, this would hash and compare passwords)
        // For demo purposes, accept any non-empty password
        boolean authSuccessful = !password.trim().isEmpty();
        
        if (authSuccessful) {
            this.authenticated = true;
            this.authenticationTime = new Date();
            this.lastActivityTime = new Date();
            this.sessionId = nextSessionId++;
            
            System.out.println("Operator " + operatorId + " authenticated successfully.");
            System.out.println("Session ID: " + sessionId);
            System.out.println("Authentication time: " + authenticationTime);
        } else {
            System.out.println("Authentication failed for operator " + operatorId);
        }
        
        return authSuccessful;
    }
    
    /**
     * Logs out the operator, ending the current session.
     */
    public void logout() {
        if (authenticated) {
            System.out.println("Operator " + operatorId + " logged out.");
            System.out.println("Session " + sessionId + " ended.");
            
            this.authenticated = false;
            this.sessionId = -1;
            updateLastActivityTime();
        } else {
            System.out.println("Operator " + operatorId + " is not currently authenticated.");
        }
    }
    
    /**
     * Checks if the operator has a specific permission.
     * 
     * @param permission The permission to check
     * @return true if operator has the permission, false otherwise
     */
    public boolean hasPermission(String permission) {
        if (permission == null) {
            return false;
        }
        return permissions.contains(permission);
    }
    
    /**
     * Adds a permission to the operator's permission set.
     * 
     * @param permission The permission to add
     * @return true if permission was added, false if already present
     */
    public boolean addPermission(String permission) {
        if (permission == null || permission.trim().isEmpty()) {
            return false;
        }
        return permissions.add(permission.trim());
    }
    
    /**
     * Removes a permission from the operator's permission set.
     * 
     * @param permission The permission to remove
     * @return true if permission was removed, false if not present
     */
    public boolean removePermission(String permission) {
        if (permission == null) {
            return false;
        }
        return permissions.remove(permission);
    }
    
    /**
     * Updates the last activity time to the current time.
     * This should be called whenever the operator performs an action.
     */
    public void updateLastActivityTime() {
        this.lastActivityTime = new Date();
    }
    
    /**
     * Checks if the operator's session has expired.
     * Sessions expire after 30 minutes of inactivity.
     * 
     * @return true if session has expired, false otherwise
     */
    public boolean isSessionExpired() {
        if (!authenticated || authenticationTime == null || lastActivityTime == null) {
            return true;
        }
        
        long currentTime = System.currentTimeMillis();
        long lastActivity = lastActivityTime.getTime();
        long sessionTimeout = 30 * 60 * 1000; // 30 minutes in milliseconds
        
        return (currentTime - lastActivity) > sessionTimeout;
    }
    
    /**
     * Checks if the operator is currently authenticated and the session is valid.
     * Automatically checks for session expiration.
     * 
     * @return true if operator is properly authenticated with a valid session, false otherwise
     */
    public boolean isAuthenticated() {
        if (authenticated && isSessionExpired()) {
            System.out.println("Session expired for operator " + operatorId);
            authenticated = false;
            sessionId = -1;
        }
        return authenticated;
    }
    
    /**
     * Validates that the operator has the required permission for an operation.
     * Updates last activity time if validation passes.
     * 
     * @param permission The required permission
     * @return true if operator is authenticated and has the permission, false otherwise
     */
    public boolean validatePermission(String permission) {
        updateLastActivityTime();
        
        if (!isAuthenticated()) {
            System.out.println("Permission validation failed: Operator " + operatorId + " is not authenticated.");
            return false;
        }
        
        if (!hasPermission(permission)) {
            System.out.println("Permission validation failed: Operator " + operatorId + 
                             " lacks permission: " + permission);
            return false;
        }
        
        return true;
    }
    
    /**
     * Simulates connection to the ETOUR server.
     * Based on the use case exit condition "Interruption of the connection to the server ETOUR".
     * 
     * @return true if connection is successful, false if interrupted
     */
    public boolean connectToEtourServer() {
        updateLastActivityTime();
        
        // Simulate occasional connection failures (10% chance)
        boolean connected = Math.random() > 0.1;
        
        if (!connected) {
            System.out.println("Connection to ETOUR server interrupted for operator " + operatorId);
            System.out.println("Exit condition: Interruption of the connection to the server ETOUR.");
        }
        
        return connected;
    }
    
    /**
     * Gets the operator's unique identifier.
     * 
     * @return Operator ID
     */
    public String getOperatorId() {
        return operatorId;
    }
    
    /**
     * Gets the operator's full name.
     * 
     * @return Operator name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the operator's email address.
     * 
     * @return Operator email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Gets the time when the operator was authenticated.
     * 
     * @return Authentication time, or null if not authenticated
     */
    public Date getAuthenticationTime() {
        return authenticationTime != null ? (Date) authenticationTime.clone() : null;
    }
    
    /**
     * Gets the time of the operator's last activity.
     * 
     * @return Last activity time
     */
    public Date getLastActivityTime() {
        return (Date) lastActivityTime.clone();
    }
    
    /**
     * Gets the operator's permissions.
     * 
     * @return Set of permissions (unmodifiable)
     */
    public Set<String> getPermissions() {
        return new HashSet<>(permissions);
    }
    
    /**
     * Gets the ID of the restaurant point this operator manages.
     * 
     * @return Restaurant point ID, or null if not assigned
     */
    public String getRestaurantPointId() {
        return restaurantPointId;
    }
    
    /**
     * Sets the restaurant point this operator manages.
     * 
     * @param restaurantPointId The restaurant point ID
     */
    public void setRestaurantPointId(String restaurantPointId) {
        this.restaurantPointId = restaurantPointId != null ? restaurantPointId.trim() : null;
        updateLastActivityTime();
    }
    
    /**
     * Gets the current session ID.
     * 
     * @return Session ID, or -1 if not authenticated
     */
    public int getSessionId() {
        return sessionId;
    }
    
    /**
     * Gets the session timeout in milliseconds.
     * 
     * @return Session timeout (30 minutes)
     */
    public long getSessionTimeout() {
        return 30 * 60 * 1000; // 30 minutes in milliseconds
    }
    
    /**
     * Calculates the remaining session time in milliseconds.
     * 
     * @return Remaining session time, or 0 if not authenticated or session expired
     */
    public long getRemainingSessionTime() {
        if (!authenticated || lastActivityTime == null) {
            return 0;
        }
        
        long currentTime = System.currentTimeMillis();
        long lastActivity = lastActivityTime.getTime();
        long elapsed = currentTime - lastActivity;
        long timeout = getSessionTimeout();
        
        return Math.max(0, timeout - elapsed);
    }
    
    /**
     * Formats remaining session time in a human-readable format.
     * 
     * @return Formatted session time (e.g., "15 minutes, 30 seconds")
     */
    public String getFormattedRemainingSessionTime() {
        long remainingMillis = getRemainingSessionTime();
        
        if (remainingMillis <= 0) {
            return "Session expired or not authenticated";
        }
        
        long minutes = remainingMillis / (60 * 1000);
        long seconds = (remainingMillis % (60 * 1000)) / 1000;
        
        if (minutes > 0) {
            return String.format("%d minutes, %d seconds", minutes, seconds);
        } else {
            return String.format("%d seconds", seconds);
        }
    }
    
    /**
     * Returns a string representation of the operator.
     * 
     * @return Operator details
     */
    @Override
    public String toString() {
        return "Operator{" +
                "operatorId='" + operatorId + '\'' +
                ", name='" + name + '\'' +
                ", authenticated=" + authenticated +
                ", sessionId=" + sessionId +
                ", restaurantPointId='" + restaurantPointId + '\'' +
                ", permissions=" + permissions.size() +
                '}';
    }
    
    /**
     * Displays operator information and status.
     */
    public void displayStatus() {
        System.out.println("\n=== Operator Status ===");
        System.out.println("ID: " + operatorId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Authenticated: " + (authenticated ? "Yes" : "No"));
        System.out.println("Session ID: " + (sessionId != -1 ? sessionId : "None"));
        
        if (authenticated) {
            System.out.println("Authentication Time: " + authenticationTime);
            System.out.println("Last Activity: " + lastActivityTime);
            System.out.println("Session Timeout: " + getFormattedRemainingSessionTime());
        }
        
        System.out.println("Restaurant Point: " + (restaurantPointId != null ? restaurantPointId : "Not assigned"));
        System.out.println("Permissions: " + permissions.size() + " total");
        System.out.println("======================\n");
    }
    
    /**
     * Main method for testing the Operator class (not used in production).
     * This would be removed or moved to a separate test class in a real application.
     */
    public static void main(String[] args) {
        // Test the Operator class
        Operator operator = new Operator("OP001", "John Doe", "john@restaurant.com", "REST001");
        operator.displayStatus();
        
        // Test authentication
        System.out.println("Authenticating with password 'test123'...");
        boolean authResult = operator.authenticate("test123");
        System.out.println("Authentication result: " + (authResult ? "Success" : "Failure"));
        
        operator.displayStatus();
        
        // Test permission validation
        System.out.println("Validating INSERT_BANNER permission...");
        boolean hasPermission = operator.validatePermission(PERMISSION_INSERT_BANNER);
        System.out.println("Has INSERT_BANNER permission: " + hasPermission);
        
        // Test logout
        operator.logout();
        operator.displayStatus();
    }
}