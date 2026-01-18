/**
 * Main application class for the Registration Request System.
 * Implements the ViewRequestsSubscription use case allowing administrators to view pending registration requests.
 * This program provides a GUI interface with login functionality, a dashboard to view pending student
 * registration requests, and manages server connection status as per the use case requirements.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Main application class that holds the main method and manages the GUI frame.
 */
public class ViewRequestsSubscription {
    public static void main(String[] args) {
        // Create and display the main application frame on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            ApplicationFrame frame = new ApplicationFrame();
            frame.setVisible(true);
        });
    }
}
/**
 * Main application frame that contains all panels and handles navigation between them.
 */
class ApplicationFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private HomePanel homePanel;
    public ApplicationFrame() {
        setTitle("Registration Request System - Administrator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        // Initialize CardLayout for panel switching
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        // Create and add panels
        loginPanel = new LoginPanel(this);
        homePanel = new HomePanel(this);
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(homePanel, "HOME");
        add(mainPanel);
        // Start with login panel
        cardLayout.show(mainPanel, "LOGIN");
    }
    /**
     * Switch to the home panel after successful login
     */
    public void showHomePanel() {
        homePanel.resetServerConnection(); // Reset connection state for new session
        cardLayout.show(mainPanel, "HOME");
    }
    /**
     * Switch back to login panel (e.g., after logout)
     */
    public void showLoginPanel() {
        cardLayout.show(mainPanel, "LOGIN");
    }
}
/**
 * Login panel for administrator authentication
 */
class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ApplicationFrame appFrame;
    public LoginPanel(ApplicationFrame appFrame) {
        this.appFrame = appFrame;
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Create and configure UI components
        JLabel titleLabel = new JLabel("Administrator Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        // Add action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        // Layout configuration
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(userLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginButton, gbc);
        // Add Enter key support for login
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }
    /**
     * Perform login validation and switch to home panel if successful
     */
    private void performLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        // Simple validation - in real application, connect to authentication service
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password.",
                "Login Failed",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // For demo purposes: accept any non-empty credentials and redirect to home
        // In production, this would validate against a database or LDAP
        JOptionPane.showMessageDialog(this,
            "Login successful! Welcome, Administrator.",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
        // Clear fields for security
        usernameField.setText("");
        passwordField.setText("");
        // Switch to home panel
        appFrame.showHomePanel();
    }
}
/**
 * Home panel that displays the list of registration requests
 */
