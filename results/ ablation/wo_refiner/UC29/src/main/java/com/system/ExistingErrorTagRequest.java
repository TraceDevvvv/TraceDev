package com.system;

/**
 * ExistingErrorTagRequest class representing a request with a tag.
 * Attribute: tag.
 * Method: getTag.
 */
public class ExistingErrorTagRequest {
    private String tag;

    public ExistingErrorTagRequest(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}