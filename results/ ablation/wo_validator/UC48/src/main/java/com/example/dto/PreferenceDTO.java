package com.example.dto;

/**
 * Data Transfer Object for Preference.
 * Used to transfer preference data between layers (e.g., controller and service).
 */
public class PreferenceDTO {
    private String language;
    private String timezone;
    private boolean notificationEnabled;
    private boolean accessibilityMode;

    // Constructors
    public PreferenceDTO() {}

    public PreferenceDTO(String language, String timezone, boolean notificationEnabled, boolean accessibilityMode) {
        this.language = language;
        this.timezone = timezone;
        this.notificationEnabled = notificationEnabled;
        this.accessibilityMode = accessibilityMode;
    }

    // Getters and Setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public boolean isAccessibilityMode() {
        return accessibilityMode;
    }

    public void setAccessibilityMode(boolean accessibilityMode) {
        this.accessibilityMode = accessibilityMode;
    }

    @Override
    public String toString() {
        return "PreferenceDTO{language='" + language + "', timezone='" + timezone +
                "', notificationEnabled=" + notificationEnabled + ", accessibilityMode=" + accessibilityMode + "}";
    }
}