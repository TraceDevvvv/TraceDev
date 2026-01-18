import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents a Tourist in the system.
 * A tourist can view sites and modify their feedback.
 */
class Tourist {
    private String id;
    private String name;

    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tourist{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}

/**
 * Represents a Site that tourists can visit and leave feedback for.
 */
class Site {
    private String id;
    private String name;
    private String description;
    private List<Feedback> feedbackList;

    public Site(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.feedbackList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    /**
     * Adds feedback to the site.
     * @param feedback The feedback to add.
     */
    public void addFeedback(Feedback feedback) {
        this.feedbackList.add(feedback);
    }

    @Override
    public String toString() {
        return "Site{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}

/**
 * Represents feedback left by a tourist for a specific site.
 * Contains a rating and a comment.
 */
class Feedback {
    private String id;
    private Tourist tourist;
    private Site site;
    private int rating; // e.g., 1-5 stars
    private String comment;
    private long timestamp; // Time when the feedback was created/modified

    public Feedback(String id, Tourist tourist, Site site, int rating, String comment) {
        this.id = id;
        this.tourist = tourist;
        this.site = site;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public Site getSite() {
        return site;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        this.timestamp = System.currentTimeMillis(); // Update timestamp on modification
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Feedback{" +
               "id='" + id + '\'' +
               ", tourist=" + tourist.getName() +
               ", site=" + site.getName() +
               ", rating=" + rating +
               ", comment='" + comment + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }
}

/**
 * Service class responsible for managing comments and their modifications.
 * This class encapsulates the business logic for the ModifyComment use case.
 */
class CommentService {

    /**
     * Simulates a connection to an external ETOUR server.
     * In a real application, this would involve network calls.
     * For this simulation, it randomly returns true or false to simulate connection issues.
     * @return true if connection is successful, false otherwise.
     */
    private boolean isETOURServerConnected() {
        // Simulate network interruption
        return Math.random() > 0.1; // 10% chance of interruption
    }

    /**
     * Modifies an existing comment for a given feedback.
     * This method implements the core logic of the "ModifyComment" use case.
     *
     * @param tourist The tourist attempting to modify the comment.
     * @param site The site where the feedback was left.
     * @param feedbackId The ID of the feedback to modify.
     * @param newComment The new comment text.
     * @return An Optional containing the modified Feedback if successful, or empty if modification failed.
     * @throws IllegalStateException if ETOUR server connection is interrupted.
     */
    public Optional<Feedback> modifyComment(Tourist tourist, Site site, String feedbackId, String newComment) throws IllegalStateException {
        // Entry Tourist conditions: is in the details of a particular site.
        // This is implicitly handled by passing the site and feedbackId.
        // In a real UI, the tourist would navigate to a site's details and then select their feedback.

        // 1. Choose to change the comment on the feedback for the site issued by the appropriate functionality.
        // This step is represented by the method call itself, with feedbackId and newComment as inputs.

        // Find the feedback associated with the given ID and tourist for the specific site.
        Optional<Feedback> feedbackOptional = site.getFeedbackList().stream()
                .filter(f -> f.getId().equals(feedbackId) && f.getTourist().getId().equals(tourist.getId()))
                .findFirst();

        if (feedbackOptional.isEmpty()) {
            System.out.println("Error: Feedback with ID '" + feedbackId + "' not found or does not belong to tourist '" + tourist.getName() + "' for site '" + site.getName() + "'.");
            return Optional.empty();
        }

        Feedback feedbackToModify = feedbackOptional.get();

        // 2. Verify the data entered and asks for confirmation of the change.
        // Where the data is invalid or insufficient, the system activates the use case Errored.
        if (newComment == null || newComment.trim().isEmpty()) {
            System.out.println("Error: New comment cannot be empty or null. Activating 'Errored' use case.");
            // In a real system, this would trigger an 'Errored' use case,
            // which might involve logging, displaying an error message, etc.
            return Optional.empty();
        }

        // Simulate asking for confirmation (in a real UI, this would be a dialog)
        System.out.println("Proposed change for feedback ID " + feedbackId + ":");
        System.out.println("Old comment: \"" + feedbackToModify.getComment() + "\"");
        System.out.println("New comment: \"" + newComment + "\"");
        System.out.print("Confirm change? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Comment modification cancelled by user.");
            return Optional.empty();
        }

        // 3. Confirm the change of the comment.
        // This is handled by the 'yes' confirmation above.

        // Check for ETOUR server connection before making the change
        if (!isETOURServerConnected()) {
            // Exit condition: Interruption of the connection to the server ETOUR.
            throw new IllegalStateException("ETOUR server connection interrupted. Cannot modify comment.");
        }

        // 4. Edit commentary on selected feedback
        feedbackToModify.setComment(newComment);
        System.out.println("Comment successfully updated for feedback ID " + feedbackId + ".");

        // Exit conditions: The system shall notify the alterations of the comment
        System.out.println("Notification: Comment for feedback ID " + feedbackId + " has been altered.");

        return Optional.of(feedbackToModify);
    }
}

/**
 * Main application class to demonstrate the ModifyComment use case.
 */
public class ModifyCommentApp {

    public static void main(String[] args) {
        // --- Setup initial data ---
        Tourist tourist1 = new Tourist("T001", "Alice");
        Tourist tourist2 = new Tourist("T002", "Bob");

        Site site1 = new Site("S001", "Eiffel Tower", "A famous landmark in Paris.");
        Site site2 = new Site("S002", "Louvre Museum", "Home to thousands of works of art.");

        // Tourist1 leaves feedback for Site1
        Feedback feedback1_1 = new Feedback("F001", tourist1, site1, 5, "Absolutely breathtaking view!");
        site1.addFeedback(feedback1_1);

        // Tourist1 leaves feedback for Site2
        Feedback feedback1_2 = new Feedback("F002", tourist1, site2, 4, "A bit crowded, but amazing art.");
        site2.addFeedback(feedback1_2);

        // Tourist2 leaves feedback for Site1
        Feedback feedback2_1 = new Feedback("F003", tourist2, site1, 3, "Overrated, too many tourists.");
        site1.addFeedback(feedback2_1);

        CommentService commentService = new CommentService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Modify Comment Use Case Demonstration ---");

        // --- Scenario 1: Successful modification ---
        System.out.println("\n--- Scenario 1: Successful modification by Tourist Alice ---");
        System.out.println("Alice's current feedback on Eiffel Tower (ID: F001): " + feedback1_1.getComment());
        try {
            Optional<Feedback> modifiedFeedback = commentService.modifyComment(tourist1, site1, "F001", "Still breathtaking, even better at night!");
            if (modifiedFeedback.isPresent()) {
                System.out.println("New comment after modification: " + modifiedFeedback.get().getComment());
            }
        } catch (IllegalStateException e) {
            System.err.println("Modification failed due to server error: " + e.getMessage());
        }

        // --- Scenario 2: Attempt to modify with empty comment (Errored use case) ---
        System.out.println("\n--- Scenario 2: Attempt to modify with empty comment ---");
        System.out.println("Alice's current feedback on Louvre Museum (ID: F002): " + feedback1_2.getComment());
        try {
            commentService.modifyComment(tourist1, site2, "F002", ""); // Empty comment
        } catch (IllegalStateException e) {
            System.err.println("Modification failed due to server error: " + e.getMessage());
        }

        // --- Scenario 3: Attempt to modify someone else's comment ---
        System.out.println("\n--- Scenario 3: Attempt by Tourist Alice to modify Tourist Bob's comment ---");
        System.out.println("Bob's current feedback on Eiffel Tower (ID: F003): " + feedback2_1.getComment());
        try {
            commentService.modifyComment(tourist1, site1, "F003", "Actually, it was quite nice!"); // Alice trying to modify Bob's feedback
        } catch (IllegalStateException e) {
            System.err.println("Modification failed due to server error: " + e.getMessage());
        }

        // --- Scenario 4: Attempt to modify non-existent feedback ---
        System.out.println("\n--- Scenario 4: Attempt to modify non-existent feedback ---");
        try {
            commentService.modifyComment(tourist1, site1, "F999", "This feedback doesn't exist.");
        } catch (IllegalStateException e) {
            System.err.println("Modification failed due to server error: " + e.getMessage());
        }

        // --- Scenario 5: User cancels modification ---
        System.out.println("\n--- Scenario 5: User cancels modification ---");
        System.out.println("Alice's current feedback on Eiffel Tower (ID: F001): " + feedback1_1.getComment());
        try {
            // For this scenario, the user would type 'no' at the confirmation prompt
            System.out.println("Please type 'no' when prompted to cancel this modification attempt.");
            commentService.modifyComment(tourist1, site1, "F001", "This comment will be cancelled.");
        } catch (IllegalStateException e) {
            System.err.println("Modification failed due to server error: " + e.getMessage());
        }

        // --- Scenario 6: Simulate ETOUR server interruption ---
        System.out.println("\n--- Scenario 6: Simulate ETOUR server interruption (may or may not happen due to random chance) ---");
        System.out.println("Alice's current feedback on Louvre Museum (ID: F002): " + feedback1_2.getComment());
        System.out.println("Attempting to modify. There's a 10% chance of server interruption.");
        try {
            // This might fail due to the random server connection check
            commentService.modifyComment(tourist1, site2, "F002", "The art was truly inspiring!");
        } catch (IllegalStateException e) {
            System.err.println("Modification failed due to server error: " + e.getMessage());
        }

        scanner.close();
        System.out.println("\n--- Demonstration End ---");
    }
}