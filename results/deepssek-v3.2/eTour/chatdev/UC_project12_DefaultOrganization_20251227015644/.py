/**
 * Utility class for handling server-related operations and error messages.
 * This provides a centralized way to manage server interactions and user notifications.
 */
import javax.swing.*;
public class ServerUtils {
    /**
     * Attempts to fetch tourist data from the server with error handling.
     * Displays appropriate messages to the user in case of success or failure.
     * @param parentComponent The parent component for dialogs (can be null).
     * @return A list of Tourist objects, or null if the operation failed.
     */
    public static java.util.List<Tourist> fetchTouristDataWithRetry(Component parentComponent) {
        int maxRetries = 3;
        int attempt = 0;
        while (attempt < maxRetries) {
            attempt++;
            try {
                java.util.List<Tourist> tourists = ServerConnectionSimulator.fetchTouristData();
                JOptionPane.showMessageDialog(parentComponent, 
                    "Data fetched successfully from ETOUR server.", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                return tourists;
            } catch (ConnectionInterruptedException e) {
                int option = JOptionPane.showConfirmDialog(parentComponent, 
                    "Attempt " + attempt + " failed: " + e.getMessage() + "\nRetry?", 
                    "Connection Interrupted", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.ERROR_MESSAGE);
                if (option != JOptionPane.YES_OPTION) {
                    break;
                }
            }
        }
        JOptionPane.showMessageDialog(parentComponent, 
            "Failed to fetch data after " + maxRetries + " attempts. Please check your connection.", 
            "Connection Error", 
            JOptionPane.ERROR_MESSAGE);
        return null;
    }
}