import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

/**
 * RoleManagementForm class simulating a UI form for managing user roles.
 * This class provides a console-based interface for administrators to
 * assign and remove roles from users, following the use case requirements.
 */
public class RoleManagementForm {
    private Administrator administrator;
    private User targetUser;
    private Set<Role> availableRoles;
    private Scanner scanner;
    
    /**
     * Constructor for RoleManagementForm.
     * 
     * @param administrator The administrator managing roles
     * @param targetUser The user whose roles are being managed
     * @param availableRoles All available roles in the system
     */
    public RoleManagementForm(Administrator administrator, User targetUser, Set<Role> availableRoles) {
        this.administrator = administrator;
        this.targetUser = targetUser;
        this.availableRoles = new HashSet<>(availableRoles);
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Display the role management form and handle user interactions.
     * This method simulates the complete form interaction as described in the use case.
     * 
     * @return true if role changes were successfully processed, false otherwise
     */
    public boolean displayAndProcessForm() {
        System.out.println("\n*** Role Management Form - Console Simulation ***");
        System.out.println("Managing roles for user: " + targetUser.getUsername());
        System.out.println("Administrator: " + administrator.getAdminName());
        System.out.println("=================================================\n");
        
        // Check if administrator can perform this action
        if (!administrator.isLoggedIn()) {
            System.err.println("Error: Administrator must be logged in to manage roles.");
            return false;
        }
        
        // Display current user roles
        displayCurrentUserRoles();
        
        // Display all available roles
        displayAvailableRoles();
        
        // Get role selections from administrator
        System.out.println("\n--- Role Selection ---");
        Set<Role> rolesToAssign = getRolesToAssign();
        Set<Role> rolesToRemove = getRolesToRemove();
        
        // Confirm selections
        if (!confirmSelections(rolesToAssign, rolesToRemove)) {
            System.out.println("Operation cancelled by administrator.");
            return false;
        }
        
        // Process the role changes
        return processRoleChanges(rolesToAssign, rolesToRemove);
    }
    
    /**
     * Display the current roles assigned to the target user.
     */
    private void displayCurrentUserRoles() {
        Set<Role> userRoles = targetUser.getRoles();
        System.out.println("Current roles assigned to " + targetUser.getUsername() + ":");
        
        if (userRoles.isEmpty()) {
            System.out.println("  No roles currently assigned.");
        } else {
            int index = 1;
            for (Role role : userRoles) {
                System.out.println("  " + index + ". " + role.getRoleName() + " (ID: " + role.getRoleId() + ")");
                index++;
            }
        }
        System.out.println();
    }
    
    /**
     * Display all available roles in the system.
     */
    private void displayAvailableRoles() {
        System.out.println("Available roles in the system:");
        
        if (availableRoles.isEmpty()) {
            System.out.println("  No roles available in the system.");
            return;
        }
        
        int index = 1;
        Set<Role> userRoles = targetUser.getRoles();
        
        for (Role role : availableRoles) {
            boolean isAssigned = userRoles.contains(role);
            String status = isAssigned ? "[ASSIGNED]" : "[AVAILABLE]";
            System.out.println("  " + index + ". " + role.getRoleName() + 
                             " (ID: " + role.getRoleId() + ") " + status);
            index++;
        }
    }
    
    /**
     * Get roles that the administrator wants to assign to the user.
     * 
     * @return Set of roles to assign
     */
    private Set<Role> getRolesToAssign() {
        Set<Role> rolesToAssign = new HashSet<>();
        
        System.out.println("\nEnter roles to ASSIGN (by ID, separated by commas, or 'none' for none):");
        System.out.print("Selection: ");
        String input = scanner.nextLine().trim();
        
        if (input.equalsIgnoreCase("none") || input.isEmpty()) {
            System.out.println("No roles selected for assignment.");
            return rolesToAssign;
        }
        
        String[] roleIds = input.split(",");
        for (String roleId : roleIds) {
            roleId = roleId.trim();
            if (!roleId.isEmpty()) {
                Role role = findRoleById(roleId);
                if (role != null) {
                    // Check if role is already assigned
                    if (targetUser.hasRole(role)) {
                        System.out.println("  Note: Role '" + role.getRoleName() + "' is already assigned to the user.");
                    } else {
                        rolesToAssign.add(role);
                        System.out.println("  Selected to assign: " + role.getRoleName());
                    }
                } else {
                    System.out.println("  Warning: Role with ID '" + roleId + "' not found.");
                }
            }
        }
        
        return rolesToAssign;
    }
    
    /**
     * Get roles that the administrator wants to remove from the user.
     * 
     * @return Set of roles to remove
     */
    private Set<Role> getRolesToRemove() {
        Set<Role> rolesToRemove = new HashSet<>();
        Set<Role> userRoles = targetUser.getRoles();
        
        if (userRoles.isEmpty()) {
            System.out.println("User has no roles to remove.");
            return rolesToRemove;
        }
        
        System.out.println("\nEnter roles to REMOVE (by ID, separated by commas, or 'none' for none):");
        System.out.print("Selection: ");
        String input = scanner.nextLine().trim();
        
        if (input.equalsIgnoreCase("none") || input.isEmpty()) {
            System.out.println("No roles selected for removal.");
            return rolesToRemove;
        }
        
        String[] roleIds = input.split(",");
        for (String roleId : roleIds) {
            roleId = roleId.trim();
            if (!roleId.isEmpty()) {
                Role role = findRoleById(roleId);
                if (role != null) {
                    if (userRoles.contains(role)) {
                        rolesToRemove.add(role);
                        System.out.println("  Selected to remove: " + role.getRoleName());
                    } else {
                        System.out.println("  Note: Role '" + role.getRoleName() + "' is not currently assigned to the user.");
                    }
                } else {
                    System.out.println("  Warning: Role with ID '" + roleId + "' not found.");
                }
            }
        }
        
        return rolesToRemove;
    }
    
    /**
     * Find a role by its ID in the available roles set.
     * 
     * @param roleId The role ID to find
     * @return The Role object if found, null otherwise
     */
    private Role findRoleById(String roleId) {
        for (Role role : availableRoles) {
            if (role.getRoleId().equals(roleId)) {
                return role;
            }
        }
        return null;
    }
    
    /**
     * Confirm the role selections with the administrator.
     * 
     * @param rolesToAssign Roles selected for assignment
     * @param rolesToRemove Roles selected for removal
     * @return true if administrator confirms, false if cancels
     */
    private boolean confirmSelections(Set<Role> rolesToAssign, Set<Role> rolesToRemove) {
        System.out.println("\n--- Confirm Changes ---");
        System.out.println("Summary of changes for user: " + targetUser.getUsername());
        
        if (rolesToAssign.isEmpty() && rolesToRemove.isEmpty()) {
            System.out.println("No changes to make.");
            return false;
        }
        
        if (!rolesToAssign.isEmpty()) {
            System.out.println("Roles to ASSIGN:");
            for (Role role : rolesToAssign) {
                System.out.println("  + " + role.getRoleName() + " (ID: " + role.getRoleId() + ")");
            }
        }
        
        if (!rolesToRemove.isEmpty()) {
            System.out.println("Roles to REMOVE:");
            for (Role role : rolesToRemove) {
                System.out.println("  - " + role.getRoleName() + " (ID: " + role.getRoleId() + ")");
            }
        }
        
        System.out.print("\nConfirm these changes? (yes/no): ");
        String response = scanner.nextLine().trim();
        
        return response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y");
    }
    
    /**
     * Process the role changes based on administrator selections.
     * 
     * @param rolesToAssign Roles to assign
     * @param rolesToRemove Roles to remove
     * @return true if changes were successfully processed, false otherwise
     */
    private boolean processRoleChanges(Set<Role> rolesToAssign, Set<Role> rolesToRemove) {
        System.out.println("\n--- Processing Role Changes ---");
        
        // Remove conflicting roles (roles both assigned and removed)
        Set<Role> conflicts = new HashSet<>(rolesToAssign);
        conflicts.retainAll(rolesToRemove);
        
        if (!conflicts.isEmpty()) {
            System.out.println("Warning: The following roles are both assigned and removed:");
            for (Role role : conflicts) {
                System.out.println("  - " + role.getRoleName() + " (ID: " + role.getRoleId() + ")");
            }
            System.out.println("These roles will be processed as REMOVALS only.");
            
            // Remove conflicts from assignment list
            rolesToAssign.removeAll(conflicts);
        }
        
        // Process removals first
        int removedCount = 0;
        if (!rolesToRemove.isEmpty()) {
            removedCount = targetUser.removeRoles(rolesToRemove);
            System.out.println("Successfully removed " + removedCount + " role(s).");
        }
        
        // Process assignments
        int assignedCount = 0;
        if (!rolesToAssign.isEmpty()) {
            assignedCount = targetUser.assignRoles(rolesToAssign);
            System.out.println("Successfully assigned " + assignedCount + " role(s).");
        }
        
        // Display final result
        System.out.println("\n--- Final Result ---");
        System.out.println("User: " + targetUser.getUsername());
        System.out.println("Total roles now assigned: " + targetUser.getRoleCount());
        
        Set<Role> finalRoles = targetUser.getRoles();
        if (!finalRoles.isEmpty()) {
            System.out.println("Assigned roles:");
            for (Role role : finalRoles) {
                System.out.println("  - " + role.getRoleName() + " (ID: " + role.getRoleId() + ")");
            }
        }
        
        // Simulate the "Send" button action from the use case
        System.out.println("\n[SIMULATION] 'Send' button clicked - changes applied successfully.");
        
        return true;
    }
    
    /**
     * Close the form and release resources.
     */
    public void closeForm() {
        if (scanner != null) {
            scanner.close();
        }
        System.out.println("Role management form closed.");
    }
    
    /**
     * Validate that the role selections are valid (no duplicates in assignment,
     * roles to remove are actually assigned, etc.).
     * 
     * @param rolesToAssign Roles selected for assignment
     * @param rolesToRemove Roles selected for removal
     * @return true if selections are valid, false otherwise
     */
    public boolean validateSelections(Set<Role> rolesToAssign, Set<Role> rolesToRemove) {
        Set<Role> userRoles = targetUser.getRoles();
        
        // Check for roles to remove that aren't actually assigned
        for (Role role : rolesToRemove) {
            if (!userRoles.contains(role)) {
                System.err.println("Validation error: Role '" + role.getRoleName() + 
                                 "' is not assigned to the user and cannot be removed.");
                return false;
            }
        }
        
        // Check for roles that would be both assigned and removed
        Set<Role> intersection = new HashSet<>(rolesToAssign);
        intersection.retainAll(rolesToRemove);
        if (!intersection.isEmpty()) {
            System.err.println("Validation error: The following roles are both assigned and removed:");
            for (Role role : intersection) {
                System.err.println("  - " + role.getRoleName());
            }
            return false;
        }
        
        return true;
    }
    
    /**
     * Simulate the form display without user interaction (for automated testing).
     * 
     * @param rolesToAssign Pre-determined roles to assign
     * @param rolesToRemove Pre-determined roles to remove
     * @return true if changes were successfully processed, false otherwise
     */
    public boolean simulateFormSubmission(Set<Role> rolesToAssign, Set<Role> rolesToRemove) {
        System.out.println("\n*** Automated Form Submission ***");
        System.out.println("Managing roles for user: " + targetUser.getUsername());
        
        if (!validateSelections(rolesToAssign, rolesToRemove)) {
            System.err.println("Form submission failed: Invalid role selections.");
            return false;
        }
        
        // Process the changes
        return administrator.processRoleChanges(targetUser, rolesToAssign, rolesToRemove);
    }
    
    /**
     * Get the target user of this form.
     * 
     * @return The target user
     */
    public User getTargetUser() {
        return targetUser;
    }
    
    /**
     * Get the available roles for this form.
     * 
     * @return Set of available roles
     */
    public Set<Role> getAvailableRoles() {
        return new HashSet<>(availableRoles);
    }
}