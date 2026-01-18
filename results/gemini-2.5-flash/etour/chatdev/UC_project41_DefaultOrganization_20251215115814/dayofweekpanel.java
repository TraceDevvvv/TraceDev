/*
 * A JPanel specifically designed to display and edit the menu for a single day of the week.
 * It integrates with the RestaurantDataStore to load and save menu data.
 * This panel is responsible for steps 3 (partially), 4, 5, 6, 7, 8 of the use case.
 * All long-running data operations (load/save) are performed using SwingWorker to keep the UI responsive.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
public class DayOfWeekPanel extends JPanel {
    private String dayName;
    private RestaurantDataStore dataStore;
    protected JTextArea menuTextArea; // Changed to protected for MenuManagerApp's internal access (for tab switch logic)
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel loadingStatusLabel; // New label for loading status
    private JLabel savingStatusLabel;  // New label for saving status
    // Stores the last loaded or successfully saved menu content to facilitate cancelling edits and checking modifications.
    private String originalMenuContent;
    // Listener to communicate save completion specifically during tab switches back to MenuManagerApp.
    private TabSaveCompleteListener tabSaveCompleteListener;
    /**
     * Constructor for DayOfWeekPanel.
     * @param dayName The name of the day this panel represents (e.g., "MONDAY").
     * @param dataStore The data store to interact with for menu operations.
     */
    public DayOfWeekPanel(String dayName, RestaurantDataStore dataStore) {
        this.dayName = dayName;
        this.dataStore = dataStore;
        this.originalMenuContent = ""; // Initialize empty, will be set by loadMenuData().
        initializePanel();
    }
    /**
     * Sets the listener for tab save completion events.
     * @param listener The listener to be set.
     */
    public void setTabSaveCompleteListener(TabSaveCompleteListener listener) {
        this.tabSaveCompleteListener = listener;
    }
    /**
     * Returns the name of the day this panel represents.
     * @return The day name.
     */
    public String getDayName() {
        return dayName;
    }
    /**
     * Sets up the layout and components for the panel.
     */
    private void initializePanel() {
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for a clear structure.
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding.
        // Top panel for title and status labels.
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Edit Menu for " + dayName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        topPanel.add(titleLabel, BorderLayout.NORTH);
        // Status labels for loading and saving.
        loadingStatusLabel = new JLabel("Loading menu...", SwingConstants.CENTER);
        loadingStatusLabel.setForeground(Color.BLUE);
        loadingStatusLabel.setVisible(false); // Hidden by default.
        savingStatusLabel = new JLabel("Saving changes...", SwingConstants.CENTER);
        savingStatusLabel.setForeground(Color.ORANGE);
        savingStatusLabel.setVisible(false); // Hidden by default.
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        statusPanel.add(loadingStatusLabel);
        statusPanel.add(savingStatusLabel);
        topPanel.add(statusPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        // Text area for menu editing (Step 5: Edit menu).
        menuTextArea = new JTextArea(15, 40); // 15 rows, 40 columns initial size.
        menuTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        menuTextArea.setLineWrap(true);
        menuTextArea.setWrapStyleWord(true); // Wrap at word boundaries.
        JScrollPane scrollPane = new JScrollPane(menuTextArea);
        add(scrollPane, BorderLayout.CENTER);
        // Panel for buttons.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");
        // Add action listeners.
        saveButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Sets the UI state to indicate whether data is currently being loaded.
     * Disables/enables input components and shows/hides the loading status label.
     * @param isLoading true if data is loading, false otherwise.
     */
    private void setLoading(boolean isLoading) {
        menuTextArea.setEnabled(!isLoading);
        saveButton.setEnabled(!isLoading);
        cancelButton.setEnabled(!isLoading);
        loadingStatusLabel.setVisible(isLoading);
        // If loading starts, also hide saving status, and vice versa if loading finishes.
        if (isLoading) savingStatusLabel.setVisible(false);
    }
    /**
     * Sets the UI state to indicate whether data is currently being saved.
     * Disables/enables input components and shows/hides the saving status label.
     * @param isSaving true if data is saving, false otherwise.
     */
    private void setSaving(boolean isSaving) {
        menuTextArea.setEnabled(!isSaving);
        saveButton.setEnabled(!isSaving);
        cancelButton.setEnabled(!isSaving);
        savingStatusLabel.setVisible(isSaving);
        // If saving starts, also hide loading status, and vice versa if saving finishes.
        if (isSaving) loadingStatusLabel.setVisible(false);
    }
    /**
     * Loads the menu data for this specific day from the data store
     * and populates the text area using a SwingWorker to keep the UI responsive.
     * This method fulfills step 4 of the use case.
     */
    public void loadMenuData() {
        setLoading(true); // Indicate that loading has started.
        menuTextArea.setText("Loading menu for " + dayName + "..."); // Provide immediate feedback.
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                // Perform the potentially long-running data load operation on a background thread.
                return dataStore.loadMenu(dayName);
            }
            @Override
            protected void done() {
                setLoading(false); // Indicate that loading has finished.
                try {
                    String loadedContent = get(); // Get the result from doInBackground().
                    originalMenuContent = loadedContent; // Update the baseline for changes.
                    menuTextArea.setText(loadedContent);
                    menuTextArea.setCaretPosition(0); // Scroll to the top if content is long.
                    System.out.println("Panel for " + dayName + ": Data loaded and displayed.");
                } catch (InterruptedException | ExecutionException ex) {
                    // Handle exceptions that occurred during loading.
                    JOptionPane.showMessageDialog(DayOfWeekPanel.this,
                            "Failed to load menu for " + dayName + ": " + ex.getMessage(),
                            "Error Loading Menu",
                            JOptionPane.ERROR_MESSAGE);
                    menuTextArea.setText("Error loading menu. Please try again.");
                    System.err.println("Error loading menu for " + dayName + ": " + ex.getMessage());
                }
            }
        }.execute(); // Execute the SwingWorker.
    }
    /**
     * Checks if the menu content in the text area has been modified
     * compared to the last loaded or saved version.
     * @return true if content is different, false otherwise.
     */
    public boolean isModified() {
        return !menuTextArea.getText().trim().equals(originalMenuContent.trim());
    }
    /**
     * Attempts to save the current menu content. This method handles validation,
     * user confirmation, and initiates an asynchronous save operation using SwingWorker.
     * This fulfills steps 6, 7, 8 for general save button interaction.
     */
    private void saveCurrentMenu() {
        final String currentMenuContent = menuTextArea.getText().trim();
        // Step 6: Verify the data entered (synchronous validation).
        if (currentMenuContent.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Menu content cannot be empty. Please enter some dishes.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return; // Data is insufficient.
        }
        // Check if there are any changes compared to the last saved/loaded content.
        if (!isModified()) {
            JOptionPane.showMessageDialog(this,
                    "No changes detected for " + dayName + " menu. Nothing to save.",
                    "No Changes",
                    JOptionPane.INFORMATION_MESSAGE);
            return; // No changes, no save needed.
        }
        // Ask for confirmation (Step 6 part 2, synchronous dialog).
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to save these changes for " + dayName + " menu?",
                "Confirm Save",
                JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) { // Step 7: Confirm the operation.
            // User confirmed, now initiate the asynchronous save operation.
            performSaveAsync(currentMenuContent);
        } else {
            // Operator cancels the confirmation dialog.
            JOptionPane.showMessageDialog(this,
                    "Save operation cancelled by user for " + dayName + " menu.",
                    "Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Initiates an asynchronous save operation using SwingWorker.
     * This is an internal helper called by saveCurrentMenu() and saveMenuForTabSwitch().
     * @param content The menu content to save.
     * @param targetTabIndex The tab index to switch to after save, or -1 if not switching tabs.
     */
    private void performSaveAsync(final String content, final int targetTabIndex) {
        setSaving(true); // Indicate that saving has started.
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                // Perform the potentially long-running data save operation on a background thread.
                return dataStore.saveMenu(dayName, content);
            }
            @Override
            protected void done() {
                setSaving(false); // Indicate that saving has finished.
                try {
                    boolean success = get(); // Get the result from doInBackground().
                    if (success) {
                        originalMenuContent = content; // Update original content after successful save.
                        // Exit condition: successfully modified.
                        JOptionPane.showMessageDialog(DayOfWeekPanel.this,
                                "Menu for " + dayName + " successfully modified!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        if (tabSaveCompleteListener != null && targetTabIndex != -1) {
                            tabSaveCompleteListener.onSaveSuccess(dayName, targetTabIndex);
                        }
                    } else {
                        // Exit condition: Interruption of the connection to the server ETOUR (simulated).
                        JOptionPane.showMessageDialog(DayOfWeekPanel.this,
                                "Failed to save menu for " + dayName + ": Connection interrupted or server error.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        if (tabSaveCompleteListener != null && targetTabIndex != -1) {
                            tabSaveCompleteListener.onSaveFailure(dayName, targetTabIndex);
                        }
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    // Handle exceptions that occurred during saving.
                    JOptionPane.showMessageDialog(DayOfWeekPanel.this,
                            "An error occurred during save: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    System.err.println("Error saving menu for " + dayName + ": " + ex.getMessage());
                    if (tabSaveCompleteListener != null && targetTabIndex != -1) {
                        tabSaveCompleteListener.onSaveFailure(dayName, targetTabIndex);
                    }
                }
            }
        }.execute(); // Execute the SwingWorker.
    }
    /**
     * Overloaded version of performSaveAsync for general save button interaction
     * where no tab switching is involved (targetTabIndex is -1).
     */
    private void performSaveAsync(final String content) {
        performSaveAsync(content, -1);
    }
    /**
     * This method is specifically called when switching tabs and the previous tab
     * has unsaved changes that the user chose to save. It performs validation,
     * confirmation, and then triggers an asynchronous save, notifying the
     * `TabSaveCompleteListener` upon completion.
     * @param targetTabIndex The index of the tab to switch to upon successful save.
     */
    public void saveMenuForTabSwitch(final int targetTabIndex) {
        final String currentMenuContent = menuTextArea.getText().trim();
        // Synchronous validation.
        if (currentMenuContent.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Menu content cannot be empty. Please enter some dishes.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            if (tabSaveCompleteListener != null) {
                tabSaveCompleteListener.onSaveFailure(dayName, targetTabIndex);
            }
            return;
        }
        if (!isModified()) {
            // No changes, no save actually needed, proceed as if saved successfully.
            JOptionPane.showMessageDialog(this,
                    "No changes detected for " + dayName + " menu.",
                    "No Changes",
                    JOptionPane.INFORMATION_MESSAGE);
            if (tabSaveCompleteListener != null) {
                tabSaveCompleteListener.onSaveSuccess(dayName, targetTabIndex);
            }
            return;
        }
        // Synchronous confirmation for saving before tab switch.
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Save changes for " + dayName + " menu before switching tabs?",
                "Confirm Save",
                JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            // User confirmed, start asynchronous save with callback to trigger tab switch.
            performSaveAsync(currentMenuContent, targetTabIndex);
        } else {
            // User cancelled confirmation, inform the listener to prevent tab switch.
            JOptionPane.showMessageDialog(this,
                    "Save operation cancelled by user.",
                    "Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
            if (tabSaveCompleteListener != null) {
                tabSaveCompleteListener.onSaveCancelled(dayName, targetTabIndex);
            }
        }
    }
    /**
     * Discards any changes made in the text area without asking for confirmation.
     * Used when the decision to discard has already been made (e.g., by the main application logic
     * during a tab switch, or after a user confirms discarding changes via a dialog).
     */
    public void discardChangesSilent() {
        menuTextArea.setText(originalMenuContent); // Revert to original content.
        JOptionPane.showMessageDialog(this,
                "Changes for " + dayName + " menu have been discarded.",
                "Discarded",
                JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Changes for " + dayName + " discarded silently.");
    }
    /**
     * Internal action listener for the buttons.
     */
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("SAVE".equals(command)) {
                saveCurrentMenu(); // Initiates validation, confirmation, and async save.
            } else if ("CANCEL".equals(command)) {
                // If there are unsaved changes, ask for confirmation to discard.
                if (isModified()) {
                    int confirmation = JOptionPane.showConfirmDialog(DayOfWeekPanel.this,
                            "Discard all unsaved changes for " + dayName + " menu?",
                            "Confirm Cancel",
                            JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        discardChangesSilent(); // Revert to original content.
                    }
                } else {
                    JOptionPane.showMessageDialog(DayOfWeekPanel.this,
                            "No changes to discard for " + dayName + " menu.",
                            "No Changes",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}