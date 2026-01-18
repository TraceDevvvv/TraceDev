package com.etur.insertbanner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a logged-in agency operator who can perform banner insertion operations.
 * Manages operator authentication, session state, and permissions to interact with the banner system.
 */
public class AgencyOperator {
    private String operatorId;
    private String username;
    private String agencyName;
    private String agencyId;
    private LocalDateTime loginTime;
    private boolean isLoggedIn;
    private List<String> permissions; // Permissions the operator has
    private List<String> managedRefreshmentPoints; // IDs of refreshment points managed by this agency
    
    // Session timeout in minutes
    public static final int SESSION_TIMEOUT_MINUTES = 30;
    
    /**
     * Creates a new agency operator with the given credentials.
     * 
     * @param username Operator's username
     * @param agencyName Name of the agency
     * @param agencyId ID of the agency
     */
    public AgencyOperator(String username, String agencyName, String agencyId) {
        this.operatorId = UUID.randomUUID().toString();
        this.username = username;
        this.agencyName = agencyName;
        this.agencyId = agencyId;
        this.isLoggedIn = false;
        this.permissions = new ArrayList<>();
        this.managedRefreshmentPoints = new ArrayList<>();
        
        // Add default permissions for banner insertion
        addDefaultPermissions();
    }
    
    /**
     * Adds default permissions for an agency operator.
     */
    private void addDefaultPermissions() {
        permissions.add("VIEW_REFRESHMENT_POINTS");
        permissions.add("SELECT_REFRESHMENT_POINT");
        permissions.add("INSERT_BANNER");
        permissions.add("VIEW_BANNERS");
        permissions.add("VALIDATE_IMAGES");
    }
    
    /**
     * Attempts to log in the operator with given credentials.
     * 
     * @param username Operator username
     * @param password Operator password (in real app, would be encrypted)
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        // In a real application, this would validate against a database
        // For simulation, we accept any non-empty username and password
        if (username != null && !username.trim().isEmpty() && 
            password != null && !password.trim().isEmpty()) {
            
            // Set login state
            this.isLoggedIn = true;
            this.loginTime = LocalDateTime.now();
            
            // Update username if different (in case of shared operator object)
            if (!this.username.equals(username)) {
                this.username = username;
            }
            
            System.out.println("Operator " + username + " from agency " + agencyName + " logged in successfully.");
            return true;
        }
        
        System.out.println("Login failed: Invalid credentials.");
        return false;
    }
    
    /**
     * Logs out the operator.
     */
    public void logout() {
        if (isLoggedIn) {
            System.out.println("Operator " + username + " logged out successfully.");
            this.isLoggedIn = false;
            this.loginTime = null;
        }
    }
    
