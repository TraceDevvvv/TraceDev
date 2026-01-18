/**
 * Custom exception class indicating a general application-level error.
 * Used to provide a consistent error handling mechanism across the application's
 * presentation or controller layer.
 * Added to satisfy Exit Conditions: Interruption of the connection to the server ETOUR.
 */
public class ApplicationException extends Exception {
    /**
     * Constructs a new ApplicationException with the specified detail message.
     * @param message The detail message.
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ApplicationException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}