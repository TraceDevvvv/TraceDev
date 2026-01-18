package etour.interfaceadapters;

import etour.application.EditPreferencesInputPort;
import etour.application.request.EditPreferencesRequest;
import etour.application.response.EditPreferencesResponse;
import etour.domain.SearchPreferences;

/**
 * ViewModel (acting as Controller) that mediates between Form and Use Case.
 */
public class PreferencesViewModel {
    private EditPreferencesInputPort useCase;
    private SearchPreferences _preferences;

    public PreferencesViewModel(EditPreferencesInputPort useCase) {
        this.useCase = useCase;
    }

    /**
     * Load preferences for a tourist.
     * @param touristId the tourist's identifier
     */
    public void loadPreferences(String touristId) {
        EditPreferencesResponse response = useCase.loadPreferences(touristId);
        if (response.isSuccess()) {
            _preferences = response.getUpdatedPreferences();
        } else {
            throw new RuntimeException("Load failed: " + response.getMessage());
        }
    }

    /**
     * Update a single field in the local preferences.
     * @param key field name
     * @param value new value
     */
    public void updateField(String key, Object value) {
        if (_preferences != null) {
            // Simulate update; in a full implementation we would apply delta.
            // For simplicity, we just note the change.
            System.out.println("ViewModel: field updated: " + key + " = " + value);
        }
    }

    /**
     * Submit all pending changes to the use case.
     */
    public void submitChanges() {
        if (_preferences == null) {
            throw new IllegalStateException("Preferences not loaded");
        }
        // In a real scenario we would track changed fields.
        // Here we just pass an empty map as a placeholder.
        EditPreferencesRequest request = new EditPreferencesRequest(_preferences.getTouristId(), null);
        EditPreferencesResponse response = useCase.execute(request);
        if (!response.isSuccess()) {
            throw new RuntimeException("Submit failed: " + response.getMessage());
        }
        _preferences = response.getUpdatedPreferences();
    }

    /**
     * Cancel the current operation.
     */
    public void cancel() {
        // Clean up any transient state if needed.
        System.out.println("ViewModel: operation cancelled.");
    }

    /**
     * Receive error response from UseCase - corresponds to sequence diagram messages m15, m19
     * @param errorMessage the error message
     */
    public void receiveErrorResponse(String errorMessage) {
        System.out.println("ViewModel received error response: " + errorMessage);
    }

    /**
     * Receive cancellation response from UseCase - corresponds to sequence diagram messages m34, m42
     * @param message the cancellation message
     */
    public void receiveCancellationResponse(String message) {
        System.out.println("ViewModel received cancellation response: " + message);
    }

    /**
     * Receive success response from UseCase - corresponds to sequence diagram message m50
     * @param message the success message
     */
    public void receiveSuccessResponse(String message) {
        System.out.println("ViewModel received success response: " + message);
    }

    public SearchPreferences getPreferences() {
        return _preferences;
    }

    public void setPreferences(SearchPreferences prefs) {
        this._preferences = prefs;
    }
}