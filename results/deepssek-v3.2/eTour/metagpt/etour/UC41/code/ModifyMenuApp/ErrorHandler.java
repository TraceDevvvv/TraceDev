import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * ErrorHandler.java
 * Utility class for handling and logging errors in the restaurant menu modification system.
 * Provides centralized error handling with different severity levels and logging capabilities.
 * Implements the Singleton pattern to ensure consistent error handling throughout the application.
 */
public class ErrorHandler {
    
    // Singleton instance
    private static ErrorHandler instance;
    
    // Logger instance
    private Logger logger;
    
    // Log file configuration
    private static final String LOG_FILE = "menu_modification.log";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    // Error severity levels
    public enum Severity {
        INFO,      // Informational messages
        WARNING,   // Warning messages that don't stop execution
        ERROR,     // Error messages that may affect functionality
        CRITICAL,  // Critical errors that stop execution
        ETOUR      // Server interruption errors (from use case)
    }
    
    /**
     * Private constructor for Singleton pattern.
     * Initializes the logger and file handler.
     */
    private ErrorHandler() {
        try {
            // Initialize logger
            logger = Logger.getLogger(ErrorHandler.class.getName());
            logger.setUseParentHandlers(false); // Disable default console handler
            
            // Create file handler for logging to file
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    return DATE_FORMAT.format(new Date(record.getMillis())) + " " +
                           record.getLevel() + ": " + 
                           record.getMessage() + "\n";
                }
            });
            logger.addHandler(fileHandler);
            
            // Also add console handler for immediate feedback
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    String prefix;
                    switch (record.getLevel().getName()) {
                        case "SEVERE":
                            prefix = "✗ CRITICAL: ";
                            break;
                        case "WARNING":
                            prefix = "⚠ WARNING: ";
                            break;
                        case "INFO":
                            prefix = "ℹ INFO: ";
                            break;
                        default:
                            prefix = record.getLevel() + ": ";
                    }
                    return prefix + record.getMessage() + "\n";
                }
            });
            logger.addHandler(consoleHandler);
            
            // Set log level
            logger.setLevel(Level.ALL);
            
        } catch (IOException e) {
            System.err.println("Failed to initialize ErrorHandler: " + e.getMessage());
            // Fallback to System.err if file logging fails
            logger = Logger.getLogger(ErrorHandler.class.getName());
        }
    }
    
    /**
     * Returns the singleton instance of ErrorHandler.
     * 
     * @return ErrorHandler instance
     */
    public static synchronized ErrorHandler getInstance() {
        if (instance == null) {
            instance = new ErrorHandler();
        }
        return instance;
    }
    
    /**
     * Logs an error with specified severity and message.
     * 
     * @param severity Error severity level
     * @param message Error message
     * @param sourceClass Source class where error occurred
     */
    public void logError(Severity severity, String message, String sourceClass) {
        logError(severity, message, sourceClass, null);
    }
    
    /**
     * Logs an error with specified severity, message, and exception.
     * 
     * @param severity Error severity level
     * @param message Error message
     * @param sourceClass Source class where error occurred
     * @param exception Exception that caused the error (can be null)
     */
    public void logError(Severity severity, String message, String sourceClass, Exception exception) {
        String formattedMessage = "[" + sourceClass + "] " + message;
        
        switch (severity) {
            case INFO:
                logger.log(Level.INFO, formattedMessage);
                break;
            case WARNING:
                logger.log(Level.WARNING, formattedMessage);
                System.out.println("⚠ " + message);
                break;
            case ERROR:
                logger.log(Level.SEVERE, formattedMessage);
                System.err.println("✗ ERROR: " + message);
                break;
            case CRITICAL:
                logger.log(Level.SEVERE, "CRITICAL: " + formattedMessage);
                System.err.println("✗✗ CRITICAL ERROR: " + message);
                if (exception != null) {
                    System.err.println("Exception: " + exception.getMessage());
                }
                break;
            case ETOUR:
                logger.log(Level.SEVERE, "ETOUR (Server Interruption): " + formattedMessage);
                System.err.println("⚡ SERVER INTERRUPTION (ETOUR): " + message);
                System.err.println("Please check your connection and try again.");
                break;
        }
        
        // Log exception stack trace if provided
        if (exception != null) {
            logger.log(Level.SEVERE, "Exception details:", exception);
            
            // Print simplified exception info to console for user
            if (severity == Severity.CRITICAL || severity == Severity.ETOUR) {
                System.err.println("Technical details: " + exception.getClass().getSimpleName() + 
                                 " - " + exception.getMessage());
            }
        }
    }
    
    /**
     * Handles authentication errors with appropriate messaging.
     * 
     * @param operatorId Operator ID that failed authentication
     * @param attemptsRemaining Number of authentication attempts remaining
     */
    public void handleAuthenticationError(String operatorId, int attemptsRemaining) {
        String message = "Authentication failed for operator ID: " + operatorId;
        if (attemptsRemaining > 0) {
            message += ". Attempts remaining: " + attemptsRemaining;
            logError(Severity.WARNING, message, "RestaurantOperator");
        } else {
            message += ". No attempts remaining.";
            logError(Severity.ERROR, message, "RestaurantOperator");
        }
    }
    
    /**
     * Handles menu validation errors.
     * 
     * @param dayOfWeek Day for which validation failed
     * @param validationErrors Validation error messages
     */
    public void handleValidationError(String dayOfWeek, String validationErrors) {
        String message = "Menu validation failed for " + dayOfWeek + ":\n" + validationErrors;
        logError(Severity.ERROR, message, "ValidationUtility");
    }
    
    /**
     * Handles menu save errors including server interruptions (ETOUR).
     * 
     * @param dayOfWeek Day for which save failed
     * @param exception Exception that occurred during save
     */
    public void handleSaveError(String dayOfWeek, Exception exception) {
        String message = "Failed to save menu for " + dayOfWeek;
        
        if (exception != null && exception.getMessage() != null && 
            exception.getMessage().contains("ETOUR")) {
            logError(Severity.ETOUR, message, "MenuManager", exception);
        } else {
            logError(Severity.ERROR, message, "MenuManager", exception);
        }
    }
    
    /**
     * Handles user input validation errors.
     * 
     * @param inputType Type of input that failed validation
     * @param invalidValue The invalid value entered
     * @param expectedFormat Expected format or valid range
     */
    public void handleInputError(String inputType, String invalidValue, String expectedFormat) {
        String message = "Invalid " + inputType + " input: '" + invalidValue + 
                        "'. Expected: " + expectedFormat;
        logError(Severity.WARNING, message, "ModifyMenuProgram");
    }
    
    /**
     * Handles operation cancellation by user.
     * 
     * @param operationType Type of operation cancelled
     * @param operatorName Name of operator who cancelled
     */
    public void handleCancellation(String operationType, String operatorName) {
        String message = operationType + " cancelled by operator: " + operatorName;
        logError(Severity.INFO, message, "ModifyMenuProgram");
    }
    
    /**
     * Handles successful operations for logging purposes.
     * 
     * @param operationType Type of successful operation
     * @param details Additional details about the operation
     */
    public void handleSuccess(String operationType, String details) {
        String message = "SUCCESS: " + operationType + " - " + details;
        logError(Severity.INFO, message, "ModifyMenuProgram");
    }
    
    /**
     * Creates a user-friendly error message for display to the operator.
     * 
     * @param severity Error severity
     * @param technicalMessage Technical error message
     * @return User-friendly error message
     */
    public String getUserFriendlyMessage(Severity severity, String technicalMessage) {
        switch (severity) {
            case INFO:
                return "Information: " + technicalMessage;
            case WARNING:
                return "Warning: " + technicalMessage;
            case ERROR:
                return "Error: " + technicalMessage + ". Please try again.";
            case CRITICAL:
                return "Critical Error: " + technicalMessage + ". Please contact system administrator.";
            case ETOUR:
                return "Server Connection Error: " + technicalMessage + ". Please check your connection and try again.";
            default:
                return "Error: " + technicalMessage;
        }
    }
    
    /**
     * Logs the start of an operation for auditing purposes.
     * 
     * @param operationName Name of the operation
     * @param operatorName Name of operator performing operation
     */
    public void logOperationStart(String operationName, String operatorName) {
        String message = "Operation START: " + operationName + " by operator: " + operatorName;
        logger.log(Level.INFO, message);
    }
    
    /**
     * Logs the completion of an operation for auditing purposes.
     * 
     * @param operationName Name of the operation
     * @param operatorName Name of operator performing operation
     * @param success Whether operation was successful
     */
    public void logOperationEnd(String operationName, String operatorName, boolean success) {
        String status = success ? "SUCCESS" : "FAILED";
        String message = "Operation END: " + operationName + " by operator: " + 
                        operatorName + " - Status: " + status;
        logger.log(Level.INFO, message);
    }
    
    /**
     * Returns the path to the log file.
     * 
     * @return Log file path
     */
    public String getLogFilePath() {
        return LOG_FILE;
    }
    
    /**
     * Clears the log file (use with caution, mainly for testing).
     * 
     * @return true if successful, false otherwise
     */
    public boolean clearLogFile() {
        try {
            File logFile = new File(LOG_FILE);
            if (logFile.exists()) {
                return logFile.delete();
            }
            return true;
        } catch (SecurityException e) {
            logError(Severity.ERROR, "Security exception when clearing log file: " + e.getMessage(), 
                    "ErrorHandler", e);
            return false;
        }
    }
    
    /**
     * Returns the contents of the log file for debugging purposes.
     * 
     * @return Log file contents as string
     */
    public String getLogContents() {
        try {
            File logFile = new File(LOG_FILE);
            if (!logFile.exists()) {
                return "Log file does not exist yet.";
            }
            
            StringBuilder contents = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    contents.append(line).append("\n");
                }
            }
            return contents.toString();
        } catch (IOException e) {
            logError(Severity.ERROR, "Failed to read log file: " + e.getMessage(), 
                    "ErrorHandler", e);
            return "Error reading log file: " + e.getMessage();
        }
    }
    
    /**
     * Closes all handlers and releases resources.
     * Should be called when application is shutting down.
     */
    public void shutdown() {
        for (Handler handler : logger.getHandlers()) {
            handler.close();
        }
        logger.log(Level.INFO, "ErrorHandler shutdown completed");
    }
    
    /**
     * Emergency error handler for when the main ErrorHandler fails.
     * This provides basic error handling even if the main ErrorHandler cannot be initialized.
     * 
     * @param severity Error severity
     * @param message Error message
     * @param sourceClass Source class
     */
    public static void emergencyError(Severity severity, String message, String sourceClass) {
        String timestamp = DATE_FORMAT.format(new Date());
        String logMessage = timestamp + " EMERGENCY [" + sourceClass + "] " + 
                           severity + ": " + message;
        
        // Always print to stderr
        System.err.println(logMessage);
        
        // Try to write to emergency log file
        try (FileWriter writer = new FileWriter("emergency.log", true)) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            // Last resort: print to stderr
            System.err.println("Failed to write to emergency log: " + e.getMessage());
        }
    }
}