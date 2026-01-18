package com.example.smos.infrastructure;

import com.example.smos.domain.Class;
import java.util.List;

/**
 * Interface for data access operations related to Class entities.
 */
public interface ClassRepository {
    /**
     * Retrieves a list of classes belonging to a specific academic year.
     * @param academicYearId The ID of the academic year.
     * @return A list of classes for the given academic year.
     * @throws com.example.smos.exception.SMOSConnectionException if there's a problem connecting to the data source.
     */
    List<Class> findByAcademicYearId(String academicYearId);
}