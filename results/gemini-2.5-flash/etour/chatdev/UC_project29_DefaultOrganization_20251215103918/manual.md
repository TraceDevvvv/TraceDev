```
# Tag Management System: ERRORETAGESISTENTE Use Case Manual

## 🤔 What is this application?

This application is a simple Java Swing-based Graphical User Interface (GUI) program designed to demonstrate a "Tag Management System." Its primary purpose is to illustrate the `ERRORETAGESISTENTE` (Existing Tag Error) use case. This scenario occurs when a user attempts to add a search tag that already exists within the system. The application simulates the flow of events for handling such an error, providing clear feedback to the user and allowing them to correct their input.

## Features

The core functionality of this application revolves around:

*   **Tag Existence Check:** The system efficiently checks if a user-entered tag already exists. Tags are treated as case-insensitive (e.g., "java" is considered the same as "Java").
*   **Error Reporting (`ERRORETAGESISTENTE`):** When a user enters an existing tag, a clear error message is displayed via a pop-up dialog.
*   **User Confirmation:** The user must acknowledge the error message, simulating confirmation of reading the notification.
*   **State Recovery:** After acknowledging the error, the system recovers its previous state by clearing the input field and refocusing it, allowing the user to quickly enter a new, unique tag.
*   **New Tag Addition:** Users can also add new, unique tags to the system. These tags are then displayed in the status message.
*   **Predefined Tags:** The system starts with a few predefined tags (e.g., "java", "programming", "software", "chatdev") to facilitate immediate testing of the error case.

## 📖 How to Install and Run

This application is written in Java and uses standard Java Swing libraries, which are part of the Java Development Kit (JDK). No external dependencies beyond a standard JDK installation are required.

### Prerequisites

*   **Java Development Kit (JDK):** You need to have JDK 8 or a newer version installed on your system. You can download it from the official Oracle website or adoptium.net.
    *   To check if Java is installed, open your terminal or command prompt and type:
        ```bash
        java -version
        javac -version
        ```
        If you see version information, you're good to go.

### Setup and Execution

1.  **Save the Source Files:**
    Save the provided Java code blocks into two separate files named `TagManager.java` and `TagApplication.java`. Ensure both files are in the *same directory*.

    *   **TagManager.java:**
        ```java
        import java.util.HashSet;
        import java.util.Set;
        /**
         * Manages a collection of tags existing in the system.
         * Simulates the backend logic for checking and adding tags.
         */
        public class TagManager {
            // A Set is used to store tags for efficient lookup (O(1) average time complexity)
            // and to naturally enforce uniqueness of tags.
            private final Set<String> existingTags;
            /**
             * Constructs a new TagManager and initializes it with some predefined tags.
             * These initial tags simulate data already present in the system.
             */
            public TagManager() {
                existingTags = new HashSet<>();
                // Add some initial tags to simulate existing data for testing the error case
                existingTags.add("java");
                existingTags.add("programming");
                existingTags.add("software");
                existingTags.add("chatdev");
            }
            /**
             * Checks if a given tag already exists in the system.
             * This method is central to the "ERRORETAGESISTENTE" use case.
             *
             * @param tag The tag string to check.
             * @return true if the tag exists (case-insensitively), false otherwise.
             */
            public boolean tagExists(String tag) {
                // Handle null or empty tags as they cannot meaningfully "exist"
                if (tag == null || tag.trim().isEmpty()) {
                    return false;
                }
                // Perform a case-insensitive check by converting the input tag to lowercase.
                return existingTags.contains(tag.toLowerCase());
            }
            /**
             * Adds a new tag to the system.
             * This method is included for completeness of a tag management system,
             * but the primary focus of the ERRORETAGESISTENTE use case is on {@link #tagExists(String)}.
             *
             * @param tag The tag string to add.
             * @return true if the tag was successfully added (i.e., it did not exist before), false otherwise.
             */
            public boolean addTag(String tag) {
                // Refuse to add null or empty tags.
                if (tag == null || tag.trim().isEmpty()) {
                    return false;
                }
                // Add the tag in lowercase to maintain case-insensitivity within the system.
                return existingTags.add(tag.toLowerCase());
            }
            /**
             * Retrieves all tags currently stored in the system.
             *
             * @return A new {@code HashSet} containing all existing tags (lowercase).
             */
            public Set<String> getAllTags() {
                // Return a copy to prevent external modification of the internal set.
                return new HashSet<>(existingTags);
            }
        }
        ```

    *   **TagApplication.java:**
        ```java
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
        ```

2.  **Open Terminal/Command Prompt:**
    Navigate to the directory where you saved `TagManager.java` and `TagApplication.java`.

3.  **Compile the Java Code:**
    Use the `javac` command to compile both Java source files.
    ```bash
    javac TagManager.java TagApplication.java
    ```
    This will create `TagManager.class` and `TagApplication.class` files in the same directory. If there are no errors, the command will complete silently.

4.  **Run the Application:**
    Now, execute the compiled `TagApplication` using the `java` command.
    ```bash
    java TagApplication
    ```
    A GUI window titled "Tag Management System - ERRORETAGESISTENTE Use Case" should appear.

## ▶️ How to Use/Play

Once the application window is open, you will see:

*   An input field labeled "Enter a tag:"
*   A "Submit Tag" button.
*   A status bar at the bottom displaying messages.

**Initial State:**
The status bar will show "Ready to add tags (case-insensitive). Current system tags: java, programming, software, chatdev". These are the tags pre-loaded into the system.

**Scenario 1: Triggering `ERRORETAGESISTENTE` (Existing Tag Error)**

1.  **Enter an Existing Tag:** In the "Enter a tag:" field, type one of the predefined tags, for example, `java`. You can also try variations like `Java` or `JAVA` as the system handles tags case-insensitively.
2.  **Submit the Tag:** Click the "Submit Tag" button or press `Enter` on your keyboard.
3.  **Observe Error Message:** A pop-up dialog box will appear with the message:
    "Error: The tag 'java' already exists in the system. Please try a different one."
    The title of the dialog will be "Tag Already Exists", and it will have an error icon.
4.  **Confirm Reading:** Click the "OK" button on the pop-up dialog.
5.  **System Recovers State:**
    *   The input field will be cleared.
    *   The input field will automatically regain focus, ready for your next input.
    *   The status bar at the bottom will update to: "Tag 'java' already exists. Please enter another tag."
    This entire flow demonstrates the `ERRORETAGESISTENTE` use case as described in the requirements.

**Scenario 2: Adding a New Tag Successfully**

1.  **Enter a Unique Tag:** In the "Enter a tag:" field, type a tag that does *not* exist in the initial list or any you've added (e.g., `python`, `agile`, `database`).
2.  **Submit the Tag:** Click the "Submit Tag" button or press `Enter`.
3.  **Observe Success Message:** The input field will clear, and the status bar will update to something like:
    "Successfully added tag: 'python'. Tags are case-insensitive. Current system tags: java, programming, software, chatdev, python" (the list of current tags will include your newly added tag).

You can repeat these steps to test different scenarios and observe the application's behavior. Close the application window to exit.

```