/**
 * Main class to run the Registration application.
 * This class initializes the logging system and starts the registration form.
 */
import javax.swing.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Registration {
    private static final Logger logger = Logger.getLogger(Registration.class.getName());
    static {
        // Configure logging (step 1: Enable the logging feature)
        setupLogging();
    }
    /**
     * Setup logging configuration for the application
     */
    private static void setupLogging() {
        try {
            // Get the root logger
            Logger rootLogger = Logger.getLogger("");
            // Remove default handlers
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
                rootLogger.removeHandler(handler);
            }
            // Create console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            // Create a simple formatter
            consoleHandler.setFormatter(new java.util.logging.SimpleFormatter() {
                @Override
                public String format(java.util.logging.LogRecord record) {
                    return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS [%2$s] %3$s%n",
                            record.getMillis(),
                            record.getLevel().getName(),
                            record.getMessage());
                }
            });
            // Add handler to root logger
            rootLogger.addHandler(consoleHandler);
            rootLogger.setLevel(Level.INFO);
            logger.info("Logging system initialized successfully.");
        } catch (Exception e) {
            System.err.println("Failed to initialize logging: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        // Log application start
        logger.info("Registration application starting...");
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(() -> {
            try {
                // Set look and feel to system default for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                // Create and display the registration form (step 2: View the registration form)
                RegistrationForm form = new RegistrationForm();
                form.setVisible(true);
                logger.info("Registration form displayed successfully.");
            } catch (Exception e) {
                logger.severe("Failed to start registration application: " + e.getMessage());
                JOptionPane.showMessageDialog(null,
                        "Failed to start the registration system:\n" + e.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}