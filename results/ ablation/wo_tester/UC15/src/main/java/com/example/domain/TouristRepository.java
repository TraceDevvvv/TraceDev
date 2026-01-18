package com.example.domain;

import java.util.List;
import java.util.Map;

/**
 * Repository interface for Tourist persistence operations.
 */
public interface TouristRepository {
    Tourist findById(String id);
    Tourist update(Tourist tourist);
    List<Tourist> findAllByCriteria(Map<String, Object> criteria);
}