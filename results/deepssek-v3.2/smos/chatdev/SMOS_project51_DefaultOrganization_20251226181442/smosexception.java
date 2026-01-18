/**
 * Custom exception for SMOS server interruptions.
 * This represents the postcondition mentioned in the use case where
 * "The user interrupts the connection operation to the interrupted SMOS server"
 */
public class SMOSException extends Exception {
    private Date timestamp;
    public SMOSException() {
        super("SMOS server connection interrupted");
        this.timestamp = new Date();
    }
    public SMOSException(String message) {
        super(message);
        this.timestamp = new Date();
    }
    public SMOSException(String message, Throwable cause) {
        super(message, cause);
        this.timestamp = new Date();
    }
    public SMOSException(Throwable cause) {
        super(cause);
        this.timestamp = new Date();
    }
    public Date getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString() {
        return "SMOSException{" +
               "timestamp=" + timestamp +
               ", message='" + getMessage() + '\'' +
               '}';
    }
}