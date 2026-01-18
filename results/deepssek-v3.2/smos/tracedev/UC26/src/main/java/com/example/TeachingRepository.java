package com.example;

import java.util.List;

/**
 * Repository interface for Teaching entities.
 * Includes atomic deletion method as per quality requirement.
 */
public interface TeachingRepository {
    Teaching findById(String id);
    Teaching save(Teaching teaching);
    void delete(String id);
    void deleteAtomic(String id); // Added to satisfy quality requirement for atomic deletion
    List<Teaching> findAll();
}