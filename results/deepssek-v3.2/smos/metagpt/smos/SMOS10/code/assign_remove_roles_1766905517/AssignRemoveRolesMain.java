import java.util.HashSet;
import java.util.Set;

/**
 * Main class demonstrating the complete 'Assign/RemoveRolesToAUser' use case.
 * This program creates sample users, roles, and administrators, then demonstrates
 * the complete workflow as described in the use case requirements.
 */
public class AssignRemoveRolesMain {
    
    /**
     * Main method - entry point of the program.
     * Demonstrates the complete use case with multiple scenarios.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Assign/RemoveRolesToAUser Use Case Demonstration ===\n");
        
        // Scenario 1: Complete use case with console interaction
        System.out.println("SCENARIO 1: Complete Use Case with Console Interaction");
        System.out.println("========================================================");
        demonstrateCompleteUseCase();
        
        // Scenario 2: Automated use case execution
        System.out.println("\n\nSCENARIO 2: Automated Use Case Execution");
        System.out.println("==========================================");
        demonstrateAutomatedUseCase();
        
        // Scenario 3: Edge case handling
        System.out.println("\n\nSCENARIO 3: Edge Case Handling");
        System.out.println("===============================");
        demonstrateEdgeCases();
        
        System.out.println("\n=== Use Case Demonstration Complete ===");
    }
    
    /**
     * Demonstrates the complete use case with console interaction simulation.
     * Follows the exact sequence described in the use case documentation.
     */
    private static void demonstrateCompleteUseCase() {
        System.out.println("\nStep 1: Setting up the system environment...");
        
        // Create sample roles
        Set<Role> availableRoles = createSampleRoles();
        System.out.println("Created " + availableRoles.size() + " sample roles.");
        
        // Create a target user
        User targetUser = new User("U001", "john_doe");
        System.out.println("Created target user: " + targetUser.getUsername());
        
        // Assign some initial roles to the user
        Role userRole = getRoleById(availableRoles, "ROLE_USER");
        Role viewerRole = getRoleById(availableRoles, "ROLE_VIEWER");
        if (userRole != null) targetUser.assignRole(userRole);
        if (viewerRole != null) targetUser.assignRole(viewerRole);
        System.out.println("Assigned initial roles to user.");
        
        // Create an administrator
        Administrator admin = new Administrator("ADMIN001", "Alice Smith");
        System.out.println("Created administrator: " + admin.getAdminName());
        
        System.out.println("\nStep 2: Simulating use case preconditions...");
        
        // Precondition: Administrator logs in
        admin.login();
        
        // Precondition: Connect to SMOS server
        admin.connectToSmosServer();
        
        // Precondition: Administrator views user details (simulating "viewdetTailsente" use case)
        admin.viewUserDetails(targetUser);
        
        // Simulate: User clicks on "User Roles" button
        System.out.println("[SIMULATION] User clicked on 'User Roles' button.");
        
        System.out.println("\nStep 3: Displaying role management form...");
        
        // Create role management form
        RoleManagementForm form = new RoleManagementForm(admin, targetUser, availableRoles);
        
        // Display and process the form (console interaction simulation)
        boolean formProcessed = form.displayAndProcessForm();
        
        // Postcondition: Administrator interrupts SMOS server connection
        admin.disconnectFromSmosServer();
        
        if (formProcessed) {
            System.out.println("Use case completed successfully!");
        } else {
            System.out.println("Use case completed with issues.");
        }
        
        // Close form resources
        form.closeForm();
        
        // Logout administrator
        admin.logout();
    }
    
