package com.example.model;

import java.util.Date;
import java.util.Map;

/**
 * Represents feedback for a specific location.
 */
public class SiteFeedback {
    private String locationId;
    private Map<String, Object> feedbackData;
    private Date timestamp;

    public SiteFeedback(String locationId, Map<String, Object> feedbackData, Date timestamp) {
        this.locationId = locationId;
        this.feedbackData = feedbackData;
        this.timestamp = timestamp;
    }

    public String getLocationId() {
        return locationId;
    }

    public Map<String, Object> getFeedbackData() {
        return feedbackData;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}