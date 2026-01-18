/**
 * Custom exception for ETOUR connection interruptions
 * As specified in the use case quality requirements
 */
public class ConnectionInterruptedException extends Exception {
    public ConnectionInterruptedException(String message) {
        super(message);
    }
    public ConnectionInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}