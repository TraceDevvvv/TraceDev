package com.agency.modifycomment.service;

import com.agency.modifycomment.model.Feedback;
import com.agency.modifycomment.repository.FeedbackRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing business logic related to Feedback objects.
 * It acts as an intermediary between the controller and the repository,
 * providing methods for feedback-related operations.
 */
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    /**
     * Constructs a new FeedbackService with the given FeedbackRepository.
     *
     * @param feedbackRepository The repository for accessing feedback data.
     */
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Retrieves a list of all available feedbacks.
     *
     * @return A List of Feedback objects.
     */
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    /**
     * Finds a feedback by its unique identifier.
     *
     * @param feedbackId The ID of the feedback to find.
     * @return An Optional containing the Feedback if found, or an empty Optional if not found.
     */
    public Optional<Feedback> getFeedbackById(String feedbackId) {
        return feedbackRepository.findById(feedbackId);
    }

    /**
     * Retrieves a list of feedbacks associated with a specific site ID.
     *
     * @param siteId The ID of the site to retrieve feedbacks for.
     * @return A List of Feedback objects associated with the given site ID.
     */
    public List<Feedback> getFeedbacksBySiteId(String siteId) {
        return feedbackRepository.findBySiteId(siteId);
    }

    /**
     * Adds a new feedback to the system.
     *
     * @param feedback The Feedback object to add.
     * @return The added Feedback object.
     * @throws IllegalArgumentException if the feedback or feedback ID is null.
     */
    public Feedback addFeedback(Feedback feedback) {
        if (feedback == null || feedback.getId() == null) {
            throw new IllegalArgumentException("Feedback and Feedback ID cannot be null.");
        }
        return feedbackRepository.save(feedback);
    }

    /**
     * Updates an existing feedback.
     *
     * @param feedback The Feedback object with updated information.
     * @return The updated Feedback object.
     * @throws IllegalArgumentException if the feedback or feedback ID is null, or if the feedback does not exist.
     */
    public Feedback updateFeedback(Feedback feedback) {
        if (feedback == null || feedback.getId() == null) {
            throw new IllegalArgumentException("Feedback and Feedback ID cannot be null.");
        }
        if (!feedbackRepository.existsById(feedback.getId())) {
            throw new IllegalArgumentException("Feedback with ID " + feedback.getId() + " does not exist.");
        }
        return feedbackRepository.save(feedback);
    }

    /**
     * Deletes a feedback by its ID.
     *
     * @param feedbackId The ID of the feedback to delete.
     * @return true if the feedback was successfully deleted, false otherwise.
     */
    public boolean deleteFeedback(String feedbackId) {
        return feedbackRepository.deleteById(feedbackId);
    }
}