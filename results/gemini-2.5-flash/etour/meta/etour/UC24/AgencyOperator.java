package ViewFeedback_1765890483;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Simulates an Agency Operator interacting with the system to view sites and their feedback.
 * This class acts as the "Participating Actor" in the use case.
 */
public class AgencyOperator {
    private final SiteManager siteManager;
    private final FeedbackManager feedbackManager;
    private boolean loggedIn; // Represents the "Entry Operator conditions: The agency has logged."

    /**
     * Constructs a new AgencyOperator.
     *
     * @param siteManager     The SiteManager instance to interact with site data.
     * @param feedbackManager The FeedbackManager instance to interact with feedback data.
     */
    public AgencyOperator(SiteManager siteManager, FeedbackManager feedbackManager) {
        this.siteManager = siteManager;
        this.feedbackManager = feedbackManager;
        this.loggedIn = false; // Initially not logged in
    }

    /**
     * Simulates the login process for the agency operator.
     * In a real application, this would involve authentication.
     */
    public void login() {
        System.out.println("Agency Operator attempting to log in...");
        // Simulate successful login for this use case
        this.loggedIn = true;
        System.out.println("Agency Operator logged in successfully.");
    }

    /**
     * Checks if the agency operator is currently logged in.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Displays a list of all available sites to the operator.
     * This corresponds to "1. View the list of sites as a result of the use case SearchSite".
     */
    public void viewSites() {
        if (!isLoggedIn()) {
            System.out.println("Error: Operator must be logged in to view sites.");
            return;
        }
        System.out.println("\n--- Available Sites ---");
        List<Site> sites = siteManager.getAllSites();
        if (sites.isEmpty()) {
            System.out.println("No sites available.");
        } else {
            for (int i = 0; i < sites.size(); i++) {
                System.out.println((i + 1) + ". " + sites.get(i));
            }
        }
        System.out.println("-----------------------");
    }

    /**
     * Allows the operator to select a site and view its feedback.
     * This corresponds to "1. ...it selects and activates a function to view the feedback."
     * and "2. Upload Site Feedback selected."
     * and "Exit conditions: The system displays all feedback regarding the site selected."
     *
     * @param scanner A Scanner object for user input.
     */
    public void selectSiteAndDisplayFeedback(Scanner scanner) {
        if (!isLoggedIn()) {
            System.out.println("Error: Operator must be logged in to view feedback.");
            return;
        }

        List<Site> sites = siteManager.getAllSites();
        if (sites.isEmpty()) {
            System.out.println("No sites available to select feedback from.");
            return;
        }

        System.out.println("\nEnter the ID of the site to view feedback for (e.g., 'S001'):");
        String selectedSiteId = scanner.nextLine().trim();

        Optional<Site> selectedSite = siteManager.getSiteById(selectedSiteId);

        if (selectedSite.isPresent()) {
            System.out.println("\n--- Feedback for " + selectedSite.get().getName() + " (ID: " + selectedSite.get().getId() + ") ---");
            List<Feedback> siteFeedback = feedbackManager.getFeedbackBySiteId(selectedSiteId);

            if (siteFeedback.isEmpty()) {
                System.out.println("No feedback found for this site.");
            } else {
                siteFeedback.forEach(System.out::println);
            }
            System.out.println("----------------------------------------------------");
        } else {
            System.out.println("Site with ID '" + selectedSiteId + "' not found. Please try again.");
        }
    }

    /**
     * Simulates an interruption of the connection to the server.
     * This is a quality requirement.
     */
    public void simulateServerInterruption() {
        System.out.println("\n--- Simulating server connection interruption ---");
        System.out.println("Connection to the server has been interrupted. Some operations might fail.");
        // In a real system, this would involve setting a flag or throwing an exception
        // that affects data retrieval or submission. For this simulation, it's a print statement.
    }
}