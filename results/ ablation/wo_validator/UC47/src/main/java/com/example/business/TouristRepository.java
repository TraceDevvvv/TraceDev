package com.example.business;

import com.example.core.TouristEntity;

/**
 * Repository interface for TouristEntity persistence.
 */
public interface TouristRepository {
    TouristEntity findById(String touristId);
    void save(TouristEntity tourist);
}