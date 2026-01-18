/**
 * The main application window for the Tourist App, allowing users to select
 * a tourist site and view its details. It integrates a simulated SiteDatabase
 * and a SiteDetailPanel for display. It also includes controls to simulate
 * a database connection interruption.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
public class TouristApp extends JFrame {
    private SiteDatabase siteDatabase;
    private SiteDetailPanel siteDetailPanel;
    private JComboBox<String> siteSelectorComboBox;
    private JToggleButton connectionToggleButton;
    /**
     * Constructs the main TouristApp frame, sets up the GUI, and initializes
     * the components for site selection, detail display, and connection simulation.
     */
    public TouristApp() {
        // Set basic frame properties
        setTitle("ETOUR: View Site Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500); // Increased size for better layout
        setLocationRelativeTo(null); // Center the window
        // Initialize the simulated database
        siteDatabase = new SiteDatabase();
        // Initialize the panel for displaying site details
        siteDetailPanel = new SiteDetailPanel();
        // --- Create control panel for site selection and connection status ---
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center alignment with spacing
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls & Selection"));
        // Site Selector ComboBox
        controlPanel.add(new JLabel("Select Site:"));
        // Populate the JComboBox with available site IDs
        Vector<String> siteIds = new Vector<>(siteDatabase.getAvailableSiteIds());
        // Add an empty string or prompt at the beginning for initial selection state
        siteIds.add(0, "-- Select a Site --");
        siteSelectorComboBox = new JComboBox<>(siteIds);
        siteSelectorComboBox.setPreferredSize(new Dimension(200, 25)); // Set preferred size
        // ActionListener for the ComboBox to instantly display details upon selection
        siteSelectorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSiteId = (String) siteSelectorComboBox.getSelectedItem();
                if (selectedSiteId != null && !selectedSiteId.equals("-- Select a Site --")) {
                    viewSiteDetails(selectedSiteId);
                } else {
                    // If "Select a Site" is chosen, clear the display and show initial message as informational
                    siteDetailPanel.clearDetails();
                    siteDetailPanel.displayInfoMessage("Please select a site to view its details."); // Changed from displayErrorMessage
                }
            }
        });
        controlPanel.add(siteSelectorComboBox);
        // Connection Toggle Button
        connectionToggleButton = new JToggleButton("Simulate Server Connection ON");
        connectionToggleButton.setSelected(true); // Initially connected
        connectionToggleButton.setBackground(new Color(144, 238, 144)); // Light green for ON
        connectionToggleButton.setOpaque(true);
        connectionToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = connectionToggleButton.isSelected();
                siteDatabase.setConnectionStatus(isSelected); // Update mock database status
                if (isSelected) {
                    connectionToggleButton.setText("Simulate Server Connection ON");
                    connectionToggleButton.setBackground(new Color(144, 238, 144)); // Light green
                    // Using the newly introduced displayInfoMessage for positive feedback
                    siteDetailPanel.displayInfoMessage("Connection to ETOUR server restored.");
                } else {
                    connectionToggleButton.setText("Simulate Server Connection OFF");
                    connectionToggleButton.setBackground(new Color(255, 99, 71)); // Tomato red
                    siteDetailPanel.displayErrorMessage("Connection to ETOUR server interrupted.");
                }
                // If a site is currently selected and connection state changed, re-attempt to load
                String currentSelection = (String) siteSelectorComboBox.getSelectedItem();
                if (currentSelection != null && !currentSelection.equals("-- Select a Site --")) {
                    viewSiteDetails(currentSelection);
                }
            }
        });
        controlPanel.add(connectionToggleButton);
        // --- Arrange panels in the frame ---
        add(controlPanel, BorderLayout.NORTH); // Control panel at the top
        add(siteDetailPanel, BorderLayout.CENTER); // Site details in the center
    }
    /**
     * Fetches and displays the details of a site based on its ID.
     * This method interacts with the SiteDatabase and updates the SiteDetailPanel.
     *
     * @param siteId The ID of the site to view.
     */
    private void viewSiteDetails(String siteId) {
        try {
            // Attempt to retrieve site details from the simulated database
            Site site = siteDatabase.getSiteDetails(siteId);
            // If successful, update the detail panel
            siteDetailPanel.updateSiteDetails(site);
        } catch (DatabaseConnectionException e) {
            // Handle database connection interruption
            siteDetailPanel.displayErrorMessage(e.getMessage());
            // Ensure connection button reflects the actual (simulated) state
            if (siteDatabase.isConnected()) {
                connectionToggleButton.setText("Simulate Server Connection ON");
                connectionToggleButton.setSelected(true);
                connectionToggleButton.setBackground(new Color(144, 238, 144));
            } else {
                connectionToggleButton.setText("Simulate Server Connection OFF");
                connectionToggleButton.setSelected(false);
                connectionToggleButton.setBackground(new Color(255, 99, 71));
            }
        } catch (SiteNotFoundException e) {
            // Handle case where the site ID is not found
            siteDetailPanel.displayErrorMessage(e.getMessage());
        }
    }
    /**
     * Main method to launch the TouristApp.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TouristApp().setVisible(true);
            }
        });
    }
}