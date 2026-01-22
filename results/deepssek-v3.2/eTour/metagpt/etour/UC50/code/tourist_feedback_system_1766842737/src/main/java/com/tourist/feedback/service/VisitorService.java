```
package com.tourist.feedback.service;

import com.tourist.feedback.dto.VisitedSiteResponse;
import com.tourist.feedback.entity.User;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ViewVisitedSites         
 *                     ，        、         
 *       ViewVisitedSites         ，  ：
 * 1.             （    ）
 * 2.         （   、  、     ）
 * 3.        
 * 4.          
 *     ViewVisitedSites           
 */
public interface VisitorService {
    
    /**
     *             （    ）
     * ViewVisitedSites         ：    ID                 
     *       P0  ：               
     *                   ，       "      ≤3 "   
     * 
     * @param userId        ，         ID（    ：       ）
     * @param pageable     ，                （P1  ：      ）
     * @return           ，          、         
     * @throws com.tourist.feedback.exception.AuthenticationException              
     * @throws com.tourist.feedback.exception.UserNotFoundException           
     * @throws com.tourist.feedback.exception.DataAccessException               
     */
    List<VisitedSiteResponse> getVisitedSites(Long userId, Pageable pageable);
    
    /**
     *             （       ）
     *                ，      "        "        
     *            （    、  ）     
     * 
     * @param userId        
     * @param startDate     （  ），            
     * @param endDate     （  ），            
     * @param pageable     
     * @return               
     * @throws IllegalArgumentException         （           ）
     */
    List<VisitedSiteResponse> getVisitedSitesByDateRange(
            Long userId, 
            LocalDateTime startDate, 
            LocalDateTime endDate, 
            Pageable pageable);
    
    /**
     *             （     ）
     *              ，  P1           
     *                     
     * 
     * @param userId        
     * @param minRating     （  ，1-5），         1
     * @param maxRating     （  ，1-5），         5
     * @param pageable     
     * @return               
     * @throws IllegalArgumentException         （ minRating > maxRating）
     */
    List<VisitedSiteResponse> getVisitedSitesByRating(
            Long userId, 
            Integer minRating, 
            Integer maxRating, 
            Pageable pageable);
    
    /**
     *             
     *       "      "   ，              
     *                  
     * 
     * @param userId        
     * @param pageable     
     * @return           
     */
    List<VisitedSiteResponse> getRecommendedSites(Long userId, Pageable pageable);
    
    /**
     *              
     *   P1        ，           
     *                   ，        
     * 
     * @param userId        
     * @param limit         ，           
     * @return            
     */
    List<VisitedSiteResponse> getRecentlyVisitedSites(Long userId, Integer limit);
    
    /**
     *               
     *       "      "   ，        
     *              ，             
     * 
     * @param userId        
     * @return              
     */
    VisitedSiteStats getVisitedSiteStats(Long userId);
    
    /**
     *           
     *   P1        ，       、         
     *                     
     * 
     * @param userId        
     * @param keyword      ，       、       
     * @param pageable     
     * @return               
     */
    List<VisitedSiteResponse> searchVisitedSites(
            Long userId, 
            String keyword, 
            Pageable pageable);
    
    /**
     *               
     *            ，         
     *   count      ，      
     * 
     * @param userId        
     * @return           
     */
    long countVisitedSites(Long userId);
    
    /**
     *         
     *   P0            ，              
     *          ：                
     * 
     * @param userId        
     * @return         
     * @throws com.tourist.feedback.exception.AuthenticationException              
     * @throws com.tourist.feedback.exception.UserNotFoundException           
     */
    User validateUserAccess(Long userId);
    
    /**
     *             
     *   P1          ，     CSV  
     *       "      "            
     * 
     * @param userId        
     * @param format     （  CSV、JSON ）
     * @return           
     */
    String exportVisitedSites(Long userId, String format);
    
    /**
     *             
     *         ：  ETOUR         ，         
     *               ，       
     * 
     * @param userId        
     * @return     ，           ；       
     * @throws com.tourist.feedback.exception.ServiceUnavailableException          
     */
    List<VisitedSiteResponse> getVisitedSitesWithFallback(Long userId);
}