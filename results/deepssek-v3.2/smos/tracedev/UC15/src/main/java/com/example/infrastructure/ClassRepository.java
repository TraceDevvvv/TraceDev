package com.example.infrastructure;

import com.example.domain.Class;

/**
 * Interface for class repository.
 * Defines the method to find a class by its ID.
 */
public interface ClassRepository {
    /**
     * Finds a class by its ID.
     * @param id The ID of the class to find.
     * @return The Class object if found.
     */
    Class findById(String id);
}