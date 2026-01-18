package com.example.touristapp.model;

import com.example.touristapp.dto.SearchPreferencesDTO;

/**
 * Entity representing a tourist's search preferences.
 * This class holds the core business logic and data for search preferences.
 */
public class SearchPreferences {
    private String preferencesId; // PrimaryKey for SearchPreferences
    private String touristId;     // ForeignKey linking to a Tourist entity (not shown)
    private String destination;
    private String budgetRange;
    private String travelDates;

    /**
     * Constructor for SearchPreferences.
     * @param preferencesId Unique identifier for these preferences.
     * @param touristId The ID of the tourist owning these preferences.
     * @param destination Preferred travel destination.
     * @param budgetRange Preferred budget range for travel.
     * @param travelDates Preferred travel dates.
     */
    public SearchPreferences(String preferencesId, String touristId, String destination, String budgetRange, String travelDates) {
        this.preferencesId = preferencesId;
        this.touristId = touristId;
        this.destination = destination;
        this.budgetRange = budgetRange;
        this.travelDates = travelDates;
    }

    // --- Getters for attributes ---
    public String getPreferencesId() {
        return preferencesId;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getDestination() {
        return destination;
    }

    public String getBudgetRange() {
        return budgetRange;
    }

    public String getTravelDates() {
        return travelDates;
    }

    /**
     * Updates the preferences using data from a DTO.
     * @param newPreferences The DTO containing the updated preferences data.
     */
    public void updatePreferences(SearchPreferencesDTO newPreferences) {
        // Assume touristId cannot be changed via this method, it's tied to the entity owner.
        // Update only modifiable fields.
        if (newPreferences.getDestination() != null) {
            this.destination = newPreferences.getDestination();
        }
        if (newPreferences.getBudgetRange() != null) {
            this.budgetRange = newPreferences.getBudgetRange();
        }
        if (newPreferences.getTravelDates() != null) {
            this.travelDates = newPreferences.getTravelDates();
        }
        // preferencesId and touristId are typically not updated directly through preferences update.
    }

    /**
     * Converts the current SearchPreferences entity into a SearchPreferencesDTO.
     * @return A SearchPreferencesDTO representing this entity.
     */
    public SearchPreferencesDTO toDTO() {
        return new SearchPreferencesDTO(
            this.touristId,
            this.destination,
            this.budgetRange,
            this.travelDates
        );
    }

    @Override
    public String toString() {
        return "SearchPreferences{" +
               "preferencesId='" + preferencesId + '\'' +
               ", touristId='" + touristId + '\'' +
               ", destination='" + destination + '\'' +
               ", budgetRange='" + budgetRange + '\'' +
               ", travelDates='" + travelDates + '\'' +
               '}';
    }
}