package hec.core;

/**
 * Custom exception for connection-related errors.
 * Includes an error code for more detailed error handling.
 */
public class ConnectionException extends RuntimeException {
    private final String message;
    private final int errorCode;

    /**
     * Constructor for ConnectionException.
     *
     * @param message   the error message
     * @param errorCode the error code
     */
    public ConnectionException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }
}