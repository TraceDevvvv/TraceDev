'''
Custom exception to represent an interruption of the connection to the server ETOUR.
As specified in the use case: "Interruption of the connection to the server ETOUR."
'''
public class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}