'''
LogoutPanel provides the logout functionality with confirmation.
It implements the complete logout use case flow.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LogoutPanel extends JPanel {
    private MainFrame parentFrame;
    private JLabel userLabel;
    private JButton logoutButton;
    private JLabel statusLabel;
    /**
     * Constructs a LogoutPanel.
     * @param parentFrame The main application frame for switching panels
     */
    public LogoutPanel(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    /**
     * Initializes all UI components for the logout panel.
     */
    private void initializeComponents() {
        userLabel = new JLabel();
        logoutButton = new JButton("Logout");
        statusLabel = new JLabel();
        // Set visual properties
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
    }
    /**
     * Sets up the layout of the logout panel.
     */
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("Logout System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(44, 62, 80));
        add(titleLabel, gbc);
        // User info
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        JLabel loggedInLabel = new JLabel("Logged in as:");
        loggedInLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(loggedInLabel, gbc);
        gbc.gridx = 1;
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setForeground(new Color(41, 128, 185));
        add(userLabel, gbc);
        // Status label
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, gbc);
        // Logout button
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(logoutButton, gbc);
        // Back to login button
        gbc.gridx = 1;
        JButton backButton = new JButton("Back to Login");
        backButton.setBackground(new Color(149, 165, 166));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToLoginPanel();
            }
        });
        add(backButton, gbc);
        // Instructions
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JLabel instructionLabel = new JLabel(
            "<html><center>Step 1: Access logout functionality (Click 'Logout')<br>" +
            "Step 2: Confirm logout in the dialog<br>" +
            "Step 3: System disconnects you and shows success message</center></html>");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        instructionLabel.setForeground(Color.DARK_GRAY);
        add(instructionLabel, gbc);
    }
    /**
     * Sets up event handlers for logout button.
     */
    private void setupEventHandlers() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 2: Ask for confirmation of the transaction
                int response = JOptionPane.showConfirmDialog(
                    LogoutPanel.this,
                    "<html>Are you sure you want to logout?<br><br>" +
                    "<b>User:</b> " + UserSession.getInstance().getUsername() + "<br>" +
                    "<i>Step 2: Confirmation requested</i></html>",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                // Step 3: User confirms the request
                if (response == JOptionPane.YES_OPTION) {
                    performLogout();
                }
            }
        });
    }
    /**
     * Updates the user information display based on current session.
     * Should be called whenever the panel becomes visible.
     */
    public void updateUserInfo() {
        UserSession session = UserSession.getInstance();
        if (session.isLoggedIn()) {
            userLabel.setText(session.getUsername());
            statusLabel.setText("You are currently logged in. Ready to logout.");
            statusLabel.setForeground(new Color(46, 204, 113));
            logoutButton.setEnabled(true);
            logoutButton.setBackground(new Color(220, 53, 69));
        } else {
            userLabel.setText("No user logged in");
            statusLabel.setText("Please login first to access logout functionality");
            statusLabel.setForeground(Color.RED);
            logoutButton.setEnabled(false);
            logoutButton.setBackground(Color.GRAY);
        }
    }
    /**
     * Performs the logout operation and switches back to login panel.
     * Step 4: Disconnects the Registered User
     */
    private void performLogout() {
        UserSession session = UserSession.getInstance();
        String username = session.getUsername();
        // Step 4: Disconnect the user (clear session)
        session.logout();
        // Update UI to reflect logout
        statusLabel.setText("Logout in progress...");
        statusLabel.setForeground(Color.BLUE);
        logoutButton.setEnabled(false);
        logoutButton.setBackground(Color.GRAY);
        // Notify successful operation (Exit conditions)
        JOptionPane.showMessageDialog(
            this,
            "<html><center><b>Logout Successful!</b><br><br>" +
            "User: " + username + " has been disconnected.<br>" +
            "Session ended successfully.</center></html>",
            "Logout Completed",
            JOptionPane.INFORMATION_MESSAGE
        );
        // Update status after logout
        statusLabel.setText("Logout successful! Redirecting to login...");
        // Switch back to login panel after a brief delay
        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToLoginPanel();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}