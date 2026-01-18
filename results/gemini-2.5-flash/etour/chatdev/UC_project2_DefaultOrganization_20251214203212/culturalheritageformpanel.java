/**
 * This class represents the GUI form for inserting a new cultural heritage object.
 * It's a JPanel containing input fields for name, description, and location,
 * along with a submit button. It validates user input, interacts with the
 * CulturalHeritageService to add new objects, and provides feedback to the user.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CulturalHeritageFormPanel extends JPanel {
    // GUI components for input
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextField locationField;
    private JButton submitButton;
    // Service layer dependency for business logic
    private CulturalHeritageService service;
    /**
     * Constructor for CulturalHeritageFormPanel.
     *
     * @param service The CulturalHeritageService instance to interact with.
     */
    public CulturalHeritageFormPanel(CulturalHeritageService service) {
        this.service = service;
        setupUI(); // Initialize and layout GUI components
        addListeners(); // Attach action listeners to components
    }
    /**
     * Sets up the graphical user interface elements of the form.
     * Uses GridBagLayout for flexible component placement.
     */
    private void setupUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Label and text field for Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);
        // Label and text area for Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        descriptionArea = new JTextArea(5, 20); // 5 rows, 20 columns
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea); // Add scroll for long descriptions
        add(scrollPane, gbc);
        // Label and text field for Location
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        locationField = new JTextField(20);
        add(locationField, gbc);
        // Submit Button
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Spans 1 column
        gbc.anchor = GridBagConstraints.EAST; // Aligns button to the right
        submitButton = new JButton("Submit Cultural Good");
        add(submitButton, gbc);
    }
    /**
     * Adds event listeners to the interactive components, specifically the submit button.
     */
    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSubmission(); // Call method to handle form submission
            }
        });
    }
    /**
     * Validates the input fields (name, description, location) to ensure they are not empty.
     *
     * @param name The cultural heritage name.
     * @param description The cultural heritage description.
     * @param location The cultural heritage location.
     * @return true if all inputs are valid, false otherwise.
     */
    private boolean validateInput(String name, String description, String location) {
        if (name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (description.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Description cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (location.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Location cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    /**
     * Processes the form submission:
     * 1. Reads data from input fields.
     * 2. Validates the data.
     * 3. Asks for user confirmation.
     * 4. Attempts to add the cultural heritage object via the service.
     * 5. Handles various outcomes: success, duplicate, network error, or invalid data.
     */
    private void processSubmission() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        String location = locationField.getText();
        // Step 4: Verify the data entered and activate use case Errored if invalid or insufficient.
        if (!validateInput(name, description, location)) {
            // Error message already shown by validateInput method.
            return; // Stop processing if input is invalid.
        }
        // Ask for confirmation before processing the transaction.
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to add this cultural good?\n" +
                        "Name: " + name + "\n" +
                        "Description: " + description + "\n" +
                        "Location: " + location,
                "Confirm Operation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        // Step 5: Confirm the operation (or cancel it).
        if (confirmation == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Operation cancelled by Agency Operator.",
                                          "Cancellation", JOptionPane.INFORMATION_MESSAGE);
            return; // Operator cancelled.
        }
        // Create a new CulturalHeritage object from the form data.
        CulturalHeritage newCulturalHeritage = new CulturalHeritage(name, description, location);
        try {
            // Step 6: Memorize your new cultural good via the service.
            // Attempt to add the cultural heritage using the service layer.
            boolean added = service.addCulturalHeritage(newCulturalHeritage);
            if (added) {
                // Exit condition: The system shall notify the proper inclusion of the cultural.
                JOptionPane.showMessageDialog(this,
                        "Cultural good '" + name + "' successfully inserted!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm(); // Clear form fields after successful insertion.
            } else {
                // Quality requirement: The system requirements will not accept duplicates cultural heritage.
                JOptionPane.showMessageDialog(this,
                        "Error: Cultural good with name '" + name + "' already exists.",
                        "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NetworkErrorException ex) {
            // Exit condition: Interruption of the connection to the server ETOUR.
            JOptionPane.showMessageDialog(this,
                    "Connection Error (ETOUR): " + ex.getMessage(),
                    "Network Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            // Handles potential errors from the service layer, although
            // input validation should ideally catch most of these earlier.
             JOptionPane.showMessageDialog(this,
                    "Data Error: " + ex.getMessage(),
                    "System Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Catch any unexpected exceptions.
            JOptionPane.showMessageDialog(this,
                    "An unexpected error occurred: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Clears the text from all input fields in the form.
     */
    private void clearForm() {
        nameField.setText("");
        descriptionArea.setText("");
        locationField.setText("");
    }
}