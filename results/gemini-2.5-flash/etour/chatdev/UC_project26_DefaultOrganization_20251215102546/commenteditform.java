'''
This JFrame provides a form for the Agency Operator to edit the comment
of a selected feedback entry.
'''
package com.chatdev.gui.components;
import com.chatdev.models.Feedback;
import com.chatdev.gui.utils.DialogUtils;
import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Runnable; // Explicitly import Runnable
public class CommentEditForm extends JFrame {
    private Feedback feedbackToEdit;
    private JTextArea commentTextArea;
    private JButton saveButton;
    private JButton cancelButton;
    /**
     * Constructs a CommentEditForm.
     * @param feedback The Feedback object whose comment is to be edited.
     * @param onSaveCallback A BiConsumer functional interface to be called when the comment is saved.
     *                       It will receive the feedback's ID and the new comment text.
     * @param onCancelCallback A Runnable functional interface to be called when the operation is cancelled.
     */
    public CommentEditForm(Feedback feedback, BiConsumer<Integer, String> onSaveCallback, Runnable onCancelCallback) {
        super("Edit Comment for Feedback ID: " + feedback.getId());
        this.feedbackToEdit = feedback;
        // Use DISPOSE_ON_CLOSE to hide and dispose this frame, but not terminate the JVM
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null); // Center the window
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // Added spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        // Original comment display (for context)
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 5, 5)); // Added spacing
        headerPanel.add(new JLabel("Original Comment (ID: " + feedback.getId() + "):"));
        JTextArea originalCommentDisplay = new JTextArea(feedback.getComment());
        originalCommentDisplay.setLineWrap(true);
        originalCommentDisplay.setWrapStyleWord(true);
        originalCommentDisplay.setEditable(false);
        originalCommentDisplay.setBackground(UIManager.getColor("Panel.background")); // Make it look like a label
        originalCommentDisplay.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Add a subtle border
        headerPanel.add(new JScrollPane(originalCommentDisplay));
        // Comment editing area
        JPanel editPanel = new JPanel(new BorderLayout(5, 5)); // Added spacing
        editPanel.add(new JLabel("Edit Comment:"), BorderLayout.NORTH);
        commentTextArea = new JTextArea(feedback.getComment(), 5, 30); // Initial text from existing comment
        commentTextArea.setLineWrap(true);
        commentTextArea.setWrapStyleWord(true);
        JScrollPane commentScrollPane = new JScrollPane(commentTextArea);
        editPanel.add(commentScrollPane, BorderLayout.CENTER);
        // Buttons
        saveButton = new JButton("Save Comment");
        saveButton.addActionListener(e -> {
            String newComment = commentTextArea.getText();
            // Basic client-side validation
            if (newComment != null && !newComment.trim().isEmpty()) {
                // Request confirmation before saving - Use Case step 6
                int response = DialogUtils.showConfirmation(this, "Are you sure you want to save this change?", "Confirm Change");
                if (response == JOptionPane.YES_OPTION) { // If user confirms - Use Case step 7
                    onSaveCallback.accept(feedbackToEdit.getId(), newComment);
                    dispose(); // Close this frame after successful operation
                }
            } else {
                // Where the data is invalid or insufficient, the system activates the use case Errored.
                // In this GUI context, we show an error message.
                DialogUtils.showError(this, "Comment cannot be empty or just whitespace.", "Invalid Comment");
                // The form remains open for the user to correct the input.
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            // Inform the system that the operation was cancelled (Exit condition)
            onCancelCallback.run();
            dispose(); // Close this frame
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Added spacing
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(editPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
}