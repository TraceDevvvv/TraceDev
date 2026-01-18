package com.tourist.feedback.repository;

import com.tourist.feedback.entity.Feedback;
import com.tourist.feedback.entity.Site;
import com.tourist.feedback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 反馈数据访问层接口
 * 扩展Spring Data JPA的JpaRepository接口，提供反馈相关的数据访问方法
 * 包含了ViewVisitedSites用例所需的核心查询方法，如查找用户反馈、按条件筛选等
 * 这是实现ViewVisitedSites功能的关键数据访问组件
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
    /**
     * 根据用户ID查找所有反馈记录
     * ViewVisitedSites用例的核心查询方法：查找指定用户的所有反馈记录
     * 使用JOIN FETCH优化查询，一次性加载关联的站点信息，避免N+1查询问题
     * 
     * @param userId 用户ID
     * @return 用户的所有反馈记录列表，按访问时间倒序排列
     */
    @Query("SELECT f FROM Feedback f JOIN FETCH f.site WHERE f.user.id = :userId AND f.isActive = true ORDER BY f.visitDate DESC")
    List<Feedback> findByUserId(@Param("userId") Long userId);
    
    /**
     * 根据用户ID和站点ID查找反馈记录
     * 用于检查用户是否已对特定站点发布过反馈，避免重复反馈
     * 
     * @param userId 用户ID
     * @param siteId 站点ID
     * @return 符合条件的反馈记录Optional对象
     */
    Optional<Feedback> findByUserIdAndSiteId(Long userId, Long siteId);
    
    /**
     * 根据用户ID和站点ID查找活跃的反馈记录
     * 用于ViewVisitedSites用例的验证，确保只返回有效的反馈记录
     * 
     * @param userId 用户ID
     * @param siteId 站点ID
     * @param isActive 反馈状态
     * @return 符合条件的反馈记录Optional对象
     */
    Optional<Feedback> findByUserIdAndSiteIdAndIsActive(Long userId, Long siteId, Boolean isActive);
    
    /**
     * 根据用户ID查找反馈记录（分页版本）
     * ViewVisitedSites用例的分页查询方法，支持大数据量的分页显示
     * 使用JOIN FETCH优化性能，确保查询效率
     * 
     * @param userId 用户ID
     * @param isActive 反馈状态
     * @return 用户的反馈记录列表
     */
    List<Feedback> findByUserIdAndIsActive(Long userId, Boolean isActive);
    
    /**
     * 根据站点ID查找所有反馈记录
     * 用于站点详情页面，显示所有用户对该站点的评价
     * 
     * @param siteId 站点ID
     * @return 站点的所有反馈记录列表，按创建时间倒序排列
     */
    @Query("SELECT f FROM Feedback f JOIN FETCH f.user WHERE f.site.id = :siteId AND f.isActive = true ORDER BY f.createdAt DESC")
    List<Feedback> findBySiteId(@Param("siteId") Long siteId);
    
    /**
     * 根据评分范围查找反馈记录
     * 用于ViewVisitedSites用例的筛选功能，用户可以按评分筛选自己的反馈记录
     * 使用BETWEEN查询优化性能，适合范围查询
     * 
     * @param userId 用户ID
     * @param minRating 最低评分（包含）
     * @param maxRating 最高评分（包含）
     * @return 评分在指定范围内的反馈记录列表
     */
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.rating BETWEEN :minRating AND :maxRating AND f.isActive = true ORDER BY f.visitDate DESC")
    List<Feedback> findByUserIdAndRatingBetween(@Param("userId") Long userId, 
                                                @Param("minRating") Integer minRating, 
                                                @Param("maxRating") Integer maxRating);
    
    /**
     * 根据访问时间范围查找反馈记录
     * 用于ViewVisitedSites用例的时间筛选功能，用户可以按时间段筛选访问记录
     * 例如：查找2024年1月访问的所有站点
     * 
     * @param userId 用户ID
     * @param startDate 开始时间（包含）
     * @param endDate 结束时间（包含）
     * @return 访问时间在指定范围内的反馈记录列表
     */
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.visitDate BETWEEN :startDate AND :endDate AND f.isActive = true ORDER BY f.visitDate DESC")
    List<Feedback> findByUserIdAndVisitDateBetween(@Param("userId") Long userId, 
                                                   @Param("startDate") LocalDateTime startDate, 
                                                   @Param("endDate") LocalDateTime endDate);
    
    /**
     * 查找用户最近访问的反馈记录（限制数量）
     * 用于ViewVisitedSites用例的首页显示，展示用户最近的访问记录
     * 使用原生SQL优化性能，直接限制返回结果数量
     * 
     * @param userId 用户ID
     * @param limit 返回结果数量限制
     * @return 用户最近访问的反馈记录列表
     */
    @Query(value = "SELECT f.* FROM feedbacks f " +
                  "WHERE f.user_id = :userId AND f.is_active = true " +
                  "ORDER BY f.visit_date DESC " +
                  "LIMIT :limit", 
           nativeQuery = true)
    List<Feedback> findRecentFeedbacksByUser(@Param("userId") Long userId, 
                                             @Param("limit") int limit);
    
    /**
     * 查找用户推荐过的站点反馈记录
     * 用于ViewVisitedSites用例的筛选功能，用户可以查看自己推荐过的站点
     * 
     * @param userId 用户ID
     * @param isRecommended 是否推荐
     * @return 用户推荐过的站点反馈记录列表
     */
    List<Feedback> findByUserIdAndIsRecommendedAndIsActive(Long userId, Boolean isRecommended, Boolean isActive);
    
    /**
     * 统计用户的总反馈数量
     * 用于ViewVisitedSites用例的数据统计，显示用户的总访问次数
     * 使用count查询优化性能，避免获取所有记录再计数
     * 
     * @param userId 用户ID
     * @return 用户的反馈记录总数
     */
    long countByUserIdAndIsActive(Long userId, Boolean isActive);
    
    /**
     * 统计用户的平均评分
     * 用于用户个人中心的数据展示，显示用户的平均评分水平
     * 使用原生SQL直接计算平均值，避免在Java中计算
     * 
     * @param userId 用户ID
     * @return 用户的平均评分
     */
    @Query(value = "SELECT AVG(rating) FROM feedbacks WHERE user_id = :userId AND is_active = true", 
           nativeQuery = true)
    Double calculateAverageRatingByUser(@Param("userId") Long userId);
    
    /**
     * 查找有评论内容的反馈记录
     * 用于ViewVisitedSites用例的筛选功能，用户可以查看自己有详细评论的反馈
     * 使用IS NOT NULL和LENGTH>0确保评论内容有效
     * 
     * @param userId 用户ID
     * @return 有评论内容的反馈记录列表
     */
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.comment IS NOT NULL AND LENGTH(f.comment) > 0 AND f.isActive = true ORDER BY f.visitDate DESC")
    List<Feedback> findFeedbacksWithCommentsByUser(@Param("userId") Long userId);
    
    /**
     * 根据多个条件组合查询反馈记录
     * 用于ViewVisitedSites用例的高级搜索功能，支持多条件组合筛选
     * 使用动态查询构建，可以在Service层根据条件动态调用
     * 
     * @param userId 用户ID（必需）
     * @param siteId 站点ID（可选）
     * @param minRating 最低评分（可选）
     * @param maxRating 最高评分（可选）
     * @param startDate 开始时间（可选）
     * @param endDate 结束时间（可选）
     * @param isRecommended 是否推荐（可选）
     * @return 符合条件的反馈记录列表
     */
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.isActive = true " +
           "AND (:siteId IS NULL OR f.site.id = :siteId) " +
           "AND (:minRating IS NULL OR f.rating >= :minRating) " +
           "AND (:maxRating IS NULL OR f.rating <= :maxRating) " +
           "AND (:startDate IS NULL OR f.visitDate >= :startDate) " +
           "AND (:endDate IS NULL OR f.visitDate <= :endDate) " +
           "AND (:isRecommended IS NULL OR f.isRecommended = :isRecommended) " +
           "ORDER BY f.visitDate DESC")
    List<Feedback> searchFeedbacksByCriteria(@Param("userId") Long userId,
                                            @Param("siteId") Long siteId,
                                            @Param("minRating") Integer minRating,
                                            @Param("maxRating") Integer maxRating,
                                            @Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate,
                                            @Param("isRecommended") Boolean isRecommended);
    
    /**
     * 查找用户访问过的站点类型统计
     * 用于ViewVisitedSites用例的数据分析，显示用户的旅行偏好
     * 返回用户访问过的不同站点类型的数量统计
     * 
     * @param userId 用户ID
     * @return 包含站点类型和数量的对象列表
     */
    @Query(value = "SELECT s.category, COUNT(f.id) as count FROM feedbacks f " +
                  "INNER JOIN sites s ON f.site_id = s.id " +
                  "WHERE f.user_id = :userId AND f.is_active = true " +
                  "GROUP BY s.category " +
                  "ORDER BY count DESC", 
           nativeQuery = true)
    List<Object[]> findSiteCategoryStatsByUser(@Param("userId") Long userId);
    
    /**
     * 查找用户每年的访问统计
     * 用于ViewVisitedSites用例的时间分析，显示用户每年的旅行频率
     * 按年份分组统计访问次数
     * 
     * @param userId 用户ID
     * @return 包含年份和访问次数的对象列表
     */
    @Query(value = "SELECT YEAR(visit_date) as year, COUNT(id) as count FROM feedbacks " +
                  "WHERE user_id = :userId AND is_active = true " +
                  "GROUP BY YEAR(visit_date) " +
                  "ORDER BY year DESC", 
           nativeQuery = true)
    List<Object[]> findYearlyVisitStatsByUser(@Param("userId") Long userId);
    
    /**
     * 检查用户是否已对指定站点发布过反馈
     * 用于创建新反馈前的验证，避免重复反馈
     * 使用exists查询优化性能，只返回布尔值
     * 
     * @param userId 用户ID
     * @param siteId 站点ID
     * @return true表示已存在反馈，false表示可以创建新反馈
     */
    boolean existsByUserIdAndSiteIdAndIsActive(Long userId, Long siteId, Boolean isActive);
    
    /**
     * 批量更新反馈状态
     * 用于批量删除或恢复反馈记录
     * 使用原生SQL提高批量更新性能
     * 
     * @param userId 用户ID
     * @param isActive 新的状态值
     * @return 更新的记录数
     */
    @Query(value = "UPDATE feedbacks SET is_active = :isActive, updated_at = NOW() " +
                  "WHERE user_id = :userId", 
           nativeQuery = true)
    int updateIsActiveByUserId(@Param("userId") Long userId, 
                               @Param("isActive") Boolean isActive);
    
    /**
     * 查找用户的高评分反馈记录（评分≥4）
     * 用于ViewVisitedSites用例的"我最喜欢的站点"功能
     * 
     * @param userId 用户ID
     * @param minRating 最低评分阈值
     * @return 高评分反馈记录列表
     */
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.rating >= :minRating AND f.isActive = true ORDER BY f.rating DESC, f.visitDate DESC")
    List<Feedback> findHighRatedFeedbacksByUser(@Param("userId") Long userId, 
                                                @Param("minRating") Integer minRating);
    
    /**
     * 查找用户访问次数最多的站点
     * 用于ViewVisitedSites用例的数据分析，显示用户最常访问的站点
     * 注意：一个用户对同一个站点可能有多个反馈，这里统计的是站点访问次数
     * 
     * @param userId 用户ID
     * @param limit 返回结果数量限制
     * @return 包含站点ID、名称和访问次数的对象列表
     */
    @Query(value = "SELECT s.id as siteId, s.name as siteName, COUNT(f.id) as visitCount " +
                  "FROM feedbacks f " +
                  "INNER JOIN sites s ON f.site_id = s.id " +
                  "WHERE f.user_id = :userId AND f.is_active = true " +
                  "GROUP BY s.id, s.name " +
                  "ORDER BY visitCount DESC " +
                  "LIMIT :limit", 
           nativeQuery = true)
    List<Object[]> findMostVisitedSitesByUser(@Param("userId") Long userId, 
                                              @Param("limit") int limit);
}