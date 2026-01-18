'''
A JDialog for administrators to assign or remove roles from a specific user.
It displays all available roles as checkboxes and updates the user's roles
in the UserManager upon clicking the "Send" button.
'''
package gui;
import model.User;
import model.UserManager;
import service.SMOSServer; 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class RoleManagementDialog extends JDialog {
    private UserManager userManager;
    private User user;
    private JPanel rolesPanel;
    private List<JCheckBox> roleCheckboxes;
    private SMOSServer smosServer; // Service to simulate server connection interruption
    /**
     * Constructs a new RoleManagementDialog.
     * @param owner The parent frame for this dialog.
     * @param manager The UserManager instance to interact with.
     * @param user The User whose roles are being managed.
     * @param smosServer The SMOSServer instance for post-transaction actions.
     */
    public RoleManagementDialog(JFrame owner, UserManager manager, User user, SMOSServer smosServer) {
        super(owner, "Manage Roles for " + user.getUsername(), true); // Modal dialog
        this.userManager = manager;
        this.user = user;
        this.smosServer = smosServer;
        this.roleCheckboxes = new ArrayList<>();
        initUI();
        populateCheckboxes();
    }
    /**
     * Initializes the user interface components of the dialog.
     */
    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setSize(400, 400);
        setLocationRelativeTo(getOwner());
        // Header for user information
        JLabel headerLabel = new JLabel("Managing roles for: " + user.getUsername(), SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(headerLabel, BorderLayout.NORTH);
        // Panel for checkboxes
        rolesPanel = new JPanel();
        rolesPanel.setLayout(new BoxLayout(rolesPanel, BoxLayout.Y_AXIS));
        rolesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(rolesPanel);
        add(scrollPane, BorderLayout.CENTER);
        // Button panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> handleSend());
        buttonPanel.add(sendButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates the rolesPanel with checkboxes for each available role.
     * Each checkbox is pre-selected if the user already has that role.
     */
    private void populateCheckboxes() {
        rolesPanel.removeAll(); // Clear existing checkboxes if any
        roleCheckboxes.clear(); // Clear the list of checkbox references
        Set<String> currentUserRoles = user.getRoles();
        Set<String> allAvailableRoles = userManager.getAvailableRoles();
        if (allAvailableRoles.isEmpty()) {
            rolesPanel.add(new JLabel("No roles available to assign."));
        } else {
            for (String role : allAvailableRoles) {
                JCheckBox checkBox = new JCheckBox(role);
                checkBox.setSelected(currentUserRoles.contains(role));
                rolesPanel.add(checkBox);
                roleCheckboxes.add(checkBox);
            }
        }
        rolesPanel.revalidate();
        rolesPanel.repaint();
    }
    /**
     * Handles the "Send" button click event.
     * This method updates the user's roles based on the checkbox selections,
     * triggers the SMOS server disconnection (postcondition), and closes the dialog.
     */
    private void handleSend() {
        Set<String> rolesToAssign = new HashSet<>();
        // Determine which roles are selected in the form
        for (JCheckBox checkBox : roleCheckboxes) {
            String roleName = checkBox.getText();
            if (checkBox.isSelected()) {
                rolesToAssign.add(roleName);
            }
        }
        boolean rolesModified = false;
        Set<String> initialRoles = user.getRoles(); // Get the user's roles BEFORE modification
        // Roles to be removed (those in initialRoles but not selected in rolesToAssign)
        Set<String> toRemove = new HashSet<>(initialRoles);
        toRemove.removeAll(rolesToAssign);
        for (String role : toRemove) {
            // Attempt to remove the role; if successful, roles were modified
            if (userManager.removeRole(user.getUserId(), role)) { 
                rolesModified = true;
            }
        }
        // Roles to be added (those selected in rolesToAssign but not in initialRoles)
        Set<String> toAdd = new HashSet<>(rolesToAssign);
        toAdd.removeAll(initialRoles);
        for (String role : toAdd) {
            // Attempt to assign the role; if successful, roles were modified
            if (userManager.assignRole(user.getUserId(), role)) { 
                rolesModified = true;
            }
        }
        if (rolesModified) {
            JOptionPane.showMessageDialog(this, "User roles modified successfully for " + user.getUsername() + ".", "Roles Updated", JOptionPane.INFORMATION_MESSAGE);
            // Postcondition: User roles are modified
            // This is handled by the userManager.assignRole and userManager.removeRole calls.
            // Postcondition: The administrator interrupts the connection to the SMOS server interrupted
            // This should only happen if roles were actually modified.
            smosServer.disconnect(); 
        } else {
            JOptionPane.showMessageDialog(this, "No changes made to user roles.", "No Changes", JOptionPane.INFORMATION_MESSAGE);
        }
        dispose(); // Close the dialog
    }
}