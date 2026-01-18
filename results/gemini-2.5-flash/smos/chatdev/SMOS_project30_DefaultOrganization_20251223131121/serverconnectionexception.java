'''
Custom exception class for indicating server connection issues.
This provides a more specific error type than a generic RuntimeException,
allowing for better error handling and clearer diagnostic messages.
'''
public class ServerConnectionException extends RuntimeException {
    /**
     * Constructs a new ServerConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ServerConnectionException(String message) {
        super(message);
    }
    /**
     * Constructs a new ServerConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}