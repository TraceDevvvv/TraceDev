package com.example.repository;

import com.example.domain.CulturalObject;

/**
 * Repository interface for CulturalObject persistence.
 */
public interface CulturalObjectRepository {
    CulturalObject findById(int id);
    CulturalObject save(CulturalObject culturalObject);
}