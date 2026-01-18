import java.util.HashSet;
import java.util.Set;

/**
 * Administrator class representing an admin user who can assign and remove roles to other users.
 * This class implements the use case "Assign/RemoveRolesToAUser" as described in the requirements.
 */
public class Administrator {
    private String adminId;
    private String adminName;
    private boolean isLoggedIn;
    private boolean smosServerConnected;
    
    /**
     * Constructor for Administrator.
     * 
     * @param adminId Unique identifier for the administrator
     * @param adminName Name of the administrator
     */
    public Administrator(String adminId, String adminName) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.isLoggedIn = false;
        this.smosServerConnected = false;
    }
    
    /**
     * Login as administrator (precondition for the use case).
     * 
     * @return true if login successful, false otherwise
     */
    public boolean login() {
        // Simulate login process
        isLoggedIn = true;
        System.out.println("Administrator " + adminName + " logged in successfully.");
        return true;
    }
    
    /**
     * Logout as administrator (postcondition for the use case).
     */
    public void logout() {
        isLoggedIn = false;
        System.out.println("Administrator " + adminName + " logged out.");
    }
    
    /**
     * Connect to SMOS server (required for the use case).
     * 
     * @return true if connection successful, false otherwise
     */
    public boolean connectToSmosServer() {
        // Simulate SMOS server connection
        smosServerConnected = true;
        System.out.println("Connected to SMOS server.");
        return true;
    }
    
    /**
     * Disconnect from SMOS server (postcondition for the use case).
     */
    public void disconnectFromSmosServer() {
        smosServerConnected = false;
        System.out.println("Disconnected from SMOS server.");
    }
    
    /**
     * Check if preconditions for the use case are met.
     * Preconditions:
     * 1. The user is logged in as an administrator
     * 2. The user has carried out the case of use "viewdetTailsente" and the system is viewing the details of a user
     * 3. The user click on the "User Roles" button
     * 
     * @param targetUser The user whose details are being viewed
     * @return true if all preconditions are met, false otherwise
     */
    public boolean checkPreconditions(User targetUser) {
        if (!isLoggedIn) {
            System.err.println("Precondition failed: Administrator is not logged in.");
            return false;
        }
        
        if (!smosServerConnected) {
            System.err.println("Precondition failed: Not connected to SMOS server.");
            return false;
        }
        
        if (targetUser == null) {
            System.err.println("Precondition failed: No user details are being viewed.");
            return false;
        }
        
        // Simulate that "viewdetTailsente" use case has been carried out
        System.out.println("Preconditions met: Admin is logged in, SMOS server connected, and viewing details of user: " + targetUser.getUsername());
        System.out.println("User clicked on 'User Roles' button.");
        return true;
    }
    
    /**
     * Display the role management form (System action 1 from use case).
     * This simulates displaying available roles for assignment/removal.
     * 
     * @param availableRoles Set of all available roles in the system
     * @param userRoles Set of roles currently assigned to the user
     */
    public void displayRoleManagementForm(Set<Role> availableRoles, Set<Role> userRoles) {
        System.out.println("\n=== Role Management Form ===");
        System.out.println("Available roles in the system:");
        
        if (availableRoles == null || availableRoles.isEmpty()) {
            System.out.println("No roles available in the system.");
        } else {
            int i = 1;
            for (Role role : availableRoles) {
                boolean isAssigned = userRoles.contains(role);
                System.out.println(i + ". " + role.getRoleName() + " (ID: " + role.getRoleId() + ")" + 
                                   (isAssigned ? " [Currently Assigned]" : ""));
                i++;
            }
        }
        
        System.out.println("\nInstructions:");
        System.out.println("1. Select roles to assign or remove (by ID or name)");
        System.out.println("2. Click 'Send' button to apply changes");
        System.out.println("====================================\n");
    }
    
    /**
     * Process role assignments and removals (System action 4 from use case).
     * This method handles the actual assignment and removal of roles based on administrator selection.
     * 
     * @param targetUser The user to modify roles for
     * @param rolesToAssign Set of roles to assign to the user
     * @param rolesToRemove Set of roles to remove from the user
     * @return true if operation successful, false otherwise
     */
    public boolean processRoleChanges(User targetUser, Set<Role> rolesToAssign, Set<Role> rolesToRemove) {
        if (targetUser == null) {
            System.err.println("Cannot process role changes: Target user is null.");
            return false;
        }
        
        // Check if administrator is logged in and has permissions
        if (!isLoggedIn) {
            System.err.println("Cannot process role changes: Administrator not logged in.");
            return false;
        }
        
        if (!smosServerConnected) {
            System.err.println("Cannot process role changes: Not connected to SMOS server.");
            return false;
        }
        
        System.out.println("Processing role changes for user: " + targetUser.getUsername());
        
        // Remove roles first to avoid conflicts
        int removedCount = 0;
        if (rolesToRemove != null && !rolesToRemove.isEmpty()) {
            removedCount = targetUser.removeRoles(rolesToRemove);
            System.out.println("Removed " + removedCount + " role(s) from user.");
        }
        
        // Assign new roles
        int assignedCount = 0;
        if (rolesToAssign != null && !rolesToAssign.isEmpty()) {
            assignedCount = targetUser.assignRoles(rolesToAssign);
            System.out.println("Assigned " + assignedCount + " role(s) to user.");
        }
        
        // Display final state
        System.out.println("User now has " + targetUser.getRoleCount() + " role(s) assigned.");
        
        // Postcondition: User roles are modified
        System.out.println("Postcondition satisfied: User roles have been modified.");
        
        return true;
    }
    
    /**
     * Execute the complete use case "Assign/RemoveRolesToAUser".
     * This method simulates the entire workflow described in the use case.
     * 
     * @param targetUser The user to assign/remove roles for
     * @param availableRoles All available roles in the system
     * @param selectedRolesToAssign Roles selected by admin to assign
     * @param selectedRolesToRemove Roles selected by admin to remove
     * @return true if use case executed successfully, false otherwise
     */
    public boolean executeAssignRemoveRolesUseCase(User targetUser, Set<Role> availableRoles,
                                                   Set<Role> selectedRolesToAssign, Set<Role> selectedRolesToRemove) {
        System.out.println("\n=== Executing Use Case: Assign/RemoveRolesToAUser ===");
        
        // Step 1: Check preconditions
        if (!checkPreconditions(targetUser)) {
            System.err.println("Use case cannot proceed: Preconditions not met.");
            return false;
        }
        
        // System action 1: Display the role management form
        displayRoleManagementForm(availableRoles, targetUser.getRoles());
        
        // User action 2: Select the roles to be assigned or removed (simulated by parameters)
        System.out.println("Administrator selected roles to assign: " + 
                          (selectedRolesToAssign != null ? selectedRolesToAssign.size() : 0) + " role(s)");
        System.out.println("Administrator selected roles to remove: " + 
                          (selectedRolesToRemove != null ? selectedRolesToRemove.size() : 0) + " role(s)");
        
        // User action 3: Click on the "Send" button
        System.out.println("Administrator clicked on 'Send' button.");
        
        // System action 4: Assign or remove the user's roles as indicated by the administrator
        boolean success = processRoleChanges(targetUser, selectedRolesToAssign, selectedRolesToRemove);
        
        if (success) {
            // Postcondition: The administrator interrupts the connection to the SMOS server
            disconnectFromSmosServer();
            System.out.println("Postcondition satisfied: SMOS server connection interrupted.");
            
            System.out.println("=== Use Case Completed Successfully ===\n");
        } else {
            System.err.println("=== Use Case Failed ===\n");
        }
        
        return success;
    }
    
    /**
     * View user details (simulates the "viewdetTailsente" use case mentioned in preconditions).
     * 
     * @param user The user to view details for
     */
    public void viewUserDetails(User user) {
        if (user == null) {
            System.out.println("Cannot view details: User is null.");
            return;
        }
        
        System.out.println("\n=== Viewing User Details ===");
        System.out.println("User ID: " + user.getUserId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Assigned Roles (" + user.getRoleCount() + "):");
        
        Set<Role> roles = user.getRoles();
        if (roles.isEmpty()) {
            System.out.println("  No roles assigned.");
        } else {
            for (Role role : roles) {
                System.out.println("  - " + role.getRoleName() + " (ID: " + role.getRoleId() + ")");
            }
        }
        System.out.println("=============================\n");
    }
    
    /**
     * Get the administrator ID.
     * 
     * @return Administrator ID
     */
    public String getAdminId() {
        return adminId;
    }
    
    /**
     * Get the administrator name.
     * 
     * @return Administrator name
     */
    public String getAdminName() {
        return adminName;
    }
    
    /**
     * Check if administrator is logged in.
     * 
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * Check if connected to SMOS server.
     * 
     * @return true if connected, false otherwise
     */
    public boolean isSmosServerConnected() {
        return smosServerConnected;
    }
    
    /**
     * String representation of the administrator.
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return "Administrator{" +
                "adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", smosServerConnected=" + smosServerConnected +
                '}';
    }
}