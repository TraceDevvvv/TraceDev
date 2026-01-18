```
package com.tourist.feedback.service;

import com.tourist.feedback.dto.VisitedSiteResponse;
import com.tourist.feedback.entity.Feedback;
import com.tourist.feedback.entity.Site;
import com.tourist.feedback.entity.User;
import com.tourist.feedback.exception.AuthenticationException;
import com.tourist.feedback.exception.DataAccessException;
import com.tourist.feedback.exception.UserNotFoundException;
import com.tourist.feedback.repository.FeedbackRepository;
import com.tourist.feedback.repository.SiteRepository;
import com.tourist.feedback.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * VisitorService的单元测试类
 * 测试ViewVisitedSites用例的核心业务逻辑，包括正常场景、边界情况和异常处理
 * 这个测试类覆盖了VisitorService的所有主要方法，确保业务逻辑的正确性和鲁棒性
 */
@ExtendWith(MockitoExtension.class)
public class VisitorServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private SiteRepository siteRepository;
    
    @Mock
    private FeedbackRepository feedbackRepository;
    
    @InjectMocks
    private VisitorServiceImpl visitorService;
    
    private User testUser;
    private Site testSite1;
    private Site testSite2;
    private Feedback testFeedback1;
    private Feedback testFeedback2;
    
    /**
     * 测试数据准备方法
     * 在每个测试方法执行前创建测试数据，确保测试的隔离性和可重复性
     */
    @BeforeEach
    void setUp() {
        // 创建测试用户
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPasswordHash("hashed_password");
        testUser.setIsActive(true);
        testUser.setCreatedAt(LocalDateTime.now());
        testUser.setUpdatedAt(LocalDateTime.now());
        
        // 创建测试站点1
        testSite1 = new Site();
        testSite1.setId(1L);
        testSite1.setName("埃菲尔铁塔");
        testSite1.setDescription("法国巴黎的标志性建筑");
        testSite1.setLocation("法国巴黎战神广场");
        testSite1.setCategory("历史遗迹");
        testSite1.setImageUrl("https://example.com/eiffel.jpg");
        testSite1.setAverageRating(4.5);
        testSite1.setVisitCount(1000);
        testSite1.setCreatedAt(LocalDateTime.now());
        
        // 创建测试站点2
        testSite2 = new Site();
        testSite2.setId(2L);
        testSite2.setName("卢浮宫");
        testSite2.setDescription("世界著名的艺术博物馆");
        testSite2.setLocation("法国巴黎塞纳河畔");
        testSite2.setCategory("博物馆");
        testSite2.setImageUrl("https://example.com/louvre.jpg");
        testSite2.setAverageRating(4.8);
        testSite2.setVisitCount(1500);
        testSite2.setCreatedAt(LocalDateTime.now());
        
        // 创建测试反馈1
        testFeedback1 = new Feedback();
        testFeedback1.setId(1L);
        testFeedback1.setUser(testUser);
        testFeedback1.setSite(testSite1);
        testFeedback1.setRating(5);
        testFeedback1.setComment("非常棒的体验！夜景特别美丽！");
        testFeedback1.setCreatedAt(LocalDateTime.now().minusDays(2));
        testFeedback1.setVisitDate(LocalDateTime.now().minusDays(3));
        testFeedback1.setIsActive(true);
        testFeedback1.setIsRecommended(true);
        
        // 创建测试反馈2
        testFeedback2 = new Feedback();
        testFeedback2.setId(2L);
        testFeedback2.setUser(testUser);
        testFeedback2.setSite(testSite2);
        testFeedback2.setRating(4);
        testFeedback2.setComment("艺术品很丰富，但人太多了");
        testFeedback2.setCreatedAt(LocalDateTime.now().minusDays(1));
        testFeedback2.setVisitDate(LocalDateTime.now().minusDays(2));
        testFeedback2.setIsActive(true);
        testFeedback2.setIsRecommended(true);
    }
    
    /**
     * 测试获取用户访问过的站点列表（正常场景）
     * 验证用户认证成功且反馈数据正确返回的情况
     */
    @Test
    void testGetVisitedSites_Success() {
        // 准备测试数据
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Feedback> feedbackPage = new PageImpl<>(feedbacks, pageable, feedbacks.size());
        
        // 模拟依赖行为
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserId(anyLong()))
            .thenReturn(feedbacks);
        
        // 执行测试
        List<VisitedSiteResponse> result = visitorService.getVisitedSites(1L, pageable);
        
        // 验证结果
        assertNotNull(result);
        assertEquals(2, result.size());
        
        // 验证第一个站点
        VisitedSiteResponse response1 = result.get(0);
        assertEquals(1L, response1.getSiteId());
        assertEquals("埃菲尔铁塔", response1.getSiteName());
        assertEquals(5, response1.getFeedbackRating());
        assertEquals("非常棒的体验！夜景特别美丽！", response1.getFeedbackComment());
        assertTrue(response1.getIsRecommended());
        
        // 验证第二个站点
        VisitedSiteResponse response2 = result.get(1);
        assertEquals(2L, response2.getSiteId());
        assertEquals("卢浮宫", response2.getSiteName());
        assertEquals(4, response2.getFeedbackRating());
        assertEquals("艺术品很丰富，但人太多了", response2.getFeedbackComment());
        assertTrue(response2.getIsRecommended());
        
        // 验证依赖调用
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findByUserId(1L);
    }
    
    /**
     * 测试用户认证失败场景
     * 验证当用户未认证或认证已过期时抛出AuthenticationException
     */
    @Test
    void testGetVisitedSites_AuthenticationFailed() {
        // 模拟用户不存在或未激活
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.empty());
        
        // 执行测试并验证异常
        AuthenticationException exception = assertThrows(
            AuthenticationException.class,
            () -> visitorService.getVisitedSites(1L, PageRequest.of(0, 10))
        );
        
        // 验证异常信息
        assertNotNull(exception);
        assertEquals("UNAUTHORIZED", exception.getErrorCode());
        assertTrue(exception.getMessage().contains("用户未认证"));
        
        // 验证依赖调用
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, never()).findByUserId(anyLong());
    }
    
    /**
     * 测试用户不存在场景
     * 验证当用户ID对应的用户不存在时抛出UserNotFoundException
     */
    @Test
    void testValidateUserAccess_UserNotFound() {
        // 模拟用户不存在
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.empty());
        
        // 执行测试并验证异常
        UserNotFoundException exception = assertThrows(
            UserNotFoundException.class,
            () -> visitorService.validateUserAccess(999L)
        );
        
        // 验证异常信息
        assertNotNull(exception);
        assertEquals("USER_NOT_FOUND", exception.getErrorCode());
        assertEquals(999L, exception.getUserId());
        assertTrue(exception.getMessage().contains("用户不存在"));
    }
    
    /**
     * 测试数据库访问失败场景
     * 验证当数据库连接中断或查询失败时抛出DataAccessException
     */
    @Test
    void testGetVisitedSites_DataAccessException() {
        // 模拟用户存在
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        
        // 模拟数据库查询抛出异常
        when(feedbackRepository.findByUserId(anyLong()))
            .thenThrow(new RuntimeException("数据库连接失败"));
        
        // 执行测试并验证异常
        DataAccessException exception = assertThrows(
            DataAccessException.class,
            () -> visitorService.getVisitedSites(1L, PageRequest.of(0, 10))
        );
        
        // 验证异常信息
        assertNotNull(exception);
        assertEquals("DATA_ACCESS_ERROR", exception.getErrorCode());
        assertTrue(exception.getMessage().contains("数据访问失败"));
        assertTrue(exception.isRetryable());
    }
    
    /**
     * 测试按时间范围筛选访问站点（正常场景）
     * 验证用户指定时间范围时正确返回该时间段的访问记录
     */
    @Test
    void testGetVisitedSitesByDateRange_Success() {
        // 准备测试数据
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        Pageable pageable = PageRequest.of(0, 10);
        
        // 模拟依赖行为
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserIdAndVisitDateBetween(anyLong(), any(), any()))
            .thenReturn(feedbacks);
        
        // 执行测试
        List<VisitedSiteResponse> result = visitorService.getVisitedSitesByDateRange(
            1L, startDate, endDate, pageable);
        
        // 验证结果
        assertNotNull(result);
        assertEquals(2, result.size());
        
        // 验证依赖调用
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findByUserIdAndVisitDateBetween(
            1L, startDate, endDate);
    }
    
    /**
     * 测试按时间范围筛选访问站点（无数据场景）
     * 验证当指定时间范围内没有访问记录时返回空列表
     */
    @Test
    void testGetVisitedSitesByDateRange_NoData() {
        // 准备测试数据
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();
        List<Feedback> feedbacks = Collections.emptyList();
        Pageable pageable = PageRequest.of(0, 10);
        
        // 模拟依赖行为
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserIdAndVisitDateBetween(anyLong(), any(), any()))
            .thenReturn(feedbacks);
        
        // 执行测试
        List<VisitedSiteResponse> result = visitorService.getVisitedSitesByDateRange(
            1L, startDate, endDate, pageable);
        
        // 验证结果
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
    /**
     * 测试按评分筛选访问站点（正常场景）
     * 验证用户指定评分范围时正确返回该评分段的访问记录
     */
    @Test
    void testGetVisitedSitesByRating_Success() {
        // 准备测试数据
        Integer minRating = 4;
        Integer maxRating = 5;
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        Pageable pageable = PageRequest.of(0, 10);
        
        // 模拟依赖行为
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserIdAndRatingBetween(anyLong(), anyInt(), anyInt()))
            .thenReturn(feedbacks);
        
        // 执行测试
        List<VisitedSiteResponse> result = visitorService.getVisitedSitesByRating(
            1L, minRating, maxRating, pageable);
        
        // 验证结果
        assertNotNull(result);
        assertEquals(2, result.size());
        
        // 验证依赖调用
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findByUserIdAndRatingBetween(
            1L, minRating, maxRating);
    }
    
    /**
     * 测试按评分筛选访问站点（无效评分范围场景）
     * 验证当评分范围无效时抛出IllegalArgumentException
     */
    @Test
    void testGetVisitedSitesByRating_InvalidRange() {
        // 准备测试数据
        Integer minRating = 5;
        Integer maxRating = 3; // 无效范围：最小值大于最大值
        
        // 模拟用户存在
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        
        // 执行测试并验证异常
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> visitorService.getVisitedSitesByRating(
                1L, minRating, maxRating, PageRequest.of(0, 10))
        );
        
        // 验证异常信息
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("评分范围无效"));
    }
    
    /**
     * 测试获取推荐过的站点列表（正常场景）
     * 验证正确返回用户推荐过的站点列表
     */
    @Test
    void testGetRecommendedSites_Success() {
        // 准备测试数据
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        Pageable pageable = PageRequest.of(0, 10);
        
        // 模拟依赖行为
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserIdAndIsRecommendedAndIsActive(anyLong(), anyBoolean(), anyBoolean()))
            .thenReturn(feedbacks);
        
        // 执行测试
        List<VisitedSiteResponse> result = visitorService.getRecommendedSites(1L, pageable);
        
        // 验证结果
        assertNotNull(result);
        assertEquals(2, result.size());
        
        // 验证依赖调用
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findByUserIdAndIsRecommendedAndIsActive(
            1L, true, true);
    }
    
    /**
     * 测试获取最近访问的站点列表（正常场景）
     * 验证正确返回用户最近访问的站点列表
     */
    @Test
    void testGetRecentlyVisitedSites_Success() {
        // 准备测试数据
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        
        // 模拟依赖行为
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findRecentFeedbacksByUser(anyLong(), anyInt()))
            .thenReturn(feedbacks);
        
        // 执行测试
        List<VisitedSiteResponse> result = visitorService.getRecentlyVisitedSites(1L, 5);
        
        // 验证结果
        assertNotNull(result);
        assertEquals(2, result.size());
        
        // 验证依赖调用
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findRecentFeedbacksByUser(1L, 5);
    }
    
    /**
     * 测试获取最近访问的站点列表（默认限制场景）
     * 验证当不指定限制数量时使用默认值
     */
    @Test
    void testGetRecentlyVisitedSites_DefaultLimit() {
        // 准备测试数据
        List<Feedback> feedbacks = Arrays.asList(testFeedback1);
        
        // 模拟依赖行为
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findRecentFeedbacksByUser(anyLong(), anyInt()))
            .thenReturn(feedbacks);
        
        // 执行测试（不指定限制数量）
        List<VisitedSiteResponse> result = visitorService.getRecentlyVisitedSites(1L, null);
        
        // 验证结果
        assertNotNull(result);
        assertEquals(1, result.size());
        
        // 验证使用默认限制（10）
        verify(feedbackRepository, times(1)).findRecentFeedbacksByUser(1L, 10);
    }
    
    /**
     * 测试获取访问站点统计数据（正常场景）
     * 验证正确返回用户访问站点的统计信息
     */
    @Test
    void testGetVisitedSiteStats_Success() {
        // 准备测试数据
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        List<Object[]> mostVisitedStats = Arrays.asList(
            new Object[]{1L, "埃菲尔铁塔", 5L},
            new Object[]{2L, "卢浮宫", 3L}
        );
        List<Object[]> yearlyStats = Arrays.asList(
            new Object[]{2024, 2L}
        );
        List<Object[]> categoryStats = Arrays.asList(
            new Object[]{"历史遗迹", 1L},
            new Object[]{"博物馆", 1L}
        );
        
        // 模拟依赖行为
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.countByUserIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(2L);
        when(feedbackRepository.calculateAverageRatingByUser(anyLong()))
            .thenReturn(4.5);
        when(feedbackRepository.findMostVisitedSitesByUser(anyLong(), anyInt()))
            .thenReturn(mostVisitedStats);
        when(f