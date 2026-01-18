'''
TagApplication.java
This is the main application class for a graphical user interface (GUI) based
Tag Management System. It demonstrates the "ERRORETAGESISTENTE" use case,
where the system responds to a user attempting to add a tag that already exists.
The GUI allows users to input tags. If an existing tag is entered, an error
message is displayed, and upon confirmation, the system reverts to a previous state
for user interaction.
This application uses Java Swing for its GUI components.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set; // Required for displaying all tags from TagManager
/**
 * Main application class for the Tag Management System GUI.
 * This class extends {@link JFrame} to provide a window-based application.
 * It primarily implements the "ERRORETAGESISTENTE" use case,
 * which handles the scenario where a user attempts to add a tag that already exists.
 */
public class TagApplication extends JFrame {
    // Backend logic for managing tags, decoupled from the UI.
    private final TagManager tagManager;
    // GUI components for user interaction and feedback.
    private JTextField tagInputField;    // Input field for the user to type tags.
    private JLabel statusLabel;          // Label to display dynamic status messages to the user.
    private JButton addButton;           // Button to trigger the tag addition action.
    /**
     * Constructs a new TagApplication instance.
     * Initializes the {@link TagManager} and sets up the graphical user interface.
     */
    public TagApplication() {
        tagManager = new TagManager(); // Instantiate the tag manager.
        // Configure the main window (JFrame).
        setTitle("Tag Management System - ERRORETAGESISTENTE Use Case");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Application exits when window is closed.
        setSize(450, 200); // Set initial window size (width, height).
        setLocationRelativeTo(null); // Center the window on the screen.
        setResizable(false); // Prevent resizing for a simpler layout.
        initGUI();       // Initialize all GUI components and their layout.
        addListeners();  // Attach event listeners to make components interactive.
        // Initial status message showing existing tags.
        // Modified: Added explicit mention of case-insensitivity for clarity.
        updateStatusLabel("Ready to add tags (case-insensitive). Current system tags: " + getFormattedTags(tagManager.getAllTags()));
    }
    /**
     * Initializes and arranges the graphical user interface components within the frame.
     * Uses {@link BorderLayout} for overall layout and {@link FlowLayout} for panels.
     */
    private void initGUI() {
        // Set the main layout manager for the JFrame.
        setLayout(new BorderLayout(10, 10)); // Add some padding around components.
        // Panel for user input components (placed at the top).
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // Add some empty border for visual spacing.
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        JLabel instructionLabel = new JLabel("Enter a tag:");
        tagInputField = new JTextField(20); // Create a text field for tag input (20 columns wide).
        addButton = new JButton("Submit Tag"); // Create the "Submit Tag" button for general processing.
        inputPanel.add(instructionLabel);
        inputPanel.add(tagInputField);
        inputPanel.add(addButton);
        // Panel for displaying status messages (placed in the center/bottom).
        // Using a JPanel with FlowLayout centered for the status label.
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusLabel = new JLabel("Status messages will appear here."); // Initialize status label.
        statusPanel.add(statusLabel);
        // Add the panels to the JFrame's content pane.
        add(inputPanel, BorderLayout.NORTH);
        add(statusPanel, BorderLayout.CENTER);
    }
    /**
     * Attaches action listeners to interactive GUI components.
     * Specifically, it links the "Add Tag" button and the Enter key press in the
     * text field to the {@link #handleAddTag()} method.
     */
    private void addListeners() {
        // Action listener for the "Submit Tag" button.
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddTag(); // Call the handler whenever the button is clicked.
            }
        });
        // Action listener for the text field, triggered when Enter key is pressed.
        tagInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddTag(); // Call the handler when Enter is pressed in the text field.
            }
        });
    }
    /**
     * Handles the logic for adding a tag based on user input.
     * This method implements the core event flow described in the "ERRORETAGESISTENTE" use case.
     */
    private void handleAddTag() {
        // 1. Get the text from the input field and trim leading/trailing whitespace.
        String tag = tagInputField.getText().trim();
        // Handle the edge case where the user inputs an empty tag.
        if (tag.isEmpty()) {
            updateStatusLabel("Input cannot be empty. Please enter a tag.");
            tagInputField.requestFocusInWindow(); // Keep focus on the input field.
            return; // Exit the method early.
        }
        // Check if the entered tag already exists in the system.
        // This is the trigger for the "ERRORETAGESISTENTE" use case.
        if (tagManager.tagExists(tag)) {
            // Use Case: ERRORETAGESISTENTE - Tag already exists.
            // Flow of events - Step 1: System not the error and displays a message.
            // Using JOptionPane for a modal dialog that requires user interaction.
            JOptionPane.showMessageDialog(this,
                    "Error: The tag '" + tag + "' already exists in the system.\nPlease try a different one.",
                    "Tag Already Exists", // Title of the dialog
                    JOptionPane.ERROR_MESSAGE); // Specifies the icon type (error icon)
            // Flow of events - Step 2: User confirms reading of the notification.
            // This implicitly occurs when the user clicks the "OK" button on the JOptionPane
            // and the dialog closes, allowing the program flow to continue.
            // Flow of events - Step 3: System recovers the previous state.
            // This is simulated by:
            tagInputField.setText(""); // Clearing the input field.
            tagInputField.requestFocusInWindow(); // Returning focus to the input field,
                                                  // allowing the user to immediately enter a new tag.
            // Update the persistent status label for continuous feedback.
            updateStatusLabel("Tag '" + tag + "' already exists. Please enter another tag.");
        } else {
            // This path is for successfully adding a new tag (outside the explicit error use case,
            // but necessary for a complete and runnable application demonstration).
            tagManager.addTag(tag); // Add the new tag to the system.
            // Modified: Added explicit mention of case-insensitivity for clarity.
            updateStatusLabel("Successfully added tag: '" + tag + "'. Tags are case-insensitive. Current system tags: " + getFormattedTags(tagManager.getAllTags()));
            tagInputField.setText(""); // Clear the input field after a successful addition.
            tagInputField.requestFocusInWindow(); // Restore focus to the input field.
        }
    }
    /**
     * Helper method to update the text of the status label.
     * @param message The message string to display.
     */
    private void updateStatusLabel(String message) {
        statusLabel.setText(message);
    }
    /**
     * Helper method to format a set of tags into a comma-separated string for display.
     * @param tags The set of tags to format.
     * @return A string representation of the tags.
     */
    private String getFormattedTags(Set<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return "None";
        }
        // Using String.join for elegant comma-separated list creation.
        return String.join(", ", tags);
    }
    /**
     * The main entry point for the TagApplication.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * for thread safety, as all Swing operations should be performed on the EDT.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure the GUI creation and updates
        // happen on the Event Dispatch Thread (EDT), which is crucial for Swing applications.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TagApplication().setVisible(true); // Create an instance of the application and make it visible.
            }
        });
    }
}