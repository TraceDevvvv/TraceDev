/**
 * Main application that demonstrates the ViewRefreshmentPointCard use case.
 * This simulates the user selecting a refreshment point from a search result.
 * The program handles the complete use case flow with GUI interface, including
 * login simulation, server connection handling, and the full use case sequence.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.border.*;
import java.net.ConnectException;
/**
 * RefreshmentPoint represents a point of rest with various attributes.
 */
class RefreshmentPoint {
    private String name;
    private String location;
    private String type;
    private double rating;
    private String description;
    private List<String> facilities;
    public RefreshmentPoint(String name, String location, String type, 
                           double rating, String description, List<String> facilities) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.rating = rating;
        this.description = description;
        this.facilities = facilities;
    }
    // Getter methods
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getType() { return type; }
    public double getRating() { return rating; }
    public String getDescription() { return description; }
    public List<String> getFacilities() { return facilities; }
    /**
     * Creates a sample refreshment point for demonstration purposes.
     * @return A sample refreshment point
     */
    public static RefreshmentPoint createSamplePoint() {
        return new RefreshmentPoint(
            "Mountain View Cafe",
            "123 Alpine Road, Mountain Peak",
            "Cafe",
            4.5,
            "A cozy cafe offering panoramic mountain views, serving fresh coffee " +
            "and homemade pastries. Perfect for hikers and travelers.",
            Arrays.asList("WiFi", "Restrooms", "Outdoor Seating", "Parking", "Wheelchair Access")
        );
    }
    @Override
    public String toString() {
        return name;
    }
}
/**
 * RefreshmentPointCardPanel displays detailed information about a selected refreshment point.
 */
