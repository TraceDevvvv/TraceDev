package com.example;

import java.util.List;

/**
 * Repository interface for Tourist entities.
 */
public interface TouristRepository {
    Tourist findById(int touristId);
    void save(Tourist tourist);
    List<Tourist> getActiveTourists();
    List<Tourist> getInactiveTourists();
}