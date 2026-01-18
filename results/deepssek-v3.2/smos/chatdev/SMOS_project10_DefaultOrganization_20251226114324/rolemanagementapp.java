/*
 * Main entry point for the Role Management Application.
 * This application simulates an administrator assigning or removing roles from a user.
 * It uses a GUI built with Swing to collect input and display results.
 * The application includes proper user/role modeling, data persistence, validation,
 * and server connection simulation.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
// Server connection simulation class
class SMOSServerConnection {
    private boolean connected;
    public SMOSServerConnection() {
        this.connected = true;
    }
    public void connect() {
        if (!connected) {
            connected = true;
            System.out.println("SMOS server connected.");
        }
    }
    public void disconnect() {
        if (connected) {
            connected = false;
            System.out.println("SMOS server connection interrupted.");
        }
    }
    public boolean isConnected() {
        return connected;
    }
}
// User class to manage user details and roles
class User {
    private String username;
    private Set<String> roles;
    public User(String username) {
        this.username = username;
        this.roles = new HashSet<>();
    }
    public User(String username, Set<String> initialRoles) {
        this.username = username;
        this.roles = new HashSet<>(initialRoles);
    }
    public String getUsername() {
        return username;
    }
    public Set<String> getRoles() {
        return new HashSet<>(roles);
    }
    public void setRoles(Set<String> newRoles) {
        this.roles = new HashSet<>(newRoles);
    }
    public boolean addRole(String role) {
        return roles.add(role);
    }
    public boolean removeRole(String role) {
        return roles.remove(role);
    }
    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}
// Role management service to handle business logic
class RoleManagementService {
    private Map<String, User> users;
    private Set<String> availableRoles;
    private SMOSServerConnection serverConnection;
    public RoleManagementService() {
        this.users = new ConcurrentHashMap<>();
        this.availableRoles = new HashSet<>(Arrays.asList("Admin", "Editor", "Viewer", "Moderator", "User"));
        this.serverConnection = new SMOSServerConnection();
        // Initialize with some sample users
        users.put("User123", new User("User123", new HashSet<>(Arrays.asList("Viewer"))));
        users.put("AdminUser", new User("AdminUser", new HashSet<>(Arrays.asList("Admin", "Editor"))));
    }
    public User getUser(String username) {
        return users.get(username);
    }
    public Set<String> getAvailableRoles() {
        return new HashSet<>(availableRoles);
    }
    public boolean updateUserRoles(String username, Set<String> newRoles) {
        User user = users.get(username);
        if (user == null) {
            return false;
        }
        // Validate roles - ensure all roles are from available roles
        for (String role : newRoles) {
            if (!availableRoles.contains(role)) {
                return false;
            }
        }
        // Update user roles
        user.setRoles(newRoles);
        // Simulate server interruption as per postcondition
        serverConnection.disconnect();
        return true;
    }
    public void resetServerConnection() {
        serverConnection.connect();
    }
    public boolean isServerConnected() {
        return serverConnection.isConnected();
    }
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
    public Set<String> getAllUsernames() {
        return users.keySet();
    }
}
public class RoleManagementApp {
    private static RoleManagementService roleService;
    public static void main(String[] args) {
        // Initialize the role management service
        roleService = new RoleManagementService();
        // Ensure GUI runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("User Role Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        // Top panel: User selection
        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(new JLabel("Select User:"));
        JComboBox<String> userComboBox = new JComboBox<>();
        for (String username : roleService.getAllUsernames()) {
            userComboBox.addItem(username);
        }
        userPanel.add(userComboBox);
        frame.add(userPanel, BorderLayout.NORTH);
        // Center panel: Role management form
        JPanel centerPanel = new JPanel(new BorderLayout());
        // Display currently selected user
        JLabel currentUserLabel = new JLabel("", SwingConstants.CENTER);
        centerPanel.add(currentUserLabel, BorderLayout.NORTH);
        // Role selection panel
        JPanel rolesPanel = new JPanel();
        rolesPanel.setLayout(new GridLayout(0, 1, 5, 5));
        rolesPanel.setBorder(BorderFactory.createTitledBorder("Select roles to assign/remove"));
        // Store checkboxes in a list
        java.util.List<JCheckBox> roleCheckboxes = new ArrayList<>();
        for (String role : roleService.getAvailableRoles()) {
            JCheckBox checkBox = new JCheckBox(role);
            rolesPanel.add(checkBox);
            roleCheckboxes.add(checkBox);
        }
        centerPanel.add(new JScrollPane(rolesPanel), BorderLayout.CENTER);
        frame.add(centerPanel, BorderLayout.CENTER);
        // Function to update role checkboxes based on selected user
        Runnable updateRoles = () -> {
            String selectedUser = (String) userComboBox.getSelectedItem();
            if (selectedUser != null) {
                currentUserLabel.setText("Managing roles for user: " + selectedUser);
                User user = roleService.getUser(selectedUser);
                if (user != null) {
                    Set<String> userRoles = user.getRoles();
                    for (JCheckBox cb : roleCheckboxes) {
                        cb.setSelected(userRoles.contains(cb.getText()));
                    }
                }
            }
        };
        // Initial update
        updateRoles.run();
        // Update when user selection changes
        userComboBox.addActionListener(e -> updateRoles.run());
        // Bottom panel: buttons
        JPanel buttonPanel = new JPanel();
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");
        // Action for Send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUser = (String) userComboBox.getSelectedItem();
                if (selectedUser == null) {
                    JOptionPane.showMessageDialog(frame,
                            "Please select a user first.",
                            "No User Selected",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Collect selected roles
                Set<String> newRoles = new HashSet<>();
                for (JCheckBox cb : roleCheckboxes) {
                    if (cb.isSelected()) {
                        newRoles.add(cb.getText());
                    }
                }
                // Update user roles through service
                boolean success = roleService.updateUserRoles(selectedUser, newRoles);
                if (success) {
                    JOptionPane.showMessageDialog(frame,
                            "Roles for " + selectedUser + " successfully updated.\n" +
                            "New roles: " + newRoles + "\n" +
                            "SMOS server connection has been interrupted.",
                            "Roles Updated",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Update display to reflect changes
                    updateRoles.run();
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Failed to update roles. User not found or invalid roles selected.",
                            "Update Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Action for Reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset roles to current user's roles
                updateRoles.run();
                // Reset server connection if needed
                if (!roleService.isServerConnected()) {
                    roleService.resetServerConnection();
                    JOptionPane.showMessageDialog(frame,
                            "Server connection has been re-established.",
                            "Server Reset",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        // Action for Exit button
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