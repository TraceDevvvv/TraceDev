package com.example.repository;

import com.example.entity.ClassEntity;
import java.util.List;

/**
 * Repository interface for Class entities.
 */
public interface ClassRepository {
    /**
     * Finds all classes for a given academic year.
     * @param year the academic year
     * @return list of ClassEntity objects
     */
    List<ClassEntity> findAllByAcademicYear(int year);

    /**
     * Finds a class by its id.
     * @param id the class id
     * @return the ClassEntity or null if not found
     */
    ClassEntity findById(int id);
}