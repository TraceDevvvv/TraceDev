package com.example.repository;

import com.example.domain.RefreshmentPoint;
import com.example.domain.PersistenceException;
import java.util.List;

/**
 * Repository interface for RefreshmentPoint data access.
 */
public interface IRefreshmentPointRepository {
    RefreshmentPoint findById(String id);
    List<RefreshmentPoint> findAll();
    boolean delete(String id) throws PersistenceException;
    void save(RefreshmentPoint point) throws PersistenceException;
}