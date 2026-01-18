
package com.example.touristapp.dto;

/**
 * Data Transfer Object (DTO) for Search Preferences.
 * Used to transfer search preference data between layers, often to/from the UI.
 */
public class SearchPreferencesDTO {
    private String touristId;
    private String destination;
    private String budgetRange;
    private String travelDates;

    /**
     * Constructor for SearchPreferencesDTO.
     * @param touristId The ID of the tourist.\n     * @param destination Preferred travel destination.\n     * @param budgetRange Preferred budget range.\n     * @param travelDates Preferred travel dates.\n     */
    public SearchPreferencesDTO(String touristId, String destination, String budgetRange, String travelDates) {
        this.touristId = touristId;
        this.destination = destination;
        this.budgetRange = budgetRange;
        this.travelDates = travelDates;
    }

    // --- Getters and Setters for attributes ---

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBudgetRange() {
        return budgetRange;
    }

    public void setBudgetRange(String budgetRange) {
        this.budgetRange = budgetRange;
    }

    public String getTravelDates() {
        return travelDates;
    }

    public void setTravelDates(String travelDates) {
        this.travelDates = travelDates;
    }

    @Override
    public String toString() {
        return "SearchPreferencesDTO{" +
               "touristId='" + touristId + '\'' +
               ", destination='" + destination + '\'' +
               ", budgetRange='" + budgetRange + '\'' +
               ", travelDates='" + travelDates + '\'' +
               '}';
    }
}
