'''
Custom exception to simulate an interruption in the connection to the server.
This can be thrown by the database layer to indicate a network or server issue.
'''
public class ServerConnectionException extends Exception {
    /**
     * Constructs a new ServerConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ServerConnectionException(String message) {
        super(message);
    }
}