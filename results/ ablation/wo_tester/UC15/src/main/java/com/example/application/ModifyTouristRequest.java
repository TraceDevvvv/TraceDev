package com.example.application;

import java.util.Map;

/**
 * Request DTO for Modify Tourist use case.
 */
public class ModifyTouristRequest {
    private String touristId;
    private Map<String, Object> updatedData;

    public ModifyTouristRequest(String touristId, Map<String, Object> updatedData) {
        this.touristId = touristId;
        this.updatedData = updatedData;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public Map<String, Object> getUpdatedData() {
        return updatedData;
    }

    public void setUpdatedData(Map<String, Object> updatedData) {
        this.updatedData = updatedData;
    }
}