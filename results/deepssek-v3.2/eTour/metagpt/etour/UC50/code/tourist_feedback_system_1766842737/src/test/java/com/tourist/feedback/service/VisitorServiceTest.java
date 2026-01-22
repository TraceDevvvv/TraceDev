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
 * VisitorService      
 *   ViewVisitedSites         ，      、         
 *         VisitorService       ，              
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
     *         
     *                 ，             
     */
    @BeforeEach
    void setUp() {
        //       
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPasswordHash("hashed_password");
        testUser.setIsActive(true);
        testUser.setCreatedAt(LocalDateTime.now());
        testUser.setUpdatedAt(LocalDateTime.now());
        
        //       1
        testSite1 = new Site();
        testSite1.setId(1L);
        testSite1.setName("     ");
        testSite1.setDescription("          ");
        testSite1.setLocation("        ");
        testSite1.setCategory("    ");
        testSite1.setImageUrl("https://example.com/eiffel.jpg");
        testSite1.setAverageRating(4.5);
        testSite1.setVisitCount(1000);
        testSite1.setCreatedAt(LocalDateTime.now());
        
        //       2
        testSite2 = new Site();
        testSite2.setId(2L);
        testSite2.setName("   ");
        testSite2.setDescription("          ");
        testSite2.setLocation("        ");
        testSite2.setCategory("   ");
        testSite2.setImageUrl("https://example.com/louvre.jpg");
        testSite2.setAverageRating(4.8);
        testSite2.setVisitCount(1500);
        testSite2.setCreatedAt(LocalDateTime.now());
        
        //       1
        testFeedback1 = new Feedback();
        testFeedback1.setId(1L);
        testFeedback1.setUser(testUser);
        testFeedback1.setSite(testSite1);
        testFeedback1.setRating(5);
        testFeedback1.setComment("      ！      ！");
        testFeedback1.setCreatedAt(LocalDateTime.now().minusDays(2));
        testFeedback1.setVisitDate(LocalDateTime.now().minusDays(3));
        testFeedback1.setIsActive(true);
        testFeedback1.setIsRecommended(true);
        
        //       2
        testFeedback2 = new Feedback();
        testFeedback2.setId(2L);
        testFeedback2.setUser(testUser);
        testFeedback2.setSite(testSite2);
        testFeedback2.setRating(4);
        testFeedback2.setComment("      ，     ");
        testFeedback2.setCreatedAt(LocalDateTime.now().minusDays(1));
        testFeedback2.setVisitDate(LocalDateTime.now().minusDays(2));
        testFeedback2.setIsActive(true);
        testFeedback2.setIsRecommended(true);
    }
    
    /**
     *               （    ）
     *                     
     */
    @Test
    void testGetVisitedSites_Success() {
        //       
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Feedback> feedbackPage = new PageImpl<>(feedbacks, pageable, feedbacks.size());
        
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserId(anyLong()))
            .thenReturn(feedbacks);
        
        //     
        List<VisitedSiteResponse> result = visitorService.getVisitedSites(1L, pageable);
        
        //     
        assertNotNull(result);
        assertEquals(2, result.size());
        
        //        
        VisitedSiteResponse response1 = result.get(0);
        assertEquals(1L, response1.getSiteId());
        assertEquals("     ", response1.getSiteName());
        assertEquals(5, response1.getFeedbackRating());
        assertEquals("      ！      ！", response1.getFeedbackComment());
        assertTrue(response1.getIsRecommended());
        
        //        
        VisitedSiteResponse response2 = result.get(1);
        assertEquals(2L, response2.getSiteId());
        assertEquals("   ", response2.getSiteName());
        assertEquals(4, response2.getFeedbackRating());
        assertEquals("      ，     ", response2.getFeedbackComment());
        assertTrue(response2.getIsRecommended());
        
        //       
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findByUserId(1L);
    }
    
    /**
     *           
     *                  AuthenticationException
     */
    @Test
    void testGetVisitedSites_AuthenticationFailed() {
        //            
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.empty());
        
        //          
        AuthenticationException exception = assertThrows(
            AuthenticationException.class,
            () -> visitorService.getVisitedSites(1L, PageRequest.of(0, 10))
        );
        
        //       
        assertNotNull(exception);
        assertEquals("UNAUTHORIZED", exception.getErrorCode());
        assertTrue(exception.getMessage().contains("     "));
        
        //       
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, never()).findByUserId(anyLong());
    }
    
    /**
     *          
     *      ID           UserNotFoundException
     */
    @Test
    void testValidateUserAccess_UserNotFound() {
        //        
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.empty());
        
        //          
        UserNotFoundException exception = assertThrows(
            UserNotFoundException.class,
            () -> visitorService.validateUserAccess(999L)
        );
        
        //       
        assertNotNull(exception);
        assertEquals("USER_NOT_FOUND", exception.getErrorCode());
        assertEquals(999L, exception.getUserId());
        assertTrue(exception.getMessage().contains("     "));
    }
    
    /**
     *            
     *                   DataAccessException
     */
    @Test
    void testGetVisitedSites_DataAccessException() {
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        
        //            
        when(feedbackRepository.findByUserId(anyLong()))
            .thenThrow(new RuntimeException("       "));
        
        //          
        DataAccessException exception = assertThrows(
            DataAccessException.class,
            () -> visitorService.getVisitedSites(1L, PageRequest.of(0, 10))
        );
        
        //       
        assertNotNull(exception);
        assertEquals("DATA_ACCESS_ERROR", exception.getErrorCode());
        assertTrue(exception.getMessage().contains("      "));
        assertTrue(exception.isRetryable());
    }
    
    /**
     *              （    ）
     *                         
     */
    @Test
    void testGetVisitedSitesByDateRange_Success() {
        //       
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        Pageable pageable = PageRequest.of(0, 10);
        
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserIdAndVisitDateBetween(anyLong(), any(), any()))
            .thenReturn(feedbacks);
        
        //     
        List<VisitedSiteResponse> result = visitorService.getVisitedSitesByDateRange(
            1L, startDate, endDate, pageable);
        
        //     
        assertNotNull(result);
        assertEquals(2, result.size());
        
        //       
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findByUserIdAndVisitDateBetween(
            1L, startDate, endDate);
    }
    
    /**
     *              （     ）
     *                       
     */
    @Test
    void testGetVisitedSitesByDateRange_NoData() {
        //       
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();
        List<Feedback> feedbacks = Collections.emptyList();
        Pageable pageable = PageRequest.of(0, 10);
        
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserIdAndVisitDateBetween(anyLong(), any(), any()))
            .thenReturn(feedbacks);
        
        //     
        List<VisitedSiteResponse> result = visitorService.getVisitedSitesByDateRange(
            1L, startDate, endDate, pageable);
        
        //     
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
    /**
     *            （    ）
     *                         
     */
    @Test
    void testGetVisitedSitesByRating_Success() {
        //       
        Integer minRating = 4;
        Integer maxRating = 5;
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        Pageable pageable = PageRequest.of(0, 10);
        
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserIdAndRatingBetween(anyLong(), anyInt(), anyInt()))
            .thenReturn(feedbacks);
        
        //     
        List<VisitedSiteResponse> result = visitorService.getVisitedSitesByRating(
            1L, minRating, maxRating, pageable);
        
        //     
        assertNotNull(result);
        assertEquals(2, result.size());
        
        //       
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findByUserIdAndRatingBetween(
            1L, minRating, maxRating);
    }
    
    /**
     *            （        ）
     *             IllegalArgumentException
     */
    @Test
    void testGetVisitedSitesByRating_InvalidRange() {
        //       
        Integer minRating = 5;
        Integer maxRating = 3; //     ：        
        
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        
        //          
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> visitorService.getVisitedSitesByRating(
                1L, minRating, maxRating, PageRequest.of(0, 10))
        );
        
        //       
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("      "));
    }
    
    /**
     *             （    ）
     *                 
     */
    @Test
    void testGetRecommendedSites_Success() {
        //       
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        Pageable pageable = PageRequest.of(0, 10);
        
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findByUserIdAndIsRecommendedAndIsActive(anyLong(), anyBoolean(), anyBoolean()))
            .thenReturn(feedbacks);
        
        //     
        List<VisitedSiteResponse> result = visitorService.getRecommendedSites(1L, pageable);
        
        //     
        assertNotNull(result);
        assertEquals(2, result.size());
        
        //       
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findByUserIdAndIsRecommendedAndIsActive(
            1L, true, true);
    }
    
    /**
     *              （    ）
     *                  
     */
    @Test
    void testGetRecentlyVisitedSites_Success() {
        //       
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findRecentFeedbacksByUser(anyLong(), anyInt()))
            .thenReturn(feedbacks);
        
        //     
        List<VisitedSiteResponse> result = visitorService.getRecentlyVisitedSites(1L, 5);
        
        //     
        assertNotNull(result);
        assertEquals(2, result.size());
        
        //       
        verify(userRepository, times(1)).findByIdAndIsActive(1L, true);
        verify(feedbackRepository, times(1)).findRecentFeedbacksByUser(1L, 5);
    }
    
    /**
     *              （      ）
     *                 
     */
    @Test
    void testGetRecentlyVisitedSites_DefaultLimit() {
        //       
        List<Feedback> feedbacks = Arrays.asList(testFeedback1);
        
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.findRecentFeedbacksByUser(anyLong(), anyInt()))
            .thenReturn(feedbacks);
        
        //     （       ）
        List<VisitedSiteResponse> result = visitorService.getRecentlyVisitedSites(1L, null);
        
        //     
        assertNotNull(result);
        assertEquals(1, result.size());
        
        //         （10）
        verify(feedbackRepository, times(1)).findRecentFeedbacksByUser(1L, 10);
    }
    
    /**
     *             （    ）
     *                  
     */
    @Test
    void testGetVisitedSiteStats_Success() {
        //       
        List<Feedback> feedbacks = Arrays.asList(testFeedback1, testFeedback2);
        List<Object[]> mostVisitedStats = Arrays.asList(
            new Object[]{1L, "     ", 5L},
            new Object[]{2L, "   ", 3L}
        );
        List<Object[]> yearlyStats = Arrays.asList(
            new Object[]{2024, 2L}
        );
        List<Object[]> categoryStats = Arrays.asList(
            new Object[]{"    ", 1L},
            new Object[]{"   ", 1L}
        );
        
        //       
        when(userRepository.findByIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(testUser));
        when(feedbackRepository.countByUserIdAndIsActive(anyLong(), anyBoolean()))
            .thenReturn(2L);
        when(feedbackRepository.calculateAverageRatingByUser(anyLong()))
            .thenReturn(4.5);
        when(feedbackRepository.findMostVisitedSitesByUser(anyLong(), anyInt()))
            .thenReturn(mostVisitedStats);
        when(f