package com.example;

/**
 * Data Transfer Object for edit feedback comment request.
 */
public class EditFeedbackCommentRequestDTO {
    private String operatorId;
    private String siteId;
    private String feedbackId;
    private String editedComment;

    public EditFeedbackCommentRequestDTO(String operatorId, String siteId,
                                         String feedbackId, String editedComment) {
        this.operatorId = operatorId;
        this.siteId = siteId;
        this.feedbackId = feedbackId;
        this.editedComment = editedComment;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getEditedComment() {
        return editedComment;
    }

    public void setEditedComment(String editedComment) {
        this.editedComment = editedComment;
    }
}