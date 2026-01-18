
package com.etoour.model;

import java.util.List;
import com.etoour.ui.EditFeedbackUI;

/**
 * Tourist class representing a tourist/user in the system.
 */
public class Tourist {
    private String userId;
    private String username;
    private String email;

    public Tourist(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get feedbacks for a specific site.
     * Note: This is a placeholder; in a real implementation,
     * this would query a service or repository.
     */
    public List<Feedback> getSiteFeedback(String siteId) {
        // In a real implementation, this would fetch feedbacks from a database
        // For this example, we return null
        return null;
    }

    /**
     * Edit feedback by calling the UI layer.
     * In a real scenario, this might be triggered via UI.
     */
    public boolean editFeedback(String feedbackId, String newComment) {
        // In a real implementation, this would invoke the UI or controller
        // For this example, we assume the edit is delegated to controller via UI
        // We return a placeholder
        return true;
    }

    /**
     * Sequence diagram message: select feedback to edit.
     */
    public void selectFeedbackToEdit(String feedbackId, EditFeedbackUI ui) {
        // Triggers UI to display existing comment (m2)
        // In sequence diagram, this message is from Tourist to UI.
        // This method would be called by the UI or controller
        // We will just call UI display existing comment
        ui.displayExistingComment(getCurrentFeedbackComment(feedbackId));
    }

    /**
     * Sequence diagram message: enter new comment.
     */
    public void enterNewComment(String newComment, EditFeedbackUI ui) {
        // Sequence message m3: Tourist to UI with new comment
        // The UI can then forward to controller
        ui.receiveNewComment(newComment);
    }

    /**
     * Helper method to get the comment of a feedback (placeholder).
     */
    private String getCurrentFeedbackComment(String feedbackId) {
        return "Existing comment for feedback " + feedbackId;
    }
}
