'''
The main entry point for the "MODIFICAPREFERENZEGENERICHE" application.
It initializes the PreferenceService, simulates tourist authentication,
and sets up the main GUI window.
'''
import javax.swing.*;
public class ModificaPreferenzeApp {
    public static void main(String[] args) {
        // Initialize the PreferenceService
        PreferenceService preferenceService = new PreferenceService();
        // Simulate successful authentication.
        // Entry condition: Tourism has successfully authenticated to the system.
        // For demonstration, we'll use a hardcoded tourist ID and username.
        String authenticatedUsername = "JohnDoe"; // Try "JohnDoe" (has preferences) or "NewUser" (null preferences)
        String authenticatedTouristId = null; // Will be set by a successful authentication call
        // Authenticate and get the tourist ID
        authenticatedTouristId = preferenceService.authenticate(authenticatedUsername, "dummyPassword");
        // For testing the `null` preference load scenario:
        // authenticatedTouristId = preferenceService.authenticate("NewUser", "dummyPassword");
        if (authenticatedTouristId == null) { // Check if authentication failed (authenticate returns null)
            JOptionPane.showMessageDialog(null, "Authentication failed for user: " + authenticatedUsername,
                                          "Authentication Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if authentication fails
        }
        System.out.println("User '" + authenticatedUsername + "' (ID: " + authenticatedTouristId + ") successfully authenticated.");
        // Create and show the GUI on the Event Dispatch Thread (EDT) for thread safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create the main application frame, passing the service and authenticated tourist ID.
                MainFrame frame = new MainFrame(preferenceService, authenticatedTouristId);
                frame.setVisible(true);
            }
        });
    }
}