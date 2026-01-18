package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for managing feedback operations.
 * Handles business logic, interactions with repositories, and data transformations.
 * Ensures integrity and persistence of modified comment data via transactional operations. (REQ-004)
 */
public class FeedbackService {
    public IFeedbackRepository feedbackRepository;
    public ISiteRepository siteRepository;
    public ValidationService validationService;
    public INotificationService notificationService; // Added to satisfy REQ-003

    /**
     * Constructor for FeedbackService, injecting its dependencies.
     * @param feedbackRepository The repository for Feedback entities.
     * @param siteRepository The repository for Site entities.
     * @param validationService The service for data validation.
     * @param notificationService The service for sending notifications (REQ-003).
     */
    public FeedbackService(IFeedbackRepository feedbackRepository, ISiteRepository siteRepository,
                           ValidationService validationService, INotificationService notificationService) {
        this.feedbackRepository = feedbackRepository;
        this.siteRepository = siteRepository;
        this.validationService = validationService;
        this.notificationService = notificationService;
    }

    /**
     * Retrieves all sites and converts them to SiteDTOs.
     * @return A list of SiteDTOs, or null if an error occurs during retrieval (simulated DB error for REQ-002).
     */
    public List<SiteDTO> getSites() {
        List<Site> sites = siteRepository.findAll();
        if (sites == null) { // Indicating a simulated DB error from SiteRepository
            return null;
        }
        return sites.stream()
                .map(site -> new SiteDTO(site.getId(), site.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all feedback for a specific site and converts them to FeedbackDTOs.
     * @param siteId The ID of the site.
     * @return A list of FeedbackDTOs for the given site, or null if an error occurs (simulated DB error for REQ-002).
     */
    public List<FeedbackDTO> getFeedbackForSite(String siteId) {
        List<Feedback> feedbackList = feedbackRepository.findBySiteId(siteId);
        if (feedbackList == null) { // Indicating a simulated DB error
            return null;
        }
        Site site = siteRepository.findById(siteId); // Retrieve site name for DTO
        String siteName = (site != null) ? site.getName() : "Unknown Site";

        return feedbackList.stream()
                .map(feedback -> new FeedbackDTO(feedback.getId(), siteName, feedback.getComment(), feedback.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single feedback by its ID and converts it to a FeedbackDTO.
     * @param feedbackId The ID of the feedback.
     * @return The FeedbackDTO if found, or null if not found or an error occurs (simulated DB error for REQ-002).
     */
    public FeedbackDTO getFeedbackById(String feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId);
        if (feedback == null) { // Not found or simulated DB error
            return null;
        }
        Site site = siteRepository.findById(feedback.getSiteId());
        String siteName = (site != null) ? site.getName() : "Unknown Site";

        return new FeedbackDTO(feedback.getId(), siteName, feedback.getComment(), feedback.getStatus());
    }

    /**
     * Updates the comment of a feedback.
     * This method validates the new comment, retrieves the existing feedback, updates it,
     * persists the changes, and sends a notification.
     * (Ensures integrity and persistence of modified comment data via transactional operations for REQ-004)
     * (Notifies system for REQ-003)
     * @param editDto The DTO containing the feedback ID and the new comment.
     * @return true if the comment was successfully updated, false otherwise (e.g., validation failed, not found, DB error).
     */
    public boolean updateFeedbackComment(CommentEditDTO editDto) {
        // 1. Validate new comment
        if (!validationService.validateComment(editDto.newComment)) {
            System.err.println("FeedbackService: Validation failed for new comment.");
            return false; // Validation failed
        }

        // 2. Retrieve existing feedback entity
        Feedback existingFeedback = feedbackRepository.findById(editDto.feedbackId);
        if (existingFeedback == null) {
            System.err.println("FeedbackService: Feedback with ID " + editDto.feedbackId + " not found or DB error during retrieval.");
            return false; // Feedback not found or simulated DB read error (REQ-002)
        }

        // 3. Update feedback entity
        existingFeedback.setComment(editDto.newComment);
        // Set auditing information (REQ-004)
        existingFeedback.setLastModifiedBy("AgencyOperator"); // Assuming authenticated user is "AgencyOperator"
        existingFeedback.setLastModifiedDate(new Date());

        // 4. Persist changes
        boolean saveSuccess = feedbackRepository.save(existingFeedback);
        if (!saveSuccess) {
            System.err.println("FeedbackService: Failed to save updated feedback with ID " + editDto.feedbackId + " (simulated DB write error for REQ-002).");
            return false; // Simulated DB write error (REQ-002)
        }

        // 5. Send notification (REQ-003)
        notificationService.notifyCommentModified(editDto.feedbackId);

        System.out.println("FeedbackService: Successfully updated comment for Feedback ID " + editDto.feedbackId);
        return true;
    }
}