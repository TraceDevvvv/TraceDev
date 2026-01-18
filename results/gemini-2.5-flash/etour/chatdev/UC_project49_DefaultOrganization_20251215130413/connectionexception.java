/**
 * Custom exception to simulate connection interruptions to the ETOUR server.
 */
class ConnectionException extends Exception {
    /**
     * Constructs a new ConnectionException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionException(String message) {
        super(message);
    }
}