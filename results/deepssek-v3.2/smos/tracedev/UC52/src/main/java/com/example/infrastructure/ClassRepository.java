package com.example.infrastructure;

import com.example.domain.Class;
import java.util.List;

/**
 * Repository interface for Class entities.
 * Extends the generic SearchRepository for Class type.
 */
public interface ClassRepository extends SearchRepository<Class> {
    /**
     * Finds classes containing the specified keywords in their searchable content.
     * @param keywords the search keywords.
     * @return a list of matching classes.
     */
    List<Class> findByKeywords(String keywords);
    // Additional class-specific methods could be added here
}