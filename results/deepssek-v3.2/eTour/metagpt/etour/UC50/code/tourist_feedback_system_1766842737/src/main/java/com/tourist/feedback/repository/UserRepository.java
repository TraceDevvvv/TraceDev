package com.tourist.feedback.repository;

import com.tourist.feedback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户数据访问层接口
 * 扩展Spring Data JPA的JpaRepository接口，提供用户相关的数据访问方法
 * 包含了用户认证、查询和管理的核心数据库操作
 * 使用Spring Data JPA的命名查询和自定义查询优化性能
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 根据用户名查找用户
     * 用于用户登录时的用户名验证
     * 注意：用户名在数据库中是唯一的，因此最多返回一个结果
     * 
     * @param username 用户名
     * @return 用户Optional对象，如果不存在则返回空Optional
     */
    Optional<User> findByUsername(String username);
    
    /**
     * 根据邮箱查找用户
     * 用于用户注册时的邮箱唯一性验证
     * 邮箱在数据库中也是唯一的，确保每个用户有唯一的邮箱地址
     * 
     * @param email 用户邮箱
     * @return 用户Optional对象，如果不存在则返回空Optional
     */
    Optional<User> findByEmail(String email);
    
    /**
     * 检查用户名是否已存在
     * 用于用户注册时的用户名重复检查
     * 使用exists查询比find查询更高效，只返回布尔值而不返回完整对象
     * 
     * @param username 要检查的用户名
     * @return true表示用户名已存在，false表示可以注册
     */
    boolean existsByUsername(String username);
    
    /**
     * 检查邮箱是否已存在
     * 用于用户注册时的邮箱重复检查
     * 防止同一个邮箱注册多个账号
     * 
     * @param email 要检查的邮箱
     * @return true表示邮箱已注册，false表示可以注册
     */
    boolean existsByEmail(String email);
    
    /**
     * 根据用户ID和激活状态查找用户
     * 用于验证用户状态（是否被禁用）
     * 结合用户ID和状态进行精确查询，确保用户状态一致性
     * 
     * @param id 用户ID
     * @param isActive 用户激活状态（true:激活，false:禁用）
     * @return 符合条件的用户Optional对象
     */
    Optional<User> findByIdAndIsActive(Long id, Boolean isActive);
    
    /**
     * 根据用户名和密码哈希查找用户
     * 用于用户登录时的身份验证
     * 注意：实际应用中密码验证应在服务层进行，此方法主要用于特殊情况
     * 
     * @param username 用户名
     * @param passwordHash 密码哈希值
     * @return 用户Optional对象
     */
    Optional<User> findByUsernameAndPasswordHash(String username, String passwordHash);
    
    /**
     * 自定义JPQL查询：查找有反馈记录的用户
     * 用于数据统计和用户分析
     * 使用JOIN FETCH优化查询性能，避免N+1查询问题
     * 
     * @param minFeedbackCount 最小反馈数量
     * @return 用户列表
     */
    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.feedbacks f WHERE SIZE(u.feedbacks) >= :minFeedbackCount")
    java.util.List<User> findUsersWithMinFeedbackCount(@Param("minFeedbackCount") int minFeedbackCount);
    
    /**
     * 自定义原生SQL查询：查找最近活跃的用户
     * 用于系统监控和用户行为分析
     * 直接使用原生SQL可以优化复杂查询的性能
     * 
     * @param days 最近几天内
     * @return 用户ID列表
     */
    @Query(value = "SELECT u.id FROM users u " +
                  "INNER JOIN feedbacks f ON u.id = f.user_id " +
                  "WHERE f.created_at >= DATE_SUB(NOW(), INTERVAL :days DAY) " +
                  "GROUP BY u.id " +
                  "HAVING COUNT(f.id) > 0 " +
                  "ORDER BY MAX(f.created_at) DESC", 
           nativeQuery = true)
    java.util.List<Long> findActiveUserIdsLastNDays(@Param("days") int days);
    
    /**
     * 统计系统中的用户总数
     * 使用count查询比获取所有用户再计数的性能更好
     * 可以添加状态筛选条件统计不同状态的用户数量
     * 
     * @param isActive 用户状态筛选条件，null表示统计所有用户
     * @return 用户数量
     */
    long countByIsActive(Boolean isActive);
    
    /**
     * 根据用户名前缀搜索用户（忽略大小写）
     * 用于管理员搜索用户时的模糊查询
     * 使用LIKE和LOWER函数实现不区分大小写的模糊匹配
     * 
     * @param usernamePrefix 用户名前缀
     * @return 匹配用户名的用户列表
     */
    java.util.List<User> findByUsernameStartingWithIgnoreCase(String usernamePrefix);
}