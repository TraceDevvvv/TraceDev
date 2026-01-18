package com.example.presentation;

import com.example.dto.SiteResultDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Model representing the search results to be displayed in the UI.
 * Used by the Presentation Layer to hold and manage search results.
 */
public class SearchResultModel {
    public List<SiteResultDTO> results;

    /**
     * Default constructor, initializes an empty list of results.
     */
    public SearchResultModel() {
        this.results = new ArrayList<>();
    }

    /**
     * Constructor with a list of results.
     * @param results The list of {@link SiteResultDTO} to be stored.
     */
    public SearchResultModel(List<SiteResultDTO> results) {
        this.results = results != null ? new ArrayList<>(results) : new ArrayList<>();
    }

    public List<SiteResultDTO> getResults() {
        return new ArrayList<>(results); // Return a copy to prevent external modification
    }

    public void setResults(List<SiteResultDTO> results) {
        this.results = results != null ? new ArrayList<>(results) : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SearchResultModel{" +
               "results=" + results.size() + " items" +
               '}';
    }
}