/*
Utility class for setting up and providing a centralized Logger instance
for the registration system.
This class corresponds to Step 1: "Enable the logging feature."
*/
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class RegistrationLogger {
    private static final Logger logger = Logger.getLogger("RegistrationSystem");
    static {
        setupLogger(); // Configure the logger when the class is loaded
    }
    /*
     Configures the global Logger for the application.
     Sets logging level, adds handlers for console and file output.
    */
    private static void setupLogger() {
        // Prevent the default console handler from displaying messages twice
        logger.setUseParentHandlers(false);
        // Set the logging level for the logger
        logger.setLevel(Level.ALL); // Log all messages
        // Console Handler: for displaying logs in the console
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter()); // Simple text format
        consoleHandler.setLevel(Level.INFO); // Only show INFO and above in console
        logger.addHandler(consoleHandler);
        // File Handler: for writing logs to a file
        try {
            // Log file will be named "registration_system.log".
            // The 'true' argument means append to the existing file if it exists.
            FileHandler fileHandler = new FileHandler("registration_system.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL); // Log all messages to file
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            // Log an error if the file handler cannot be created
            logger.log(Level.SEVERE, "Could not set up file logger.", e);
        }
        logger.info("Logging feature enabled and configured.");
    }
    /*
     Returns the configured Logger instance.
     @return The Logger instance for the RegistrationSystem.
    */
    public static Logger getLogger() {
        return logger;
    }
}