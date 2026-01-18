package errorhandling;

/**
 * Error handling component as per requirement REQ-009.
 * Activated when errors occur in the main flow.
 */
public class ErroredUseCase {

    /**
     * Activates the error flow.
     * In a real system, this might log, notify, or switch to error UI.
     */
    public void activateErrorFlow(String errorDetails) {
        logError("ERROR_FLOW_ACTIVATED", errorDetails);
    }

    /**
     * Logs an error with given type and details.
     */
    public void logError(String errorType, String details) {
        // In a real implementation, this would write to a log file, send to monitoring, etc.
        System.err.println("[" + errorType + "] " + details);
    }
}