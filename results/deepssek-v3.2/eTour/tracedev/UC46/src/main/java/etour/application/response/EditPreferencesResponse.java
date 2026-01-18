package etour.application.response;

import etour.domain.SearchPreferences;

/**
 * Response DTO for the Edit Preferences use case.
 */
public class EditPreferencesResponse {
    private boolean success;
    private String message;
    private SearchPreferences updatedPreferences;

    public EditPreferencesResponse(boolean success, String message, SearchPreferences updatedPreferences) {
        this.success = success;
        this.message = message;
        this.updatedPreferences = updatedPreferences;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public SearchPreferences getUpdatedPreferences() {
        return updatedPreferences;
    }

    public static EditPreferencesResponse success(String message, SearchPreferences prefs) {
        return new EditPreferencesResponse(true, message, prefs);
    }

    public static EditPreferencesResponse error(String message) {
        return new EditPreferencesResponse(false, message, null);
    }

    public static EditPreferencesResponse cancellation(String message) {
        return new EditPreferencesResponse(false, message, null);
    }
}