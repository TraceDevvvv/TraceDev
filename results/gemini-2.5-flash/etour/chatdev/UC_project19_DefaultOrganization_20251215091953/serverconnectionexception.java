'''
Custom exception to represent an interruption in connection to the ETOUR server.
This can be used to simulate network issues or server unavailability.
'''
package exception;
public class ServerConnectionException extends Exception {
    /**
     * Constructs a new ServerConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ServerConnectionException(String message) {
        super(message);
    }
}