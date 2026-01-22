      Task 5，  VisitorService      。    ViewVisitedSites           ，        、    、                。

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
 * VisitorService      
 *   ViewVisitedSites         ，        、    、           
 *         P0、P1   P2       ，             
 * 
 *     ：
 * 1.          （P0-001）
 * 2.         （P0-002）
 * 3.         （P0-003）
 * 4.       （P0-004）
 * 5.          （P0-005）
 * 6.   、  、    （P1  ）
 * 7.          （P1  ）
 * 8.         （    ）
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VisitorServiceImpl implements VisitorService {
    
    //     ：            
    private final UserRepository userRepository;
    private final SiteRepository siteRepository;
    private final FeedbackRepository feedbackRepository;
    
    //      （   ）
    private Map<Long, List<VisitedSiteResponse>> responseCache = new HashMap<>();
    private final Object cacheLock = new Object();
    
    //     
    private static final int DEFAULT_RECENT_LIMIT = 10;
    private static final int CACHE_EXPIRY_HOURS = 6;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     *             （    ）
     * ViewVisitedSites         ：    ID                 
     *     ：
     * 1.         （    ：       ）
     * 2.          （    ：            ）
     * 3.      DTO  
     * 4.       
     * 
     * @param userId        ，         ID
     * @param pageable     ，    、         
     * @return           ，         
     * @throws AuthenticationException              
     * @throws UserNotFoundException           
     * @throws DataAccessException               
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getVisitedSites(Long userId, Pageable pageable) {
        log.info("              ，  ID: {},     : {}", userId, pageable);
        
        try {
            //   1：        
            User user = validateUserAccess(userId);
            log.debug("      : {}", user.getUsername());
            
            //   2：         
            //   JOIN FETCH    ，            
            List<Feedback> feedbacks = feedbackRepository.findByUserId(userId);
            log.debug("           : {}", feedbacks.size());
            
            //   3：     DTO  
            List<VisitedSiteResponse> responses = convertFeedbacksToResponses(feedbacks);
            
            //   4：      
            return applyPagination(responses, pageable);
            
        } catch (AuthenticationException | UserNotFoundException e) {
            //       ，      
            throw e;
        } catch (Exception e) {
            //         ，   DataAccessException
            log.error("                 ，  ID: {}", userId, e);
            throw new DataAccessException("          ，     ", "FEEDBACK_QUERY_ERROR", e);
        }
    }
    
    /**
     *             （       ）
     *     ：
     * 1.             
     * 2.         
     * 3.               
     * 4.      DTO       
     * 
     * @param userId        
     * @param startDate     （  ），null     
     * @param endDate     （  ），null     
     * @param pageable     
     * @return               
     * @throws IllegalArgumentException         
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getVisitedSitesByDateRange(
            Long userId, 
            LocalDateTime startDate, 
            LocalDateTime endDate, 
            Pageable pageable) {
        
        log.info("                ，  ID: {},     : {},     : {}", 
                userId, startDate, endDate);
        
        try {
            //             
            validateDateRange(startDate, endDate);
            
            //         
            User user = validateUserAccess(userId);
            log.debug("            : {}", user.getUsername());
            
            //               
            List<Feedback> feedbacks;
            if (startDate != null && endDate != null) {
                feedbacks = feedbackRepository.findByUserIdAndVisitDateBetween(userId, startDate, endDate);
            } else if (startDate != null) {
                //        
                feedbacks = feedbackRepository.findByUserIdAndVisitDateBetween(
                    userId, startDate, LocalDateTime.now());
            } else if (endDate != null) {
                //        
                feedbacks = feedbackRepository.findByUserIdAndVisitDateBetween(
                    userId, LocalDateTime.MIN, endDate);
            } else {
                //      ，    
                feedbacks = feedbackRepository.findByUserId(userId);
            }
            
            log.debug("               : {}", feedbacks.size());
            
            //      DTO       
            List<VisitedSiteResponse> responses = convertFeedbacksToResponses(feedbacks);
            return applyPagination(responses, pageable);
            
        } catch (IllegalArgumentException e) {
            log.error("        ", e);
            throw e;
        } catch (Exception e) {
            log.error("                  ", e);
            throw new DataAccessException("         ", "DATE_RANGE_QUERY_ERROR", e);
        }
    }
    
    /**
     *             （     ）
     *     ：
     * 1.             
     * 2.         （     null）
     * 3.         
     * 4.               
     * 5.      DTO       
     * 
     * @param userId        
     * @param minRating     （1-5），null      1
     * @param maxRating     （1-5），null      5
     * @param pageable     
     * @return               
     * @throws IllegalArgumentException         
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getVisitedSitesByRating(
            Long userId, 
            Integer minRating, 
            Integer maxRating, 
            Pageable pageable) {
        
        log.info("              ，  ID: {},     : {},     : {}", 
                userId, minRating, maxRating);
        
        try {
            //         
            int effectiveMinRating = (minRating != null) ? minRating : 1;
            int effectiveMaxRating = (maxRating != null) ? maxRating : 5;
            
            //             
            validateRatingRange(effectiveMinRating, effectiveMaxRating);
            
            //         
            User user = validateUserAccess(userId);
            log.debug("            : {}", user.getUsername());
            
            //               
            List<Feedback> feedbacks = feedbackRepository.findByUserIdAndRatingBetween(
                userId, effectiveMinRating, effectiveMaxRating);
            
            log.debug("               : {}", feedbacks.size());
            
            //      DTO       
            List<VisitedSiteResponse> responses = convertFeedbacksToResponses(feedbacks);
            return applyPagination(responses, pageable);
            
        } catch (IllegalArgumentException e) {
            log.error("        ", e);
            throw e;
        } catch (Exception e) {
            log.error("                  ", e);
            throw new DataAccessException("         ", "RATING_RANGE_QUERY_ERROR", e);
        }
    }
    
    /**
     *             
     *     ：
     * 1.         
     * 2.       （isRecommended=true）     
     * 3.      DTO       
     * 
     * @param userId        
     * @param pageable     
     * @return           
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getRecommendedSites(Long userId, Pageable pageable) {
        log.info("            ，  ID: {}", userId);
        
        try {
            //         
            User user = validateUserAccess(userId);
            log.debug("            : {}", user.getUsername());
            
            //            
            List<Feedback> feedbacks = feedbackRepository.findByUserIdAndIsRecommendedAndIsActive(
                userId, true, true);
            
            log.debug("             : {}", feedbacks.size());
            
            //      DTO       
            List<VisitedSiteResponse> responses = convertFeedbacksToResponses(feedbacks);
            return applyPagination(responses, pageable);
            
        } catch (Exception e) {
            log.error("             ", e);
            throw new DataAccessException("        ", "RECOMMENDED_QUERY_ERROR", e);
        }
    }
    
    /**
     *              
     *     ：
     * 1.         
     * 2.         
     * 3.            （  SQL    ）
     * 4.      DTO  
     * 
     * @param userId        
     * @param limit         ，null      
     * @return            
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> getRecentlyVisitedSites(Long userId, Integer limit) {
        log.info("             ，  ID: {},     : {}", userId, limit);
        
        try {
            //         
            User user = validateUserAccess(userId);
            log.debug("            : {}", user.getUsername());
            
            //         
            int effectiveLimit = (limit != null && limit > 0) ? limit : DEFAULT_RECENT_LIMIT;
            
            //            （    SQL    ）
            List<Feedback> feedbacks = feedbackRepository.findRecentFeedbacksByUser(userId, effectiveLimit);
            
            log.debug("             : {}", feedbacks.size());
            
            //      DTO  
            return convertFeedbacksToResponses(feedbacks);
            
        } catch (Exception e) {
            log.error("               ", e);
            throw new DataAccessException("          ", "RECENT_VISITS_QUERY_ERROR", e);
        }
    }
    
    /**
     *               
     *     ：
     * 1.         
     * 2.          
     * 3.         
     * 4.           
     * 5.           
     * 6.         
     * 
     * @param userId        
     * @return              
     */
    @Override
    @Transactional(readOnly = true)
    public VisitedSiteStats getVisitedSiteStats(Long userId) {
        log.info("              ，  ID: {}", userId);
        
        try {
            //         
            User user = validateUserAccess(userId);
            log.debug("            : {}", user.getUsername());
            
            //          
            long totalVisits = feedbackRepository.countByUserIdAndIsActive(userId, true);
            
            //         
            Double averageRating = feedbackRepository.calculateAverageRatingByUser(userId);
            if (averageRating == null) {
                averageRating = 0.0;
            }
            
            //           （         ）
            List<Object[]> mostVisitedStats = feedbackRepository.findMostVisitedSitesByUser(userId, 5);
            Map<String, Long> mostVisitedSites = new LinkedHashMap<>();
            for (Object[] stat : mostVisitedStats) {
                Long siteId = ((Number) stat[0]).longValue();
                String siteName = (String) stat[1];
                Long visitCount = ((Number) stat[2]).longValue();
                mostVisitedSites.put(siteName, visitCount);
            }
            
            //           
            List<Object[]> yearlyStats = feedbackRepository.findYearlyVisitStatsByUser(userId);
            Map<Integer, Long> yearlyVisits = new TreeMap<>(Collections.reverseOrder());
            for (Object[] stat : yearlyStats) {
                Integer year = ((Number) stat[0]).intValue();
                Long count = ((Number) stat[1]).longValue();
                yearlyVisits.put(year, count);
            }
            
            //             
            List<Object[]> categoryStats = feedbackRepository.findSiteCategoryStatsByUser(userId);
            Map<String, Long> categoryVisits = new HashMap<>();
            for (Object[] stat : categoryStats) {
                String category = (String) stat[0];
                Long count = ((Number) stat[1]).longValue();
                if (category != null) {
                    categoryVisits.put(category, count);
                }
            }
            
            //           
            List<Feedback> recommendedFeedbacks = feedbackRepository.findByUserIdAndIsRecommendedAndIsActive(
                userId, true, true);
            long recommendedCount = recommendedFeedbacks.size();
            
            //              
            List<Feedback> commentedFeedbacks = feedbackRepository.findFeedbacksWithCommentsByUser(userId);
            long commentedCount = commentedFeedbacks.size();
            
            //            
            return VisitedSiteStats.builder()
                    .userId(userId)
                    .totalVisits(totalVisits)
                    .averageRating(Math.round(averageRating * 10.0) / 10.0) //   1   
                    .recommendedCount(recommendedCount)
                    .commentedCount(commentedCount)
                    .mostVisitedSites(mostVisitedSites)
                    .yearlyVisits(yearlyVisits)
                    .categoryVisits(categoryVisits)
                    .build();
            
        } catch (Exception e) {
            log.error("               ", e);
            throw new DataAccessException("        ", "STATS_QUERY_ERROR", e);
        }
    }
    
    /**
     *           
     *     ：
     * 1.         
     * 2.        （           ）
     * 3.       
     * 4.      DTO       
     * 
     * @param userId        
     * @param keyword      ，       、       
     * @param pageable     
     * @return               
     */
    @Override
    @Transactional(readOnly = true)
    public List<VisitedSiteResponse> searchVisitedSites(
            Long userId, 
           