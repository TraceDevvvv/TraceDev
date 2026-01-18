package com.ata.service;

import com.ata.entity.Class;
import java.util.List;

/**
 * Interface for querying classes. Part of the application layer.
 */
public interface ClassQueryService {
    /**
     * Retrieve all classes with their registry keys.
     * @return List of Class entities.
     */
    List<Class> getAllClasses();
}