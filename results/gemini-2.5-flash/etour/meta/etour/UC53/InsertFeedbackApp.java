import java.util.*;

/**
 * Main application class for the InsertFeedback use case.
 * Simulates a simple console-based feedback system for tourists visiting sites.
 */
public class InsertFeedbackApp {

    public static void main(String[] args) {
        // Initialize system data
        List<Site> sites = Arrays.asList(
                new Site("S1", "Colosseum"),
                new Site("S2", "Louvre Museum"),
                new Site("S3", "Great Wall of China")
        );

        Tourist tourist = new Tourist("T1", "Alice");

        FeedbackManager feedbackManager = new FeedbackManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome, " + tourist.getName() + "!");

        // Simulate tourist card is in a particular site
        Site currentSite = selectSite(scanner, sites);
        if (currentSite == null) {
            System.out.println("No site selected. Exiting.");
            return;
        }

        // Entry condition: Tourist card is in a particular site
        System.out.println("You are at: " + currentSite.getName());

        // Activate the feature on the issue of feedback
        System.out.println("Would you like to leave feedback for this site? (yes/no)");
        String activate = scanner.nextLine().trim().toLowerCase();
        if (!activate.equals("yes")) {
            System.out.println("Feedback operation cancelled.");
            return;
        }

        // Step 2: Verify if feedback already exists
        if (feedbackManager.hasFeedback(tourist, currentSite)) {
            // FeedbackAlreadyReleased use case
            System.out.println("You have already released feedback for this site.");
            return;
        }

        // Step 3: Fill out the form
        Feedback feedback = fillFeedbackForm(scanner, tourist, currentSite);
        if (feedback == null) {
            System.out.println("Feedback operation cancelled.");
            return;
        }

        // Step 4: Verify the data entered
        if (!feedback.isValid()) {
            // Errored use case
            System.out.println("Error: Invalid or insufficient data. Feedback not submitted.");
            return;
        }

        // Step 5: Confirming the issue of feedback
        System.out.println("Please confirm submission of your feedback (yes/no):");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (!confirm.equals("yes")) {
            System.out.println("Feedback operation cancelled.");
            return;
        }

        // Step 6: Remember feedback and insert site in visited list
        try {
            feedbackManager.addFeedback(feedback);
            tourist.addVisitedSite(currentSite);
            System.out.println("Feedback successfully submitted for site: " + currentSite.getName());
        } catch (ETOURConnectionException e) {
            System.out.println("Connection to ETOUR server interrupted. Please try again later.");
        }

        scanner.close();
    }

    /**
     * Allows the user to select a site from the list.
     */
    private static Site selectSite(Scanner scanner, List<Site> sites) {
        System.out.println("Available sites:");
        for (int i = 0; i < sites.size(); i++) {
            System.out.println((i + 1) + ". " + sites.get(i).getName());
        }
        System.out.println("Select a site by number (or type 0 to exit):");
        int choice = -1;
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
                if (choice == 0) {
                    return null;
                }
                if (choice >= 1 && choice <= sites.size()) {
                    return sites.get(choice - 1);
                }
            } catch (NumberFormatException e) {
                // Ignore and reprompt
            }
            System.out.println("Invalid selection. Please enter a valid number:");
        }
    }

    /**
     * Prompts the user to fill out the feedback form.
     */
    private static Feedback fillFeedbackForm(Scanner scanner, Tourist tourist, Site site) {
        System.out.println("---- Feedback Form ----");
        System.out.println("Select your vote (1-5):");
        int vote = -1;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("cancel")) {
                return null;
            }
            try {
                vote = Integer.parseInt(input);
                if (vote >= 1 && vote <= 5) {
                    break;
                }
            } catch (NumberFormatException e) {
                // Ignore and reprompt
            }
            System.out.println("Invalid vote. Please enter a number between 1 and 5 (or type 'cancel' to abort):");
        }

        System.out.println("Enter your comment (at least 5 characters, or type 'cancel' to abort):");
        String comment = "";
        while (true) {
            comment = scanner.nextLine().trim();
            if (comment.equalsIgnoreCase("cancel")) {
                return null;
            }
            if (comment.length() >= 5) {
                break;
            }
            System.out.println("Comment too short. Please enter at least 5 characters (or type 'cancel' to abort):");
        }

        return new Feedback(tourist, site, vote, comment);
    }
}

/**
 * Represents a tourist.
 */
class Tourist {
    private final String id;
    private final String name;
    private final Set<String> visitedSiteIds;

    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
        this.visitedSiteIds = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addVisitedSite(Site site) {
        visitedSiteIds.add(site.getId());
    }

    public boolean hasVisitedSite(Site site) {
        return visitedSiteIds.contains(site.getId());
    }
}

/**
 * Represents a site.
 */
class Site {
    private final String id;
    private final String name;

    public Site(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

/**
 * Represents feedback left by a tourist for a site.
 */
class Feedback {
    private final Tourist tourist;
    private final Site site;
    private final int vote; // 1-5
    private final String comment;

    public Feedback(Tourist tourist, Site site, int vote, String comment) {
        this.tourist = tourist;
        this.site = site;
        this.vote = vote;
        this.comment = comment;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public Site getSite() {
        return site;
    }

    public int getVote() {
        return vote;
    }

    public String getComment() {
        return comment;
    }

    /**
     * Validates the feedback data.
     */
    public boolean isValid() {
        return vote >= 1 && vote <= 5 && comment != null && comment.trim().length() >= 5;
    }
}

/**
 * Manages feedbacks and simulates connection to ETOUR server.
 */
class FeedbackManager {
    // Map: (touristId, siteId) -> Feedback
    private final Map<String, Feedback> feedbacks = new HashMap<>();
    private final Random random = new Random();

    /**
     * Checks if the tourist has already left feedback for the site.
     */
    public boolean hasFeedback(Tourist tourist, Site site) {
        String key = makeKey(tourist, site);
        return feedbacks.containsKey(key);
    }

    /**
     * Adds feedback to the system, simulating possible server interruption.
     * Throws ETOURConnectionException if connection is interrupted.
     */
    public void addFeedback(Feedback feedback) throws ETOURConnectionException {
        // Simulate a 5% chance of server interruption
        if (random.nextInt(100) < 5) {
            throw new ETOURConnectionException("Connection interrupted.");
        }
        String key = makeKey(feedback.getTourist(), feedback.getSite());
        feedbacks.put(key, feedback);
    }

    private String makeKey(Tourist tourist, Site site) {
        return tourist.getId() + "::" + site.getId();
    }
}

/**
 * Exception representing interruption of connection to ETOUR server.
 */
class ETOURConnectionException extends Exception {
    public ETOURConnectionException(String message) {
        super(message);
    }
}