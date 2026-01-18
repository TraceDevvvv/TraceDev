// Manages feedback data and business logic.
// Tracks which users have submitted feedback for which sites.
// Provides methods to check for existing feedback and manage user interactions.
package feedbacksystem;
import java.util.ArrayList;
import java.util.List;
/**
 * Manages feedback data and business logic.
 * Tracks which users have submitted feedback for which sites.
 * Provides methods to check for existing feedback and manage user interactions.
 */
public class FeedbackManager {
    private List<FeedbackData> feedbackList;
    private String currentUserId;
    private String currentSite;
    public FeedbackManager(String userId, String site) {
        this.currentUserId = userId;
        this.currentSite = site;
        this.feedbackList = new ArrayList<>();
        // DO NOT initialize sample data here - let it be configurable
    }
    /**
     * Adds sample data including feedback for current user/site
     * Call this to simulate the "entry condition met" scenario
     */
    public void initializeWithExistingFeedback() {
        feedbackList.add(new FeedbackData(
            "Amazon", 
            "user123", 
            "Great shopping experience!", 
            new java.util.Date(System.currentTimeMillis() - 86400000L),
            5
        ));
        feedbackList.add(new FeedbackData(
            "Google", 
            "user456", 
            "Search engine works well.", 
            new java.util.Date(System.currentTimeMillis() - 172800000L),
            4
        ));
        feedbackList.add(new FeedbackData(
            currentSite, 
            currentUserId, 
            "Already submitted feedback earlier.", 
            new java.util.Date(System.currentTimeMillis() - 3600000L),
            3
        ));
    }
    /**
     * Adds sample data WITHOUT feedback for current user/site
     * Call this to simulate the "entry condition NOT met" scenario
     */
    public void initializeWithoutExistingFeedback() {
        feedbackList.add(new FeedbackData(
            "Amazon", 
            "user123", 
            "Great shopping experience!", 
            new java.util.Date(System.currentTimeMillis() - 86400000L),
            5
        ));
        feedbackList.add(new FeedbackData(
            "Google", 
            "user456", 
            "Search engine works well.", 
            new java.util.Date(System.currentTimeMillis() - 172800000L),
            4
        ));
        // Note: NO feedback for current user/site
    }
    /**
     * Checks if the current user has already submitted feedback for the current site.
     * 
     * @return true if feedback already exists, false otherwise
     */
    public boolean hasUserAlreadySubmittedFeedback() {
        for (FeedbackData feedback : feedbackList) {
            if (feedback.getSiteName().equals(currentSite) && 
                feedback.getUserId().equals(currentUserId)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Strict validation of entry conditions as per use case.
     * Checks if user already has feedback for the selected site.
     * 
     * @param userId the user ID to check
     * @param siteName the site name to check
     * @return true if entry condition is met (user already has feedback), false otherwise
     */
    public boolean validateEntryCondition(String userId, String siteName) {
        for (FeedbackData feedback : feedbackList) {
            if (feedback.getSiteName().equals(siteName) && 
                feedback.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Gets the existing feedback submitted by the current user for the current site.
     * 
     * @return the existing FeedbackData, or null if none exists
     */
    public FeedbackData getExistingFeedback() {
        for (FeedbackData feedback : feedbackList) {
            if (feedback.getSiteName().equals(currentSite) && 
                feedback.getUserId().equals(currentUserId)) {
                return feedback;
            }
        }
        return null;
    }
    /**
     * Gets the site currently being viewed/processed.
     * 
     * @return current site name
     */
    public String getCurrentSite() {
        return currentSite;
    }
    /**
     * Gets the current user ID.
     * 
     * @return current user ID
     */
    public String getCurrentUserId() {
        return currentUserId;
    }
    /**
     * Gets the total number of feedback entries in the system.
     * 
     * @return total feedback count
     */
    public int getTotalFeedbackCount() {
        return feedbackList.size();
    }
}