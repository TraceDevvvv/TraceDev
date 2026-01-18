package com.example.search.infrastructure;

import com.example.search.domain.CulturalObject;
import com.example.search.domain.SearchCriteria;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of CulturalObjectRepository that uses an ETOURClient to fetch data.
 */
public class ETOURCulturalObjectRepository implements CulturalObjectRepository {

    private final ETOURClient etourClient;

    /**
     * Constructs an ETOURCulturalObjectRepository with a dependency on ETOURClient.
     *
     * @param etourClient The ETOURClient instance used to communicate with the external system.
     */
    public ETOURCulturalObjectRepository(ETOURClient etourClient) {
        this.etourClient = etourClient;
    }

    /**
     * Finds cultural objects by converting SearchCriteria to an ETOUR query,
     * calling the ETOURClient, and mapping the raw results back to CulturalObject domain objects.
     * Handles connection errors as per the sequence diagram's alternative flow.
     *
     * @param criteria The search criteria.
     * @return A list of CulturalObject instances.
     * @throws ETOURConnectionException if the ETOURClient encounters a connection issue.
     */
    @Override
    public List<CulturalObject> findByCriteria(SearchCriteria criteria) {
        System.out.println("Repo: Finding cultural objects by criteria: " + criteria.getKeyword());

        // Convert SearchCriteria to a map format suitable for ETOURClient
        Map<String, String> etourQuery = criteria.toQueryMap();

        try {
            // Call ETOURClient to perform the search (as per sequence diagram)
            List<Map<String, String>> rawDataResponse = etourClient.searchETOUR(etourQuery);

            // Map the raw data from ETOUR into CulturalObject domain objects
            return rawDataResponse.stream()
                    .map(this::mapRawDataToCulturalObject)
                    .collect(Collectors.toList());
        } catch (ETOURConnectionException e) {
            // Propagate the connection error as per sequence diagram's "error" path
            System.err.println("Repo: ETOUR connection error: " + e.getMessage());
            throw e; // Re-throw to be handled by upstream layers
        }
    }

    /**
     * Helper method to map a raw data map from ETOUR into a CulturalObject.
     * Assumes keys like "id", "name", "description", "type", "year" exist in the map.
     *
     * @param rawData A map containing raw cultural object data.
     * @return A CulturalObject instance.
     */
    private CulturalObject mapRawDataToCulturalObject(Map<String, String> rawData) {
        String id = rawData.get("id");
        String name = rawData.get("name");
        String description = rawData.getOrDefault("description", "No description available.");
        String type = rawData.get("type");
        int year = 0;
        try {
            year = Integer.parseInt(rawData.get("year"));
        } catch (NumberFormatException e) {
            System.err.println("Warning: Could not parse year for CulturalObject ID " + id + ". Using default 0. Error: " + e.getMessage());
        }
        return new CulturalObject(id, name, description, type, year);
    }
}