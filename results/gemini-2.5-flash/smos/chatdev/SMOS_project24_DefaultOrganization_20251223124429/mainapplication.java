'''
The main application class for the "View Teaching Details" use case.
This class sets up the main window, simulates a list of teachings (via a dropdown),
and provides a button to view the details of a selected teaching.
It interacts with the TeachingService to fetch data and TeachingDetailsView to display it.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException; // Explicitly import for SwingWorker error handling
/**
 * The main application class for the "View Teaching Details" use case.
 * This class sets up the main window, simulates a list of teachings (via a dropdown),
 * and provides a button to view the details of a selected teaching.
 * It interacts with the TeachingService to fetch data and TeachingDetailsView to display it.
 */
public class MainApplication extends JFrame {
    private TeachingService teachingService;
    private JComboBox<String> teachingIdSelector;
    private TeachingDetailsView detailsView;
    private JProgressBar progressBar;
    // Button to fetch and display details
    private JButton fetchDetailsButton;
    /**
     * Constructs the main application window.
     * Initializes the service, GUI components, and sets up event listeners.
     */
    public MainApplication() {
        super("Teaching Details Viewer (Administrator)");
        teachingService = new TeachingService();
        initComponents();
        setupLayout();
        setupListeners(); // Call setupListeners after components are initialized and laid out
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure JVM exits on close
        pack(); // Pack components to their preferred sizes
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible
    }
    /**
     * Initializes the GUI components of the application.
     */
    private void initComponents() {
        // Simulate a logged-in user viewing a list of teachings by providing sample IDs
        String[] sampleTeachingIds = {"T001", "T002", "T003", "T004 (Not Found)"};
        teachingIdSelector = new JComboBox<>(sampleTeachingIds);
        teachingIdSelector.setPreferredSize(new Dimension(200, 30));
        fetchDetailsButton = new JButton("View Details"); // Initialize the button here
        detailsView = new TeachingDetailsView();
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Indeterminate progress bar for unknown duration tasks
        progressBar.setVisible(false); // Hidden by default
    }
    /**
     * Sets up the layout of the GUI components within the main frame.
     */
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10)); // Outer layout with some padding
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Select Teaching"));
        controlPanel.add(new JLabel("Teaching ID:"));
        controlPanel.add(teachingIdSelector);
        controlPanel.add(fetchDetailsButton); // Add the initialized fetchDetailsButton
        add(controlPanel, BorderLayout.NORTH);
        add(detailsView, BorderLayout.CENTER); // Details view takes center space
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(progressBar);
        add(statusPanel, BorderLayout.SOUTH);
    }
    /**
     * Sets up event listeners for interactive components.
     * The primary listener is for the "View Details" button.
     */
    private void setupListeners() {
        // Add action listener to the "View Details" button
        fetchDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTeachingDetails();
            }
        });
    }
    /**
     * Handles the action of viewing teaching details.
     * This method is triggered when the "View Details" button is clicked.
     * It fetches data from the TeachingService in a background thread to keep the GUI responsive.
     */
    private void viewTeachingDetails() {
        String selectedTeachingId = (String) teachingIdSelector.getSelectedItem();
        if (selectedTeachingId == null || selectedTeachingId.trim().isEmpty()) {
            detailsView.displayErrorMessage("Please select a teaching ID.");
            return;
        }
        // Extract clean ID, removing "(Not Found)" if present, for service call
        String idToFetch = selectedTeachingId.split(" ")[0];
        // Disable UI elements during fetch to prevent multiple requests and maintain UI consistency
        fetchDetailsButton.setEnabled(false);
        teachingIdSelector.setEnabled(false);
        // Use SwingWorker to perform potentially long-running operations in a background thread
        // to prevent freezing the UI.
        SwingWorker<Teaching, Void> worker = new SwingWorker<>() {
            @Override
            protected Teaching doInBackground() throws TeachingService.ConnectionInterruptedException {
                // Show progress bar while fetching. Must be done on EDT.
                SwingUtilities.invokeLater(() -> progressBar.setVisible(true));
                // Simulate the "user click on the 'Teaching details' button" event
                // and the "system displays the detailed information" sequence.
                return teachingService.getTeachingDetails(idToFetch);
            }
            @Override
            protected void done() {
                // Re-enable UI elements when done. Must be done on EDT.
                fetchDetailsButton.setEnabled(true);
                teachingIdSelector.setEnabled(true);
                // Hide progress bar when done. Must be done on EDT.
                progressBar.setVisible(false);
                try {
                    Teaching teaching = get(); // Get the result from doInBackground()
                    if (teaching != null) {
                        detailsView.displayTeachingDetails(teaching);
                    } else {
                        // This case covers when teachingId is valid but not found in the mock database
                        detailsView.displayErrorMessage("Teaching with ID '" + idToFetch + "' not found.");
                    }
                } catch (TeachingService.ConnectionInterruptedException e) {
                    // This catches the simulated SMOS server interruption
                    detailsView.displayErrorMessage("SMOS Server Error: " + e.getMessage());
                    // Postcondition: Connection to the interrupted SMOS server (simulated) is logged/handled here.
                } catch (ExecutionException e) {
                    // This catches exceptions thrown by doInBackground that are not ConnectionInterruptedException
                    // ExecutionException wraps any exception thrown by doInBackground(). We get the cause.
                    Throwable cause = e.getCause();
                     if (cause instanceof TeachingService.ConnectionInterruptedException) {
                         // If the cause is our specific connection interruption, handle it as such.
                         detailsView.displayErrorMessage("SMOS Server Error: " + cause.getMessage());
                     } else {
                         // Catch other potential runtime exceptions/errors from doInBackground
                         detailsView.displayErrorMessage("An error occurred during data retrieval: " + (cause != null ? cause.getMessage() : "Unknown error"));
                         cause.printStackTrace(); // Log the full stack trace for debugging
                     }
                } catch (InterruptedException e) {
                    // This catches if the worker thread was interrupted while waiting for an operation to complete,
                    // e.g., if get() was called on an interrupted thread
                    detailsView.displayErrorMessage("Data retrieval was interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                } catch (Exception e) { // Catch any other unexpected exceptions that might occur in 'done()'
                    detailsView.displayErrorMessage("An unexpected error occurred during result processing: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        worker.execute(); // Start the background task
    }
    /**
     * Main method to launch the application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(MainApplication::new);
    }
}