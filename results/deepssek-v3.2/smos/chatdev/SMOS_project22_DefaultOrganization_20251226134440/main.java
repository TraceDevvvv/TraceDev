/**
 * Main entry point for the ViewTeachingsList application.
 * This program simulates a GUI for an Administrator to view a list of teachings.
 * It includes a login screen, main menu, and a teachings list screen.
 * The application demonstrates role-based access, GUI navigation, and
 * simulation of server connection interruption post data display.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class main {
    public static void main(String[] args) {
        // Use Swing utilities to ensure thread-safe GUI operations
        SwingUtilities.invokeLater(() -> {
            createAndShowLoginGUI();
        });
    }
    /**
     * Creates and displays the login GUI.
     * Only admin users can proceed.
     */
    private static void createAndShowLoginGUI() {
        JFrame loginFrame = new JFrame("Admin Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 200);
        loginFrame.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);
        gbc.gridx = 1;
        panel.add(userField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);
        loginFrame.add(panel, BorderLayout.CENTER);
        // Simulate admin login (for demo, accept any non-empty input)
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                char[] password = passField.getPassword();
                // Validate non-empty credentials
                if (username.isEmpty() || password.length == 0) {
                    JOptionPane.showMessageDialog(loginFrame, 
                        "Please enter both username and password.", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    // In a real application, add authentication logic here
                    loginFrame.dispose();
                    createAndShowMainMenuGUI();
                }
                // Clear password field for security
                passField.setText("");
            }
        });
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }
    /**
     * Creates and displays the main menu GUI after successful login.
     * Includes a "Management Management" button to view teachings.
     */
    private static void createAndShowMainMenuGUI() {
        JFrame mainMenuFrame = new JFrame("Main Menu - Administrator");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(500, 300);
        mainMenuFrame.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome, Administrator!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainMenuFrame.add(welcomeLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton managementButton = new JButton("Management Management");
        managementButton.setPreferredSize(new Dimension(250, 60));
        managementButton.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonPanel.add(managementButton);
        mainMenuFrame.add(buttonPanel, BorderLayout.CENTER);
        // When "Management Management" is clicked, go to teachings list screen
        managementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.dispose();
                createAndShowTeachingsListGUI();
            }
        });
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
    }
    /**
     * Creates and displays the teachings list GUI.
     * Simulates fetching data from an archive and displaying it.
     * After showing the list, simulates interrupting connection to SMOS server.
     */
    private static void createAndShowTeachingsListGUI() {
        JFrame teachingsFrame = new JFrame("Teachings List");
        teachingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        teachingsFrame.setSize(700, 500);
        teachingsFrame.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Teachings in Archive", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        teachingsFrame.add(titleLabel, BorderLayout.NORTH);
        // Status panel to display connection messages
        JPanel statusPanel = new JPanel();
        JLabel statusLabel = new JLabel("Status: Fetching data from SMOS server...");
        statusPanel.add(statusLabel);
        teachingsFrame.add(statusPanel, BorderLayout.NORTH);
        // Simulate data from archive (in real app, this would come from a database/API)
        // Added more diverse sample data for realistic demonstration
        ArrayList<String> teachings = new ArrayList<>();
        teachings.add("Teaching 1: Introduction to Java Programming");
        teachings.add("Teaching 2: Advanced Swing GUI Development");
        teachings.add("Teaching 3: Database Management Systems");
        teachings.add("Teaching 4: Modern Web Development Techniques");
        teachings.add("Teaching 5: Software Engineering Principles");
        teachings.add("Teaching 6: Network Security Fundamentals");
        teachings.add("Teaching 7: Mobile Application Development");
        teachings.add("Teaching 8: Cloud Computing Architecture");
        // Convert to array for JList
        String[] teachingArray = teachings.toArray(new String[0]);
        JList<String> teachingsList = new JList<>(teachingArray);
        teachingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teachingsList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        teachingsList.setBackground(new Color(240, 240, 240));
        JScrollPane scrollPane = new JScrollPane(teachingsList);
        teachingsFrame.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setPreferredSize(new Dimension(200, 40));
        buttonPanel.add(backButton);
        teachingsFrame.add(buttonPanel, BorderLayout.SOUTH);
        // Simulate data fetch delay using Swing Timer to show realistic sequence
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display teachings list
                // Then interrupt connection as per postcondition
                statusLabel.setText("Status: Connection to SMOS server interrupted.");
                statusLabel.setForeground(Color.RED);
                System.out.println("[SYSTEM] Connection to SMOS server interrupted as per postcondition.");
            }
        });
        timer.setRepeats(false);
        timer.start();
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teachingsFrame.dispose();
                createAndShowMainMenuGUI();
            }
        });
        teachingsFrame.setLocationRelativeTo(null);
        teachingsFrame.setVisible(true);
    }
}