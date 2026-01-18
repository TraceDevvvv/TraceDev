package com.duplicatefeedback.application.strategy;

/**
 * Strategy interface for duplicate feedback detection.
 * Part of the application business rules (Use Case Layer).
 */
public interface DuplicateDetectionStrategy {
    /**
     * Checks if the given feedback is a duplicate.
     * @param feedback the feedback to check
     * @return true if duplicate exists, false otherwise
     */
    boolean isDuplicate(com.duplicatefeedback.domain.model.Feedback feedback);

    /**
     * Abstract method to check for duplicate by site and user.
     * @param siteId the site identifier
     * @param userId the user identifier
     * @return true if duplicate exists, false otherwise
     */
    boolean checkForDuplicate(String siteId, String userId);
}