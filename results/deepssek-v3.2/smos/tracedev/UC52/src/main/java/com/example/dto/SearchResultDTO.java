package com.example.dto;

/**
 * Data Transfer Object for individual search results.
 * Contains entity details and relevance scoring.
 */
public class SearchResultDTO {
    private Long entityId;
    private String entityType;
    private String displayName;
    private Double relevanceScore;
    private String highlightText;

    // Constructors
    public SearchResultDTO() {
    }

    public SearchResultDTO(Long entityId, String entityType, String displayName, 
                          Double relevanceScore, String highlightText) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.displayName = displayName;
        this.relevanceScore = relevanceScore;
        this.highlightText = highlightText;
    }

    /**
     * Creates a formatted result string for display.
     * @return formatted result string.
     */
    public String getFormattedResult() {
        return String.format("[%s] %s (Score: %.2f) - %s", 
                entityType, displayName, relevanceScore, highlightText);
    }

    // Getters and Setters
    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Double getRelevanceScore() {
        return relevanceScore;
    }

    public void setRelevanceScore(Double relevanceScore) {
        this.relevanceScore = relevanceScore;
    }

    public String getHighlightText() {
        return highlightText;
    }

    public void setHighlightText(String highlightText) {
        this.highlightText = highlightText;
    }
}