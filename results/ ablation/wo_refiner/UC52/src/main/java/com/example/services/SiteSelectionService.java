package com.example.serv;

import com.example.model.Site;
import com.example.model.SiteFeatures;
import java.util.List;

/**
 * Service to select a site based on features.
 */
public class SiteSelectionService {
    public SiteSelectionService() {
        // default constructor
    }

    /**
     * Selects a site from list that matches given features.
     */
    public Site selectSiteByFeatures(List<Site> availableSites, SiteFeatures features) {
        for (Site site : availableSites) {
            if (site.getFeatures().matches(features)) {
                return site;
            }
        }
        return null;
    }
}