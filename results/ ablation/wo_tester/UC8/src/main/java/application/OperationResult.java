package application;

/**
 * Represents the result of an operation.
 */
public class OperationResult {
    private boolean isSuccess;
    private String message;
    private String errorCode;

    public OperationResult(boolean isSuccess, String message, String errorCode) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}