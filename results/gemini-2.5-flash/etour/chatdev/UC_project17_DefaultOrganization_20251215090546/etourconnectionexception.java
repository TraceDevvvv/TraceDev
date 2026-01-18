'''
Custom exception class to represent connection issues with the ETOUR server.
This is used to simulate the "Interruption of the connection to the server ETOUR"
exit condition from the use case.
'''
package exceptions;
/**
 * Custom exception class to represent connection issues with the ETOUR server.
 * This is used to simulate the "Interruption of the connection to the server ETOUR"
 * exit condition from the use case.
 */
public class EtourConnectionException extends Exception {
    /**
     * Constructs a new EtourConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public EtourConnectionException(String message) {
        super(message);
    }
}