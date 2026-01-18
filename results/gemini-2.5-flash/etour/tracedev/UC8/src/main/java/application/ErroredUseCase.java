package application;

/**
 * Application Layer: Represents an Errored Use Case.
 * Handles displaying or logging error messages.
 * Added to satisfy requirements R7, R12.
 */
public class ErroredUseCase {

    /**
     * Activates the errored use case, typically by displaying a critical error.
     * This is used when an error prevents the normal flow of a use case. (R7)
     * @param errorMessage The detailed error message.
     */
    public void activate(String errorMessage) {
        System.err.println("[ErroredUseCase] Activating Errored Use Case: " + errorMessage);
        // In a real application, this might trigger a specific error screen,
        // log to a central error system, or notify administrators.
    }

    /**
     * Handles an error that occurred, potentially allowing the system to recover or
     * providing feedback without completely halting the current operation. (R12)
     * @param errorMessage The detailed error message.
     */
    public void handleError(String errorMessage) {
        System.err.println("[ErroredUseCase] Handling Error: " + errorMessage);
        // This could be a less critical error, e.g., a connection blip
        // where the system tries to re-establish or notify the user gracefully.
    }
}