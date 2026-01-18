package com.example.repository;

import com.example.model.Convention;
import java.util.List;

/**
 * Repository interface for convention history.
 */
public interface ConventionHistoryRepository {
    /**
     * Finds conventions by point of rest ID.
     * @param pointOfRestId the point of rest ID.
     * @return list of conventions.
     * @throws ServerConnectionException if server connection fails.
     */
    List<Convention> findByPointOfRestId(String pointOfRestId) throws ServerConnectionException;
}