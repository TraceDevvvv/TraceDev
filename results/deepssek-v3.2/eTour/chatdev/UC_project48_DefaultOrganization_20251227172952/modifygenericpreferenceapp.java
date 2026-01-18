/**
 * Main application entry point for ModifyGenericPreference system.
 * This application allows authenticated tourists to modify their generic personal preferences.
 */
package modifygenericpreference;
import javax.swing.*;
public class ModifyGenericPreferenceApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Ensure username is provided (in a real system, this would come from authentication)
                String username = "tourist123";
                if (username == null || username.trim().isEmpty()) {
                    throw new IllegalArgumentException("Username cannot be null or empty");
                }
                Tourist authenticatedTourist = new Tourist(username, "John Doe", "john@example.com");
                MainFrame mainFrame = new MainFrame(authenticatedTourist);
                mainFrame.setVisible(true);
                System.out.println("ModifyGenericPreference application started successfully.");
            } catch (IllegalArgumentException e) {
                // Log and display error safely on the Event Dispatch Thread
                System.err.println("Invalid input: " + e.getMessage());
                JOptionPane.showMessageDialog(null,
                    "Startup Error: " + e.getMessage(),
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                // Catch any other unexpected exceptions
                System.err.println("Unexpected error: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                    "Error starting application: " + e.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}