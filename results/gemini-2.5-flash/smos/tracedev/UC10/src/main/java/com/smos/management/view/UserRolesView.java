package com.smos.management.view;

import com.smos.management.exceptions.SMOSConnectionException;
import com.smos.management.model.Role;
import com.smos.management.model.User;
import com.smos.management.service.UserRoleManagementService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the User Roles View, responsible for displaying user role information
 * and handling user input for role modifications.
 * This class corresponds to the 'UserRolesView' class in the Class Diagram
 * and interacts with the 'UserRoleManagementService' as per the Sequence Diagram.
 *
 * Accessible only by authenticated Administrator. (Requirement EC1)
 */
public class UserRolesView {
    private UserRoleManagementService userRoleManagementService;
    private List<String> selectedRoleIdsCache; // To simulate selected roles from a UI
    private List<String> unselectedRoleIdsCache; // To simulate unselected roles from a UI

    /**
     * Constructs a UserRolesView with a dependency on UserRoleManagementService.
     *
     * @param service the service responsible for managing user roles.
     */
    public UserRolesView(UserRoleManagementService service) {
        this.userRoleManagementService = service;
        this.selectedRoleIdsCache = new ArrayList<>();
        this.unselectedRoleIdsCache = new ArrayList<>();
    }

    /**
     * Initiates the role management process for a given user.
     * This method is the entry point for the "Assign or Remove Roles" use case,
     * corresponding to `Admin -> View : initiateRoleManagement("user123")` in the Sequence Diagram.
     * Pre-condition: userId obtained from 'viewdetTailsente' use case. (Requirement EC2)
     *
     * @param userId the ID of the user to manage roles for.
     */
    public void initiateRoleManagement(String userId) {
        System.out.println("\n--- Initiating Role Management for User ID: " + userId + " ---");
        try {
            // Sequence Diagram: View -> Service : getUserDetailsAndAvailableRoles("user123") (corresponds to m3 in intent)
            Map<String, Object> data = userRoleManagementService.getUserDetailsAndAvailableRoles(userId);

            User user = (User) data.get("user");
            List<Role> allRoles = (List<Role>) data.get("allRoles");

            // Sequence Diagram: m8: Service --> View : displayData(userObject.getRoles(), allAvailableRoles) (implemented by this method call)
            displayRoleManagementForm(user.getUserId(), user.getRoles(), allRoles);

        } catch (IllegalArgumentException e) {
            showOperationResult(false, "Failed to initiate role management: " + e.getMessage());
        } catch (SMOSConnectionException e) {
            showOperationResult(false, "Failed to initiate role management due to connection error: " + e.getMessage());
        }
    }

