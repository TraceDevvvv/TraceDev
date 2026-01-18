/**
 * Custom exception thrown when the operator cancels an operation.
 * This is used to distinguish user-initiated cancellations from other errors.
 */
public class OperationCancelledException extends Exception {
    
    /**
     * Constructs a new OperationCancelledException with a default message.
     */
    public OperationCancelledException() {
        super("Operation was cancelled by the operator.");
    }
    
    /**
     * Constructs a new OperationCancelledException with the specified detail message.
     * 
     * @param message the detail message explaining the cancellation reason
     */
    public OperationCancelledException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new OperationCancelledException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the cancellation reason
     * @param cause the underlying cause of the cancellation
     */
    public OperationCancelledException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new OperationCancelledException with the specified cause.
     * 
     * @param cause the underlying cause of the cancellation
     */
    public OperationCancelledException(Throwable cause) {
        super(cause);
    }
}