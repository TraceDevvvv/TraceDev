package com.example.repository;

import com.example.model.Class;
import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Repository interface for retrieving Class entities.
 */
public interface ClassRepository {
    /**
     * Finds classes by academic year.
     * @param academicYear the academic year to search for
     * @return a list of classes for the given academic year
     * @throws ConnectionException if a connection failure occurs
     */
    List<Class> findByAcademicYear(String academicYear) throws ConnectionException;
}