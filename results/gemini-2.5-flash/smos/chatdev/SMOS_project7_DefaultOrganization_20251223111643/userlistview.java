'''
This file defines the UserListView class, which serves as the main application window
for the administrator. It displays a list of all users from the system and allows
the administrator to view the detailed information of a selected user by clicking
a "Details" button. It handles the precondition of viewing the list of users
and initiating the detail view.
'''
package com.chatdev.adminapp.view;
import com.chatdev.adminapp.model.User;
import com.chatdev.adminapp.service.UserService;
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class UserListView extends JFrame {
    private JList<User> userList;
    private DefaultListModel<User> listModel;
    private JButton detailsButton;
    /**
     * Constructs a new UserListView.
     * This frame displays a list of users and a button to view their details.
     * It simulates the administrator's perspective of "ViewingLanciutenti".
     */
    public UserListView() {
        super("Administrator: User List");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Close operation for the main window
        setSize(500, 400); // Set window size
        setLocationRelativeTo(null); // Center the window on screen
        initComponents(); // Initialize GUI components
        loadUserData(); // Load user data into the list
        addListeners(); // Add event listeners
    }
    /**
     * Initializes the GUI components for the UserListView.
     * This includes a JList to display users and a JButton to view details.
     */
    private void initComponents() {
        // Use BorderLayout for the main frame
        setLayout(new BorderLayout());
        // Panel for the list of users
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one user to be selected at a time
        userList.setFixedCellHeight(25); // Set a fixed height for list items for better appearance
        // Add a scroll pane to the JList in case there are many users
        JScrollPane scrollPane = new JScrollPane(userList);
        listPanel.add(new JLabel("Select a user to view details:"), BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        detailsButton = new JButton("View Details");
        buttonPanel.add(detailsButton);
        // Add panels to the frame
        add(listPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Loads user data from the UserService and populates the JList.
     * This simulates the "system is viewing the list of users in the system" precondition.
     */
    private void loadUserData() {
        List<User> users = UserService.getAllUsers();
        for (User user : users) {
            listModel.addElement(user);
        }
        if (!users.isEmpty()) {
            userList.setSelectedIndex(0); // Select the first user by default
        }
    }
    /**
     * Adds action listeners to the buttons.
     * Specifically, it handles the "Details" button click event.
     */
    private void addListeners() {
        detailsButton.addActionListener(e -> {
            User selectedUser = userList.getSelectedValue();
            if (selectedUser != null) {
                // Precondition: user click on the "Details" button (handled by this action listener)
                displayUserDetails(selectedUser);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a user from the list to view details.",
                        "No User Selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    /**
     * Displays the detailed information of the given user in a new dialog window.
     * This fulfills the event sequence step: "Displays the detailed user information".
     *
     * @param user The User object whose details are to be displayed.
     */
    private void displayUserDetails(User user) {
        // Create and show the UserDetailView dialog
        UserDetailView detailView = new UserDetailView(this, user);
        detailView.setVisible(true);
        // Postcondition: The system displays detailed information about a user (handled by UserDetailView)
    }
}