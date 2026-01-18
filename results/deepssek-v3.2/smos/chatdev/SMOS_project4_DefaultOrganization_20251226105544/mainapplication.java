/**
 * Main application class to demonstrate the Change Password functionality.
 * This class simulates a main application window with a change password button.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainApplication extends JFrame {
    private UserSession currentSession;
    private JLabel statusLabel;
    /**
     * Constructor for the main application window.
     * @param session The current user session.
     */
    public MainApplication(UserSession session) {
        this.currentSession = session;
        initializeUI();
    }
    /**
     * Initializes the main application UI.
     */
    private void initializeUI() {
        setTitle("Main Application - Welcome " + currentSession.getUsername());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem changePasswordItem = new JMenuItem("Change Password");
        JMenuItem exitItem = new JMenuItem("Exit");
        changePasswordItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openChangePasswordWindow();
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(changePasswordItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        // Create content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Main Application!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(welcomeLabel, gbc);
        // Change password button
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setFont(new Font("Arial", Font.PLAIN, 14));
        changePasswordButton.setPreferredSize(new Dimension(200, 40));
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openChangePasswordWindow();
            }
        });
        gbc.gridy = 1;
        contentPanel.add(changePasswordButton, gbc);
        // Status label
        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        gbc.gridy = 2;
        contentPanel.add(statusLabel, gbc);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    /**
     * Opens the Change Password window.
     */
    private void openChangePasswordWindow() {
        statusLabel.setText("Opening Change Password window...");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChangePasswordUI changePasswordUI = new ChangePasswordUI(currentSession);
                changePasswordUI.setVisible(true);
                statusLabel.setText("Change Password window opened.");
            }
        });
    }
    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create a mock user session for demonstration
                UserSession mockSession = new UserSession("testUser", "oldPass123");
                new MainApplication(mockSession).setVisible(true);
                // Display current password for testing
                System.out.println("Current password for testing: " + mockSession.getPassword());
            }
        });
    }
}