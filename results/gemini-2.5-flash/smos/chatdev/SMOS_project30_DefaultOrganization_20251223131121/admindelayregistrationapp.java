'''
This class represents the main graphical user interface for the Administrator
to register student delays. It handles user input, communicates with a
simulated server, and displays status messages.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException; // Added for more specific exception handling in done()
import java.util.stream.Collectors;
public class AdminDelayRegistrationApp extends JFrame {
    // UI Components
    private JLabel dateLabel;
    private JTextArea studentDelayInputArea;
    private JScrollPane studentDelayScrollPane;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextArea logOutputArea;
    private JScrollPane logScrollPane;
    // Data for the current registration session
    private LocalDate registrationDate;
    /**
     * Constructor for AdminDelayRegistrationApp.
     * Sets up the main window and initializes all UI components.
     *
     * @param title The title of the application window.
     * @param selectedDate The date for which delays are being registered, passed from a prior action.
     */
    public AdminDelayRegistrationApp(String title, LocalDate selectedDate) {
        super(title);
        this.registrationDate = selectedDate;
        initUI();
    }
    /**
     * Initializes all graphical user interface components and sets up their layout and event listeners.
     */
    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // Center the window
        // Set up the main panel with a BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Top panel for date display
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dateLabel = new JLabel("Delay Registration Date: " + registrationDate.toString());
        dateLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        topPanel.add(dateLabel);
        // Center panel for student input
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Enter Student IDs/Names with Delay (one per line)"));
        studentDelayInputArea = new JTextArea(10, 40);
        studentDelayInputArea.setLineWrap(true);
        studentDelayInputArea.setWrapStyleWord(true);
        studentDelayScrollPane = new JScrollPane(studentDelayInputArea);
        centerPanel.add(studentDelayScrollPane, BorderLayout.CENTER);
        // Bottom panel for buttons and log
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save Delay Data");
        cancelButton = new JButton("Cancel Operation");
        // Add action listeners to buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDelayData();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interruptOperation();
            }
        });
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        // Log output area
        logOutputArea = new JTextArea(8, 50);
        logOutputArea.setEditable(false);
        logOutputArea.setLineWrap(true);
        logOutputArea.setWrapStyleWord(true);
        logOutputArea.setBorder(BorderFactory.createTitledBorder("System Log"));
        logScrollPane = new JScrollPane(logOutputArea);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(logScrollPane, BorderLayout.CENTER);
        // Add panels to the main frame
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        // Initial log message
        logMessage("Application started. Ready to register delays for " + registrationDate.toString() + ".");
    }
    /**
     * Handles the saving of delay data when the "Save" button is clicked.
     * This method simulates sending data to a server and sending notifications to parents.
     */
    private void saveDelayData() {
        // Disable buttons during server interaction to prevent multiple submissions
        setControlsEnabled(false);
        logMessage("Attempting to save delay data...");
        // Get student IDs/names from the input area, splitting by new line and filtering empty entries
        List<String> studentDelays = Arrays.stream(studentDelayInputArea.getText().split("\n"))
                                            .map(String::trim)
                                            .filter(s -> !s.isEmpty())
                                            .collect(Collectors.toList());
        if (studentDelays.isEmpty()) {
            logMessage("No student delays entered. Please enter student IDs/names.");
            setControlsEnabled(true);
            return;
        }
        // Simulate sending data to the server in a background thread to keep UI responsive
        new SwingWorker<Boolean, String>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    // Step 3: Send data to the server
                    logMessage("  - Sending delay data to server...");
                    boolean dataSent = ServerSimulator.sendDelayData(registrationDate, studentDelays);
                    if (dataSent) {
                        logMessage("  - Delay data sent successfully. Initiating parent notifications...");
                        // Simulate sending notifications to parents
                        boolean notificationsSent = ServerSimulator.sendNotificationsToParents(studentDelays);
                        if (notificationsSent) {
                            logMessage("  - Notifications sent to parents successfully.");
                            return true; // Success
                        } else {
                            logMessage("  - Failed to send notifications to parents.");
                            return false; // Partial failure due to simulated server error
                        }
                    } else {
                        logMessage("  - Failed to send delay data to server.");
                        return false; // Total failure due to simulated server error
                    }
                } catch (ServerConnectionException e) {
                    // Catches the specific exception for server connection issues
                    logMessage("  - ERROR: Connection to SMOS server interrupted. " + e.getMessage());
                    return false; // Indicate failure due to connection interruption
                }
            }
            @Override
            protected void done() {
                try {
                    // get() will return the result from doInBackground or throw an ExecutionException if doInBackground threw an unhandled exception.
                    // If ServerConnectionException is caught in doInBackground, get() will return false, which is handled here.
                    if (get() != null && get()) { // Check get() != null for robustness
                        logMessage("Delay data entry complete for " + studentDelays.size() + " students.");
                        // Postcondition: The delay data has been entered in the system.
                        // Postcondition: The system remains on the registry screen.
                        studentDelayInputArea.setText(""); // Clear the input for new entries
                    } else {
                        logMessage("Delay registration failed or was aborted. Please check the log for details.");
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    // This catch block handles exceptions unhandled by doInBackground (e.g., unexpected RuntimeExceptions not caught specifically there)
                    logMessage("An unexpected error occurred during save operation: " + (ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));
                    ex.printStackTrace();
                } finally {
                    setControlsEnabled(true); // Re-enable controls
                }
            }
        }.execute();
    }
    /**
     * Handles the interruption of the operation when the "Cancel" button is clicked.
     * This simulates interrupting server connection and potentially clearing the view.
     */
    private void interruptOperation() {
        logMessage("Administrator initiated operation interruption.");
        // Disable controls immediately to prevent further user input while interruption is processed.
        setControlsEnabled(false);
        // Simulate interrupting server connection in a background thread
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                String interruptionMessage;
                try {
                    logMessage("  - Attempting to interrupt connection to SMOS server...");
                    ServerSimulator.interruptConnection(); // This sets connectionActive to false
                    interruptionMessage = "SMOS Server connection successfully terminated.";
                    return true; // Connection successfully interrupted (simulated)
                } catch (Exception e) {
                    interruptionMessage = "  - Error interrupting connection: " + e.getMessage();
                    return false; // Failure to interrupt or already interrupted
                } finally {
                    logMessage(interruptionMessage); // Log the result of the interruption attempt.
                }
            }
            @Override
            protected void done() {
                try {
                    // Check the result from doInBackground(). If true, interruption was successful.
                    if (get()) {
                        logMessage("Operation interrupted. SMOS Server connection successfully terminated. Further data entry or saving will not be possible for this session.");
                        // As per postcondition, the SMOS server connection is now interrupted.
                        // Therefore, disable related controls as they will no longer function correctly.
                        saveButton.setEnabled(false);
                        studentDelayInputArea.setEnabled(false);
                        cancelButton.setEnabled(false); // No further interruption possible in this state
                        studentDelayInputArea.setText(""); // Clear any pending input
                    } else {
                        // If interruption was not successful (e.g., doInBackground returned false),
                        // re-enable all controls so the administrator can retry.
                        logMessage("Operation partially interrupted or encountered an issue during termination. Please re-attempt if necessary.");
                        setControlsEnabled(true);
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    // Catch any exceptions that occurred in doInBackground or during the SwingWorker's lifecycle.
                    // Ensure controls are re-enabled in case of an unexpected error, allowing user to retry.
                    logMessage("An unexpected error occurred during interruption: " + (ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));
                    ex.printStackTrace();
                    setControlsEnabled(true);
                }
            }
        }.execute();
    }
    /**
     * Appends a message to the log output area.
     * Ensures thread-safety for UI updates.
     *
     * @param message The message to log.
     */
    private void logMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            logOutputArea.append(System.lineSeparator() + "[APP] " + message);
            // Auto-scroll to the bottom
            logOutputArea.setCaretPosition(logOutputArea.getDocument().getLength());
        });
    }
    /**
     * Enables or disables the interactable controls (buttons, input area) on the form.
     * Used to prevent user input during background operations.
     *
     * @param enabled True to enable controls, false to disable.
     */
    private void setControlsEnabled(boolean enabled) {
        saveButton.setEnabled(enabled);
        cancelButton.setEnabled(enabled);
        studentDelayInputArea.setEnabled(enabled);
    }
}