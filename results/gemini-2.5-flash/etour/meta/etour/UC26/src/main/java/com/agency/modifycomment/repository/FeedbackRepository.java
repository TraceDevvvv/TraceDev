package com.agency.modifycomment.repository;

import com.agency.modifycomment.model.Feedback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Repository for managing Feedback objects.
 * This class simulates a data access layer for Feedback entities using an in-memory map.
 */
public class FeedbackRepository {
    // Using ConcurrentHashMap to simulate a data store for thread-safe operations
    private final ConcurrentHashMap<String, Feedback> feedbacks = new ConcurrentHashMap<>();

    /**
     * Constructor to pre-populate some dummy data for demonstration purposes.
     */
    public FeedbackRepository() {
        // Pre-populate with some sample feedbacks
        feedbacks.put("fb1", new Feedback("fb1", "site1", "Great Feature", "The new search functionality is amazing!"));
        feedbacks.put("fb2", new Feedback("fb2", "site1", "Bug Report", "Login button is not working on mobile."));
        feedbacks.put("fb3", new Feedback("fb3", "site2", "Suggestion", "Add dark mode to the application."));
        feedbacks.put("fb4", new Feedback("fb4", "site3", "Performance Issue", "Page load times are very slow."));
        feedbacks.put("fb5", new Feedback("fb5", "site1", "UI/UX Feedback", "The new UI is clean and intuitive."));
    }

    /**
     * Finds a feedback by its ID.
     *
     * @param id The ID of the feedback to find.
     * @return An Optional containing the Feedback if found, or an empty Optional if not found.
     */
    public Optional<Feedback> findById(String id) {
        return Optional.ofNullable(feedbacks.get(id));
    }

    /**
     * Returns a list of all feedbacks.
     *
     * @return A List of all Feedback objects currently stored.
     */
    public List<Feedback> findAll() {
        return new ArrayList<>(feedbacks.values());
    }

    /**
     * Finds all feedbacks associated with a specific site ID.
     *
     * @param siteId The ID of the site to retrieve feedbacks for.
     * @return A List of Feedback objects associated with the given site ID.
     */
    public List<Feedback> findBySiteId(String siteId) {
        return feedbacks.values().stream()
                .filter(feedback -> feedback.getSiteId().equals(siteId))
                .collect(Collectors.toList());
    }

    /**
     * Saves a feedback. If a feedback with the same ID already exists, it will be updated.
     * Otherwise, a new feedback will be added.
     *
     * @param feedback The Feedback object to save.
     * @return The saved Feedback object.
     */
    public Feedback save(Feedback feedback) {
        if (feedback == null || feedback.getId() == null) {
            throw new IllegalArgumentException("Feedback and Feedback ID cannot be null.");
        }
        feedbacks.put(feedback.getId(), feedback);
        return feedback;
    }

    /**
     * Deletes a feedback by its ID.
     *
     * @param id The ID of the feedback to delete.
     * @return true if the feedback was deleted, false otherwise.
     */
    public boolean deleteById(String id) {
        return feedbacks.remove(id) != null;
    }

    /**
     * Checks if a feedback with the given ID exists.
     *
     * @param id The ID of the feedback to check.
     * @return true if a feedback with the ID exists, false otherwise.
     */
    public boolean existsById(String id) {
        return feedbacks.containsKey(id);
    }
}