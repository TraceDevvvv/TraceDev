package com.example.model;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents generic preferences for a tourist.
 */
public class GenericPreference {
    private String preferenceId;
    private String touristId;
    private String language;
    private String theme;
    private String notificationSettings;

    public GenericPreference(String preferenceId, String touristId, String language, String theme, String notificationSettings) {
        this.preferenceId = preferenceId;
        this.touristId = touristId;
        this.language = language;
        this.theme = theme;
        this.notificationSettings = notificationSettings;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getNotificationSettings() {
        return notificationSettings;
    }

    public void setNotificationSettings(String notificationSettings) {
        this.notificationSettings = notificationSettings;
    }

    /**
     * Returns preference details as a map.
     */
    public Map<String, String> getDetails() {
        Map<String, String> details = new HashMap<>();
        details.put("preferenceId", preferenceId);
        details.put("touristId", touristId);
        details.put("language", language);
        details.put("theme", theme);
        details.put("notificationSettings", notificationSettings);
        return details;
    }

    /**
     * Updates preference details from a map.
     */
    public void updateDetails(Map<String, String> details) {
        if (details.containsKey("language")) {
            this.language = details.get("language");
        }
        if (details.containsKey("theme")) {
            this.theme = details.get("theme");
        }
        if (details.containsKey("notificationSettings")) {
            this.notificationSettings = details.get("notificationSettings");
        }
    }
}