package com.example.repository;

import com.example.model.AgencyOperator;
import java.util.Optional;

/**
 * Repository interface for AgencyOperator entities.
 * Defines CRUD operations.
 */
public interface AgencyRepository {
    Optional<AgencyOperator> findById(String agencyId);
    void save(AgencyOperator agency);
}