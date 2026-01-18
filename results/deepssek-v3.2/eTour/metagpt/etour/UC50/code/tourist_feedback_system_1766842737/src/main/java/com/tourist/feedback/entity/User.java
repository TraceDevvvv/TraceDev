package com.tourist.feedback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户实体类
 * 对应数据库中的users表，存储游客的基本信息
 * 包含用户名、邮箱、密码哈希等核心字段
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    /**
     * 用户唯一标识符
     * 使用自增主键策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名，必须唯一且不为空
     * 长度限制在2-50个字符之间
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 50, message = "用户名长度必须在2-50个字符之间")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * 用户邮箱，必须唯一且格式正确
     * 用于用户认证和通知
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * 密码哈希值
     * 使用BCrypt加密存储，确保密码安全
     */
    @NotBlank(message = "密码不能为空")
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    /**
     * 用户创建时间
     * 默认值为当前时间戳
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 用户信息最后更新时间
     * 每次更新时自动设置为当前时间
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 用户状态
     * true: 正常状态，false: 禁用状态
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    /**
     * 与反馈记录的一对多关系
     * 一个用户可以有多个反馈记录
     * 使用懒加载优化性能
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    /**
     * 实体保存前的预处理方法
     * 自动设置创建时间和更新时间
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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
     * 自定义toString方法，避免密码信息泄露
     * 只显示用户基本信息，不包含敏感数据
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}