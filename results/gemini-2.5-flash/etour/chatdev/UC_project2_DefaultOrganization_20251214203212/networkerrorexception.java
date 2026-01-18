/**
 * Custom exception class to represent a network or server connection error.
 * This is used to simulate the "ETOUR" condition mentioned in the use case.
 */
public class NetworkErrorException extends Exception {
    /**
     * Constructs a new NetworkErrorException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public NetworkErrorException(String message) {
        super(message);
    }
}