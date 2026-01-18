package com.example.repository;

import com.example.model.SearchCriteria;
import com.example.model.Location;
import com.example.model.Site;
import com.example.exception.ConnectionException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Implementation of SiteRepository.
 * Simulates querying an external ETOUR server.
 */
public class SiteRepositoryImpl implements SiteRepository {

    @Override
    public List<Site> findByCriteria(SearchCriteria criteria, Location location) throws ConnectionException {
        // Simulate connection to ETOUR server
        boolean connectionSuccessful = Math.random() > 0.2; // 80% success rate for demo

        if (!connectionSuccessful) {
            throw new ConnectionException("CONN_001", "ETOUR server connection interrupted");
        }

        // Simulate query and result
        List<Map<String, Object>> siteDataList = querySites(criteria, location);

        List<Site> sites = new ArrayList<>();
        for (Map<String, Object> siteData : siteDataList) {
            sites.add(new Site(siteData));
        }

        return sites;
    }

    /**
     * Simulates querying the ETOUR server.
     */
    private List<Map<String, Object>> querySites(SearchCriteria criteria, Location location) {
        System.out.println("Querying ETOUR server with criteria: " + criteria.getFilters() + " near " + location);

        // Return mock data
        List<Map<String, Object>> results = new ArrayList<>();

        Map<String, Object> site1 = new HashMap<>();
        site1.put("id", "S001");
        site1.put("name", "Central Park");
        site1.put("latitude", location.getLatitude() + 0.01);
        site1.put("longitude", location.getLongitude() + 0.01);

        Map<String, Object> site2 = new HashMap<>();
        site2.put("id", "S002");
        site2.put("name", "Museum of Modern Art");
        site2.put("latitude", location.getLatitude() - 0.01);
        site2.put("longitude", location.getLongitude() - 0.01);

        results.add(site1);
        results.add(site2);

        return results;
    }

    public void handleConnectionInterruption() {
        System.out.println("Connection interruption handled");
    }

    public List<Site> getEmptyList() {
        return new ArrayList<>();
    }
}