package ViewFeedback_1765890483;

import java.util.Scanner;

/**
 * Main application class to demonstrate the "ViewFeedback" use case.
 * This class initializes the necessary managers, simulates an agency operator's actions,
 * and orchestrates the flow of events as described in the use case.
 */
public class ViewFeedbackApp {

    public static void main(String[] args) {
        // 1. Initialize Managers
        SiteManager siteManager = new SiteManager();
        FeedbackManager feedbackManager = new FeedbackManager();

        // 2. Populate with sample data (simulating existing sites and feedback)
        // Add some sites
        Site site1 = new Site("S001", "Eiffel Tower");
        Site site2 = new Site("S002", "Louvre Museum");
        Site site3 = new Site("S003", "Statue of Liberty");
        siteManager.addSite(site1);
        siteManager.addSite(site2);
        siteManager.addSite(site3);

        // Add some feedback
        feedbackManager.addFeedback(new Feedback("S001", "Alice", "Amazing view, a must-see!"));
        feedbackManager.addFeedback(new Feedback("S001", "Bob", "Too crowded, but iconic."));
        feedbackManager.addFeedback(new Feedback("S002", "Charlie", "The Mona Lisa is smaller than expected."));
        feedbackManager.addFeedback(new Feedback("S002", "David", "Incredible art collection, spent hours here."));
        feedbackManager.addFeedback(new Feedback("S003", "Eve", "A symbol of freedom, very moving experience."));

        // 3. Initialize the Agency Operator
        AgencyOperator operator = new AgencyOperator(siteManager, feedbackManager);
        Scanner scanner = new Scanner(System.in);

        // Simulate "Entry Operator conditions: The agency has logged."
        operator.login();

        if (operator.isLoggedIn()) {
            System.out.println("\n--- Starting ViewFeedback Use Case ---");

            // Flow of events User System:
            // 1. View the list of sites as a result of the use case SearchSite,
            //    it selects and activates a function to view the feedback.
            operator.viewSites(); // Simulates SearchSite result display

            // 2. Upload Site Feedback selected. (This step is interpreted as selecting a site to view its feedback)
            operator.selectSiteAndDisplayFeedback(scanner);

            // Simulate another interaction to show robustness
            System.out.println("\n--- Attempting to view feedback for another site ---");
            operator.selectSiteAndDisplayFeedback(scanner);

            // Simulate the quality requirement: "Interruption of the connection to the server"
            operator.simulateServerInterruption();

            // Demonstrate attempting to view feedback after an interruption (though our simulation just prints a message)
            System.out.println("\n--- Attempting to view feedback after server interruption ---");
            operator.selectSiteAndDisplayFeedback(scanner);

            System.out.println("\n--- ViewFeedback Use Case Completed ---");
        } else {
            System.out.println("Application cannot proceed: Agency Operator failed to log in.");
        }

        scanner.close();
    }
}