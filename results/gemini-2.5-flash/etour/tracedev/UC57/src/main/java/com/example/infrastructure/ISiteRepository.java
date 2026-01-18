package com.example.infrastructure;

import com.example.domain.Site;
import com.example.domain.Location;
import com.example.dto.AdvancedSearchRequestDTO;

import java.util.List;

/**
 * Interface for a repository that manages Site entities.
 * Defines methods for searching sites based on advanced criteria and location.
 */
public interface ISiteRepository {
    /**
     * Finds sites based on advanced search criteria and a user's location.
     * @param criteria The {@link AdvancedSearchRequestDTO} containing search parameters.
     * @param userLocation The user's current {@link Location}, used for proximity search.
     * @return A list of {@link Site} entities matching the criteria.
     */
    List<Site> querySites(AdvancedSearchRequestDTO criteria, Location userLocation);
}