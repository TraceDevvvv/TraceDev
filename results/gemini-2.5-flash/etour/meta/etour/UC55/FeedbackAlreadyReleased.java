import java.util.Scanner;

/**
 * Represents a user in the system.
 * For simplicity, this class only holds a user ID.
 */
class User {
    private String userId;

    /**
     * Constructs a new User with the given ID.
     * @param userId The unique identifier for the user.
     */
    public User(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the user ID.
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }
}

/**
 * Represents a site in the system.
 * For simplicity, this class only holds a site ID.
 */
class Site {
    private String siteId;

    /**
     * Constructs a new Site with the given ID.
     * @param siteId The unique identifier for the site.
     */
    public Site(String siteId) {
        this.siteId = siteId;
    }

    /**
     * Gets the site ID.
     * @return The site ID.
     */
    public String getSiteId() {
        return siteId;
    }
}

/**
 * Represents a feedback entry.
 * For simplicity, this class only indicates if feedback exists for a user and site.
 */
class Feedback {
    private User user;
    private Site site;
    private String feedbackContent; // Placeholder for actual feedback content

    /**
     * Constructs a new Feedback entry.
     * @param user The user who provided the feedback.
     * @param site The site for which the feedback was provided.
     * @param feedbackContent The actual content of the feedback.
     */
    public Feedback(User user, Site site, String feedbackContent) {
        this.user = user;
        this.site = site;
        this.feedbackContent = feedbackContent;
    }

    /**
     * Gets the user associated with this feedback.
     * @return The user object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the site associated with this feedback.
     * @return The site object.
     */
    public Site getSite() {
        return site;
    }

    /**
     * Gets the feedback content.
     * @return The feedback content string.
     */
    public String getFeedbackContent() {
        return feedbackContent;
    }
}

/**
 * Manages feedback operations, including checking for existing feedback.
 * This class simulates a data store for feedbacks.
 */
class FeedbackManager {
    // In a real application, this would be a database or a more sophisticated data structure.
    // For this simulation, a simple array or list would suffice, but a more direct check
    // based on user and site is implemented for clarity of the use case.
    private Feedback[] feedbacks;
    private int feedbackCount;
    private static final int MAX_FEEDBACKS = 10; // Max feedbacks for this simulation

    /**
     * Constructs a new FeedbackManager.
     */
    public FeedbackManager() {
        this.feedbacks = new Feedback[MAX_FEEDBACKS];
        this.feedbackCount = 0;
    }

    /**
     * Checks if a user has already provided feedback for a specific site.
     *
     * @param user The user to check.
     * @param site The site to check.
     * @return true if feedback already exists, false otherwise.
     */
    public boolean hasUserProvidedFeedback(User user, Site site) {
        // Simulate checking a database or persistent storage
        for (int i = 0; i < feedbackCount; i++) {
            if (feedbacks[i].getUser().getUserId().equals(user.getUserId()) &&
                feedbacks[i].getSite().getSiteId().equals(site.getSiteId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds new feedback to the system.
     * In a real scenario, this would involve saving to a database.
     *
     * @param feedback The feedback object to add.
     * @return true if feedback was added successfully, false if storage is full.
     */
    public boolean addFeedback(Feedback feedback) {
        if (feedbackCount < MAX_FEEDBACKS) {
            feedbacks[feedbackCount++] = feedback;
            return true;
        }
        System.out.println("Feedback storage is full. Cannot add more feedback.");
        return false;
    }
}

/**
 * Main class to demonstrate the "FeedbackAlreadyReleased" use case.
 */
public class FeedbackAlreadyReleased {

    private static Scanner scanner = new Scanner(System.in);
    private static FeedbackManager feedbackManager = new FeedbackManager();

    /**
     * Simulates the process of a user attempting to submit feedback.
     * This method embodies the "FeedbackAlreadyReleased" use case.
     *
     * @param currentUser The user attempting to submit feedback.
     * @param selectedSite The site for which feedback is being submitted.
     */
    public static void attemptSubmitFeedback(User currentUser, Site selectedSite) {
        System.out.println("\n--- Attempting to submit feedback for Site: " + selectedSite.getSiteId() + " by User: " + currentUser.getUserId() + " ---");

        // Entry conditions: Check if the user has already been issued with a feedback for the selected site.
        if (feedbackManager.hasUserProvidedFeedback(currentUser, selectedSite)) {
            // Flow of events 1: Notification that the user has already issued a feedback for the site
            // and cancel the operation to insert a new feedback.
            System.out.println("Notification: You have already submitted feedback for site '" + selectedSite.getSiteId() + "'.");
            System.out.println("Operation to insert a new feedback has been cancelled.");

            // Flow of events 2: Confirmation of the reading of the notification.
            System.out.println("Press Enter to acknowledge this notification and return to the previous state...");
            scanner.nextLine(); // Wait for user input to confirm reading

            // Flow of events 3: Recovers the previous state.
            // In this simulation, "recovering previous state" means simply returning
            // control to the main loop or the calling function without proceeding
            // with new feedback submission.
            System.out.println("Previous state recovered. No new feedback was submitted.");
        } else {
            // If no feedback exists, proceed with submitting new feedback (simplified for this use case)
            System.out.println("No existing feedback found for site '" + selectedSite.getSiteId() + "'.");
            System.out.print("Please enter your feedback for site '" + selectedSite.getSiteId() + "': ");
            String feedbackContent = scanner.nextLine();
            Feedback newFeedback = new Feedback(currentUser, selectedSite, feedbackContent);
            if (feedbackManager.addFeedback(newFeedback)) {
                System.out.println("Feedback successfully submitted for site '" + selectedSite.getSiteId() + "'.");
            } else {
                System.out.println("Failed to submit feedback due to system limitations.");
            }
        }
        // Exit conditions: The system returns control to the user interaction.
        System.out.println("--- Control returned to user interaction. ---");
    }

    public static void main(String[] args) {
        // Initialize some users and sites for demonstration
        User user1 = new User("user123");
        User user2 = new User("user456");
        Site siteA = new Site("siteA");
        Site siteB = new Site("siteB");

        // Simulate an initial feedback being submitted by user1 for siteA
        System.out.println("--- Initializing system with some existing feedback ---");
        feedbackManager.addFeedback(new Feedback(user1, siteA, "Great site!"));
        System.out.println("User 'user123' has already submitted feedback for 'siteA'.");
        System.out.println("----------------------------------------------------");

        // Scenario 1: User1 tries to submit feedback for siteA again (FeedbackAlreadyReleased use case)
        attemptSubmitFeedback(user1, siteA);

        // Scenario 2: User1 tries to submit feedback for siteB (no existing feedback)
        attemptSubmitFeedback(user1, siteB);

        // Scenario 3: User2 tries to submit feedback for siteA (no existing feedback from user2)
        attemptSubmitFeedback(user2, siteA);

        // Scenario 4: User2 tries to submit feedback for siteA again (FeedbackAlreadyReleased use case)
        attemptSubmitFeedback(user2, siteA);

        scanner.close();
        System.out.println("\nProgram finished.");
    }
}