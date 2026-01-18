package com.example.interfaceadapters.gateways;

import com.example.domain.Teaching;
import java.util.List;

/**
 * Repository interface for Teaching entities.
 */
public interface TeachingRepository {
    Teaching findById(String id);
    void save(Teaching teaching);
    List<Teaching> findAll();
}