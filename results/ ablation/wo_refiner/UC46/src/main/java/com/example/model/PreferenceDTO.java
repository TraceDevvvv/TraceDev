package com.example.model;

import java.util.List;
import java.util.Objects;

/**
 * Data Transfer Object for SearchPreference.
 * Carries the fields needed to update a search preference.
 */
public class PreferenceDTO {
    public Integer maxDistance;
    public String priceRange;
    public List<String> preferredCategories;
    public Double ratingThreshold;

    public PreferenceDTO() {}

    public PreferenceDTO(Integer maxDistance, String priceRange, List<String> preferredCategories, Double ratingThreshold) {
        this.maxDistance = maxDistance;
        this.priceRange = priceRange;
        this.preferredCategories = preferredCategories;
        this.ratingThreshold = ratingThreshold;
    }

    public Integer getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public List<String> getPreferredCategories() {
        return preferredCategories;
    }

    public void setPreferredCategories(List<String> preferredCategories) {
        this.preferredCategories = preferredCategories;
    }

    public Double getRatingThreshold() {
        return ratingThreshold;
    }

    public void setRatingThreshold(Double ratingThreshold) {
        this.ratingThreshold = ratingThreshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenceDTO that = (PreferenceDTO) o;
        return Objects.equals(maxDistance, that.maxDistance) &&
                Objects.equals(priceRange, that.priceRange) &&
                Objects.equals(preferredCategories, that.preferredCategories) &&
                Objects.equals(ratingThreshold, that.ratingThreshold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxDistance, priceRange, preferredCategories, ratingThreshold);
    }
}