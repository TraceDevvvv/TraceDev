package com.example.model;

import java.util.List;

/**
 * Represents search results from a tourist's research.
 * Added to satisfy requirement Entry Conditions (Tourist IS located in Research Results).
 */
public class ResearchResults {
    public String query;
    public List<String> results;

    public ResearchResults(String query, List<String> results) {
        this.query = query;
        this.results = results;
    }

    /**
     * Selects a site from the research results.
     * @param id the site ID
     * @return the site ID if found, null otherwise
     */
    public String selectSiteFromResults(String id) {
        if (results.contains(id)) {
            return id;
        }
        return null;
    }
}