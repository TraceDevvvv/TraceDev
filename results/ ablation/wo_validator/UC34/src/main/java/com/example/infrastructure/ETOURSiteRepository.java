package com.example.infrastructure;

import com.example.domain.Coordinates;
import com.example.domain.SearchCriteria;
import com.example.domain.Site;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository that integrates with an external ETOUR service.
 * Simulates HTTP communication with a cultural heritage API.
 */
public class ETOURSiteRepository extends SiteRepository {
    private static final String ETOUR_SERVICE_URL = "https://api.etour.example.com/search";

    // Simulated HTTP client (for demonstration, no real HTTP calls)
    private Object httpClient = new Object();

    @Override
    public List<Site> findByCriteria(SearchCriteria criteria) {
        // Step: prepare API request (simulated)
        System.out.println("Preparing request to ETOUR service with criteria: "
                + criteria.getSiteName() + ", " + criteria.getCulturalPeriod());

        // Step: callETOURService (simulated)
        String jsonResponse = callETOURService(criteria);

        // Simulate parsing response and creating Site objects
        // For demo, we return mock data.
        return createMockSites();
    }

    /**
     * Simulates calling the external ETOUR service.
     * In a real implementation this would use an HTTP client.
     */
    private String callETOURService(SearchCriteria criteria) {
        // Simulate network call; for demo we return a dummy JSON string.
        // If connection fails, an exception could be thrown (simulating sequence diagram's "Connection Interrupted").
        // Here we assume connection is successful.
        return "{ \"sites\": [] }";
    }

    /**
     * Creates a list of mock Site objects for demonstration.
     */
    private List<Site> createMockSites() {
        List<Site> sites = new ArrayList<>();

        // Mock site 1
        Site s1 = new Site();
        s1.setId("S001");
        s1.setName("Colosseum");
        s1.setDescription("An ancient amphitheatre in Rome.");
        Coordinates loc1 = new Coordinates();
        loc1.setLatitude(41.8902);
        loc1.setLongitude(12.4922);
        s1.setLocation(loc1);
        s1.setCulturalPeriod("Roman Empire");
        sites.add(s1);

        // Mock site 2
        Site s2 = new Site();
        s2.setId("S002");
        s2.setName("Pantheon");
        s2.setDescription("A former Roman temple, now a church.");
        Coordinates loc2 = new Coordinates();
        loc2.setLatitude(41.8986);
        loc2.setLongitude(12.4769);
        s2.setLocation(loc2);
        s2.setCulturalPeriod("Roman Empire");
        sites.add(s2);

        return sites;
    }
}