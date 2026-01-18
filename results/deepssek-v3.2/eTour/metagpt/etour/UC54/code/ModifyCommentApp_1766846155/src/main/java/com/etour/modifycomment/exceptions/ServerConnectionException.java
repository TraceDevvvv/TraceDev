package com.etour.modifycomment.exceptions;

/**
 * Exception thrown when there's a connection interruption to server ETOUR
 * as specified in the use case requirements.
 * 
 * The use case specifically mentions "Interruption of the connection to the server ETOUR"
 * as a scenario that needs to be handled.
 * 
 * This exception is thrown when the system cannot establish or maintain a connection
 * to the ETOUR server during the comment modification process.
 */
public class ServerConnectionException extends ModifyCommentException {
    
    private final String serverUrl;
    private final String operation;
    private final int retryCount;
    private final long timeoutMillis;
    
    /**
     * Constructs a new ServerConnectionException with detailed information.
     * 
     * @param serverUrl the URL of the ETOUR server that could not be reached
     * @param operation the operation that was being performed when the connection failed
     * @param retryCount the number of retry attempts that were made
     * @param timeoutMillis the timeout value in milliseconds
     * @param message the detail message explaining the connection failure
     */
    public ServerConnectionException(String serverUrl, String operation, 
                                     int retryCount, long timeoutMillis, String message) {
        super(message);
        this.serverUrl = serverUrl;
        this.operation = operation;
        this.retryCount = retryCount;
        this.timeoutMillis = timeoutMillis;
    }
    
    /**
     * Constructs a new ServerConnectionException with basic information.
     * 
     * @param message the detail message explaining the connection failure
     */
    public ServerConnectionException(String message) {
        super(message);
        this.serverUrl = "etour.example.com"; // Default ETOUR server
        this.operation = "comment_modification";
        this.retryCount = 0;
        this.timeoutMillis = 5000; // Default 5-second timeout
    }
    
