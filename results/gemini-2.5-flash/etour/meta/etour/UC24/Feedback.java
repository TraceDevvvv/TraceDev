package ViewFeedback_1765890483;

/**
 * Represents a feedback entry for a specific site.
 * Each feedback includes the site ID it belongs to, the author, and the comment.
 */
public class Feedback {
    private String siteId;
    private String author;
    private String comment;

    /**
     * Constructs a new Feedback object.
     *
     * @param siteId  The ID of the site this feedback is for.
     * @param author  The author of the feedback.
     * @param comment The feedback comment.
     */
    public Feedback(String siteId, String author, String comment) {
        this.siteId = siteId;
        this.author = author;
        this.comment = comment;
    }

    /**
     * Returns the ID of the site this feedback is associated with.
     *
     * @return The site ID.
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Returns the author of the feedback.
     *
     * @return The feedback author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the comment of the feedback.
     *
     * @return The feedback comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns a string representation of the Feedback object.
     *
     * @return A string containing the site ID, author, and comment.
     */
    @Override
    public String toString() {
        return "  Feedback [Site ID: " + siteId + ", Author: " + author + ", Comment: \"" + comment + "\"]";
    }
}