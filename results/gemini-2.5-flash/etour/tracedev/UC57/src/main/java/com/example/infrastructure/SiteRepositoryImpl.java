package com.example.infrastructure;

import com.example.domain.Site;
import com.example.domain.Location;
import com.example.dto.AdvancedSearchRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Concrete implementation of ISiteRepository.
 * Simulates interaction with a database to retrieve Site entities.
 */
public class SiteRepositoryImpl implements ISiteRepository {

    // In-memory dummy data for demonstration
    private final List<Site> dummySites;

    public SiteRepositoryImpl() {
        this.dummySites = new ArrayList<>();
        // Populate with some dummy sites
        dummySites.add(new Site("S001", "Eiffel Tower", "Iconic iron lattice tower in Paris.", new Location(48.8584, 2.2945)));
        dummySites.add(new Site("S002", "Louvre Museum", "World's largest art museum, a historic monument in Paris.", new Location(48.8606, 2.3376)));
        dummySites.add(new Site("S003", "Statue of Liberty", "A colossal neoclassical sculpture on Liberty Island in New York Harbor.", new Location(40.6892, -74.0445)));
        dummySites.add(new Site("S004", "Central Park", "An urban park in New York City.", new Location(40.7829, -73.9654)));
        dummySites.add(new Site("S005", "Tokyo Tower", "A communications and observation tower in the Shiba-koen district of Minato, Tokyo.", new Location(35.6586, 139.7454)));
        dummySites.add(new Site("S006", "Tokyo Imperial Palace", "The primary residence of the Emperor of Japan.", new Location(35.6853, 139.7528)));
        dummySites.add(new Site("S007", "Colosseum", "An oval amphitheatre in the centre of the city of Rome, Italy.", new Location(41.8902, 12.4922)));
        dummySites.add(new Site("S008", "Pantheon", "A former Roman temple, now a church, in Rome, Italy.", new Location(41.8986, 12.4769)));
    }

    /**
     * Finds sites based on advanced search criteria and a user's location.
     * This implementation filters dummy data based on keyword, category, and distance.
     *
     * @param criteria The {@link AdvancedSearchRequestDTO} containing search parameters.
     * @param userLocation The user's current {@link Location}, used for proximity search.
     * @return A list of {@link Site} entities matching the criteria.
     */
    @Override
    public List<Site> querySites(AdvancedSearchRequestDTO criteria, Location userLocation) {
        System.out.println("SiteRepositoryImpl: Querying sites from DB with criteria: " + criteria + ", user location: " + userLocation);

        List<Site> filteredSites = dummySites.stream()
            .filter(site -> {
                boolean matchesKeyword = true;
                if (criteria.keyword != null && !criteria.keyword.trim().isEmpty()) {
                    matchesKeyword = site.getName().toLowerCase().contains(criteria.keyword.toLowerCase()) ||
                                     site.getDescription().toLowerCase().contains(criteria.keyword.toLowerCase());
                }

                boolean matchesCategory = true;
                // Simplified category matching: assume 'category' is part of description or a hidden tag
                // For this demo, let's just check if description contains category keyword
                if (criteria.category != null && !criteria.category.trim().isEmpty()) {
                    matchesCategory = site.getDescription().toLowerCase().contains(criteria.category.toLowerCase());
                }

                boolean withinDistance = true;
                if (criteria.maxDistanceKm > 0 && userLocation != null) {
                    double distance = site.getDistance(userLocation);
                    withinDistance = distance <= criteria.maxDistanceKm;
                }

                return matchesKeyword && matchesCategory && withinDistance;
            })
            .collect(Collectors.toList());

        System.out.println("SiteRepositoryImpl: Found " + filteredSites.size() + " sites.");
        return filteredSites;
    }
}