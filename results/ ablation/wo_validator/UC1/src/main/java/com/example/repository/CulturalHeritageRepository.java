package com.example.repository;

import com.example.domain.CulturalHeritage;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Cultural Heritage data access
 */
public interface CulturalHeritageRepository {
    List<CulturalHeritage> findAll();
    Optional<CulturalHeritage> findById(String id);
    boolean delete(String id);
    CulturalHeritage save(CulturalHeritage entity);
}