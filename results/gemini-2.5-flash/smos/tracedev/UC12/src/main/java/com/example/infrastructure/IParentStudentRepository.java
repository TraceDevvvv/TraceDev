package com.example.infrastructure;

import com.example.exception.RepositoryAccessException;
import java.util.List;

/**
 * Interface for data access operations related to Parent-Student associations.
 */
public interface IParentStudentRepository {
    /**
     * Retrieves a list of student IDs associated with a given parent ID.
     * @param parentId The ID of the parent.
     * @return A list of student IDs.
     * @throws RepositoryAccessException if there is an issue accessing the data store.
     */
    List<String> getAssociatedStudentIds(String parentId) throws RepositoryAccessException;

    /**
     * Adds an association between a parent and a student.
     * @param parentId The ID of the parent.
     * @param studentId The ID of the student.
     * @throws RepositoryAccessException if there is an issue accessing the data store.
     */
    void addAssociation(String parentId, String studentId) throws RepositoryAccessException;

    /**
     * Removes an association between a parent and a student.
     * @param parentId The ID of the parent.
     * @param studentId The ID of the student.
     * @throws RepositoryAccessException if there is an issue accessing the data store.
     */
    void removeAssociation(String parentId, String studentId) throws RepositoryAccessException;
}