import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Provides the graphical user interface for inserting new teachings into the archive.
 * Implements the "InsertNewTeaching" use case as prompted by the "New Teaching" button
 * in the main view (TeachingListView).
 */
public class InsertNewTeachingGUI extends JFrame {
    private TeachingArchive teachingArchive; // Reference to the archive business logic
    private JTextField teachingNameField;   // Input field for teaching name
    private JButton saveButton;             // Button to save the new teaching
    private JLabel statusLabel;             // Label to display success/error messages
    /**
     * Enum for message types to control label styling.
     */
    private enum MessageType {
        INFO, SUCCESS, ERROR
    }
    /**
     * Constructs a new InsertNewTeachingGUI.
     * @param archive The TeachingArchive instance to interact with.
     */
    public InsertNewTeachingGUI(TeachingArchive archive) {
        this.teachingArchive = archive;
        setTitle("Insert New Teaching");
        setSize(400, 200);
        // Close only this dialog window, not the entire application
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null); // Center the window relative to the screen
        createUI(); // Initialize GUI components
    }
    /**
     * Initializes and lays out the GUI components for the insertion form.
     */
    private void createUI() {
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        // Form panel for input fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Label for teaching name
        JLabel nameLabel = new JLabel("Teaching Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(nameLabel, gbc);
        // Text field for teaching name input
        teachingNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Allow text field to expand horizontally
        formPanel.add(teachingNameField, gbc);
        add(formPanel, BorderLayout.CENTER);
        // Status label at the bottom for messages
        statusLabel = new JLabel("Fill out the form to add a new teaching.");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH); // Display above the form section
        // Button panel for Save and Cancel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSaveButtonClicked();
            }
        });
        buttonPanel.add(saveButton);
        // Add a Cancel button as per postcondition "the administrator interrupts the operation"
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Disposes the window, simulating the administrator interrupting the operation
                dispose(); 
            }
        });
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Handles the action when the "Save" button is clicked.
     * Retrieves input, validates it, and attempts to add the teaching to the archive.
     * Displays appropriate feedback to the user and handles various outcomes.
     */
    private void onSaveButtonClicked() {
        String teachingName = teachingNameField.getText();
        // Attempt to add the teaching through the archive manager
        TeachingArchive.AddTeachingResult result = teachingArchive.addTeaching(teachingName);
        // Provide feedback to the user based on the result
        switch (result) {
            case SUCCESS:
                displayMessage("Teaching '" + teachingName + "' added successfully!", MessageType.SUCCESS);
                clearForm(); // Clear input field on success
                // Optionally, automatically close the window after successful insertion
                // dispose(); 
                break;
            case INVALID_DATA:
                // Activates the case of "Errodati" use.
                displayMessage("Error: Teaching name cannot be empty or invalid.", MessageType.ERROR);
                break;
            case DUPLICATE_NAME:
                displayMessage("Error: A teaching with the name '" + teachingName + "' already exists.", MessageType.ERROR);
                break;
            case SMOS_CONNECTION_ERROR:
                // Notifies the error: connection to the SMOS server interrupted.
                // Using JOptionPane for critical errors to ensure administrator awareness.
                JOptionPane.showMessageDialog(this,
                                              "Error: Connection to the SMOS server interrupted. Teaching could not be saved.",
                                              "SMOS Server Error - Critical",
                                              JOptionPane.ERROR_MESSAGE);
                // Still update the JLabel for consistency and immediate feedback on the form itself.
                displayMessage("Error: SMOS connection failed. See pop-up for details.", MessageType.ERROR);
                break;
        }
    }
    /**
     * Clears the input field after a successful operation or if needed.
     */
    private void clearForm() {
        teachingNameField.setText("");
        // Optionally reset status message
        displayMessage("Fill out the form to add a new teaching.", MessageType.INFO);
    }
    /**
     * Displays a message to the user in the status label with appropriate styling.
     * @param message The message string to display.
     * @param type The type of message (INFO, SUCCESS, ERROR) to determine text color.
     */
    private void displayMessage(String message, MessageType type) {
        statusLabel.setText(message);
        switch (type) {
            case INFO:
                statusLabel.setForeground(Color.BLACK);
                break;
            case SUCCESS:
                statusLabel.setForeground(new Color(0, 128, 0)); // Green color for success
                break;
            case ERROR:
                statusLabel.setForeground(Color.RED); // Red color for errors
                break;
        }
    }
}