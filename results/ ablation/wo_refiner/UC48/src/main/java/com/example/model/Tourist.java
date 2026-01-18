package com.example.model;

import com.example.service.AuthenticationService;
import com.example.controller.PreferenceController;
import com.example.form.PreferenceForm;
import java.util.Map;

/**
 * Represents a Tourist entity that can modify preferences.
 * Includes methods for accessing preference modification functionality.
 */
public class Tourist {
    private String touristId;
    private String name;
    private String sessionId;

    public Tourist(String touristId, String name, String sessionId) {
        this.touristId = touristId;
        this.name = name;
        this.sessionId = sessionId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Retrieves preferences for this tourist.
     * Updated return type to satisfy requirement 1,2.
     */
    public GenericPreference getPreferences() {
        // In a real implementation, this would call a service.
        // For simplicity, returns a mock object.
        return new GenericPreference("pref123", touristId, "English", "Dark", "Enabled");
    }

    /**
     * Updates preferences for this tourist.
     */
    public boolean updatePreferences(GenericPreference preferences) {
        // In a real implementation, this would call a service.
        // For simplicity, returns true.
        return true;
    }

    /**
     * Cancels a preference modification operation.
     * Added to satisfy requirement 13.
     */
    public void cancelModification(String operationId) {
        System.out.println("Tourist " + touristId + " cancels operation " + operationId);
    }

    /**
     * Provides access to preference modification functionality.
     * Added to satisfy requirement 6.
     */
    public void accessPreferenceModification() {
        System.out.println("Tourist " + touristId + " accesses preference modification.");
    }
}