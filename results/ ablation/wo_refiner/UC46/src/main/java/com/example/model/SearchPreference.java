package com.example.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity representing the search preferences of a tourist.
 * It can be updated from a PreferenceDTO.
 */
public class SearchPreference {
    private String id;
    private String touristId;
    private Integer maxDistance;
    private String priceRange;
    private List<String> preferredCategories;
    private Double ratingThreshold;

    public SearchPreference() {
        this.id = UUID.randomUUID().toString();
    }

    public SearchPreference(String touristId) {
        this();
        this.touristId = touristId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
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

    /**
     * Updates this entity's fields from the given DTO.
     * The touristId is not changed.
     */
    public void updateFromDTO(PreferenceDTO dto) {
        if (dto.getMaxDistance() != null) {
            this.maxDistance = dto.getMaxDistance();
        }
        if (dto.getPriceRange() != null) {
            this.priceRange = dto.getPriceRange();
        }
        if (dto.getPreferredCategories() != null) {
            this.preferredCategories = dto.getPreferredCategories();
        }
        if (dto.getRatingThreshold() != null) {
            this.ratingThreshold = dto.getRatingThreshold();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchPreference that = (SearchPreference) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SearchPreference{" +
                "id='" + id + '\'' +
                ", touristId='" + touristId + '\'' +
                ", maxDistance=" + maxDistance +
                ", priceRange='" + priceRange + '\'' +
                ", preferredCategories=" + preferredCategories +
                ", ratingThreshold=" + ratingThreshold +
                '}';
    }
}