class HomePanel extends JPanel {
    private JButton viewRequestsButton;
    private JTextArea requestsTextArea;
    private JScrollPane scrollPane;
    private JLabel statusLabel;
    private JButton disconnectButton;
    private ApplicationFrame appFrame;
    private List<RegistrationRequest> pendingRequests;
    private boolean serverConnected = true;
    public HomePanel(ApplicationFrame appFrame) {
        this.appFrame = appFrame;
        this.pendingRequests = new ArrayList<>();
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Initialize statusLabel early to avoid NullPointerException
        statusLabel = new JLabel("Server Status: Connected");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setForeground(Color.GREEN);
        initializeData(); // Load sample data
        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        JLabel headerLabel = new JLabel("Administrator Dashboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(70, 130, 180));
        headerPanel.add(headerLabel, BorderLayout.NORTH);
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        viewRequestsButton = new JButton("View Registration Requests");
        viewRequestsButton.setBackground(new Color(70, 130, 180));
        viewRequestsButton.setForeground(Color.WHITE);
        viewRequestsButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewRequestsButton.setPreferredSize(new Dimension(250, 40));
        // Add action listener for viewing requests
        viewRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRegistrationRequests();
            }
        });
        // Create disconnect button for simulating server interruption
        disconnectButton = new JButton("Disconnect Server");
        disconnectButton.setBackground(new Color(220, 20, 60));
        disconnectButton.setForeground(Color.WHITE);
        disconnectButton.setFont(new Font("Arial", Font.BOLD, 14));
        disconnectButton.setPreferredSize(new Dimension(180, 40));
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleServerConnection();
            }
        });
        buttonPanel.add(viewRequestsButton);
        buttonPanel.add(disconnectButton);
        headerPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Create requests display area
        requestsTextArea = new JTextArea(15, 50);
        requestsTextArea.setEditable(false);
        requestsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        requestsTextArea.setLineWrap(true);
        requestsTextArea.setWrapStyleWord(true);
        scrollPane = new JScrollPane(requestsTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Pending Registration Requests"));
        // Create status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(statusLabel);
        // Add connection status button to status panel
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(disconnectButton);
        // Add logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(HomePanel.this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    // Simulate server interruption as per use case
                    serverConnected = false;
                    statusLabel.setText("Server Status: Disconnected");
                    statusLabel.setForeground(Color.RED);
                    disconnectButton.setText("Reconnect Server");
                    // Clear the requests display
                    requestsTextArea.setText("");
                    // Return to login panel
                    appFrame.showLoginPanel();
                }
            }
        });
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(logoutButton);
        // Add all components to main panel
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        // Ensure initial connection state is properly set
        resetServerConnection();
    }
    /**
     * Reset server connection state when starting a new session.
     * This ensures proper functionality when re-logging in after logout.
     */
    public void resetServerConnection() {
        serverConnected = true;
        if (statusLabel != null) {
            statusLabel.setText("Server Status: Connected");
            statusLabel.setForeground(Color.GREEN);
            disconnectButton.setText("Disconnect Server");
            requestsTextArea.setText("");
        }
    }
    /**
     * Toggle server connection status (connect/disconnect)
     * This simulates the user interrupting the connection as per the use case
     */
    private void toggleServerConnection() {
        serverConnected = !serverConnected;
        if (serverConnected) {
            statusLabel.setText("Server Status: Connected");
            statusLabel.setForeground(Color.GREEN);
            disconnectButton.setText("Disconnect Server");
            JOptionPane.showMessageDialog(this,
                "SMOS server connection restored.",
                "Server Connected",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            statusLabel.setText("Server Status: Disconnected");
            statusLabel.setForeground(Color.RED);
            disconnectButton.setText("Reconnect Server");
            JOptionPane.showMessageDialog(this,
                "SMOS server connection interrupted by user.",
                "Server Disconnected",
                JOptionPane.WARNING_MESSAGE);
            // Clear requests display when disconnected
            requestsTextArea.setText("Server connection interrupted. Cannot display requests.");
        }
    }
    /**
     * Initialize sample registration request data
     */
    private void initializeData() {
        // Add sample registration requests
        pendingRequests.add(new RegistrationRequest("S001", "John Doe", "john.doe@student.edu", "Computer Science", "2024-03-15"));
        pendingRequests.add(new RegistrationRequest("S002", "Jane Smith", "jane.smith@student.edu", "Mathematics", "2024-03-16"));
        pendingRequests.add(new RegistrationRequest("S003", "Bob Johnson", "bob.johnson@student.edu", "Physics", "2024-03-17"));
        pendingRequests.add(new RegistrationRequest("S004", "Alice Brown", "alice.brown@student.edu", "Engineering", "2024-03-18"));
        pendingRequests.add(new RegistrationRequest("S005", "Charlie Wilson", "charlie.wilson@student.edu", "Biology", "2024-03-19"));
    }
    /**
     * Display the list of pending registration requests
     * Handles server connection status as per use case requirements
     */
    private void displayRegistrationRequests() {
        // Check server connection status
        if (!serverConnected) {
            JOptionPane.showMessageDialog(this,
                "Cannot retrieve requests: SMOS server connection interrupted.",
                "Server Error",
                JOptionPane.ERROR_MESSAGE);
            requestsTextArea.setText("Unable to retrieve registration requests.\nServer connection has been interrupted.");
            return;
        }
        // Check if there are pending requests
        if (pendingRequests.isEmpty()) {
            requestsTextArea.setText("No pending registration requests found.");
            JOptionPane.showMessageDialog(this,
                "There are no pending registration requests at this time.",
                "No Requests Found",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Format and display the requests
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-20s %-30s %-20s %-15s\n", 
            "ID", "Name", "Email", "Department", "Request Date"));
        sb.append("=".repeat(100)).append("\n");
        for (RegistrationRequest request : pendingRequests) {
            sb.append(String.format("%-10s %-20s %-30s %-20s %-15s\n",
                request.getStudentId(),
                request.getStudentName(),
                request.getEmail(),
                request.getDepartment(),
                request.getRequestDate()));
        }
        sb.append("\nTotal pending requests: ").append(pendingRequests.size());
        requestsTextArea.setText(sb.toString());
        JOptionPane.showMessageDialog(this,
            "Displaying " + pendingRequests.size() + " pending registration requests.",
            "Requests Retrieved",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
/**
 * Data class representing a registration request
 */
class RegistrationRequest {
    private String studentId;
    private String studentName;
    private String email;
    private String department;
    private String requestDate;
    public RegistrationRequest(String studentId, String studentName, String email, String department, String requestDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.department = department;
        this.requestDate = requestDate;
    }
    // Getter methods
    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getRequestDate() { return requestDate; }
}