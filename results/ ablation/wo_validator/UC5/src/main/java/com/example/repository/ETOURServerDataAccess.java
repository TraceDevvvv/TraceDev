package com.example.repository;

import com.example.model.CulturalHeritageEntity;
import com.example.model.HeritageSearchCriteria;
import java.util.List;
import java.util.ArrayList;

/**
 * Concrete implementation of CulturalHeritageRepository that accesses the ETOUR server.
 */
public class ETOURServerDataAccess implements CulturalHeritageRepository {

    private String serverUrl;
    private int connectionTimeout;

    /**
     * Constructor with configuration.
     * @param serverUrl the URL of the ETOUR server
     * @param connectionTimeout the connection timeout in milliseconds
     */
    public ETOURServerDataAccess(String serverUrl, int connectionTimeout) {
        this.serverUrl = serverUrl;
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * Fetches a cultural heritage by ID from the ETOUR server.
     * @param id the unique identifier
     * @return JSON data as a string (simulated)
     */
    public String fetchHeritageById(String id) {
        // Simulate network call to ETOUR server
        System.out.println("Fetching heritage with ID: " + id + " from ETOUR server at " + serverUrl);
        // In a real implementation, this would make an HTTP request and return JSON.
        // For simulation, we return a dummy JSON string.
        return "{\"id\":\"" + id + "\",\"name\":\"Sample Heritage\",\"location\":\"Athens\",\"period\":\"Classical\",\"description\":\"A beautiful ancient monument.\"}";
    }

    /**
     * Searches for cultural heritage items on the ETOUR server based on criteria.
     * @param criteria the search criteria
     * @return JSON data as a string (simulated)
     */
    public String searchHeritage(HeritageSearchCriteria criteria) {
        // Simulate network call to ETOUR server
        System.out.println("Searching heritage with criteria: keyword=" + criteria.getKeyword() +
                ", period=" + criteria.getPeriodFilter() + ", location=" + criteria.getLocationFilter());
        // Return dummy JSON array
        return "[{\"id\":\"1\",\"name\":\"Heritage One\",\"location\":\"Rome\",\"period\":\"Ancient\",\"description\":\"First heritage.\"}, " +
               "{\"id\":\"2\",\"name\":\"Heritage Two\",\"location\":\"Paris\",\"period\":\"Medieval\",\"description\":\"Second heritage.\"}]";
    }

    @Override
    public CulturalHeritageEntity findById(String id) {
        try {
            String jsonData = fetchHeritageById(id);
            // Simulate parsing JSON into an entity.
            CulturalHeritageEntity entity = new CulturalHeritageEntity();
            entity.setId(id);
            entity.setName("Sample Heritage");
            entity.setLocation("Athens");
            entity.setPeriod("Classical");
            entity.setDescription("A beautiful ancient monument.");
            return entity;
        } catch (Exception e) {
            // Simulate connection interruption as per sequence diagram
            throw new RuntimeException("ConnectionException: Unable to connect to ETOUR server", e);
        }
    }

    @Override
    public List<CulturalHeritageEntity> findAllByCriteria(HeritageSearchCriteria criteria) {
        try {
            String jsonData = searchHeritage(criteria);
            // Simulate parsing JSON array into a list of entities.
            List<CulturalHeritageEntity> result = new ArrayList<>();
            CulturalHeritageEntity entity1 = new CulturalHeritageEntity();
            entity1.setId("1");
            entity1.setName("Heritage One");
            entity1.setLocation("Rome");
            entity1.setPeriod("Ancient");
            entity1.setDescription("First heritage.");
            result.add(entity1);
            CulturalHeritageEntity entity2 = new CulturalHeritageEntity();
            entity2.setId("2");
            entity2.setName("Heritage Two");
            entity2.setLocation("Paris");
            entity2.setPeriod("Medieval");
            entity2.setDescription("Second heritage.");
            result.add(entity2);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error during search", e);
        }
    }
}