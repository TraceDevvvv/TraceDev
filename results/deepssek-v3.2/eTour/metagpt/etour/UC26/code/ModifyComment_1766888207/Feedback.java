import java.time.LocalDateTime;

/**
 * Feedback       ，    ID、    、  ID、           。
 *         ，       。
 */
public class Feedback {
    private int feedbackId;
    private String comment;
    private int siteId;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private boolean isModified;

    /**
     *     ，     。
     * @param feedbackId       
     * @param comment     
     * @param siteId     ID
     */
    public Feedback(int feedbackId, String comment, int siteId) {
        this.feedbackId = feedbackId;
        this.comment = comment;
        this.siteId = siteId;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = this.createdAt;
        this.isModified = false;
    }

    /**
     *     ID。
     * @return   ID
     */
    public int getFeedbackId() {
        return feedbackId;
    }

    /**
     *     ID。
     * @param feedbackId     ID
     */
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     *       。
     * @return     
     */
    public String getComment() {
        return comment;
    }

    /**
     *       ，            。
     * @param comment       
     * @return                true，    false
     */
    public boolean setComment(String comment) {
        if (comment == null || comment.trim().isEmpty()) {
            return false; //          
        }
        
        if (!comment.equals(this.comment)) {
            this.comment = comment;
            this.lastModifiedAt = LocalDateTime.now();
            this.isModified = true;
            return true;
        }
        return false; //        
    }

    /**
     *       ID。
     * @return   ID
     */
    public int getSiteId() {
        return siteId;
    }

    /**
     *       ID。
     * @param siteId     ID
     */
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    /**
     *       。
     * @return     
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     *         。
     * @return       
     */
    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    /**
     *            。
     * @return            true，    false
     */
    public boolean isModified() {
        return isModified;
    }

    /**
     *       。
     */
    public void resetModifiedFlag() {
        this.isModified = false;
    }

    /**
     *           。
     * @return                    true，    false
     */
    public boolean isValidComment() {
        if (comment == null || comment.trim().isEmpty()) {
            return false;
        }
        //         1 1000    
        return comment.length() >= 1 && comment.length() <= 1000;
    }

    /**
     *            。
     * @return        
     */
    @Override
    public String toString() {
        return "Feedback [ID=" + feedbackId + 
               ", SiteID=" + siteId + 
               ", Comment=" + (comment.length() > 50 ? comment.substring(0, 47) + "..." : comment) +
               ", Created=" + createdAt + 
               ", Modified=" + lastModifiedAt + 
               ", IsModified=" + isModified + "]";
    }
}