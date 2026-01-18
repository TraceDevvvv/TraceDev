package com.example.culturalheritage.repository;

import com.example.culturalheritage.adapter.ETOURSystemAdapter;
import com.example.culturalheritage.model.CulturalHeritage;
import com.example.culturalheritage.model.Location;
import com.example.culturalheritage.model.SearchCriteria;
import com.example.culturalheritage.exception.SearchFailedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of the ICulturalHeritageRepository.
 * This class interacts with the external ETOUR system via an adapter
 * to retrieve cultural heritage data.
 */
public class CulturalHeritageRepository implements ICulturalHeritageRepository {
    private ETOURSystemAdapter etourSystemAdapter;

    /**
     * Constructor for CulturalHeritageRepository.
     * Injects the ETOURSystemAdapter dependency.
     * @param etourSystemAdapter The adapter for communicating with the ETOUR system.
     */
    public CulturalHeritageRepository(ETOURSystemAdapter etourSystemAdapter) {
        this.etourSystemAdapter = etourSystemAdapter;
    }

    /**
     * Finds cultural heritage items by converting the search criteria into a query
     * for the ETOUR system and mapping the results back to domain entities.
     * @param criteria The search criteria.
     * @param userPosition The user's current geographical location.
     * @return A list of CulturalHeritage objects.
     * @throws SearchFailedException if the ETOUR system connection fails (REQ-011).
     */
    @Override
    public List<CulturalHeritage> findHeritageByCriteria(SearchCriteria criteria, Location userPosition) throws SearchFailedException {
        System.out.println("CulturalHeritageRepository: findHeritageByCriteria() - Converting criteria and calling adapter.");

        // Step 1: Convert SearchCriteria to a string query suitable for ETOUR (REQ-009).
        String stringQuery = convertCriteriaToString(criteria);

        // Step 2: Call the ETOURSystemAdapter to query the external system (REQ-009, REQ-011).
        List<Map<String, Object>> rawHeritageData;
        try {
            rawHeritageData = etourSystemAdapter.queryCulturalHeritage(stringQuery, userPosition);
        } catch (SearchFailedException e) {
            // Propagate the exception if the ETOUR system is interrupted (REQ-011).
            System.err.println("CulturalHeritageRepository: ETOUR system query failed. " + e.getMessage());
            throw e;
        }

        // Step 3: Map raw data from ETOUR (List<Map<String, Object>>) to domain entities (List<CulturalHeritage>).
        List<CulturalHeritage> culturalHeritageList = new ArrayList<>();
        System.out.println("CulturalHeritageRepository: Mapping raw data to domain entities.");
        for (Map<String, Object> rawItem : rawHeritageData) {
            // Assuming the raw data map contains keys like "id", "name", "description", "type", "latitude", "longitude"
            String id = (String) rawItem.getOrDefault("id", "UNKNOWN_ID");
            String name = (String) rawItem.getOrDefault("name", "Unknown Name");
            String description = (String) rawItem.getOrDefault("description", "No description available.");
            String type = (String) rawItem.getOrDefault("type", "Unknown Type");
            double latitude = ((Number) rawItem.getOrDefault("latitude", 0.0)).doubleValue();
            double longitude = ((Number) rawItem.getOrDefault("longitude", 0.0)).doubleValue();

            Location itemLocation = new Location(latitude, longitude);
            CulturalHeritage heritage = new CulturalHeritage(id, name, description, type, itemLocation);
            culturalHeritageList.add(heritage);
        }

        return culturalHeritageList;
    }

    /**
     * Converts a SearchCriteria object into a string query format expected by the ETOUR system.
     * This method is added to satisfy requirement REQ-009.
     * @param criteria The SearchCriteria object to convert.
     * @return A string representation of the query.
     */
    public String convertCriteriaToString(SearchCriteria criteria) {
        System.out.println("CulturalHeritageRepository: Converting SearchCriteria to string query.");
        // Example conversion: a simple concatenated string.
        // In a real system, this might be a JSON string, an XML payload, or a specific API query format.
        return String.format("keyword=%s&type=%s&radius=%d",
                criteria.getKeyword(),
                criteria.getTypeFilter(),
                criteria.getRadius());
    }
}