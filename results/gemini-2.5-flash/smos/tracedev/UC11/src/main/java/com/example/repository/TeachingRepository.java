package com.example.repository;

import com.example.model.Teaching;

import java.util.List;

/**
 * Interface for data access operations related to Teaching entities.
 */
public interface TeachingRepository {
    /**
     * Finds all teachings for a given class ID.
     * @param schoolClassId The ID of the school class.
     * @return A list of Teaching objects.
     */
    List<Teaching> findBySchoolClassId(String schoolClassId); // Renamed from findByClassId

    /**
     * Finds all teachings for a given class ID.
     * This method exists to directly match the class diagram's naming.
     * @param classId The ID of the class.
     * @return A list of Teaching objects.
     */
    List<Teaching> findByClassId(String classId);

    /**
     * Finds a teaching by its ID.
     * @param id The ID of the teaching.
     * @return The Teaching object if found, null otherwise.
     */
    Teaching findById(String id);
}