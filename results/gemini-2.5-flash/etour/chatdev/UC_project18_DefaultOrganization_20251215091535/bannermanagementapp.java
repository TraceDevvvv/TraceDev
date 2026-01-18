/*
 * Main application class responsible for setting up the Graphical User Interface (GUI)
 * and orchestrating the banner limit check process.
 * It extends JFrame to provide a window-based application.
 */
package com.chatdev.bannerapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap; // Required for mock data storage
import java.util.Map;     // Required for mock data storage
public class BannerManagementApp extends JFrame {
    // GUI components for user interaction and displaying results
    private JTextField txtPointName;
    private JTextField txtMaxBanners;
    private JTextField txtCurrentBanners;
    private JLabel lblStatus;
    private JButton btnCheck;
    // Service class to handle the business logic of checking banner limits
    private BannerCheckService bannerCheckService;
    // In-memory data store to simulate loading refreshment point data (as per use case step 1)
    private Map<String, RefreshmentPointData> mockDataSource;
    /*
     * Constructor for the BannerManagementApp.
     * Initializes the JFrame properties, creates GUI components,
     * instantiates the BannerCheckService, and populates mock data.
     */
    public BannerManagementApp() {
        super("Banner Management System - Check Limit"); // Set the title of the application window
        bannerCheckService = new BannerCheckService(); // Initialize the business logic service
        // As per the project requirements and use case description, this application
        // relies on local mock data for refreshment points.
        // Therefore, the "Interruption of the connection to the server ETOUR" exit condition
        // specified in the use case is not implemented or simulated, as no external
        // server interaction is performed by this version of the application.
        // The detailed "Flow of events" in the use case does not describe any specific
        // server interaction for verifying the banner limit, only "Load the data".
        mockDataSource = new HashMap<>();
        mockDataSource.put("Convention Center Main Hall", new RefreshmentPointData("Convention Center Main Hall", 5, 3));
        mockDataSource.put("Hotel Lobby A", new RefreshmentPointData("Hotel Lobby A", 2, 2));
        mockDataSource.put("Exhibition Hall B", new RefreshmentPointData("Exhibition Hall B", 10, 7));
        mockDataSource.put("Food Court", new RefreshmentPointData("Food Court", 3, 1)); // Another example
        createUI(); // Call method to build the graphical user interface and initialize components
        // After UI creation, pre-fill fields for the default refreshment point.
        // This directly addresses Use Case Step 1: "Load the data of the Convention of refreshment point".
        // The default point name is passed directly, as the JTextField might not yet have its default text
        // fully set when read during the constructor's initial phase.
        loadInitialPointData("Convention Center Main Hall");
        // Configure JFrame properties for standard window behavior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
        pack(); // Adjust window size to fit all components
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible to the user
    }
    /*
     * Private helper method to create and arrange all GUI components within the frame.
     * It uses BorderLayout for overall layout and GridLayout for input fields.
     */
    private void createUI() {
        // Panel for input fields, arranged in a grid for neatness
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns, with 10px gaps
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        // Refreshment Point Name input field and label
        inputPanel.add(new JLabel("Refreshment Point Name:"));
        // Set a default such that it matches a pre-loaded mock data entry.
        // This value will be used by loadInitialPointData to populate other fields on startup.
        txtPointName = new JTextField("Convention Center Main Hall");
        inputPanel.add(txtPointName);
        // Maximum Banners Allowed input field and label
        inputPanel.add(new JLabel("Max Banners Allowed:"));
        // These will be overridden by loadInitialPointData if a matching point is found,
        // but provide initial values for display or if no data is found.
        txtMaxBanners = new JTextField("0");
        inputPanel.add(txtMaxBanners);
        // Current Banners input field and label
        inputPanel.add(new JLabel("Current Banners:"));
        // These will be overridden by loadInitialPointData if a matching point is found,
        // but provide initial values for display or if no data is found.
        txtCurrentBanners = new JTextField("0");
        inputPanel.add(txtCurrentBanners);
        // Panel for the check button, using FlowLayout to center it
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCheck = new JButton("Check if New Banner Can Be Added");
        // Attach an ActionListener to the button to perform the check when clicked
        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBannerAction(); // Delegate to a separate method for handling the check logic
            }
        });
        buttonPanel.add(btnCheck);
        // Status label to display the result or error messages
        lblStatus = new JLabel("Enter details and click 'Check' to verify banner limit.");
        lblStatus.setFont(new Font("Serif", Font.BOLD, 14)); // Make status text prominent
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally
        // Panel to hold the status label, providing padding
        JPanel statusWrapperPanel = new JPanel(new BorderLayout());
        statusWrapperPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Padding
        statusWrapperPanel.add(lblStatus, BorderLayout.CENTER);
        // Add the created panels to the main JFrame using BorderLayout
        add(inputPanel, BorderLayout.NORTH); // Input fields at the top
        add(buttonPanel, BorderLayout.CENTER); // Button in the middle
        add(statusWrapperPanel, BorderLayout.SOUTH); // Status at the bottom
    }
    /*
     * Loads the initial data for the specified refreshment point from the mock data source
     * and pre-fills the corresponding text fields in the GUI.
     * This addresses the "Load the data of the Convention of refreshment point" part of Use Case Step 1.
     * This method is called once on application startup for the default point name.
     * Users can still manually modify the pre-filled values for "what-if" scenarios.
     *
     * @param pointName The name of the refreshment point to load data for.
     */
    private void loadInitialPointData(String pointName) {
        RefreshmentPointData data = mockDataSource.get(pointName.trim());
        if (data != null) {
            // If data is found for the default point name, update the text fields with the loaded values.
            txtMaxBanners.setText(String.valueOf(data.getMaxBanners()));
            txtCurrentBanners.setText(String.valueOf(data.getCurrentBanners()));
            lblStatus.setText("Data for '" + pointName + "' loaded. You can modify values before checking.");
            lblStatus.setForeground(Color.BLUE); // Informational status
        } else {
            // This case should ideally not happen if the default txtPointName matches mock data.
            // If it does, inform the user that no pre-existing data was found for the default.
            lblStatus.setText("ERR: Default point '" + pointName + "' not found in mock data. Enter values manually.");
            lblStatus.setForeground(Color.RED); // Error status
        }
    }
    /*
     * This method is triggered when the "Check" button is clicked.
     * It performs the following steps as per the use case:
     * 1. Parses user input from text fields.
     * 2. Validates the input for correctness (numeric, non-negative, and logical consistency).
     * 3. Creates a RefreshmentPointData object based on the *current* values in the text fields.
     *    This allows for "what-if" scenarios even if data was initially loaded and then user-modified.
     * 4. Calls the BannerCheckService to verify the banner limit. This corresponds to
     *    Use Case Step 1: "...verify that the number of banners is less than the specified number...
     *    If not checked, will end the operation input and displays a notification."
     * 5. Updates the GUI's status label based on the service's result.
     * 6. Handles use case Exit conditions (returns control after check).
     */
    private void checkBannerAction() {
        String pointName = txtPointName.getText().trim();
        int maxBanners = 0;
        int currentBanners = 0;
        // Input validation: Ensure Refreshment Point Name is not empty.
        if (pointName.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Refreshment Point Name cannot be empty. Please enter a name.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            lblStatus.setText("Error: Refreshment Point Name is empty.");
            lblStatus.setForeground(Color.RED);
            return; // Stop processing
        }
        // Input validation: Parse numeric fields and handle NumberFormatException if input is not a valid integer.
        try {
            maxBanners = Integer.parseInt(txtMaxBanners.getText().trim());
            currentBanners = Integer.parseInt(txtCurrentBanners.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input: Please enter valid whole numbers for 'Max Banners Allowed' and 'Current Banners'.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            lblStatus.setText("Error: Invalid numeric input.");
            lblStatus.setForeground(Color.RED);
            return; // Stop processing
        }
        // Input validation: Ensure banner counts are not negative.
        if (maxBanners < 0 || currentBanners < 0) {
            JOptionPane.showMessageDialog(this,
                    "Banner counts cannot be negative. Please enter non-negative numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            lblStatus.setText("Error: Banner counts cannot be negative.");
            lblStatus.setForeground(Color.RED);
            return; // Stop processing
        }
        // Input validation: Ensure current banners do not initially exceed max banners.
        // This is a logical consistency check before attempting to perform a check to add a new one.
        if (currentBanners > maxBanners) {
            JOptionPane.showMessageDialog(this,
                    "Current banners cannot be greater than 'Max Banners Allowed'. Please correct the input.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            lblStatus.setText("Error: Current banners exceed max allowed on input.");
            lblStatus.setForeground(Color.RED);
            return; // Stop processing
        }
        // Create a RefreshmentPointData object with validated user input.
        RefreshmentPointData point = null;
        try {
            point = new RefreshmentPointData(pointName, maxBanners, currentBanners);
        } catch (IllegalArgumentException e) {
            // This catch block acts as a fallback for any unexpected data validation issues in the model.
            JOptionPane.showMessageDialog(this, "Internal Data Error: " + e.getMessage(), "Application Error", JOptionPane.ERROR_MESSAGE);
            lblStatus.setText("Error: Problem creating refreshment point data.");
            lblStatus.setForeground(Color.RED);
            return;
        }
        // Use the BannerCheckService to perform the business logic verification.
        // This calls the method that implements Use Case Step 1 and 2 (notification).
        boolean canAdd = bannerCheckService.verifyBannerLimit(point);
        // Update the GUI's status label based on the verification result.
        if (canAdd) {
            lblStatus.setText(String.format("Result: A new banner CAN be added to '%s'.", pointName));
            lblStatus.setForeground(new Color(0, 102, 0)); // Dark green for success
        } else {
            lblStatus.setText(String.format("Result: A new banner CANNOT be added to '%s'. Limit reached!", pointName));
            lblStatus.setForeground(Color.RED); // Red for failure
             // Use case 3: "Recovers the previous state." - Implicitly handled. Since this operation is only a check,
             // no actual modification to the system state (e.g., adding a banner) occurs if the limit is reached.
             // The system simply reports the inability to add and waits for further user interaction.
        }
        // Use case Exit condition: "The system returns control to the user interaction." - The GUI remains active and responsive.
    }
    /*
     * Main method - the entry point of the application.
     * It ensures that the GUI creation and update operations are performed
     * on the Event Dispatch Thread (EDT) for thread safety in Swing applications.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BannerManagementApp(); // Instantiate and display the main application window
            }
        });
    }
}