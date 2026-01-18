'''
Custom exception to simulate an interruption of the connection to the server ETOUR.
'''
public class NetworkInterruptionException extends Exception {
    /**
     * Constructs a new NetworkInterruptionException with the specified detail message.
     * @param message The detail message.
     */
    public NetworkInterruptionException(String message) {
        super(message);
    }
}