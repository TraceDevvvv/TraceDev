package com.example;

/**
 * Defines the contract for security-related operations,
 * specifically for authentication and role-based authorization.
 * This is part of the Security Layer.
 */
public interface ISecurityService {

    /**
     * Checks if the current user is authenticated (logged in).
     * @return true if the user is authenticated, false otherwise.
     */
    boolean isAuthenticated();

    /**
     * Checks if the current user has a specific role.
     * @param role The role to check against.
     * @return true if the user has the specified role, false otherwise.
     */
    boolean hasRole(String role);
}