package com.example.repository;

import com.example.model.Class;
import java.util.List;

/**
 * Repository interface for Class entities.
 */
public interface ClassRepository {
    List<Class> findByAcademicYear(String academicYear);
}