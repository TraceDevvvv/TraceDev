/**
 * Main class for the ActiveConvention application.
 * This application implements the use case for activating a convention request by a refreshment point.
 * It provides a GUI for an Agency Operator to enable activation, load data, check agreement, confirm, and process.
 * The application simulates server communication and handles interruptions.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
public class ActiveConvention {
    private static final Logger logger = Logger.getLogger(ActiveConvention.class.getName());
    private JFrame mainFrame;
    private JTextArea statusArea;
    private JButton enableButton;
    private JButton loadButton;
    private JButton checkButton;
    private JButton confirmButton;
    private JButton processButton;
    private boolean isEnabled = false;
    private boolean isDataLoaded = false;
    private boolean isAgreementChecked = false;
    private boolean isConfirmed = false;
    private boolean isServerConnected = true;
    /**
     * Constructor to set up the GUI and initialize components.
     */
    public ActiveConvention() {
        prepareGUI();
    }
    /**
     * Initializes the main GUI window and components.
     */
    private void prepareGUI() {
        mainFrame = new JFrame("ActiveConvention System");
        mainFrame.setSize(600, 400);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        enableButton = new JButton("1. Enable Activation");
        loadButton = new JButton("2. Load Convention Data");
        checkButton = new JButton("3. Check Agreement");
        confirmButton = new JButton("4. Confirm Activation");
        processButton = new JButton("5. Process Request");
        // Initially disable all buttons except enable
        loadButton.setEnabled(false);
        checkButton.setEnabled(false);
        confirmButton.setEnabled(false);
        processButton.setEnabled(false);
        buttonPanel.add(enableButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(processButton);
        // Status text area
        statusArea = new JTextArea(10, 50);
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);
        // Add components to frame
        mainFrame.add(buttonPanel, BorderLayout.NORTH);
        mainFrame.add(scrollPane, BorderLayout.CENTER);
        // Attach event listeners
        attachListeners();
        mainFrame.setVisible(true);
        logStatus("System started. Ready to enable activation.");
    }
    /**
     * Attaches action listeners to all buttons.
     */
    private void attachListeners() {
        enableButton.addActionListener(e -> enableActivation());
        loadButton.addActionListener(e -> loadData());
        checkButton.addActionListener(e -> checkAgreement());
        confirmButton.addActionListener(e -> confirmActivation());
        processButton.addActionListener(e -> processRequest());
    }
    /**
     * Step 1: Enable the activation function of the Convention.
     */
    private void enableActivation() {
        if (!isEnabled) {
            isEnabled = true;
            loadButton.setEnabled(true);
            logStatus("Activation function enabled. Proceed to load data.");
            enableButton.setEnabled(false);
        }
    }
    /**
     * Step 2: Load the data request of the Convention from the point of rest and display the form.
     */
    private void loadData() {
        if (isEnabled && !isDataLoaded) {
            // Simulate loading data from a server (ETOUR)
            if (isServerConnected) {
                isDataLoaded = true;
                checkButton.setEnabled(true);
                logStatus("Convention data loaded successfully. Displaying form.");
                // In a real application, display a form with loaded data
                showDataForm();
            } else {
                logStatus("Error: Connection to server ETOUR interrupted. Cannot load data.");
            }
        }
    }
    /**
     * Displays a simple form with loaded convention data (simulated).
     */
    private void showDataForm() {
        JFrame formFrame = new JFrame("Convention Data Form");
        formFrame.setSize(400, 300);
        formFrame.setLayout(new GridLayout(5, 2));
        // Simulated data fields
        String[] labels = {"Convention ID:", "Refreshment Point:", "Start Date:", "End Date:", "Status:"};
        String[] values = {"CONV123", "Point Alpha", "2023-10-01", "2023-10-10", "Pending Activation"};
        for (int i = 0; i < labels.length; i++) {
            formFrame.add(new JLabel(labels[i]));
            formFrame.add(new JTextField(values[i]));
        }
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> formFrame.dispose());
        formFrame.add(new JLabel());
        formFrame.add(closeButton);
        formFrame.setVisible(true);
    }
    /**
     * Step 3: Check the data of the agreement and decide for activation.
     */
    private void checkAgreement() {
        if (isDataLoaded && !isAgreementChecked) {
            // Simulate agreement check with a dialog
            int response = JOptionPane.showConfirmDialog(mainFrame,
                    "Do you agree to activate the convention based on the loaded data?",
                    "Agreement Check",
                    JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                isAgreementChecked = true;
                confirmButton.setEnabled(true);
                logStatus("Agreement checked and approved. Ready for confirmation.");
            } else {
                logStatus("Agreement not approved. Activation cancelled.");
                resetSystem();
            }
        }
    }
    /**
     * Step 4: Ask for confirmation of the activation.
     */
    private void confirmActivation() {
        if (isAgreementChecked && !isConfirmed) {
            int response = JOptionPane.showConfirmDialog(mainFrame,
                    "Are you sure you want to activate the convention?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                isConfirmed = true;
                processButton.setEnabled(true);
                logStatus("Activation confirmed. Proceed to process request.");
            } else {
                logStatus("Activation not confirmed. Process halted.");
                resetSystem();
            }
        }
    }
    /**
     * Step 5 and 6: Process the request and handle server communication.
     */
    private void processRequest() {
        if (isConfirmed) {
            // Simulate processing with a delay
            logStatus("Processing request...");
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                if (isServerConnected) {
                    // Simulate successful processing
                    logStatus("Request processed successfully. Convention activated.");
                    JOptionPane.showMessageDialog(mainFrame, "The system has notified the activation of the convention.");
                } else {
                    logStatus("Error: Connection to server ETOUR interrupted. Processing failed.");
                    JOptionPane.showMessageDialog(mainFrame, "Connection to server ETOUR lost. Please check network.");
                }
                resetSystem();
                executor.shutdown();
            }, 2, TimeUnit.SECONDS);
        }
    }
    /**
     * Logs messages to both the GUI status area and the console logger.
     * @param message The message to log.
     */
    private void logStatus(String message) {
        statusArea.append(message + "\n");
        logger.info(message);
    }
    /**
     * Resets the system to initial state (except server connection) for a new operation.
     */
    private void resetSystem() {
        isEnabled = false;
        isDataLoaded = false;
        isAgreementChecked = false;
        isConfirmed = false;
        enableButton.setEnabled(true);
        loadButton.setEnabled(false);
        checkButton.setEnabled(false);
        confirmButton.setEnabled(false);
        processButton.setEnabled(false);
        logStatus("System reset. Ready for a new operation.");
    }
    /**
     * Simulates a server connection interruption.
     * This can be triggered externally (for demonstration).
     */
    public void simulateServerInterruption() {
        isServerConnected = false;
        logStatus("!!! SERVER CONNECTION INTERRUPTED !!!");
    }
    /**
     * Main method to launch the application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Set up logging for the static logger
        logger.setLevel(Level.INFO);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO);
        logger.addHandler(handler);
        // Disable default parent handlers to prevent duplicate logging
        logger.setUseParentHandlers(false);
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            ActiveConvention app = new ActiveConvention();
            // Optionally simulate a server interruption after 30 seconds for demonstration
            Executors.newSingleThreadScheduledExecutor().schedule(
                    app::simulateServerInterruption, 30, TimeUnit.SECONDS);
        });
    }
}