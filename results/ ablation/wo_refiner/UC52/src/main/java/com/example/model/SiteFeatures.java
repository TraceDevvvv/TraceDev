package com.example.model;

import java.util.List;

/**
 * Features of a tourist site.
 */
public class SiteFeatures {
    private String category;
    private List<String> tags;
    private int rating;

    public SiteFeatures(String category, List<String> tags, int rating) {
        this.category = category;
        this.tags = tags;
        this.rating = rating;
    }

    public boolean matches(SiteFeatures otherFeatures) {
        // Simplified matching: same category and rating >= other's rating.
        return this.category.equals(otherFeatures.category) && this.rating >= otherFeatures.rating;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "SiteFeatures{category=" + category + ", rating=" + rating + "}";
    }
}