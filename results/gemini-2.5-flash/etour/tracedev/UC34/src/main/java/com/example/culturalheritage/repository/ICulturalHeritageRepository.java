package com.example.culturalheritage.repository;

import com.example.culturalheritage.model.SearchCriteria;
import com.example.culturalheritage.model.CulturalHeritage;
import com.example.culturalheritage.model.Location;
import com.example.culturalheritage.exception.SearchFailedException;

import java.util.List;

/**
 * An interface for the Cultural Heritage Repository.
 * This defines the contract for data access operations related to cultural heritage.
 * It abstracts away the details of the data source.
 */
public interface ICulturalHeritageRepository {

    /**
     * Finds cultural heritage items based on search criteria and user's location.
     * @param criteria The criteria to use for the search.
     * @param userPosition The user's current geographical location, used for proximity searches.
     * @return A list of CulturalHeritage objects matching the criteria.
     * @throws SearchFailedException if an error occurs during data retrieval (e.g., external system unavailable).
     */
    List<CulturalHeritage> findHeritageByCriteria(SearchCriteria criteria, Location userPosition) throws SearchFailedException;
}