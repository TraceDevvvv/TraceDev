```
package com.tourist.feedback.service;

import com.tourist.feedback.dto.VisitedSiteResponse;
import com.tourist.feedback.entity.User;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ViewVisitedSites用例的核心服务接口
 * 定义了获取用户访问过的站点列表的业务方法，包含用户认证验证、数据查询和错误处理
 * 该接口封装了ViewVisitedSites用例的所有业务逻辑，包括：
 * 1. 获取用户访问过的站点列表（核心功能）
 * 2. 支持各种筛选条件（按时间、评分、是否推荐等）
 * 3. 处理分页和排序
 * 4. 处理数据统计和分析
 * 这是实现ViewVisitedSites用例的核心业务逻辑组件
 */
public interface VisitorService {
    
    /**
     * 获取用户访问过的站点列表（核心方法）
     * ViewVisitedSites用例的核心业务方法：根据用户ID获取其访问过并有反馈记录的站点列表
     * 此方法实现了P0需求：用户认证验证和反馈数据查询功能
     * 使用分页技术优化大数据量下的查询性能，符合性能需求中"页面加载时间≤3秒"的要求
     * 
     * @param userId 用户唯一标识符，必须为已认证的用户ID（入口条件：游客已成功认证）
     * @param pageable 分页参数，用于控制返回数据的数量和排序方式（P1需求：数据分页功能）
     * @return 用户访问过的站点列表，每个条目包含站点信息、访问时间和反馈详情
     * @throws com.tourist.feedback.exception.AuthenticationException 如果用户未认证或认证已过期
     * @throws com.tourist.feedback.exception.UserNotFoundException 如果指定的用户不存在
     * @throws com.tourist.feedback.exception.DataAccessException 如果数据库连接失败或查询异常
     */
    List<VisitedSiteResponse> getVisitedSites(Long userId, Pageable pageable);
    
    /**
     * 获取用户访问过的站点列表（按时间范围筛选）
     * 支持查询指定时间段内的访问记录，符合用户故事"快速查看历史记录"的按时间排序需求
     * 用于用户查看特定时间段（如某个月、某年）的旅行记录
     * 
     * @param userId 用户唯一标识符
     * @param startDate 开始时间（包含），如果不提供则查询所有记录
     * @param endDate 结束时间（包含），如果不提供则查询所有记录
     * @param pageable 分页参数
     * @return 指定时间范围内的访问站点列表
     * @throws IllegalArgumentException 如果时间范围无效（如结束时间早于开始时间）
     */
    List<VisitedSiteResponse> getVisitedSitesByDateRange(
            Long userId, 
            LocalDateTime startDate, 
            LocalDateTime endDate, 
            Pageable pageable);
    
    /**
     * 获取用户访问过的站点列表（按评分筛选）
     * 支持筛选指定评分范围的站点，符合P1需求中的搜索和筛选功能
     * 用于用户查看自己高度评价或低度评价的站点
     * 
     * @param userId 用户唯一标识符
     * @param minRating 最低评分（包含，1-5），如果不提供则默认为1
     * @param maxRating 最高评分（包含，1-5），如果不提供则默认为5
     * @param pageable 分页参数
     * @return 指定评分范围内的访问站点列表
     * @throws IllegalArgumentException 如果评分范围无效（如minRating > maxRating）
     */
    List<VisitedSiteResponse> getVisitedSitesByRating(
            Long userId, 
            Integer minRating, 
            Integer maxRating, 
            Pageable pageable);
    
    /**
     * 获取用户推荐过的站点列表
     * 符合用户故事"分享旅行经历"的需求，用户可以查看自己推荐过的站点
     * 用于快速筛选用户特别推荐的优质景点
     * 
     * @param userId 用户唯一标识符
     * @param pageable 分页参数
     * @return 用户推荐过的站点列表
     */
    List<VisitedSiteResponse> getRecommendedSites(Long userId, Pageable pageable);
    
    /**
     * 获取用户最近访问的站点列表
     * 实现P1需求中的排序功能，默认按访问时间倒序排列
     * 用户默认查看页面时显示最近的访问记录，符合用户使用习惯
     * 
     * @param userId 用户唯一标识符
     * @param limit 返回结果数量限制，如果不提供则使用默认值
     * @return 用户最近访问的站点列表
     */
    List<VisitedSiteResponse> getRecentlyVisitedSites(Long userId, Integer limit);
    
    /**
     * 获取用户访问过的站点统计数据
     * 符合用户故事"管理个人足迹"的需求，提供数据统计展示
     * 用于用户个人中心的数据分析，帮助用户了解自己的旅行偏好
     * 
     * @param userId 用户唯一标识符
     * @return 用户访问数据的统计信息对象
     */
    VisitedSiteStats getVisitedSiteStats(Long userId);
    
    /**
     * 搜索用户访问过的站点
     * 实现P1需求中的搜索功能，支持按站点名称、位置等进行模糊搜索
     * 用户可以通过关键词快速找到特定的访问记录
     * 
     * @param userId 用户唯一标识符
     * @param keyword 搜索关键词，可以是站点名称、位置的部分匹配
     * @param pageable 分页参数
     * @return 匹配搜索关键词的访问站点列表
     */
    List<VisitedSiteResponse> searchVisitedSites(
            Long userId, 
            String keyword, 
            Pageable pageable);
    
    /**
     * 获取用户访问过的所有站点数量
     * 用于分页功能的总数计算，也用于数据统计展示
     * 使用count查询优化性能，避免全表扫描
     * 
     * @param userId 用户唯一标识符
     * @return 用户访问过的站点总数
     */
    long countVisitedSites(Long userId);
    
    /**
     * 验证用户访问权限
     * 实现P0需求中的用户认证验证机制，确保只有认证用户才能访问功能
     * 入口条件的安全验证：检查用户是否已成功认证且状态正常
     * 
     * @param userId 用户唯一标识符
     * @return 验证后的用户对象
     * @throws com.tourist.feedback.exception.AuthenticationException 如果用户未认证或认证已过期
     * @throws com.tourist.feedback.exception.UserNotFoundException 如果指定的用户不存在
     */
    User validateUserAccess(Long userId);
    
    /**
     * 导出用户访问过的站点数据
     * 实现P1需求中的数据导出功能，支持导出为CSV格式
     * 符合用户故事"管理个人足迹"中支持导出个人数据的需求
     * 
     * @param userId 用户唯一标识符
     * @param format 导出格式（支持CSV、JSON等）
     * @return 导出数据的字符串表示
     */
    String exportVisitedSites(Long userId, String format);
    
    /**
     * 处理服务器连接中断的异常
     * 实现边界条件处理：当到ETOUR服务器的连接中断时，提供优雅的降级处理
     * 使用缓存机制保证基本功能可用，符合高质量要求
     * 
     * @param userId 用户唯一标识符
     * @return 如果可用，返回缓存的访问站点数据；否则返回空列表
     * @throws com.tourist.feedback.exception.ServiceUnavailableException 如果服务完全不可用
     */
    List<VisitedSiteResponse> getVisitedSitesWithFallback(Long userId);
}