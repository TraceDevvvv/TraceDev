package com.example.preferences;

/**
 * Represents a user's preference entity stored in the system.
 * This class maps directly to a persistent storage concept.
 */
public class Preference {
    // Unique identifier for the preference entry. Marked private as it's typically managed by the persistence layer.
    private Long id;
    // Identifier for the tourist associated with these preferences.
    private String touristId;
    // User's chosen theme (e.g., 'dark', 'light').
    private String theme;
    // User's chosen language (e.g., 'en', 'es').
    private String language;
    // User's chosen timezone (e.g., 'GMT+1', 'America/New_York').
    private String timezone;

    /**
     * Constructor for creating a new Preference object without an ID.
     * This is typically used when creating a new preference before it's saved to the database.
     *
     * @param touristId The unique ID of the tourist.
     * @param theme The preferred theme.
     * @param language The preferred language.
     * @param timezone The preferred timezone.
     */
    public Preference(String touristId, String theme, String language, String timezone) {
        this.touristId = touristId;
        this.theme = theme;
        this.language = language;
        this.timezone = timezone;
    }

    /**
     * Full constructor including ID. Used when retrieving an existing preference from the database.
     *
     * @param id The unique identifier of the preference.
     * @param touristId The unique ID of the tourist.
     * @param theme The preferred theme.
     * @param language The preferred language.
     * @param timezone The preferred timezone.
     */
    public Preference(Long id, String touristId, String theme, String language, String timezone) {
        this(touristId, theme, language, timezone);
        this.id = id;
    }

    // --- Getters ---

    /**
     * Gets the unique identifier of the preference.
     * @return The preference ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the preference.
     * This is typically used by the persistence layer when an object is saved.
     * @param id The preference ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the unique tourist ID associated with this preference.
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
        return "Preference{" +
               "id=" + id +
               ", touristId='" + touristId + '\'' +
               ", theme='" + theme + '\'' +
               ", language='" + language + '\'' +
               ", timezone='" + timezone + '\'' +
               '}';
    }
}