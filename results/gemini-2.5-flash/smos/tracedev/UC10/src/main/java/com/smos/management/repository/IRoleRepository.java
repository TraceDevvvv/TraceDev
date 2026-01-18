package com.smos.management.repository;

import com.smos.management.exceptions.SMOSConnectionException;
import com.smos.management.model.Role;

import java.util.List;

/**
 * Interface for data access operations related to Roles.
 * This corresponds to the 'IRoleRepository' interface in the Class Diagram.
 */
public interface IRoleRepository {

    /**
     * Retrieves all roles available in the system.
     *
     * @return a list of all Role objects.
     * @throws SMOSConnectionException if there's an issue connecting to the data source.
     */
    List<Role> findAll() throws SMOSConnectionException;

    /**
     * Retrieves a specific role by its ID.
     *
     * @param roleId the unique identifier of the role.
     * @return the Role object if found, null otherwise.
     * @throws SMOSConnectionException if there's an issue connecting to the data source.
     */
    Role findById(String roleId) throws SMOSConnectionException;
}