package com.example.repository;

import com.example.exception.RepositoryException;
import com.example.model.CulturalHeritage;

/**
 * Interface defining repository operations for Cultural Heritage.
 */
public interface ICulturalHeritageRepository {
    /**
     * Finds CulturalHeritage by ID.
     * @param id the ID to search for
     * @return CulturalHeritage object
     * @throws RepositoryException if repository operation fails
     */
    CulturalHeritage findById(String id) throws RepositoryException;
}