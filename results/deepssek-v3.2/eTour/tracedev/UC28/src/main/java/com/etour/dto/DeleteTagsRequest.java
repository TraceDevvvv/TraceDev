package com.etour.dto;

import com.etour.agency.AgencyOperator;
import java.util.List;

/**
 * Request object for deleting tags.
 * Contains actor ID and list of tag IDs to delete.
 */
public class DeleteTagsRequest {
    private String actorId;
    private List<String> tagIds;

    public DeleteTagsRequest(String actorId, List<String> tagIds) {
        this.actorId = actorId;
        this.tagIds = tagIds;
    }

    public String getActorId() {
        return actorId;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    /**
     * Validates the request fields.
     * Returns a ValidationResult with any errors.
     */
    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();
        if (actorId == null || actorId.trim().isEmpty()) {
            result.addError("Actor ID is required");
        }
        if (tagIds == null || tagIds.isEmpty()) {
            result.addError("At least one tag ID must be provided");
        }
        return result;
    }
}