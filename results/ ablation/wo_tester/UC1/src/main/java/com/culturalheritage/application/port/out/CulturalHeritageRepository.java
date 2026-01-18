package com.culturalheritage.application.port.out;

import com.culturalheritage.domain.model.CulturalHeritage;

/**
 * Repository interface for CulturalHeritage persistence.
 */
public interface CulturalHeritageRepository {
    CulturalHeritage findById(String id);
    boolean deleteById(String id);
    boolean existsById(String id);
}