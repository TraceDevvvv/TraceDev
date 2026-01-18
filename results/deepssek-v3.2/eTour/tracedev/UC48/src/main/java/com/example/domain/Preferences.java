package com.example.domain;

import com.example.dto.PreferencesDTO;
import java.util.List;
import java.util.ArrayList;

/**
 * Domain entity representing user preferences.
 * This matches the sequence diagram participant "PreferencesEntity".
 */
public class Preferences {
    private String language;
    private String currency;
    private boolean notificationEnabled;
    private List<String> dietaryRestrictions;

    /**
     * Constructs a Preferences object with given values.
     * @param language the language
     * @param currency the currency
     * @param notificationEnabled whether notifications are enabled
     * @param dietaryRestrictions the list of dietary restrictions
     */
    public Preferences(String language, String currency, boolean notificationEnabled, List<String> dietaryRestrictions) {
        this.language = language;
        this.currency = currency;
        this.notificationEnabled = notificationEnabled;
        this.dietaryRestrictions = (dietaryRestrictions != null) ? new ArrayList<>(dietaryRestrictions) : new ArrayList<>();
    }

    /**
     * Default constructor.
     */
    public Preferences() {
        this.dietaryRestrictions = new ArrayList<>();
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

    /**
     * Updates this Preferences object from a DTO (mapped from sequence diagram message m33).
     * @param dto the DTO containing new values
     */
    public void updateFromDTO(PreferencesDTO dto) {
        this.language = dto.getLanguage();
        this.currency = dto.getCurrency();
        this.notificationEnabled = dto.isNotificationEnabled();
        this.dietaryRestrictions = dto.getDietaryRestrictions();
    }

    /**
     * Converts this Preferences object to a DTO (mapped from sequence diagram message m17).
     * @return a new PreferencesDTO with current values
     */
    public PreferencesDTO toDTO() {
        return new PreferencesDTO(language, currency, notificationEnabled, dietaryRestrictions);
    }
}