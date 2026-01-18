import java.util.*;

public class Application {
    public static void main(String[] args) {
        // Simulate Agency Operator logged in
        AgencyOperator operator = AgencyOperator.getInstance();
        operator.setLoggedIn(true);
        
        // Simulate site search and selection
        Site selectedSite = SiteManager.searchSites("Site A").get(0);
        
        // Activate function to view feedback
        FeedbackManager feedbackManager = FeedbackManager.getInstance();
        feedbackManager.displayFeedbackForSite(selectedSite);
    }
}