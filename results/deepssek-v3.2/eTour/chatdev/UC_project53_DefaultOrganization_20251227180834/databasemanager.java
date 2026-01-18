/**
 * Manages in-memory storage for feedbacks and visited sites.
 * Simulates database operations and handles edge cases with thread safety.
 * Provides methods for feedback management and visited sites tracking.
 */
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
public class DatabaseManager {
    private List<Feedback> feedbacks;
    private Map<Integer, Set<Integer>> visitedSites; // touristId -> set of siteIds
    public DatabaseManager() {
        // Use thread-safe collections for concurrent access simulation
        feedbacks = new CopyOnWriteArrayList<>();
        visitedSites = new ConcurrentHashMap<>();
        // Initialize with some sample data for testing
        feedbacks.add(new Feedback(1, 101, 2, 4, "Great experience at the Louvre Museum. The artwork was amazing!"));
        feedbacks.add(new Feedback(2, 102, 1, 5, "The Eiffel Tower was absolutely breathtaking at night. Highly recommend visiting during sunset."));
        // Initialize visited sites
        Set<Integer> tourist101Sites = new HashSet<>(Arrays.asList(2));
        Set<Integer> tourist102Sites = new HashSet<>(Arrays.asList(1, 3));
        visitedSites.put(101, new HashSet<>(tourist101Sites));
        visitedSites.put(102, new HashSet<>(tourist102Sites));
    }
    /**
     * Checks if a tourist has already given feedback for a specific site.
     * Returns true if feedback exists, false otherwise.
     */
    public boolean hasFeedbackForSite(int touristId, int siteId) {
        if (touristId <= 0 || siteId <= 0) {
            return false;
        }
        for (Feedback f : feedbacks) {
            if (f.getTouristId() == touristId && f.getSiteId() == siteId) {
                return true;
            }
        }
        return false;
    }
    /**
     * Inserts a new feedback into the storage.
     * Throws exception if feedback is invalid or duplicate.
     */
    public synchronized void insertFeedback(Feedback feedback) {
        if (feedback == null) {
            throw new IllegalArgumentException("Feedback cannot be null");
        }
        // Check if feedback already exists
        if (hasFeedbackForSite(feedback.getTouristId(), feedback.getSiteId())) {
            throw new IllegalStateException("Feedback already exists for this tourist and site");
        }
        // Validate feedback
        if (!feedback.isValid()) {
            throw new IllegalArgumentException("Invalid feedback data");
        }
        feedbacks.add(feedback);
    }
    /**
     * Adds a site to the tourist's visited sites list.
     * Creates the list if it doesn't exist.
     */
    public synchronized void addVisitedSite(int touristId, int siteId) {
        if (touristId <= 0 || siteId <= 0) {
            throw new IllegalArgumentException("Invalid touristId or siteId");
        }
        visitedSites.putIfAbsent(touristId, new HashSet<>());
        visitedSites.get(touristId).add(siteId);
    }
    /**
     * Gets the total number of feedbacks (for ID generation).
     */
    public int getAllFeedbacksSize() {
        return feedbacks.size();
    }
    /**
     * Gets all feedbacks for a specific tourist
     */
    public List<Feedback> getFeedbacksByTourist(int touristId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback f : feedbacks) {
            if (f.getTouristId() == touristId) {
                result.add(f);
            }
        }
        return result;
    }
    /**
     * Gets all feedbacks for a specific site
     */
    public List<Feedback> getFeedbacksBySite(int siteId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback f : feedbacks) {
            if (f.getSiteId() == siteId) {
                result.add(f);
            }
        }
        return result;
    }
    /**
     * Gets all sites visited by a tourist
     */
    public Set<Integer> getVisitedSitesByTourist(int touristId) {
        return visitedSites.getOrDefault(touristId, new HashSet<>());
    }
    /**
     * Removes all data (for testing purposes)
     */
    public synchronized void clearAllData() {
        feedbacks.clear();
        visitedSites.clear();
    }
    /**
     * Gets statistics about feedbacks
     */
    public Map<String, Object> getFeedbackStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalFeedbacks", feedbacks.size());
        stats.put("totalTourists", visitedSites.size());
        if (!feedbacks.isEmpty()) {
            double averageVote = feedbacks.stream()
                .mapToInt(Feedback::getVote)
                .average()
                .orElse(0.0);
            stats.put("averageVote", averageVote);
        }
        return stats;
    }
}