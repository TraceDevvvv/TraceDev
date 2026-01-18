package com.example.interfaces;

import com.example.dtos.RoleDetails;

/**
 * Query service for user data (CQRS pattern).
 */
public interface IUserQueryService {
    /**
     * Retrieves the role details for a given user ID.
     *
     * @param userId the ID of the user
     * @return the role details of the user
     */
    RoleDetails getUserRole(String userId);
}