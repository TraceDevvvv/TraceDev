package com.example.repository;

import com.example.model.TouristCard;

/**
 * Repository interface for TouristCard entities.
 */
public interface ITouristCardRepository {
    TouristCard findByTouristId(String touristId);
}