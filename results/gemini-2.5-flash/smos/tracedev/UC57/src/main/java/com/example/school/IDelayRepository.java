package com.example.school;

import java.util.List;

/**
 * Repository interface specifically for Delay entities.
 * Extends IRepository with Delay as the entity type and String as the ID type.
 */
public interface IDelayRepository extends IRepository<Delay, String> {
    /**
     * Finds all delay records for a specific class.
     * @param classId The unique identifier of the class.
     * @return A list of delay records for the class.
     * @throws SMOSConnectionException if there's a connection issue.
     */
    List<Delay> findByClassId(String classId) throws SMOSConnectionException;
}