/**
 * Custom exception for connection/data retrieval failures.
 */
public class DataRetrievalException extends RuntimeException {
    private String errorCode;

    public DataRetrievalException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}