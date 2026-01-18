/**
 * LoginSystem.java
 * Main entry point for the Login application.
 * Implements a simple GUI login system that validates username and password.
 * Uses a simulated "archive" of loggable users.
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
public class Login {
    // Simulated archive of loggable users (username, password)
    private static HashMap<String, String> userArchive = new HashMap<>();
    public static void main(String[] args) {
        // Pre-populate the archive with some sample users (for demonstration)
        initializeUserArchive();
        // Create and set up the login GUI
        SwingUtilities.invokeLater(Login::createAndShowGUI);
    }
    /**
     * Initializes the user archive with dummy data for testing.
     */
    private static void initializeUserArchive() {
        userArchive.put("admin12", "adminpass123");
        userArchive.put("user007", "password007");
        userArchive.put("testuser", "testpass12345");
    }
    /**
     * Creates and displays the main login window.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Login System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(5, 2, 10, 10));
        // Username label and field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setHorizontalAlignment(JLabel.RIGHT);
        JTextField userField = new JTextField(20);
        frame.add(userLabel);
        frame.add(userField);
        // Password label and field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setHorizontalAlignment(JLabel.RIGHT);
        JPasswordField passField = new JPasswordField(20);
        frame.add(passLabel);
        frame.add(passField);
        // Empty row for spacing
        frame.add(new JLabel(""));
        frame.add(new JLabel(""));
        // Status label (spans two columns)
        JLabel statusLabel = new JLabel(" ", JLabel.CENTER);
        statusLabel.setForeground(Color.RED);
        frame.add(statusLabel);
        // Login button
        JButton loginButton = new JButton("Login");
        frame.add(loginButton);
        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword()).trim();
                // Step 1: Validate length of username and password
                if (username.length() < 5 || password.length() < 5) {
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("Error: Username and password must each be at least 5 characters.");
                    return;
                }
                // Step 2: Search in the archive for matching credentials
                if (userArchive.containsKey(username) && userArchive.get(username).equals(password)) {
                    // Step 3: Successful login
                    statusLabel.setForeground(Color.GREEN);
                    statusLabel.setText("Login successful! Displaying workspace...");
                    // Delay a bit before closing the login window and opening the workspace
                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            frame.dispose(); // Close the login window
                            showWorkspace(username); // Display the user workspace
                            // Simulate interruption of connection to SMOS server (postcondition)
                            System.out.println("[DEBUG] Interruption of connection to SMOS server.");
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                } else {
                    // Failed login
                    statusLabel.setForeground(Color.RED);
                    statusLabel.setText("Invalid username or password.");
                }
            }
        });
        frame.setVisible(true);
    }
    /**
     * Displays the registered user workspace.
     * @param username The username of the logged-in user.
     */
    private static void showWorkspace(String username) {
        JFrame workspaceFrame = new JFrame("User Workspace - " + username);
        workspaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        workspaceFrame.setSize(600, 400);
        JLabel welcomeLabel = new JLabel("Welcome to your workspace, " + username + "!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        workspaceFrame.add(welcomeLabel, BorderLayout.CENTER);
        workspaceFrame.setVisible(true);
    }
}