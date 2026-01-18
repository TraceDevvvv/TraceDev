package application;

/**
 * Result of an operation, returned to the UI.
 */
public class OperationResult {
    public final boolean isSuccess;
    public final String message;
    public final String confirmationId;

    public OperationResult(boolean isSuccess, String message, String confirmationId) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.confirmationId = confirmationId;
    }
}