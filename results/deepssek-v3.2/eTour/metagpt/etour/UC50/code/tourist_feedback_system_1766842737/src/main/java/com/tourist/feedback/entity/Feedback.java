package com.tourist.feedback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * 反馈实体类
 * 对应数据库中的feedbacks表，连接用户和站点
 * 存储用户对特定站点的评分、评论和访问时间等反馈信息
 * 这是ViewVisitedSites用例的核心数据实体
 */
@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    
    /**
     * 反馈唯一标识符
     * 使用自增主键策略，确保每条反馈记录都有唯一ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联的用户ID
     * 使用外键关联到users表，确保用户存在
     */
    @NotNull(message = "用户ID不能为空")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 关联的站点ID
     * 使用外键关联到sites表，确保站点存在
     */
    @NotNull(message = "站点ID不能为空")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    /**
     * 用户评分，范围1-5分
     * 1分表示非常不满意，5分表示非常满意
     */
    @NotNull(message = "评分不能为空")
    @Min(value = 1, message = "评分不能小于1")
    @Max(value = 5, message = "评分不能大于5")
    @Column(name = "rating", nullable = false)
    private Integer rating;

    /**
     * 用户评论内容
     * 使用TEXT类型存储较长的评论，最大长度2000字符
     */
    @Size(max = 2000, message = "评论长度不能超过2000个字符")
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    /**
     * 反馈创建时间
     * 默认值为当前时间戳，记录用户提交反馈的时间
     * 这也是ViewVisitedSites用例中的"访问时间"
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 反馈更新时间
     * 当用户修改反馈时自动更新
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 反馈状态
     * true: 正常显示，false: 已删除或隐藏
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    /**
     * 访问日期（用户实际访问站点的日期）
     * 与created_at不同，created_at是提交反馈的时间
     * 用于ViewVisitedSites用例中的时间排序和筛选
     */
    @Column(name = "visit_date")
    private LocalDateTime visitDate;

    /**
     * 是否推荐给其他游客
     * true: 推荐，false: 不推荐
     */
    @Column(name = "is_recommended")
    private Boolean isRecommended;

    /**
     * 实体保存前的预处理方法
     * 自动设置创建时间，确保数据一致性
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        // 如果访问日期为空，使用创建时间作为默认值
        if (this.visitDate == null) {
            this.visitDate = LocalDateTime.now();
        }
        // 如果推荐状态为空，根据评分设置默认值
        if (this.isRecommended == null && this.rating != null) {
            this.isRecommended = this.rating >= 4; // 4分及以上默认推荐
        }
    }

    /**
     * 实体更新前的预处理方法
     * 自动更新更新时间
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 获取简化的站点信息
     * 用于ViewVisitedSites用例的响应数据，避免返回完整的站点对象
     * 这个方法提高了API响应效率，减少了数据传输量
     */
    public String getSiteName() {
        return site != null ? site.getName() : null;
    }

    /**
     * 获取访问日期的格式化字符串
     * 返回格式为"yyyy-MM-dd"的日期字符串
     * 用于ViewVisitedSites用例的界面显示
     */
    public String getFormattedVisitDate() {
        if (visitDate == null) {
            return null;
        }
        return visitDate.toLocalDate().toString();
    }

    /**
     * 获取反馈创建时间的格式化字符串
     * 返回格式为"yyyy-MM-dd HH:mm:ss"的日期时间字符串
     * 用于日志记录和调试
     */
    public String getFormattedCreatedAt() {
        if (createdAt == null) {
            return null;
        }
        return createdAt.toString();
    }

    /**
     * 验证反馈数据的完整性
     * 检查所有必需字段是否已正确设置
     * 用于业务逻辑层的数据验证
     */
    public boolean isValid() {
        return user != null && 
               site != null && 
               rating != null && 
               rating >= 1 && 
               rating <= 5 && 
               createdAt != null;
    }

    /**
     * 自定义toString方法，避免循环引用
     * 只显示核心信息，不显示关联的完整对象
     * 防止在日志输出时出现无限递归
     */
    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", userId=" + (user != null ? user.getId() : null) +
                ", siteId=" + (site != null ? site.getId() : null) +
                ", rating=" + rating +
                ", visitDate=" + (visitDate != null ? visitDate.toLocalDate() : null) +
                ", isActive=" + isActive +
                '}';
    }
}