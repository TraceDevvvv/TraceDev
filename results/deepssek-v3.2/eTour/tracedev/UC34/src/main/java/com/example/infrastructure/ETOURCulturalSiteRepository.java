package com.example.infrastructure;

import com.example.domain.CulturalSite;
import com.example.application.SearchCriteria;
import java.util.List;
import java.util.Map;

/**
 * Repository implementation that uses the ETOUR server client.
 */
public class ETOURCulturalSiteRepository implements CulturalSiteRepository {
    private ETOURServerClient eTourServerClient;

    public ETOURCulturalSiteRepository(ETOURServerClient client) {
        this.eTourServerClient = client;
    }

    @Override
    public List<CulturalSite> findByCriteria(Object criteria) {
        SearchCriteria searchCriteria = (SearchCriteria) criteria;
        // Convert SearchCriteria to a Map<String, String> as expected by querySites
        Map<String, String> searchParams = convertCriteriaToMap(searchCriteria);
        return eTourServerClient.querySites(searchParams);
    }

    private Map<String, String> convertCriteriaToMap(SearchCriteria criteria) {
        // Simplified conversion (real implementation would map all fields)
        return Map.of(
                "keywords", criteria.getKeywords(),
                "siteType", criteria.getSiteType(),
                "lat", String.valueOf(criteria.getUserLatitude()),
                "lon", String.valueOf(criteria.getUserLongitude()),
                "radius", String.valueOf(criteria.getSearchRadiusKm())
        );
    }
}