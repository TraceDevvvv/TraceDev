/**
 * ConnectionInterruptedException.java
 * 
 * Custom exception to represent interruption of the connection to the server.
 * This is used to handle the specific interruption case mentioned in the use case.
 */
public class ConnectionInterruptedException extends Exception {
    public ConnectionInterruptedException(String message) {
        super(message);
    }
}