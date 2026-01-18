package com.etour.model;

/**
 * Represents generic personal preferences of a Tourist.
 */
public class UserPreference {
    private String language;
    private String theme;
    private boolean emailNotifications;
    private String currency;
    private int searchRadius;

    public UserPreference() {}

    public UserPreference(String language, String theme, boolean emailNotifications, String currency, int searchRadius) {
        this.language = language;
        this.theme = theme;
        this.emailNotifications = emailNotifications;
        this.currency = currency;
        this.searchRadius = searchRadius;
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

    public boolean isEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(int searchRadius) {
        this.searchRadius = searchRadius;
    }

    @Override
    public String toString() {
        return "UserPreference{" +
                "language='" + language + '\'' +
                ", theme='" + theme + '\'' +
                ", emailNotifications=" + emailNotifications +
                ", currency='" + currency + '\'' +
                ", searchRadius=" + searchRadius +
                '}';
    }
}