package com.example.dto;

import com.example.model.SearchPreferences;
import java.util.Date;

/**
 * Data Transfer Object for SearchPreferences.
 * Used to transfer data between layers without exposing the entity.
 */
public class SearchPreferencesDTO {
    private String locationPreference;
    private String activityTypePreference;
    private double budgetRange;
    private Date travelDates;

    public SearchPreferencesDTO() {
    }

    public SearchPreferencesDTO(String locationPreference, String activityTypePreference,
                                double budgetRange, Date travelDates) {
        this.locationPreference = locationPreference;
        this.activityTypePreference = activityTypePreference;
        this.budgetRange = budgetRange;
        this.travelDates = travelDates;
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
     * Converts DTO to entity.
     * Note: preferenceId and touristId are not in DTO, so they must be set separately.
     */
    public SearchPreferences toEntity() {
        SearchPreferences entity = new SearchPreferences();
        entity.setLocationPreference(this.locationPreference);
        entity.setActivityTypePreference(this.activityTypePreference);
        entity.setBudgetRange(this.budgetRange);
        entity.setTravelDates(this.travelDates);
        return entity;
    }
}