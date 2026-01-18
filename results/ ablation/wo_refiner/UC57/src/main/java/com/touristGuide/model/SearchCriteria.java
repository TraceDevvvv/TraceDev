package com.touristGuide.model;

import java.util.Map;

public class SearchCriteria {
    private Map<String, String> attributes;
    private String eventType;

    public SearchCriteria(Map<String, String> attributes, String eventType) {
        this.attributes = attributes;
        this.eventType = eventType;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String getEventType() {
        return eventType;
    }
}