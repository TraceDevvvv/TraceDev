package com.example.domain.repository;

import com.example.domain.model.Class;
import java.util.List;

/**
 * Interface for Class repository operations.
 */
public interface ClassRepository {
    Class findById(String id);
    void delete(Class classEntity);
    List<Class> findAll();
}