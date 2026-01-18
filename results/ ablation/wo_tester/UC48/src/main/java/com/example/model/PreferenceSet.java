package com.example.model;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents a set of user preferences.
 */
public class PreferenceSet {
    private String language;
    private String theme;
    private boolean notificationsEnabled;
    private Map<String, Object> accessibilityOptions;

    public PreferenceSet() {
        this.accessibilityOptions = new HashMap<>();
    }

    public PreferenceSet(String language, String theme, boolean notificationsEnabled, Map<String, Object> accessibilityOptions) {
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

    // Missing method from class diagram - already present, ensuring it exists
    public boolean validate() {
        // Simple validation: language and theme should not be empty
        return language != null && !language.trim().isEmpty() && theme != null && !theme.trim().isEmpty();
    }
}