/**
 * Main application class for displaying sites with feedback for an authenticated tourist.
 * This class provides a graphical user interface (GUI) using Java Swing.
 */
package com.chatdev.etour;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Main application class for displaying sites with feedback for an authenticated tourist.
 * This class provides a graphical user interface (GUI) using Java Swing.
 * It allows a user to input a Tourist ID and view a list of sites for which that tourist
 * has provided feedback, simulating data retrieval and potential network errors.
 */
public class SiteDisplayApp extends JFrame {
    // --- UI Components ---
    private JList<Site> siteList;
    private DefaultListModel<Site> listModel; // Model to manage items in the JList
    private JButton displayButton;
    private JLabel statusLabel;
    private JTextField touristIdField;
    // --- Service Layer ---
    private FeedbackService feedbackService;
    // --- State Variables ---
    // In a real application, the authenticated tourist ID would come from a proper authentication process.
    // For this example, we use a default ID and allow user input.
    private static final String DEFAULT_AUTHENTICATED_TOURIST_ID = "user1";
    /**
     * Constructs the main application window and initializes all UI components and logic.
     */
    public SiteDisplayApp() {
        super("ETOUR: Visited Sites with Feedback"); // Set window title
        // Initialize the simulated feedback service
        feedbackService = new FeedbackService();
        // --- Frame Setup ---
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define behavior when the window is closed
        setSize(500, 400); // Set the initial size of the window
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with 10 pixel gaps
        // --- North Panel: Tourist ID input ---
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        northPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding
        northPanel.add(new JLabel("Tourist ID (e.g., user1, user2, user3):")); // Label for input field
        touristIdField = new JTextField(DEFAULT_AUTHENTICATED_TOURIST_ID, 10); // Input field with default value
        northPanel.add(touristIdField);
        add(northPanel, BorderLayout.NORTH); // Add north panel to the top of the frame
        // --- Center Panel: Site List ---
        // Create a DefaultListModel to dynamically manage the list of Site objects
        listModel = new DefaultListModel<>();
        // Create the JList component using the model
        siteList = new JList<>(listModel);
        siteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one item to be selected at a time
        siteList.setLayoutOrientation(JList.VERTICAL); // Arrange items vertically
        siteList.setVisibleRowCount(-1); // Display as many rows as needed without affecting dimensions
        // Wrap the JList in a JScrollPane to provide scrollability if there are many items
        JScrollPane scrollPane = new JScrollPane(siteList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Sites with your Feedback")); // Add a descriptive border
        add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the center of the frame
        // --- South Panel: Button and Status Label ---
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        displayButton = new JButton("Display My Feedback Sites"); // Button to trigger data loading
        statusLabel = new JLabel("Enter Tourist ID and click to view feedback sites."); // Label to display status messages
        statusLabel.setForeground(Color.BLUE); // Initial status message color
        southPanel.add(displayButton);
        southPanel.add(statusLabel);
        add(southPanel, BorderLayout.SOUTH); // Add south panel to the bottom of the frame
        // --- Event Handling ---
        // Add an ActionListener to the display button to respond to clicks
        displayButton.addActionListener(e -> {
            // Get the current tourist ID from the input field, trim whitespace
            String currentTouristId = touristIdField.getText().trim();
            if (currentTouristId.isEmpty()) {
                // Show an error if the tourist ID field is empty
                JOptionPane.showMessageDialog(this, "Tourist ID cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop processing
            }
            // Trigger the method to display sites for the specified tourist
            displaySitesForTourist(currentTouristId);
        });
        // Set the window to be visible after all components are added
        setVisible(true);
    }
    /**
     * Initiates the process of fetching and displaying the list of sites
     * for which the specified tourist has issued feedback.
     * This method uses a SwingWorker to perform the potentially long-running
     * network operation in a background thread, preventing the UI from freezing.
     *
     * @param touristId The ID of the authenticated tourist for whom to fetch feedback.
     */
    private void displaySitesForTourist(String touristId) {
        // Clear any previously displayed sites and update the status label
        listModel.clear();
        statusLabel.setText("Loading sites with feedback for " + touristId + "...");
        statusLabel.setForeground(Color.ORANGE); // Set status color to indicate loading
        displayButton.setEnabled(false); // Disable the button to prevent multiple concurrent requests
        // SwingWorker is used to perform background tasks without blocking the Event Dispatch Thread (EDT).
        // <List<Site>, Void> specifies the result type of doInBackground() and the type for intermediate results published by process() (not used here).
        SwingWorker<List<Site>, Void> worker = new SwingWorker<List<Site>, Void>() {
            @Override
            protected List<Site> doInBackground() throws Exception {
                // This method runs in a background thread.
                // Simulate fetching data from the ETOUR server using the FeedbackService.
                return feedbackService.getSitesWithFeedback(touristId);
            }
            @Override
            protected void done() {
                // This method runs on the Event Dispatch Thread (UI thread) after doInBackground() completes.
                try {
                    List<Site> sites = get(); // Get the result from doInBackground()
                    if (sites.isEmpty()) {
                        statusLabel.setText("No feedback sites found for " + touristId + " or data not available.");
                        statusLabel.setForeground(Color.GRAY); // Gray color for no results
                    } else {
                        // Populate the list model with the fetched sites
                        for (Site site : sites) {
                            listModel.addElement(site);
                        }
                        statusLabel.setText("Successfully displayed " + sites.size() + " sites for " + touristId + ".");
                        statusLabel.setForeground(Color.MAGENTA); // Success message color
                    }
                } catch (Exception ex) {
                    // Handle any exceptions that occurred in doInBackground(), including simulated connection errors.
                    // The `get()` method wraps checked exceptions from doInBackground() in an ExecutionException.
                    // We need to unwrap it to get the original cause.
                    Throwable cause = ex.getCause();
                    if (cause instanceof FeedbackService.ConnectionInterruptionException) {
                        statusLabel.setText("Error: " + cause.getMessage()); // Display specific error message
                        statusLabel.setForeground(Color.RED); // Red color for error
                    } else {
                        // Handle other unexpected errors
                        statusLabel.setText("An unexpected error occurred: " + (cause != null ? cause.getMessage() : ex.getMessage()));
                        statusLabel.setForeground(Color.RED);
                        ex.printStackTrace(); // Print stack trace for debugging purposes
                    }
                    listModel.clear(); // Clear the list on error
                } finally {
                    // Always re-enable the button once the operation is complete (success or failure)
                    displayButton.setEnabled(true);
                }
            }
        };
        // Execute the SwingWorker, starting the background task
        worker.execute();
    }
    /**
     * Main method to start the application.
     * This method ensures that the GUI creation and updates are done on the Event Dispatch Thread (EDT),
     * which is crucial for Swing applications to maintain responsiveness and thread safety.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(SiteDisplayApp::new);
    }
}