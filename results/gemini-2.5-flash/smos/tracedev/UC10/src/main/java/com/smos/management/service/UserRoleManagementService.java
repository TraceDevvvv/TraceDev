package com.smos.management.service;

import com.smos.management.exceptions.SMOSConnectionException;
import com.smos.management.model.Role;
import com.smos.management.model.User;
import com.smos.management.repository.IRoleRepository;
import com.smos.management.repository.IUserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manages user roles, interacting with user and role repositories.
 * This class implements the core business logic as described in the Class Diagram
 * and orchestrates interactions as seen in the Sequence Diagram.
 */
public class UserRoleManagementService {
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;

    /**
     * Constructs a UserRoleManagementService with necessary repository dependencies.
     * This demonstrates dependency injection for the 'uses' relationships.
     *
     * @param userRepo the repository for user data.
     * @param roleRepo the repository for role data.
     */
    public UserRoleManagementService(IUserRepository userRepo, IRoleRepository roleRepo) {
        this.userRepository = userRepo;
        this.roleRepository = roleRepo;
    }

    /**
     * Retrieves details for a specific user and all available roles.
     * This method corresponds to the initial part of the sequence diagram,
     * where the View requests user and role data.
     *
     * Sequence Diagram: m3: View -> Service: displayUserRoleForm("user123") (conceptually, this method provides data for the form)
     * Sequence Diagram: m5: UserRepo --> Service: userObject (UserEntity)
     * Sequence Diagram: m7: RoleRepo --> Service: allAvailableRoles (List<RoleEntity>)
     *
     * @param userId the ID of the user whose details are required.
     * @return a Map containing "user" (User object) and "allRoles" (List<Role>).
     * @throws SMOSConnectionException if there's a problem accessing user or role data.
     * @throws IllegalArgumentException if the user is not found.
     */
    public Map<String, Object> getUserDetailsAndAvailableRoles(String userId) throws SMOSConnectionException {
        // Sequence Diagram: Service -> UserRepo : findById("user123")
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        // Sequence Diagram: Service -> RoleRepo : findAll()
        List<Role> allRoles = roleRepository.findAll();

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("allRoles", allRoles);
        return result;
    }

    /**
     * Updates a user's roles by assigning new roles and removing existing ones.
     * This method implements the main logic of the 'handleSubmitRoles' part
     * of the Sequence Diagram.
     *
     * @param userId the ID of the user to modify.
     * @param rolesToAssign a list of role IDs to assign to the user.
     * @param rolesToRemove a list of role IDs to remove from the user.
     * @return true if the role modification was successful, false otherwise.
     * @throws SMOSConnectionException if there's a problem accessing user or role data.
     */
    public boolean updateUserRoles(String userId, List<String> rolesToAssign, List<String> rolesToRemove) throws SMOSConnectionException {
        // Sequence Diagram: Service -> UserRepo : findById("user123")
        User userToModify = userRepository.findById(userId);
        if (userToModify == null) {
            System.err.println("Error: User with ID " + userId + " not found for role modification.");
            return false;
        }

        // Sequence Diagram: m14: note: "System MUST ensure integrity and security of modifications."
        // Sequence Diagram: m30: note: "Integrity or security violation detected"
        // Sequence Diagram: Service -> Service : validateRoleModification(...)
        if (!validateRoleModification(userToModify, rolesToAssign, rolesToRemove)) {
            System.err.println("Role modification failed: integrity/security violation detected during validation.");
            return false;
        }

        // Apply roles to assign
        // Sequence Diagram: loop for each roleId in rolesToAssign
        for (String roleId : rolesToAssign) {
            // Sequence Diagram: Service -> RoleRepo : findById(roleId)
            Role roleToAdd = roleRepository.findById(roleId);
            if (roleToAdd != null) {
                // Sequence Diagram: Service -> UserEntity : addRole(roleToAdd)
                userToModify.addRole(roleToAdd);
            } else {
                System.err.println("Warning: Role with ID " + roleId + " to assign not found.");
            }
        }

        // Apply roles to remove
        // Sequence Diagram: loop for each roleId in rolesToRemove
        for (String roleId : rolesToRemove) {
            // Sequence Diagram: Service -> RoleRepo : findById(roleId)
            Role roleToRemove = roleRepository.findById(roleId);
            if (roleToRemove != null) {
                // Sequence Diagram: Service -> UserEntity : removeRole(roleToRemove)
                userToModify.removeRole(roleToRemove);
            } else {
                System.err.println("Warning: Role with ID " + roleId + " to remove not found.");
            }
        }

        // Sequence Diagram: Service -> UserRepo : save(userToModify)
        try {
            userRepository.save(userToModify);
            // Sequence Diagram: UserRepo --> Service : success
            System.out.println("User " + userId + " roles successfully updated. Current roles: " + userToModify.getRoles().stream().map(Role::getRoleName).collect(Collectors.joining(", ")));
            // Sequence Diagram: m24: Service --> View: true, "User roles modified." (part of return path)
            return true;
        } catch (SMOSConnectionException e) {
            // Sequence Diagram: m26: UserRepo --> Service : error: "SMOS server connection interrupted."
            // Sequence Diagram: m27: note: "Handles data access error"
            System.err.println("Error saving user roles due to connection issue: " + e.getMessage());
            throw e; // Re-throw to propagate the exception as per diagram's error handling
        }
    }

    /**
     * Validates the proposed role modification.
     * This private method is called internally to ensure integrity and security.
     * For this implementation, it's a placeholder that assumes validity.
     * In a real application, this would contain complex business rules.
     *
     * Sequence Diagram: m14: note: "System MUST ensure integrity and security of modifications."
     *
     * @param user the user whose roles are being modified.
     * @param rolesToAssign the list of role IDs proposed for assignment.
     * @param rolesToRemove the list of role IDs proposed for removal.
     * @return true if the modification is valid, false otherwise.
     */
    private boolean validateRoleModification(User user, List<String> rolesToAssign, List<String> rolesToRemove) {
        // Note: This is a placeholder for actual validation logic.
        // As per the sequence diagram, it simply indicates a check for integrity/security.
        // For example, it might check:
        // - if critical roles (e.g., 'admin') cannot be removed from the last admin.
        // - if roles being assigned/removed actually exist.
        // - if the current administrator has permissions to make these changes.
        System.out.println("Service: Validating role modification for user " + user.getUserId() + "...");
        // Assume valid for now for demonstration purposes.
        return true;
    }
}