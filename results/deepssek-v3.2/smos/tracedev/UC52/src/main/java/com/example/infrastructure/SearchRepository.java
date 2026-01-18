package com.example.infrastructure;

import java.util.List;

/**
 * Generic repository interface for search operations.
 * Implementations handle actual database queries or external service calls.
 * @param <T> the type of entity this repository manages.
 */
public interface SearchRepository<T> {
    /**
     * Finds entities containing the specified keywords in their searchable content.
     * @param keywords the search keywords.
     * @return a list of matching entities.
     */
    List<T> findByKeywords(String keywords);
}