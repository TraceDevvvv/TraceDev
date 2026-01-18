/*
AdminDashboardFrame.java - Represents the main GUI window for the administrator.
This frame allows the administrator to view a list of pending registration requests
and handles interactions with a simulated backend service.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException; // Import for SwingWorker get() method
public class AdminDashboardFrame extends JFrame {
    // JTextArea to display the list of registration requests
    private JTextArea requestDisplayArea;
    // Instance of RegistrationService to fetch requests from a simulated server
    private RegistrationService registrationService;
    /**
     * Constructor for AdminDashboardFrame.
     * Initializes the GUI components, sets up the layout, and adds event listeners.
     */
    public AdminDashboardFrame() {
        // Set the title of the frame
        setTitle("Administrator Dashboard - View Registration Requests");
        // Set the default size of the frame
        setSize(600, 400);
        // Define the default close operation (exit the application when closed)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the frame on the screen
        setLocationRelativeTo(null);
        // Initialize the RegistrationService
        registrationService = new RegistrationService();
        // Create a main panel with BorderLayout to organize components
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Create a panel for the button at the top
        JPanel buttonPanel = new JPanel();
        // Create the "View list request list" button
        JButton viewRequestsButton = new JButton("View list request list");
        buttonPanel.add(viewRequestsButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH); // Add button panel to the top
        // Initialize the JTextArea for displaying requests
        requestDisplayArea = new JTextArea();
        requestDisplayArea.setEditable(false); // Make it read-only
        requestDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Set a monospaced font for better alignment
        // Add the JTextArea to a JScrollPane to allow scrolling if content exceeds visible area
        JScrollPane scrollPane = new JScrollPane(requestDisplayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the center
        // Add an ActionListener to the viewRequestsButton
        viewRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to display registration requests when the button is clicked
                displayRegistrationRequests();
            }
        });
        // Add the main panel to the frame
        add(mainPanel);
    }
    /**
     * Fetches and displays the registration requests on a background thread.
     * This method utilizes SwingWorker to prevent blocking the Event Dispatch Thread (EDT)
     * and maintains UI responsiveness. It simulates interaction with a backend service (SMOS server)
     * and handles potential connection interruptions.
     */
    private void displayRegistrationRequests() {
        // Show a loading message immediately to indicate an action is in progress
        requestDisplayArea.setText("Fetching pending registration requests...\n");
        // Create a SwingWorker to perform the network operation in a background thread
        SwingWorker<List<RegistrationRequest>, Void> worker = new SwingWorker<List<RegistrationRequest>, Void>() {
            /**
             * Performs the long-running task of fetching requests in a background thread.
             * @return A list of pending registration requests.
             * @throws ConnectionInterruptedException if the simulated connection fails.
             * @throws Exception for any other unexpected errors.
             */
            @Override
            protected List<RegistrationRequest> doInBackground() throws ConnectionInterruptedException, Exception {
                // Attempt to fetch the list of pending requests
                return registrationService.fetchPendingRequests();
            }
            /**
             * Executes on the Event Dispatch Thread (EDT) after doInBackground() completes.
             * Updates the GUI with the results or error messages.
             */
            @Override
            protected void done() {
                try {
                    // Get the result from doInBackground(). This will re-throw any exceptions
                    List<RegistrationRequest> requests = get();
                    // Build the string to display in the text area
                    StringBuilder sb = new StringBuilder();
                    sb.append("--- Pending Registration Requests ---\n");
                    // Check if any requests were returned
                    if (requests.isEmpty()) {
                        sb.append("No pending requests found.\n");
                    } else {
                        // Append each request's details to the string builder
                        for (RegistrationRequest req : requests) {
                            sb.append(req.toString()).append("\n");
                        }
                    }
                    // Update the JTextArea with the fetched requests on the EDT
                    requestDisplayArea.setText(sb.toString());
                } catch (InterruptedException | ExecutionException e) {
                    // Extract the actual cause of the exception
                    Throwable cause = e.getCause();
                    String errorMessage;
                    if (cause instanceof ConnectionInterruptedException) {
                        errorMessage = "Error: " + cause.getMessage() + "\nPlease try again later.";
                        JOptionPane.showMessageDialog(AdminDashboardFrame.this, cause.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
                    } else if (cause != null) {
                        errorMessage = "An unexpected error occurred: " + cause.getMessage();
                        JOptionPane.showMessageDialog(AdminDashboardFrame.this, "An unexpected error occurred: " + cause.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // If cause is null, it's likely an InterruptedException or problem within SwingWorker itself
                        errorMessage = "An unexpected error occurred: " + e.getMessage();
                        JOptionPane.showMessageDialog(AdminDashboardFrame.this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // Display the error message in the text area
                    requestDisplayArea.setText(errorMessage);
                }
            }
        };
        // Execute the SwingWorker to start the background task
        worker.execute();
    }
}