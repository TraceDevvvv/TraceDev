package com.example.preferences;

/**
 * View Model for displaying preference data to the user interface.
 * This class holds only the data necessary for rendering a view.
 * Fields are private with public getters, as view models are typically read-only from the view's perspective.
 * This interpretation aligns with Java bean conventions, similar to PreferenceDto.
 */
public class PreferencesViewModel {
    private String touristId;
    private String theme;
    private String language;
    private String timezone;

    /**
     * Constructor to create a PreferencesViewModel.
     *
     * @param touristId The unique ID of the tourist.
     * @param theme The preferred theme.
     * @param language The preferred language.
     * @param timezone The preferred timezone.
     */
    public PreferencesViewModel(String touristId, String theme, String language, String timezone) {
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

    @Override
    public String toString() {
        return "PreferencesViewModel{" +
               "touristId='" + touristId + '\'' +
               ", theme='" + theme + '\'' +
               ", language='" + language + '\'' +
               ", timezone='" + timezone + '\'' +
               '}';
    }
}