    /**
     * Demonstrates automated execution of the use case without user interaction.
     * Uses pre-defined role selections to show the automated workflow.
     */
    private static void demonstrateAutomatedUseCase() {
        System.out.println("\nStep 1: Setting up automated test environment...");
        
        // Create sample roles
        Set<Role> availableRoles = createSampleRoles();
        
        // Create a target user
        User targetUser = new User("U002", "jane_smith");
        
        // Assign initial roles
        Role userRole = getRoleById(availableRoles, "ROLE_USER");
        Role editorRole = getRoleById(availableRoles, "ROLE_EDITOR");
        if (userRole != null) targetUser.assignRole(userRole);
        if (editorRole != null) targetUser.assignRole(editorRole);
        
        System.out.println("Created user '" + targetUser.getUsername() + "' with " + 
                          targetUser.getRoleCount() + " initial roles.");
        
        // Create administrator
        Administrator admin = new Administrator("ADMIN002", "Bob Johnson");
        
        // Login and connect to server
        admin.login();
        admin.connectToSmosServer();
        
        // View user details (precondition)
        admin.viewUserDetails(targetUser);
        
        System.out.println("\nStep 2: Automated role selection...");
        
        // Predefine roles to assign and remove
        Set<Role> rolesToAssign = new HashSet<>();
        Set<Role> rolesToRemove = new HashSet<>();
        
        // Select roles to assign
        Role adminRole = getRoleById(availableRoles, "ROLE_ADMIN");
        Role auditorRole = getRoleById(availableRoles, "ROLE_AUDITOR");
        if (adminRole != null) rolesToAssign.add(adminRole);
        if (auditorRole != null) rolesToAssign.add(auditorRole);
        
        // Select roles to remove
        if (editorRole != null) rolesToRemove.add(editorRole);
        
        System.out.println("Automated selection:");
        System.out.println("  Roles to assign: " + rolesToAssign.size());
        System.out.println("  Roles to remove: " + rolesToRemove.size());
        
        System.out.println("\nStep 3: Executing use case with executeAssignRemoveRolesUseCase()...");
        
        // Execute the complete use case using the administrator's method
        boolean success = admin.executeAssignRemoveRolesUseCase(
            targetUser, availableRoles, rolesToAssign, rolesToRemove);
        
        System.out.println("\nStep 4: Verifying results...");
        System.out.println("User '" + targetUser.getUsername() + "' now has " + 
                          targetUser.getRoleCount() + " roles.");
        
        // Display final user state
        admin.viewUserDetails(targetUser);
        
        // Cleanup
        admin.disconnectFromSmosServer();
        admin.logout();
        
        if (success) {
            System.out.println("Automated use case execution: SUCCESS");
        } else {
            System.out.println("Automated use case execution: FAILED");
        }
    }
    
    /**
     * Demonstrates handling of edge cases and error conditions.
     * Shows how the system handles invalid inputs and boundary conditions.
     */
    private static void demonstrateEdgeCases() {
        System.out.println("\nTesting edge cases and error handling...");
        
        // Edge Case 1: Null user
        System.out.println("\nEdge Case 1: Handling null user");
        System.out.println("---------------------------------");
        Administrator admin = new Administrator("ADMIN003", "Charlie Brown");
        admin.login();
        admin.connectToSmosServer();
        
        // Try to execute use case with null user
        Set<Role> roles = createSampleRoles();
        boolean result = admin.executeAssignRemoveRolesUseCase(null, roles, new HashSet<>(), new HashSet<>());
        System.out.println("Result with null user: " + (result ? "SUCCESS" : "FAILED (expected)"));
        
        // Edge Case 2: Empty role sets
        System.out.println("\nEdge Case 2: Empty role selections");
        System.out.println("-----------------------------------");
        User testUser = new User("U003", "test_user");
        admin.viewUserDetails(testUser);
        
        // Execute with empty selections
        result = admin.executeAssignRemoveRolesUseCase(testUser, roles, new HashSet<>(), new HashSet<>());
        System.out.println("Result with empty selections: " + (result ? "SUCCESS" : "FAILED"));
        
        // Edge Case 3: Administrator not logged in
        System.out.println("\nEdge Case 3: Administrator not logged in");
        System.out.println("-----------------------------------------");
        admin.logout(); // Logout first
        
        Administrator notLoggedInAdmin = new Administrator("ADMIN004", "NotLogged");
        // Don't call login() for this administrator
        
        result = notLoggedInAdmin.executeAssignRemoveRolesUseCase(testUser, roles, new HashSet<>(), new HashSet<>());
        System.out.println("Result when admin not logged in: " + (result ? "SUCCESS" : "FAILED (expected)"));
        
        // Edge Case 4: Duplicate role assignment
        System.out.println("\nEdge Case 4: Duplicate role assignment");
        System.out.println("---------------------------------------");
        User dupUser = new User("U004", "dup_user");
        Role userRole = getRoleById(roles, "ROLE_USER");
        
        if (userRole != null) {
            // Assign role once
            boolean firstAssign = dupUser.assignRole(userRole);
            System.out.println("First assignment of ROLE_USER: " + (firstAssign ? "SUCCESS" : "FAILED"));
            
            // Try to assign the same role again
            boolean secondAssign = dupUser.assignRole(userRole);
            System.out.println("Second assignment of ROLE_USER: " + (secondAssign ? "SUCCESS (unexpected)" : "FAILED (expected - duplicate)"));
            
            System.out.println("User has " + dupUser.getRoleCount() + " roles (should be 1).");
        }
        
        // Edge Case 5: Remove non-existent role
        System.out.println("\nEdge Case 5: Removing non-existent role");
        System.out.println("----------------------------------------");
        User emptyUser = new User("U005", "empty_user");
        Role nonExistentRole = new Role("NON_EXISTENT", "Non Existent Role");
        
        boolean removeResult = emptyUser.removeRole(nonExistentRole);
        System.out.println("Removing non-existent role: " + (removeResult ? "SUCCESS (unexpected)" : "FAILED (expected)"));
        
        // Edge Case 6: Null role handling
        System.out.println("\nEdge Case 6: Null role handling");
        System.out.println("-------------------------------");
        User nullTestUser = new User("U006", "null_test");
        
        // Try to assign null role
        boolean nullAssign = nullTestUser.assignRole(null);
        System.out.println("Assigning null role: " + (nullAssign ? "SUCCESS (unexpected)" : "FAILED (expected)"));
        
        // Try to remove null role
        boolean nullRemove = nullTestUser.removeRole(null);
        System.out.println("Removing null role: " + (nullRemove ? "SUCCESS (unexpected)" : "FAILED (expected)"));
        
        // Cleanup
        admin.disconnectFromSmosServer();
        
        System.out.println("\nAll edge cases tested successfully!");
    }
    
