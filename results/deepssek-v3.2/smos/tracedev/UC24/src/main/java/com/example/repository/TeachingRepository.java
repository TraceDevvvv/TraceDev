package com.example.repository;

import com.example.domain.Teaching;

/**
 * Repository interface for Teaching entities.
 */
public interface TeachingRepository {
    Teaching findById(int teachingId);
}