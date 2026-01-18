package com.agency.infrastructure;

import com.agency.domain.AgencyOperator;
import java.util.Optional;

/**
 * Repository interface for AgencyOperator persistence.
 * Defines the contract for data access operations.
 */
public interface IAgencyRepository {
    /**
     * Saves an AgencyOperator to the database.
     * @param operator The operator to save.
     * @return true if saved successfully, false otherwise.
     */
    boolean save(AgencyOperator operator);

    /**
     * Finds an AgencyOperator by username.
     * @param username The username to search for.
     * @return Optional containing the operator if found, empty otherwise.
     */
    Optional<AgencyOperator> findByUsername(String username);

    /**
     * Saves an AgencyOperator with fallback handling for connection errors.
     * @param operator The operator to save.
     * @return true if saved successfully, false on connection error.
     */
    boolean saveWithFallback(AgencyOperator operator);
}