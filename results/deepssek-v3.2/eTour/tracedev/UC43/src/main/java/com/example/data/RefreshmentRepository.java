package com.example.data;

import com.example.domain.Refreshment;

/**
 * Repository interface for Refreshment entities.
 */
public interface RefreshmentRepository {
    Refreshment findById(Long id);
    void save(Refreshment refreshment);
}