package com.example.school;

import java.util.List;

/**
 * Repository interface specifically for Class entities.
 * Extends IRepository with Class as the entity type and String as the ID type.
 */
public interface IClassRepository extends IRepository<Class, String> {
    /**
     * Finds all classes taught by a specific professor.
     * @param professorId The unique identifier of the professor.
     * @return A list of classes taught by the professor.
     * @throws SMOSConnectionException if there's a connection issue.
     */
    List<Class> findByProfessorId(String professorId) throws SMOSConnectionException;
}