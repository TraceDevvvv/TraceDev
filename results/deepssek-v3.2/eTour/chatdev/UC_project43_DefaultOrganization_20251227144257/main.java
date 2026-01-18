"""
Main entry point for the Refreshment Point Management System
Launches the application starting with login screen
This is a self-contained, runnable Java program that includes all necessary classes in a single file
Compile and run with: javac Main.java && java Main
"""
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI operations
        SwingUtilities.invokeLater(() -> {
            // Create and display the login frame
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setVisible(true);
        });
    }
}
/**
 * Login frame for operator authentication
 * Handles the entry condition: operator must successfully authenticate to the system
 */
class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    public LoginFrame() {
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Refreshment Point Management - Login");
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Create header label
        JLabel headerLabel = new JLabel("Operator Authentication", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        // Add empty cells for alignment
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateOperator();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit application when cancelled
            }
        });
        // Add main panel to frame
        add(mainPanel);
    }
    /**
     * Authenticates the operator as per entry condition
     * Validates credentials and proceeds to main application if successful
     */
    private void authenticateOperator() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        // Simple authentication logic
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password",
                "Authentication Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        // In a real application, this would validate against a database
        // For demonstration, accept any non-empty credentials
        if (!username.isEmpty() && !password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Authentication successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            // Close login frame and open main application
            this.dispose();
            openModifyRefreshmentPointFrame();
        }
    }
    /**
     * Opens the main frame for modifying refreshment point data
     */
    private void openModifyRefreshmentPointFrame() {
        ModifyRefreshmentPointFrame mainFrame = new ModifyRefreshmentPointFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
/**
 * Main frame for modifying refreshment point data
 * Implements the flow of events from the use case:
 * 1. Enable functionality
 * 2. Upload and display data
 * 3. Change data in form and submit
 * 4. Verify data and ask for confirmation
 * 5. Confirm operation
 * 6. Store modified data
 */
class ModifyRefreshmentPointFrame extends JFrame {
    private JTextField nameField;
    private JTextField locationField;
    private JTextField capacityField;
    private JTextArea refreshmentsArea;
    private JButton loadButton;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton verifyButton;
    private DataService dataService;
    private ValidationService validationService;
    private RefreshmentPoint currentPoint;
    public ModifyRefreshmentPointFrame() {
        dataService = new DataService();
        validationService = new ValidationService();
        // Initialize with empty point to prevent null pointer exceptions
        currentPoint = new RefreshmentPoint(0, "", "", 0, new String[0]);
        initializeUI();
        loadInitialData(); // Step 1: Enable functionality
    }
    private void initializeUI() {
        setTitle("Modify Refreshment Point - Restaurant Point Operator");
        setSize(800, 600);
        setLocationRelativeTo(null);
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Create header
        JLabel headerLabel = new JLabel("Modify Refreshment Point Data", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        // Create form panel
        mainPanel.add(createFormPanel(), BorderLayout.CENTER);
        // Create button panel
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Creates the form panel with all input fields for refreshment point data
     */
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Refreshment Point Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        // Row 0: Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Point Name:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        nameField = new JTextField(30);
        formPanel.add(nameField, gbc);
        // Row 1: Location
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        locationField = new JTextField(30);
        formPanel.add(locationField, gbc);
        // Row 2: Capacity
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Capacity:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        capacityField = new JTextField(10);
        formPanel.add(capacityField, gbc);
        // Row 3: Refreshments label
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        formPanel.add(new JLabel("Available Refreshments (one per line):"), gbc);
        // Row 4: Refreshments text area
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        refreshmentsArea = new JTextArea(10, 40);
        refreshmentsArea.setLineWrap(true);
        refreshmentsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(refreshmentsArea);
        formPanel.add(scrollPane, gbc);
        return formPanel;
    }
    /**
     * Creates the button panel with action buttons for the use case flow
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        loadButton = new JButton("Load Data");
        verifyButton = new JButton("Verify Changes");
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");
        // Disable save button initially (needs verification first)
        saveButton.setEnabled(false);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRefreshmentPointData(); // Step 2: Upload and display data
            }
        });
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyDataChanges(); // Step 4: Verify entered data
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmAndSaveChanges(); // Step 5 & 6: Confirm and store modified data
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelOperation(); // Exit condition: Operator cancels operation
            }
        });
        buttonPanel.add(loadButton);
        buttonPanel.add(verifyButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }
    /**
     * Step 1: Enable functionality by loading initial data
     * Loads existing refreshment point data into the form for editing
     */
    private void loadInitialData() {
        // Simulate loading available refreshment points
        // In a real application, this would fetch from a database
        currentPoint = dataService.getCurrentRefreshmentPoint();
        if (currentPoint != null) {
            nameField.setText(currentPoint.getName());
            locationField.setText(currentPoint.getLocation());
            capacityField.setText(String.valueOf(currentPoint.getCapacity()));
            refreshmentsArea.setText(String.join("\n", currentPoint.getRefreshments()));
        }
    }
    /**
     * Step 2: Upload data point refreshments and display them in a form
     * Simulates loading data from server with connection checking
     */
    private void loadRefreshmentPointData() {
        try {
            // Simulate server connection
            if (!dataService.isConnected()) {
                throw new Exception("Connection to server ETOUR interrupted");
            }
            currentPoint = dataService.getRefreshmentPointData();
            if (currentPoint != null) {
                nameField.setText(currentPoint.getName());
                locationField.setText(currentPoint.getLocation());
                capacityField.setText(String.valueOf(currentPoint.getCapacity()));
                refreshmentsArea.setText(String.join("\n", currentPoint.getRefreshments()));
                JOptionPane.showMessageDialog(this,
                    "Refreshment point data loaded successfully.\n" +
                    "You can now modify the data in the form.",
                    "Data Loaded",
                    JOptionPane.INFORMATION_MESSAGE);
                // Enable verification button now that data is loaded
                verifyButton.setEnabled(true);
            }
        } catch (Exception e) {
            // Handle server connection interruption (exit condition)
            handleError("Failed to load data: " + e.getMessage());
        }
    }
    /**
     * Step 3 & 4: Change data in the form and submit, then verify data entered
     * Validates the data and asks for confirmation of the change
     * Activates Errored use case for invalid or insufficient data
     */
    private void verifyDataChanges() {
        try {
            // Collect data from form (Step 3: User changes data)
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();
            String capacityStr = capacityField.getText().trim();
            String refreshmentsText = refreshmentsArea.getText().trim();
            // Split refreshments by newline
            String[] refreshmentsArray = refreshmentsText.isEmpty() ?
                new String[0] : refreshmentsText.split("\n");
            // Validate data (Step 4)
            String validationResult = validationService.validateRefreshmentPointData(
                name, location, capacityStr, refreshmentsArray);
            if (!validationResult.isEmpty()) {
                // Invalid data - activate Errored use case
                handleError(validationResult);
                return;
            }
            int capacity = Integer.parseInt(capacityStr);
            // Create updated refreshment point
            RefreshmentPoint updatedPoint = new RefreshmentPoint(
                currentPoint != null ? currentPoint.getId() : 1,
                name,
                location,
                capacity,
                refreshmentsArray
            );
            // Ask for confirmation of the change (Step 4)
            int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to save these changes?\n\n" +
                "Name: " + name + "\n" +
                "Location: " + location + "\n" +
                "Capacity: " + capacity + "\n" +
                "Number of refreshments: " + refreshmentsArray.length,
                "Confirm Changes",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (confirmation == JOptionPane.YES_OPTION) {
                // Enable save button for final confirmation
                saveButton.setEnabled(true);
                currentPoint = updatedPoint;
                JOptionPane.showMessageDialog(this,
                    "Data verified successfully. Click 'Save Changes' to finalize.",
                    "Verification Complete",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            handleError("Invalid capacity value. Please enter a valid number.");
        } catch (Exception e) {
            handleError("Error during verification: " + e.getMessage());
        }
    }
    /**
     * Step 5 & 6: Confirm the operation and store the modified data
     * Performs final confirmation and saves the changes to persistent storage
     */
    private void confirmAndSaveChanges() {
        // Check if currentPoint is null to prevent NullPointerException
        if (currentPoint == null) {
            JOptionPane.showMessageDialog(this,
                "No data to save. Please load and verify data first.",
                "No Data",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // Final confirmation (Step 5)
            int finalConfirmation = JOptionPane.showConfirmDialog(this,
                "Final confirmation: Save changes to the refreshment point?",
                "Final Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            if (finalConfirmation != JOptionPane.YES_OPTION) {
                return;
            }
            // Save data (Step 6)
            boolean success = dataService.saveRefreshmentPoint(currentPoint);
            if (success) {
                // Exit condition: The notification system has been changing the data point
                JOptionPane.showMessageDialog(this,
                    "Refreshment point data modified successfully!\n" +
                    "The system has been notified of the changes.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                // Reset form
                saveButton.setEnabled(false);
                // Re-enable verify button for further edits
                verifyButton.setEnabled(true);
            } else {
                handleError("Failed to save changes. Please try again.");
            }
        } catch (Exception e) {
            handleError("Error saving data: " + e.getMessage());
        }
    }
    /**
     * Exit condition: Restaurant Point Of Operator cancels the operation
     */
    private void cancelOperation() {
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel?\nAny unsaved changes will be lost.",
            "Cancel Operation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            this.dispose();
            System.exit(0);
        }
    }
    /**
     * Handles errors according to Errored use case
     * Disables save button and displays error message to user
     */
    private void handleError(String errorMessage) {
        JOptionPane.showMessageDialog(this,
            "Error: " + errorMessage + "\n\nPlease correct the data and try again.",
            "Data Error",
            JOptionPane.ERROR_MESSAGE);
        // Disable save button on error
        saveButton.setEnabled(false);
    }
}
/**
 * Model class representing a refreshment point
 * Contains all data associated with a refreshment point including ID, name,
 * location, capacity, and available refreshments
 */
class RefreshmentPoint {
    private int id;
    private String name;
    private String location;
    private int capacity;
    private String[] refreshments;
    /**
     * Constructor for creating a refreshment point
     */
    public RefreshmentPoint(int id, String name, String location, int capacity, String[] refreshments) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.refreshments = refreshments != null ? refreshments : new String[0];
    }
    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public String[] getRefreshments() {
        return refreshments;
    }
    public List<String> getRefreshmentsList() {
        return Arrays.asList(refreshments);
    }
    public void setRefreshments(String[] refreshments) {
        this.refreshments = refreshments != null ? refreshments : new String[0];
    }
    @Override
    public String toString() {
        return "RefreshmentPoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", refreshments=" + Arrays.toString(refreshments) +
                '}';
    }
}
/**
 * Service class for data operations
 * Simulates server connection and data persistence including handling of
 * server ETOUR connection interruptions as per exit conditions
 */
class DataService {
    private boolean connected = true;
    private RefreshmentPoint currentPoint;
    public DataService() {
        // Initialize with sample data
        initializeSampleData();
    }
    private void initializeSampleData() {
        // Create sample refreshment point
        String[] sampleRefreshments = {
            "Coffee",
            "Tea",
            "Sandwiches",
            "Pastries",
            "Soft Drinks",
            "Water"
        };
        currentPoint = new RefreshmentPoint(
            1,
            "Main Cafeteria",
            "Building A, Floor 1",
            50,
            sampleRefreshments
        );
    }
    /**
     * Gets the current refreshment point data
     * Simulates fetching from database
     */
    public RefreshmentPoint getCurrentRefreshmentPoint() {
        // In real application, this would fetch from database
        return currentPoint;
    }
    /**
     * Gets refreshment point data from server
     * Simulates uploading data with network delay
     * @throws RuntimeException if connection to server ETOUR is interrupted
     */
    public RefreshmentPoint getRefreshmentPointData() {
        // Check server connection
        if (!isConnected()) {
            throw new RuntimeException("Connection to server ETOUR interrupted");
        }
        // Simulate network delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return currentPoint;
    }
    /**
     * Saves the modified refreshment point data
     * Simulates storing to database with network delay
     * @throws RuntimeException if connection to server ETOUR is interrupted
     */
    public boolean saveRefreshmentPoint(RefreshmentPoint point) {
        // Check server connection
        if (!isConnected()) {
            throw new RuntimeException("Connection to server ETOUR interrupted");
        }
        // Validate point is not null
        if (point == null) {
            return false;
        }
        // Simulate network delay and save operation
        try {
            Thread.sleep(1000);
            // In real application, this would save to database
            currentPoint = point;
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    /**
     * Checks if connected to server ETOUR
     * Simulates occasional connection issues (10% chance of failure)
     */
    public boolean isConnected() {
        // Simulate occasional connection issues
        if (Math.random() < 0.1) { // 10% chance of connection failure
            connected = false;
        }
        return connected;
    }
    /**
     * Simulate reconnection to server ETOUR
     */
    public void reconnect() {
        connected = true;
    }
}
/**
 * Service class for data validation
 * Implements validation logic for refreshment point data according to business rules
 * Returns error messages for invalid or insufficient data as per Errored use case
 */
class ValidationService {
    /**
     * Validates refreshment point data according to business rules
     * Returns empty string if valid, error message if invalid
     * Covers all validation requirements: name, location, capacity, refreshments
     */
    public String validateRefreshmentPointData(String name, String location,
                                                String capacityStr, String[] refreshments) {
        StringBuilder errors = new StringBuilder();
        // Validate name
        if (name == null || name.trim().isEmpty()) {
            errors.append("Point name is required.\n");
        } else if (name.length() > 100) {
            errors.append("Point name cannot exceed 100 characters.\n");
        }
        // Validate location
        if (location == null || location.trim().isEmpty()) {
            errors.append("Location is required.\n");
        } else if (location.length() > 200) {
            errors.append("Location cannot exceed 200 characters.\n");
        }
        // Validate capacity
        if (capacityStr == null || capacityStr.trim().isEmpty()) {
            errors.append("Capacity is required.\n");
        } else {
            try {
                int capacity = Integer.parseInt(capacityStr);
                if (capacity <= 0) {
                    errors.append("Capacity must be a positive number.\n");
                } else if (capacity > 1000) {
                    errors.append("Capacity cannot exceed 1000.\n");
                }
            } catch (NumberFormatException e) {
                errors.append("Capacity must be a valid integer number.\n");
            }
        }
        // Validate refreshments
        if (refreshments == null || refreshments.length == 0) {
            errors.append("At least one refreshment is required.\n");
        } else {
            for (int i = 0; i < refreshments.length; i++) {
                String item = refreshments[i].trim();
                if (item.isEmpty()) {
                    errors.append("Refreshment item ").append(i + 1).append(" cannot be empty.\n");
                } else if (item.length() > 50) {
                    errors.append("Refreshment '").append(item)
                          .append("' exceeds 50 characters.\n");
                }
            }
        }
        return errors.toString();
    }
    /**
     * Validates if a string contains only alphanumeric characters and spaces
     * Utility method for additional validation if needed
     */
    private boolean isValidAlphanumeric(String str) {
        if (str == null) return false;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
        return pattern.matcher(str).matches();
    }
}