package com.example.dto;

import com.example.model.Feedback;
import java.util.Date;

/**
 * Data transfer object for feedback.
 */
public class FeedbackDto {
    private String feedbackId;
    private String siteId;
    private String userId;
    private String content;
    private Date createdAt;

    public FeedbackDto(String feedbackId, String siteId, String userId, String content, Date createdAt) {
        this.feedbackId = feedbackId;
        this.siteId = siteId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Converts a Feedback entity to a FeedbackDto.
     * @param feedback the Feedback entity.
     * @return the corresponding FeedbackDto.
     */
    public static FeedbackDto fromFeedback(Feedback feedback) {
        return new FeedbackDto(
            feedback.getId(),
            feedback.getSiteId(),
            feedback.getUserId(),
            feedback.getContent(),
            feedback.getCreatedAt()
        );
    }
}