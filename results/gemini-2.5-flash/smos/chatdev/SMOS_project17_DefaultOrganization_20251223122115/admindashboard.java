'''
Represents the main dashboard for an administrator after successful login.
This dashboard provides access to various management functions, including
address management.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AdminDashboard extends JFrame {
    private JPanel mainContentPanel; // Panel to switch between different views
    private AddressService addressService; // Service to interact with address data
    private AddressListPanel addressListPanel; // Reference to the AddressListPanel instance
    /**
     * Constructs a new AdminDashboard.
     * Initializes the GUI components, including navigation buttons and a dynamic
     * content area.
     */
    public AdminDashboard() {
        addressService = new AddressService(); // Initialize the service
        // Set up the JFrame properties
        setTitle("Administrator Dashboard - Address Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout()); // Use BorderLayout for overall layout
        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(e -> {
            dispose(); // Close dashboard
            new LoginScreen(); // Return to login screen
        });
        fileMenu.add(logoutItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        // Create a sidebar for navigation (e.g., using a JPanel with FlowLayout or BoxLayout)
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); // Vertical layout
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        sidebar.setPreferredSize(new Dimension(200, getHeight())); // Fixed width sidebar
        sidebar.setBackground(new Color(240, 240, 240));
        // Create the "Address Management" button
        JButton addressManagementButton = new JButton("Address Management");
        addressManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button horizontally
        addressManagementButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, addressManagementButton.getMinimumSize().height));
        addressManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This event sequence part fulfills: "The user click on the "Address Management" button"
                displayAddressList();
            }
        });
        // Add some spacing and the button to the sidebar
        sidebar.add(Box.createVerticalStrut(20)); // Space from top
        sidebar.add(addressManagementButton);
        sidebar.add(Box.createVerticalGlue()); // Pushes content to the top
        add(sidebar, BorderLayout.WEST); // Add sidebar to the west (left)
        // Main content panel where different views will be displayed
        mainContentPanel = new JPanel(new CardLayout()); // Use CardLayout to swap panels
        // Add a default welcome screen
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        JLabel welcomeLabel = new JLabel("Welcome, Administrator! Please select an option from the left.");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        welcomePanel.add(welcomeLabel);
        mainContentPanel.add(welcomePanel, "Welcome");
        ((CardLayout) mainContentPanel.getLayout()).show(mainContentPanel, "Welcome");
        add(mainContentPanel, BorderLayout.CENTER); // Add main content to the center
        setVisible(true);
    }
    /**
     * Displays the address list in the main content area.
     * This method handles the event sequence: "1. Displays the address list."
     * and the precondition: "The user click on the "Address Management" button".
     * It also initiates the postcondition: "Connection to the SMOS server interrupted"
     * by calling the service after displaying the list.
     *
     * This method has been revised to correctly manage the AddressListPanel
     * using CardLayout, ensuring that only one instance of AddressListPanel
     * is created and added to the main content area, rather than creating a new
     * instance every time the button is clicked.
     */
    private void displayAddressList() {
        CardLayout cl = (CardLayout) (mainContentPanel.getLayout());
        // Check if the AddressListPanel has already been created and added
        if (addressListPanel == null) {
            // If not, create it and add it to the CardLayout
            addressListPanel = new AddressListPanel(addressService);
            mainContentPanel.add(addressListPanel, "AddressListCard");
            // The addressListPanel.loadAddressesAndInterruptConnection() logic is in its constructor,
            // so it will run when the panel is created.
        }
        // Show the AddressListCard (whether it was just added or already existed)
        cl.show(mainContentPanel, "AddressListCard");
        // No need for revalidate/repaint here as CardLayout.show implicitly handles updates.
        // If the AddressListPanel needed its data refreshed each time it's shown,
        // a refresh method could be called on 'addressListPanel' here.
    }
}