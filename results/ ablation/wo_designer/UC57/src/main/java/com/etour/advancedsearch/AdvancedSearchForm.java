package com.etour.advancedsearch;

/**
 * Represents the advanced search form filled by the tourist.
 */
public class AdvancedSearchForm {
    private String nameKeyword;
    private String category;
    private Double minRating;
    private Integer maxDistance; // in kilometers

    public AdvancedSearchForm() {
        // Default empty form
    }

    public String getNameKeyword() {
        return nameKeyword;
    }

    public void setNameKeyword(String nameKeyword) {
        this.nameKeyword = nameKeyword;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMinRating() {
        return minRating;
    }

    public void setMinRating(Double minRating) {
        this.minRating = minRating;
    }

    public Integer getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public String toString() {
        return String.format("AdvancedSearchForm{nameKeyword='%s', category='%s', minRating=%s, maxDistance=%s}",
                nameKeyword, category, minRating, maxDistance);
    }
}