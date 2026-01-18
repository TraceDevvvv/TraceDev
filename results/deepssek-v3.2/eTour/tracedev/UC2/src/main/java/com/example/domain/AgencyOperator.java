package com.example.domain;

import com.example.presentation.WebUI;
import com.example.presentation.LocationData;

/**
 * Represents the Agency Operator actor.
 * Corresponds to class diagram class "AgencyOperator".
 */
public class AgencyOperator {
    private String username;
    
    public AgencyOperator(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    /**
     * Simulates the operator activating the insert feature.
     * Sequence message m1.
     * @param webUI the web UI
     */
    public void activateInsertFeature(WebUI webUI) {
        webUI.activateInsertNewCulturalGoodFeature();
    }
    
    /**
     * Simulates the operator filling out the form.
     * Sequence message m5.
     * @param webUI the web UI
     * @param name the name
     * @param description the description
     * @param type the type
     * @param locationData the location data
     * @return the FormData
     */
    public com.example.presentation.FormData fillOutForm(WebUI webUI, String name, String description, CulturalGoodType type, LocationData locationData) {
        return webUI.fillForm(name, description, type, locationData);
    }
    
    /**
     * Simulates the operator submitting the form.
     * Sequence message m6.
     * @param webUI the web UI
     * @param formData the form data
     */
    public void submitForm(WebUI webUI, com.example.presentation.FormData formData) {
        webUI.submitForm(formData);
    }
}