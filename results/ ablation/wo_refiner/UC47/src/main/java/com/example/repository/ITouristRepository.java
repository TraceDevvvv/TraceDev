package com.example.repository;

import com.example.entity.TouristEntity;

/**
 * Repository interface for TouristEntity data access.
 */
public interface ITouristRepository {
    TouristEntity findById(String userId);
    boolean update(TouristEntity tourist);
}