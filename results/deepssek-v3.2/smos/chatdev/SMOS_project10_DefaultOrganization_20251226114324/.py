/**
 * Main entry point for the Role Management Application.
 * This application simulates an administrator assigning or removing roles from a user.
 * It uses a GUI built with Swing to collect input and display results.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class RoleManagementApp {
    // Sample list of available roles
    private static final String[] ALL_ROLES = {"Admin", "Editor", "Viewer", "Moderator", "User"};
    private static Set<String> userRoles = new HashSet<>(Arrays.asList("Viewer")); // Initial user roles
    private static String currentUser = "User123"; // Simulated user
    public static void main(String[] args) {
        // Ensure GUI runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("User Role Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        // Top panel: display current user info
        JLabel userLabel = new JLabel("Managing roles for user: " + currentUser, SwingConstants.CENTER);
        frame.add(userLabel, BorderLayout.NORTH);
        // Center panel: list of all roles with checkboxes
        JPanel rolesPanel = new JPanel();
        rolesPanel.setLayout(new GridLayout(ALL_ROLES.length, 1, 5, 5));
        rolesPanel.setBorder(BorderFactory.createTitledBorder("Select roles to assign/remove"));
        // Store checkboxes in a list to access their states
        java.util.List<JCheckBox> roleCheckboxes = new ArrayList<>();
        for (String role : ALL_ROLES) {
            JCheckBox checkBox = new JCheckBox(role);
            checkBox.setSelected(userRoles.contains(role)); // Pre-select if user already has the role
            rolesPanel.add(checkBox);
            roleCheckboxes.add(checkBox);
        }
        frame.add(new JScrollPane(rolesPanel), BorderLayout.CENTER);
        // Bottom panel: buttons
        JPanel buttonPanel = new JPanel();
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");
        // Action for Send button: assign/remove roles based on checkboxes
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<String> newRoles = new HashSet<>();
                for (JCheckBox cb : roleCheckboxes) {
                    if (cb.isSelected()) {
                        newRoles.add(cb.getText());
                    }
                }
                // Update user roles
                userRoles = newRoles;
                JOptionPane.showMessageDialog(frame,
                        "Roles for " + currentUser + " updated.\nAssigned roles: " + userRoles,
                        "Roles Updated",
                        JOptionPane.INFORMATION_MESSAGE);
                // Simulate SMOS server interruption (postcondition)
                System.out.println("SMOS server connection interrupted.");
                // In a real application, you would close server connections here
            }
        });
        // Action for Reset button: reset checkboxes to current user roles
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox cb : roleCheckboxes) {
                    cb.setSelected(userRoles.contains(cb.getText()));
                }
            }
        });
        // Action for Exit button: close application
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(sendButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        // Make frame visible
        frame.setVisible(true);
    }
}