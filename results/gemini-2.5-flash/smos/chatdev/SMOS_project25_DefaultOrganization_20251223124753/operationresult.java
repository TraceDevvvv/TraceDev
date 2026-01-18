/**
 * Encapsulates the outcome of a service operation, including success status and a message.
 */
public class OperationResult {
    private boolean success;
    private String message;
    /**
     * Constructs an OperationResult with a success status and a message.
     * @param success True if the operation was successful, false otherwise.
     * @param message A string message describing the outcome (e.g., success message, error message).
     */
    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    /**
     * Checks if the operation was successful.
     * @return True if the operation succeeded, false otherwise.
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * Retrieves the message associated with the operation's outcome.
     * @return The message string.
     */
    public String getMessage() {
        return message;
    }
}