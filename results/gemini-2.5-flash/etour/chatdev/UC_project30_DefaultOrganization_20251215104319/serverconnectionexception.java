/**
 * Custom exception thrown when there is an interruption in the connection to the server.
 * Corresponds to the 'ETOUR' exit condition in the use case.
 */
public class ServerConnectionException extends Exception {
    /**
     * Constructs a new ServerConnectionException with the specified detail message.
     * @param message The detail message.
     */
    public ServerConnectionException(String message) {
        super(message);
    }
    /**
     * Constructs a new ServerConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}