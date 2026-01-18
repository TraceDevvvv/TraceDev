package com.example.model;

import java.util.Date;

/**
 * Entity class representing search preferences of a tourist.
 * Maps to the SearchPreferences class in the class diagram.
 */
public class SearchPreferences {
    private String preferenceId;
    private String touristId;
    private String locationPreference;
    private String activityTypePreference;
    private double budgetRange;
    private Date travelDates;

    public SearchPreferences() {
    }

    public SearchPreferences(String preferenceId, String touristId, String locationPreference,
                             String activityTypePreference, double budgetRange, Date travelDates) {
        this.preferenceId = preferenceId;
        this.touristId = touristId;
        this.locationPreference = locationPreference;
        this.activityTypePreference = activityTypePreference;
        this.budgetRange = budgetRange;
        this.travelDates = travelDates;
    }

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getLocationPreference() {
        return locationPreference;
    }

    public void setLocationPreference(String locationPreference) {
        this.locationPreference = locationPreference;
    }

    public String getActivityTypePreference() {
        return activityTypePreference;
    }

    public void setActivityTypePreference(String activityTypePreference) {
        this.activityTypePreference = activityTypePreference;
    }

    public double getBudgetRange() {
        return budgetRange;
    }

    public void setBudgetRange(double budgetRange) {
        this.budgetRange = budgetRange;
    }

    public Date getTravelDates() {
        return travelDates;
    }

    public void setTravelDates(Date travelDates) {
        this.travelDates = travelDates;
    }

    /**
     * Retrieves preferences for a given touristId.
     * Note: This method is static as per class diagram, but in a real app,
     * this would be handled by a repository or service.
     */
    public static SearchPreferences getPreferences(String touristId) {
        // This is a mock implementation.
        // In reality, this would fetch from a database.
        return null;
    }

    /**
     * Updates preferences with data from DTO.
     * Returns true if update is successful.
     */
    public boolean updatePreferences(SearchPreferences updatedData) {
        if (updatedData == null) {
            return false;
        }
        this.locationPreference = updatedData.getLocationPreference();
        this.activityTypePreference = updatedData.getActivityTypePreference();
        this.budgetRange = updatedData.getBudgetRange();
        this.travelDates = updatedData.getTravelDates();
        return true;
    }
}