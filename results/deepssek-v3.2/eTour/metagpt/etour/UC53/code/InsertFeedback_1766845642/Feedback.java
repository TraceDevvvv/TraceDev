import java.time.LocalDateTime;

/**
 * Feedback实体类，表示游客对站点的反馈
 * 包含评分、评论、时间戳、游客ID和站点ID等属性
 */
public class Feedback {
    private String feedbackId;
    private String touristId;
    private String siteId;
    private int rating; // 评分，范围1-5
    private String comment;
    private LocalDateTime timestamp;
    
    /**
     * 构造函数
     * @param feedbackId 反馈ID
     * @param touristId 游客ID
     * @param siteId 站点ID
     * @param rating 评分（1-5）
     * @param comment 评论
     */
    public Feedback(String feedbackId, String touristId, String siteId, int rating, String comment) {
        this.feedbackId = feedbackId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * 无参构造函数
     */
    public Feedback() {
        this.timestamp = LocalDateTime.now();
    }
    
    // Getter和Setter方法
    
    public String getFeedbackId() {
        return feedbackId;
    }
    
    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }
    
    public String getTouristId() {
        return touristId;
    }
    
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
    
    public String getSiteId() {
        return siteId;
    }
    
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * 验证反馈数据是否有效
     * @return 如果评分在1-5之间且评论不为空，则返回true
     */
    public boolean isValid() {
        return rating >= 1 && rating <= 5 && comment != null && !comment.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId='" + feedbackId + '\'' +
                ", touristId='" + touristId + '\'' +
                ", siteId='" + siteId + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}