'''
ViewClassDetailsGUI.java
This class creates the graphical user interface for viewing class details.
It extends JFrame and uses Swing components to allow selection of a class
and then display its name, address, and school year.
It interacts with ClassService to fetch the data based on the selected class.
'''
package com.chatdev.smos.gui;
import com.chatdev.smos.model.ClassDetails;
import com.chatdev.smos.service.ClassService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;
import java.util.Vector; // Used for JComboBox model initialization
public class ViewClassDetailsGUI extends JFrame {
    // Service to fetch class data
    private ClassService classService;
    // GUI components for selecting a class and displaying details
    private JComboBox<String> classSelectorComboBox; // Dropdown for class selection
    private JLabel nameLabelTitle;
    private JLabel nameValueLabel;
    private JLabel addressLabelTitle;
    private JLabel addressValueLabel;
    private JLabel schoolYearLabelTitle;
    private JLabel schoolYearValueLabel;
    private JButton showDetailsButton;
    /**
     * Constructs the ViewClassDetailsGUI.
     * Initializes the ClassService and sets up all graphical user interface components,
     * including a dropdown for class selection and display labels for class details,
     * as well as the action button.
     */
    public ViewClassDetailsGUI() {
        super("Class Details Viewer"); // Set window title
        // Initialize the service layer
        classService = new ClassService();
        // Configure the main window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(450, 350); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false); // Prevent resizing
        // Set up the main panel using GridBagLayout for flexible component placement
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add interior padding
        GridBagConstraints gbc = new GridBagConstraints(); // Constraints for GridBagLayout
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around individual components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components fill their display area horizontally
        // --- Class Selector Component ---
        JLabel selectClassLabel = new JLabel("Select Class:");
        selectClassLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; // First column
        gbc.gridy = 0; // First row
        mainPanel.add(selectClassLabel, gbc);
        // Fetch available class IDs from the service and populate the combo box
        Set<String> classIds = classService.getAllClassIds();
        classSelectorComboBox = new JComboBox<>(new Vector<>(classIds)); // Using Vector for JComboBox constructor
        classSelectorComboBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1; // Second column
        gbc.gridy = 0; // First row
        gbc.weightx = 1.0; // Allow the combo box to expand horizontally
        mainPanel.add(classSelectorComboBox, gbc);
        // Add a horizontal separator for visual grouping of controls and details
        gbc.gridx = 0; // Resets to first column
        gbc.gridy = 1; // Second row
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(10, 0, 10, 0); // Increase vertical padding for separator
        mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        gbc.insets = new Insets(5, 5, 5, 5); // Reset insets for subsequent components
        gbc.gridwidth = 1; // Reset grid width to default
        // --- Class Details Display Labels ---
        // Initialize title labels for class details (static text)
        nameLabelTitle = new JLabel("Class Name:");
        nameLabelTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        addressLabelTitle = new JLabel("Address:");
        addressLabelTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        schoolYearLabelTitle = new JLabel("School Year:");
        schoolYearLabelTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        // Initialize value labels for displaying actual class details (initially empty or 'N/A')
        nameValueLabel = new JLabel("N/A");
        nameValueLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        addressValueLabel = new JLabel("N/A");
        addressValueLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        schoolYearValueLabel = new JLabel("N/A");
        schoolYearValueLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        // Add title labels to the panel, starting from the row after the separator
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(nameLabelTitle, gbc);
        gbc.gridy = 3;
        mainPanel.add(addressLabelTitle, gbc);
        gbc.gridy = 4;
        mainPanel.add(schoolYearLabelTitle, gbc);
        // Add value labels to the panel
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Allow value labels to expand horizontally
        gbc.gridy = 2;
        mainPanel.add(nameValueLabel, gbc);
        gbc.gridy = 3;
        mainPanel.add(addressValueLabel, gbc);
        gbc.gridy = 4;
        mainPanel.add(schoolYearValueLabel, gbc);
        // --- Show Details Button ---
        showDetailsButton = new JButton("Show Class Details");
        showDetailsButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        // Register an ActionListener to handle button clicks, using a method reference
        showDetailsButton.addActionListener(this::displayClassDetails);
        // Add the button to the panel
        gbc.gridx = 0; // Start at the first column
        gbc.gridy = 5; // After all detail labels
        gbc.gridwidth = 2; // Span across both columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button horizontally
        gbc.weightx = 0; // Do not expand horizontally beyond preferred size
        gbc.insets = new Insets(20, 5, 5, 5); // Add more top padding for spacing
        mainPanel.add(showDetailsButton, gbc);
        // Set the configured main panel as the content pane of the frame
        add(mainPanel);
        // Initial GUI state based on available classes
        if (classIds.isEmpty()) {
            // If no classes are available, disable selection and button, and show a message
            showDetailsButton.setEnabled(false);
            classSelectorComboBox.setEnabled(false);
            nameValueLabel.setText("No classes available for selection.");
            addressValueLabel.setText("");
            schoolYearValueLabel.setText("");
        } else {
            // If classes exist, select the first one by default for better user experience
            classSelectorComboBox.setSelectedIndex(0);
        }
    }
    /**
     * Fetches class details based on the currently selected class in the dropdown
     * using the {@code ClassService} and updates the GUI display.
     * This method simulates the interaction flow as per the use case:
     * <ol>
     *     <li>User selects a class from the dropdown.</li>
     *     <li>User clicks the "Show class details" button.</li>
     *     <li>System retrieves and displays details for the selected class.</li>
     *     <li>System simulates disconnection from the SMOS server.</li>
     * </ol>
     *
     * @param e The {@code ActionEvent} triggered by the button click. This parameter
     *          is required for the method reference syntax but can be ignored.
     */
    private void displayClassDetails(ActionEvent e) {
        // Get the currently selected class ID from the combo box
        String selectedClassId = (String) classSelectorComboBox.getSelectedItem();
        // Validate if a class has actually been selected
        if (selectedClassId == null || selectedClassId.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a class to view its details.",
                    "No Class Selected",
                    JOptionPane.WARNING_MESSAGE);
            return; // Stop execution if no class is selected
        }
        // Disable UI elements to prevent further interaction while details are being fetched
        showDetailsButton.setEnabled(false);
        classSelectorComboBox.setEnabled(false);
        // Update labels to indicate data fetching is in progress
        nameValueLabel.setText("Fetching...");
        addressValueLabel.setText("Fetching...");
        schoolYearValueLabel.setText("Fetching...");
        // Use SwingWorker to perform the potentially long-running service call on a background thread.
        // This keeps the GUI responsive and prevents freezing.
        SwingWorker<ClassDetails, Void> worker = new SwingWorker<ClassDetails, Void>() {
            @Override
            protected ClassDetails doInBackground() throws Exception {
                // Call the service to get class details using the selected ID
                return classService.getClassDetails(selectedClassId);
            }
            @Override
            protected void done() {
                try {
                    ClassDetails details = get(); // Retrieve the result from doInBackground()
                    if (details != null) {
                        // If details are successfully fetched, update the GUI labels
                        nameValueLabel.setText(details.getName());
                        addressValueLabel.setText(details.getAddress());
                        schoolYearValueLabel.setText(details.getSchoolYear());
                        JOptionPane.showMessageDialog(ViewClassDetailsGUI.this,
                                "Class details loaded successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // If no details are returned (e.g., class ID not found or service error),
                        // update labels to show 'Not Found' or 'Error'
                        // The ClassService prints an error to System.err if connection is interrupted.
                        nameValueLabel.setText("Failed to Load");
                        addressValueLabel.setText("Failed to Load");
                        schoolYearValueLabel.setText("Failed to Load");
                        JOptionPane.showMessageDialog(ViewClassDetailsGUI.this,
                                "Failed to load class details. Class '" + selectedClassId + "' not found or SMOS connection interrupted.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // Catch any exceptions that occurred during background processing (e.g., InterruptedException)
                    nameValueLabel.setText("Error");
                    addressValueLabel.setText("Error");
                    schoolYearValueLabel.setText("Error");
                    JOptionPane.showMessageDialog(ViewClassDetailsGUI.this,
                            "An unexpected error occurred: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // Print stack trace for debugging
                } finally {
                    // This block always executes, whether successful or not
                    // Fulfill postcondition: "Connection to the SMOS server interrupted"
                    classService.interruptSMOSConnection();
                    // Update UI to reflect the interrupted connection state permanently for this session
                    showDetailsButton.setEnabled(false); // No more details can be fetched
                    classSelectorComboBox.setEnabled(false); // No more classes can be selected for viewing
                    // Inform the user about the connection interruption
                    JOptionPane.showMessageDialog(ViewClassDetailsGUI.this,
                            "Connection to the SMOS server has been interrupted. No further class details can be viewed.",
                            "SMOS Connection Interrupted",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        };
        worker.execute(); // Start the SwingWorker to run the task
    }
}