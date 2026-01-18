'''
Custom exception class to simulate connection interruptions to the SMOS server.
This helps in demonstrating error handling as per the use case.
'''
public class SMOSConnectionException extends Exception {
    /**
     * Constructs a new SMOSConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public SMOSConnectionException(String message) {
        super(message);
    }
    /**
     * Constructs a new SMOSConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}