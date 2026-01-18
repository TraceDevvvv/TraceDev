package com.example.dto;

import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object for PreferenceSet.
 */
public class PreferenceSetDTO {
    private String language;
    private String theme;
    private boolean notificationsEnabled;
    private Map<String, Object> accessibilityOptions;

    public PreferenceSetDTO() {
        this.accessibilityOptions = new HashMap<>();
    }

    public PreferenceSetDTO(String language, String theme, boolean notificationsEnabled, Map<String, Object> accessibilityOptions) {
        this.language = language;
        this.theme = theme;
        this.notificationsEnabled = notificationsEnabled;
        this.accessibilityOptions = accessibilityOptions != null ? new HashMap<>(accessibilityOptions) : new HashMap<>();
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

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public Map<String, Object> getAccessibilityOptions() {
        return new HashMap<>(accessibilityOptions);
    }

    public void setAccessibilityOptions(Map<String, Object> accessibilityOptions) {
        this.accessibilityOptions = accessibilityOptions != null ? new HashMap<>(accessibilityOptions) : new HashMap<>();
    }
}