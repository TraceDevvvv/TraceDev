package com.example.smos.infrastructure;

import com.example.smos.domain.AcademicYear;
import java.util.List;

/**
 * Interface for data access operations related to AcademicYear entities.
 */
public interface AcademicYearRepository {
    /**
     * Retrieves all available academic years.
     * @return A list of all academic years.
     * @throws com.example.smos.exception.SMOSConnectionException if there's a problem connecting to the data source.
     */
    List<AcademicYear> findAll();
}