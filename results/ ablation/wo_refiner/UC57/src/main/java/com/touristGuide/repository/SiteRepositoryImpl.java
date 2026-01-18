package com.touristGuide.repository;

import com.touristGuide.model.SearchCriteria;
import com.touristGuide.model.Site;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulates interaction with a database.
 */
public class SiteRepositoryImpl implements SiteRepository {
    // Simulated data source
    private List<Site> dataSource;

    public SiteRepositoryImpl(List<Site> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Site> findByCriteria(SearchCriteria criteria) {
        // Simplified filtering: match event type if present
        List<Site> filtered = new ArrayList<>();
        for (Site site : dataSource) {
            if (criteria.getEventType() == null || criteria.getEventType().isEmpty()) {
                filtered.add(site);
            } else {
                // Assuming event type is stored in attributes
                Object eventAttr = site.getAttributes().get("eventType");
                if (eventAttr != null && eventAttr.toString().equals(criteria.getEventType())) {
                    filtered.add(site);
                }
            }
        }
        return filtered;
    }

    /**
     * Query sites by criteria (sequence message m15).
     */
    public List<Site> querySitesByCriteria(SearchCriteria criteria) {
        return findByCriteria(criteria);
    }
}