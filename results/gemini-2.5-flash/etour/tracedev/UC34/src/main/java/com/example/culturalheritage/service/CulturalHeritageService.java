package com.example.culturalheritage.service;

import com.example.culturalheritage.model.SearchCriteria;
import com.example.culturalheritage.model.SearchResult;
import com.example.culturalheritage.model.CulturalHeritage;
import com.example.culturalheritage.model.Location;
import com.example.culturalheritage.repository.ICulturalHeritageRepository;
import com.example.culturalheritage.exception.SearchFailedException;

import java.util.List;

/**
 * An Application Service responsible for orchestrating the search for cultural heritage.
 * It uses the repository to access data and returns a SearchResult.
 */
public class CulturalHeritageService {
    private ICulturalHeritageRepository culturalHeritageRepository;

    /**
     * Constructor for CulturalHeritageService.
     * Injects the repository dependency.
     * @param culturalHeritageRepository The repository for accessing cultural heritage data.
     */
    public CulturalHeritageService(ICulturalHeritageRepository culturalHeritageRepository) {
        this.culturalHeritageRepository = culturalHeritageRepository;
    }

    /**
     * Searches for cultural heritage items based on the provided criteria and user's position.
     * @param criteria The search criteria.
     * @param userPosition The current geographical location of the user.
     * @return A SearchResult object containing the found cultural heritage items.
     * @throws SearchFailedException if there's an issue retrieving data from the underlying system.
     */
    public SearchResult searchCulturalHeritage(SearchCriteria criteria, Location userPosition) throws SearchFailedException {
        System.out.println("CulturalHeritageService: searchCulturalHeritage() - Delegate to repository.");

        // Delegate the actual data retrieval to the cultural heritage repository.
        // The repository handles interaction with the external system (ETOUR).
        List<CulturalHeritage> heritageList = culturalHeritageRepository.findHeritageByCriteria(criteria, userPosition);

        // Wrap the list of cultural heritage items into a SearchResult object.
        return new SearchResult(heritageList);
    }
}