package com.example.dto;

import java.util.List;
import java.util.ArrayList;

/**
 * Data Transfer Object for Preferences.
 */
public class PreferencesDTO {
    private String language;
    private String currency;
    private boolean notificationEnabled;
    private List<String> dietaryRestrictions;

    /**
     * Default constructor.
     */
    public PreferencesDTO() {
        this.dietaryRestrictions = new ArrayList<>();
    }

    /**
     * Parameterized constructor.
     * @param language the language
     * @param currency the currency
     * @param notificationEnabled whether notifications are enabled
     * @param dietaryRestrictions the list of dietary restrictions
     */
    public PreferencesDTO(String language, String currency, boolean notificationEnabled, List<String> dietaryRestrictions) {
        this.language = language;
        this.currency = currency;
        this.notificationEnabled = notificationEnabled;
        this.dietaryRestrictions = (dietaryRestrictions != null) ? new ArrayList<>(dietaryRestrictions) : new ArrayList<>();
    }

    // Getters and setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public List<String> getDietaryRestrictions() {
        return new ArrayList<>(dietaryRestrictions);
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = (dietaryRestrictions != null) ? new ArrayList<>(dietaryRestrictions) : new ArrayList<>();
    }
}