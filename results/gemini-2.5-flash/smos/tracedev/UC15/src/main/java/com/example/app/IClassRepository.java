package com.example.app;

/**
 * Interface for managing Class data access.
 * This corresponds to the 'IClassRepository' interface in the UML Class Diagram.
 */
public interface IClassRepository {

    /**
     * Finds a Class by its unique identifier.
     *
     * @param classId The ID of the class to find.
     * @return The Class object if found.
     * @throws ConnectionException If there is a problem connecting to the data source (R12).
     */
    Class findById(String classId) throws ConnectionException;
}