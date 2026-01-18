package com.example.application.dto;

/**
 * Data Transfer Object for inserting a tag request.
 */
public class InsertTagRequest {
    private String tagName;
    private String description;

    public InsertTagRequest(String tagName, String description) {
        this.tagName = tagName;
        this.description = description;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}