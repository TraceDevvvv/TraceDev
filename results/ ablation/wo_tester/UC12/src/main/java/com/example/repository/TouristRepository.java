package com.example.repository;

import com.example.model.Tourist;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Tourist operations.
 */
public interface TouristRepository {
    List<Tourist> findAll(String searchCriteria);
    Optional<Tourist> findById(int id);
}