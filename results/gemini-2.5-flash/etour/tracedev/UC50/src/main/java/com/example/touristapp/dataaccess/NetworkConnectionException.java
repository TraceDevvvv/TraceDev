package com.example.touristapp.dataaccess;

/**
 * Custom exception to represent network connection issues.
 * This exception is thrown when there's an interruption in connection to the server or database.
 * Contributes to REQ-008: Ensures reliable display by handling connection issues.
 * Satisfies REQ-007: Exit Conditions: Connection to the server ETOUR IS interrupted.
 */
public class NetworkConnectionException extends Exception {
    private String message; // The class diagram specified a 'message' attribute.

    /**
     * Constructs a new NetworkConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public NetworkConnectionException(String message) {
        super(message); // Call the constructor of the parent Exception class
        this.message = message;
    }

    /**
     * Returns the detail message string of this throwable.
     * @return the detail message string of this Throwable instance.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message for the exception.
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}