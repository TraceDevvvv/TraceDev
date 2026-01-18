package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete repository that accesses a database of sites.
 */
public class SiteDatabaseRepository implements SiteRepository {
    /**
     * Simulates database query to find sites.
     * @param criteria the search criteria.
     * @param location the reference location.
     * @return a list of sites (simulated data).
     */
    @Override
    public List<Site> findByCriteria(SearchCriteria criteria, Location location) {
        // Simulate database access with dummy data.
        List<Site> sites = new ArrayList<>();
        Site site1 = new Site(1, "Statue of Liberty", "Iconic monument", new Location(40.6892, -74.0445), "Monument", 4.8);
        Site site2 = new Site(2, "Central Park", "Large urban park", new Location(40.7851, -73.9683), "Park", 4.6);
        sites.add(site1);
        sites.add(site2);

        // Apply simple filter based on maxDistance if set.
        double maxDist = criteria.getMaxDistance();
        if (maxDist > 0) {
            List<Site> filtered = new ArrayList<>();
            for (Site s : sites) {
                double dist = s.calculateDistance(location);
                if (dist <= maxDist) {
                    filtered.add(s);
                }
            }
            sites = filtered;
        }

        return sites;
    }
}