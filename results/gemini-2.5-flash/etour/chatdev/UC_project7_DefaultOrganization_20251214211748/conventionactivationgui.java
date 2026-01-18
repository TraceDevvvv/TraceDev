'''
This class provides the Graphical User Interface (GUI) for the ATTIVACONVENZIONE use case.
It allows an Agency Operator to load, review, confirm, and activate a convention
request from a refreshment point. It handles user interaction, displays convention
data, and manages the flow of events as described in the use case.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * This class provides the Graphical User Interface (GUI) for the ATTIVACONVENZIONE use case.
 * It allows an Agency Operator to load, review, confirm, and activate a convention
 * request from a refreshment point. It handles user interaction, displays convention
 * data, and manages the flow of events as described in the use case.
 */
public class ConventionActivationGUI extends JFrame {
    // UI Components
    private JTextField refreshmentPointIdField;
    private JButton loadButton;
    private JLabel conventionIdLabel;
    private JLabel conventionNameLabel;
    private JLabel conventionRefreshmentPointLabel;
    private JLabel conventionIsActiveLabel;
    private JLabel conventionStatusLabel;
    private JButton activateButton;
    private JButton resetButton;
    private JTextArea messageArea;
    private JScrollPane scrollPane;
    // Service for backend operations
    private ConventionService conventionService;
    // Currently loaded convention
    private Convention currentConvention;
    // Executor for handling long-running tasks without freezing the UI
    private ExecutorService executorService;
    /**
     * Constructor for ConventionActivationGUI.
     * Initializes the service and GUI components.
     */
    public ConventionActivationGUI() {
        super("ATTIVACONVENZIONE - Convention Activation");
        conventionService = new ConventionService();
        executorService = Executors.newSingleThreadExecutor();
        initializeUI();
        setupListeners();
        resetUI(); // Initialize buttons and fields to their default state
    }
    /**
     * Initializes all the Swing GUI components and sets up the layout.
     */
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null); // Center the window
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Top Panel for input
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        inputPanel.add(new JLabel("Refreshment Point ID:"));
        refreshmentPointIdField = new JTextField(15);
        inputPanel.add(refreshmentPointIdField);
        loadButton = new JButton("Load Convention Data");
        inputPanel.add(loadButton);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        // Center Panel for Convention Details
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Convention Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        // Label 1: Convention ID
        gbc.gridx = 0; gbc.gridy = 0;
        detailsPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        conventionIdLabel = new JLabel("N/A");
        detailsPanel.add(conventionIdLabel, gbc);
        // Label 2: Convention Name
        gbc.gridx = 0; gbc.gridy = 1;
        detailsPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        conventionNameLabel = new JLabel("N/A");
        detailsPanel.add(conventionNameLabel, gbc);
        // Label 3: Refreshment Point ID
        gbc.gridx = 0; gbc.gridy = 2;
        detailsPanel.add(new JLabel("Refreshment Point:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        conventionRefreshmentPointLabel = new JLabel("N/A");
        detailsPanel.add(conventionRefreshmentPointLabel, gbc);
        // Label 4: Is Active (boolean)
        gbc.gridx = 0; gbc.gridy = 3;
        detailsPanel.add(new JLabel("Active:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        conventionIsActiveLabel = new JLabel("N/A");
        detailsPanel.add(conventionIsActiveLabel, gbc);
        // Label 5: Status (string)
        gbc.gridx = 0; gbc.gridy = 4;
        detailsPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        conventionStatusLabel = new JLabel("N/A");
        detailsPanel.add(conventionStatusLabel, gbc);
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        activateButton = new JButton("Activate Convention");
        detailsPanel.add(activateButton, gbc);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        // Bottom Panel for Messages and Reset
        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        messageArea = new JTextArea(5, 40);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Messages"));
        southPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        resetButton = new JButton("Reset");
        buttonPanel.add(resetButton);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Sets up action listeners for all interactive UI components.
     */
    private void setupListeners() {
        loadButton.addActionListener(e -> loadConventionData()); // Step 2
        activateButton.addActionListener(e -> decideAndActivate()); // Step 3 & 4 initiate
        resetButton.addActionListener(e -> resetUI());
    }
    /**
     * Resets the UI components to their initial state.
     * Disables buttons, clears fields, and resets labels.
     */
    private void resetUI() {
        refreshmentPointIdField.setText("");
        conventionIdLabel.setText("N/A");
        conventionNameLabel.setText("N/A");
        conventionRefreshmentPointLabel.setText("N/A");
        conventionIsActiveLabel.setText("N/A");
        conventionStatusLabel.setText("N/A");
        messageArea.setText("Enter Refreshment Point ID and click 'Load'.");
        loadButton.setEnabled(true);
        activateButton.setEnabled(false);
        currentConvention = null;
    }
    /**
     * Loads convention data based on the entered refreshment point ID.
     * This corresponds to flow of events step 2.
     * Runs in a separate thread to prevent UI freezing.
     */
    private void loadConventionData() {
        String rpId = refreshmentPointIdField.getText().trim();
        if (rpId.isEmpty()) {
            showError("Please enter a Refreshment Point ID.");
            return;
        }
        loadButton.setEnabled(false);
        activateButton.setEnabled(false);
        showMessage("Loading convention data for " + rpId + "...");
        executorService.submit(() -> {
            try {
                // Simulates step 2: Load the data request
                Convention loadedConvention = conventionService.loadConventionRequest(rpId);
                SwingUtilities.invokeLater(() -> {
                    if (loadedConvention != null) {
                        currentConvention = loadedConvention;
                        updateConventionDisplay();
                        if ("PENDING_ACTIVATION".equals(currentConvention.getStatus())) {
                            showMessage("Convention '" + currentConvention.getName() + "' loaded successfully. Ready for activation.");
                            activateButton.setEnabled(true); // Enable activation if pending
                        } else if ("ACTIVE".equals(currentConvention.getStatus())) {
                            showMessage("Convention '" + currentConvention.getName() + "' is already active.");
                            activateButton.setEnabled(false); // Can't activate if already active
                        } else {
                            showMessage("Convention '" + currentConvention.getName() + "' status is " + currentConvention.getStatus() + ". Cannot activate.");
                            activateButton.setEnabled(false);
                        }
                    } else {
                        currentConvention = null;
                        updateConventionDisplay();
                        showError("No pending convention request found for Refreshment Point ID: " + rpId);
                        activateButton.setEnabled(false);
                    }
                    loadButton.setEnabled(true);
                });
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                SwingUtilities.invokeLater(() -> {
                    showError("Loading interrupted: " + ex.getMessage());
                    loadButton.setEnabled(true);
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    showError("An unexpected error occurred during loading: " + ex.getMessage());
                    loadButton.setEnabled(true);
                });
            }
        });
    }
    /**
     * Updates the display labels with the details of the currentConvention.
     * If currentConvention is null, labels are reset to "N/A".
     */
    private void updateConventionDisplay() {
        if (currentConvention != null) {
            conventionIdLabel.setText(currentConvention.getId());
            conventionNameLabel.setText(currentConvention.getName());
            conventionRefreshmentPointLabel.setText(currentConvention.getRefreshmentPointId());
            conventionIsActiveLabel.setText(String.valueOf(currentConvention.isActive()));
            conventionStatusLabel.setText(currentConvention.getStatus());
        } else {
            conventionIdLabel.setText("N/A");
            conventionNameLabel.setText("N/A");
            conventionRefreshmentPointLabel.setText("N/A");
            conventionIsActiveLabel.setText("N/A");
            conventionStatusLabel.setText("N/A");
        }
    }
    /**
     * Checks data and asks for activation confirmation.
     * This combines flow of events step 3 and 4.
     */
    private void decideAndActivate() {
        // Step 3: Check data and decide for activation (Operator makes this decision visually)
        if (currentConvention == null) {
            showError("No convention data loaded to activate.");
            return;
        }
        if (!"PENDING_ACTIVATION".equals(currentConvention.getStatus())) {
            showError("Convention is not in 'PENDING_ACTIVATION' status. Cannot activate.");
            activateButton.setEnabled(false);
            return;
        }
        // Step 4: Asks for confirmation of the activation.
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Do you really want to activate convention '" + currentConvention.getName() + "'?",
                "Confirm Activation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (confirmation == JOptionPane.YES_OPTION) {
            confirmOperation(); // Step 5
        } else {
            showMessage("Convention activation cancelled by operator.");
        }
    }
    /**
     * Confirms the operation and proceeds with processing the request.
     * This corresponds to flow of events step 5.
     * Runs in a separate thread to not block the UI.
     */
    private void confirmOperation() {
        activateButton.setEnabled(false);
        loadButton.setEnabled(false);
        showMessage("Confirmation received. Processing activation request...");
        processRequest(); // Step 6
    }
    /**
     * Processes the activation request for the current convention.
     * This corresponds to flow of events step 6.
     * Handles ETOUR connection issues and notifies activation.
     */
    private void processRequest() {
        executorService.submit(() -> {
            try {
                // Step 6: Processing the request.
                Convention activatedConvention = conventionService.activateConvention(currentConvention);
                SwingUtilities.invokeLater(() -> {
                    currentConvention = activatedConvention;
                    updateConventionDisplay();
                    showMessage("Convention '" + currentConvention.getName() + "' successfully activated!");
                    // Exit condition: The system shall notify the activation of the convention.
                    conventionService.notifyActivation(currentConvention);
                    resetButton.setEnabled(true);
                });
            } catch (ETOURConnectionException ex) {
                // Exit condition: Interruption of the connection to the server ETOUR.
                SwingUtilities.invokeLater(() -> {
                    showError("Activation failed: " + ex.getMessage());
                    resetButton.setEnabled(true);
                });
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                SwingUtilities.invokeLater(() -> {
                    showError("Activation processing interrupted: " + ex.getMessage());
                    resetButton.setEnabled(true);
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    showError("An unexpected error occurred during activation: " + ex.getMessage());
                    resetButton.setEnabled(true);
                });
            } finally {
                // Re-enable load button in case user wants to try another one.
                // The activate button's state should remain disabled after any activation attempt (success or failure)
                // for robustness. The user can then load a new convention or use the Reset button.
                SwingUtilities.invokeLater(() -> {
                    loadButton.setEnabled(true);
                });
            }
        });
    }
    /**
     * Displays an error message in the message area and as a pop-up.
     *
     * @param message The error message to display.
     */
    private void showError(String message) {
        messageArea.append("ERROR: " + message + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength()); // Scroll to bottom
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Displays a general message in the message area.
     *
     * @param message The message to display.
     */
    private void showMessage(String message) {
        messageArea.append("INFO: " + message + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength()); // Scroll to bottom
    }
}