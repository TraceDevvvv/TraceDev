package com.example;

/**
 * Contains details for a Teaching, used for display purposes.
 * As per the class diagram.
 */
public class TeachingDetails {
    private String teachingId;
    private String details;

    public TeachingDetails(String teachingId, String details) {
        this.teachingId = teachingId;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}