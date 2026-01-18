package com.example.serv;

import com.example.models.RefreshmentPoint;
import java.util.List;
import java.util.Optional;

/**
 * Service for retrieving RefreshmentPoint details.
 */
public interface RefreshmentPointService {
    /**
     * Fetches details for a specific refreshment point.
     * Simulates fetching from a remote server like ETOUR.
     * @param id the refreshment point id
     * @return Optional containing the RefreshmentPoint if found
     * @throws ServerConnectionException if connection to server is interrupted
     */
    Optional<RefreshmentPoint> getRefreshmentPointDetails(String id) throws ServerConnectionException;
}