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
 *          
 *   Spring Data JPA JpaRepository  ，             
 *    ViewVisitedSites           ，       、      
 *     ViewVisitedSites           
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
    /**
     *     ID        
     * ViewVisitedSites         ：             
     *   JOIN FETCH    ，            ，  N+1    
     * 
     * @param userId   ID
     * @return            ，         
     */
    @Query("SELECT f FROM Feedback f JOIN FETCH f.site WHERE f.user.id = :userId AND f.isActive = true ORDER BY f.visitDate DESC")
    List<Feedback> findByUserId(@Param("userId") Long userId);
    
    /**
     *     ID   ID      
     *                    ，      
     * 
     * @param userId   ID
     * @param siteId   ID
     * @return          Optional  
     */
    Optional<Feedback> findByUserIdAndSiteId(Long userId, Long siteId);
    
    /**
     *     ID   ID         
     *   ViewVisitedSites     ，            
     * 
     * @param userId   ID
     * @param siteId   ID
     * @param isActive     
     * @return          Optional  
     */
    Optional<Feedback> findByUserIdAndSiteIdAndIsActive(Long userId, Long siteId, Boolean isActive);
    
    /**
     *     ID      （    ）
     * ViewVisitedSites         ，           
     *   JOIN FETCH    ，      
     * 
     * @param userId   ID
     * @param isActive     
     * @return          
     */
    List<Feedback> findByUserIdAndIsActive(Long userId, Boolean isActive);
    
    /**
     *     ID        
     *         ，             
     * 
     * @param siteId   ID
     * @return            ，         
     */
    @Query("SELECT f FROM Feedback f JOIN FETCH f.user WHERE f.site.id = :siteId AND f.isActive = true ORDER BY f.createdAt DESC")
    List<Feedback> findBySiteId(@Param("siteId") Long siteId);
    
    /**
     *             
     *   ViewVisitedSites       ，                
     *   BETWEEN      ，      
     * 
     * @param userId   ID
     * @param minRating     （  ）
     * @param maxRating     （  ）
     * @return                
     */
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.rating BETWEEN :minRating AND :maxRating AND f.isActive = true ORDER BY f.visitDate DESC")
    List<Feedback> findByUserIdAndRatingBetween(@Param("userId") Long userId, 
                                                @Param("minRating") Integer minRating, 
                                                @Param("maxRating") Integer maxRating);
    
    /**
     *               
     *   ViewVisitedSites         ，              
     *   ：  2024 1        
     * 
     * @param userId   ID
     * @param startDate     （  ）
     * @param endDate     （  ）
     * @return                  
     */
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.visitDate BETWEEN :startDate AND :endDate AND f.isActive = true ORDER BY f.visitDate DESC")
    List<Feedback> findByUserIdAndVisitDateBetween(@Param("userId") Long userId, 
                                                   @Param("startDate") LocalDateTime startDate, 
                                                   @Param("endDate") LocalDateTime endDate);
    
    /**
     *              （    ）
     *   ViewVisitedSites       ，           
     *     SQL    ，          
     * 
     * @param userId   ID
     * @param limit         
     * @return              
     */
    @Query(value = "SELECT f.* FROM feedbacks f " +
                  "WHERE f.user_id = :userId AND f.is_active = true " +
                  "ORDER BY f.visit_date DESC " +
                  "LIMIT :limit", 
           nativeQuery = true)
    List<Feedback> findRecentFeedbacksByUser(@Param("userId") Long userId, 
                                             @Param("limit") int limit);
    
    /**
     *               
     *   ViewVisitedSites       ，              
     * 
     * @param userId   ID
     * @param isRecommended     
     * @return               
     */
    List<Feedback> findByUserIdAndIsRecommendedAndIsActive(Long userId, Boolean isRecommended, Boolean isActive);
    
    /**
     *           
     *   ViewVisitedSites       ，          
     *   count      ，           
     * 
     * @param userId   ID
     * @return          
     */
    long countByUserIdAndIsActive(Long userId, Boolean isActive);
    
    /**
     *          
     *              ，           
     *     SQL       ，   Java   
     * 
     * @param userId   ID
     * @return        
     */
    @Query(value = "SELECT AVG(rating) FROM feedbacks WHERE user_id = :userId AND is_active = true", 
           nativeQuery = true)
    Double calculateAverageRatingByUser(@Param("userId") Long userId);
    
    /**
     *             
     *   ViewVisitedSites       ，                
     *   IS NOT NULL LENGTH>0        
     * 
     * @param userId   ID
     * @return             
     */
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.comment IS NOT NULL AND LENGTH(f.comment) > 0 AND f.isActive = true ORDER BY f.visitDate DESC")
    List<Feedback> findFeedbacksWithCommentsByUser(@Param("userId") Long userId);
    
    /**
     *               
     *   ViewVisitedSites         ，         
     *         ，   Service         
     * 
     * @param userId   ID（  ）
     * @param siteId   ID（  ）
     * @param minRating     （  ）
     * @param maxRating     （  ）
     * @param startDate     （  ）
     * @param endDate     （  ）
     * @param isRecommended     （  ）
     * @return            
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
     *               
     *   ViewVisitedSites       ，         
     *                    
     * 
     * @param userId   ID
     * @return               
     */
    @Query(value = "SELECT s.category, COUNT(f.id) as count FROM feedbacks f " +
                  "INNER JOIN sites s ON f.site_id = s.id " +
                  "WHERE f.user_id = :userId AND f.is_active = true " +
                  "GROUP BY s.category " +
                  "ORDER BY count DESC", 
           nativeQuery = true)
    List<Object[]> findSiteCategoryStatsByUser(@Param("userId") Long userId);
    
    /**
     *            
     *   ViewVisitedSites       ，           
     *            
     * 
     * @param userId   ID
     * @return               
     */
    @Query(value = "SELECT YEAR(visit_date) as year, COUNT(id) as count FROM feedbacks " +
                  "WHERE user_id = :userId AND is_active = true " +
                  "GROUP BY YEAR(visit_date) " +
                  "ORDER BY year DESC", 
           nativeQuery = true)
    List<Object[]> findYearlyVisitStatsByUser(@Param("userId") Long userId);
    
    /**
     *                  
     *            ，      
     *   exists      ，      
     * 
     * @param userId   ID
     * @param siteId   ID
     * @return true       ，false         
     */
    boolean existsByUserIdAndSiteIdAndIsActive(Long userId, Long siteId, Boolean isActive);
    
    /**
     *         
     *              
     *     SQL        
     * 
     * @param userId   ID
     * @param isActive      
     * @return       
     */
    @Query(value = "UPDATE feedbacks SET is_active = :isActive, updated_at = NOW() " +
                  "WHERE user_id = :userId", 
           nativeQuery = true)
    int updateIsActiveByUserId(@Param("userId") Long userId, 
                               @Param("isActive") Boolean isActive);
    
    /**
     *             （  ≥4）
     *   ViewVisitedSites   "       "  
     * 
     * @param userId   ID
     * @param minRating       
     * @return          
     */
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId AND f.rating >= :minRating AND f.isActive = true ORDER BY f.rating DESC, f.visitDate DESC")
    List<Feedback> findHighRatedFeedbacksByUser(@Param("userId") Long userId, 
                                                @Param("minRating") Integer minRating);
    
    /**
     *              
     *   ViewVisitedSites       ，           
     *   ：                 ，            
     * 
     * @param userId   ID
     * @param limit         
     * @return     ID、            
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