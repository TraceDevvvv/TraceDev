package com.example.infrastructure;

import com.example.domain.Teaching;
import java.util.List;

/**
 * Repository interface for Teaching entities.
 * Extends the generic SearchRepository for Teaching type.
 */
public interface TeachingRepository extends SearchRepository<Teaching> {
    /**
     * Finds teachings containing the specified keywords in their searchable content.
     * @param keywords the search keywords.
     * @return a list of matching teachings.
     */
    List<Teaching> findByKeywords(String keywords);
    // Additional teaching-specific methods could be added here
}