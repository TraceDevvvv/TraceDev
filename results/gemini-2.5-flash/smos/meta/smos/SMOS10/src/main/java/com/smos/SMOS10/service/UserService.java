package com.smos.rolemanagement.service;

import com.smos.rolemanagement.model.Role;
import com.smos.rolemanagement.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service layer for managing users and their roles.
 * This class simulates data storage and business logic for user and role operations.
 * It provides methods to retrieve user details and update user roles.
 */
public class UserService {

    // Simulates a database or data store for users.
    // Key: User ID, Value: User object
    private final Map<String, User> usersDataStore;

    // Simulates a database or data store for available roles.
    // Key: Role ID, Value: Role object
    private final Map<String, Role> availableRolesDataStore;

    /**
     * Constructor for UserService.
     * Initializes the in-memory data stores with some sample data.
     */
    public UserService() {
        this.usersDataStore = new HashMap<>();
        this.availableRolesDataStore = new HashMap<>();
        initializeSampleData();
    }

    /**
     * Initializes sample users and roles for demonstration purposes.
     * In a real application, this data would come from a persistent storage.
     */
    private void initializeSampleData() {
        // Initialize available roles
        Role adminRole = new Role("R001", "Administrator");
        Role editorRole = new Role("R002", "Editor");
        Role viewerRole = new Role("R003", "Viewer");
        Role reporterRole = new Role("R004", "Reporter");

        availableRolesDataStore.put(adminRole.getId(), adminRole);
        availableRolesDataStore.put(editorRole.getId(), editorRole);
        availableRolesDataStore.put(viewerRole.getId(), viewerRole);
        availableRolesDataStore.put(reporterRole.getId(), reporterRole);

        // Initialize sample users
        User adminUser = new User("U001", "admin");
        adminUser.addRole(adminRole); // Admin user has admin role
        adminUser.addRole(editorRole);

        User regularUser = new User("U002", "john.doe");
        regularUser.addRole(viewerRole);

        User anotherUser = new User("U003", "jane.smith");
        anotherUser.addRole(reporterRole);
        anotherUser.addRole(viewerRole);

        usersDataStore.put(adminUser.getId(), adminUser);
        usersDataStore.put(regularUser.getId(), regularUser);
        usersDataStore.put(anotherUser.getId(), anotherUser);
    }

    /**
     * Retrieves a user by their unique identifier.
     * This method fulfills the "viewing the details of a user" precondition.
     *
     * @param userId The ID of the user to retrieve.
     * @return An Optional containing the User object if found, or an empty Optional if not found.
     */
    public Optional<User> getUserById(String userId) {
        // Return a deep copy of the user to prevent external modification of the stored object
        // and its roles directly.
        return Optional.ofNullable(usersDataStore.get(userId))
                .map(user -> new User(user.getId(), user.getUsername(), user.getRoles()));
    }

    /**
     * Retrieves all available roles in the system.
     *
     * @return A set of all available Role objects.
     */
    public Set<Role> getAllAvailableRoles() {
        return new HashSet<>(availableRolesDataStore.values());
    }

    /**
     * Assigns or removes roles for a specific user.
     * This is the core logic for the "Assign/RemoveRolesToAUser" use case.
     * It updates the user's roles based on the provided set of desired role IDs.
     *
     * @param userId The ID of the user whose roles are to be updated.
     * @param desiredRoleIds A set of role IDs that the user should have after the update.
     *                       Roles not in this set will be removed, and roles in this set
     *                       that the user doesn't currently have will be added.
     * @return An Optional containing the updated User object if the user exists and roles were modified,
     *         or an empty Optional if the user does not exist.
     * @throws IllegalArgumentException if any of the desiredRoleIds do not correspond to valid roles.
     */
    public Optional<User> updateUserRoles(String userId, Set<String> desiredRoleIds) {
        User userToUpdate = usersDataStore.get(userId);

        if (userToUpdate == null) {
            // User not found, cannot update roles.
            return Optional.empty();
        }

        // Convert desired role IDs to actual Role objects
        Set<Role> newRoles = new HashSet<>();
        for (String roleId : desiredRoleIds) {
            Role role = availableRolesDataStore.get(roleId);
            if (role == null) {
                // This indicates an attempt to assign a non-existent role.
                // In a real system, this might be a validation error returned to the UI.
                throw new IllegalArgumentException("Invalid role ID provided: " + roleId);
            }
            newRoles.add(role);
        }

        // Check if the roles are actually changing to avoid unnecessary updates
        if (userToUpdate.getRoles().equals(newRoles)) {
            // No change in roles, return the current user state.
            // We return a copy to maintain immutability of the stored object.
            return Optional.of(new User(userToUpdate.getId(), userToUpdate.getUsername(), userToUpdate.getRoles()));
        }

        // Update the user's roles in the data store
        userToUpdate.setRoles(newRoles);

        // Return a deep copy of the updated user.
        return Optional.of(new User(userToUpdate.getId(), userToUpdate.getUsername(), userToUpdate.getRoles()));
    }
}