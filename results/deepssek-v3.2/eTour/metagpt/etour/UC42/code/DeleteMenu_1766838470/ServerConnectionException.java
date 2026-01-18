/**
 * Custom exception thrown when there is a server connection interruption.
 * This exception is specifically for ETOUR server connection issues as mentioned
 * in the use case requirements.
 */
public class ServerConnectionException extends Exception {
    
    /**
     * Constructs a new ServerConnectionException with a default message.
     */
    public ServerConnectionException() {
        super("Server connection interrupted. Please check your connection to the ETOUR server.");
    }
    
    /**
     * Constructs a new ServerConnectionException with the specified detail message.
     * 
     * @param message the detail message explaining the connection issue
     */
    public ServerConnectionException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ServerConnectionException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the connection issue
     * @param cause the underlying cause of the connection interruption
     */
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new ServerConnectionException with the specified cause.
     * 
     * @param cause the underlying cause of the connection interruption
     */
    public ServerConnectionException(Throwable cause) {
        super(cause);
    }
}