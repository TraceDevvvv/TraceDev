package com.example.repository;

import com.example.entity.Class;
import java.util.List;

/**
 * Repository interface for Class entities.
 */
public interface ClassRepository {
    Class findById(String id);
    List<Class> findByAcademicYear(String year);
}