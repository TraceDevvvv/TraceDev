package com.example.school.infrastructure;

import com.example.school.domain.Class;
import java.util.List;

/**
 * Interface for operations related to Class persistence.
 */
public interface IClassRepository {
    /**
     * Finds all classes associated with a specific academic year.
     * @param yearId The ID of the academic year.
     * @return A list of Class objects.
     */
    List<Class> findClassesByAcademicYear(String yearId);
}