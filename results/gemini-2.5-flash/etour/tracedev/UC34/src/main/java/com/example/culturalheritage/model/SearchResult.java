package com.example.culturalheritage.model;

import java.util.List;
import java.util.Collections;

/**
 * A Value Object representing the results of a cultural heritage search.
 * Contains a list of CulturalHeritage entities.
 */
public class SearchResult {
    private List<CulturalHeritage> items;

    /**
     * Constructor for SearchResult.
     * @param items A list of CulturalHeritage objects found by the search.
     */
    public SearchResult(List<CulturalHeritage> items) {
        // Ensure the list is not null and is immutable from outside
        this.items = items != null ? Collections.unmodifiableList(items) : Collections.emptyList();
    }

    /**
     * Returns an unmodifiable list of cultural heritage items.
     * @return A list of CulturalHeritage objects.
     */
    public List<CulturalHeritage> getItems() {
        return items;
    }

    // No setter for items, as SearchResult is a Value Object and should be immutable once created.
}