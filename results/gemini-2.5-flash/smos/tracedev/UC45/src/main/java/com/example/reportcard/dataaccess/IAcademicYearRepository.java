package com.example.reportcard.dataaccess;

import com.example.reportcard.domain.AcademicYear;
import java.util.List;

/**
 * Interface for data access operations related to AcademicYear.
 */
public interface IAcademicYearRepository {
    /**
     * Retrieves all academic years.
     * @return A list of all AcademicYear objects.
     */
    List<AcademicYear> findAll();
}