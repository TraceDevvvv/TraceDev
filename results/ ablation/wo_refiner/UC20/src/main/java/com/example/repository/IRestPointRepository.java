package com.example.repository;

import com.example.model.RestPoint;

/**
 * Rest point repository interface.
 */
public interface IRestPointRepository {
    RestPoint findById(String id);
    void save(RestPoint restPoint);
}