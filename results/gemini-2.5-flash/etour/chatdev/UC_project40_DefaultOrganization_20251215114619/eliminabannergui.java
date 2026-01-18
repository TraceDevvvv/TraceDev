'''
Provides a Graphical User Interface (GUI) for the 'EliminaBanner' use case.
Allows a restaurant operator to authenticate, view, and delete banner ads.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
class EliminaBannerGUI extends JFrame {
    private RestaurantOperatorSystem system;
    // GUI components for login
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    // GUI components for main functionality
    private JPanel mainPanel;
    private DefaultListModel<Banner> bannerListModel;
    private JList<Banner> bannerList;
    private JButton removeBannerButton;
    private JButton logoutButton; // Added for completeness
    /**
     * Constructs the EliminaBannerGUI, initializing the system and setting up the UI.
     */
    public EliminaBannerGUI() {
        super("EliminaBanner - Restaurant Operator System");
        system = new RestaurantOperatorSystem();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400); // Set a fixed size for the window
        setLocationRelativeTo(null); // Center the window
        // Initialize panels
        loginPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Rows, cols, hgap, vgap
        mainPanel = new JPanel(new BorderLayout(10, 10));
        setupLoginPanel();
        setupMainPanel();
        // Initially show the login panel
        add(loginPanel);
        // mainPanel will be added after successful login
        setVisible(true);
    }
    /**
     * Sets up the login panel components and their layout.
     * This panel is displayed before the user authenticates.
     */
    private void setupLoginPanel() {
        loginPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Add padding
        loginPanel.add(new JLabel("Username:"));
        usernameField = new JTextField("operator"); // Pre-fill for convenience
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField("pass"); // Pre-fill for convenience
        loginPanel.add(passwordField);
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        loginPanel.add(loginButton);
    }
    /**
     * Sets up the main panel components for banner management.
     * This panel is displayed after successful authentication.
     */
    private void setupMainPanel() {
        // Step 1: Select the feature for 'removal of the banner.
        // This is implicitly handled by the user navigating to this GUI and clicking the button.
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        // Step 2: View the list of banner associated with the point of rest.
        bannerListModel = new DefaultListModel<>();
        bannerList = new JList<>(bannerListModel);
        bannerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bannerList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scrollPane = new JScrollPane(bannerList);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        mainPanel.add(new JLabel("Available Banners:"), BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
        removeBannerButton = new JButton("Remove Selected Banner");
        // Initially disable the button until banners are loaded and selected
        removeBannerButton.setEnabled(false); // Disable by default
        removeBannerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedBanner();
            }
        });
        buttonPanel.add(removeBannerButton);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            system.authenticate("", ""); // Simulate logging out by clearing authentication
            showLoginView();
        });
        buttonPanel.add(logoutButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add a ListSelectionListener to enable/disable the remove button based on selection
        bannerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Only respond when selection changes are final
                    // Enable the remove button if an item is selected from the list, otherwise disable it.
                    removeBannerButton.setEnabled(bannerList.getSelectedIndex() != -1);
                }
            }
        });
    }
    /**
     * Handles the authentication process when the login button is clicked.
     * Checks credentials and switches between login and main views.
     */
    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (system.authenticate(username, password)) {
            JOptionPane.showMessageDialog(this, "Authentication successful!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
            showMainView();
            updateBannerList(); // Load banners after successful login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Switches the view from the login panel to the main banner management panel.
     */
    private void showMainView() {
        getContentPane().remove(loginPanel);
        getContentPane().add(mainPanel);
        revalidate();
        repaint();
        setTitle("EliminaBanner - Banner Management");
    }
    /**
     * Switches the view from the main banner management panel back to the login panel.
     */
    private void showLoginView() {
        getContentPane().remove(mainPanel);
        getContentPane().add(loginPanel);
        revalidate();
        repaint();
        setTitle("EliminaBanner - Restaurant Operator System");
        // Clear password field for security
        passwordField.setText("");
    }
    /**
     * Updates the JList with the current list of banners from the system.
     * This reflects the Step 2: View the list of banner associated with the point of rest.
     * It also manages the enabled state of the remove button.
     */
    private void updateBannerList() {
        bannerListModel.clear();
        for (Banner banner : system.getBanners()) {
            bannerListModel.addElement(banner);
        }
        if (!bannerListModel.isEmpty()) {
            bannerList.setSelectedIndex(0); // Select the first banner by default
            removeBannerButton.setEnabled(true); // Enable if banners are present
        } else {
            removeBannerButton.setEnabled(false); // Disable if no banners
        }
    }
    /**
     * Handles the removal of a selected banner, following the use case flow.
     * Steps 3-6 of the flow of events are implemented here.
     * Entry and Exit conditions are handled as well.
     */
    private void removeSelectedBanner() {
        // Step 3: Select a banner from the list.
        Banner selectedBanner = bannerList.getSelectedValue();
        if (selectedBanner == null) {
            JOptionPane.showMessageDialog(this, "Please select a banner to remove.", "No Banner Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Step 4: Displays a message confirming the deletion.
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to remove the banner:\n\"" + selectedBanner.getName() + "\"?",
                "Confirm Banner Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        // Step 5: Confirm the operation.
        if (confirmation == JOptionPane.YES_OPTION) {
            // Step 6. Removes the banner.
            int result = system.removeBanner(selectedBanner);
            if (result == 0) {
                // Exit condition: The system shall notify the successful elimination of the selected banner.
                JOptionPane.showMessageDialog(this,
                        "Banner \"" + selectedBanner.getName() + "\" successfully removed.",
                        "Deletion Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                updateBannerList(); // Refresh the list
            } else if (result == 1) {
                // Exit condition: Interruption of the connection to the server ETOUR.
                JOptionPane.showMessageDialog(this,
                        "Failed to remove banner. Server connection interrupted.",
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // This case should ideally not happen if banner is selected from the list.
                JOptionPane.showMessageDialog(this,
                        "Failed to remove banner. Banner not found.",
                        "Deletion Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Exit condition: The Point Of Operator Restaurant cancels the operation.
            JOptionPane.showMessageDialog(this,
                    "Banner removal operation cancelled.",
                    "Operation Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}