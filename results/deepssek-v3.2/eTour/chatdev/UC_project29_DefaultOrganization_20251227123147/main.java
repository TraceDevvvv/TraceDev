/**
 * Main class for the ExistingErrorTag application
 * This program simulates a system where users enter search tags,
 * with error handling for duplicate tags and state recovery.
 * The use case "ExistingErrorTag" is implemented with the following flow:
 * 1. Notice the error message and asks for confirmation of its reading.
 * 2. Confirmation of the reading of the notification.
 * 3. Recovers the previous state.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
public class Main {
    private JFrame frame;
    private JTextField tagField;
    private JButton addButton;
    private JTextArea tagsArea;
    private List<String> existingTags;
    private String previousState;
    public Main() {
        existingTags = new ArrayList<>();
        previousState = "";
        // Initialize sample tags in lowercase for consistency
        existingTags.add("java".toLowerCase());
        existingTags.add("programming".toLowerCase());
        existingTags.add("gui".toLowerCase());
        existingTags.add("swing".toLowerCase());
        initializeGUI();
    }
    /**
     * Initialize the GUI components and layout.
     * Sets up the main window, input panel, tags display, and action listeners.
     */
    private void initializeGUI() {
        frame = new JFrame("Search Tag System - ExistingErrorTag Use Case");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        // Create top panel for input
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Search Tag:"));
        tagField = new JTextField(20);
        inputPanel.add(tagField);
        addButton = new JButton("Add Tag");
        inputPanel.add(addButton);
        frame.add(inputPanel, BorderLayout.NORTH);
        // Create center panel for displaying existing tags
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Existing Tags in System"));
        tagsArea = new JTextArea(10, 30);
        tagsArea.setEditable(false);
        updateTagsDisplay();
        JScrollPane scrollPane = new JScrollPane(tagsArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(centerPanel, BorderLayout.CENTER);
        // Create bottom panel for status
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel statusLabel = new JLabel("Status: Ready");
        bottomPanel.add(statusLabel);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTag();
            }
        });
        tagField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTag();
            }
        });
        frame.setVisible(true);
    }
    /**
     * Adds a new tag to the system.
     * Implements the use case flow:
     * 1. Check if tag exists, show error if it does (Step 1: Notice error and ask confirmation).
     * 2. Ask for confirmation of error reading (Step 2: Confirmation).
     * 3. Recover previous state (Step 3: State recovery).
     */
    private void addTag() {
        String newTag = tagField.getText().trim();
        // Check if tag is empty
        if (newTag.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a tag", "Input Error", JOptionPane.WARNING_MESSAGE);
            tagField.requestFocus();
            return;
        }
        // Store current input as previous state BEFORE checking for duplicates
        // This captures the state before the erroneous entry attempt
        previousState = tagField.getText();
        // Normalize tag to lowercase for case-insensitive comparison and storage
        String normalizedTag = newTag.toLowerCase();
        // Check if tag already exists (use case entry condition)
        if (existingTags.contains(normalizedTag)) {
            // Step 1: Notice the error message and ask for confirmation
            int response = JOptionPane.showConfirmDialog(
                frame,
                "Error: The tag '" + newTag + "' already exists in the system.\n\nDo you understand this error message?",
                "Duplicate Tag Error",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE
            );
            // Step 2: Confirmation of the reading of the notification
            if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Thank you for confirming. The system will now recover the previous state.",
                    "Confirmation Received",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    frame,
                    "Please read the error message carefully. The tag '" + newTag + "' is already in the system.",
                    "Please Review Error",
                    JOptionPane.WARNING_MESSAGE
                );
            }
            // Step 3: Recovers the previous state
            recoverPreviousState();
        } else {
            // Add new tag to system (normalized to lowercase)
            existingTags.add(normalizedTag);
            updateTagsDisplay();
            tagField.setText("");
            // Clear previousState after successful addition since we don't need to recover from valid operations
            previousState = "";
            JOptionPane.showMessageDialog(frame, "Tag '" + newTag + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            tagField.requestFocus();
        }
    }
    /**
     * Updates the display area with current tags.
     * Formats tags with bullet points and new lines for readability.
     */
    private void updateTagsDisplay() {
        StringBuilder sb = new StringBuilder();
        for (String tag : existingTags) {
            sb.append("- ").append(tag).append("\n");
        }
        tagsArea.setText(sb.toString());
    }
    /**
     * Recovers the previous state (from step 3 of use case).
     * Restores the input field to its content before the invalid input was entered,
     * and returns focus to the input field.
     */
    private void recoverPreviousState() {
        // Restore the field to its state before the erroneous entry
        tagField.setText(previousState);
        tagField.requestFocus();
        // Reset previousState after recovery
        previousState = "";
        // The system returns control to the user interaction (exit condition)
    }
    /**
     * Main method to launch the application.
     * Ensures thread-safe GUI creation via SwingUtilities.invokeLater.
     */
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}