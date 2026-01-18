package com.etour.repository;

import com.etour.model.Tourist;
import java.util.List;

/**
 * Repository interface for tourist data access.
 */
public interface TouristRepository {
    List<Tourist> findAll();
    Tourist findById(String id);
}