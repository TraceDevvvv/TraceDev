package com.example.repository;

import java.util.Set;
import java.util.HashSet;

/**
 * Repository for managing search tags.
 */
public class SearchTagRepository {
    private Set<String> searchTags;

    public SearchTagRepository() {
        searchTags = new HashSet<>();
        // Initialize with some example tags
        searchTags.add("java");
        searchTags.add("design-patterns");
        searchTags.add("uml");
    }

    public boolean exists(String tag) {
        return searchTags.contains(tag.toLowerCase());
    }

    public Set<String> getAllTags() {
        return new HashSet<>(searchTags);
    }
}