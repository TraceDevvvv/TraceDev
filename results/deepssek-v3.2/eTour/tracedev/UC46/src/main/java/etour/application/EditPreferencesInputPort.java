package etour.application;

import etour.application.request.EditPreferencesRequest;
import etour.application.response.EditPreferencesResponse;

/**
 * Input port (interface) for the Edit Preferences use case.
 * Part of the Application layer.
 */
public interface EditPreferencesInputPort {
    EditPreferencesResponse execute(EditPreferencesRequest request);
    EditPreferencesResponse loadPreferences(String touristId);
}