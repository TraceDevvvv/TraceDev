// Handles the feedback submission process with proper entry condition validation.
// Manages the state recovery process as specified in the use case.
package feedbacksystem;
/**
 * Handles the feedback submission process with proper entry condition validation.
 * Manages the state recovery process as specified in the use case.
 */
public class FeedbackSubmissionHandler {
    private FeedbackManager feedbackManager;
    private ApplicationState previousState;
    private boolean notificationAcknowledged;
    /**
     * Creates a new feedback submission handler.
     * 
     * @param feedbackManager the feedback manager containing business logic
     */
    public FeedbackSubmissionHandler(FeedbackManager feedbackManager) {
        this.feedbackManager = feedbackManager;
        this.notificationAcknowledged = false;
    }
    /**
     * Validates entry conditions before attempting to submit feedback.
     * This implements the "Entry conditions" from the use case.
     * 
     * @return true if user already has feedback for the site (entry condition met), false otherwise
     */
    public boolean validateEntryConditions() {
        String userId = feedbackManager.getCurrentUserId();
        String siteName = feedbackManager.getCurrentSite();
        boolean conditionMet = feedbackManager.validateEntryCondition(userId, siteName);
        System.out.println("Entry condition validation:");
        System.out.println("  User: " + userId);
        System.out.println("  Site: " + siteName);
        System.out.println("  Already has feedback: " + conditionMet);
        return conditionMet;
    }
    /**
     * Saves the current application state before showing notification.
     * This prepares for state recovery in Step 3.
     * 
     * @param currentScreen the current screen name
     * @param formData any form data to preserve
     */
    public void saveCurrentState(String currentScreen, Object formData) {
        previousState = new ApplicationState(
            currentScreen,
            feedbackManager.getCurrentSite(),
            feedbackManager.getCurrentUserId(),
            formData
        );
        System.out.println("Saved previous state: " + previousState);
    }
    /**
     * Processes the feedback submission attempt according to use case flow.
     * Returns true if notification should be shown, false otherwise.
     */
    public boolean shouldShowNotification() {
        // Step 1: Check if entry condition is met
        if (validateEntryConditions()) {
            // Entry condition met - user already has feedback
            return true;
        }
        // Entry condition not met - user doesn't have feedback yet
        return false;
    }
    /**
     * Marks the notification as read/acknowledged.
     * This implements Step 2 of the use case: "Confirmation of the reading of the notification"
     */
    public void acknowledgeNotification() {
        notificationAcknowledged = true;
        System.out.println("Notification acknowledged by user.");
    }
    /**
     * Restores the previous application state.
     * This implements Step 3 of the use case: "Recovers the previous state"
     * 
     * @return the restored ApplicationState
     */
    public ApplicationState restorePreviousState() {
        if (previousState != null) {
            System.out.println("Restoring previous state: " + previousState);
            return previousState;
        } else {
            System.out.println("No previous state to restore.");
            return null;
        }
    }
    /**
     * Checks if notification has been acknowledged.
     * 
     * @return true if notification was acknowledged, false otherwise
     */
    public boolean isNotificationAcknowledged() {
        return notificationAcknowledged;
    }
    /**
     * Gets the previously saved state.
     * 
     * @return the saved ApplicationState
     */
    public ApplicationState getPreviousState() {
        return previousState;
    }
}