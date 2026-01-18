'''
The main application window for the Administrator.
It displays a list of all users and allows the administrator to view
a user's details and subsequently manage their roles.
This simulates the administrator being logged in and able to view user details.
'''
package gui;
import model.User;
import model.UserManager;
import service.SMOSServer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Collection;
public class AdminDashboardFrame extends JFrame {
    private UserManager userManager;
    private SMOSServer smosServer;
    private JList<User> userList;
    private DefaultListModel<User> userListModel;
    private JButton viewDetailsButton;
    /**
     * Constructs a new AdminDashboardFrame.
     * @param manager The UserManager instance for user operations.
     * @param smosServer The SMOSServer instance for post-transaction actions.
     */
    public AdminDashboardFrame(UserManager manager, SMOSServer smosServer) {
        this.userManager = manager;
        this.smosServer = smosServer;
        setTitle("Admin Dashboard - User Role Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initUI();
        populateUserList();
    }
    /**
     * Initializes the user interface components of the main dashboard frame.
     */
    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        // Header
        JLabel titleLabel = new JLabel("Welcome, Administrator! Manage User Roles", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        // User list panel
        JPanel userListPanel = new JPanel(new BorderLayout());
        userListPanel.setBorder(BorderFactory.createTitledBorder("Users"));
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setCellRenderer(new UserListCellRenderer()); // Custom renderer for better display
        JScrollPane scrollPane = new JScrollPane(userList);
        userListPanel.add(scrollPane, BorderLayout.CENTER);
        // Listener for user selection changes
        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Only respond when selection has finished
                viewDetailsButton.setEnabled(userList.getSelectedIndex() != -1);
            }
        });
        add(userListPanel, BorderLayout.WEST); // Place user list on the left
        // Control panel for buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        viewDetailsButton = new JButton("View User Details");
        viewDetailsButton.setEnabled(false); // Initially disabled until a user is selected
        viewDetailsButton.addActionListener(e -> handleViewUserDetails());
        controlPanel.add(viewDetailsButton);
        add(controlPanel, BorderLayout.SOUTH); // Buttons at the bottom
        // Information panel on the right (can be expanded for more info if needed)
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Instructions"));
        JTextArea instructions = new JTextArea();
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setText("1. Select a user from the list on the left.\n" +
                            "2. Click 'View User Details' to see their current roles.\n" +
                            "3. In the user details window, click 'User Roles' to modify their roles.\n" +
                            "4. In the 'Manage Roles' dialog, select/deselect roles and click 'Send' to apply changes.\n" +
                            "   (Simulates: User roles are modified AND SMOS server connection is interrupted if changes were made.)"); // Updated instruction
        infoPanel.add(new JScrollPane(instructions), BorderLayout.CENTER);
        add(infoPanel, BorderLayout.CENTER); // Place instructions on the right
    }
    /**
     * Populates the user list with users from the UserManager.
     */
    public void populateUserList() {
        userListModel.clear();
        Collection<User> allUsers = userManager.getAllUsers();
        for (User user : allUsers) {
            userListModel.addElement(user);
        }
    }
    /**
     * Handles the "View User Details" button click event.
     * This method corresponds to the "viewdetTailsente" use case.
     * It opens a dialog to display details of the selected user.
     */
    private void handleViewUserDetails() {
        User selectedUser = userList.getSelectedValue();
        if (selectedUser != null) {
            // Precondition: The user has carried out the case of use "viewdetTailsente"
            // and the system is Viewing the details of a user
            UserDetailsDialog detailsDialog = new UserDetailsDialog(this, userManager, selectedUser, smosServer);
            detailsDialog.setVisible(true);
            // The detailsDialog will be updated when the RoleManagementDialog closes.
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user first.", "No User Selected", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Custom ListCellRenderer for the JList to display User objects more clearly.
     */
    private static class UserListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof User) {
                User user = (User) value;
                label.setText(user.getUsername() + " (ID: " + user.getUserId() + ")");
            }
            return label;
        }
    }
}