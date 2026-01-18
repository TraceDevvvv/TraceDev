package com.example.application;

/**
 * Interface for authentication and authorization serv.
 * Defines the contract for checking user roles and permissions.
 */
public interface IAuthService {
    /**
     * Checks if a user has administrative privileges.
     * @param userId The ID of the user to check.
     * @return true if the user is an administrator, false otherwise.
     */
    boolean isAdmin(String userId);
}