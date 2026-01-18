import java.time.LocalDateTime;

/**
 * Feedback类表示一个反馈，包含反馈ID、评论内容、站点ID、创建时间和最后修改时间。
 * 支持评论编辑功能，并跟踪修改历史。
 */
public class Feedback {
    private int feedbackId;
    private String comment;
    private int siteId;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private boolean isModified;

    /**
     * 构造函数，初始化反馈。
     * @param feedbackId 反馈唯一标识
     * @param comment 评论内容
     * @param siteId 所属站点ID
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
     * 获取反馈ID。
     * @return 反馈ID
     */
    public int getFeedbackId() {
        return feedbackId;
    }

    /**
     * 设置反馈ID。
     * @param feedbackId 新的反馈ID
     */
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * 获取评论内容。
     * @return 评论内容
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置评论内容，并更新修改时间和修改标志。
     * @param comment 新的评论内容
     * @return 如果评论内容有效且已更改则返回true，否则返回false
     */
    public boolean setComment(String comment) {
        if (comment == null || comment.trim().isEmpty()) {
            return false; // 评论不能为空或空白
        }
        
        if (!comment.equals(this.comment)) {
            this.comment = comment;
            this.lastModifiedAt = LocalDateTime.now();
            this.isModified = true;
            return true;
        }
        return false; // 评论内容未改变
    }

    /**
     * 获取所属站点ID。
     * @return 站点ID
     */
    public int getSiteId() {
        return siteId;
    }

    /**
     * 设置所属站点ID。
     * @param siteId 新的站点ID
     */
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取创建时间。
     * @return 创建时间
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取最后修改时间。
     * @return 最后修改时间
     */
    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    /**
     * 检查反馈是否已被修改过。
     * @return 如果评论被修改过则返回true，否则返回false
     */
    public boolean isModified() {
        return isModified;
    }

    /**
     * 重置修改标志。
     */
    public void resetModifiedFlag() {
        this.isModified = false;
    }

    /**
     * 验证评论内容是否有效。
     * @return 如果评论不为空且长度在合理范围内则返回true，否则返回false
     */
    public boolean isValidComment() {
        if (comment == null || comment.trim().isEmpty()) {
            return false;
        }
        // 假设评论长度应在1到1000字符之间
        return comment.length() >= 1 && comment.length() <= 1000;
    }

    /**
     * 获取反馈信息字符串表示。
     * @return 反馈信息字符串
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