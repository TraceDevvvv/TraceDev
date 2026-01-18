package com.example.school.repository;

import com.example.school.model.ClassRegistryEntry;
import java.util.List;

/**
 * Interface defining the contract for retrieving student class registry data.
 * This allows for different implementations of data storage (e.g., database, archive, external service).
 */
public interface StudentRepository {

    /**
     * Finds and returns a list of class registry entries for a given student.
     *
     * @param studentId The unique identifier of the student.
     * @return A list of {@link ClassRegistryEntry} objects for the specified student,
     *         or an empty list if no entries are found or an error occurs.
     */
    List<ClassRegistryEntry> findClassRegistryEntries(String studentId);
}