package com.example.repository;

import com.example.domain.RefreshmentPoint;

/**
 * Repository interface for RefreshmentPoint entities.
 */
public interface IRefreshmentPointRepository {
    RefreshmentPoint findById(int id);
    boolean delete(int id);
    void save(RefreshmentPoint entity);
}