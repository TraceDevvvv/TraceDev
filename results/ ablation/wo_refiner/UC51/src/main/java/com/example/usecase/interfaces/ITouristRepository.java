package com.example.usecase.interfaces;

import com.example.domain.entity.Tourist;

/**
 * Port: Interface for tourist repository operations
 */
public interface ITouristRepository {
    Tourist findTouristById(String touristId);
}