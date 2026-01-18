
package com.etour.usecases;

import com.etour.agency.AgencyOperator;
import com.etour.tags.TagRepository;
import com.etour.security.Permission;
import com.etour.monitoring.PerformanceMonitor;
import java.util.List;
import java.util.ArrayList;

/**
 * Use case for deleting tags.
 * Orchestrates validation, authorization, and deletion.
 * Implements quality requirement: completion within 5 seconds.
 */
public class DeleteTagsUseCase {
    private TagRepository tagRepository;
    private PerformanceMonitor performanceMonitor;

    public DeleteTagsUseCase(TagRepository tagRepository, PerformanceMonitor performanceMonitor) {
        this.tagRepository = tagRepository;
        this.performanceMonitor = performanceMonitor;
    }

    /**
     * Main execution method for deleting tags.
     * Follows the sequence diagram steps.
     */
    public DeleteTagsResult execute(AgencyOperator actor, List<String> tagIds) {
        long startTime = System.currentTimeMillis();

        // Validate request (authentication, authorization, input)
        ValidationResult validation = validateRequest(actor, tagIds);
        if (!validation.isValid()) {
            return new DeleteTagsResult(false, "Validation failed: " + String.join(", ", validation.getErrors()), 0);
        }

        // Perform deletion
        try {
            deleteTags(tagIds);
        } catch (Exception e) {
            return new DeleteTagsResult(false, "Deletion failed: " + e.getMessage(), 0);
        }

        // Check timeout requirement (5 seconds)
        long duration = System.currentTimeMillis() - startTime;
        if (performanceMonitor.checkTimeout(duration)) {
            return new DeleteTagsResult(false, "Operation timed out (exceeded 5 seconds)", 0);
        }

        return new DeleteTagsResult(true, "Tags deleted successfully", tagIds.size());
    }

    /**
     * Validates the actor and tag IDs.
     * Implements entry conditions: actor must be authenticated and authorized.
     */
    private ValidationResult validateRequest(AgencyOperator actor, List<String> tagIds) {
        ValidationResult result = new ValidationResult();

        if (!actor.isAuthenticated()) {
            result.addError("Actor is not authenticated");
        }
        if (!actor.isAuthorized(Permission.MANAGE_TAGS)) {
            result.addError("Actor is not authorized to manage tags");
        }
        if (tagIds == null || tagIds.isEmpty()) {
            result.addError("No tag IDs provided");
        }

        return result;
    }

    /**
     * Delegates deletion to the repository.
     */
    private void deleteTags(List<String> tagIds) {
        tagRepository.deleteAllById(tagIds);
    }

    /**
     * Result class for delete tags operation
     */
    public static class DeleteTagsResult {
        private final boolean success;
        private final String message;
        private final int deletedCount;

        public DeleteTagsResult(boolean success, String message, int deletedCount) {
            this.success = success;
            this.message = message;
            this.deletedCount = deletedCount;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public int getDeletedCount() {
            return deletedCount;
        }
    }

    /**
     * Validation result class
     */
    private static class ValidationResult {
        private final List<String> errors;

        public ValidationResult() {
            this.errors = new ArrayList<>();
        }

        public void addError(String error) {
            errors.add(error);
        }

        public boolean isValid() {
            return errors.isEmpty();
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}