    /**
     * Displays a simulated form for managing user roles.
     * In a real UI, this would render a graphical interface. Here, it prints to console.
     *
     * This method corresponds to sequence diagram message m8: Service --> View : displayData(...)
     *
     * @param userId the ID of the user.
     * @param currentRoles the roles currently assigned to the user.
     * @param allRoles a list of all available roles in the system.
     */
    public void displayRoleManagementForm(String userId, List<Role> currentRoles, List<Role> allRoles) {
        System.out.println("\n--- Displaying Role Management Form for User: " + userId + " ---");
        System.out.println("Current Roles for User:");
        if (currentRoles.isEmpty()) {
            System.out.println("  (None)");
        } else {
            currentRoles.forEach(role -> System.out.println("  - " + role.getRoleName() + " (ID: " + role.getRoleId() + ")"));
        }

        System.out.println("\nAll Available Roles:");
        List<Role> rolesNotAssigned = new ArrayList<>();
        if (allRoles.isEmpty()) {
            System.out.println("  (None available)");
        } else {
            for (Role role : allRoles) {
                if (!currentRoles.contains(role)) {
                    rolesNotAssigned.add(role);
                    System.out.println("  - " + role.getRoleName() + " (ID: " + role.getRoleId() + ") [Available to assign]");
                } else {
                    System.out.println("  - " + role.getRoleName() + " (ID: " + role.getRoleId() + ") [Currently assigned]");
                }
            }
        }

        // Simulate a selection. For demonstration, let's say an admin decides:
        // - To remove 'ROLE_EDITOR' if present.
        // - To assign 'ROLE_MODERATOR' if not present.
        System.out.println("\n--- Simulating Admin's selections ---");
        List<String> rolesToAssign = new ArrayList<>();
        List<String> rolesToRemove = new ArrayList<>();

        // Logic to simulate admin cho
        for (Role currentRole : currentRoles) {
            if (currentRole.getRoleId().equals("R2") && currentRole.getRoleName().equals("ROLE_EDITOR")) {
                rolesToRemove.add("R2"); // Admin decides to remove editor role
                System.out.println("Admin chose to REMOVE role: " + currentRole.getRoleName());
            }
        }
        for (Role availableRole : rolesNotAssigned) {
            if (availableRole.getRoleId().equals("R3") && availableRole.getRoleName().equals("ROLE_MODERATOR")) {
                rolesToAssign.add("R3"); // Admin decides to add moderator role
                System.out.println("Admin chose to ASSIGN role: " + availableRole.getRoleName());
            }
        }
        // Cache these for later 'getSelectedRoleIds' and 'getUnselectedRoleIds' which would normally come from UI.
        this.selectedRoleIdsCache = rolesToAssign;
        this.unselectedRoleIdsCache = rolesToRemove;

        System.out.println("Simulated roles to assign: " + rolesToAssign);
        System.out.println("Simulated roles to remove: " + rolesToRemove);

        // This would typically be a user action after reviewing the form
        // For simulation, we'll immediately call handleSubmitRoles with the simulated cho.
        // Sequence Diagram: m10: Admin -> View : selects/unselects roles, clicks "Send" (implemented by this direct call)
        System.out.println("\n--- Simulating Admin submitting the form ---");
        handleSubmitRoles(userId, getSelectedRoleIds(), getUnselectedRoleIds());
    }

    /**
     * Handles the submission of role changes by the administrator.
     * This method corresponds to `Admin -> View : handleSubmitRoles(...)` in the Sequence Diagram.
     * Also traces sequence diagram message m10: Admin -> View : selects/unselects roles, clicks "Send" (which triggers this method).
     *
     * @param userId the ID of the user whose roles are being updated.
     * @param rolesToAssign a list of role IDs to be assigned.
     * @param rolesToRemove a list of role IDs to be removed.
     */
    public void handleSubmitRoles(String userId, List<String> rolesToAssign, List<String> rolesToRemove) {
        System.out.println("View: Submitting role changes for user " + userId);
        try {
            // Sequence Diagram: View -> Service : updateUserRoles(...)
            boolean success = userRoleManagementService.updateUserRoles(userId, rolesToAssign, rolesToRemove);

            // Sequence Diagram: m24: Service --> View : true, "User roles modified."
            // Sequence Diagram: m31: Service --> View : false, "Role modification failed: integrity/security violation."
            String message = success ? "User roles modified successfully." : "User roles modification failed.";
            showOperationResult(success, message);
        } catch (SMOSConnectionException e) {
            // Sequence Diagram: m28: Service --> View : false, "SMOS server connection IS interrupted."
            showOperationResult(false, "Failed to update roles due to connection error: " + e.getMessage());
        }
    }

    /**
     * Simulates fetching role IDs selected by the administrator from a UI form.
     * In a real application, this would read from UI components.
     *
     * @return a list of role IDs selected for assignment.
     */
    public List<String> getSelectedRoleIds() {
        return new ArrayList<>(selectedRoleIdsCache); // Return a copy
    }

    /**
     * Simulates fetching role IDs unselected by the administrator from a UI form (i.e., roles to remove).
     * In a real application, this would read from UI components.
     *
     * @return a list of role IDs selected for removal.
     */
    public List<String> getUnselectedRoleIds() {
        return new ArrayList<>(unselectedRoleIdsCache); // Return a copy
    }

    /**
     * Displays the result of an operation to the administrator.
     * In a real UI, this would show a dialog or status message. Here, it prints to console.
     *
     * Traces sequence diagram messages m24, m28, m31 where the service returns an operation result.
     *
     * @param success true if the operation was successful, false otherwise.
     * @param message a message describing the outcome.
     */
    public void showOperationResult(boolean success, String message) {
        System.out.println("\n--- Operation Result ---");
        System.out.println("Status: " + (success ? "SUCCESS" : "FAILURE"));
        System.out.println("Message: " + message);
        System.out.println("------------------------");
    }
}