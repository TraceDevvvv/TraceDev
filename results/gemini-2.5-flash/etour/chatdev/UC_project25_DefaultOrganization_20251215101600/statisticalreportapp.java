/**
 * Main application class for the Statistical Report Viewer.
 * Implements the VISUALIZZAREPORTSTATISTICI use case with a Swing GUI.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
public class StatisticalReportApp extends JFrame {
    // UI Components
    private JComboBox<Location> locationComboBox;
    private JButton loadLocationsButton;
    private JButton generateReportButton;
    private JTextArea reportTextArea;
    private JLabel statusLabel;
    private JCheckBox simulateErrorCheckBox; // Checkbox to simulate connection error
    // Service layer
    private RicercaSitoService ricercaSitoService;
    // Simulated login state for the Agency Operator
    // Change initial value to false to simulate a login action
    private boolean agencyOperatorLoggedIn = false; 
    /**
     * Constructs the main application window and initializes its components.
     */
    public StatisticalReportApp() {
        super("Statistical Report Viewer");
        ricercaSitoService = new RicercaSitoService();
        initUI();
        // Simulate the agency operator logging in as per the use case entry condition.
        // This makes the fulfillment of the entry condition explicit.
        simulateLogin();
        // Initially, locations aren't loaded yet. The load button activates the feature.
        updateUIState(false); 
    }
    /**
     * Initializes all graphical user interface components and sets up the layout.
     */
    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        // Set a modern look and feel for Swing
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Could not set system look and feel: " + e.getMessage());
        }
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Top Panel for controls (load, selection, generate)
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        loadLocationsButton = new JButton("1. Load Locations");
        locationComboBox = new JComboBox<>();
        generateReportButton = new JButton("3. Generate Report");
        simulateErrorCheckBox = new JCheckBox("Simulate Connection Error");
        controlPanel.add(loadLocationsButton);
        controlPanel.add(new JLabel("2. Select Location:"));
        controlPanel.add(locationComboBox);
        controlPanel.add(generateReportButton);
        controlPanel.add(simulateErrorCheckBox);
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        // Center Panel for Report Display
        reportTextArea = new JTextArea();
        reportTextArea.setEditable(false);
        reportTextArea.setWrapStyleWord(true); // Wrap words, not just characters
        reportTextArea.setLineWrap(true);      // Enable line wrapping
        reportTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Statistical Report"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Bottom Panel for Status Messages
        statusLabel = new JLabel("Welcome! Agency operator logged in."); // Initial message updated by simulateLogin()
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(statusLabel);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        add(mainPanel);
        // Add Action Listeners
        loadLocationsButton.addActionListener(this::handleLoadLocations);
        generateReportButton.addActionListener(this::handleGenerateReport);
        simulateErrorCheckBox.addActionListener(e -> ricercaSitoService.setSimulateConnectionError(simulateErrorCheckBox.isSelected()));
    }
    /**
     * Simulates the agency operator logging in, fulfilling the use case entry condition.
     * In a real application, this would involve a login screen and credential validation.
     * Here, it simply sets the logged-in state and provides a status message.
     */
    private void simulateLogin() {
        this.agencyOperatorLoggedIn = true;
        // Provide a clearer initial message reflecting the login action.
        displayStatus("Agency operator logged in successfully. Please load locations to begin.", Color.BLUE);
    }
    /**
     * Updates the state of UI components (buttons, combobox) based on whether locations are loaded.
     * @param locationsLoaded true if locations have been successfully loaded, false otherwise.
     */
    private void updateUIState(boolean locationsLoaded) {
        locationComboBox.setEnabled(locationsLoaded);
        generateReportButton.setEnabled(locationsLoaded && locationComboBox.getItemCount() > 0);
        loadLocationsButton.setEnabled(true); // Always able to retry loading locations
    }
    /**
     * Handles the action event for the "Load Locations" button.
     * This corresponds to step 1 and 2 of the use case flow.
     * It fetches locations from the service and populates the JComboBox.
     * @param e ActionEvent triggered by the button.
     */
    private void handleLoadLocations(ActionEvent e) {
        if (!agencyOperatorLoggedIn) {
            displayStatus("Error: Agency operator not logged in.", Color.RED);
            return;
        }
        displayStatus("Loading locations...", Color.ORANGE);
        loadLocationsButton.setEnabled(false); // Disable button during loading
        // Perform network operation in a background thread to keep GUI responsive
        SwingWorker<List<Location>, Void> worker = new SwingWorker<List<Location>, Void>() {
            @Override
            protected List<Location> doInBackground() throws IOException {
                // Simulate step 2: Upload the list of places. This is where getAllLocations is called.
                return ricercaSitoService.getAllLocations();
            }
            @Override
            protected void done() {
                try {
                    List<Location> locations = get(); // Get the result from doInBackground
                    locationComboBox.removeAllItems();
                    if (locations.isEmpty()) {
                        displayStatus("No locations found.", Color.ORANGE);
                        updateUIState(false);
                    } else {
                        for (Location loc : locations) {
                            locationComboBox.addItem(loc);
                        }
                        displayStatus("Locations loaded successfully. Please select a location.", Color.BLUE);
                        updateUIState(true);
                    }
                } catch (Exception ex) {
                    // Handle exceptions from doInBackground (e.g., IOException, InterruptedException, ExecutionException)
                    Throwable cause = ex.getCause() != null ? ex.getCause() : ex;
                    // Exit condition: Interruption of connection to the server
                    displayStatus("Failed to load locations: " + cause.getMessage(), Color.RED);
                    updateUIState(false);
                } finally {
                    loadLocationsButton.setEnabled(true); // Re-enable button
                }
            }
        };
        worker.execute();
    }
    /**
     * Handles the action event for the "Generate Report" button.
     * This corresponds to step 3 and 4 of the use case flow.
     * It retrieves the selected location, fetches its statistical data, and displays the report.
     * @param e ActionEvent triggered by the button.
     */
    private void handleGenerateReport(ActionEvent e) {
        Location selectedLocation = (Location) locationComboBox.getSelectedItem();
        if (selectedLocation == null) {
            displayStatus("Please select a location first.", Color.RED);
            reportTextArea.setText("Please select a location from the dropdown menu to generate a report.");
            return;
        }
        displayStatus("Generating report for " + selectedLocation.getName() + "...", Color.ORANGE);
        generateReportButton.setEnabled(false); // Disable button during generation
        // Perform network operation in a background thread
        SwingWorker<StatisticalReport, Void> worker = new SwingWorker<StatisticalReport, Void>() {
            @Override
            protected StatisticalReport doInBackground() throws Exception {
                // Simulate step 4: Upload midsize site feedback and prepare statistical report.
                return ricercaSitoService.getStatisticalDataForLocation(selectedLocation.getId());
            }
            @Override
            protected void done() {
                try {
                    StatisticalReport report = get(); // Get the result from doInBackground
                    displayReport(report); // Display the report
                    displayStatus("Statistical report generated successfully for " + selectedLocation.getName() + ".", Color.GREEN);
                } catch (Exception ex) {
                    // Handle exceptions from doInBackground (e.g., IOException, IllegalArgumentException, InterruptedException, ExecutionException)
                    Throwable cause = ex.getCause() != null ? ex.getCause() : ex;
                    // Exit condition: Interruption of connection to the server
                    displayStatus("Failed to generate report: " + cause.getMessage(), Color.RED);
                    reportTextArea.setText("Error generating report:\n" + cause.getMessage() + "\n\n" +
                                           "Please try again or check your connection.");
                } finally {
                    generateReportButton.setEnabled(true); // Re-enable button
                }
            }
        };
        worker.execute();
    }
    /**
     * Displays the generated statistical report in the text area.
     * This fulfills an exit condition of the use case.
     * @param report The StatisticalReport object to display.
     */
    private void displayReport(StatisticalReport report) {
        // Exit condition: The system displays the statistical report for the selected location.
        reportTextArea.setText(report.toString());
        reportTextArea.setCaretPosition(0); // Scroll to the top
    }
    /**
     * Displays a status message in the status label at the bottom of the window.
     * @param message The message to display.
     * @param color The color of the message text.
     */
    private void displayStatus(String message, Color color) {
        statusLabel.setForeground(color);
        statusLabel.setText(message);
    }
    /**
     * Main method to run the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            StatisticalReportApp app = new StatisticalReportApp();
            app.setVisible(true);
        });
    }
}