package etour.application.request;

import java.util.Map;

/**
 * Request DTO for the Edit Preferences use case.
 */
public class EditPreferencesRequest {
    private String touristId;
    private Map<String, Object> updatedFields;

    public EditPreferencesRequest() {}

    public EditPreferencesRequest(String touristId, Map<String, Object> updatedFields) {
        this.touristId = touristId;
        this.updatedFields = updatedFields;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public Map<String, Object> getUpdatedFields() {
        return updatedFields;
    }

    public void setUpdatedFields(Map<String, Object> updatedFields) {
        this.updatedFields = updatedFields;
    }
}