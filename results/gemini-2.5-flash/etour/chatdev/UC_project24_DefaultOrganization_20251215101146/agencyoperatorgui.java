/*
 * This class represents the graphical user interface for the Agency Operator.
 * It allows the operator to view a list of sites and then display feedback
 * for a selected site, implementing the VISUALIZZAFEEDBACK use case.
 */
package com.chatdev.gui;
import com.chatdev.model.Feedback;
import com.chatdev.model.Site;
import com.chatdev.service.SiteService;
import com.chatdev.service.FeedbackService;
import com.chatdev.exception.ConnectionException; // Import the common ConnectionException
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;
public class AgencyOperatorGUI extends JFrame {
    private JList<Site> siteList; // Component to display the list of sites
    private JTextArea feedbackDisplayArea; // Component to display feedback details
    private JButton viewFeedbackButton; // Button to trigger feedback loading
    private JLabel statusLabel; // Label to display system messages or errors
    private SiteService siteService; // Service to retrieve site data
    private FeedbackService feedbackService; // Service to retrieve feedback data
    /**
     * Constructs the main GUI window for the Agency Operator.
     * Initializes components, serv, and loads initial data.
     */
    public AgencyOperatorGUI() {
        super("Agency Operator - View Site Feedback"); // Set window title
        this.siteService = new SiteService(); // Initialize site service
        this.feedbackService = new FeedbackService(); // Initialize feedback service
        initComponents(); // Set up GUI components
        loadSites(); // Load and display sites on startup
    }
    /**
     * Initializes and arranges all GUI components within the JFrame.
     */
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        setSize(800, 600); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        // Panel for site selection
        JPanel siteSelectionPanel = new JPanel(new BorderLayout(5, 5));
        siteSelectionPanel.setBorder(BorderFactory.createTitledBorder("Select a Site")); // Add a title border
        siteList = new JList<>(); // Initialize JList for sites
        siteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one site to be selected at a time
        JScrollPane siteListScrollPane = new JScrollPane(siteList); // Add JList to a scroll pane
        siteListScrollPane.setPreferredSize(new Dimension(200, 0)); // Set preferred width for the list
        viewFeedbackButton = new JButton("View Feedback"); // Initialize the button
        // Add action listener to the button
        viewFeedbackButton.addActionListener(e -> viewFeedbackForSelectedSite());
        siteSelectionPanel.add(siteListScrollPane, BorderLayout.CENTER);
        siteSelectionPanel.add(viewFeedbackButton, BorderLayout.SOUTH);
        // Panel for feedback display
        JPanel feedbackPanel = new JPanel(new BorderLayout(5, 5));
        feedbackPanel.setBorder(BorderFactory.createTitledBorder("Site Feedback")); // Add a title border
        feedbackDisplayArea = new JTextArea(); // Initialize text area for feedback
        feedbackDisplayArea.setEditable(false); // Make it read-only
        feedbackDisplayArea.setLineWrap(true); // Enable line wrapping
        feedbackDisplayArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane feedbackScrollPane = new JScrollPane(feedbackDisplayArea); // Add text area to a scroll pane
        feedbackPanel.add(feedbackScrollPane, BorderLayout.CENTER);
        // Status bar at the bottom
        statusLabel = new JLabel("Application Ready."); // Initialize status label
        statusLabel.setBorder(BorderFactory.createEtchedBorder()); // Add a border for visual separation
        // Add panels and status label to the main frame
        add(siteSelectionPanel, BorderLayout.WEST);
        add(feedbackPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }
    /**
     * Loads the list of sites from the SiteService and populates the JList.
     * Handles potential connection errors during site loading.
     */
    private void loadSites() {
        try {
            List<Site> sites = siteService.getAllSites(); // Fetch all sites
            // Convert List<Site> to Vector<Site> which JList's constructor accepts
            siteList.setListData(new Vector<>(sites));
            if (sites.isEmpty()) {
                statusLabel.setText("No sites found.");
            } else {
                statusLabel.setText("Sites loaded. Select a site to view feedback.");
            }
        } catch (ConnectionException e) {
            // Handle simulated connection error during site loading using the common ConnectionException
            statusLabel.setText("Error loading sites: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Failed to load sites: " + e.getMessage() +
                    "\nPlease check connection and restart.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Retrieves and displays feedback for the currently selected site.
     * Validates selection and handles cases with no feedback or connection errors.
     */
    private void viewFeedbackForSelectedSite() {
        Site selectedSite = siteList.getSelectedValue(); // Get the selected site from the list
        if (selectedSite == null) {
            // If no site is selected, display a warning
            statusLabel.setText("Please select a site from the list.");
            feedbackDisplayArea.setText(""); // Clear previous feedback
            return;
        }
        statusLabel.setText("Loading feedback for '" + selectedSite.getName() + "'...");
        feedbackDisplayArea.setText("Loading feedback..."); // Show loading message
        // Execute feedback retrieval in a separate thread to keep GUI responsive
        SwingWorker<List<Feedback>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Feedback> doInBackground() throws Exception {
                // Simulate network delay for better user experience
                Thread.sleep(500); // 0.5 second delay
                return feedbackService.getFeedbackForSite(selectedSite.getId()); // Fetch feedback
            }
            @Override
            protected void done() {
                try {
                    List<Feedback> feedbackList = get(); // Get result from doInBackground
                    if (feedbackList.isEmpty()) {
                        // If no feedback is found for the selected site
                        feedbackDisplayArea.setText("No feedback available for " + selectedSite.getName() + ".");
                        statusLabel.setText("No feedback found for '" + selectedSite.getName() + "'.");
                    } else {
                        // Build a string with all feedback and display it
                        // Append System.lineSeparator() for proper spacing.
                        StringBuilder sb = new StringBuilder();
                        for (Feedback feedback : feedbackList) {
                            sb.append(feedback.toString()).append(System.lineSeparator()).append(System.lineSeparator());
                        }
                        // Remove the trailing two line separators if present, for cleaner display, although
                        // JTextArea usually handles this gracefully.
                        if (sb.length() > 0) {
                             sb.setLength(sb.length() - (2 * System.lineSeparator().length()));
                        }
                        feedbackDisplayArea.setText(sb.toString());
                        statusLabel.setText("Displayed feedback for '" + selectedSite.getName() + "'.");
                    }
                } catch (InterruptedException | java.util.concurrent.ExecutionException e) {
                    Throwable cause = e.getCause(); // Get the actual exception
                     // Handle simulated connection error during feedback retrieval using the common ConnectionException
                    if (cause instanceof ConnectionException) {
                        feedbackDisplayArea.setText("Error: " + cause.getMessage() + "\nPlease try again.");
                        statusLabel.setText("Connection interruption: " + cause.getMessage());
                        JOptionPane.showMessageDialog(AgencyOperatorGUI.this,
                                "Failed to retrieve feedback: " + cause.getMessage(),
                                "Connection Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Handle other unexpected errors
                        feedbackDisplayArea.setText("An unexpected error occurred: " + cause.getMessage());
                        statusLabel.setText("Error: " + cause.getMessage());
                        JOptionPane.showMessageDialog(AgencyOperatorGUI.this,
                                "An unexpected error occurred: " + cause.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    e.printStackTrace(); // Log the full stack trace for debugging
                }
            }
        };
        worker.execute(); // Start the background task
    }
}