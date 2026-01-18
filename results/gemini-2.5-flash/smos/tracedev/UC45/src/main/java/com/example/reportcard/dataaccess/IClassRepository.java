package com.example.reportcard.dataaccess;

import com.example.reportcard.domain.Class;
import java.util.List;

/**
 * Interface for data access operations related to Class.
 */
public interface IClassRepository {
    /**
     * Finds classes associated with a specific academic year.
     * @param yearId The ID of the academic year.
     * @return A list of Class objects for the given academic year.
     */
    List<Class> findByAcademicYear(String yearId);
}