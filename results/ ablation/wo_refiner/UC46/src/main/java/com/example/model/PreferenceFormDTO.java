package com.example.model;

import java.util.List;

/**
 * Data Transfer Object for the preference form, containing current preferences and available options.
 */
public class PreferenceFormDTO {
    public PreferenceDTO currentPreferences;
    public List<String> availableCategories;
    public List<String> priceRanges;

    public PreferenceFormDTO() {}

    public PreferenceFormDTO(PreferenceDTO currentPreferences, List<String> availableCategories, List<String> priceRanges) {
        this.currentPreferences = currentPreferences;
        this.availableCategories = availableCategories;
        this.priceRanges = priceRanges;
    }

    public PreferenceDTO getCurrentPreferences() {
        return currentPreferences;
    }

    public void setCurrentPreferences(PreferenceDTO currentPreferences) {
        this.currentPreferences = currentPreferences;
    }

    public List<String> getAvailableCategories() {
        return availableCategories;
    }

    public void setAvailableCategories(List<String> availableCategories) {
        this.availableCategories = availableCategories;
    }

    public List<String> getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(List<String> priceRanges) {
        this.priceRanges = priceRanges;
    }
}