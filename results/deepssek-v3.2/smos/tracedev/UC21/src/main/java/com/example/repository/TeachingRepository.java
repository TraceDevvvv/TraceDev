package com.example.repository;

import com.example.model.Teaching;
import java.util.List;

/**
 * Repository interface for Teaching entities.
 * Based on the UML class diagram.
 */
public interface TeachingRepository {
    Teaching findById(String teachingId);
    List<Teaching> findAll();
    boolean save(Teaching teaching);
}