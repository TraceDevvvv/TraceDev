package com.example;

/**
 * Data Transfer Object (DTO) for displaying Feedback information.
 * This class is used to transfer a simplified view of Feedback data,
 * potentially including related information like site name, to the UI.
 */
public class FeedbackDTO {
    public String id;
    public String siteName; // Derived from Site entity
    public String comment;
    public String status;

    /**
     * Constructs a new FeedbackDTO.
     * @param id The unique identifier of the feedback.
     * @param siteName The name of the site associated with this feedback.
     * @param comment The comment text of the feedback.
     * @param status The current status of the feedback.
     */
    public FeedbackDTO(String id, String siteName, String comment, String status) {
        this.id = id;
        this.siteName = siteName;
        this.comment = comment;
        this.status = status;
    }
}