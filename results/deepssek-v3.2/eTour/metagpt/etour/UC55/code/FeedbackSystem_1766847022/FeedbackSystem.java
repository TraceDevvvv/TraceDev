import java.util.Scanner;

/**
 * Main class that simulates user interaction and handles the FeedbackAlreadyReleased use case.
 * This program demonstrates the flow when a user tries to submit feedback for a site
 * but has already submitted feedback previously.
 */
public class FeedbackSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize the feedback manager with some existing feedback
        FeedbackManager feedbackManager = new FeedbackManager();
        NotificationService notificationService = new NotificationService();
        StateManager stateManager = new StateManager();
        
        // Simulate existing feedback for user "john_doe" on site "example.com"
        feedbackManager.addFeedback(new Feedback("john_doe", "example.com", "Great site!"));
        
        System.out.println("=== Feedback Submission System ===");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter site URL: ");
        String siteUrl = scanner.nextLine();
        
        System.out.print("Enter your feedback message: ");
        String message = scanner.nextLine();
        
        // Save current state before attempting to submit feedback
        stateManager.saveState(username, siteUrl, message);
        
        // Check if feedback already exists for this user and site
        boolean feedbackExists = feedbackManager.hasFeedbackForSite(username, siteUrl);
        
        if (feedbackExists) {
            // Step 1: Show notification that feedback already exists
            notificationService.showNotification(
                "You have already submitted feedback for this site. " +
                "The operation to insert new feedback has been cancelled."
            );
            
            // Step 2: Confirm reading of the notification
            boolean confirmed = notificationService.confirmNotification();
            
            if (confirmed) {
                System.out.println("Notification confirmed by user.");
                
                // Step 3: Recover previous state
                StateManager.State previousState = stateManager.recoverState();
                System.out.println("Previous state recovered:");
                System.out.println("  Username: " + previousState.username());
                System.out.println("  Site URL: " + previousState.siteUrl());
                System.out.println("  Feedback message: " + previousState.feedbackMessage());
            } else {
                System.out.println("Notification was not confirmed.");
            }
            
            // Exit condition: Return control to user interaction
            System.out.println("\nReturning control to user interaction...");
            System.out.println("You can now perform other operations.");
        } else {
            // If no feedback exists, proceed with submission
            Feedback newFeedback = new Feedback(username, siteUrl, message);
            feedbackManager.addFeedback(newFeedback);
            System.out.println("Feedback submitted successfully!");
        }
        
        scanner.close();
    }
}

/**
 * Represents a feedback entry with user, site, and message.
 */
class Feedback {
    private final String username;
    private final String siteUrl;
    private final String message;
    
    public Feedback(String username, String siteUrl, String message) {
        this.username = username;
        this.siteUrl = siteUrl;
        this.message = message;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getSiteUrl() {
        return siteUrl;
    }
    
    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        return "Feedback from " + username + " for " + siteUrl + ": " + message;
    }
}

/**
 * Manages feedback entries and checks if feedback already exists for a user and site.
 */
class FeedbackManager {
    private java.util.List<Feedback> feedbackList;
    
    public FeedbackManager() {
        feedbackList = new java.util.ArrayList<>();
    }
    
    /**
     * Adds a new feedback to the system.
     * @param feedback The feedback to add
     */
    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }
    
    /**
     * Checks if a user has already submitted feedback for a specific site.
     * @param username The username to check
     * @param siteUrl The site URL to check
     * @return true if feedback exists, false otherwise
     */
    public boolean hasFeedbackForSite(String username, String siteUrl) {
        for (Feedback feedback : feedbackList) {
            if (feedback.getUsername().equals(username) && 
                feedback.getSiteUrl().equals(siteUrl)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets all feedback for a specific user.
     * @param username The username to get feedback for
     * @return List of feedback from the user
     */
    public java.util.List<Feedback> getFeedbackByUser(String username) {
        java.util.List<Feedback> userFeedback = new java.util.ArrayList<>();
        for (Feedback feedback : feedbackList) {
            if (feedback.getUsername().equals(username)) {
                userFeedback.add(feedback);
            }
        }
        return userFeedback;
    }
}

/**
 * Handles notifications and confirmation of reading.
 */
class NotificationService {
    private Scanner scanner;
    
    public NotificationService() {
        scanner = new Scanner(System.in);
    }
    
    /**
     * Displays a notification message to the user.
     * @param message The notification message to display
     */
    public void showNotification(String message) {
        System.out.println("\n=== NOTIFICATION ===");
        System.out.println(message);
        System.out.println("===================\n");
    }
    
    /**
     * Asks the user to confirm they have read the notification.
     * @return true if user confirms, false otherwise
     */
    public boolean confirmNotification() {
        System.out.print("Have you read the notification? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}

/**
 * Manages state recovery for the system.
 */
class StateManager {
    /**
     * Represents the state of a feedback submission attempt.
     */
    public record State(String username, String siteUrl, String feedbackMessage) {}
    
    private State currentState;
    
    /**
     * Saves the current state before attempting to submit feedback.
     * @param username The username attempting to submit feedback
     * @param siteUrl The site URL for the feedback
     * @param feedbackMessage The feedback message
     */
    public void saveState(String username, String siteUrl, String feedbackMessage) {
        currentState = new State(username, siteUrl, feedbackMessage);
    }
    
    /**
     * Recovers the previous state.
     * @return The previous state that was saved
     * @throws IllegalStateException if no state has been saved
     */
    public State recoverState() {
        if (currentState == null) {
            throw new IllegalStateException("No previous state to recover");
        }
        return currentState;
    }
    
    /**
     * Clears the saved state.
     */
    public void clearState() {
        currentState = null;
    }
}