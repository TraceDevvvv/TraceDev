package com.example.model;

import java.util.List;

/**
 * Represents a list of favorite sites for a tourist.
 * Added to satisfy requirement Entry Conditions (List of Favorites).
 */
public class FavoritesList {
    public String touristId;
    public List<String> sites;

    public FavoritesList(String touristId, List<String> sites) {
        this.touristId = touristId;
        this.sites = sites;
    }

    /**
     * Selects a site from the favorites list.
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