'''
This is the main GUI class for the "MODIFICACOMMENTO" use case.
It orchestrates the flow of events for editing a feedback comment,
utilizing other GUI components and the backend system.
This version removes System.exit() calls for a more continuous user experience,
allowing the operator to perform multiple actions or return to a main menu.
It also includes error handling for simulated connection failures.
'''
package com.chatdev.gui;
import com.chatdev.gui.components.CommentEditForm;
import com.chatdev.gui.components.FeedbackSelectionFrame;
import com.chatdev.gui.components.SiteSelectionFrame;
import com.chatdev.gui.utils.DialogUtils;
import com.chatdev.models.Feedback;
import com.chatdev.models.Site;
import com.chatdev.system.AgencyOperatorSystem;
import com.chatdev.system.ConnectionFailedException; // Add this import
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class ModificaCommentoGUI extends JFrame {
    private AgencyOperatorSystem system;
    private Site selectedSite;
    private Feedback selectedFeedback;
    private JPanel mainPanel;
    private JButton startEditingButton;
    /**
     * Constructs the main ModificaCommentoGUI application.
     * Initializes the UI and the backend system.
     */
    public ModificaCommentoGUI() {
        super("ChatDev - Modify Feedback Comment");
        this.system = new AgencyOperatorSystem(); // Initialize the backend system
        // AgencyOperatorSystem.simulateConnectionError = true; // Uncomment to test connection error
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation for the main window
        setSize(400, 200); // Set a reasonable size for the main application window
        setLocationRelativeTo(null); // Center the main window
        // Set up the main panel with a button to start the workflow
        mainPanel = new JPanel(new GridBagLayout());
        startEditingButton = new JButton("Start Modifying Feedback Comment");
        startEditingButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        // Action listener to start the modification flow when the button is pressed
        startEditingButton.addActionListener(e -> startModifyCommentFlow());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Add some padding
        mainPanel.add(startEditingButton, gbc);
        add(mainPanel); // Add the main panel to the frame
        setVisible(true); // Make the main application window visible
    }
    /**
     * Initiates the workflow for modifying a comment.
     * Hides the main window and launches the site selection process.
     * Corresponds to the initial state where the operator is logged in.
     * (Operator conditions: The agency has logged.)
     */
    private void startModifyCommentFlow() {
        this.setVisible(false); // Hide the main window while child frames are active
        try {
            // 1. View the list of sites
            List<Site> sites = system.getSites();
            if (sites.isEmpty()) {
                DialogUtils.showMessage(this, "No sites available to select from.", "No Sites");
                this.setVisible(true); // Show main window again if no sites
                return;
            }
            // Launch the SiteSelectionFrame
            SwingUtilities.invokeLater(() -> {
                SiteSelectionFrame siteFrame = new SiteSelectionFrame(sites, this::onSiteSelected, this::onChildFlowCancelled);
                siteFrame.setVisible(true);
            });
        } catch (ConnectionFailedException ex) {
            DialogUtils.showError(this, "Connection to server interrupted: " + ex.getMessage(), "Connection Error");
            this.setVisible(true); // Bring back the main window
        }
    }
    /**
     * Callback method executed when a site is selected from the SiteSelectionFrame.
     * Corresponds to part of Use Case step 1 and leads to step 2.
     * @param site The selected Site object.
     */
    private void onSiteSelected(Site site) {
        this.selectedSite = site;
        DialogUtils.showMessage(this, "Site selected: " + site.getName(), "Site Selected");
        try {
            // 2. Upload feedback issued to that site and displays them in a form.
            List<Feedback> feedbackList = system.getFeedbackForSite(site.getId());
            if (feedbackList.isEmpty()) {
                DialogUtils.showMessage(this, "No feedback available for site: " + site.getName(), "No Feedback");
                this.setVisible(true); // Show main window again if no feedback for selected site
                this.selectedSite = null; // Clear selected site as this path ends here for this particular flow
                return;
            }
            // Launch the FeedbackSelectionFrame
            SwingUtilities.invokeLater(() -> {
                FeedbackSelectionFrame feedbackFrame = new FeedbackSelectionFrame(
                        site.getName(),
                        feedbackList,
                        this::onFeedbackSelected,
                        this::onChildFlowCancelled);
                feedbackFrame.setVisible(true);
            });
        } catch (ConnectionFailedException ex) {
            DialogUtils.showError(this, "Connection to server interrupted: " + ex.getMessage(), "Connection Error");
            this.setVisible(true); // Bring back the main window
            this.selectedSite = null; // Clear selected site because the operation failed
        }
    }
    /**
     * Callback method executed when feedback is selected from the FeedbackSelectionFrame.
     * Corresponds to Use Case step 3 and leads to step 4.
     * @param feedback The selected Feedback object.
     */
    private void onFeedbackSelected(Feedback feedback) {
        this.selectedFeedback = feedback;
        DialogUtils.showMessage(this, "Feedback selected for editing: " + feedback.getComment(), "Feedback Selected");
        // 4. Displays a form for editing the comment of feedback selected.
        SwingUtilities.invokeLater(() -> {
            CommentEditForm editForm = new CommentEditForm(
                    feedback,
                    this::onCommentSaved,
                    this::onChildFlowCancelled);
            editForm.setVisible(true);
        });
    }
    /**
     * Callback method executed when the "Save" button is pressed in the CommentEditForm.
     * Corresponds to Use Case step 5, 6, 7, 8 and Exit Conditions.
     * Handles validation, update, and feedback to the user.
     * @param feedbackId The ID of the feedback being edited.
     * @param newComment The new comment text provided by the user.
     */
    private void onCommentSaved(int feedbackId, String newComment) {
        // 6. Verify the data entered
        // CommentEditForm already validates for non-empty. This is an additional check.
        if (!system.validateComment(newComment)) {
            // This case should ideally not be reached if CommentEditForm's validation is robust.
            // If it does, inform the user and allow them to retry. Do not return to main frame.
            DialogUtils.showError(null, "Invalid comment: Comment cannot be empty or just whitespace.", "Validation Error");
            // The CommentEditForm should still be open, allowing the user to correct.
            // We do not manage the form's state here, as it calls this method after its own validation.
            return;
        }
        // 7. Confirmation (handled by DialogUtils.showConfirmation in CommentEditForm before this callback is invoked).
        try {
            // 8. Remember the comment changed.
            boolean success = system.updateFeedbackComment(feedbackId, newComment);
            if (success) {
                DialogUtils.showMessage(this,
                        "Comment for Feedback ID " + feedbackId + " has been successfully updated!\nNew Comment: \"" + newComment + "\"",
                        "Comment Updated");
            } else {
                DialogUtils.showError(this,
                        "Failed to update comment for Feedback ID: " + feedbackId + ". Please try again.",
                        "Update Error");
                // If update failed (e.g., feedback not found, DB error), an operator might want to retry
                // or go back. For now, we return to the main menu.
            }
        } catch (ConnectionFailedException ex) {
            DialogUtils.showError(this, "Connection to server interrupted: " + ex.getMessage(), "Connection Error");
        } finally {
            // Reset selected items and show the main window, ready for a new operation.
            this.selectedSite = null;
            this.selectedFeedback = null;
            this.setVisible(true); // Ensure main application window is visible regardless of success/failure
        }
    }
    /**
     * Generic callback for when an operation is cancelled at any stage of the child flow.
     * Corresponds to an Exit Condition: Operator Agency cancels the operation.
     * This method ensures the main application window is shown again.
     */
    private void onChildFlowCancelled() {
        DialogUtils.showMessage(this, "Operation cancelled by Agency Operator.", "Operation Cancelled");
        // Reset selected items if the flow was interrupted
        this.selectedSite = null;
        this.selectedFeedback = null;
        this.setVisible(true); // Show the main application window again
    }
    /**
     * Main method to run the application.
     * Ensures GUI updates are done on the Event Dispatch Thread.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(ModificaCommentoGUI::new);
    }
}