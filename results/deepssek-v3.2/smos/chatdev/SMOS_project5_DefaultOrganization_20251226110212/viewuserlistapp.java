'''
Main GUI application for viewing user list.
Implements the ViewUserList use case with administrator interface,
including proper SMOS server connection handling.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class ViewUserListApp extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private UserService userService;
    private JLabel statusLabel;
    public ViewUserListApp() {
        super("User Management System");
        userService = new UserService();
        initializeUI();
    }
    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Create title label
        JLabel titleLabel = new JLabel("User Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Create control panel with buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton userManagerButton = new JButton("User Manager");
        userManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Precondition: Check if admin is logged in
                if (!AdminSession.isAdminLoggedIn()) {
                    JOptionPane.showMessageDialog(ViewUserListApp.this,
                        "Please log in as administrator first.",
                        "Authentication Required",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Search for users and display the list
                displayUserList();
            }
        });
        JButton loginButton = new JButton("Login as Admin");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginDialog();
            }
        });
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (AdminSession.isAdminLoggedIn()) {
                    displayUserList();
                }
            }
        });
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminSession.logout();
                updateStatus();
                JOptionPane.showMessageDialog(ViewUserListApp.this,
                    "Logged out successfully.",
                    "Logout", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        controlPanel.add(userManagerButton);
        controlPanel.add(loginButton);
        controlPanel.add(refreshButton);
        controlPanel.add(logoutButton);
        // Create table for displaying users
        String[] columnNames = {"ID", "Username", "Email", "Role", "Created Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        userTable = new JTable(tableModel);
        userTable.setRowHeight(25);
        userTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("User List"));
        // Create status panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Status: Not logged in");
        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        // Add components to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        add(mainPanel);
        updateStatus();
    }
    /**
     * Displays the login dialog for administrator authentication.
     */
    private void showLoginDialog() {
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        int result = JOptionPane.showConfirmDialog(
            this, panel, "Administrator Login",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            boolean loggedIn = AdminSession.loginAsAdmin(username, password);
            if (loggedIn) {
                updateStatus();
                JOptionPane.showMessageDialog(this,
                    "Logged in as administrator successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Invalid administrator credentials.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Displays the list of users in the system.
     * Implements the main events sequence from the use case,
     * including proper SMOS server connection handling.
     */
    private void displayUserList() {
        // Clear existing table data
        tableModel.setRowCount(0);
        try {
            // Check SMOS server connection status (postcondition handling)
            if (userService.isSmosConnectionInterrupted()) {
                statusLabel.setText("Status: SMOS server connection interrupted");
                int choice = JOptionPane.showConfirmDialog(this,
                    "SMOS server connection is interrupted. Would you like to attempt reconnection?",
                    "Connection Alert",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    boolean reconnected = userService.reconnectSMOS();
                    if (reconnected) {
                        JOptionPane.showMessageDialog(this,
                            "Successfully reconnected to SMOS server.",
                            "Reconnection Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "Failed to reconnect to SMOS server. Please try again later.",
                            "Reconnection Failed",
                            JOptionPane.ERROR_MESSAGE);
                        return; // Exit without loading users
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Cannot load user list without SMOS server connection.",
                        "Connection Required",
                        JOptionPane.ERROR_MESSAGE);
                    return; // Exit without loading users
                }
            }
            // Get users from service (simulates searching in archive)
            List<User> users = userService.getUsers();
            // Add users to table
            for (User user : users) {
                Object[] rowData = {
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole(),
                    user.getCreatedDate()
                };
                tableModel.addRow(rowData);
            }
            // Update status
            statusLabel.setText("Status: Displaying " + users.size() + " users");
            System.out.println("User list displayed successfully. Total users: " + users.size());
        } catch (RuntimeException e) {
            // Handle SMOS connection errors specifically
            if (e.getMessage().contains("SMOS server connection")) {
                statusLabel.setText("Status: SMOS server connection error");
                userService.interruptSMOSConnection();
                JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage() + "\n\nPlease check server connection and try again.",
                    "SMOS Server Error",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                // Handle other exceptions
                statusLabel.setText("Status: Error loading users");
                JOptionPane.showMessageDialog(this,
                    "Error loading user list: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    /**
     * Updates the status label based on current session state.
     */
    private void updateStatus() {
        if (AdminSession.isAdminLoggedIn()) {
            statusLabel.setText("Status: Logged in as administrator");
        } else {
            statusLabel.setText("Status: Not logged in");
        }
    }
}