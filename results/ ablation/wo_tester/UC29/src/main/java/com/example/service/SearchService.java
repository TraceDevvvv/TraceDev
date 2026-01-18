package com.example.service;

import com.example.model.ValidationResult;
import com.example.repository.SearchTagRepository;

/**
 * Service for handling search operations.
 */
public class SearchService {
    private SearchTagRepository searchTagRepository;

    public SearchService() {
        this.searchTagRepository = new SearchTagRepository();
    }

    public SearchService(SearchTagRepository repository) {
        this.searchTagRepository = repository;
    }

    public ValidationResult validateSearchTag(String tag) {
        if (tag == null || tag.trim().isEmpty()) {
            return new ValidationResult(false, 1001, "Tag cannot be empty");
        }
        if (searchTagRepository.exists(tag)) {
            return new ValidationResult(false, 1002, "Tag already exists");
        }
        return new ValidationResult(true, 0, "Valid tag");
    }

    public Object searchWithTag(String tag) {
        // Simplified search result - in real implementation would return SearchResult
        return "Search results for tag: " + tag;
    }
}