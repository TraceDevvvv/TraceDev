package com.example.dto;

import com.example.enums.SearchType;

/**
 * Data Transfer Object for search parameters.
 */
public class SearchDTO {
    private String query;
    private SearchType searchType;

    /**
     * Constructor for SearchDTO.
     * @param query the search query string
     * @param searchType the type of search (BASIC/ADVANCED)
     */
    public SearchDTO(String query, SearchType searchType) {
        this.query = query;
        this.searchType = searchType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }
}