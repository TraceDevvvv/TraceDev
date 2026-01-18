package com.example.model;

import java.util.Objects;

/**
 * Represents user preferences for a Tourist.
 * Includes language, timezone, notification settings, and accessibility mode.
 */
public class Preference {
    private String id;
    private String language;
    private String timezone;
    private boolean notificationEnabled;
    private boolean accessibilityMode;

    // Constructors
    public Preference() {}

    public Preference(String id, String language, String timezone, boolean notificationEnabled, boolean accessibilityMode) {
        this.id = id;
        this.language = language;
        this.timezone = timezone;
        this.notificationEnabled = notificationEnabled;
        this.accessibilityMode = accessibilityMode;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preference that = (Preference) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Preference{id='" + id + "', language='" + language + "', timezone='" + timezone +
                "', notificationEnabled=" + notificationEnabled + ", accessibilityMode=" + accessibilityMode + "}";
    }
}