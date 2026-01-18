package com.example.repository;

import com.example.entity.Teaching;

/**
 * Repository interface for Teaching entities.
 */
public interface TeachingRepository {
    Teaching findById(String id);
}