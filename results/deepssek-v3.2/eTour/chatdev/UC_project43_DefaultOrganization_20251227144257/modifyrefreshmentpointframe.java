"""
Main frame for modifying refreshment point data
Implements the flow of events from the use case
"""
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ModifyRefreshmentPointFrame extends JFrame {
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
        // Disable verify button initially until data is loaded
        verifyButton.setEnabled(false);
        saveButton.setEnabled(false);
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
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        loadButton = new JButton("Load Data");
        verifyButton = new JButton("Verify Changes");
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");
        // Disable save and verify buttons initially
        verifyButton.setEnabled(false);
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
            // Enable verification button now that data is loaded
            verifyButton.setEnabled(true);
        }
    }
    /**
     * Step 2: Upload data point refreshments and display in form
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
                // Enable verification button
                verifyButton.setEnabled(true);
                // Disable save button until verification
                saveButton.setEnabled(false);
            }
        } catch (Exception e) {
            // Handle server connection interruption
            handleError("Failed to load data: " + e.getMessage());
        }
    }
    /**
     * Step 3: Changes are made directly in the form fields
     * Step 4: Verify data entered and ask for confirmation
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
            // Validate data
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
            // Ask for confirmation (Step 4)
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
     * Step 5: Confirm operation and Step 6: Store modified data
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
                // Exit condition: Notification system has been changing the data
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
     * Exit condition: Operator cancels operation
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