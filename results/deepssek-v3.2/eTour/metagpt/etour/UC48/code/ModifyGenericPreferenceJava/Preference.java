package com.etour.preference;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents generic personal preferences for a tourist in the ETOUR system.
 * This class contains various personalization settings that can be modified by the user.
 */
public class Preference implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Core preference fields
    private String language;
    private String currency;
    private String temperatureUnit;
    private boolean emailNotifications;
    private boolean smsNotifications;
    private boolean pushNotifications;
    private String theme;
    private int resultsPerPage;
    private boolean accessibilityMode;
    private Set<String> favoriteCategories;
    
    /**
     * Default constructor with default values.
     */
    public Preference() {
        this.language = "English";
        this.currency = "USD";
        this.temperatureUnit = "Celsius";
        this.emailNotifications = true;
        this.smsNotifications = false;
        this.pushNotifications = true;
        this.theme = "Light";
        this.resultsPerPage = 10;
        this.accessibilityMode = false;
        this.favoriteCategories = new HashSet<>();
        // Add some default favorite categories
        this.favoriteCategories.add("Adventure");
        this.favoriteCategories.add("Cultural");
    }
    
    /**
     * Copy constructor to create a deep copy of preferences.
     * @param other the preference to copy
     */
    public Preference(Preference other) {
        this.language = other.language;
        this.currency = other.currency;
        this.temperatureUnit = other.temperatureUnit;
        this.emailNotifications = other.emailNotifications;
        this.smsNotifications = other.smsNotifications;
        this.pushNotifications = other.pushNotifications;
        this.theme = other.theme;
        this.resultsPerPage = other.resultsPerPage;
        this.accessibilityMode = other.accessibilityMode;
        this.favoriteCategories = new HashSet<>(other.favoriteCategories);
    }
    
    // Getters and setters for all fields
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        if (language != null && !language.trim().isEmpty()) {
            this.language = language.trim();
        }
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        if (currency != null && !currency.trim().isEmpty()) {
            this.currency = currency.trim();
        }
    }
    
    public String getTemperatureUnit() {
        return temperatureUnit;
    }
    
    public void setTemperatureUnit(String temperatureUnit) {
        if (temperatureUnit != null && !temperatureUnit.trim().isEmpty()) {
            this.temperatureUnit = temperatureUnit.trim();
        }
    }
    
    public boolean isEmailNotifications() {
        return emailNotifications;
    }
    
    public void setEmailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }
    
    public boolean isSmsNotifications() {
        return smsNotifications;
    }
    
    public void setSmsNotifications(boolean smsNotifications) {
        this.smsNotifications = smsNotifications;
    }
    
    public boolean isPushNotifications() {
        return pushNotifications;
    }
    
    public void setPushNotifications(boolean pushNotifications) {
        this.pushNotifications = pushNotifications;
    }
    
    public String getTheme() {
        return theme;
    }
    
    public void setTheme(String theme) {
        if (theme != null && !theme.trim().isEmpty()) {
            this.theme = theme.trim();
        }
    }
    
    public int getResultsPerPage() {
        return resultsPerPage;
    }
    
    public void setResultsPerPage(int resultsPerPage) {
        if (resultsPerPage >= 5 && resultsPerPage <= 100) {
            this.resultsPerPage = resultsPerPage;
        }
    }
    
    public boolean isAccessibilityMode() {
        return accessibilityMode;
    }
    
    public void setAccessibilityMode(boolean accessibilityMode) {
        this.accessibilityMode = accessibilityMode;
    }
    
    public Set<String> getFavoriteCategories() {
        return new HashSet<>(favoriteCategories);
    }
    
    public void setFavoriteCategories(Set<String> favoriteCategories) {
        if (favoriteCategories != null) {
            this.favoriteCategories = new HashSet<>(favoriteCategories);
        }
    }
    
    /**
     * Adds a favorite category.
     * @param category the category to add
     * @return true if the category was added, false if it already exists
     */
    public boolean addFavoriteCategory(String category) {
        if (category != null && !category.trim().isEmpty()) {
            return favoriteCategories.add(category.trim());
        }
        return false;
    }
    
    /**
     * Removes a favorite category.
     * @param category the category to remove
     * @return true if the category was removed, false if it didn't exist
     */
    public boolean removeFavoriteCategory(String category) {
        if (category != null) {
            return favoriteCategories.remove(category.trim());
        }
        return false;
    }
    
    /**
     * Checks if the preference has any changes compared to another preference.
     * @param other the preference to compare with
     * @return true if there are differences, false if they are identical
     */
    public boolean hasChanges(Preference other) {
        if (other == null) return true;
        
        return !Objects.equals(language, other.language) ||
               !Objects.equals(currency, other.currency) ||
               !Objects.equals(temperatureUnit, other.temperatureUnit) ||
               emailNotifications != other.emailNotifications ||
               smsNotifications != other.smsNotifications ||
               pushNotifications != other.pushNotifications ||
               !Objects.equals(theme, other.theme) ||
               resultsPerPage != other.resultsPerPage ||
               accessibilityMode != other.accessibilityMode ||
               !Objects.equals(favoriteCategories, other.favoriteCategories);
    }
    
    /**
     * Returns a string representation of the preference for display.
     * @return formatted string with all preference settings
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Personal Preferences ===\n");
        sb.append("Language: ").append(language).append("\n");
        sb.append("Currency: ").append(currency).append("\n");
        sb.append("Temperature Unit: ").append(temperatureUnit).append("\n");
        sb.append("Theme: ").append(theme).append("\n");
        sb.append("Results per page: ").append(resultsPerPage).append("\n");
        sb.append("Accessibility Mode: ").append(accessibilityMode ? "Enabled" : "Disabled").append("\n");
        sb.append("\n=== Notification Settings ===\n");
        sb.append("Email Notifications: ").append(emailNotifications ? "Enabled" : "Disabled").append("\n");
        sb.append("SMS Notifications: ").append(smsNotifications ? "Enabled" : "Disabled").append("\n");
        sb.append("Push Notifications: ").append(pushNotifications ? "Enabled" : "Disabled").append("\n");
        sb.append("\n=== Favorite Categories ===\n");
        if (favoriteCategories.isEmpty()) {
            sb.append("No favorite categories set\n");
        } else {
            for (String category : favoriteCategories) {
                sb.append("- ").append(category).append("\n");
            }
        }
        return sb.toString();
    }
    
    /**
     * Compares this preference with another object for equality.
     * Two preferences are equal if all their fields are equal.
     * @param obj the object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Preference other = (Preference) obj;
        return emailNotifications == other.emailNotifications &&
               smsNotifications == other.smsNotifications &&
               pushNotifications == other.pushNotifications &&
               resultsPerPage == other.resultsPerPage &&
               accessibilityMode == other.accessibilityMode &&
               Objects.equals(language, other.language) &&
               Objects.equals(currency, other.currency) &&
               Objects.equals(temperatureUnit, other.temperatureUnit) &&
               Objects.equals(theme, other.theme) &&
               Objects.equals(favoriteCategories, other.favoriteCategories);
    }
    
    /**
     * Returns a hash code based on all preference fields.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(language, currency, temperatureUnit, emailNotifications, 
                           smsNotifications, pushNotifications, theme, resultsPerPage, 
                           accessibilityMode, favoriteCategories);
    }
}