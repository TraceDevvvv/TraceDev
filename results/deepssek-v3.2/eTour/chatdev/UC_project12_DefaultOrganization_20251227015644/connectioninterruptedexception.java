/**
 * Custom exception to represent server connection interruptions in the ETOUR system.
 */
public class ConnectionInterruptedException extends Exception {
    public ConnectionInterruptedException(String message) {
        super(message);
    }
}