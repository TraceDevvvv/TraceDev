
package com.touristGuide.usecase;

import com.touristGuide.model.*;
import com.touristGuide.repository.SiteRepository;
import com.touristGuide.service.ETOURClient;
import com.touristGuide.service.GeoLocationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <<trace>> Requirement 11: ETOUR connection handling.
 */
public class AdvancedSearchUseCaseImpl implements AdvancedSearchUseCase {
    private SiteRepository siteRepository;
    private GeoLocationService geoService;
    private ETOURClient etourClient;

    public AdvancedSearchUseCaseImpl(SiteRepository siteRepository, GeoLocationService geoService, ETOURClient etourClient) {
        this.siteRepository = siteRepository;
        this.geoService = geoService;
        this.etourClient = etourClient;
    }

    @Override
    public List<Site> execute(SearchQuery query) {
        // Step 1: Filter by repository criteria
        List<Site> sitesByCriteria = siteRepository.findByCriteria(query.getCriteria());

        // Step 2: Filter by geo location
        List<Site> sitesByGeo = filterByLocation(sitesByCriteria, query.getUserLocation());

        // Step 3: Event filtering if ETOUR connected
        List<Site> sitesByEvent = new ArrayList<>();
        boolean connectionOk = checkETOURConnection();
        if (connectionOk) {
            List<TouristEvent> events = etourClient.fetchTouristEvents(query.getUserLocation());
            sitesByEvent = applyEventFilter(sitesByCriteria, events, query.getUserLocation());
        } else {
            // <<trace>> Requirement 11: connection lost – continue without event filtering
            // Connection error reporting is handled by controller via separate mechanism.
            // Here we just skip event filtering.
            sitesByEvent = new ArrayList<>(sitesByCriteria);
        }

        // Step 4: Combine and rank results
        return combineAndRankResults(sitesByGeo, sitesByEvent);
    }

    /**
     * Check ETOUR connection.
     */
    protected boolean checkETOURConnection() {
        return etourClient.isConnected();
    }

    /**
     * Apply event filter (requirement 9).
     */
    protected List<Site> applyEventFilter(List<Site> sites, List<TouristEvent> events, Location location) {
        // Simplified: keep sites that have any event of matching type.
        // In reality, would match events to sites by siteId and filter by event type.
        List<Site> filtered = new ArrayList<>();
        for (Site site : sites) {
            for (TouristEvent event : events) {
                if (event.getSiteId().equals(site.getId())) {
                    filtered.add(site);
                    break;
                }
            }
        }
        return filtered;
    }

    /**
     * Combine and rank results (as per sequence diagram).
     */
    protected List<Site> combineAndRankResults(List<Site> sitesByGeo, List<Site> sitesByEvent) {
        // Simple union, remove duplicates by id.
        List<Site> combined = new ArrayList<>();
        for (Site site : sitesByGeo) {
            if (!containsSite(combined, site.getId())) {
                combined.add(site);
            }
        }
        for (Site site : sitesByEvent) {
            if (!containsSite(combined, site.getId())) {
                combined.add(site);
            }
        }
        // Ranking: by proximity? For now, just return as is.
        return combined;
    }

    /**
     * Filter sites by location (missing from class diagram)
     */
    protected List<Site> filterByLocation(List<Site> sites, Location location) {
        List<Site> filtered = new ArrayList<>();
        for (Site site : sites) {
            // Calculate distance and filter within a radius (e.g., 10 km)
            float distance = site.getLocation().calculateDistance(location);
            if (distance <= 10.0f) {
                filtered.add(site);
            }
        }
        return filtered;
    }

    /**
     * Filter by tourist event type (missing from class diagram)
     */
    protected List<Site> filterByTouristEvent(List<Site> sites, String eventType) {
        List<Site> filtered = new ArrayList<>();
        for (Site site : sites) {
            Map<String, Object> attrs = site.getAttributes();
            if (attrs != null) {
                Object eventAttr = attrs.get("eventType");
                if (eventAttr != null && eventAttr.toString().equalsIgnoreCase(eventType)) {
                    filtered.add(site);
                }
            }
        }
        return filtered;
    }

    private boolean containsSite(List<Site> list, String id) {
        for (Site s : list) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
