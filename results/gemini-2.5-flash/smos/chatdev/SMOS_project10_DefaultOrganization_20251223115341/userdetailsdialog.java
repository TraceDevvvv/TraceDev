'''
A JDialog that displays the details of a specific user.
It includes a button to open the RoleManagementDialog for that user,
addressing the "viewdetTailsente" precondition and the "User Roles" button click.
'''
package gui;
import model.User;
import model.UserManager;
import service.SMOSServer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Set;
public class UserDetailsDialog extends JDialog {
    private UserManager userManager;
    private User user;
    private JLabel usernameLabel;
    private JTextArea rolesTextArea;
    private SMOSServer smosServer;
    /**
     * Constructs a new UserDetailsDialog.
     * @param owner The parent frame for this dialog.
     * @param manager The UserManager instance to interact with.
     * @param user The User object whose details are to be displayed.
     * @param smosServer The SMOSServer instance.
     */
    public UserDetailsDialog(JFrame owner, UserManager manager, User user, SMOSServer smosServer) {
        super(owner, "Details for " + user.getUsername(), true); // Modal dialog
        this.userManager = manager;
        this.user = user; // The user object might be modified externally, so ensure we have a reference.
        this.smosServer = smosServer;
        initUI();
        updateDisplay();
    }
    /**
     * Initializes the user interface components of the dialog.
     */
    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setSize(350, 300);
        setLocationRelativeTo(getOwner());
        // User information panel
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        usernameLabel = new JLabel();
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(Box.createVerticalStrut(10));
        JLabel rolesHeader = new JLabel("Current Roles:");
        rolesHeader.setFont(new Font("SansSerif", Font.PLAIN, 12));
        userInfoPanel.add(rolesHeader);
        rolesTextArea = new JTextArea(5, 20);
        rolesTextArea.setEditable(false);
        rolesTextArea.setLineWrap(true);
        rolesTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(rolesTextArea);
        userInfoPanel.add(scrollPane);
        add(userInfoPanel, BorderLayout.CENTER);
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton manageRolesButton = new JButton("User Roles");
        manageRolesButton.addActionListener(e -> handleManageRoles());
        buttonPanel.add(manageRolesButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Updates the display with the current user's information and roles.
     * This method should be called if the user's roles are modified externally
     * (e.g., by the RoleManagementDialog).
     */
    public void updateDisplay() {
        usernameLabel.setText("User: " + user.getUsername() + " (ID: " + user.getUserId() + ")");
        Set<String> currentRoles = user.getRoles();
        if (currentRoles.isEmpty()) {
            rolesTextArea.setText("No roles assigned.");
        } else {
            rolesTextArea.setText(String.join("\n", currentRoles));
        }
        repaint(); // Ensure GUI refreshes
    }
    /**
     * Handles the "User Roles" button click event.
     * Opens the RoleManagementDialog for the current user.
     */
    private void handleManageRoles() {
        // Precondition: The user clicks on the "User Roles" button
        // System event 1: Displays the role management form
        RoleManagementDialog roleDialog = new RoleManagementDialog((JFrame) getParent(), userManager, user, smosServer);
        roleDialog.setVisible(true); // This dialog is modal and blocks until closed
        // After the role management dialog closes, update this dialog's display
        // to reflect any changes made to the user's roles.
        updateDisplay(); 
    }
}