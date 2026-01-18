package com.ata.repository;

import com.ata.entity.Class;
import java.util.List;

/**
 * Repository interface for Class entity.
 */
public interface ClassRepository {
    /**
     * Finds all classes.
     * @return List of Class entities.
     */
    List<Class> findAll();
}