import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This is the main application class for the "VISUALIZZASTATISTICHEPERSONALI" use case.
 * It creates a Java Swing GUI that allows a Point Of Restaurant Operator to view personal statistics.
 * It handles user interaction (button click) to trigger loading and displaying statistics,
 * and simulates conditions like server connection interruption.
 */
public class RestaurantStatisticsApp extends JFrame {
    private StatisticsPanel statisticsPanel;
    private JButton viewStatisticsButton;
    /**
     * Constructs the main application window (JFrame).
     * Initializes the GUI components, sets up layout, and adds event listeners.
     */
    public RestaurantStatisticsApp() {
        // Set up the main window properties
        setTitle("Point Of Restaurant Statistics");
        setSize(500, 400); // Increased size slightly to accommodate data
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        // Use BorderLayout for the main frame
        setLayout(new BorderLayout(10, 10)); // 10px horizontal and vertical gaps
        // Panel for the button
        JPanel controlPanel = new JPanel();
        viewStatisticsButton = new JButton("View Personal Statistics");
        controlPanel.add(viewStatisticsButton);
        add(controlPanel, BorderLayout.NORTH); // Place button at the top
        // Statistics display panel
        statisticsPanel = new StatisticsPanel();
        add(statisticsPanel, BorderLayout.CENTER); // Place statistics in the center
        // Set up action listener for the button
        viewStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the button is clicked, simulate loading the statistics
                // This task should ideally run in a background thread to prevent GUI freezing.
                viewStatisticsButton.setEnabled(false); // Disable button during loading
                statisticsPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // Show wait cursor
                // Use SwingWorker for background processing to keep the UI responsive
                new SwingWorker<StatisticsData, Void>() {
                    @Override
                    protected StatisticsData doInBackground() throws Exception {
                        // Simulate data loading, which might throw an exception (e.g., connection interruption)
                        return statisticsPanel.loadStatistics();
                    }
                    @Override
                    protected void done() {
                        try {
                            StatisticsData data = get(); // Get the result from doInBackground
                            statisticsPanel.displayStatistics(data); // Display the data
                        } catch (Exception ex) {
                            // This catch block handles exceptions thrown within doInBackground (e.g., simulated connection error)
                            // The cause message is often more specific for exceptions wrapped by SwingWorker.
                            String errorMessage = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
                            statisticsPanel.showError("Error processing statistics: " + errorMessage);
                        } finally {
                            viewStatisticsButton.setEnabled(true); // Re-enable button
                            statisticsPanel.setCursor(Cursor.getDefaultCursor()); // Restore default cursor
                        }
                    }
                }.execute(); // Execute the SwingWorker
            }
        });
        // Initial state: The StatisticsPanel constructor sets its default "Ready" status and hides statistics labels.
        // The user is then prompted via the button to view statistics.
    }
    /**
     * Main method to start the application.
     * It ensures that the GUI creation and updates are done on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Entry condition: assumes the operator has successfully authenticated.
                // The application starts directly presenting the option to view statistics.
                RestaurantStatisticsApp app = new RestaurantStatisticsApp();
                app.setVisible(true); // Make the main window visible
            }
        });
    }
}