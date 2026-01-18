/**
 * A JPanel form for editing or adding teaching details.
 * This panel is activated when the user wants to edit a teaching or add a new one.
 * It performs data validation upon saving as per the use case.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
public class EditTeachingPanel extends JPanel {
    private TeachingApp parentApp; // Reference to the main application to switch panels
    private TeachingService teachingService;
    private JTextField idField;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextField instructorField;
    private JTextField creditsField;
    private JButton saveButton;
    private JButton cancelButton;
    private Teaching currentTeaching; // The teaching object being edited (null if adding a new one)
    /**
     * Constructs the EditTeachingPanel.
     * @param parentApp The main application instance for panel navigation.
     */
    public EditTeachingPanel(TeachingApp parentApp) {
        this.parentApp = parentApp;
        this.teachingService = TeachingService.getInstance();
        setLayout(new BorderLayout(10, 10)); // Add some padding
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // ID Field (read-only)
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(20);
        idField.setEditable(false); // ID is not editable
        formPanel.add(idField, gbc);
        // Name Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);
        // Description Area
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        descriptionArea = new JTextArea(5, 20); // 5 rows, 20 columns
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        formPanel.add(descriptionScrollPane, gbc);
        // Instructor Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Instructor:"), gbc);
        gbc.gridx = 1;
        instructorField = new JTextField(20);
        formPanel.add(instructorField, gbc);
        // Credits Field
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Credits:"), gbc);
        gbc.gridx = 1;
        creditsField = new JTextField(20);
        formPanel.add(creditsField, gbc);
        add(formPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Action Listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeaching();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the list view without saving changes
                parentApp.showListPanel();
                // Postcondition: "the administrator interrupts the operation" is met.
            }
        });
    }
    /**
     * Loads the details of a teaching into the form fields.
     * If the teaching object is null, it prepares the form for adding a new teaching.
     * @param teaching The Teaching object to load, or null for a new teaching.
     */
    public void loadTeaching(Teaching teaching) {
        this.currentTeaching = teaching;
        if (teaching == null) {
            // Clear fields for a new teaching
            idField.setText("New ID (auto-generated)"); // Placeholder for new ID
            nameField.setText("");
            descriptionArea.setText("");
            instructorField.setText("");
            creditsField.setText("");
        } else {
            // Populate fields for editing an existing teaching
            idField.setText(teaching.getId());
            nameField.setText(teaching.getName());
            descriptionArea.setText(teaching.getDescription());
            instructorField.setText(teaching.getInstructor());
            creditsField.setText(String.valueOf(teaching.getCredits()));
        }
    }
    /**
     * Handles the save operation. It validates the input data and
     * either updates an existing teaching or adds a new one.
     * Activates "Errodati" use case if data is invalid.
     * Notifies the user about the success or failure of the operation.
     */
    private void saveTeaching() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        String instructor = instructorField.getText();
        String creditsStr = creditsField.getText();
        // Step 2: Make checks on the validity of the data entered
        String validationErrors = Validator.validateTeaching(name, description, instructor, creditsStr);
        if (validationErrors != null) {
            // In the event that the data entered are not valid, activates the case of "Errodati" use.
            ErrorDialog.showError(validationErrors);
            // Postcondition: "is notified the data error"
            return;
        }
        // Data is valid, proceed with saving
        int credits = Integer.parseInt(creditsStr);
        OperationResult result; // Use the new OperationResult type
        if (currentTeaching == null) {
            // Add new teaching
            String newId = UUID.randomUUID().toString(); // Generate a unique ID for new teaching
            Teaching newTeaching = new Teaching(newId, name, description, instructor, credits);
            result = teachingService.addTeaching(newTeaching);
        } else {
            // Update existing teaching
            // Create a new Teaching object with updated data.
            // This approach ensures that we pass a new object potentially with a new hash/identity
            // if the original 'currentTeaching' reference was being held elsewhere,
            // though TeachingService will match by ID.
            Teaching updatedTeaching = new Teaching(currentTeaching.getId(), name, description, instructor, credits);
            result = teachingService.updateTeaching(updatedTeaching);
            // Postcondition: "The user has changed a teaching" is met if success is true
        }
        // Check the success flag from the OperationResult
        if (result.isSuccess()) {
            JOptionPane.showMessageDialog(this, result.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
            // Step 3: View the list of updated teachings
            // This is handled by returning to the TeachingListPanel which will refresh its data.
            parentApp.showListPanel();
        } else {
            // Display the specific error message provided by the service
            ErrorDialog.showError(result.getMessage());
        }
    }
}