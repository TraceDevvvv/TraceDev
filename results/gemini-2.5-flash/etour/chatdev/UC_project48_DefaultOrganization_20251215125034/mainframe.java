'''
The main application window (JFrame) that hosts the PreferencesPanel.
It sets up the basic window properties and integrates the preference editing functionality.
'''
import javax.swing.*;
import java.awt.*;
public class MainFrame extends JFrame {
    private PreferenceService preferenceService;
    private String authenticatedTouristId; // Stores the ID of the currently authenticated tourist
    /**
     * Constructor for MainFrame.
     * Initializes the JFrame, its components, and loads initial preferences.
     * @param service The PreferenceService instance for backend operations.
     * @param touristId The ID of the authenticated tourist whose preferences will be edited.
     */
    public MainFrame(PreferenceService service, String touristId) {
        this.preferenceService = service;
        this.authenticatedTouristId = touristId;
        setTitle("ETOUR - Edit Generic Personal Preferences");
        setSize(500, 400); // Set a default size for the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        // Step 1: Access to functionality for the modification of generic personal preferences.
        // The main frame itself provides this access by displaying the preferences panel.
        PreferencesPanel preferencesPanel = new PreferencesPanel(this, preferenceService);
        add(preferencesPanel, BorderLayout.CENTER);
        // Initially disable fields until preferences are loaded to prevent editing an empty form prematurely
        preferencesPanel.setEnabled(false); // Disables the panel and its children for now by convention.
                                          // Note: For a JPanel, it controls the enabled state of its children.
                                          // It's more robust to specifically control sub-components or overlay with a wait cursor.
        // Simulate fetching initial preferences on application start
        // This is part of Step 2: Upload your preferences and the general view in a form.
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // Show busy cursor
        new SwingWorker<Tourist, Void>() {
            @Override
            protected Tourist doInBackground() throws Exception {
                // Simulate authentication condition: Tourist has successfully authenticated.
                // We assume 'authenticatedTouristId' is valid after successful authentication.
                return preferenceService.getPreferences(authenticatedTouristId);
            }
            @Override
            protected void done() {
                try {
                    Tourist tourist = get(); // Get result from doInBackground
                    // Handle case where preferences data is not found (tourist is null) or was found.
                    if (tourist == null) {
                        // This case can happen for a new user who has not set preferences yet,
                        // or if an authenticated ID surprisingly has no data.
                        // For a blank slate, we want to allow the user to set preferences.
                        // Instead of disposing, we load 'empty' preferences.
                        // However, if the error is due to "no data found for this ID" being critical
                        // and not just 'unset', then dispose might be appropriate.
                        // Based on the comment, it seems to imply a critical initial data loading failure.
                        // Re-evaluating: The original `PreferencePanel.loadPreferences` can handle `null`
                        // gracefully by displaying an empty form, allowing the user to *set* preferences.
                        // The comment implies an *inconsistent* state if the frame remains open with nothing to do.
                        // If no preferences are found *for an authenticated user*, it's an exceptional case if
                        // the system requires initial preferences. For a `MODIFICAPREFERENZEGENERICHE` use case,
                        // it implies preferences *exist* to be modified. If they don't, it might be a different flow.
                        // For consistency, if we cannot load *any* preferences for an authenticated ID,
                        // indicating a problem with the user's data state, we should exit.
                        // If it's merely 'empty preferences', then `preferencesPanel.loadPreferences(null)` should suffice.
                        // Let's assume for this specific use case, not finding preferences for an existing user ID is critical
                        // *unless* explicitly designed to create new ones from a blank form.
                        // As the task is "Edit Preferences", if there's nothing to edit, it's a failure for this use case.
                        JOptionPane.showMessageDialog(MainFrame.this,
                                "Failed to load preferences: No existing preferences found for authenticated user ID '" + authenticatedTouristId + "'.\nPlease contact support.",
                                "Loading Error", JOptionPane.ERROR_MESSAGE);
                        MainFrame.this.dispose(); // Close the window as no preferences can be displayed/edited.
                    } else {
                        preferencesPanel.loadPreferences(tourist);
                    }
                } catch (Exception ex) {
                    // Handle exceptions from getPreferences, e.g., ServiceConnectionException
                    Throwable cause = ex.getCause() != null ? ex.getCause() : ex; // Get actual cause if wrapped
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Failed to load preferences: " + cause.getMessage() + "\nPlease restart the application.",
                            "Loading Error", JOptionPane.ERROR_MESSAGE);
                    // Critical error, close application or disable functionality
                    MainFrame.this.dispose(); // Close the window
                } finally {
                    setCursor(Cursor.getDefaultCursor()); // Restore default cursor
                    // preferencesPanel.setEnabled(true); // Re-enable the panel, done by loadPreferences too implicitly
                }
            }
        }.execute();
    }
}