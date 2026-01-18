'''
This class defines a custom exception for simulating connection interruptions to an external system,
specifically referred to as "ETour Server" in the use case.
Using a custom exception allows for more specific error handling and identification
compared to throwing a generic RuntimeException.
'''
public class EtourConnectionException extends Exception {
    /**
     * Constructs a new EtourConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public EtourConnectionException(String message) {
        super(message);
    }
    /**
     * Constructs a new EtourConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public EtourConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}