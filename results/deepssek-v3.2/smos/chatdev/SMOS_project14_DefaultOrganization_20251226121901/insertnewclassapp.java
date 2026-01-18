'''
Main application class for the InsertNewClass use case.
This class contains the main method and sets up the GUI for administrators.
The application allows administrators to insert new classes into the archive
after viewing the list of classes for a particular academic year.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.regex.Pattern;
public class InsertNewClassApp {
    // Database connection details - should be externalized in production
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school_db";
    private static final String DB_USER = "admin";
    private static final String DB_PASS = "password";
    public static void main(String[] args) {
        // Ensure the database driver is loaded
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Database driver not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Create and display the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InsertNewClassGUI();
            }
        });
    }
    /**
     * GUI class for the Insert New Class form.
     * This form is shown after the administrator clicks "New Class" button
     * while viewing the list of classes for a particular academic year.
     */
    static class InsertNewClassGUI extends JFrame {
        private JTextField nameField;
        private JTextField addressField;
        private JTextField academicYearField;
        private JButton saveButton;
        private JButton cancelButton;
        private JButton reconnectButton;
        private JLabel statusLabel;
        // Instance-specific SMOS server connection flag
        private boolean smosServerConnected;
        public InsertNewClassGUI() {
            // Initialize SMOS server connection status
            checkSMOSServerConnection();
            setTitle("Insert New Class - Administrator");
            setSize(450, 350);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window, not entire app
            setLocationRelativeTo(null); // Center the window
            // Initialize components
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            // Name field
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Class Name*:"), gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            nameField = new JTextField(20);
            panel.add(nameField, gbc);
            // Address field
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Address*:"), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            addressField = new JTextField(20);
            panel.add(addressField, gbc);
            // Academic Year field
            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Academic Year* (YYYY-YYYY):"), gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            academicYearField = new JTextField(20);
            panel.add(academicYearField, gbc);
            // Status panel for SMOS server
            JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            statusLabel = new JLabel("SMOS Server: " + 
                (smosServerConnected ? "Connected" : "Disconnected"));
            statusLabel.setForeground(smosServerConnected ? Color.GREEN.darker() : Color.RED);
            reconnectButton = new JButton("Reconnect");
            reconnectButton.setEnabled(!smosServerConnected);
            reconnectButton.addActionListener(new ReconnectButtonListener());
            statusPanel.add(statusLabel);
            statusPanel.add(reconnectButton);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            panel.add(statusPanel, gbc);
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            saveButton = new JButton("Save");
            saveButton.setEnabled(smosServerConnected);
            cancelButton = new JButton("Cancel");
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.EAST;
            panel.add(buttonPanel, gbc);
            // Add action listeners
            saveButton.addActionListener(new SaveButtonListener());
            cancelButton.addActionListener(new CancelButtonListener());
            add(panel);
            setVisible(true);
        }
        /**
         * Checks connection to SMOS server.
         * In a real application, this would be an actual network check.
         */
        private void checkSMOSServerConnection() {
            // Simulate connection check with 80% success rate
            smosServerConnected = Math.random() < 0.8;
        }
        /**
         * Updates the SMOS server connection status in the UI.
         */
        private void updateSMOSStatus() {
            statusLabel.setText("SMOS Server: " + 
                (smosServerConnected ? "Connected" : "Disconnected"));
            statusLabel.setForeground(smosServerConnected ? Color.GREEN.darker() : Color.RED);
            reconnectButton.setEnabled(!smosServerConnected);
            saveButton.setEnabled(smosServerConnected);
            // Update tooltips for better user guidance
            if (smosServerConnected) {
                saveButton.setToolTipText("Save the new class to the archive");
                statusLabel.setToolTipText("Connected to SMOS server - Ready to save");
            } else {
                saveButton.setToolTipText("Cannot save - SMOS server disconnected");
                statusLabel.setToolTipText("Disconnected from SMOS server - Click Reconnect to retry");
            }
        }
        /**
         * Listener for the Reconnect button.
         * Allows administrator to attempt reconnection to SMOS server.
         */
        private class ReconnectButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                reconnectButton.setEnabled(false);
                reconnectButton.setText("Connecting...");
                // Simulate reconnection attempt
                SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        // Simulate network delay
                        Thread.sleep(1500);
                        // Attempt reconnection
                        checkSMOSServerConnection();
                        return smosServerConnected;
                    }
                    @Override
                    protected void done() {
                        try {
                            boolean connected = get();
                            updateSMOSStatus();
                            if (connected) {
                                JOptionPane.showMessageDialog(InsertNewClassGUI.this,
                                    "Successfully reconnected to SMOS server!",
                                    "Reconnection Successful",
                                    JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(InsertNewClassGUI.this,
                                    "Failed to reconnect to SMOS server. Please try again.",
                                    "Reconnection Failed",
                                    JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(InsertNewClassGUI.this,
                                "Reconnection error: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        } finally {
                            reconnectButton.setText("Reconnect");
                            reconnectButton.setEnabled(!smosServerConnected);
                        }
                    }
                };
                worker.execute();
            }
        }
        /**
         * Listener for the Save button.
         * Validates input and inserts new class into the database.
         * Handles SMOS server connection interruptions as per postconditions.
         */
        private class SaveButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check SMOS server connection before proceeding
                if (!smosServerConnected) {
                    JOptionPane.showMessageDialog(InsertNewClassGUI.this, 
                        "Cannot save: Connection to SMOS server interrupted.\n" +
                        "Please click the Reconnect button and try again.", 
                        "Server Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String name = nameField.getText().trim();
                String address = addressField.getText().trim();
                String academicYear = academicYearField.getText().trim();
                // Validate input
                String errorMsg = validateInput(name, address, academicYear);
                if (errorMsg != null) {
                    // Activate error case: show error dialog
                    JOptionPane.showMessageDialog(InsertNewClassGUI.this, 
                        errorMsg, "Data Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Insert into database
                try {
                    boolean success = insertClassIntoDatabase(name, address, academicYear);
                    if (success) {
                        JOptionPane.showMessageDialog(InsertNewClassGUI.this, 
                            "Class inserted successfully!", "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                        // Clear fields after successful insertion
                        nameField.setText("");
                        addressField.setText("");
                        academicYearField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(InsertNewClassGUI.this, 
                            "Failed to insert class. Please try again.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    // Handle database errors including connection issues
                    String errorMessage = "Database error: " + ex.getMessage();
                    // Check if it's a connection interruption
                    if (ex.getMessage().toLowerCase().contains("connection") || 
                        ex.getMessage().toLowerCase().contains("communicate")) {
                        errorMessage = "Database connection interrupted. Please check your connection.";
                        // Update SMOS server status
                        smosServerConnected = false;
                        updateSMOSStatus();
                    }
                    JOptionPane.showMessageDialog(InsertNewClassGUI.this, 
                        errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    // Handle any other unexpected errors
                    JOptionPane.showMessageDialog(InsertNewClassGUI.this, 
                        "Unexpected error: " + ex.getMessage(), "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            /**
             * Validates the input fields comprehensively.
             * @param name Class name
             * @param address Address
             * @param academicYear Academic year in format YYYY-YYYY
             * @return Error message if invalid, null if valid
             */
            private String validateInput(String name, String address, String academicYear) {
                // Check for empty fields
                if (name.isEmpty()) {
                    return "Class name cannot be empty.";
                }
                if (address.isEmpty()) {
                    return "Address cannot be empty.";
                }
                if (academicYear.isEmpty()) {
                    return "Academic year cannot be empty.";
                }
                // Validate class name length and format
                if (name.length() > 100) {
                    return "Class name cannot exceed 100 characters.";
                }
                if (!Pattern.matches("[a-zA-Z0-9\\s\\-]+", name)) {
                    return "Class name can only contain letters, numbers, spaces, and hyphens.";
                }
                // Validate address length
                if (address.length() > 200) {
                    return "Address cannot exceed 200 characters.";
                }
                // Validate academic year format: YYYY-YYYY
                if (!Pattern.matches("\\d{4}-\\d{4}", academicYear)) {
                    return "Academic year must be in format YYYY-YYYY (e.g., 2023-2024).";
                }
                // Check that the years are consecutive and the first is less than the second
                String[] years = academicYear.split("-");
                try {
                    int startYear = Integer.parseInt(years[0]);
                    int endYear = Integer.parseInt(years[1]);
                    if (endYear != startYear + 1) {
                        return "Academic year must be consecutive (e.g., 2023-2024).";
                    }
                    // Validate year range
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    if (startYear < 1900 || startYear > currentYear + 10) {
                        return "Academic year must be between 1900 and " + (currentYear + 10) + ".";
                    }
                } catch (NumberFormatException e) {
                    return "Academic year contains invalid numbers.";
                }
                return null; // All validation passed
            }
            /**
             * Inserts a new class record into the database.
             * Includes duplicate checking before insertion.
             * @param name Class name
             * @param address Address
             * @param academicYear Academic year
             * @return true if insertion successful, false otherwise
             * @throws SQLException if a database error occurs
             */
            private boolean insertClassIntoDatabase(String name, String address, 
                                                   String academicYear) throws SQLException {
                Connection conn = null;
                PreparedStatement checkStmt = null;
                PreparedStatement insertStmt = null;
                ResultSet rs = null;
                try {
                    conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                    conn.setAutoCommit(false); // Start transaction
                    // Check for duplicate class name for the same academic year
                    String checkSql = "SELECT COUNT(*) FROM classes WHERE name = ? AND academic_year = ?";
                    checkStmt = conn.prepareStatement(checkSql);
                    checkStmt.setString(1, name);
                    checkStmt.setString(2, academicYear);
                    rs = checkStmt.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(InsertNewClassGUI.this,
                            "A class with name '" + name + "' already exists for academic year " + academicYear + ".",
                            "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                        conn.rollback();
                        return false;
                    }
                    // Insert the new class
                    String insertSql = "INSERT INTO classes (name, address, academic_year, created_date) VALUES (?, ?, ?, NOW())";
                    insertStmt = conn.prepareStatement(insertSql);
                    insertStmt.setString(1, name);
                    insertStmt.setString(2, address);
                    insertStmt.setString(3, academicYear);
                    int rowsAffected = insertStmt.executeUpdate();
                    conn.commit(); // Commit transaction
                    return rowsAffected > 0;
                } catch (SQLException ex) {
                    // Rollback in case of error
                    if (conn != null) {
                        try {
                            conn.rollback();
                        } catch (SQLException rollbackEx) {
                            // Log rollback error
                        }
                    }
                    throw ex; // Re-throw the exception
                } finally {
                    // Close all resources in reverse order
                    if (rs != null) try { rs.close(); } catch (SQLException e) { }
                    if (checkStmt != null) try { checkStmt.close(); } catch (SQLException e) { }
                    if (insertStmt != null) try { insertStmt.close(); } catch (SQLException e) { }
                    if (conn != null) try { conn.close(); } catch (SQLException e) { }
                }
            }
        }
        /**
         * Listener for the Cancel button.
         * Allows administrator to interrupt the operation without exiting the system.
         * Returns to the class viewing screen as per use case flow.
         */
        private class CancelButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(InsertNewClassGUI.this,
                    "Are you sure you want to cancel inserting a new class?", 
                    "Cancel Operation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // Administrator interrupts the operation - just close this window
                    dispose(); // Only closes the insert form, not the entire application
                    // In a full application, this would return to the class viewing screen
                    JOptionPane.showMessageDialog(null,
                        "Returning to class list view...",
                        "Operation Cancelled",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}