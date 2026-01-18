package com.example.repository;

import com.example.model.Site;
import com.example.model.Feedback;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of SiteRepository.
 */
public class SiteRepositoryImpl implements SiteRepository {
    /**
     * Simulates querying a database for sites with feedback for a tourist.
     * @param touristId the tourist's ID
     * @return list of Site objects
     */
    @Override
    public List<Site> findSitesWithFeedback(int touristId) {
        System.out.println("Querying database for sites with feedback for touristId: " + touristId);
        // Simulate database query: In a real application, this would use JDBC, JPA, etc.
        List<Site> sites = new ArrayList<>();
        // Example data: assume tourist 1 has visited sites 101 and 102
        if (touristId == 1) {
            sites.add(new Site(101, "Eiffel Tower", "Iconic tower in Paris"));
            sites.add(new Site(102, "Louvre Museum", "World's largest art museum"));
        }
        // In a real scenario, we would join with Feedback table to filter sites with feedback.
        // For simplicity, we return the sites as if they have feedback.
        return sites;
    }
}