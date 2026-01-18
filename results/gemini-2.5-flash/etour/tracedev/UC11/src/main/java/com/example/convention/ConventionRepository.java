package com.example.convention;

import java.util.List;

/**
 * Interface for retrieving Convention data.
 * This acts as a contract for data access operations related to conventions.
 */
public interface ConventionRepository {
    /**
     * Finds a list of conventions associated with a specific Point of Rest ID.
     *
     * @param pointOfRestId The ID of the Point of Rest to query conventions for.
     * @return A list of Convention objects.
     * @throws EtourConnectionException if there is an issue connecting to the underlying ETOUR system.
     */
    List<Convention> findByPointOfRestId(String pointOfRestId);
}