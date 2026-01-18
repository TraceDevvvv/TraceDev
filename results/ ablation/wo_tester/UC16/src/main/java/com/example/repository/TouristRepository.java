package com.example.repository;

import com.example.domain.Tourist;
import java.util.List;

/**
 * Repository interface for Tourist entity operations.
 */
public interface TouristRepository {
    /**
     * Find all tourists matching the given criteria.
     * @param criteria search criteria (e.g., name, email)
     * @return list of matching tourists
     */
    List<Tourist> findAll(String criteria);

    /**
     * Find a tourist by ID.
     * @param id tourist identifier
     * @return Tourist object or null if not found
     */
    Tourist findById(String id);

    /**
     * Soft delete a tourist (mark as deleted).
     * @param tourist tourist to delete
     */
    void delete(Tourist tourist);

    /**
     * Permanently delete a tourist from the database and mark status as DELETED.
     * This is a trace to quality requirement: Permanent deletion.
     * @param id tourist identifier
     */
    void permanentDelete(String id);
}