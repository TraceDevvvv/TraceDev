/*
ConnectionInterruptedException.java - A custom exception class to represent
a failure in connecting to or communicating with the SMOS server.
*/
public class ConnectionInterruptedException extends Exception {
    /**
     * Constructs a new ConnectionInterruptedException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionInterruptedException(String message) {
        super(message);
    }
    /**
     * Constructs a new ConnectionInterruptedException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public ConnectionInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}