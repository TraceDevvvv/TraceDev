// File: SMOSServerException.java
import java.io.Serializable;

/**
 * Custom exception class for SMOS server-related errors.
 * This exception is thrown when there are issues connecting to or interacting with the SMOS server,
 * as specified by the 'Exit Conditions: Connection to the interrupted SMOS server.' requirement.
 */
public class SMOSServerException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for serializable exceptions

    // + message : String
    private String message;

    /**
     * Constructs a new SMOSServerException with the specified detail message.
     *
     * @param message The detail message.
     */
    public SMOSServerException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return The detail message string.
     */
    @Override
    public String getMessage() {
        return message;
    }
}