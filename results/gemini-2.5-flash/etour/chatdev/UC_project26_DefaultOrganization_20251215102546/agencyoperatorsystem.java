'''
Simulates the backend system for an Agency Operator.
Manages site and feedback data, and provides methods for interacting with them.
This class handles data persistence in memory for this example.
'''
package com.chatdev.system;
import com.chatdev.models.Site;
import com.chatdev.models.Feedback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class AgencyOperatorSystem {
    // Stores sites by their ID
    private Map<Integer, Site> sites;
    // Stores feedback, mapping site ID to a list of feedback for that site
    private Map<Integer, List<Feedback>> feedbackMap;
    // Stores all feedback by their unique ID for efficient lookup (O(1) average time complexity)
    private Map<Integer, Feedback> allFeedbackById;
    // Counter for generating unique feedback IDs
    private int nextFeedbackId = 1;
    // Flag to simulate connection issues
    public static boolean simulateConnectionError = false;
    /**
     * Constructs an AgencyOperatorSystem and initializes it with some dummy data.
     */
    public AgencyOperatorSystem() {
        sites = new HashMap<>();
        feedbackMap = new HashMap<>();
        allFeedbackById = new HashMap<>(); // Initialize the new map
        initData(); // Populate with initial data
    }
    /**
     * Initializes the system with dummy sites and feedback data.
     * This simulates data retrieval from a database or external service.
     */
    private void initData() {
        // Add dummy sites
        Site site1 = new Site(1, "Eiffel Tower Tour");
        Site site2 = new Site(2, "Rome Colosseum Visit");
        Site site3 = new Site(3, "Tokyo Skytree Experience");
        sites.put(site1.getId(), site1);
        sites.put(site2.getId(), site2);
        sites.put(site3.getId(), site3);
        // Add dummy feedback for Site 1
        addFeedbackInternal(site1.getId(), "Great tour, highly recommend!");
        addFeedbackInternal(site1.getId(), "A bit crowded, but views were amazing.");
        addFeedbackInternal(site1.getId(), "Guide was very knowledgeable.");
        // Add dummy feedback for Site 2
        addFeedbackInternal(site2.getId(), "Historical and beautiful. Must-see!");
        addFeedbackInternal(site2.getId(), "Long queues, suggest booking in advance.");
        addFeedbackInternal(site2.getId(), "Could use more interactive exhibits. Still awe-inspiring.");
        // Add dummy feedback for Site 3
        addFeedbackInternal(site3.getId(), "Stunning panoramic views of Tokyo.");
        addFeedbackInternal(site3.getId(), "Cafe at the top was a nice touch.");
    }
    /**
     * Helper method to add feedback internally and manage unique IDs.
     * @param siteId The ID of the site to which feedback belongs.
     * @param comment The comment text.
     */
    private void addFeedbackInternal(int siteId, String comment) {
        Feedback feedback = new Feedback(nextFeedbackId++, siteId, comment);
        feedbackMap.computeIfAbsent(siteId, k -> new ArrayList<>()).add(feedback);
        allFeedbackById.put(feedback.getId(), feedback); // Add to the new map for direct lookup
    }
    /**
     * Checks if a simulated connection error should occur and throws an exception if so.
     * This method is called at the beginning of methods that would interact with a "server".
     */
    private void checkConnection() {
        if (simulateConnectionError) {
            throw new ConnectionFailedException("Simulated connection to server interrupted.");
        }
    }
    /**
     * Retrieves a list of all available sites.
     * @return A list of Site objects.
     */
    public List<Site> getSites() {
        checkConnection(); // Call at the beginning of the method
        // Return a new ArrayList to prevent external modifications to the internal map
        return new ArrayList<>(sites.values());
    }
    /**
     * Retrieves all feedback associated with a specific site.
     * @param siteId The ID of the site.
     * @return A list of Feedback objects for the given site, or an empty list if none exist.
     */
    public List<Feedback> getFeedbackForSite(int siteId) {
        checkConnection(); // Call at the beginning of the method
        return feedbackMap.getOrDefault(siteId, new ArrayList<>());
    }
    /**
     * Finds a specific feedback by its unique ID.
     * @param feedbackId The unique ID of the feedback.
     * @return The Feedback object if found, otherwise null.
     */
    public Feedback getFeedbackById(int feedbackId) {
        // Now directly retrieve from the map for O(1) lookup
        return allFeedbackById.get(feedbackId);
    }
    /**
     * Validates a provided comment string.
     * For this example, a comment is considered valid if it's not null and not empty after trimming.
     * @param comment The comment string to validate.
     * @return true if the comment is valid, false otherwise.
     */
    public boolean validateComment(String comment) {
        return comment != null && !comment.trim().isEmpty();
    }
    /**
     * Updates the comment for a specific feedback entry.
     * @param feedbackId The ID of the feedback to update.
     * @param newComment The new comment text.
     * @return true if the update was successful, false if the feedback was not found.
     */
    public boolean updateFeedbackComment(int feedbackId, String newComment) {
        checkConnection(); // Call at the beginning of the method
        Feedback feedbackToUpdate = getFeedbackById(feedbackId);
        if (feedbackToUpdate != null) {
            feedbackToUpdate.setComment(newComment);
            System.out.println("DEBUG: Feedback ID " + feedbackId + " comment updated to: " + newComment);
            return true;
        }
        return false;
    }
}