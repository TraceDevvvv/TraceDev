package com.example.dataaccess;

import com.example.domain.ClassRegistry;

/**
 * Interface for accessing ClassRegistry data.
 * Defines the contract for concrete repository implementations.
 */
public interface IRegistryRepository {

    /**
     * Finds and retrieves a ClassRegistry by its unique class ID.
     *
     * @param classId The ID of the class registry to find.
     * @return The ClassRegistry object if found, or null if not found.
     * @throws SMOSConnectionException if there's an issue connecting to the data source.
     */
    ClassRegistry findByClassId(String classId);
}