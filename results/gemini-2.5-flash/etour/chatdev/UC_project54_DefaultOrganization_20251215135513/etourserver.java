/**
 * This class simulates interaction with a backend server (ETOUR).
 * It holds in-memory data for Sites and Feedback and provides methods
 * to retrieve and update this data. It's designed as a Singleton.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class ETourServer {
    private static ETourServer instance;
    private final Map<Integer, Site> sites = new HashMap<>();
    private final Map<Integer, Feedback> feedbacks = new HashMap<>();
    private int nextFeedbackId = 1; // Simple ID generator
    /**
     * Private constructor to enforce Singleton pattern.
     */
    private ETourServer() {
        // Private constructor
    }
    /**
     * Returns the singleton instance of ETourServer.
     * @return The single instance of ETourServer.
     */
    public static synchronized ETourServer getInstance() {
        if (instance == null) {
            instance = new ETourServer();
        }
        return instance;
    }
    /**
     * Initializes the server with some dummy data for demonstration.
     */
    public void initializeData() {
        // Sample Sites
        Site site1 = new Site(1, "Eiffel Tower", "Iconic wrought-iron lattice tower on the Champ de Mars in Paris.");
        Site site2 = new Site(2, "Colosseum", "An oval amphitheatre in the centre of the city of Rome, Italy.");
        sites.put(site1.getId(), site1);
        sites.put(site2.getId(), site2);
        // Sample Feedback for Site 1
        addFeedback(new Feedback(nextFeedbackId++, 1, "Alice", 5, "Absolutely breathtaking! A must-visit."));
        addFeedback(new Feedback(nextFeedbackId++, 1, "Bob", 4, "Crowded, but the view from the top was amazing."));
        addFeedback(new Feedback(nextFeedbackId++, 1, "Charlie", 3, "It was okay, a bit underwhelming compared to pictures."));
        // Sample Feedback for Site 2
        addFeedback(new Feedback(nextFeedbackId++, 2, "Diana", 5, "Steeped in history, truly magnificent."));
        addFeedback(new Feedback(nextFeedbackId++, 2, "Eve", 4, "Fascinating, but long queues. Buy tickets in advance!"));
    }
    /**
     * Adds a feedback to the in-memory store.
     * @param feedback The feedback object to add.
     */
    private void addFeedback(Feedback feedback) {
        feedbacks.put(feedback.getId(), feedback);
    }
    /**
     * Retrieves a Site by its ID.
     * @param siteId The ID of the site.
     * @return An Optional containing the Site if found, or empty otherwise.
     */
    public Optional<Site> getSiteById(int siteId) {
        return Optional.ofNullable(sites.get(siteId));
    }
    /**
     * Retrieves feedback entries for a specific site.
     * @param siteId The ID of the site.
     * @return A list of Feedback objects for the given site.
     */
    public List<Feedback> getFeedbackForSite(int siteId) {
        List<Feedback> siteFeedbacks = new ArrayList<>();
        for (Feedback feedback : feedbacks.values()) {
            if (feedback.getSiteId() == siteId) {
                siteFeedbacks.add(feedback);
            }
        }
        return siteFeedbacks;
    }
    /**
     * Retrieves a Feedback entry by its ID.
     * @param feedbackId The ID of the feedback.
     * @return An Optional containing the Feedback if found, or empty otherwise.
     */
    public Optional<Feedback> getFeedbackById(int feedbackId) {
        return Optional.ofNullable(feedbacks.get(feedbackId));
    }
    /**
     * Updates the comment of a specific feedback entry.
     * @param feedbackId The ID of the feedback to update.
     * @param newComment The new comment string.
     * @return true if the comment was successfully updated, false if feedback not found.
     * @throws RuntimeException if a simulated network error occurs (for demonstration).
     */
    public boolean updateComment(int feedbackId, String newComment) throws RuntimeException {
        // Simulate a delay for network operation
        try {
            Thread.sleep(500); // Simulate 0.5 second network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Feedback feedback = feedbacks.get(feedbackId);
        if (feedback != null) {
            // Create a new Feedback object with the updated comment
            // or modify the existing one. For simplicity, we'll modify existing.
            feedback.setComment(newComment);
            // In a real application, this would interact with a database
            System.out.println("LOG: Feedback " + feedbackId + " comment updated to: " + newComment);
            return true;
        }
        return false;
    }
}