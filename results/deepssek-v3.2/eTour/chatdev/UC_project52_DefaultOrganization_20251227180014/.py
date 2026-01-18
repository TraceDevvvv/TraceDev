/**
 * TouristPreferenceManager.java
 * This class implements the GUI for the DeleteSiteFromPreferences use case.
 * It provides a window where users can view bookmarked sites, select one for removal,
 * confirm the action, and see the updated list.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class TouristPreferenceManager extends JFrame {
    private PreferenceManager preferenceManager;
    private DefaultListModel<Site> listModel;
    private JList<Site> siteList;
    private JButton removeButton;
    private JButton refreshButton;
    private JLabel statusLabel;
    // Constructor sets up the GUI components and initializes the PreferenceManager
    public TouristPreferenceManager() {
        super("Tourist Site Bookmark Manager");
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        loadSampleData(); // Load some sample data for demonstration
        updateSiteList();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null); // Center the window
    }
    /**
     * Initializes GUI components.
     */
    private void initializeComponents() {
        preferenceManager = new PreferenceManager();
        listModel = new DefaultListModel<>();
        siteList = new JList<>(listModel);
        siteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        removeButton = new JButton("Remove Selected Site");
        refreshButton = new JButton("Refresh List");
        statusLabel = new JLabel("Welcome! Select a site to remove.");
        statusLabel.setForeground(Color.BLUE);
    }
    /**
     * Arranges components in the window using BorderLayout and GridLayout.
     */
    private void setupLayout() {
        // Main panel with BorderLayout
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        // Top panel with instructions
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createTitledBorder("Instructions"));
        JLabel instructionLabel = new JLabel("<html>1. Choose a site from the list.<br>2. Click 'Remove Selected Site'.<br>3. Confirm removal in the dialog.<br>4. Site will be removed from bookmarks.</html>");
        topPanel.add(instructionLabel);
        contentPane.add(topPanel, BorderLayout.NORTH);
        // Center panel with the list of sites
        JScrollPane scrollPane = new JScrollPane(siteList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Bookmarked Sites"));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        // Bottom panel with buttons and status
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        buttonPanel.add(refreshButton);
        bottomPanel.add(buttonPanel);
        bottomPanel.add(statusLabel);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }
    /**
     * Sets up event handlers for buttons.
     */
    private void setupEventHandlers() {
        // Remove button: triggers the removal flow
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedSite();
            }
        });
        // Refresh button: updates the list (useful after removal)
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSiteList();
                statusLabel.setText("List refreshed.");
            }
        });
    }
    /**
     * Loads sample data into the PreferenceManager for demonstration.
     * In a real application, data would be loaded from a persistent storage or server.
     */
    private void loadSampleData() {
        preferenceManager.addSite(new Site("S001", "Eiffel Tower"));
        preferenceManager.addSite(new Site("S002", "Colosseum"));
        preferenceManager.addSite(new Site("S003", "Statue of Liberty"));
        preferenceManager.addSite(new Site("S004", "Great Wall of China"));
        preferenceManager.addSite(new Site("S005", "Machu Picchu"));
    }
    /**
     * Updates the JList with the current bookmarked sites from the PreferenceManager.
     */
    private void updateSiteList() {
        listModel.clear();
        List<Site> sites = preferenceManager.getBookmarkedSites();
        for (Site site : sites) {
            listModel.addElement(site);
        }
    }
    /**
     * Implements the removal flow as per the use case.
     * Steps:
     * 1. User chooses a site (via selection in the list) -> handled by siteList.getSelectedIndex()
     * 2. Prompt removal -> show confirmation dialog
     * 3. Confirm the removal -> if user confirms, proceed
     * 4. Removes the selected site -> call preferenceManager.removeSiteByIndex()
     * Also handles exit conditions: user cancellation and server connection interruption.
     */
    private void removeSelectedSite() {
        // Check if a site is selected (Entry condition: tourist card is in a particular site)
        int selectedIndex = siteList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a site to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Step 2: Prompt removal with a confirmation dialog
        Site selectedSite = listModel.getElementAt(selectedIndex);
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove '" + selectedSite.getSiteName() + "' from bookmarks?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION);
        // Step 3: Confirm the removal
        if (confirmation == JOptionPane.YES_OPTION) {
            // Check server connection (simulating interruption handling)
            if (!preferenceManager.isConnectionActive()) {
                JOptionPane.showMessageDialog(this, "Connection to server ETOUR interrupted. Removal cancelled.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Removal failed: server connection interrupted.");
                return;
            }
            // Step 4: Remove the selected site from the list of bookmarks
            boolean removed = preferenceManager.removeSiteByIndex(selectedIndex);
            if (removed) {
                updateSiteList(); // Refresh the GUI list
                // Exit condition: notification system removed (simulated by status label update)
                statusLabel.setText("Successfully removed '" + selectedSite.getSiteName() + "' from bookmarks.");
                JOptionPane.showMessageDialog(this, "Site removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                statusLabel.setText("Removal failed. Please try again.");
            }
        } else {
            // The Tourist cancel the operation
            statusLabel.setText("Removal cancelled by user.");
        }
    }
}