    /**
     * Creates a set of sample roles for demonstration purposes.
     * 
     * @return Set of sample Role objects
     */
    private static Set<Role> createSampleRoles() {
        Set<Role> roles = new HashSet<>();
        
        // Create standard system roles
        roles.add(new Role("ROLE_ADMIN", "Administrator"));
        roles.add(new Role("ROLE_USER", "Standard User"));
        roles.add(new Role("ROLE_EDITOR", "Content Editor"));
        roles.add(new Role("ROLE_VIEWER", "Content Viewer"));
        roles.add(new Role("ROLE_AUDITOR", "Auditor"));
        roles.add(new Role("ROLE_REPORTER", "Report Generator"));
        roles.add(new Role("ROLE_MODERATOR", "Content Moderator"));
        roles.add(new Role("ROLE_ANALYST", "Data Analyst"));
        
        return roles;
    }
    
    /**
     * Helper method to find a role by ID in a set of roles.
     * 
     * @param roles Set of roles to search
     * @param roleId ID of the role to find
     * @return Role object if found, null otherwise
     */
    private static Role getRoleById(Set<Role> roles, String roleId) {
        if (roles == null || roleId == null) {
            return null;
        }
        
        for (Role role : roles) {
            if (role.getRoleId().equals(roleId)) {
                return role;
            }
        }
        
        return null;
    }
    
    /**
     * Displays a summary of the use case requirements.
     * This method provides documentation of the use case being implemented.
     */
    private static void displayUseCaseSummary() {
        System.out.println("\n=== Use Case Summary: Assign/RemoveRolesToAUser ===");
        System.out.println("Actors: Administrator");
        System.out.println("Description: Allows you to assign or remove roles to a user");
        System.out.println("\nPreconditions:");
        System.out.println("1. The user is logged in as an administrator");
        System.out.println("2. The user has carried out the case of use 'viewdetTailsente'");
        System.out.println("3. The system is viewing the details of a user");
        System.out.println("4. The user click on the 'User Roles' button");
        
        System.out.println("\nEvents Sequence:");
        System.out.println("User Actions:");
        System.out.println("2. Select the roles to be assigned or removed to the user");
        System.out.println("3. Click on the 'Send' button");
        
        System.out.println("\nSystem Actions:");
        System.out.println("1. Displays the role management form");
        System.out.println("4. Assign or remove the user's roles as indicated by the administrator");
        
        System.out.println("\nPostconditions:");
        System.out.println("1. User roles are modified");
        System.out.println("2. The administrator interrupts the connection to the SMOS server");
        System.out.println("====================================================\n");
    }
    
    /**
     * Demonstrates the role management functionality with a simple example.
     * This is a utility method that can be used for quick testing.
     */
    public static void demonstrateRoleManagement() {
        System.out.println("\n=== Quick Role Management Demonstration ===");
        
        // Create a user
        User user = new User("TEST001", "Test User");
        
        // Create some roles
        Role role1 = new Role("R001", "Manager");
        Role role2 = new Role("R002", "Developer");
        Role role3 = new Role("R003", "Tester");
        
        // Assign roles
        user.assignRole(role1);
        user.assignRole(role2);
        
        System.out.println("After assigning 2 roles:");
        System.out.println("User: " + user.getUsername());
        System.out.println("Roles count: " + user.getRoleCount());
        
        // Check if user has a specific role
        System.out.println("Has 'Manager' role: " + user.hasRole(role1));
        System.out.println("Has 'Tester' role: " + user.hasRole(role3));
        
        // Remove a role
        user.removeRole(role1);
        System.out.println("\nAfter removing 'Manager' role:");
        System.out.println("Roles count: " + user.getRoleCount());
        System.out.println("Has 'Manager' role: " + user.hasRole(role1));
        
        // Assign multiple roles at once
        Set<Role> newRoles = new HashSet<>();
        newRoles.add(role1);
        newRoles.add(role3);
        
        int added = user.assignRoles(newRoles);
        System.out.println("\nAfter assigning " + added + " new roles:");
        System.out.println("Total roles: " + user.getRoleCount());
        
        System.out.println("\nFinal user state:");
        System.out.println(user);
    }
}