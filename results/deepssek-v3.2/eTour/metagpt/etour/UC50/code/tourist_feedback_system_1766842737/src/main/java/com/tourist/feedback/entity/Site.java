package com.tourist.feedback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 站点（景点）实体类
 * 对应数据库中的sites表，存储旅游景点的基本信息
 * 包含景点名称、描述、位置等核心字段
 */
@Entity
@Table(name = "sites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Site {
    
    /**
     * 站点唯一标识符
     * 使用自增主键策略，确保每条记录都有唯一ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 站点名称，必须唯一且不为空
     * 长度限制在2-100个字符之间，确保名称合理
     */
    @NotBlank(message = "站点名称不能为空")
    @Size(min = 2, max = 100, message = "站点名称长度必须在2-100个字符之间")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /**
     * 站点详细描述
     * 使用TEXT类型存储较长的描述信息
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * 站点地理位置
     * 存储具体的地址信息，最大长度为255个字符
     */
    @NotBlank(message = "位置信息不能为空")
    @Size(max = 255, message = "位置信息长度不能超过255个字符")
    @Column(name = "location", nullable = false)
    private String location;

    /**
     * 站点类型（如：历史遗迹、自然景观、博物馆等）
     * 用于分类和筛选
     */
    @Column(name = "category")
    private String category;

    /**
     * 站点创建时间
     * 默认值为当前时间戳，记录数据创建时间
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 站点图片URL
     * 存储站点图片的访问路径
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 站点平均评分
     * 根据所有用户的反馈评分计算得出
     */
    @Column(name = "average_rating", precision = 3, scale = 2)
    private Double averageRating;

    /**
     * 站点访问次数统计
     * 记录有多少用户访问过该站点
     */
    @Column(name = "visit_count", nullable = false)
    private Integer visitCount = 0;

    /**
     * 与反馈记录的一对多关系
     * 一个站点可以有多个用户反馈
     * 使用懒加载优化查询性能
     */
    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    /**
     * 实体保存前的预处理方法
     * 自动设置创建时间，确保数据一致性
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        // 如果平均评分为null，初始化为0.0
        if (this.averageRating == null) {
            this.averageRating = 0.0;
        }
        // 如果访问次数为null，初始化为0
        if (this.visitCount == null) {
            this.visitCount = 0;
        }
    }

    /**
     * 计算平均评分
     * 当有新的反馈时调用此方法更新平均评分
     * 此方法应在Service层调用，保证业务逻辑一致性
     */
    public void updateAverageRating() {
        if (feedbacks == null || feedbacks.isEmpty()) {
            this.averageRating = 0.0;
            return;
        }
        
        double sum = feedbacks.stream()
                .filter(feedback -> feedback.getRating() != null)
                .mapToInt(Feedback::getRating)
                .sum();
        
        long count = feedbacks.stream()
                .filter(feedback -> feedback.getRating() != null)
                .count();
        
        if (count > 0) {
            this.averageRating = sum / count;
        } else {
            this.averageRating = 0.0;
        }
    }

    /**
     * 增加访问次数
     * 当用户创建对本站点的反馈时调用此方法
     */
    public void incrementVisitCount() {
        if (this.visitCount == null) {
            this.visitCount = 0;
        }
        this.visitCount++;
    }

    /**
     * 自定义toString方法，简化日志输出和调试
     * 只显示核心信息，避免循环引用
     */
    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", averageRating=" + averageRating +
                ", visitCount=" + visitCount +
                '}';
    }
}