    /**
     * Constructs a new ServerConnectionException with the specified detail message and cause.
     * 
     * @param message the detail message explaining the connection failure
     * @param cause the underlying cause of the exception (e.g., IOException, SocketException)
     */
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
        this.serverUrl = "etour.example.com";
        this.operation = "comment_modification";
        this.retryCount = 0;
        this.timeoutMillis = 5000;
    }
    
    /**
     * Gets the URL of the ETOUR server that could not be reached.
     * 
     * @return the server URL, or a default if not specified
     */
    public String getServerUrl() {
        return serverUrl;
    }
    
    /**
     * Gets the operation that was being performed when the connection failed.
     * 
     * @return the operation name, or a default if not specified
     */
    public String getOperation() {
        return operation;
    }
    
    /**
     * Gets the number of retry attempts that were made.
     * 
     * @return the retry count
     */
    public int getRetryCount() {
        return retryCount;
    }
    
    /**
     * Gets the timeout value in milliseconds that was used.
     * 
     * @return the timeout in milliseconds
     */
    public long getTimeoutMillis() {
        return timeoutMillis;
    }
    
    /**
     * Gets a user-friendly error message for display to the tourist.
     * Overrides the base implementation to provide connection-specific guidance.
     * 
     * @return a formatted error message suitable for user display
     */
    @Override
    public String getUserFriendlyMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Server Connection Issue\n\n");
        sb.append("We're having trouble connecting to the ETOUR server.\n\n");
        
        sb.append("What happened:\n");
        sb.append("• Could not complete: ").append(operation).append("\n");
        sb.append("• Server: ").append(serverUrl).append("\n");
        
        if (retryCount > 0) {
            sb.append("• Retry attempts: ").append(retryCount).append("\n");
        }
        
        if (getCause() != null) {
            String causeMessage = getCause().getMessage();
            if (causeMessage != null && !causeMessage.isEmpty()) {
                sb.append("• Error details: ").append(causeMessage).append("\n");
            }
        }
        
        sb.append("\nWhat you can do:\n");
        sb.append("1. Check your internet connection\n");
        sb.append("2. Try again in a few moments\n");
        sb.append("3. If the problem persists, contact ETOUR support\n");
        
        return sb.toString();
    }
    
    /**
     * Gets a detailed error message for logging and debugging purposes.
     * This should be logged for system administration and troubleshooting.
     * 
     * @return a detailed error message including connection details
     */
    @Override
    public String getDetailedErrorMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("ServerConnectionException: ").append(getMessage()).append("\n");
        sb.append("Timestamp: ").append(java.time.LocalDateTime.now()).append("\n");
        sb.append("Server URL: ").append(serverUrl).append("\n");
        sb.append("Operation: ").append(operation).append("\n");
        sb.append("Retry Count: ").append(retryCount).append("\n");
        sb.append("Timeout (ms): ").append(timeoutMillis).append("\n");
        
        if (getCause() != null) {
            sb.append("Root Cause: ").append(getCause().getClass().getName()).append("\n");
            sb.append("Cause Message: ").append(getCause().getMessage()).append("\n");
            
            // Include stack trace for the root cause
            StackTraceElement[] stackTrace = getCause().getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                sb.append("Root Cause Stack Trace (first 5 lines):\n");
                for (int i = 0; i < Math.min(5, stackTrace.length); i++) {
                    sb.append("  at ").append(stackTrace[i]).append("\n");
                }
            }
        }
        
        sb.append("System Status: Network connectivity issue detected.");
        
        return sb.toString();
    }
    
    /**
     * Creates a specific ServerConnectionException for a connection timeout.
     * 
     * @param serverUrl the URL of the ETOUR server
     * @param operation the operation that timed out
     * @param timeoutMillis the timeout value that was exceeded
     * @param retryCount the number of retry attempts made
     * @return a ServerConnectionException for a timeout scenario
     */
    public static ServerConnectionException forTimeout(String serverUrl, String operation, 
                                                       long timeoutMillis, int retryCount) {
        return new ServerConnectionException(
            serverUrl,
            operation,
            retryCount,
            timeoutMillis,
            "Connection to ETOUR server timed out after " + timeoutMillis + "ms " +
            "while performing operation: " + operation
        );
    }
    
    /**
     * Creates a specific ServerConnectionException for a refused connection.
     * 
     * @param serverUrl the URL of the ETOUR server
     * @param operation the operation that was attempted
     * @param port the port number that was refused
     * @return a ServerConnectionException for a connection refused scenario
     */
    public static ServerConnectionException forConnectionRefused(String serverUrl, 
                                                                 String operation, int port) {
        return new ServerConnectionException(
            serverUrl,
            operation,
            0,
            5000,
            "Connection to ETOUR server was refused on port " + port + 
            " while performing operation: " + operation
        );
    }
    
    /**
     * Creates a specific ServerConnectionException for a general network error.
     * 
     * @param serverUrl the URL of the ETOUR server
     * @param operation the operation that failed
     * @param cause the underlying network exception
     * @return a ServerConnectionException for a network error scenario
     */
    public static ServerConnectionException forNetworkError(String serverUrl, 
                                                            String operation, Throwable cause) {
        ServerConnectionException exception = new ServerConnectionException(
            serverUrl,
            operation,
            3, // Default retry count
            10000, // Default 10-second timeout
            "Network error while connecting to ETOUR server during operation: " + operation
        );
        exception.initCause(cause);
        return exception;
    }
    
    /**
     * Creates a specific ServerConnectionException for server unavailability.
     * 
     * @param serverUrl the URL of the ETOUR server
     * @param operation the operation that could not be performed
     * @param httpStatus the HTTP status code received, if any
     * @return a ServerConnectionException for server unavailability
     */
    public static ServerConnectionException forServerUnavailable(String serverUrl, 
                                                                 String operation, int httpStatus) {
        String message;
        if (httpStatus > 0) {
            message = "ETOUR server returned HTTP status " + httpStatus + 
                     " while performing operation: " + operation;
        } else {
            message = "ETOUR server is unavailable or not responding during operation: " + operation;
        }
        
        return new ServerConnectionException(
            serverUrl,
            operation,
            2, // Default retry count for server errors
            30000, // Longer timeout for server issues
            message
        );
    }
    
    /**
     * Suggests whether the operation should be retried based on the error type.
     * 
     * @return true if the operation should be retried, false otherwise
     */
    public boolean shouldRetry() {
        // Retry for timeouts and temporary network errors
        // Don't retry for connection refused or permanent server errors
        String message = getMessage().toLowerCase();
        
        if (message.contains("timeout") || message.contains("network error")) {
            return retryCount < 3; // Limit retries
        }
        
        return false;
    }
    
    /**
     * Gets the suggested retry delay in milliseconds.
     * 
     * @return the suggested delay before retrying, or -1 if should not retry
     */
    public long getSuggestedRetryDelay() {
        if (!shouldRetry()) {
            return -1;
        }
        
        // Exponential backoff: 1s, 2s, 4s, etc.
        return (long) Math.pow(2, retryCount) * 1000;
    }
    
    /**
     * Creates a string representation of the exception for debugging.
     * 
     * @return string representation including connection details
     */
    @Override
    public String toString() {
        return "ServerConnectionException{" +
                "serverUrl='" + serverUrl + '\'' +
                ", operation='" + operation + '\'' +
                ", retryCount=" + retryCount +
                ", timeoutMillis=" + timeoutMillis +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}