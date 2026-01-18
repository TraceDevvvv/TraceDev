/**
 * This panel provides the user interface for editing a comment.
 * It displays the current comment, allows the user to input a new one,
 * and handles the interaction with the simulated ETourServer.
 * This class has been updated to use SwingWorker for asynchronous operations,
 * preventing the UI from freezing during simulated network calls.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.concurrent.ExecutionException; // Added for SwingWorker exception handling
public class CommentEditorPanel extends JPanel {
    private ETourServer etourServer;
    private int currentFeedbackId;
    private int currentSiteId;
    private JLabel siteInfoLabel;
    private JLabel originalCommentLabel;
    private JTextArea newCommentTextArea;
    private JButton editButton;
    private JButton confirmButton;
    private JButton cancelButton;
    /**
     * Constructor for CommentEditorPanel.
     * Initializes the panel with UI components and links to the ETourServer.
     * @param etourServer The simulated backend server instance.
     */
    public CommentEditorPanel(ETourServer etourServer) {
        this.etourServer = etourServer;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        // --- North Panel: Site and Feedback Information ---
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        siteInfoLabel = new JLabel("Please select a feedback to edit.");
        originalCommentLabel = new JLabel("Original Comment: ");
        infoPanel.add(siteInfoLabel);
        infoPanel.add(originalCommentLabel);
        add(infoPanel, BorderLayout.NORTH);
        // --- Center Panel: New Comment Input ---
        JPanel commentInputPanel = new JPanel(new BorderLayout(5, 5));
        commentInputPanel.add(new JLabel("Enter New Comment:"), BorderLayout.NORTH);
        newCommentTextArea = new JTextArea(5, 30); // 5 rows, 30 columns
        newCommentTextArea.setLineWrap(true);
        newCommentTextArea.setWrapStyleWord(true);
        newCommentTextArea.setEnabled(false); // Disable until 'Edit' is clicked
        JScrollPane scrollPane = new JScrollPane(newCommentTextArea);
        commentInputPanel.add(scrollPane, BorderLayout.CENTER);
        add(commentInputPanel, BorderLayout.CENTER);
        // --- South Panel: Action Buttons ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        editButton = new JButton("Edit Comment");
        confirmButton = new JButton("Confirm Change");
        cancelButton = new JButton("Cancel");
        editButton.addActionListener(new EditButtonListener());
        confirmButton.addActionListener(new ConfirmButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        buttonPanel.add(editButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Initial state of buttons
        setEditingMode(false);
    }
    /**
     * Loads the details of a specific feedback into the panel for editing.
     * @param feedbackId The ID of the feedback to load.
     */
    public void loadFeedback(int feedbackId) {
        Optional<Feedback> feedbackOpt = etourServer.getFeedbackById(feedbackId);
        if (feedbackOpt.isPresent()) {
            Feedback feedback = feedbackOpt.get();
            Optional<Site> siteOpt = etourServer.getSiteById(feedback.getSiteId());
            if (siteOpt.isPresent()) {
                Site site = siteOpt.get();
                currentFeedbackId = feedbackId;
                currentSiteId = feedback.getSiteId();
                siteInfoLabel.setText("Site: " + site.getName() + " | Tourist: " + feedback.getTouristName());
                originalCommentLabel.setText("Original Comment: " + feedback.getComment());
                newCommentTextArea.setText(feedback.getComment()); // Pre-fill with existing comment
                // Allow editing now that feedback is loaded
                editButton.setEnabled(true); 
                setEditingMode(false); // Start in view mode
            } else {
                JOptionPane.showMessageDialog(this, "Site details not found for feedback ID: " + feedbackId, 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                resetPanel();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Feedback with ID " + feedbackId + " not found.", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            resetPanel();
        }
    }
    /**
     * Sets the UI elements to editing mode or view mode.
     * @param editing true for editing mode, false for view mode.
     */
    private void setEditingMode(boolean editing) {
        newCommentTextArea.setEnabled(editing);
        confirmButton.setEnabled(editing);
        cancelButton.setEnabled(editing);
        editButton.setEnabled(!editing); // Edit button is enabled only when not editing
        if (editing) {
            newCommentTextArea.requestFocusInWindow();
        }
    }
    /**
     * Resets the panel to its initial state, clearing all loaded data.
     */
    private void resetPanel() {
        currentFeedbackId = -1;
        currentSiteId = -1;
        siteInfoLabel.setText("Please select a feedback to edit.");
        originalCommentLabel.setText("Original Comment: ");
        newCommentTextArea.setText("");
        setEditingMode(false);
        editButton.setEnabled(false); // No feedback loaded, so cannot edit
    }
    /**
     * ActionListener for the "Edit Comment" button.
     * Enables the text area and 'Confirm'/'Cancel' buttons.
     */
    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentFeedbackId != -1) {
                setEditingMode(true);
                JOptionPane.showMessageDialog(CommentEditorPanel.this, 
                                            "Now you can edit the comment in the text area below.", 
                                            "Edit Mode Activated", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(CommentEditorPanel.this, 
                                            "Please load a feedback entry first.", 
                                            "No Feedback Selected", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    /**
     * ActionListener for the "Confirm Change" button.
     * Validates the new comment, asks for confirmation, and attempts to update the comment
     * via the ETourServer. Handles success and error messages.
     * This method now uses SwingWorker to perform the update on a background thread,
     * keeping the UI responsive during simulated network operations.
     */
    private class ConfirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newComment = newCommentTextArea.getText().trim();
            // 2. Verify data entered and asks for confirmation
            if (newComment.isEmpty()) {
                // Invalid data - activate "Errored" use case
                JOptionPane.showMessageDialog(CommentEditorPanel.this, 
                                            "Comment cannot be empty.", 
                                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (newComment.length() < 10) { // Example validation: minimum length
                JOptionPane.showMessageDialog(CommentEditorPanel.this,
                        "Comment is too short. Minimum 10 characters.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (newComment.length() > 500) { // Example validation: maximum length
                JOptionPane.showMessageDialog(CommentEditorPanel.this,
                        "Comment is too long. Maximum 500 characters.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirmation = JOptionPane.showConfirmDialog(CommentEditorPanel.this,
                                                            "Are you sure you want to change the comment to:\n\"" + newComment + "\"",
                                                            "Confirm Comment Change",
                                                            JOptionPane.YES_NO_OPTION);
            // 3. Confirm the change of the comment
            if (confirmation == JOptionPane.YES_OPTION) {
                // Disable UI elements to indicate processing and prevent further interaction
                confirmButton.setEnabled(false);
                cancelButton.setEnabled(false);
                newCommentTextArea.setEnabled(false);
                editButton.setEnabled(false); // Ensure edit button is also disabled during async operation
                // Use SwingWorker to perform the network operation on a background thread
                new SwingWorker<Boolean, Void>() {
                    private final String commentToUpdate = newComment; // Capture new comment value
                    private Exception backgroundException = null; // To store any exceptions from background thread
                    @Override
                    protected Boolean doInBackground() {
                        try {
                            // Simulate network interruption with a 10% chance
                            if (Math.random() < 0.1) {
                                throw new RuntimeException("Simulated connection interruption to ETOUR server.");
                            }
                            // 4. Edit commentary on selected feedback
                            return etourServer.updateComment(currentFeedbackId, commentToUpdate);
                        } catch (Exception ex) {
                            // Catch any exception from the simulated server or network
                            backgroundException = ex;
                            return false; // Indicate failure
                        }
                    }
                    @Override
                    protected void done() {
                        // This method is executed on the Event Dispatch Thread (EDT)
                        // after doInBackground() completes, whether successfully or with an exception.
                        try {
                            if (backgroundException != null) {
                                // An exception occurred in doInBackground (e.g., simulated connection error)
                                JOptionPane.showMessageDialog(CommentEditorPanel.this,
                                                            "Failed to update comment: " + backgroundException.getMessage() +
                                                            "\nPlease check your connection or try again later.",
                                                            "Connection Error", JOptionPane.ERROR_MESSAGE);
                                setEditingMode(true); // Re-enable editing for the user to try again
                            } else {
                                // Background task completed without an exception
                                boolean success = get(); // Get the return value from doInBackground
                                if (success) {
                                    // Exit conditions: The system shall notify the alterations of the comment
                                    JOptionPane.showMessageDialog(CommentEditorPanel.this,
                                                                "Comment successfully updated!",
                                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                                    setEditingMode(false); // Go back to view mode
                                    // Reload feedback to show the updated original comment
                                    loadFeedback(currentFeedbackId);
                                } else {
                                    // etourServer.updateComment returned false (e.g., feedback not found)
                                    JOptionPane.showMessageDialog(CommentEditorPanel.this,
                                                                "Failed to update comment. Feedback not found or internal server error.",
                                                                "Update Failed", JOptionPane.ERROR_MESSAGE);
                                    setEditingMode(true); // Re-enable editing for the user to try again
                                }
                            }
                        } catch (InterruptedException | ExecutionException ex) {
                            // Handle exceptions that might occur while retrieving the result (e.g., if cancelled)
                            JOptionPane.showMessageDialog(CommentEditorPanel.this,
                                                        "An unexpected error occurred during the update process: " + ex.getMessage(),
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                            setEditingMode(true); // Re-enable editing
                        }
                    }
                }.execute(); // Start the SwingWorker on a background thread
            }
        }
    }
    /**
     * ActionListener for the "Cancel" button.
     * Reverts to the original comment and disables editing mode.
     */
    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Reload the original comment to the text area
            Optional<Feedback> feedbackOpt = etourServer.getFeedbackById(currentFeedbackId);
            feedbackOpt.ifPresent(feedback -> newCommentTextArea.setText(feedback.getComment()));
            setEditingMode(false);
            JOptionPane.showMessageDialog(CommentEditorPanel.this, 
                                            "Comment editing cancelled. No changes were made.", 
                                            "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}