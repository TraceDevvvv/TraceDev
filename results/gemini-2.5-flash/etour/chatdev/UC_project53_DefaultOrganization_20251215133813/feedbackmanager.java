'''
Manages the core business logic for handling tourists, sites, and feedback operations.
This class acts as the "System" in the use case, performing verifications and data persistence.
It also simulates external interactions like the ETOUR server for network issues.
'''
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class FeedbackManager {
    private Map<String, Tourist> tourists;
    private Map<String, Site> sites;
    private Random random; // For simulating network interruptions
    private boolean simulateNetworkFailure = false; // Flag to force network failure for testing
    /**
     * Constructs a new FeedbackManager and initializes it with empty data stores.
     */
    public FeedbackManager() {
        this.tourists = new HashMap<>();
        this.sites = new HashMap<>();
        this.random = new Random();
    }
    // --- Data Management Methods ---
    /**
     * Adds a tourist to the manager's data store.
     * @param tourist The Tourist object to add.
     */
    public void addTourist(Tourist tourist) {
        if (tourist != null) {
            tourists.put(tourist.getId(), tourist);
        }
    }
    /**
     * Adds a site to the manager's data store.
     * @param site The Site object to add.
     */
    public void addSite(Site site) {
        if (site != null) {
            sites.put(site.getId(), site);
        }
    }
    /**
     * Retrieves a tourist by their ID.
     * @param id The unique ID of the tourist.
     * @return The Tourist object if found, otherwise null.
     */
    public Tourist getTourist(String id) {
        return tourists.get(id);
    }
    /**
     * Retrieves a site by its ID.
     * @param id The unique ID of the site.
     * @return The Site object if found, otherwise null.
     */
    public Site getSite(String id) {
        return sites.get(id);
    }
    /**
     * @return A map of all tourists managed by this system.
     */
    public Map<String, Tourist> getAllTourists() {
        return tourists;
    }
    /**
     * @return A map of all sites managed by this system.
     */
    public Map<String, Site> getAllSites() {
        return sites;
    }
    // --- Feedback Use Case Logic ---
    /**
     * Verifies if a tourist meets the conditions to give feedback for a specific site.
     * Corresponds to Step 2 of the use case.
     *
     * Entry conditions:
     * 1. The tourist card is in a particular site (tourist's current site matches the target site).
     *
     * Flow condition:
     * 2. The visitor has not already issued a feedback for the site.
     *
     * @param tourist The Tourist attempting to give feedback.
     * @param site The Site for which feedback is to be given.
     * @return True if the tourist can give feedback, false otherwise.
     */
    public boolean canGiveFeedback(Tourist tourist, Site site) {
        if (tourist == null || site == null) {
            System.out.println("DEBUG: Tourist or Site is null.");
            return false; // Invalid input
        }
        // Entry condition check: "The tourist card is in a particular site."
        // We assume 'currentSite' in the Tourist reflects this.
        if (tourist.getCurrentSite() == null || !tourist.getCurrentSite().equals(site)) {
            System.out.println("DEBUG: Tourist " + tourist.getName() + " is currently at " +
                    (tourist.getCurrentSite() != null ? tourist.getCurrentSite().getName() : "nowhere") +
                    ", but feedback is for " + site.getName());
            return false; // Tourist is not at the selected site
        }
        // Flow condition check: "The visitor has not already issued a feedback for the site."
        if (tourist.hasGivenFeedback(site)) {
            System.out.println("DEBUG: Tourist " + tourist.getName() + " has already given feedback for " + site.getName());
            // Activates the use case FeedbackGiàRilasciato
            return false;
        }
        return true; // All conditions met
    }
    /**
     * Validates the provided feedback data.
     * Corresponds to part of Step 4 of the use case ("Verify the data entered").
     *
     * @param vote The numerical vote.
     * @param comment The textual comment.
     * @return True if the data is valid, false otherwise.
     */
    private boolean isValidFeedback(int vote, String comment) {
        // Vote must be between 1 and 5 (inclusive)
        if (vote < 1 || vote > 5) {
            return false;
        }
        // Comment must not be empty or null
        // Trim to consider comments with only whitespace as empty
        if (comment == null || comment.trim().isEmpty()) {
            return false;
        }
        return true;
    }
    /**
     * Submits the feedback for a given tourist and site.
     * This method encapsulates steps 3, 4, 5, and 6 of the use case flow.
     *
     * @param tourist The Tourist giving the feedback.
     * @param site The Site receiving the feedback.
     * @param vote The numerical vote.
     * @param comment The textual comment.
     * @throws InvalidFeedbackDataException If the feedback data is invalid/insufficient (Errored use case).
     * @throws NetworkInterruptionException If there's a simulated connection interruption to ETOUR.
     */
    public void submitFeedback(Tourist tourist, Site site, int vote, String comment)
            throws InvalidFeedbackDataException, NetworkInterruptionException {
        // Step 4: Verify the data entered
        if (!isValidFeedback(vote, comment)) {
            // Activates the use case Errored
            throw new InvalidFeedbackDataException("Invalid or insufficient feedback data. Vote must be 1-5, comment cannot be empty.");
        }
        // Simulate connection interruption to ETOUR
        if (simulateNetworkFailure || random.nextInt(10) < 1) { // 10% chance of failure (or forced)
            // Interruption of the connection to the server ETOUR.
            throw new NetworkInterruptionException("Connection to ETOUR server interrupted. Please try again.");
        }
        // Step 5: Confirming the issue of feedback (implicitly confirmed by successful execution)
        // Step 6: Remember feedback and inserts the selected site in the list of sites visited.
        Feedback feedback = new Feedback(tourist.getId(), site.getId(), vote, comment);
        tourist.giveFeedback(site, feedback); // Tourist remembers feedback and ensures site is in visited list
        site.addFeedback(feedback);           // Site remembers feedback
        // The following line is redundant as tourist.giveFeedback already calls tourist.addVisitedSite(site);
        // tourist.addVisitedSite(site);         // Ensure site is in tourist's visited list, although setCurrentSite already does this for the current site
        System.out.println("Feedback successfully recorded: " + feedback);
    }
    /**
     * Sets the flag to simulate network failure.
     * @param simulateNetworkFailure True to force network failures, false otherwise.
     */
    public void setSimulateNetworkFailure(boolean simulateNetworkFailure) {
        this.simulateNetworkFailure = simulateNetworkFailure;
    }
}