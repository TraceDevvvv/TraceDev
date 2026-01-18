/*
This custom exception is used to simulate a network or server connection interruption.
It allows the service layer to throw a specific error that the GUI can catch
and report to the user, fulfilling the "Interruption of connection to the server ETOUR" exit condition.
*/
public class ConnectionInterruptionException extends Exception {
    /**
     * Constructs a new ConnectionInterruptionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionInterruptionException(String message) {
        super(message);
    }
}