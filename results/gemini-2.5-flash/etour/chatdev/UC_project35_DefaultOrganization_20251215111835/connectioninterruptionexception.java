/**
 * Custom exception class for simulating connection interruptions (e.g., ETOUR).
 * This exception is used to model network issues during the authentication process.
 */
public class ConnectionInterruptionException extends Exception {
    /**
     * Constructs a new ConnectionInterruptionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionInterruptionException(String message) {
        super(message);
    }
    /**
     * Constructs a new ConnectionInterruptionException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionInterruptionException(String message, Throwable cause) {
        super(message, cause);
    }
}