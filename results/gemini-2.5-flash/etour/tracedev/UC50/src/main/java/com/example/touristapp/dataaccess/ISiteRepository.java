package com.example.touristapp.dataaccess;

import com.example.touristapp.domain.Site;

import java.util.List;

/**
 * Interface for data access operations related to {@link Site} entities.
 * Specifies the contract for retrieving site information from a data source.
 */
public interface ISiteRepository {
    /**
     * Finds all sites visited by a specific tourist.
     * Satisfies REQ-007: Exit Conditions: Connection to the server ETOUR IS interrupted.
     * // Traceability: Sequence Diagram: --> SiteRepo: List<Site> siteEntities
     * // Traceability: Sequence Diagram: --x SiteRepo: throws NetworkConnectionException
     *
     * @param touristId The unique identifier of the tourist.
     * @return A list of {@link Site} objects visited by the tourist.
     * @throws NetworkConnectionException If a network connection issue occurs during data retrieval.
     */
    List<Site> findVisitedSitesByTourist(String touristId) throws NetworkConnectionException;
}