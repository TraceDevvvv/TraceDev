package com.agency.touristmanager;
/**
 * Main GUI class for the Tourist Account Activation/Deactivation system.
 * This class sets up the graphical interface, handles user interactions,
 * and manages the tourist account states.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
public class TouristAccountGUI extends JFrame {
    private JComboBox<String> touristComboBox;
    private JLabel currentStatusLabel;
    private JButton activateButton;
    private JButton deactivateButton;
    private JButton disconnectButton;
    private JButton reconnectButton;
    private JPanel mainPanel;
    private JTextArea logArea;
    // Simulated database of tourists (ID -> Tourist object)
    private HashMap<String, Tourist> touristDatabase;
    private boolean isConnected = true;
    /**
     * Constructor sets up the GUI components and initializes the tourist database.
     */
    public TouristAccountGUI() {
        setTitle("Tourist Account Manager - Agency Operator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null); // Center the window
        // Initialize the database with sample data
        initializeDatabase();
        // Create and set up the main panel
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Top Panel: Tourist selection and current status
        JPanel topPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Tourist Account Details"));
        topPanel.add(new JLabel("Select Tourist:"));
        touristComboBox = new JComboBox<>(getTouristNames());
        touristComboBox.addActionListener(e -> updateStatusDisplay());
        topPanel.add(touristComboBox);
        topPanel.add(new JLabel("Current Status:"));
        currentStatusLabel = new JLabel("Unknown");
        currentStatusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        topPanel.add(currentStatusLabel);
        // Buttons for activation/deactivation
        activateButton = new JButton("Activate Account");
        activateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeAccountStatus(true);
            }
        });
        topPanel.add(activateButton);
        deactivateButton = new JButton("Deactivate Account");
        deactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeAccountStatus(false);
            }
        });
        topPanel.add(deactivateButton);
        // Disconnect and Reconnect buttons
        disconnectButton = new JButton("Disconnect from Server");
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnectFromServer();
            }
        });
        topPanel.add(disconnectButton);
        reconnectButton = new JButton("Reconnect to Server");
        reconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reconnectToServer();
            }
        });
        reconnectButton.setEnabled(false); // Initially disabled
        topPanel.add(reconnectButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // Log area at the bottom
        logArea = new JTextArea(10, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Operation Log"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);
        updateStatusDisplay(); // Initial display update
    }
    /**
     * Initializes the simulated tourist database with sample data.
     */
    private void initializeDatabase() {
        touristDatabase = new HashMap<>();
        touristDatabase.put("T001", new Tourist("T001", "John Doe", true));
        touristDatabase.put("T002", new Tourist("T002", "Jane Smith", false));
        touristDatabase.put("T003", new Tourist("T003", "Alice Johnson", true));
        touristDatabase.put("T004", new Tourist("T004", "Bob Brown", true));
        touristDatabase.put("T005", new Tourist("T005", "Charlie Davis", false));
    }
    /**
     * Returns an array of tourist names for the combo box.
     */
    private String[] getTouristNames() {
        return touristDatabase.values().stream()
                .map(Tourist::getName)
                .toArray(String[]::new);
    }
    /**
     * Updates the status label based on the currently selected tourist.
     */
    private void updateStatusDisplay() {
        Tourist selectedTourist = getSelectedTourist();
        if (selectedTourist != null) {
            String status = selectedTourist.isActive() ? "Active" : "Inactive";
            currentStatusLabel.setText(status);
            currentStatusLabel.setForeground(selectedTourist.isActive() ? Color.GREEN.darker() : Color.RED.darker());
        } else {
            currentStatusLabel.setText("Unknown");
            currentStatusLabel.setForeground(Color.BLACK);
        }
    }
    /**
     * Retrieves the Tourist object corresponding to the name selected in the combo box.
     */
    private Tourist getSelectedTourist() {
        String selectedName = (String) touristComboBox.getSelectedItem();
        for (Tourist t : touristDatabase.values()) {
            if (t.getName().equals(selectedName)) {
                return t;
            }
        }
        return null;
    }
    /**
     * Changes the account status of the selected tourist after confirmation.
     * @param activate true to activate, false to deactivate.
     */
    private void changeAccountStatus(boolean activate) {
        if (!isConnected) {
            log("Error: Cannot perform operation. Disconnected from server.");
            JOptionPane.showMessageDialog(this,
                    "You are disconnected from the server. Please reconnect first.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Tourist selectedTourist = getSelectedTourist();
        if (selectedTourist == null) {
            log("Error: No tourist selected.");
            return;
        }
        // Check if the account is already in the desired state
        if ((activate && selectedTourist.isActive()) || (!activate && !selectedTourist.isActive())) {
            String state = activate ? "already active" : "already inactive";
            log(selectedTourist.getName() + " account is " + state + ". No change needed.");
            return;
        }
        // Step 2: Ask for confirmation
        String action = activate ? "activate" : "deactivate";
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to " + action + " the account for " + selectedTourist.getName() + "?",
                "Confirm " + action + " Account",
                JOptionPane.YES_NO_OPTION);
        // Step 3: Confirm the operation
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Step 4: Enable/disable the account (simulate server interaction)
                boolean success = performStatusChange(selectedTourist, activate);
                if (success) {
                    selectedTourist.setActive(activate);
                    updateStatusDisplay();
                    log("Success: Account for " + selectedTourist.getName() + " has been " + (activate ? "activated" : "deactivated") + ".");
                } else {
                    log("Error: Failed to " + action + " the account. Please check server connection.");
                }
            } catch (Exception e) {
                log("Error: Operation interrupted due to server connection issue. " + e.getMessage());
                // Simulate disconnection on server error
                handleServerDisconnection();
            }
        } else {
            log("Operation cancelled for " + selectedTourist.getName() + ".");
        }
    }
    /**
     * Simulates the server interaction to change account status.
     * In a real application, this would involve network calls to the ETOUR server.
     * @param tourist The tourist whose status is to be changed.
     * @param activate true to activate, false to deactivate.
     * @return true if the change was successful on the server, false otherwise.
     */
    private boolean performStatusChange(Tourist tourist, boolean activate) {
        // Simulate server call with a random chance of failure to demonstrate error handling
        try {
            // Simulate network delay
            Thread.sleep(500);
            // Simulate random server failure (10% chance)
            if (Math.random() < 0.1) {
                throw new RuntimeException("Connection to server ETOUR interrupted.");
            }
            // Simulate successful status change
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    /**
     * Disconnects from the ETOUR server as per use case exit condition.
     */
    private void disconnectFromServer() {
        // Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to disconnect from the ETOUR server?",
                "Confirm Disconnection",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Simulate disconnection delay
                Thread.sleep(300);
                isConnected = false;
                // Disable account management controls
                activateButton.setEnabled(false);
                deactivateButton.setEnabled(false);
                touristComboBox.setEnabled(false);
                disconnectButton.setEnabled(false);
                reconnectButton.setEnabled(true);
                log("Disconnected from ETOUR server successfully.");
                JOptionPane.showMessageDialog(this,
                        "Disconnected from ETOUR server. Account management features are disabled.",
                        "Disconnection Successful",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log("Error during disconnection: " + e.getMessage());
            }
        } else {
            log("Disconnection cancelled.");
        }
    }
    /**
     * Reconnects to the ETOUR server.
     */
    private void reconnectToServer() {
        try {
            // Simulate reconnection delay
            Thread.sleep(500);
            // Simulate random reconnection failure (15% chance)
            if (Math.random() < 0.15) {
                throw new RuntimeException("Failed to reconnect to ETOUR server.");
            }
            isConnected = true;
            // Re-enable account management controls
            activateButton.setEnabled(true);
            deactivateButton.setEnabled(true);
            touristComboBox.setEnabled(true);
            disconnectButton.setEnabled(true);
            reconnectButton.setEnabled(false);
            log("Reconnected to ETOUR server successfully.");
            JOptionPane.showMessageDialog(this,
                    "Reconnected to ETOUR server. Account management features are now enabled.",
                    "Reconnection Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("Error during reconnection: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Reconnection failed. Please try again.",
                    "Reconnection Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            log("Error: Failed to reconnect to server. " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Reconnection failed: " + e.getMessage(),
                    "Reconnection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Handles server disconnection when an operation fails.
     */
    private void handleServerDisconnection() {
        isConnected = false;
        activateButton.setEnabled(false);
        deactivateButton.setEnabled(false);
        touristComboBox.setEnabled(false);
        disconnectButton.setEnabled(false);
        reconnectButton.setEnabled(true);
        log("Automatic disconnection due to server error. Please reconnect.");
    }
    /**
     * Appends a message to the log area with a timestamp.
     * @param message The message to log.
     */
    private void log(String message) {
        logArea.append(java.time.LocalTime.now().toString().substring(0, 8) + " - " + message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength()); // Auto-scroll
    }
    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TouristAccountGUI().setVisible(true);
            }
        });
    }
}