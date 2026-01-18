'''
A JPanel that provides a form for viewing and editing a tourist's generic personal preferences.
It includes fields for destination, activities, and notification settings, along with
buttons for submitting or canceling changes.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PreferencesPanel extends JPanel {
    private JTextField favoriteDestinationField;
    private JTextArea preferredActivitiesArea;
    private JCheckBox receiveNotificationsCheckBox;
    private JButton submitButton;
    private JButton cancelButton;
    private Tourist currentTourist; // Hold the tourist whose preferences are being edited.
    private PreferenceService preferenceService;
    private JFrame parentFrame; // Reference to the main frame for dialogs
    /**
     * Constructor for the PreferencesPanel.
     * Sets up the GUI components and their initial layout.
     * @param parentFrame The parent JFrame to center dialogs.
     * @param service The PreferenceService instance to interact with the backend logic.
     */
    public PreferencesPanel(JFrame parentFrame, PreferenceService service) {
        this.parentFrame = parentFrame;
        this.preferenceService = service;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall structure, with padding
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel
        // Create a panel for form fields using GridBagLayout for better alignment
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding for components
        // --- Favorite Destination ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Favorite Destination:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        favoriteDestinationField = new JTextField(25); // Set preferred width
        formPanel.add(favoriteDestinationField, gbc);
        // --- Preferred Activities ---
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align label to top-left
        formPanel.add(new JLabel("Preferred Activities:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH; // Allow text area to expand
        gbc.weighty = 1.0; // Give vertical space to text area
        preferredActivitiesArea = new JTextArea(5, 25); // 5 rows, 25 columns
        preferredActivitiesArea.setLineWrap(true);
        preferredActivitiesArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(preferredActivitiesArea);
        formPanel.add(scrollPane, gbc);
        // --- Receive Notifications ---
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0; // Don't give extra vertical space
        receiveNotificationsCheckBox = new JCheckBox("Receive Email Notifications");
        formPanel.add(receiveNotificationsCheckBox, gbc);
        add(formPanel, BorderLayout.CENTER);
        // --- Buttons Panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Align buttons to the right
        submitButton = new JButton("Submit Changes");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // --- Add Action Listeners ---
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 4: Asks for confirmation of the change.
                int confirmationResult = JOptionPane.showConfirmDialog(
                    parentFrame,
                    "Are you sure you want to save these changes?",
                    "Confirm Preference Update",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                // Step 5: Confirm the operation.
                if (confirmationResult == JOptionPane.YES_OPTION) {
                    savePreferences();
                } else {
                    // Exit condition: The Tourist cancels the operation.
                    JOptionPane.showMessageDialog(parentFrame, "Preference modification canceled by user.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                    // Reset the form fields to original values on cancellation from confirmation dialog
                    if (currentTourist != null) {
                        loadPreferences(currentTourist); // Reload original preferences
                    } else {
                        // If currentTourist was null, it means no preferences were loaded initially.
                        // In this case, simply clear the form or leave it as is.
                        // For this use case, we clear it.
                        favoriteDestinationField.setText("");
                        preferredActivitiesArea.setText("");
                        receiveNotificationsCheckBox.setSelected(false);
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit condition: The Tourist cancels the operation immediately.
                JOptionPane.showMessageDialog(parentFrame, "Preference modification canceled by user.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                // Optionally, reset the form to original values or close the window.
                if (currentTourist != null) {
                    loadPreferences(currentTourist); // Reload original preferences
                } else {
                    // If currentTourist was null, it means no preferences were loaded initially.
                    // In this case, simply clear the form or leave it as is.
                    favoriteDestinationField.setText("");
                    preferredActivitiesArea.setText("");
                    receiveNotificationsCheckBox.setSelected(false);
                }
            }
        });
    }
    /**
     * Loads the generic personal preferences of a given Tourist into the form fields.
     * This method is called to populate the form for editing (Step 2 of the flow).
     * @param tourist The Tourist object whose preferences are to be displayed.
     */
    public void loadPreferences(Tourist tourist) {
        this.currentTourist = tourist; // Store the original tourist data, could be null if no preferences found
        if (tourist != null) {
            favoriteDestinationField.setText(tourist.getFavoriteDestination());
            preferredActivitiesArea.setText(tourist.getPreferredActivities());
            receiveNotificationsCheckBox.setSelected(tourist.wantsReceiveNotifications());
            // Enable editing fields if preferences are loaded
            favoriteDestinationField.setEnabled(true);
            preferredActivitiesArea.setEnabled(true);
            receiveNotificationsCheckBox.setEnabled(true);
            submitButton.setEnabled(true);
        } else {
            // If no tourist data is available (e.g., for a new user with no preferences yet)
            favoriteDestinationField.setText("");
            preferredActivitiesArea.setText("");
            receiveNotificationsCheckBox.setSelected(false);
            // Fields should still be editable to allow setting preferences for the first time
            favoriteDestinationField.setEnabled(true);
            preferredActivitiesArea.setEnabled(true);
            receiveNotificationsCheckBox.setEnabled(true);
            submitButton.setEnabled(true);
            // Optionally, we could disable fields until some input is made or always enable for first-time setup
            // For this use case, we assume if the panel is shown, input is expected.
        }
    }
    /**
     * Collects the edited preferences from the form fields and attempts to save them
     * using the PreferenceService.
     * This method corresponds to Step 6 of the flow (Stores preferences changed).
     * It also handles success/failure notifications.
     */
    private void savePreferences() {
        // If currentTourist is null, it means this is a new profile or no preferences were initially set.
        // We need at least an ID and username to create a new Tourist object for saving.
        // For this simulation, we'll assume currentTourist.getId() and currentTourist.getUsername() are available
        // from the authenticated session context, even if other preferences were null.
        if (currentTourist == null) {
            JOptionPane.showMessageDialog(parentFrame, "Error: No authenticated user context to save preferences for.", "Save Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Create a new Tourist object with the updated preferences from the form.
        // We reuse the ID and username from the current (original) tourist object.
        Tourist updatedTourist = new Tourist(
            currentTourist.getId(),
            currentTourist.getUsername(), // Or fetch from the authenticated session if not in currentTourist
            favoriteDestinationField.getText(),
            preferredActivitiesArea.getText(),
            receiveNotificationsCheckBox.isSelected()
        );
        // Perform the update via the service in a separate thread to keep GUI responsive
        // and simulate network operations.
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Simulate network operation
                preferenceService.updatePreferences(updatedTourist);
                return null;
            }
            @Override
            protected void done() {
                try {
                    get(); // This will re-throw any exceptions from doInBackground
                    // Update currentTourist reference after successful save to reflect latest state.
                    // This is crucial if preferences were previously null and are now set.
                    currentTourist = updatedTourist;
                    // Exit condition: The system shall notify the successful modification.
                    JOptionPane.showMessageDialog(parentFrame, "Generic preferences successfully modified!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    // Handle exceptions from doInBackground (e.g., ServiceConnectionException)
                    Throwable cause = ex.getCause() != null ? ex.getCause() : ex; // Get actual cause if wrapped
                    // Exit condition: Interruption of the connection to the server ETOUR.
                    JOptionPane.showMessageDialog(parentFrame, "Connection error: " + cause.getMessage() + "\nPlease try again.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Any post-execution logic if needed (e.g., re-enable buttons)
                }
            }
        }.execute();
    }
}