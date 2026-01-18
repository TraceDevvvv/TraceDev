package com.example.repository;

import com.example.tourist.Tourist;

/**
 * Interface for tourist repository operations.
 */
public interface TouristRepositoryInterface {
    Tourist findById(String id);
    Tourist update(Tourist tourist);
    Tourist save(Tourist tourist);
}