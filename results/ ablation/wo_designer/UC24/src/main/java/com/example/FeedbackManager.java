import java.util.*;
import java.util.Date;

public class FeedbackManager {
    private static FeedbackManager instance;
    private List<Feedback> allFeedback;
    private ServerConnection serverConnection;
    
    private FeedbackManager() {
        allFeedback = new ArrayList<>();
        serverConnection = new ServerConnection();
        initializeSampleFeedback();
    }
    
    // Singleton pattern
    public static FeedbackManager getInstance() {
        if (instance == null) {
            instance = new FeedbackManager();
        }
        return instance;
    }
    
    // Initializes sample feedback data
    private void initializeSampleFeedback() {
        allFeedback.add(new Feedback("F001", "S001", "Excellent site maintenance", 5, new Date()));
        allFeedback.add(new Feedback("F002", "S001", "Good facilities", 4, new Date()));
        allFeedback.add(new Feedback("F003", "S002", "Average experience", 3, new Date()));
        allFeedback.add(new Feedback("F004", "S001", "Needs improvement", 2, new Date()));
        allFeedback.add(new Feedback("F005", "S003", "Outstanding service", 5, new Date()));
    }
    
    // Main function to display feedback for selected site
    public void displayFeedbackForSite(Site site) {
        if (site == null) {
            System.out.println("Error: No site selected.");
            return;
        }
        
        try {
            // Check if connection is available
            if (!serverConnection.isConnected()) {
                System.out.println("Error: Connection to server interrupted.");
                return;
            }
            
            // Upload/retrieve feedback for the site
            List<Feedback> siteFeedback = getFeedbackForSite(site.getSiteId());
            
            // Display all feedback
            if (siteFeedback.isEmpty()) {
                System.out.println("No feedback available for site: " + site.getName());
            } else {
                System.out.println("Feedback for Site: " + site.getName());
                System.out.println("==========================================");
                for (Feedback feedback : siteFeedback) {
                    System.out.println("Comment: " + feedback.getComment());
                    System.out.println("Rating: " + feedback.getRating() + "/5");
                    System.out.println("Date: " + feedback.getTimestamp());
                    System.out.println("------------------------------------------");
                }
                System.out.println("Total Feedback: " + siteFeedback.size());
            }
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving feedback: " + e.getMessage());
        }
    }
    
    // Retrieves feedback for a specific site
    private List<Feedback> getFeedbackForSite(String siteId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : allFeedback) {
            if (feedback.getSiteId().equals(siteId)) {
                result.add(feedback);
            }
        }
        return result;
    }
}