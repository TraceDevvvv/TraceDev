package com.example.dto;

/**
 * Data Transfer Object for feedback, used to send data to the UI layer.
 */
public class FeedbackDTO {
    private String siteId;
    private String content;
    private int rating;
    private String formattedTimestamp;

    public FeedbackDTO(String siteId, String content, int rating, String formattedTimestamp) {
        this.siteId = siteId;
        this.content = content;
        this.rating = rating;
        this.formattedTimestamp = formattedTimestamp;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public String getFormattedTimestamp() {
        return formattedTimestamp;
    }
}