class RefreshmentPointCardPanel extends JPanel {
    private JLabel nameLabel;
    private JTextArea descriptionArea;
    private JLabel locationLabel;
    private JLabel typeLabel;
    private JLabel ratingLabel;
    private JList<String> facilitiesList;
    private DefaultListModel<String> facilitiesModel;
    private JLabel statusLabel;
    /**
     * Constructor sets up the card panel layout and components.
     */
    public RefreshmentPointCardPanel() {
        // Set layout and border
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Refreshment Point Details"));
        // Status bar at top
        statusLabel = new JLabel("Ready to display point details.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(statusLabel, BorderLayout.NORTH);
        // Create a panel for details
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        detailsPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameLabel = new JLabel("---");
        detailsPanel.add(nameLabel, gbc);
        // Location
        gbc.gridx = 0; gbc.gridy = 1;
        detailsPanel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        locationLabel = new JLabel("---");
        detailsPanel.add(locationLabel, gbc);
        // Type
        gbc.gridx = 0; gbc.gridy = 2;
        detailsPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        typeLabel = new JLabel("---");
        detailsPanel.add(typeLabel, gbc);
        // Rating
        gbc.gridx = 0; gbc.gridy = 3;
        detailsPanel.add(new JLabel("Rating:"), gbc);
        gbc.gridx = 1;
        ratingLabel = new JLabel("---");
        detailsPanel.add(ratingLabel, gbc);
        // Facilities
        gbc.gridx = 0; gbc.gridy = 4;
        detailsPanel.add(new JLabel("Facilities:"), gbc);
        gbc.gridx = 1;
        facilitiesModel = new DefaultListModel<>();
        facilitiesList = new JList<>(facilitiesModel);
        JScrollPane facilitiesScroll = new JScrollPane(facilitiesList);
        facilitiesScroll.setPreferredSize(new Dimension(150, 80));
        detailsPanel.add(facilitiesScroll, gbc);
        // Description
        gbc.gridx = 0; gbc.gridy = 5;
        detailsPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        detailsPanel.add(descriptionScroll, gbc);
        add(detailsPanel, BorderLayout.CENTER);
    }
    /**
     * Updates the card panel with details of a refreshment point.
     * @param point The RefreshmentPoint to display, or null to clear the panel.
     */
    public void updateCard(RefreshmentPoint point) {
        if (point == null) {
            nameLabel.setText("---");
            locationLabel.setText("---");
            typeLabel.setText("---");
            ratingLabel.setText("---");
            descriptionArea.setText("");
            facilitiesModel.clear();
            statusLabel.setText("No point selected. Please select a point from search results.");
            return;
        }
        // Update labels and text areas with point details
        nameLabel.setText(point.getName());
        locationLabel.setText(point.getLocation());
        typeLabel.setText(point.getType());
        ratingLabel.setText(String.format("%.1f", point.getRating()));
        descriptionArea.setText(point.getDescription());
        // Update facilities list
        facilitiesModel.clear();
        for (String facility : point.getFacilities()) {
            facilitiesModel.addElement(facility);
        }
        statusLabel.setText("Displaying details for: " + point.getName());
    }
    /**
     * Updates the status message for connection issues.
     * @param message Status message to display
     */
    public void setStatus(String message) {
        statusLabel.setText(message);
    }
}
/**
 * ETOURServerSimulator handles server connection status and data retrieval.
 */
class ETOURServerSimulator {
    private boolean connected;
    private Random random;
    public ETOURServerSimulator() {
        random = new Random();
        connected = true; // Start connected
    }
    /**
     * Checks connection to ETOUR server with simulation of intermittent failures.
     * @return true if connected, false otherwise
     */
    public boolean checkConnection() {
        // Simulate 90% chance of successful connection
        connected = random.nextDouble() > 0.1;
        return connected;
    }
    /**
     * Simulates fetching search results from server.
     * @return List of refreshment points or null if connection fails
     */
    public List<RefreshmentPoint> getSearchResults() {
        if (!checkConnection()) {
            return null;
        }
        // Return sample data
        return Arrays.asList(
            RefreshmentPoint.createSamplePoint(),
            new RefreshmentPoint(
                "Riverside Restaurant",
                "45 River Street, Waterside",
                "Restaurant",
                4.2,
                "Fine dining by the river with fresh local ingredients. " +
                "Specializes in seafood and regional cuisine.",
                Arrays.asList("Full Bar", "River View", "Private Rooms", "Valet Parking", "Live Music")
            ),
            new RefreshmentPoint(
                "City Center Coffee",
                "789 Main Street, Downtown",
                "Coffee Shop",
                4.7,
                "Modern coffee shop with specialty brews and comfortable workspace. " +
                "Open early until late.",
                Arrays.asList("Free WiFi", "Power Outlets", "Laptop Friendly", "Vegan Options", "Delivery")
            ),
            new RefreshmentPoint(
                "Highway Diner",
                "Exit 42, Interstate 95",
                "Diner",
                3.8,
                "24-hour classic American diner with hearty meals for travelers. " +
                "Known for breakfast specials.",
                Arrays.asList("24/7 Service", "Fuel Station", "Truck Parking", "Takeout", "Family Friendly")
            )
        );
    }
    /**
     * Simulates connection interruption.
     */
    public void simulateDisconnection() {
        connected = false;
    }
    /**
     * Attempts to reconnect to server.
     * @return true if reconnection successful
     */
    public boolean reconnect() {
        connected = random.nextDouble() > 0.3; // 70% chance of successful reconnect
        return connected;
    }
    public boolean isConnected() {
        return connected;
    }
}
/**
 * Main application class implementing the complete ViewRefreshmentPointCard use case.
 */
public class ViewRefreshmentPointCard {
    private JFrame mainFrame;
    private JPanel currentPanel;
    private ETOURServerSimulator server;
    private boolean agencyLoggedIn;
    private JLabel connectionStatusLabel;
    /**
     * Main method launches the application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ViewRefreshmentPointCard().run();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                    "Application failed to start: " + e.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    /**
     * Runs the application with complete use case flow.
     */
    public void run() {
        server = new ETOURServerSimulator();
        agencyLoggedIn = false;
        createMainWindow();
        showLoginScreen();
    }
    /**
     * Creates the main application window.
     */
    private void createMainWindow() {
        mainFrame = new JFrame("ETOUR Agency Portal - View Refreshment Point Card");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 650);
        mainFrame.setLayout(new BorderLayout());
        // Add connection status bar at bottom
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        connectionStatusLabel = new JLabel("Checking server connection...");
        connectionStatusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusPanel.add(connectionStatusLabel);
        mainFrame.add(statusPanel, BorderLayout.SOUTH);
        // Update connection status
        updateConnectionStatus();
        mainFrame.setLocationRelativeTo(null);
    }
    /**
     * Shows the agency operator login screen (entry condition).
     */
    private void showLoginScreen() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;
        // Title
        JLabel titleLabel = new JLabel("ETOUR Agency Operator Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(titleLabel, gbc);
        // Username
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        loginPanel.add(new JLabel("Username:"), gbc);
        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);
        // Password
        gbc.gridy = 2;
        gbc.gridx = 0;
        loginPanel.add(new JLabel("Password:"), gbc);
        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);
        // Login button
        gbc.gridwidth = 2;
        gbc.gridy = 3;
        gbc.gridx = 0;
        JButton loginButton = new JButton("Login as Agency Operator");
        loginPanel.add(loginButton, gbc);
        // Info about use case entry condition
        gbc.gridy = 4;
        JTextArea infoArea = new JTextArea(
            "Use Case Entry Condition: The agency must be logged in.\n" +
            "After login, you can search for refreshment points and view their details."
        );
        infoArea.setEditable(false);
        infoArea.setWrapStyleWord(true);
        infoArea.setLineWrap(true);
        infoArea.setBackground(loginPanel.getBackground());
        loginPanel.add(infoArea, gbc);
        // Check server status
        if (!server.checkConnection()) {
            JOptionPane.showMessageDialog(mainFrame,
                "Cannot connect to ETOUR server. Please check your network connection.",
                "Server Connection Error",
                JOptionPane.ERROR_MESSAGE);
        }
        // Login button action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
            // Simple validation (in real app would validate against database)
            if (username.isEmpty() || password.length == 0) {
                JOptionPane.showMessageDialog(mainFrame,
                    "Please enter both username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Check server connection before login
            if (!server.isConnected()) {
                int choice = JOptionPane.showConfirmDialog(mainFrame,
                    "ETOUR server is not available. Attempt to reconnect?",
                    "Server Offline",
                    JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (server.reconnect()) {
                        updateConnectionStatus();
                    } else {
                        JOptionPane.showMessageDialog(mainFrame,
                            "Reconnection failed. Please try again later.",
                            "Connection Failed",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    return;
                }
            }
            agencyLoggedIn = true;
            updateConnectionStatus();
            showMainApplication();
        });
        // Clear current panel and show login
        if (currentPanel != null) {
            mainFrame.remove(currentPanel);
        }
        currentPanel = loginPanel;
        mainFrame.add(currentPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
    /**
     * Shows the main application interface after login.
     */
    private void showMainApplication() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel welcomeLabel = new JLabel("Welcome Agency Operator!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        JPanel headerRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            agencyLoggedIn = false;
            currentPanel = null;
            showLoginScreen();
        });
        headerRightPanel.add(logoutButton);
        headerPanel.add(headerRightPanel, BorderLayout.EAST);
        // Card panel for displaying point details
        RefreshmentPointCardPanel cardPanel = new RefreshmentPointCardPanel();
        // Control panel for use case flow
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Use Case Flow Control"));
        // Step 1: Simulate SearchRefreshmentPoint use case
        JPanel step1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton searchButton = new JButton("1. Search Refreshment Points");
        JLabel step1Label = new JLabel("Simulate the SearchRefreshmentPoint use case");
        step1Panel.add(searchButton);
        step1Panel.add(step1Label);
        // Step 2: Select point from results
        JPanel step2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        step2Panel.add(new JLabel("2. Select Point: "));
        JComboBox<String> pointSelector = new JComboBox<>();
        pointSelector.setEnabled(false);
        JButton selectButton = new JButton("Select");
        selectButton.setEnabled(false);
        step2Panel.add(pointSelector);
        step2Panel.add(selectButton);
        // Step 3: View card
        JPanel step3Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton viewCardButton = new JButton("3. View Point Card");
        viewCardButton.setEnabled(false);
        JButton clearButton = new JButton("Clear Card");
        step3Panel.add(viewCardButton);
        step3Panel.add(clearButton);
        // Info panel
        JTextArea infoArea = new JTextArea(
            "Use Case: ViewRefreshmentPointCard\n" +
            "Description: View the details of a selected point of rest.\n" +
            "Participating Actor: Agency Operator\n\n" +
            "Flow of Events:\n" +
            "1. View list of points from SearchRefreshmentPoint use case results\n" +
            "2. Select a point and activate view card function\n" +
            "3. System displays detailed information\n\n" +
            "Exit Conditions: Details displayed successfully.\n" +
            "Exception: Handle server connection interruption gracefully."
        );
        infoArea.setEditable(false);
        infoArea.setWrapStyleWord(true);
        infoArea.setLineWrap(true);
        infoArea.setBackground(new Color(240, 240, 240));
        JScrollPane infoScroll = new JScrollPane(infoArea);
        infoScroll.setPreferredSize(new Dimension(300, 150));
        // Store search results for later use
        final List<RefreshmentPoint>[] searchResults = new List[]{null};
        // Search button action (Step 1)
        searchButton.addActionListener(e -> {
            if (!server.isConnected()) {
                JOptionPane.showMessageDialog(mainFrame,
                    "Cannot perform search: ETOUR server is not connected.",
                    "Server Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Simulate search operation
            searchResults[0] = server.getSearchResults();
            if (searchResults[0] == null) {
                JOptionPane.showMessageDialog(mainFrame,
                    "Search failed: Connection to ETOUR server was interrupted.\n" +
                    "Please check your connection and try again.",
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Populate dropdown with results
            pointSelector.removeAllItems();
            for (RefreshmentPoint point : searchResults[0]) {
                pointSelector.addItem(point.getName());
            }
            pointSelector.setEnabled(true);
            selectButton.setEnabled(true);
            JOptionPane.showMessageDialog(mainFrame,
                "Search completed successfully!\n" +
                "Found " + searchResults[0].size() + " refreshment points.\n" +
                "Please select a point to view its details card.",
                "Search Results",
                JOptionPane.INFORMATION_MESSAGE);
        });
        // Select button action (Step 2)
        selectButton.addActionListener(e -> {
            int selectedIndex = pointSelector.getSelectedIndex();
            if (selectedIndex >= 0 && searchResults[0] != null && 
                selectedIndex < searchResults[0].size()) {
                viewCardButton.setEnabled(true);
                JOptionPane.showMessageDialog(mainFrame,
                    "Selected: " + searchResults[0].get(selectedIndex).getName() + "\n" +
                    "Click 'View Point Card' to see details.",
                    "Point Selected",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // View card button action (Step 3 - Main use case)
        viewCardButton.addActionListener(e -> {
            int selectedIndex = pointSelector.getSelectedIndex();
            if (selectedIndex >= 0 && searchResults[0] != null && 
                selectedIndex < searchResults[0].size()) {
                // Check server connection before displaying
                if (!server.isConnected()) {
                    cardPanel.setStatus("Error: Server connection interrupted.");
                    JOptionPane.showMessageDialog(mainFrame,
                        "Cannot display point card: Connection to ETOUR server interrupted.\n" +
                        "Data may be incomplete or unavailable.",
                        "Connection Interrupted",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                RefreshmentPoint selectedPoint = searchResults[0].get(selectedIndex);
                cardPanel.updateCard(selectedPoint);
                // Log use case completion
                System.out.println("ViewRefreshmentPointCard use case completed successfully.");
                System.out.println("Point displayed: " + selectedPoint.getName());
                System.out.println("Agency operator logged in: " + agencyLoggedIn);
                System.out.println("Server connected: " + server.isConnected());
            }
        });
        // Clear button action
        clearButton.addActionListener(e -> {
            cardPanel.updateCard(null);
        });
        // Add server disconnect simulation button
        JPanel simPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton simDisconnectButton = new JButton("Simulate Server Disconnection");
        simDisconnectButton.addActionListener(e -> {
            server.simulateDisconnection();
            updateConnectionStatus();
            pointSelector.setEnabled(false);
            selectButton.setEnabled(false);
            viewCardButton.setEnabled(false);
            cardPanel.setStatus("Server disconnected. Some functions unavailable.");
            JOptionPane.showMessageDialog(mainFrame,
                "ETOUR server connection interrupted!\n" +
                "This simulates the exception condition from the use case.\n" +
                "New searches and data retrieval will fail until connection is restored.",
                "Server Disconnection Simulated",
                JOptionPane.WARNING_MESSAGE);
        });
        simPanel.add(simDisconnectButton);
        // Add reconnect button
        JButton reconnectButton = new JButton("Reconnect to Server");
        reconnectButton.addActionListener(e -> {
            if (server.reconnect()) {
                updateConnectionStatus();
                JOptionPane.showMessageDialog(mainFrame,
                    "Successfully reconnected to ETOUR server!",
                    "Reconnection Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(mainFrame,
                    "Failed to reconnect. Please try again later.",
                    "Reconnection Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        simPanel.add(reconnectButton);
        // Add components to control panel
        controlPanel.add(step1Panel);
        controlPanel.add(step2Panel);
        controlPanel.add(step3Panel);
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(simPanel);
        // Assemble main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(controlPanel, BorderLayout.NORTH);
        bottomPanel.add(infoScroll, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        // Replace current panel
        mainFrame.remove(currentPanel);
        currentPanel = mainPanel;
        mainFrame.add(currentPanel, BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    /**
     * Updates the connection status display.
     */
    private void updateConnectionStatus() {
        String status;
        Color color;
        if (!agencyLoggedIn) {
            status = "Not logged in | ";
        } else {
            status = "Agency Operator logged in | ";
        }
        if (server.isConnected()) {
            status += "ETOUR server: Connected ✓";
            color = new Color(0, 150, 0); // Green
        } else {
            status += "ETOUR server: Disconnected ✗";
            color = Color.RED;
        }
        connectionStatusLabel.setText(status);
        connectionStatusLabel.setForeground(color);
    }
}