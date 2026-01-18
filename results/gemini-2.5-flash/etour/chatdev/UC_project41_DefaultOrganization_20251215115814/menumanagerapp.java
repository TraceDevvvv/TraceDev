/*
 * Main application class for the Restaurant Menu Management system.
 * This class sets up the graphical user interface using Swing,
 * allowing an operator to change daily menus.
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
/**
 * Interface to handle completion of a tab-switching save operation.
 * Used by DayOfWeekPanel to communicate back to MenuManagerApp.
 */
interface TabSaveCompleteListener {
    void onSaveSuccess(String dayName, int targetTabIndex);
    void onSaveFailure(String dayName, int targetTabIndex); // Includes validation/connection errors
    void onSaveCancelled(String dayName, int targetTabIndex); // User cancelled confirmation during save
}
public class MenuManagerApp extends JFrame {
    private RestaurantDataStore dataStore;
    private JTabbedPane tabbedPane;
    private Map<String, DayOfWeekPanel> dayPanels;
    private int lastSelectedIndex = -1; // Tracks the index of the tab that was previously selected
    private volatile boolean isHandlingTabSwitch = false; // Flag to prevent re-entrant calls during complex tab switching
    /**
     * Constructor for MenuManagerApp.
     * Initializes the data store and sets up the main application window.
     */
    public MenuManagerApp() {
        super("Restaurant Daily Menu Editor");
        this.dataStore = new RestaurantDataStore();
        this.dayPanels = new HashMap<>();
        initializeGUI();
    }
    /**
     * Initializes the graphical user interface.
     * Sets up the main frame, tabbed pane, and individual day panels.
     * Implements "load-on-demand" for menu data and handles unsaved changes across tab switches.
     */
    private void initializeGUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Custom close operation
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 14)); // Make tabs more prominent
        // Populate tabs for each day of the week
        for (String day : RestaurantDataStore.DAYS_OF_WEEK) {
            DayOfWeekPanel panel = new DayOfWeekPanel(day, dataStore);
            dayPanels.put(day, panel);
            tabbedPane.addTab(day, panel);
        }
        // Add a ChangeListener to handle tab selection, including checking for unsaved changes.
        // This listener is designed to manage the complexities of asynchronous saving during tab switches.
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Use a flag to prevent re-entrant calls during the asynchronous tab switch process.
                if (isHandlingTabSwitch) {
                    return;
                }
                final int newSelectedIndex = tabbedPane.getSelectedIndex();
                // If no tab was previously selected (initial state) or the selection hasn't actually changed,
                // or if the current tab is being switched to itself programmatically,
                // just manage the new panel's data loading.
                if (lastSelectedIndex == -1 || lastSelectedIndex == newSelectedIndex) {
                    loadDataForPanel(newSelectedIndex);
                    lastSelectedIndex = newSelectedIndex;
                    return;
                }
                // A tab is being deselected and a new one selected.
                final int prevSelectedIndex = lastSelectedIndex;
                String prevDay = tabbedPane.getTitleAt(prevSelectedIndex);
                DayOfWeekPanel prevPanel = dayPanels.get(prevDay);
                // Check for unsaved changes on the previously selected tab.
                if (prevPanel != null && prevPanel.isModified()) {
                    isHandlingTabSwitch = true; // Set flag to indicate tab switch is being handled.
                    // Use SwingUtilities.invokeLater to ensure the dialog appears correctly
                    // and to allow the JTabbedPane to revert its visual selection temporarily
                    // if the user chooses to stay or save asynchronously.
                    SwingUtilities.invokeLater(() -> {
                        // Temporarily revert the tab selection visually so that the dialog context is clear.
                        // The actual switch will be controlled by the user's choice and save outcome.
                        tabbedPane.setSelectedIndex(prevSelectedIndex);
                        int choice = JOptionPane.showOptionDialog(
                                MenuManagerApp.this,
                                "Menu for " + prevDay + " has unsaved changes. What do you want to do?",
                                "Unsaved Changes",
                                JOptionPane.YES_NO_CANCEL_OPTION, // 0: Save, 1: Discard, 2: Cancel Tab Change
                                JOptionPane.WARNING_MESSAGE,
                                new String[]{"Save Changes", "Discard Changes", "Stay on " + prevDay},
                                "Save Changes" // Default focused button
                        );
                        if (choice == JOptionPane.YES_OPTION) { // User chose 'Save Changes'
                            // Set a callback for the DayOfWeekPanel's save operation.
                            prevPanel.setTabSaveCompleteListener(new TabSaveCompleteListener() {
                                @Override
                                public void onSaveSuccess(String dayName, int targetTabIndex) {
                                    // Save successful, proceed to the new tab.
                                    tabbedPane.setSelectedIndex(targetTabIndex);
                                    isHandlingTabSwitch = false; // Reset flag after completion.
                                }
                                @Override
                                public void onSaveFailure(String dayName, int targetTabIndex) {
                                    // Save failed (e.g., validation, connection error). Stay on the current tab.
                                    JOptionPane.showMessageDialog(MenuManagerApp.this,
                                            "Could not complete save operation for " + dayName + ". Please resolve issues and try again.",
                                            "Save Error", JOptionPane.ERROR_MESSAGE);
                                    tabbedPane.setSelectedIndex(prevSelectedIndex); // Ensure we stay on the current tab.
                                    isHandlingTabSwitch = false; // Reset flag.
                                }
                                @Override
                                public void onSaveCancelled(String dayName, int targetTabIndex) {
                                    // User cancelled the save confirmation during the asynchronous process. Stay on current tab.
                                    tabbedPane.setSelectedIndex(prevSelectedIndex); // Ensure we stay on the current tab.
                                    isHandlingTabSwitch = false; // Reset flag.
                                }
                            });
                            // Initiate the save process specifically for tab switching.
                            // The actual tab switch will be triggered by the callback.
                            prevPanel.saveMenuForTabSwitch(newSelectedIndex);
                        } else if (choice == JOptionPane.NO_OPTION) { // User chose 'Discard Changes'
                            prevPanel.discardChangesSilent(); // Discard without further prompts.
                            tabbedPane.setSelectedIndex(newSelectedIndex); // Now it's safe to switch.
                            isHandlingTabSwitch = false; // Reset flag.
                        } else { // User chose 'Stay on current tab' (CANCEL_OPTION or dialog closed)
                            tabbedPane.setSelectedIndex(prevSelectedIndex); // Revert to the old tab.
                            isHandlingTabSwitch = false; // Reset flag.
                        }
                    });
                    return; // Exit this stateChanged as the switch is now handled asynchronously or reverted.
                }
                // If no unsaved changes on the previous tab, or changes were discarded,
                // this block proceeds with loading data for the newly selected tab.
                loadDataForPanel(newSelectedIndex);
                lastSelectedIndex = newSelectedIndex; // Update last selected index.
            }
        });
        add(tabbedPane, BorderLayout.CENTER);
        // Add a WindowListener to handle closing gracefully, checking for unsaved changes across all panels.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Iterate through all panels to check for unsaved changes before exiting.
                for (DayOfWeekPanel panel : dayPanels.values()) {
                    if (panel.isModified()) {
                        String day = panel.getDayName();
                        int confirmExit = JOptionPane.showOptionDialog(
                                MenuManagerApp.this,
                                "Menu for " + day + " has unsaved changes. Exit anyway?",
                                "Unsaved Changes - Exit Confirmation",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE,
                                null, null, null);
                        if (confirmExit != JOptionPane.YES_OPTION) {
                            return; // Do not exit if user says no to exiting with unsaved changes.
                        }
                    }
                }
                int confirm = JOptionPane.showOptionDialog(
                        MenuManagerApp.this,
                        "Are you sure you want to exit the application?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose(); // Close the frame.
                    System.exit(0); // Terminate the application.
                }
            }
        });
        setVisible(true);
        // Manually trigger initial loading for the first tab once the GUI is visible.
        if (tabbedPane.getTabCount() > 0) {
            // Setting selected index programmatically fires the ChangeListener.
            // This ensures the first tab's data is loaded immediately upon application start.
            tabbedPane.setSelectedIndex(0);
        }
    }
    /**
     * Helper method to load data for a given panel index.
     * @param index The index of the tab to load data for.
     */
    private void loadDataForPanel(int index) {
        if (index >= 0 && index < tabbedPane.getTabCount()) {
            String day = tabbedPane.getTitleAt(index);
            DayOfWeekPanel panel = dayPanels.get(day);
            if (panel != null) {
                panel.loadMenuData(); // Load fresh data for the newly selected tab (step 4).
            }
        }
    }
    /**
     * Main method to start the application.
     * Ensures the GUI is created and updated on the Event Dispatch Thread.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT).
        SwingUtilities.invokeLater(MenuManagerApp::new);
    }
}