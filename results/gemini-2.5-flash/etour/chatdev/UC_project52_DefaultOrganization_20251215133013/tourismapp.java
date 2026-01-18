'''
Main application class for the Tourism delete bookmark feature.
Provides a GUI for tourists to view and remove sites from their bookmark list.
Built using Java Swing.
'''
package com.chatdev.tourism;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class TourismApp extends JFrame {
    private BookmarkManager bookmarkManager;
    private JList<Site> siteJList;
    private DefaultListModel<Site> listModel;
    private JButton removeButton;
    private JLabel statusLabel;
    private JCheckBox simulateErrorCheckBox; // Checkbox to toggle simulated connection errors
    /**
     * Constructs the main TourismApp GUI.
     * Initializes the bookmark manager and sets up the user interface.
     */
    public TourismApp() {
        bookmarkManager = new BookmarkManager();
        initData(); // Populate with some initial sites
        setTitle("ETOUR - Delete Bookmarked Site");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initUI(); // Set up the GUI components
        populateSitesList(); // Populate the JList with current bookmarks
    }
    /**
     * Initializes some sample data for the bookmark manager.
     */
    private void initData() {
        bookmarkManager.addSite(new Site("S001", "Eiffel Tower", "Paris, France"));
        bookmarkManager.addSite(new Site("S002", "Colosseum", "Rome, Italy"));
        bookmarkManager.addSite(new Site("S003", "Great Wall", "Beijing, China"));
        bookmarkManager.addSite(new Site("S004", "Taj Mahal", "Agra, India"));
    }
    /**
     * Initializes the graphical user interface components.
     * Sets up layout, list, buttons, and status messages.
     * This method orchestrates the creation of different UI sections.
     */
    private void initUI() {
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for main frame
        // Initialize control panel components first, specifically removeButton
        // as it's referenced by the siteJList's listener.
        add(createControlPanel(), BorderLayout.NORTH);
        // Initialize sites display panel components, now `removeButton` exists.
        add(createSitesDisplayPanel(), BorderLayout.CENTER);
        // Initialize and add status bar
        add(createStatusBar(), BorderLayout.SOUTH);
    }
    /**
     * Creates and configures the panel for displaying bookmarked sites.
     * @return A JPanel containing the JList of sites.
     */
    private JPanel createSitesDisplayPanel() {
        JPanel sitesPanel = new JPanel(new BorderLayout());
        sitesPanel.setBorder(BorderFactory.createTitledBorder("My Bookmarked Sites"));
        listModel = new DefaultListModel<>();
        siteJList = new JList<>(listModel);
        siteJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        siteJList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        // Add a ListSelectionListener to dynamically enable/disable the remove button
        siteJList.addListSelectionListener(e -> {
            // Only respond to final selection changes, not intermediate ones during a drag
            if (!e.getValueIsAdjusting()) {
                // Ensure removeButton is not null (initialized in createControlPanel)
                if (removeButton != null) {
                    removeButton.setEnabled(siteJList.getSelectedIndex() != -1);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(siteJList);
        sitesPanel.add(scrollPane, BorderLayout.CENTER);
        return sitesPanel;
    }
    /**
     * Creates and configures the panel with control buttons and options.
     * This method initializes the removeButton and simulateErrorCheckBox class members.
     * @return A JPanel containing the remove button and error simulation checkbox.
     */
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        removeButton = new JButton("Remove Selected Site");
        // Initially disable the button as no site is selected
        removeButton.setEnabled(false);
        removeButton.addActionListener(e -> removeSelectedSite());
        simulateErrorCheckBox = new JCheckBox("Simulate Server Connection Error");
        simulateErrorCheckBox.addActionListener(e -> {
            bookmarkManager.setSimulateConnectionError(simulateErrorCheckBox.isSelected());
            statusLabel.setText("Server error simulation: " + (simulateErrorCheckBox.isSelected() ? "ON" : "OFF"));
        });
        controlPanel.add(simulateErrorCheckBox);
        controlPanel.add(removeButton);
        return controlPanel;
    }
    /**
     * Creates and configures the status bar label.
     * This method initializes the statusLabel class member.
     * @return A JLabel for displaying status messages.
     */
    private JLabel createStatusBar() {
        statusLabel = new JLabel("Ready to remove bookmarks.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        return statusLabel;
    }
    /**
     * Populates the JList with the current sites from the BookmarkManager.
     */
    private void populateSitesList() {
        listModel.clear(); // Clear existing items
        for (Site site : bookmarkManager.getAllBookmarks()) {
            listModel.addElement(site); // Add each site to the list model
        }
        // After repopulating, no item is selected, so disable the remove button.
        // The ListSelectionListener will enable it if an item is selected.
        if (removeButton != null) { // Ensure button is initialized before attempting to disable
            removeButton.setEnabled(false);
        }
    }
    /**
     * Handles the process of removing a selected site from the bookmarks.
     * This includes prompting for confirmation and handling various exit conditions.
     */
    private void removeSelectedSite() {
        Site selectedSite = siteJList.getSelectedValue(); // Get the currently selected site
        if (selectedSite == null) {
            statusLabel.setText("Please select a site to remove.");
            JOptionPane.showMessageDialog(this, "No site selected. Please choose a site from the list.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 2. Prompt removal - asking for confirmation
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove '" + selectedSite.getName() + "' from your bookmarks?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // 3. Confirm the removal (User confirmed)
            try {
                // 4. Removes the selected site from the list of bookmarks.
                boolean removed = bookmarkManager.removeSite(selectedSite.getId());
                if (removed) {
                    populateSitesList(); // Refresh the list in the GUI
                    statusLabel.setText("'" + selectedSite.getName() + "' has been successfully removed from bookmarks.");
                    // Exit condition: The notification system has been removed from the site bookmarks.
                    JOptionPane.showMessageDialog(this, "'" + selectedSite.getName() + "' removed successfully!", "Removal Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    statusLabel.setText("Failed to remove '" + selectedSite.getName() + "'. Site not found.");
                    JOptionPane.showMessageDialog(this, "Site not found. It might have already been removed.", "Removal Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ServerConnectionException ex) {
                // Exit condition: Interruption of the connection to the server ETOUR.
                statusLabel.setText("Error: " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Server Connection Error: " + ex.getMessage() + "\nPlease try again later.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Exit condition: The Tourist cancel the operation
            statusLabel.setText("Removal of '" + selectedSite.getName() + "' cancelled by user.");
            JOptionPane.showMessageDialog(this, "Operation cancelled. The site was NOT removed.", "Operation Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Main method to run the TourismApp.
     * Ensures the GUI is created and updated on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TourismApp().setVisible(true);
            }
        });
    }
}