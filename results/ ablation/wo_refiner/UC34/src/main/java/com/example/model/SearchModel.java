package com.example.model;

import com.example.dto.SearchCriteriaDTO;
import com.example.dto.SiteDTO;
import java.util.List;

/**
 * Model component in MVC, holding search state.
 */
public class SearchModel {
    private SearchCriteriaDTO searchCriteria;
    private List<SiteDTO> searchResults;

    public SearchCriteriaDTO getCriteria() {
        return searchCriteria;
    }

    public void setCriteria(SearchCriteriaDTO criteria) {
        this.searchCriteria = criteria;
    }

    public List<SiteDTO> getResults() {
        return searchResults;
    }

    public void setResults(List<SiteDTO> results) {
        this.searchResults = results;
    }
}