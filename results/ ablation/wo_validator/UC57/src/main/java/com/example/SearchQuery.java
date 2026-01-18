package com.example;

/**
 * Query interface for performing searches.
 */
public interface SearchQuery {
    /**
     * Executes a search query.
     * @param criteria the search criteria.
     * @param location the location.
     * @return the search result.
     */
    SearchResult execute(SearchCriteria criteria, Location location);
}