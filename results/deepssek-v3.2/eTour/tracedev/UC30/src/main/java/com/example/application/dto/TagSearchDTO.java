package com.example.application.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for tag search form data.
 */
public class TagSearchDTO {
    public String tag;
    public String operatorId;
    public LocalDateTime timestamp;

    // Default constructor
    public TagSearchDTO() {
    }

    public TagSearchDTO(String tag, String operatorId, LocalDateTime timestamp) {
        this.tag = tag;
        this.operatorId = operatorId;
        this.timestamp = timestamp;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}