package com.tourist.feedback.repository;

import com.tourist.feedback.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 站点数据访问层接口
 * 扩展Spring Data JPA的JpaRepository接口，提供站点相关的数据访问方法
 * 包含了站点查询、统计和管理的核心数据库操作
 * ViewVisitedSites用例中使用此接口查询用户访问过的站点信息
 */
@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    
    /**
     * 根据站点名称查找站点（精确匹配）
     * 用于站点搜索和管理员操作
     * 站点名称在数据库中是唯一的，确保精确匹配
     * 
     * @param name 站点名称
     * @return 站点Optional对象，如果没有匹配的站点则返回空Optional
     */
    Optional<Site> findByName(String name);
    
    /**
     * 根据站点名称前缀模糊查找站点（忽略大小写）
     * 用于ViewVisitedSites用例的搜索功能，支持用户按名称搜索访问过的站点
     * 使用LIKE和LOWER函数实现不区分大小写的模糊匹配
     * 
     * @param namePrefix 站点名称前缀
     * @return 匹配的站点列表，按名称排序
     */
    List<Site> findByNameStartingWithIgnoreCase(String namePrefix);
    
    /**
     * 根据站点类型查找站点
     * 用于站点分类筛选功能，如筛选历史遗迹、自然景观等类别的站点
     * 
     * @param category 站点类型
     * @return 该类型的站点列表
     */
    List<Site> findByCategory(String category);
    
    /**
     * 根据地理位置模糊查找站点
     * 用于用户按地区筛选访问过的站点
     * 支持部分匹配，如查找某个城市的所有站点
     * 
     * @param location 地理位置关键词
     * @return 匹配地理位置的站点列表
     */
    List<Site> findByLocationContaining(String location);
    
    /**
     * 根据平均评分范围查找站点
     * 用于ViewVisitedSites用例的筛选功能，用户可以根据评分筛选访问过的站点
     * 使用BETWEEN查询优化性能，适合范围查询
     * 
     * @param minRating 最低评分（包含）
     * @param maxRating 最高评分（包含）
     * @return 评分在指定范围内的站点列表
     */
    @Query("SELECT s FROM Site s WHERE s.averageRating BETWEEN :minRating AND :maxRating")
    List<Site> findByAverageRatingBetween(@Param("minRating") Double minRating, 
                                          @Param("maxRating") Double maxRating);
    
    /**
     * 查找有反馈记录的站点
     * 用于ViewVisitedSites用例的核心查询，确保只返回用户有反馈的站点
     * 使用JOIN语句连接Site和Feedback表，避免返回没有反馈记录的站点
     * 
     * @return 有反馈记录的站点列表
     */
    @Query("SELECT DISTINCT s FROM Site s JOIN s.feedbacks f WHERE f.isActive = true")
    List<Site> findSitesWithFeedback();
    
    /**
     * 查找指定用户有反馈记录的站点
     * ViewVisitedSites用例的核心查询方法：查找特定用户访问过并有反馈的站点
     * 使用JOIN连接Site和Feedback表，通过用户ID筛选
     * 这是实现ViewVisitedSites功能的关键数据访问方法
     * 
     * @param userId 用户ID
     * @return 指定用户访问过并有反馈的站点列表
     */
    @Query("SELECT DISTINCT s FROM Site s " +
           "JOIN s.feedbacks f " +
           "WHERE f.user.id = :userId AND f.isActive = true " +
           "ORDER BY f.visitDate DESC")
    List<Site> findSitesVisitedByUser(@Param("userId") Long userId);
    
    /**
     * 查找访问次数最多的站点（热门站点）
     * 用于数据分析和统计功能，可以展示最受欢迎的旅游景点
     * 按访问次数降序排列，支持分页查询
     * 
     * @return 按访问次数降序排列的站点列表
     */
    @Query("SELECT s FROM Site s ORDER BY s.visitCount DESC")
    List<Site> findPopularSitesByVisitCount();
    
    /**
     * 查找高评分站点（平均评分≥4.0）
     * 用于推荐系统，为用户推荐高质量的旅游景点
     * 
     * @return 高评分站点列表，按平均评分降序排列
     */
    @Query("SELECT s FROM Site s WHERE s.averageRating >= 4.0 ORDER BY s.averageRating DESC")
    List<Site> findHighlyRatedSites();
    
    /**
     * 统计站点反馈数量
     * 用于数据分析和站点质量评估
     * 返回每个站点的反馈数量和站点基本信息
     * 
     * @return 包含站点ID、名称和反馈数量的对象列表
     */
    @Query(value = "SELECT s.id as siteId, s.name as siteName, COUNT(f.id) as feedbackCount " +
                  "FROM sites s " +
                  "LEFT JOIN feedbacks f ON s.id = f.site_id AND f.is_active = true " +
                  "GROUP BY s.id, s.name " +
                  "ORDER BY feedbackCount DESC", 
           nativeQuery = true)
    List<Object[]> findSiteFeedbackStats();
    
    /**
     * 自定义查询：查找用户最近访问的站点
     * 用于ViewVisitedSites用例的默认排序，按访问时间倒序排列
     * 返回用户访问过的最新站点，用于"最近访问"功能
     * 
     * @param userId 用户ID
     * @param limit 返回结果数量限制
     * @return 用户最近访问的站点列表
     */
    @Query(value = "SELECT s.* FROM sites s " +
                  "INNER JOIN feedbacks f ON s.id = f.site_id " +
                  "WHERE f.user_id = :userId AND f.is_active = true " +
                  "ORDER BY f.visit_date DESC " +
                  "LIMIT :limit", 
           nativeQuery = true)
    List<Site> findRecentlyVisitedSitesByUser(@Param("userId") Long userId, 
                                              @Param("limit") int limit);
    
    /**
     * 根据站点类型和平均评分查找站点
     * 用于高级筛选功能，用户可以结合类型和评分筛选站点
     * 例如：查找"历史遗迹"类型且评分≥4.0的站点
     * 
     * @param category 站点类型
     * @param minRating 最低平均评分
     * @return 符合条件的站点列表
     */
    @Query("SELECT s FROM Site s WHERE s.category = :category AND s.averageRating >= :minRating " +
           "ORDER BY s.averageRating DESC")
    List<Site> findByCategoryAndAverageRatingGreaterThanEqual(
            @Param("category") String category, 
            @Param("minRating") Double minRating);
    
    /**
     * 检查站点名称是否已存在
     * 用于添加新站点时的重复检查，确保站点名称的唯一性
     * 使用exists查询优化性能，只返回布尔值而不返回完整对象
     * 
     * @param name 站点名称
     * @return true表示站点名称已存在，false表示可以添加新站点
     */
    boolean existsByName(String name);
    
    /**
     * 更新站点平均评分
     * 当有新的反馈添加或现有反馈修改时，需要重新计算平均评分
     * 使用原生SQL提高大数据量下的更新性能
     * 
     * @param siteId 站点ID
     */
    @Query(value = "UPDATE sites s " +
                  "SET average_rating = (SELECT AVG(rating) FROM feedbacks " +
                  "WHERE site_id = :siteId AND is_active = true) " +
                  "WHERE s.id = :siteId", 
           nativeQuery = true)
    void updateAverageRating(@Param("siteId") Long siteId);
    
    /**
     * 搜索站点（多条件组合查询）
     * 用于ViewVisitedSites用例的高级搜索功能，支持多个条件组合筛选
     * 使用动态查询构建，可以在Service层根据条件动态调用
     * 
     * @param name 站点名称关键词（可选）
     * @param category 站点类型（可选）
     * @param minRating 最低评分（可选）
     * @param maxRating 最高评分（可选）
     * @param location 地理位置关键词（可选）
     * @return 符合条件的站点列表
     */
    @Query("SELECT s FROM Site s WHERE " +
           "(:name IS NULL OR s.name LIKE %:name%) AND " +
           "(:category IS NULL OR s.category = :category) AND " +
           "(:minRating IS NULL OR s.averageRating >= :minRating) AND " +
           "(:maxRating IS NULL OR s.averageRating <= :maxRating) AND " +
           "(:location IS NULL OR s.location LIKE %:location%) " +
           "ORDER BY s.averageRating DESC")
    List<Site> searchSites(@Param("name") String name,
                          @Param("category") String category,
                          @Param("minRating") Double minRating,
                          @Param("maxRating") Double maxRating,
                          @Param("location") String location);
}