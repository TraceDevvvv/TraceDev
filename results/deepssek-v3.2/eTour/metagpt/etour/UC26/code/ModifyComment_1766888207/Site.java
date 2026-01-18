import java.util.ArrayList;
import java.util.List;

/**
 * Site类表示一个站点，包含站点ID、名称和该站点的反馈列表。
 * 用于模拟站点数据，支持从SearchSite用例获取站点列表。
 */
public class Site {
    private int siteId;
    private String siteName;
    private List<Feedback> feedbacks;

    /**
     * 构造函数，初始化站点。
     * @param siteId 站点唯一标识
     * @param siteName 站点名称
     */
    public Site(int siteId, String siteName) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.feedbacks = new ArrayList<>();
    }

    /**
     * 获取站点ID。
     * @return 站点ID
     */
    public int getSiteId() {
        return siteId;
    }

    /**
     * 设置站点ID。
     * @param siteId 新的站点ID
     */
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取站点名称。
     * @return 站点名称
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * 设置站点名称。
     * @param siteName 新的站点名称
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * 获取该站点的所有反馈列表。
     * @return 反馈列表
     */
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    /**
     * 向站点添加一个新的反馈。
     * @param feedback 要添加的反馈对象
     */
    public void addFeedback(Feedback feedback) {
        if (feedback != null) {
            feedbacks.add(feedback);
        }
    }

    /**
     * 根据反馈ID查找反馈。
     * @param feedbackId 要查找的反馈ID
     * @return 如果找到则返回反馈对象，否则返回null
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
     * 获取站点信息字符串表示。
     * @return 站点信息字符串
     */
    @Override
    public String toString() {
        return "Site [ID=" + siteId + ", Name=" + siteName + ", Feedbacks=" + feedbacks.size() + "]";
    }
}