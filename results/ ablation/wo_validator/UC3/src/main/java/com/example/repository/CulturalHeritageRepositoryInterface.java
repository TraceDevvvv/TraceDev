package com.example.repository;

import com.example.model.CulturalHeritage;
import java.util.List;

/**
 * Interface for Cultural Heritage repository operations.
 * Maps to the CulturalHeritageRepositoryInterface in the UML diagram.
 */
public interface CulturalHeritageRepositoryInterface {
    CulturalHeritage findById(String id);
    List<CulturalHeritage> findAll();
    void save(CulturalHeritage culturalHeritage);
    void update(CulturalHeritage culturalHeritage);
    void delete(String id);
}