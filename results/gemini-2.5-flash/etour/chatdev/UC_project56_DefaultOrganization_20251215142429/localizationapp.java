/**
 * Main application class for the LOCALIZATION use case.
 * Provides a GUI for the user to initiate a localization search
 * and displays the results from a simulated GPS system.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
public class LocalizationApp extends JFrame implements ActionListener {
    private final JButton localizeButton;
    private final JLabel statusLabel;
    private final JTextArea resultArea;
    private final GPSModule gpsModule;
    /**
     * Constructor for LocalizationApp.
     * Initializes the GUI components and sets up the GPS module.
     */
    public LocalizationApp() {
        super("Tourist Localization System"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        setSize(400, 300); // Set window size
        setLocationRelativeTo(null); // Center the window
        // Initialize GPS module
        gpsModule = new GPSModule();
        // Set up layout
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        // Panel for controls (button)
        JPanel controlPanel = new JPanel();
        localizeButton = new JButton("Start Localization");
        localizeButton.addActionListener(this); // Add action listener to the button
        controlPanel.add(localizeButton);
        add(controlPanel, BorderLayout.NORTH); // Add control panel to the top
        // Status label to show in-progress messages
        statusLabel = new JLabel("Click 'Start Localization' to begin.");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text
        add(statusLabel, BorderLayout.CENTER); // Add status label to the center
        // Result area to display detailed localization results
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false); // Make it read-only
        resultArea.setLineWrap(true); // Wrap lines
        resultArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(resultArea); // Add scroll capability
        add(scrollPane, BorderLayout.SOUTH); // Add scroll pane to the bottom
        // Make the frame visible
        setVisible(true);
    }
    /**
     * Handles action events from GUI components, specifically the "Start Localization" button.
     *
     * @param e The ActionEvent generated.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == localizeButton) {
            // Disable button to prevent multiple concurrent localizations
            localizeButton.setEnabled(false);
            resultArea.setText(""); // Clear previous results
            // Use SwingWorker to perform the time-consuming GPS calculation in a background thread,
            // keeping the GUI responsive. The second type parameter 'String' indicates the type
            // of intermediate results published (status messages).
            SwingWorker<Position, String> worker = new SwingWorker<>() {
                @Override
                protected Position doInBackground() {
                    // Entry Condition: A search or advanced search has begun (triggered by button click).
                    // Publish status messages to be displayed on the EDT via process()
                    publish("1. System requires position data...");
                    // Introduce a small artificial delay to make the status update visible before the next one,
                    // if the GPS calculation is very fast.
                    try {
                        Thread.sleep(200); 
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return Position.createFailed("Localization process interrupted while updating status.");
                    }
                    // Steps 2 and 3: The GPS calculates the position, and the system is on hold during this time.
                    publish("2. GPS is calculating position. System on hold until data is received...\n(Please wait)");
                    // This is where the actual GPS simulation (with delay) happens,
                    // representing the period the system is "on hold" waiting for data.
                    Position position = gpsModule.calculatePosition();
                    // The "System on hold" message was published before the calculation started,
                    // accurately reflecting the blocking period. Once calculatePosition() returns,
                    // the system has received its data, so no "on hold" message is needed after it.
                    return position; // Return the calculated position
                }
                @Override
                protected void process(List<String> chunks) {
                    // This method runs on the EDT and updates the statusLabel with published messages.
                    // We take the last message, assuming it's the most current status.
                    if (!chunks.isEmpty()) {
                        statusLabel.setText(chunks.get(chunks.size() - 1));
                    }
                }
                @Override
                protected void done() {
                    try {
                        // Exit Condition: The system receives the position of the tourist OR
                        // the position is not detectable by GPS.
                        Position result = get(); // Get the result from doInBackground()
                        resultArea.setText(result.toString()); // Display the result
                        if (result.isSuccess()) {
                            statusLabel.setText("Localization complete: Position received.");
                        } else {
                            statusLabel.setText("Localization failed: " + result.getStatusMessage());
                        }
                    } catch (InterruptedException ex) {
                        statusLabel.setText("Localization interrupted: " + ex.getMessage());
                        resultArea.setText("Error: " + ex.getMessage());
                        Thread.currentThread().interrupt(); // Restore interrupt status
                    } catch (ExecutionException ex) {
                        statusLabel.setText("Localization error: " + ex.getCause().getMessage());
                        resultArea.setText("Error during localization: " + ex.getCause().getMessage());
                    } finally {
                        // Re-enable the button regardless of success or failure
                        localizeButton.setEnabled(true);
                    }
                }
            };
            worker.execute(); // Start the background worker
        }
    }
    /**
     * Main method to start the LocalizationApp.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(LocalizationApp::new);
    }
}