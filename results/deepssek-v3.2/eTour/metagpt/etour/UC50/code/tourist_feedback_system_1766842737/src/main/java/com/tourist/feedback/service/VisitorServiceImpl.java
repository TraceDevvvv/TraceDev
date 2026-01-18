我将继续完成Task 5，创建VisitorService接口的实现类。这个类是ViewVisitedSites用例的核心业务逻辑实现，包含用户认证验证、数据查询、错误处理和边界情况处理等关键功能。

```java
package com.tourist.feedback.service;

import com.tourist.feedback.dto.VisitedSiteResponse;
import com.tourist.feedback.entity.Feedback;
import com.tourist.feedback.entity.Site;
import com.tourist.feedback.entity.User;
import com.tourist.feedback.exception.*;
import com.tourist.feedback.repository.FeedbackRepository;
import com.tourist.feedback.repository.SiteRepository;
import com.tourist.feedback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * VisitorService接口的实现类
 * 包含ViewVisitedSites用例的核心业务逻辑，处理用户认证验证、数据查询、错误处理和边界情况处理
 * 这个类实现了所有P0、P1和部分P2需求的业务逻辑，是系统中最核心的业务层组件
 * 
 * 核心职责：
 * 1. 用户认证和权限验证（P0-001）
 * 2. 查询用户反馈数据（P0-002）
 * 3. 关联查询站点信息（P0-003）
 * 4. 数据列表处理（P0-004）
 * 5. 错误处理和异常管理（P0-005）
 * 6. 分页、搜索、排序功能（P1需求）
 * 7. 数据导出和统计功能（P1需求）
 * 8. 服务中断降级处理（边界条件）
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VisitorServiceImpl implements VisitorService {
    
    // 依赖注入：所有必要的数据访问层组件
    private final UserRepository userRepository;
    private final SiteRepository siteRepository;
    private final FeedbackRepository feedbackRepository;
    
    // 缓存管理器（简化版）
    private Map<Long, List<VisitedSiteResponse>> responseCache = new HashMap<>();
    private final Object cacheLock = new Object();
    
    // 常量定义
    private static final int DEFAULT_RECENT_LIMIT = 10;
    private static final int CACHE_EXPIRY_HOURS = 6;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 获取用户访问过的站点列表（核心方法）
     * ViewVisitedSites用例的核心业务方法：根据用户ID获取其访问过并有反馈记录的站点列表
     * 实现步骤：
     * 1. 验证用户访问权限（入口条件：游客已成功认证）
     * 2. 查询用户的反馈记录（出口条件：系统显示访问过的站点列表）
     * 3. 转换为响应DTO对象
     * 4. 应用分页逻辑
     * 
     * @param userId 用户唯一标识符，必须为已认证的用户ID
     * @param pageable 分页参数，包含页码、每页大小和排序信息
     * @return 用户访问过的站点列表，按访问时间倒序排列
     * @throws AuthenticationException 如果用户未认证或认证已过期
     * @throws UserNotFoundException 如果指定的用户不存在
     * @throws DataAccessException 如果数据库连接失败或查询异常
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getVisitedSites(Long userId, Pageable pageable) {
        log.info("开始获取用户访问过的站点列表，用户ID: {}, 分页参数: {}", userId, pageable);
        
        try {
            // 步骤1：验证用户访问权限
            User user = validateUserAccess(userId);
            log.debug("用户验证成功: {}", user.getUsername());
            
            // 步骤2：查询用户的反馈记录
            // 使用JOIN FETCH优化查询，一次性加载关联的站点信息
            List<Feedback> feedbacks = feedbackRepository.findByUserId(userId);
            log.debug("查询到用户反馈记录数量: {}", feedbacks.size());
            
            // 步骤3：转换为响应DTO对象
            List<VisitedSiteResponse> responses = convertFeedbacksToResponses(feedbacks);
            
            // 步骤4：应用分页逻辑
            return applyPagination(responses, pageable);
            
        } catch (AuthenticationException | UserNotFoundException e) {
            // 认证相关异常，直接向上抛出
            throw e;
        } catch (Exception e) {
            // 数据库或系统异常，包装为DataAccessException
            log.error("获取用户访问过的站点列表时发生异常，用户ID: {}", userId, e);
            throw new DataAccessException("查询用户访问记录失败，请稍后重试", "FEEDBACK_QUERY_ERROR", e);
        }
    }
    
    /**
     * 获取用户访问过的站点列表（按时间范围筛选）
     * 实现步骤：
     * 1. 验证时间范围参数的有效性
     * 2. 验证用户访问权限
     * 3. 查询指定时间范围内的反馈记录
     * 4. 转换为响应DTO对象并应用分页
     * 
     * @param userId 用户唯一标识符
     * @param startDate 开始时间（包含），null表示不限制
     * @param endDate 结束时间（包含），null表示不限制
     * @param pageable 分页参数
     * @return 指定时间范围内的访问站点列表
     * @throws IllegalArgumentException 如果时间范围无效
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getVisitedSitesByDateRange(
            Long userId, 
            LocalDateTime startDate, 
            LocalDateTime endDate, 
            Pageable pageable) {
        
        log.info("获取用户按时间范围筛选的访问站点，用户ID: {}, 开始时间: {}, 结束时间: {}", 
                userId, startDate, endDate);
        
        try {
            // 验证时间范围参数的有效性
            validateDateRange(startDate, endDate);
            
            // 验证用户访问权限
            User user = validateUserAccess(userId);
            log.debug("时间筛选查询用户验证成功: {}", user.getUsername());
            
            // 查询指定时间范围内的反馈记录
            List<Feedback> feedbacks;
            if (startDate != null && endDate != null) {
                feedbacks = feedbackRepository.findByUserIdAndVisitDateBetween(userId, startDate, endDate);
            } else if (startDate != null) {
                // 只限制开始时间
                feedbacks = feedbackRepository.findByUserIdAndVisitDateBetween(
                    userId, startDate, LocalDateTime.now());
            } else if (endDate != null) {
                // 只限制结束时间
                feedbacks = feedbackRepository.findByUserIdAndVisitDateBetween(
                    userId, LocalDateTime.MIN, endDate);
            } else {
                // 都没有限制，查询所有
                feedbacks = feedbackRepository.findByUserId(userId);
            }
            
            log.debug("时间范围筛选查询到反馈记录数量: {}", feedbacks.size());
            
            // 转换为响应DTO对象并应用分页
            List<VisitedSiteResponse> responses = convertFeedbacksToResponses(feedbacks);
            return applyPagination(responses, pageable);
            
        } catch (IllegalArgumentException e) {
            log.error("时间范围参数无效", e);
            throw e;
        } catch (Exception e) {
            log.error("按时间范围查询用户访问记录时发生异常", e);
            throw new DataAccessException("按时间范围查询失败", "DATE_RANGE_QUERY_ERROR", e);
        }
    }
    
    /**
     * 获取用户访问过的站点列表（按评分筛选）
     * 实现步骤：
     * 1. 验证评分范围参数的有效性
     * 2. 设置默认评分范围（如果参数为null）
     * 3. 验证用户访问权限
     * 4. 查询指定评分范围内的反馈记录
     * 5. 转换为响应DTO对象并应用分页
     * 
     * @param userId 用户唯一标识符
     * @param minRating 最低评分（1-5），null表示最小评分1
     * @param maxRating 最高评分（1-5），null表示最大评分5
     * @param pageable 分页参数
     * @return 指定评分范围内的访问站点列表
     * @throws IllegalArgumentException 如果评分范围无效
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getVisitedSitesByRating(
            Long userId, 
            Integer minRating, 
            Integer maxRating, 
            Pageable pageable) {
        
        log.info("获取用户按评分筛选的访问站点，用户ID: {}, 最低评分: {}, 最高评分: {}", 
                userId, minRating, maxRating);
        
        try {
            // 设置默认评分范围
            int effectiveMinRating = (minRating != null) ? minRating : 1;
            int effectiveMaxRating = (maxRating != null) ? maxRating : 5;
            
            // 验证评分范围参数的有效性
            validateRatingRange(effectiveMinRating, effectiveMaxRating);
            
            // 验证用户访问权限
            User user = validateUserAccess(userId);
            log.debug("评分筛选查询用户验证成功: {}", user.getUsername());
            
            // 查询指定评分范围内的反馈记录
            List<Feedback> feedbacks = feedbackRepository.findByUserIdAndRatingBetween(
                userId, effectiveMinRating, effectiveMaxRating);
            
            log.debug("评分范围筛选查询到反馈记录数量: {}", feedbacks.size());
            
            // 转换为响应DTO对象并应用分页
            List<VisitedSiteResponse> responses = convertFeedbacksToResponses(feedbacks);
            return applyPagination(responses, pageable);
            
        } catch (IllegalArgumentException e) {
            log.error("评分范围参数无效", e);
            throw e;
        } catch (Exception e) {
            log.error("按评分范围查询用户访问记录时发生异常", e);
            throw new DataAccessException("按评分范围查询失败", "RATING_RANGE_QUERY_ERROR", e);
        }
    }
    
    /**
     * 获取用户推荐过的站点列表
     * 实现步骤：
     * 1. 验证用户访问权限
     * 2. 查询用户推荐（isRecommended=true）的反馈记录
     * 3. 转换为响应DTO对象并应用分页
     * 
     * @param userId 用户唯一标识符
     * @param pageable 分页参数
     * @return 用户推荐过的站点列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getRecommendedSites(Long userId, Pageable pageable) {
        log.info("获取用户推荐过的站点列表，用户ID: {}", userId);
        
        try {
            // 验证用户访问权限
            User user = validateUserAccess(userId);
            log.debug("推荐站点查询用户验证成功: {}", user.getUsername());
            
            // 查询用户推荐的反馈记录
            List<Feedback> feedbacks = feedbackRepository.findByUserIdAndIsRecommendedAndIsActive(
                userId, true, true);
            
            log.debug("推荐站点查询到反馈记录数量: {}", feedbacks.size());
            
            // 转换为响应DTO对象并应用分页
            List<VisitedSiteResponse> responses = convertFeedbacksToResponses(feedbacks);
            return applyPagination(responses, pageable);
            
        } catch (Exception e) {
            log.error("查询用户推荐站点时发生异常", e);
            throw new DataAccessException("查询推荐站点失败", "RECOMMENDED_QUERY_ERROR", e);
        }
    }
    
    /**
     * 获取用户最近访问的站点列表
     * 实现步骤：
     * 1. 验证用户访问权限
     * 2. 设置默认限制数量
     * 3. 查询用户最近的反馈记录（原生SQL优化性能）
     * 4. 转换为响应DTO对象
     * 
     * @param userId 用户唯一标识符
     * @param limit 返回结果数量限制，null则使用默认值
     * @return 用户最近访问的站点列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getRecentlyVisitedSites(Long userId, Integer limit) {
        log.info("获取用户最近访问的站点列表，用户ID: {}, 限制数量: {}", userId, limit);
        
        try {
            // 验证用户访问权限
            User user = validateUserAccess(userId);
            log.debug("最近访问查询用户验证成功: {}", user.getUsername());
            
            // 设置默认限制数量
            int effectiveLimit = (limit != null && limit > 0) ? limit : DEFAULT_RECENT_LIMIT;
            
            // 查询用户最近的反馈记录（使用原生SQL优化性能）
            List<Feedback> feedbacks = feedbackRepository.findRecentFeedbacksByUser(userId, effectiveLimit);
            
            log.debug("最近访问查询到反馈记录数量: {}", feedbacks.size());
            
            // 转换为响应DTO对象
            return convertFeedbacksToResponses(feedbacks);
            
        } catch (Exception e) {
            log.error("查询用户最近访问站点时发生异常", e);
            throw new DataAccessException("查询最近访问记录失败", "RECENT_VISITS_QUERY_ERROR", e);
        }
    }
    
    /**
     * 获取用户访问过的站点统计数据
     * 实现步骤：
     * 1. 验证用户访问权限
     * 2. 查询用户总访问次数
     * 3. 查询用户平均评分
     * 4. 查询用户访问站点统计
     * 5. 查询用户年度访问统计
     * 6. 封装统计信息对象
     * 
     * @param userId 用户唯一标识符
     * @return 用户访问数据的统计信息对象
     */
    @Override
    @Transactional(readOnly = true)
    public VisitedSiteStats getVisitedSiteStats(Long userId) {
        log.info("获取用户访问过的站点统计数据，用户ID: {}", userId);
        
        try {
            // 验证用户访问权限
            User user = validateUserAccess(userId);
            log.debug("统计数据查询用户验证成功: {}", user.getUsername());
            
            // 查询用户总访问次数
            long totalVisits = feedbackRepository.countByUserIdAndIsActive(userId, true);
            
            // 查询用户平均评分
            Double averageRating = feedbackRepository.calculateAverageRatingByUser(userId);
            if (averageRating == null) {
                averageRating = 0.0;
            }
            
            // 查询用户访问站点统计（访问次数最多的站点）
            List<Object[]> mostVisitedStats = feedbackRepository.findMostVisitedSitesByUser(userId, 5);
            Map<String, Long> mostVisitedSites = new LinkedHashMap<>();
            for (Object[] stat : mostVisitedStats) {
                Long siteId = ((Number) stat[0]).longValue();
                String siteName = (String) stat[1];
                Long visitCount = ((Number) stat[2]).longValue();
                mostVisitedSites.put(siteName, visitCount);
            }
            
            // 查询用户年度访问统计
            List<Object[]> yearlyStats = feedbackRepository.findYearlyVisitStatsByUser(userId);
            Map<Integer, Long> yearlyVisits = new TreeMap<>(Collections.reverseOrder());
            for (Object[] stat : yearlyStats) {
                Integer year = ((Number) stat[0]).intValue();
                Long count = ((Number) stat[1]).longValue();
                yearlyVisits.put(year, count);
            }
            
            // 查询用户访问站点类型统计
            List<Object[]> categoryStats = feedbackRepository.findSiteCategoryStatsByUser(userId);
            Map<String, Long> categoryVisits = new HashMap<>();
            for (Object[] stat : categoryStats) {
                String category = (String) stat[0];
                Long count = ((Number) stat[1]).longValue();
                if (category != null) {
                    categoryVisits.put(category, count);
                }
            }
            
            // 查询用户推荐站点数量
            List<Feedback> recommendedFeedbacks = feedbackRepository.findByUserIdAndIsRecommendedAndIsActive(
                userId, true, true);
            long recommendedCount = recommendedFeedbacks.size();
            
            // 查询用户评论最多的站点数量
            List<Feedback> commentedFeedbacks = feedbackRepository.findFeedbacksWithCommentsByUser(userId);
            long commentedCount = commentedFeedbacks.size();
            
            // 创建并返回统计信息对象
            return VisitedSiteStats.builder()
                    .userId(userId)
                    .totalVisits(totalVisits)
                    .averageRating(Math.round(averageRating * 10.0) / 10.0) // 保留1位小数
                    .recommendedCount(recommendedCount)
                    .commentedCount(commentedCount)
                    .mostVisitedSites(mostVisitedSites)
                    .yearlyVisits(yearlyVisits)
                    .categoryVisits(categoryVisits)
                    .build();
            
        } catch (Exception e) {
            log.error("获取用户访问统计数据时发生异常", e);
            throw new DataAccessException("获取统计数据失败", "STATS_QUERY_ERROR", e);
        }
    }
    
    /**
     * 搜索用户访问过的站点
     * 实现步骤：
     * 1. 验证用户访问权限
     * 2. 处理搜索关键词（支持站点名称和位置搜索）
     * 3. 执行搜索查询
     * 4. 转换为响应DTO对象并应用分页
     * 
     * @param userId 用户唯一标识符
     * @param keyword 搜索关键词，可以是站点名称、位置的部分匹配
     * @param pageable 分页参数
     * @return 匹配搜索关键词的访问站点列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> searchVisitedSites(
            Long userId, 
           