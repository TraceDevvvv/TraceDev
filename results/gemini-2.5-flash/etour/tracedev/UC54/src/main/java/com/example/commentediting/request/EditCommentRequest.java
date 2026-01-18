package com.example.commentediting.request;

/**
 * Represents the request object for editing a comment.
 * Contains all necessary data for the comment update process.
 */
public class EditCommentRequest {
    private String commentId;
    private String newText;
    private String touristId;
    private String siteId; // Added to satisfy requirement R3
    private boolean isConfirmed;

    /**
     * Constructs a new EditCommentRequest.
     *
     * @param commentId   The unique identifier of the comment to be edited.
     * @param newText     The new text content for the comment.
     * @param touristId   The ID of the tourist making the request.
     * @param siteId      The ID of the site where the comment was made. (R3)
     * @param isConfirmed A flag indicating if the edit action has been confirmed by the user.
     */
    public EditCommentRequest(String commentId, String newText, String touristId, String siteId, boolean isConfirmed) {
        this.commentId = commentId;
        this.newText = newText;
        this.touristId = touristId;
        this.siteId = siteId;
        this.isConfirmed = isConfirmed;
    }

    /**
     * Checks if the edit action has been confirmed.
     * Added for encapsulation (original + isConfirmed: boolean)
     *
     * @return true if confirmed, false otherwise.
     */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Sets the confirmation status for the edit action.
     * Added for encapsulation (original + isConfirmed: boolean)
     *
     * @param confirmed The confirmation status.
     */
    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getNewText() {
        return newText;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSiteId() {
        return siteId;
    }
}