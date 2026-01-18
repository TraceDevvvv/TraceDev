package com.example;

import java.util.List;

/**
 * Data Transfer Object containing list of feedbacks for a site.
 */
public class FeedbackListDTO {
    private List<FeedbackDTO> feedbacks;
    private String siteName;

    public FeedbackListDTO(List<FeedbackDTO> feedbacks, String siteName) {
        this.feedbacks = feedbacks;
        this.siteName = siteName;
    }

    public List<FeedbackDTO> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackDTO> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}