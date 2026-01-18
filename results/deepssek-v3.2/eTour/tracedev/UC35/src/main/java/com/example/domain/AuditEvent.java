package com.example.domain;

/**
 * Represents an audit event.
 */
public class AuditEvent {
    private String eventType;
    private String details;

    public AuditEvent(String eventType, String details) {
        this.eventType = eventType;
        this.details = details;
    }

    public String getEventType() {
        return eventType;
    }

    public String getDetails() {
        return details;
    }
}