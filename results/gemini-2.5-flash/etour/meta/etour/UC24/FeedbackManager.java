package ViewFeedback_1765890483;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a collection of Feedback objects.
 * Provides functionalities to add new feedback and retrieve feedback for a specific site.
 */
public class FeedbackManager {
    private final List<Feedback> feedbackEntries;

    /**
     * Constructs a new FeedbackManager.
     * Initializes an empty list to store feedback entries.
     */
    public FeedbackManager() {
        this.feedbackEntries = new ArrayList<>();
    }

    /**
     * Adds a new feedback entry to the manager.
     *
     * @param feedback The Feedback object to be added.
     */
    public void addFeedback(Feedback feedback) {
        if (feedback != null) {
            this.feedbackEntries.add(feedback);
        }
    }

    /**
     * Retrieves all feedback entries for a given site ID.
     *
     * @param siteId The ID of the site for which to retrieve feedback.
     * @return A List of Feedback objects associated with the given site ID.
     *         Returns an empty list if no feedback is found for the site.
     */
    public List<Feedback> getFeedbackBySiteId(String siteId) {
        if (siteId == null || siteId.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return feedbackEntries.stream()
                .filter(feedback -> feedback.getSiteId().equals(siteId))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Returns an unmodifiable list of all feedback entries currently managed.
     *
     * @return A List of all Feedback objects.
     */
    public List<Feedback> getAllFeedback() {
        return Collections.unmodifiableList(feedbackEntries);
    }
}