    /**
     * Checks if the operator session is still valid (not timed out).
     * 
     * @return true if session is valid, false if timed out
     */
    public boolean isSessionValid() {
        if (!isLoggedIn || loginTime == null) {
            return false;
        }
        
        LocalDateTime now = LocalDateTime.now();
        long minutesSinceLogin = java.time.Duration.between(loginTime, now).toMinutes();
        
        if (minutesSinceLogin > SESSION_TIMEOUT_MINUTES) {
            System.out.println("Session timed out after " + minutesSinceLogin + " minutes.");
            logout();
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks if the operator has a specific permission.
     * 
     * @param permission Permission to check
     * @return true if operator has the permission, false otherwise
     */
    public boolean hasPermission(String permission) {
        if (!isLoggedIn || !isSessionValid()) {
            return false;
        }
        
        return permissions.contains(permission);
    }
    
    /**
     * Adds a permission to the operator's permission list.
     * 
     * @param permission Permission to add
     */
    public void addPermission(String permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
            System.out.println("Permission '" + permission + "' added for operator " + username);
        }
    }
    
    /**
     * Removes a permission from the operator's permission list.
     * 
     * @param permission Permission to remove
     */
    public void removePermission(String permission) {
        if (permissions.contains(permission)) {
            permissions.remove(permission);
            System.out.println("Permission '" + permission + "' removed from operator " + username);
        }
    }
    
    /**
     * Adds a refreshment point to the list of points managed by this agency.
     * 
     * @param refreshmentPointId ID of the refreshment point
     */
    public void addManagedRefreshmentPoint(String refreshmentPointId) {
        if (!managedRefreshmentPoints.contains(refreshmentPointId)) {
            managedRefreshmentPoints.add(refreshmentPointId);
            System.out.println("Refreshment point " + refreshmentPointId + " added to managed list.");
        }
    }
    
    /**
     * Removes a refreshment point from the list of points managed by this agency.
     * 
     * @param refreshmentPointId ID of the refreshment point to remove
     */
    public void removeManagedRefreshmentPoint(String refreshmentPointId) {
        managedRefreshmentPoints.remove(refreshmentPointId);
        System.out.println("Refreshment point " + refreshmentPointId + " removed from managed list.");
    }
    
    /**
     * Checks if the operator can manage a specific refreshment point.
     * 
     * @param refreshmentPointId ID of the refreshment point
     * @return true if operator can manage the point, false otherwise
     */
    public boolean canManageRefreshmentPoint(String refreshmentPointId) {
        // In a real application, this would involve more complex authorization logic
        // For this simulation, we check if the point is in the managed list
        // and if the operator has the necessary permission
        return isLoggedIn && 
               isSessionValid() && 
               hasPermission("SELECT_REFRESHMENT_POINT") && 
               managedRefreshmentPoints.contains(refreshmentPointId);
    }
    
    /**
     * Validates if the operator is authorized to insert a banner at a refreshment point.
     * 
     * @param refreshmentPointId ID of the refreshment point
     * @return true if authorized, false otherwise
     */
    public boolean isAuthorizedForBannerInsertion(String refreshmentPointId) {
        return isLoggedIn && 
               isSessionValid() && 
               hasPermission("INSERT_BANNER") && 
               canManageRefreshmentPoint(refreshmentPointId);
    }
    
    /**
     * Simulates checking server connection for ETOUR server.
     * 
     * @return true if connection is available, false otherwise
     */
    public boolean checkServerConnection() {
        // In a real application, this would actually ping the server
        // For simulation, we'll randomly fail 10% of the time to demonstrate error handling
        double random = Math.random();
        boolean connectionAvailable = random > 0.1; // 90% success rate
        
        if (!connectionAvailable) {
            System.out.println("ERROR: Connection to ETOUR server interrupted.");
        }
        
        return connectionAvailable;
    }
    
    /**
     * Gets the operator's session information.
     * 
     * @return String containing session details
     */
    public String getSessionInfo() {
        if (!isLoggedIn) {
            return "Operator not logged in.";
        }
        
        StringBuilder info = new StringBuilder();
        info.append("Operator Session Information:\n");
        info.append("=============================\n");
        info.append("Operator ID: ").append(operatorId).append("\n");
        info.append("Username: ").append(username).append("\n");
        info.append("Agency: ").append(agencyName).append(" (ID: ").append(agencyId).append(")\n");
        info.append("Login Time: ").append(loginTime).append("\n");
        info.append("Session Status: ").append(isSessionValid() ? "Active" : "Expired").append("\n");
        
        LocalDateTime now = LocalDateTime.now();
        if (loginTime != null) {
            long minutesLoggedIn = java.time.Duration.between(loginTime, now).toMinutes();
            info.append("Minutes Logged In: ").append(minutesLoggedIn).append("\n");
            info.append("Session Timeout In: ").append(SESSION_TIMEOUT_MINUTES - minutesLoggedIn).append(" minutes\n");
        }
        
        info.append("Managed Refreshment Points: ").append(managedRefreshmentPoints.size()).append("\n");
        info.append("Permissions: ").append(permissions.size()).append("\n");
        
        return info.toString();
    }
    
    // Getters and setters
    
    public String getOperatorId() {
        return operatorId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getAgencyName() {
        return agencyName;
    }
    
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    
    public String getAgencyId() {
        return agencyId;
    }
    
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    
    public LocalDateTime getLoginTime() {
        return loginTime;
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public List<String> getPermissions() {
        return new ArrayList<>(permissions); // Return copy for encapsulation
    }
    
    public List<String> getManagedRefreshmentPoints() {
        return new ArrayList<>(managedRefreshmentPoints); // Return copy for encapsulation
    }
    
    /**
     * Returns a string representation of the agency operator.
     * 
     * @return String containing operator details
     */
    @Override
    public String toString() {
        return "AgencyOperator{" +
                "operatorId='" + operatorId + '\'' +
                ", username='" + username + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", agencyId='" + agencyId + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", sessionValid=" + isSessionValid() +
                ", permissions=" + permissions.size() +
                ", managedPoints=" + managedRefreshmentPoints.size() +
                '}';
    }
}