package exceptions;

import java.util.Date;

/**
 * Custom exception thrown when data source access fails (e.g., connection lost).
 * Satisfies requirement REQ-014.
 */
public class DataSourceAccessException extends RuntimeException {
    private String message;
    private Date timestamp;

    public DataSourceAccessException(String message) {
        super(message);
        this.message = message;
        this.timestamp = new Date();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}