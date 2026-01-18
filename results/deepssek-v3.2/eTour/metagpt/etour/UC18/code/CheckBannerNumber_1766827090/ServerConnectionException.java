/**
 * Custom exception class for server ETOUR connection interruptions.
 * This exception is thrown when there is a connection issue with the ETOUR server.
 */
public class ServerConnectionException extends Exception {
    
    /**
     * Constructs a new ServerConnectionException with a default message.
     */
    public ServerConnectionException() {
        super("ETOUR server connection interrupted");
    }
    
    /**
     * Constructs a new ServerConnectionException with the specified detail message.
     * 
     * @param message The detail message explaining the cause of the exception
     */
    public ServerConnectionException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ServerConnectionException with the specified detail message and cause.
     * 
     * @param message The detail message explaining the cause of the exception
     * @param cause The underlying cause of the exception
     */
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new ServerConnectionException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public ServerConnectionException(Throwable cause) {
        super("ETOUR server connection interrupted", cause);
    }
}