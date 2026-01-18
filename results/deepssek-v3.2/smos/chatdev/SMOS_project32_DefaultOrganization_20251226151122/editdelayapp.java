'''
Main application class for EditDelay use case.
This program allows an administrator to edit delays for a selected date.
It includes a GUI for date selection, delay editing, and server communication simulation.
Handles null values for original delays when dates are not pre-populated.
'''
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class EditDelayApp {
    private JFrame mainFrame;
    private JComboBox<String> dateComboBox;
    private JTextField delayField;
    private JTextArea logArea;
    private JButton saveButton;
    private JButton cancelButton;
    // Simulated server data: mapping from date (as string) to delay value
    private Map<String, String> serverData;
    // Simulated current logged-in administrator
    private String currentUser = "admin";
    private String currentUserRole = "administrator";
    // Date format for display
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Constructor: initializes the GUI and data.
     */
    public EditDelayApp() {
        initializeServerData();
        setupGUI();
    }
    /**
     * Simulates initial server data with some predefined delays.
     */
    private void initializeServerData() {
        serverData = new HashMap<>();
        // Prepopulate with sample dates and delays
        serverData.put("2023-10-01", "5");
        serverData.put("2023-10-02", "10");
        serverData.put("2023-10-03", "15");
        // Add more dates for better demonstration
        serverData.put("2023-10-04", "20");
        serverData.put("2023-10-05", "25");
    }
    /**
     * Sets up the main GUI components.
     */
    private void setupGUI() {
        mainFrame = new JFrame("Edit Delay - Administrator");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 450);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setLocationRelativeTo(null); // Center the window
        // Top panel for user info and logout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(new Color(240, 240, 240));
        JLabel userLabel = new JLabel("Logged in as: " + currentUser + " (" + currentUserRole + ")");
        userLabel.setFont(new Font("Arial", Font.BOLD, 12));
        topPanel.add(userLabel, BorderLayout.WEST);
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(220, 80, 80));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> handleLogout());
        topPanel.add(logoutButton, BorderLayout.EAST);
        mainFrame.add(topPanel, BorderLayout.NORTH);
        // Center panel for date selection and delay editing
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        // Date selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        dateComboBox = new JComboBox<>(getDateOptions());
        dateComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        dateComboBox.addActionListener(e -> updateScreenForSelectedDate());
        centerPanel.add(dateComboBox, gbc);
        // Delay input
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel delayLabel = new JLabel("Delay (minutes):");
        delayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(delayLabel, gbc);
        gbc.gridx = 1;
        delayField = new JTextField(15);
        delayField.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(delayField, gbc);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        saveButton = new JButton("Save Changes");
        saveButton.setBackground(new Color(70, 130, 180));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setPreferredSize(new Dimension(150, 35));
        saveButton.addActionListener(e -> handleSave());
        buttonPanel.add(saveButton);
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(169, 169, 169));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setPreferredSize(new Dimension(150, 35));
        cancelButton.addActionListener(e -> handleCancel());
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerPanel.add(buttonPanel, gbc);
        mainFrame.add(centerPanel, BorderLayout.CENTER);
        // Bottom panel for logging
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel logLabel = new JLabel("Activity Log:");
        logLabel.setFont(new Font("Arial", Font.BOLD, 12));
        logPanel.add(logLabel, BorderLayout.NORTH);
        logArea = new JTextArea(6, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setBackground(new Color(245, 245, 245));
        logArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(logArea);
        logPanel.add(scrollPane, BorderLayout.CENTER);
        mainFrame.add(logPanel, BorderLayout.SOUTH);
        // Initialize with the first date selected
        updateScreenForSelectedDate();
        mainFrame.setVisible(true);
        log("Application started. Please select a date to edit its delay.");
    }
    /**
     * Generates an array of date strings for the combo box.
     * In a real application, this would fetch from server.
     */
    private String[] getDateOptions() {
        return serverData.keySet().toArray(new String[0]);
    }
    /**
     * Updates the delay field based on the selected date.
     * This simulates step 1 of the events sequence.
     */
    private void updateScreenForSelectedDate() {
        String selectedDate = (String) dateComboBox.getSelectedItem();
        if (selectedDate != null && serverData.containsKey(selectedDate)) {
            delayField.setText(serverData.get(selectedDate));
            log("Screen updated for date: " + selectedDate);
        } else {
            delayField.setText("");
            log("No delay data for selected date.");
        }
    }
    /**
     * Handles the save action (step 2 and 3 of events sequence).
     * Validates input, updates local data, and simulates server communication.
     * Handles null original delays safely.
     */
    private void handleSave() {
        String selectedDate = (String) dateComboBox.getSelectedItem();
        String delayText = delayField.getText().trim();
        // Save original value safely for possible error recovery
        String originalDelay = serverData.getOrDefault(selectedDate, "");
        // Input validation
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(mainFrame, "Please select a date.", "Error", JOptionPane.ERROR_MESSAGE);
            log("Save failed: No date selected.");
            return;
        }
        if (delayText.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Delay field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            log("Save failed: Delay field empty.");
            return;
        }
        int delay;
        try {
            delay = Integer.parseInt(delayText);
            if (delay < 0) {
                JOptionPane.showMessageDialog(mainFrame, "Delay cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                log("Save failed: Negative delay.");
                return;
            }
            if (delay > 1440) { // 24 hours in minutes
                JOptionPane.showMessageDialog(mainFrame, "Delay cannot exceed 1440 minutes (24 hours).", "Error", JOptionPane.ERROR_MESSAGE);
                log("Save failed: Delay too large.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Delay must be a valid integer number.", "Error", JOptionPane.ERROR_MESSAGE);
            log("Save failed: Invalid delay format.");
            return;
        }
        // Prepare display string for original delay
        String originalDisplay = originalDelay.isEmpty() ? "[empty]" : originalDelay;
        // Confirm save operation
        int confirm = JOptionPane.showConfirmDialog(mainFrame, 
            "Change delay for " + selectedDate + " from " + originalDisplay + " to " + delayText + " minutes?",
            "Confirm Save", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            log("Save operation cancelled by user.");
            return;
        }
        // Simulate sending data to server (step 3)
        boolean serverSuccess = sendDataToServer(selectedDate, delayText);
        if (serverSuccess) {
            // Update local data
            serverData.put(selectedDate, delayText);
            JOptionPane.showMessageDialog(mainFrame, 
                "Delay updated successfully!\nDate: " + selectedDate + "\nNew Delay: " + delayText + " minutes",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            log("SUCCESS: Delay for " + selectedDate + " changed from " + originalDisplay + " to " + delayText + " minutes.");
        } else {
            // Restore original value on server failure
            if (originalDelay.isEmpty()) {
                // If there was no original delay, remove the entry
                serverData.remove(selectedDate);
                delayField.setText("");
            } else {
                serverData.put(selectedDate, originalDelay);
                delayField.setText(originalDelay);
            }
            JOptionPane.showMessageDialog(mainFrame, 
                "Server communication failed. Delay not saved.\nOriginal value restored.",
                "Error", JOptionPane.ERROR_MESSAGE);
            log("FAILED: Could not update delay for " + selectedDate + " due to server error.");
        }
    }
    /**
     * Simulates sending modified data to the SMOS server.
     * In a real application, this would involve network communication.
     * Handles interruption and connection failure as per postconditions.
     */
    private boolean sendDataToServer(String date, String delay) {
        log("Attempting to send data to SMOS server...");
        // Simulate server communication with possible interruption or failure
        try {
            // Create a progress dialog
            ProgressMonitor progressMonitor = new ProgressMonitor(mainFrame,
                "Sending data to SMOS server...",
                "Connecting...", 0, 100);
            progressMonitor.setMillisToDecideToPopup(0);
            progressMonitor.setMillisToPopup(0);
            // Simulate connection process
            for (int i = 0; i <= 100; i += 10) {
                if (progressMonitor.isCanceled()) {
                    log("Server communication cancelled by user.");
                    return false;
                }
                Thread.sleep(200); // Simulate network delay
                progressMonitor.setProgress(i);
                switch (i) {
                    case 20: progressMonitor.setNote("Authenticating..."); break;
                    case 40: progressMonitor.setNote("Preparing data..."); break;
                    case 60: progressMonitor.setNote("Sending to server..."); break;
                    case 80: progressMonitor.setNote("Waiting for response..."); break;
                }
            }
            // Simulate random connection interruption (15% chance)
            if (Math.random() < 0.15) {
                log("ERROR: Connection to SMOS server interrupted.");
                progressMonitor.close();
                return false;
            }
            // Simulate server response
            progressMonitor.setNote("Processing complete");
            progressMonitor.setProgress(100);
            Thread.sleep(300);
            progressMonitor.close();
            log("SUCCESS: Data sent to SMOS server - Date=" + date + ", Delay=" + delay + " minutes");
            return true;
        } catch (InterruptedException e) {
            log("ERROR: Server communication interrupted by system.");
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            log("ERROR: Unexpected error during server communication: " + e.getMessage());
            return false;
        }
    }
    /**
     * Handles cancel action (administrator interrupts the operation).
     * Safely handles null or empty original delays.
     */
    private void handleCancel() {
        String selectedDate = (String) dateComboBox.getSelectedItem();
        String currentDelay = delayField.getText().trim();
        // Get original delay safely, defaulting to empty string if not present
        String originalDelay = serverData.get(selectedDate);
        // Check for unsaved changes, handling null or empty original delays
        boolean hasChanges = false;
        if (selectedDate != null && originalDelay != null && !originalDelay.isEmpty()) {
            hasChanges = !currentDelay.equals(originalDelay);
        } else if (selectedDate != null) {
            // If originalDelay is null or empty, there are changes if currentDelay is not empty
            hasChanges = !currentDelay.isEmpty();
        }
        if (hasChanges) {
            String originalDisplay = (originalDelay == null || originalDelay.isEmpty()) ? "[empty]" : originalDelay;
            int confirm = JOptionPane.showConfirmDialog(mainFrame, 
                "Cancel operation? Current changes will be lost.\nOriginal delay: " + originalDisplay,
                "Confirm Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                // Reset to original data for selected date
                updateScreenForSelectedDate();
                log("Operation cancelled. Changes discarded.");
            }
        } else {
            log("No changes to discard.");
        }
    }
    /**
     * Handles logout action.
     * In a real app, this would clear session and return to login.
     */
    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(mainFrame, 
            "Are you sure you want to logout?",
            "Confirm Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            log("User " + currentUser + " logged out.");
            JOptionPane.showMessageDialog(mainFrame, 
                "Logged out successfully.\nReturning to login screen.", 
                "Logout", JOptionPane.INFORMATION_MESSAGE);
            // In a real application, you would return to login screen
            mainFrame.dispose();
            System.exit(0);
        }
    }
    /**
     * Logs messages to the text area with timestamp and status.
     */
    private void log(String message) {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        logArea.append("[" + timestamp + "] " + message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        // Set system look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Use default look and feel if system L&F is not available
        }
        // Ensure GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            EditDelayApp app = new EditDelayApp();
            app.log("EditDelay application initialized successfully.");
            app.log("System ready for administrator operations.");
        });
    }
}