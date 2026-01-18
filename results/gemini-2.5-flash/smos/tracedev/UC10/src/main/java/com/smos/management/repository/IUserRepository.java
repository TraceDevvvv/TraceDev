package com.smos.management.repository;

import com.smos.management.exceptions.SMOSConnectionException;
import com.smos.management.model.User;

/**
 * Interface for data access operations related to Users.
 * This corresponds to the 'IUserRepository' interface in the Class Diagram.
 */
public interface IUserRepository {

    /**
     * Retrieves a specific user by their ID.
     *
     * @param userId the unique identifier of the user.
     * @return the User object if found, null otherwise.
     * @throws SMOSConnectionException if there's an issue connecting to the data source.
     */
    User findById(String userId) throws SMOSConnectionException;

    /**
     * Saves or updates a user in the data source.
     *
     * @param user the User object to save.
     * @throws SMOSConnectionException if there's an issue connecting to the data source.
     */
    void save(User user) throws SMOSConnectionException;
}