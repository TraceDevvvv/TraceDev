/**
 * Main class for the DeleteDelay application.
 * Provides GUI for administrators to login, select a date,
 * view late entries, and delete a selected late entry.
 * Handles administrator interruption and SMOS server connection interruption
 * as per use case postconditions.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Collections;
public class Main {
    private JFrame mainFrame;
    private JPanel currentPanel;
    private DatabaseSimulator database;
    private String loggedInAdmin = null;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().initialize());
    }
    /**
     * Initialize the application, show login screen.
     */
    public void initialize() {
        database = new DatabaseSimulator();
        database.populateSampleData();
        showLoginScreen();
    }
    /**
     * Display the login panel where admin can enter credentials.
     */
    private void showLoginScreen() {
        if (mainFrame == null) {
            mainFrame = new JFrame("DeleteDelay - Admin System");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(600, 500);
            mainFrame.setLocationRelativeTo(null);
        }
        if (currentPanel != null) {
            mainFrame.remove(currentPanel);
        }
        LoginPanel loginPanel = new LoginPanel();
        currentPanel = loginPanel;
        mainFrame.add(currentPanel);
        mainFrame.setVisible(true);
    }
    /**
     * Handle successful admin login.
     * @param adminName the authenticated admin's username
     */
    public void loginSuccessful(String adminName) {
        this.loggedInAdmin = adminName;
        showRegistryScreen();
    }
    /**
     * Display the main registry screen where admin can select a date and see late entries.
     * This screen remains visible even when interruptions occur (postcondition).
     */
    private void showRegistryScreen() {
        if (mainFrame == null) return;
        if (currentPanel != null) {
            mainFrame.remove(currentPanel);
        }
        RegistryPanel registryPanel = new RegistryPanel();
        currentPanel = registryPanel;
        mainFrame.add(currentPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    /**
     * Handle logout, clear session and show login screen.
     */
    private void logout() {
        this.loggedInAdmin = null;
        showLoginScreen();
    }
    /**
     * Inner class for login panel UI.
     */
    class LoginPanel extends JPanel {
        public LoginPanel() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            JLabel titleLabel = new JLabel("Admin Login - DeleteDelay System");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(titleLabel, gbc);
            JLabel userLabel = new JLabel("Username:");
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(userLabel, gbc);
            JTextField userField = new JTextField(15);
            gbc.gridx = 1;
            add(userField, gbc);
            JLabel passLabel = new JLabel("Password:");
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(passLabel, gbc);
            JPasswordField passField = new JPasswordField(15);
            gbc.gridx = 1;
            add(passField, gbc);
            JButton loginButton = new JButton("Login");
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            add(loginButton, gbc);
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = userField.getText();
                    char[] password = passField.getPassword();
                    // Simple hardcoded authentication for demonstration.
                    if ("admin".equals(username) && "admin123".equals(new String(password))) {
                        loginSuccessful(username);
                    } else {
                        JOptionPane.showMessageDialog(LoginPanel.this,
                                "Invalid credentials",
                                "Login Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
    /**
     * Inner class for the main registry screen.
     * Implements postcondition: "The system remains on the registry screen"
     * during administrator interruption and server connection interruption.
     */
    class RegistryPanel extends JPanel {
        private JComboBox<String> dateComboBox;
        private JList<LateEntry> lateEntriesList;
        private DefaultListModel<LateEntry> listModel;
        private JButton deleteButton, logoutButton, interruptButton, reconnectButton;
        private JLabel serverStatusLabel;
        public RegistryPanel() {
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            // Top panel with title and date selection
            JPanel topPanel = new JPanel(new BorderLayout());
            JPanel topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            topLeftPanel.add(new JLabel("Select Date:"));
            // Get available dates dynamically from the database
            Set<String> dateSet = database.getAvailableDates();
            if (dateSet.isEmpty()) {
                dateSet = new HashSet<>();
                dateSet.add("No dates available");
            }
            String[] dates = dateSet.toArray(new String[0]);
            dateComboBox = new JComboBox<>(dates);
            topLeftPanel.add(dateComboBox);
            topPanel.add(topLeftPanel, BorderLayout.WEST);
            // Server status indicator
            JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            serverStatusLabel = new JLabel("SMOS Server: " + (database.isServerConnected() ? "Connected" : "DISCONNECTED"));
            serverStatusLabel.setForeground(database.isServerConnected() ? Color.GREEN.darker() : Color.RED);
            topRightPanel.add(serverStatusLabel);
            topPanel.add(topRightPanel, BorderLayout.EAST);
            add(topPanel, BorderLayout.NORTH);
            // Middle panel to display late entries for selected date
            listModel = new DefaultListModel<>();
            lateEntriesList = new JList<>(listModel);
            lateEntriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            lateEntriesList.setCellRenderer(new LateEntryCellRenderer());
            JScrollPane scrollPane = new JScrollPane(lateEntriesList);
            add(scrollPane, BorderLayout.CENTER);
            // Bottom panel with action buttons
            JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 5, 5));
            // First row: Delete and Reconnect buttons
            JPanel buttonRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            deleteButton = new JButton("Delete Selected Delay");
            reconnectButton = new JButton("Reconnect to SMOS Server");
            reconnectButton.setEnabled(!database.isServerConnected());
            buttonRow1.add(deleteButton);
            buttonRow1.add(reconnectButton);
            bottomPanel.add(buttonRow1);
            // Second row: Interrupt and Logout buttons
            JPanel buttonRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            interruptButton = new JButton("Interrupt Operation");
            logoutButton = new JButton("Logout");
            buttonRow2.add(interruptButton);
            buttonRow2.add(logoutButton);
            bottomPanel.add(buttonRow2);
            add(bottomPanel, BorderLayout.SOUTH);
            // Load initial data for first date
            loadLateEntriesForDate((String) dateComboBox.getSelectedItem());
            // Event listeners
            dateComboBox.addActionListener(e -> {
                loadLateEntriesForDate((String) dateComboBox.getSelectedItem());
            });
            deleteButton.addActionListener(e -> deleteSelectedDelay());
            logoutButton.addActionListener(e -> logout());
            // Add interrupt button listener (simulates administrator interruption)
            interruptButton.addActionListener(e -> {
                int choice = JOptionPane.showConfirmDialog(this,
                        "Interrupt current operation? The system will remain on this screen.",
                        "Interrupt Operation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this,
                            "Operation interrupted by administrator.\nSystem remains on registry screen.",
                            "Interruption Complete",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Postcondition: The system remains on the registry screen
                    // No action needed as we're already on registry screen
                }
            });
            // Add reconnect button listener for server reconnection
            reconnectButton.addActionListener(e -> {
                boolean reconnected = database.reconnectServer();
                if (reconnected) {
                    serverStatusLabel.setText("SMOS Server: Connected");
                    serverStatusLabel.setForeground(Color.GREEN.darker());
                    reconnectButton.setEnabled(false);
                    deleteButton.setEnabled(true);
                    JOptionPane.showMessageDialog(this,
                            "Successfully reconnected to SMOS server.",
                            "Reconnection Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Refresh the data after reconnection
                    loadLateEntriesForDate((String) dateComboBox.getSelectedItem());
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Failed to reconnect to SMOS server. Please try again.",
                            "Reconnection Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        /**
         * Load late entries for a given date into the list.
         * Simulates updating screen based on selected date (Event 1).
         * @param date the selected date string
         */
        private void loadLateEntriesForDate(String date) {
            listModel.clear();
            // Check server connection before attempting to load data
            if (!database.isServerConnected()) {
                listModel.addElement(new LateEntry(-1, "Cannot load data: SMOS server disconnected", "", date, ""));
                serverStatusLabel.setText("SMOS Server: DISCONNECTED");
                serverStatusLabel.setForeground(Color.RED);
                reconnectButton.setEnabled(true);
                deleteButton.setEnabled(false);
                return;
            }
            List<LateEntry> entries = database.getLateEntriesByDate(date);
            if (entries.isEmpty()) {
                listModel.addElement(new LateEntry(-1, "No late entries for this date", "", date, ""));
            } else {
                for (LateEntry entry : entries) {
                    listModel.addElement(entry);
                }
            }
            // Update server status
            serverStatusLabel.setText("SMOS Server: Connected");
            serverStatusLabel.setForeground(Color.GREEN.darker());
            reconnectButton.setEnabled(false);
            deleteButton.setEnabled(true);
        }
        /**
         * Delete the selected late entry (Event 2 and 3).
         * Handles removal from UI and archive.
         * Handles server connection interruption as per postcondition.
         */
        private void deleteSelectedDelay() {
            int selectedIndex = lateEntriesList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this,
                        "Please select a late entry to delete.",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            LateEntry selectedEntry = listModel.getElementAt(selectedIndex);
            if (selectedEntry.getId() == -1) {
                // Dummy "no entries" placeholder
                JOptionPane.showMessageDialog(this,
                        "Cannot delete placeholder entry.",
                        "Invalid Selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this late entry?\n" +
                            selectedEntry.getStudentName() + " on " + selectedEntry.getDate(),
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Simulate delete from archive (Event 3)
                boolean success = database.deleteLateEntry(selectedEntry.getId());
                if (success) {
                    // Remove from UI list (Event 2 visual feedback)
                    listModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(this,
                            "Delay deleted successfully.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Update server status
                    serverStatusLabel.setText("SMOS Server: Connected");
                    serverStatusLabel.setForeground(Color.GREEN.darker());
                    reconnectButton.setEnabled(false);
                } else {
                    // Check if failure was due to server disconnection (postcondition)
                    if (!database.isServerConnected()) {
                        serverStatusLabel.setText("SMOS Server: DISCONNECTED");
                        serverStatusLabel.setForeground(Color.RED);
                        reconnectButton.setEnabled(true);
                        deleteButton.setEnabled(false);
                        JOptionPane.showMessageDialog(this,
                                "Failed to delete delay. SMOS server connection interrupted.\n" +
                                "Please reconnect using the 'Reconnect to SMOS Server' button.",
                                "Server Connection Error",
                                JOptionPane.ERROR_MESSAGE);
                        // Postcondition: System remains on registry screen (already satisfied)
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Failed to delete delay. Entry not found or database error.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    /**
     * Custom renderer for LateEntry objects in JList.
     */
    class LateEntryCellRenderer extends JLabel implements ListCellRenderer<LateEntry> {
        @Override
        public Component getListCellRendererComponent(JList<? extends LateEntry> list,
                                                      LateEntry value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            if (value.getId() == -1) {
                setText(value.getStudentName());
                setFont(getFont().deriveFont(Font.ITALIC));
                setForeground(Color.GRAY);
            } else {
                setText(String.format("<html><b>%s</b> - Course: %s - Date: %s - Reason: %s</html>",
                        value.getStudentName(),
                        value.getCourse(),
                        value.getDate(),
                        value.getReason()));
                setFont(getFont().deriveFont(Font.PLAIN));
                setForeground(Color.BLACK);
            }
            setOpaque(true);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                // Keep gray color for placeholder entries even when not selected
                if (value.getId() == -1 && !isSelected) {
                    setForeground(Color.GRAY);
                }
            }
            return this;
        }
    }
}