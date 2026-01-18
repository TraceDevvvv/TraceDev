package com.example.model;

import java.util.List;

/**
 * Memento for storing search-related state.
 */
public class SearchMemento extends SystemStateMemento {
    private String previousSearchTag;
    private List<Object> searchResults; // Using Object as generic type for SearchResult

    public SearchMemento() {
        super();
    }

    public String getPreviousSearchTag() {
        return previousSearchTag;
    }

    public void setPreviousSearchTag(String tag) {
        this.previousSearchTag = tag;
        stateData.put("previousSearchTag", tag);
    }

    public List<Object> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Object> searchResults) {
        this.searchResults = searchResults;
        stateData.put("searchResults", searchResults);
    }
}