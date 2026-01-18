package com.example.preferences;

/**
 * Data Transfer Object (DTO) for transferring preference data between layers.
 * This class represents the data pertinent to preferences from the perspective of external clients or UI.
 * Fields are private with public getters/setters for proper encapsulation, despite the diagram's
 * `+ field : Type` notation which might suggest public fields. This interpretation aligns with Java bean conventions.
 */
public class PreferenceDto {
    private String touristId;
    private String theme;
    private String language;
    private String timezone;

    /**
     * Constructor to create a PreferenceDto.
     *
     * @param touristId The unique ID of the tourist.
     * @param theme The preferred theme.
     * @param language The preferred language.
     * @param timezone The preferred timezone.
     */
    public PreferenceDto(String touristId, String theme, String language, String timezone) {
        this.touristId = touristId;
        this.theme = theme;
        this.language = language;
        this.timezone = timezone;
    }

    // --- Getters ---

    /**
     * Gets the tourist ID.
     * @return The tourist ID.
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Gets the preferred theme.
     * @return The theme string.
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Gets the preferred language.
     * @return The language string.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Gets the preferred timezone.
     * @return The timezone string.
     */
    public String getTimezone() {
        return timezone;
    }

    // --- Setters ---

    /**
     * Sets the tourist ID.
     * @param touristId The new tourist ID.
     */
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    /**
     * Sets the preferred theme.
     * @param theme The new theme string.
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Sets the preferred language.
     * @param language The new language string.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Sets the preferred timezone.
     * @param timezone The new timezone string.
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "PreferenceDto{" +
               "touristId='" + touristId + '\'' +
               ", theme='" + theme + '\'' +
               ", language='" + language + '\'' +
               ", timezone='" + timezone + '\'' +
               '}';
    }
}