package com.example.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a User in the system.
 * Contains roles and validation methods for role assignment and removal.
 */
public class User {
    private String id;
    public String name;
    public Set<Role> roles;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.roles = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return new HashSet<>(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Adds a role to the user after validation.
     * @param role the role to add
     */
    public void addRole(Role role) {
        if (validateRoleAssignment(role)) {
            roles.add(role);
        }
    }

    /**
     * Removes a role from the user after validation.
     * @param role the role to remove
     */
    public void removeRole(Role role) {
        if (validateRoleRemoval(role)) {
            roles.remove(role);
        }
    }

    /**
     * Validates if a role can be assigned to the user.
     * This is a stub implementation; business rules can be added here.
     * @param role the role to validate
     * @return true if assignment is valid
     */
    public boolean validateRoleAssignment(Role role) {
        // Example validation: role must not be null and user must not already have the role
        if (role == null) {
            return false;
        }
        if (roles.contains(role)) {
            System.out.println("User already has role: " + role.getName());
            return false;
        }
        return true;
    }

    /**
     * Validates if a role can be removed from the user.
     * This is a stub implementation; business rules can be added here.
     * @param role the role to validate
     * @return true if removal is valid
     */
    public boolean validateRoleRemoval(Role role) {
        // Example validation: role must not be null and user must have the role
        if (role == null) {
            return false;
        }
        if (!roles.contains(role)) {
            System.out.println("User does not have role: " + role.getName());
            return false;
        }
        return true;
    }
}