'''
Custom exception to simulate interruptions in connection to the ETOUR server.
This helps in handling the "Interruption of the connection to the server ETOUR" exit condition.
'''
public class ServiceConnectionException extends Exception {
    /**
     * Constructs a new ServiceConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ServiceConnectionException(String message) {
        super(message);
    }
}