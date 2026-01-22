import java.util.ArrayList;
import java.util.List;

/**
 * Site       ，    ID、           。
 *         ，   SearchSite        。
 */
public class Site {
    private int siteId;
    private String siteName;
    private List<Feedback> feedbacks;

    /**
     *     ，     。
     * @param siteId       
     * @param siteName     
     */
    public Site(int siteId, String siteName) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.feedbacks = new ArrayList<>();
    }

    /**
     *     ID。
     * @return   ID
     */
    public int getSiteId() {
        return siteId;
    }

    /**
     *     ID。
     * @param siteId     ID
     */
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    /**
     *       。
     * @return     
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     *       。
     * @param siteName       
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     *             。
     * @return     
     */
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    /**
     *            。
     * @param feedback         
     */
    public void addFeedback(Feedback feedback) {
        if (feedback != null) {
            feedbacks.add(feedback);
        }
    }

    /**
     *     ID    。
     * @param feedbackId       ID
     * @return            ，    null
     */
    public Feedback findFeedbackById(int feedbackId) {
        for (Feedback feedback : feedbacks) {
            if (feedback.getFeedbackId() == feedbackId) {
                return feedback;
            }
        }
        return null;
    }

    /**
     *            。
     * @return        
     */
    @Override
    public String toString() {
        return "Site [ID=" + siteId + ", Name=" + siteName + ", Feedbacks=" + feedbacks.size() + "]";
    }
}