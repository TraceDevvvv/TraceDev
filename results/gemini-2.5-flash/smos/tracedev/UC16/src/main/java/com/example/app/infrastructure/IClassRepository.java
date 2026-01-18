package com.example.app.infrastructure;

import com.example.app.domain.ArchivedClass;
import java.util.List;

/**
 * Interface for repository operations on ArchivedClass entities.
 * This defines the contract for persisting and retrieving class data.
 */
public interface IClassRepository {
    /**
     * Deletes a class by its ID.
     * @param classId The ID of the class to delete.
     * @throws ConnectionInterruptedException if there's a problem with the database connection.
     */
    void delete(String classId) throws ConnectionInterruptedException;

    /**
     * Finds a class by its ID.
     * @param classId The ID of the class to find.
     * @return The ArchivedClass object if found, null otherwise.
     * @throws ConnectionInterruptedException if there's a problem with the database connection.
     */
    ArchivedClass findById(String classId) throws ConnectionInterruptedException;

    /**
     * Retrieves all classes from the repository.
     * @return A list of all ArchivedClass objects.
     * @throws ConnectionInterruptedException if there's a problem with the database connection.
     */
    List<ArchivedClass> findAll() throws ConnectionInterruptedException;
}