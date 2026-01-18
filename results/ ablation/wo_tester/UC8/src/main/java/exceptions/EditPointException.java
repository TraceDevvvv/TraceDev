package exceptions;

/**
 * Custom exception for edit point operations.
 */
public class EditPointException extends RuntimeException {
    private String message;
    private String errorType;

    public EditPointException(String message, String errorType) {
        super(message);
        this.message = message;
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getErrorType() {
        return errorType;
    }
}