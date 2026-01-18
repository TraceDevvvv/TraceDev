'''
TouristPreferenceManager.java
This class implements the GUI for the DeleteSiteFromPreferences use case.
It provides a window where users can view bookmarked sites, select one for removal,
confirm the action, and see the updated list.
'''
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
    private JButton simulateDisconnectButton;
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
        this.setSize(500, 450);
        this.setLocationRelativeTo(null); // Center the window
        this.setResizable(false);
    }
    /**
     * Initializes GUI components.
     */
    private void initializeComponents() {
        preferenceManager = new PreferenceManager();
        listModel = new DefaultListModel<>();
        siteList = new JList<>(listModel);
        siteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        siteList.setBackground(new Color(240, 248, 255)); // Light background
        removeButton = new JButton("Remove Selected Site");
        removeButton.setBackground(new Color(220, 60, 60));
        removeButton.setForeground(Color.WHITE);
        refreshButton = new JButton("Refresh List");
        refreshButton.setBackground(new Color(60, 120, 220));
        refreshButton.setForeground(Color.WHITE);
        simulateDisconnectButton = new JButton("Simulate Connection Disruption");
        simulateDisconnectButton.setBackground(new Color(255, 140, 0));
        simulateDisconnectButton.setForeground(Color.BLACK);
        statusLabel = new JLabel("Welcome! Select a site to remove.", SwingConstants.CENTER);
        statusLabel.setForeground(Color.BLUE);
    }
    /**
     * Arranges components in the window using BorderLayout and GridLayout.
     */
    private void setupLayout() {
        // Main panel with BorderLayout
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBackground(Color.WHITE);
        // Top panel with instructions
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE, 2), 
                "Instructions", TitledBorder.CENTER, TitledBorder.TOP));
        topPanel.setBackground(new Color(230, 240, 255));
        JLabel instructionLabel = new JLabel("<html><div style='text-align: center;'>"
                + "<b>Site Bookmark Removal Process:</b><br>"
                + "1. Select a site from the list below<br>"
                + "2. Click 'Remove Selected Site' button<br>"
                + "3. Confirm removal in the dialog box<br>"
                + "4. Site will be removed from bookmarks</div></html>");
        topPanel.add(instructionLabel);
        contentPane.add(topPanel, BorderLayout.NORTH);
        // Center panel with the list of sites
        JScrollPane scrollPane = new JScrollPane(siteList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), 
                "Bookmarked Sites", TitledBorder.CENTER, TitledBorder.TOP));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        // Bottom panel with buttons and status
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBackground(Color.WHITE);
        // Button panel with three buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(removeButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(simulateDisconnectButton);
        // Status panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(Color.WHITE);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(statusPanel, BorderLayout.SOUTH);
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
        // Refresh button: updates the list
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSiteList();
                statusLabel.setText("List refreshed successfully.");
                statusLabel.setForeground(Color.GREEN.darker());
            }
        });
        // Simulate disconnect button: toggles server connection status
        simulateDisconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currentStatus = preferenceManager.isConnectionActive();
                preferenceManager.setConnectionActive(!currentStatus);
                if (preferenceManager.isConnectionActive()) {
                    statusLabel.setText("Connection to ETOUR server RESTORED.");
                    statusLabel.setForeground(Color.GREEN.darker());
                    simulateDisconnectButton.setText("Simulate Connection Disruption");
                    simulateDisconnectButton.setBackground(new Color(255, 140, 0));
                } else {
                    statusLabel.setText("Connection to ETOUR server LOST - Simulated Disruption");
                    statusLabel.setForeground(Color.RED);
                    simulateDisconnectButton.setText("Restore Connection");
                    simulateDisconnectButton.setBackground(new Color(50, 205, 50));
                }
            }
        });
    }
    /**
     * Loads sample data into the PreferenceManager for demonstration.
     */
    private void loadSampleData() {
        preferenceManager.addSite(new Site("S001", "Eiffel Tower", "Paris, France"));
        preferenceManager.addSite(new Site("S002", "Colosseum", "Rome, Italy"));
        preferenceManager.addSite(new Site("S003", "Statue of Liberty", "New York, USA"));
        preferenceManager.addSite(new Site("S004", "Great Wall of China", "Beijing, China"));
        preferenceManager.addSite(new Site("S005", "Machu Picchu", "Cusco, Peru"));
        preferenceManager.addSite(new Site("S006", "Taj Mahal", "Agra, India"));
        preferenceManager.addSite(new Site("S007", "Pyramids of Giza", "Cairo, Egypt"));
        preferenceManager.addSite(new Site("S008", "Sydney Opera House", "Sydney, Australia"));
    }
    /**
     * Updates the JList with the current bookmarked sites from the PreferenceManager.
     */
    private void updateSiteList() {
        listModel.clear();
        List<Site> sites = preferenceManager.getBookmarkedSites();
        if (sites.isEmpty()) {
            listModel.addElement(new Site("No Sites", "No bookmarked sites available", ""));
        } else {
            for (Site site : sites) {
                listModel.addElement(site);
            }
        }
    }
    /**
     * Implements the removal flow as per the use case.
     * Handles all steps: selection, prompt, confirmation, and removal.
     * Also handles edge cases: no selection, user cancellation, server disconnection.
     */
    private void removeSelectedSite() {
        // Check if a site is selected AND it's not the placeholder
        int selectedIndex = siteList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, 
                    "Please select a site from the list to remove.", 
                    "No Site Selected", 
                    JOptionPane.WARNING_MESSAGE);
            statusLabel.setText("Please select a site first.");
            statusLabel.setForeground(Color.ORANGE.darker());
            return;
        }
        Site selectedSite = listModel.getElementAt(selectedIndex);
        // Prevent removal of placeholder
        if (selectedSite.getSiteId().equals("No Sites")) {
            JOptionPane.showMessageDialog(this, 
                    "Cannot remove placeholder item. No bookmarked sites available.", 
                    "Invalid Selection", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Step 2: Prompt removal with detailed confirmation dialog
        int confirmation = JOptionPane.showConfirmDialog(this,
                "<html><b>Confirm Site Removal</b><br><br>"
                + "Site: <b>" + selectedSite.getSiteName() + "</b><br>"
                + "Location: " + selectedSite.getLocation() + "<br>"
                + "ID: " + selectedSite.getSiteId() + "<br><br>"
                + "Are you sure you want to remove this site from your bookmarks?</html>",
                "Confirm Site Removal",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        // Step 3: Process confirmation or cancellation
        if (confirmation == JOptionPane.YES_OPTION) {
            // Check server connection before proceeding
            if (!preferenceManager.isConnectionActive()) {
                JOptionPane.showMessageDialog(this, 
                        "<html><b>Connection Error</b><br>"
                        + "Unable to connect to ETOUR server.<br>"
                        + "Please check your connection and try again.</html>", 
                        "Server Connection Interrupted", 
                        JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Removal failed: Connection to server interrupted.");
                statusLabel.setForeground(Color.RED);
                return;
            }
            // Step 4: Remove the selected site
            boolean removed = preferenceManager.removeSiteByIndex(selectedIndex);
            if (removed) {
                // Update GUI and provide success feedback
                updateSiteList();
                statusLabel.setText("Successfully removed '" + selectedSite.getSiteName() + "' from bookmarks.");
                statusLabel.setForeground(Color.GREEN.darker());
                // Success notification
                JOptionPane.showMessageDialog(this,
                        "<html><b>Removal Successful!</b><br><br>"
                        + "Site: <b>" + selectedSite.getSiteName() + "</b><br>"
                        + "has been removed from your bookmarks.</html>",
                        "Site Removed",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Handle removal failure
                statusLabel.setText("Removal failed. The site may have been already removed.");
                statusLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this,
                        "Failed to remove the site. Please try again.",
                        "Removal Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // User cancelled the operation
            statusLabel.setText("Removal cancelled by user.");
            statusLabel.setForeground(Color.BLUE);
        }
    }
    /**
     * Main method to launch the application.
     * Uses SwingUtilities.invokeLater for thread safety.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set system look and feel for better appearance
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    // Fall back to default if system L&F not available
                }
                TouristPreferenceManager app = new TouristPreferenceManager();
                app.setVisible(true);
            }
        });
    }
}