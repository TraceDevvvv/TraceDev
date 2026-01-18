/**
 * Simulates a database for the ViewFeedback use case.
 * Provides methods to retrieve sites and feedback, with a chance of connection interruption.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class DatabaseSimulator {
    private Random random;
    public DatabaseSimulator() {
        random = new Random();
    }
    /**
     * Returns a list of all sites (simulating result from SearchSite use case).
     * @return List of Site objects.
     */
    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<>();
        sites.add(new Site(1, "Central Park"));
        sites.add(new Site(2, "City Museum"));
        sites.add(new Site(3, "Downtown Plaza"));
        sites.add(new Site(4, "Tech Hub Office"));
        sites.add(new Site(5, "Riverside Walkway"));
        return sites;
    }
    /**
     * Returns feedback for a specific site.
     * @param siteId The ID of the site.
     * @return List of Feedback objects for the site.
     * @throws Exception if connection interruption occurs (10% chance).
     */
    public List<Feedback> getFeedbackForSite(int siteId) throws Exception {
        // Simulate connection interruption with 10% probability (Quality requirement: Interruption of the connection to the server)
        if (random.nextDouble() < 0.1) {
            throw new Exception("Connection to server interrupted.");
        }
        List<Feedback> feedbackList = new ArrayList<>();
        switch (siteId) {
            case 1:
                feedbackList.add(new Feedback(1, "Beautiful and well-maintained.", 5));
                feedbackList.add(new Feedback(1, "Needs more benches.", 3));
                break;
            case 2:
                feedbackList.add(new Feedback(2, "Excellent exhibits.", 5));
                feedbackList.add(new Feedback(2, "Crowded on weekends.", 4));
                feedbackList.add(new Feedback(2, "Guides were very knowledgeable.", 5));
                break;
            case 3:
                feedbackList.add(new Feedback(3, "Clean and modern.", 4));
                feedbackList.add(new Feedback(3, "Parking is expensive.", 2));
                break;
            case 4:
                feedbackList.add(new Feedback(4, "Great workspace but Wi-Fi is slow.", 3));
                break;
            case 5:
                feedbackList.add(new Feedback(5, "Scenic views, perfect for evening walks.", 5));
                feedbackList.add(new Feedback(5, "Pathway needs repair in some sections.", 2));
                break;
            default:
                // No feedback for other sites
                break;
        }
        return feedbackList;
    }
}