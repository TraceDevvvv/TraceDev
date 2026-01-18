package com.example.school;

import java.util.List;

/**
 * Repository interface specifically for Absence entities.
 * Extends IRepository with Absence as the entity type and String as the ID type.
 */
public interface IAbsenceRepository extends IRepository<Absence, String> {
    /**
     * Finds all absence records for a specific class.
     * @param classId The unique identifier of the class.
     * @return A list of absence records for the class.
     * @throws SMOSConnectionException if there's a connection issue.
     */
    List<Absence> findByClassId(String classId) throws SMOSConnectionException;
}