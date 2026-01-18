package com.example.repository;

import com.example.domain.Tourist;
import java.util.List;

/**
 * Interface for Tourist repository operations.
 */
public interface TouristRepository {
    List<Tourist> findAll();
    void deleteById(String id);
}