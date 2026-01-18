'''
SearchTourist Application - Main Entry Point
A complete, runnable Java application for searching tourist accounts with GUI interface.
Handles ETOUR server connection interruptions as specified in use case requirements.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Main application entry point for SearchTourist system
 */
public class SearchTouristApp {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread-safe GUI operations
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    /**
     * Creates and displays the main application window
     */
    private static void createAndShowGUI() {
        try {
            // Create connection manager and service
            ETOURConnectionManager connectionManager = new ETOURConnectionManager();
            TouristService touristService = new TouristService(connectionManager);
            // Create and display the main window
            SearchTouristFrame frame = new SearchTouristFrame(touristService);
            frame.setVisible(true);
        } catch (Exception e) {
            System.err.println("Failed to initialize application: " + e.getMessage());
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Application initialization failed. Please contact support.",
                "Initialization Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
/**
 * Represents search criteria for tourist search functionality
 * Used in step 3: Fill out the form
 */
class SearchCriteria {
    private String name;
    private String email;
    private String touristId;
    public SearchCriteria() {
        // Empty constructor for flexibility
    }
    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTouristId() {
        return touristId;
    }
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
    /**
     * Checks if all search criteria are empty
     * @return true if all fields are null or empty
     */
    public boolean isEmpty() {
        return (name == null || name.trim().isEmpty()) &&
               (email == null || email.trim().isEmpty()) &&
               (touristId == null || touristId.trim().isEmpty());
    }
}
/**
 * Represents a tourist account in the system
 * Implements the exit condition: returns accounts that meet search criteria
 */
class Tourist {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String nationality;
    public Tourist(String id, String name, String email, String phone, String nationality) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nationality = nationality;
    }
    // Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getNationality() {
        return nationality;
    }
    // For debugging and logging
    @Override
    public String toString() {
        return "Tourist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
/**
 * Custom exception for ETOUR connection interruptions
 * As specified in the use case quality requirements
 */
class ConnectionInterruptedException extends Exception {
    public ConnectionInterruptedException(String message) {
        super(message);
    }
    public ConnectionInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}
/**
 * Manages connection to the ETOUR server
 * Handles potential connection interruptions as specified in quality requirements
 */
class ETOURConnectionManager {
    private boolean connected = false;
    public ETOURConnectionManager() {
        // In a real application, this would establish a connection
        // For this example, we simulate connection
        simulateConnection();
    }
    /**
     * Simulates connection to ETOUR server
     */
    private void simulateConnection() {
        try {
            // Simulate connection delay
            Thread.sleep(100);
            connected = true;
        } catch (InterruptedException e) {
            connected = false;
            throw new RuntimeException("Connection to ETOUR server failed", e);
        }
    }
    /**
     * Checks connection status
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }
    /**
     * Performs a query to the ETOUR server
     * @param query The query to execute
     * @return Query results
     * @throws ConnectionInterruptedException if connection is lost
     */
    public String executeQuery(String query) throws ConnectionInterruptedException {
        if (!connected) {
            throw new ConnectionInterruptedException("Not connected to ETOUR server");
        }
        // In a real application, this would execute the query
        // For this example, we simulate potential connection interruption
        double random = Math.random();
        if (random < 0.1) { // 10% chance of connection interruption for demonstration
            connected = false;
            throw new ConnectionInterruptedException("Connection to ETOUR server was interrupted during query execution");
        }
        // Simulate query execution delay
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionInterruptedException("Query execution was interrupted", e);
        }
        return "Query executed successfully: " + query;
    }
    /**
     * Reconnects to the ETOUR server
     */
    public void reconnect() throws ConnectionInterruptedException {
        try {
            Thread.sleep(200); // Simulate reconnection delay
            connected = true;
        } catch (InterruptedException e) {
            throw new ConnectionInterruptedException("Reconnection attempt failed", e);
        }
    }
}
/**
 * Service layer for tourist operations
 * Implements the business logic for searching tourists
 */
class TouristService {
    private ETOURConnectionManager connectionManager;
    public TouristService(ETOURConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    /**
     * Searches for tourists based on criteria
     * @param criteria Search criteria from the form
     * @return List of tourists matching the criteria
     * @throws ConnectionInterruptedException if connection to ETOUR is lost
     */
    public List<Tourist> searchTourists(SearchCriteria criteria) throws ConnectionInterruptedException {
        // Validate connection
        if (!connectionManager.isConnected()) {
            throw new ConnectionInterruptedException("Not connected to ETOUR server. Please reconnect.");
        }
        List<Tourist> tourists = new ArrayList<>();
        try {
            // Build query based on criteria
            String query = buildQuery(criteria);
            // Execute query through connection manager
            String result = connectionManager.executeQuery(query);
            System.out.println("Query executed: " + result);
            // In a real application, this would parse the result from ETOUR server
            // For demonstration, we create mock data
            tourists = getMockTourists(criteria);
        } catch (ConnectionInterruptedException e) {
            // Log the interruption
            System.err.println("Connection interrupted during search: " + e.getMessage());
            // Attempt to reconnect
            try {
                connectionManager.reconnect();
                throw new ConnectionInterruptedException("Search failed due to connection interruption. Please try again.");
            } catch (ConnectionInterruptedException re) {
                throw new ConnectionInterruptedException("Search failed and reconnection failed: " + re.getMessage());
            }
        }
        return tourists;
    }
    /**
     * Builds SQL-like query from search criteria
     */
    private String buildQuery(SearchCriteria criteria) {
        StringBuilder query = new StringBuilder("SELECT * FROM tourists WHERE 1=1");
        if (criteria.getName() != null && !criteria.getName().trim().isEmpty()) {
            query.append(" AND name LIKE '%").append(criteria.getName()).append("%'");
        }
        if (criteria.getEmail() != null && !criteria.getEmail().trim().isEmpty()) {
            query.append(" AND email = '").append(criteria.getEmail()).append("'");
        }
        if (criteria.getTouristId() != null && !criteria.getTouristId().trim().isEmpty()) {
            query.append(" AND id = '").append(criteria.getTouristId()).append("'");
        }
        return query.toString();
    }
    /**
     * Provides mock tourist data for demonstration
     * In a real application, this would retrieve data from the ETOUR database
     */
    private List<Tourist> getMockTourists(SearchCriteria criteria) {
        List<Tourist> mockTourists = new ArrayList<>();
        // Create some sample tourists
        mockTourists.add(new Tourist("T001", "John Smith", "john.smith@example.com", "+1-555-0101", "USA"));
        mockTourists.add(new Tourist("T002", "Maria Garcia", "maria.garcia@example.com", "+34-555-0202", "Spain"));
        mockTourists.add(new Tourist("T003", "Chen Wei", "chen.wei@example.com", "+86-555-0303", "China"));
        mockTourists.add(new Tourist("T004", "Anna Schmidt", "anna.schmidt@example.com", "+49-555-0404", "Germany"));
        mockTourists.add(new Tourist("T005", "David Brown", "david.brown@example.com", "+44-555-0505", "UK"));
        // Filter based on criteria if provided
        if (!criteria.isEmpty()) {
            List<Tourist> filtered = new ArrayList<>();
            for (Tourist tourist : mockTourists) {
                if (matchesCriteria(tourist, criteria)) {
                    filtered.add(tourist);
                }
            }
            return filtered;
        }
        return mockTourists;
    }
    /**
     * Checks if a tourist matches the search criteria
     */
    private boolean matchesCriteria(Tourist tourist, SearchCriteria criteria) {
        boolean matches = true;
        if (criteria.getName() != null && !criteria.getName().trim().isEmpty()) {
            matches &= tourist.getName().toLowerCase().contains(criteria.getName().toLowerCase());
        }
        if (criteria.getEmail() != null && !criteria.getEmail().trim().isEmpty()) {
            matches &= tourist.getEmail().equalsIgnoreCase(criteria.getEmail());
        }
        if (criteria.getTouristId() != null && !criteria.getTouristId().trim().isEmpty()) {
            matches &= tourist.getId().equalsIgnoreCase(criteria.getTouristId());
        }
        return matches;
    }
}
/**
 * Main GUI frame for search tourist functionality
 * Implements steps 1-3 from the flow of events:
 * 1. Access the search functionality
 * 2. Show the form
 * 3. Fill out the form and submit
 */
class SearchTouristFrame extends JFrame {
    private TouristService touristService;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField idField;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    public SearchTouristFrame(TouristService touristService) {
        this.touristService = touristService;
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Search Tourist System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        // Create search panel (form)
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);
        // Create results panel
        JPanel resultsPanel = createResultsPanel();
        add(resultsPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center on screen
    }
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Search Criteria"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);
        // Email field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);
        // Tourist ID field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Tourist ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(20);
        panel.add(idField, gbc);
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton searchButton = new JButton("Search");
        JButton clearButton = new JButton("Clear");
        // Search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        // Clear button action
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
                clearResults();
            }
        });
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(buttonPanel, gbc);
        return panel;
    }
    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Search Results"));
        // Create table model with column names
        String[] columnNames = {"Tourist ID", "Name", "Email", "Phone", "Nationality"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        resultTable = new JTable(tableModel);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    /**
     * Step 4: Process the search request
     */
    private void performSearch() {
        try {
            // Create search criteria from form
            SearchCriteria criteria = new SearchCriteria();
            criteria.setName(nameField.getText().trim());
            criteria.setEmail(emailField.getText().trim());
            criteria.setTouristId(idField.getText().trim());
            // Call service to search tourists
            List<Tourist> tourists = touristService.searchTourists(criteria);
            // Clear existing results
            clearResults();
            // Populate results in table
            for (Tourist tourist : tourists) {
                Object[] row = {
                    tourist.getId(),
                    tourist.getName(),
                    tourist.getEmail(),
                    tourist.getPhone(),
                    tourist.getNationality()
                };
                tableModel.addRow(row);
            }
            // Show message if no results found
            if (tourists.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "No tourists found matching the search criteria.",
                    "No Results",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ConnectionInterruptedException e) {
            JOptionPane.showMessageDialog(this,
                "Connection to ETOUR server was interrupted: " + e.getMessage(),
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "An error occurred during search: " + e.getMessage(),
                "Search Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        idField.setText("");
    }
    private void clearResults() {
        tableModel.setRowCount(0);
    }
}