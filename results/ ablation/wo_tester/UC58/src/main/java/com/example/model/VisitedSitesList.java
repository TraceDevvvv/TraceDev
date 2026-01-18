package com.example.model;

import java.util.List;

/**
 * Represents a list of sites visited by a tourist.
 * Added to satisfy requirement Entry Conditions (List of Sites Visited).
 */
public class VisitedSitesList {
    public String touristId;
    public List<String> sites;

    public VisitedSitesList(String touristId, List<String> sites) {
        this.touristId = touristId;
        this.sites = sites;
    }

    /**
     * Selects a site from the visited sites list.
     * @param id the site ID
     * @return the site ID if found, null otherwise
     */
    public String selectSiteFromList(String id) {
        if (sites.contains(id)) {
            return id;
        }
        return null;
    }
}