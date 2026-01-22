package com.tourist.feedback.repository;

import com.tourist.feedback.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *          
 *   Spring Data JPA JpaRepository  ，             
 *        、             
 * ViewVisitedSites                    
 */
@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    
    /**
     *           （    ）
     *             
     *              ，      
     * 
     * @param name     
     * @return   Optional  ，             Optional
     */
    Optional<Site> findByName(String name);
    
    /**
     *               （     ）
     *   ViewVisitedSites       ，               
     *   LIKE LOWER               
     * 
     * @param namePrefix       
     * @return        ，     
     */
    List<Site> findByNameStartingWithIgnoreCase(String namePrefix);
    
    /**
     *           
     *           ，       、          
     * 
     * @param category     
     * @return         
     */
    List<Site> findByCategory(String category);
    
    /**
     *             
     *                
     *       ，            
     * 
     * @param location        
     * @return            
     */
    List<Site> findByLocationContaining(String location);
    
    /**
     *             
     *   ViewVisitedSites       ，                
     *   BETWEEN      ，      
     * 
     * @param minRating     （  ）
     * @param maxRating     （  ）
     * @return              
     */
    @Query("SELECT s FROM Site s WHERE s.averageRating BETWEEN :minRating AND :maxRating")
    List<Site> findByAverageRatingBetween(@Param("minRating") Double minRating, 
                                          @Param("maxRating") Double maxRating);
    
    /**
     *           
     *   ViewVisitedSites       ，             
     *   JOIN    Site Feedback ，             
     * 
     * @return           
     */
    @Query("SELECT DISTINCT s FROM Site s JOIN s.feedbacks f WHERE f.isActive = true")
    List<Site> findSitesWithFeedback();
    
    /**
     *               
     * ViewVisitedSites         ：                
     *   JOIN  Site Feedback ，    ID  
     *     ViewVisitedSites           
     * 
     * @param userId   ID
     * @return                 
     */
    @Query("SELECT DISTINCT s FROM Site s " +
           "JOIN s.feedbacks f " +
           "WHERE f.user.id = :userId AND f.isActive = true " +
           "ORDER BY f.visitDate DESC")
    List<Site> findSitesVisitedByUser(@Param("userId") Long userId);
    
    /**
     *            （    ）
     *            ，             
     *          ，      
     * 
     * @return               
     */
    @Query("SELECT s FROM Site s ORDER BY s.visitCount DESC")
    List<Site> findPopularSitesByVisitCount();
    
    /**
     *        （    ≥4.0）
     *       ，             
     * 
     * @return        ，         
     */
    @Query("SELECT s FROM Site s WHERE s.averageRating >= 4.0 ORDER BY s.averageRating DESC")
    List<Site> findHighlyRatedSites();
    
    /**
     *         
     *              
     *                   
     * 
     * @return     ID、            
     */
    @Query(value = "SELECT s.id as siteId, s.name as siteName, COUNT(f.id) as feedbackCount " +
                  "FROM sites s " +
                  "LEFT JOIN feedbacks f ON s.id = f.site_id AND f.is_active = true " +
                  "GROUP BY s.id, s.name " +
                  "ORDER BY feedbackCount DESC", 
           nativeQuery = true)
    List<Object[]> findSiteFeedbackStats();
    
    /**
     *      ：           
     *   ViewVisitedSites       ，         
     *             ，  "    "  
     * 
     * @param userId   ID
     * @param limit         
     * @return            
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
     *                
     *         ，               
     *   ：  "    "     ≥4.0   
     * 
     * @param category     
     * @param minRating       
     * @return          
     */
    @Query("SELECT s FROM Site s WHERE s.category = :category AND s.averageRating >= :minRating " +
           "ORDER BY s.averageRating DESC")
    List<Site> findByCategoryAndAverageRatingGreaterThanEqual(
            @Param("category") String category, 
            @Param("minRating") Double minRating);
    
    /**
     *            
     *              ，          
     *   exists      ，              
     * 
     * @param name     
     * @return true         ，false         
     */
    boolean existsByName(String name);
    
    /**
     *         
     *                 ，          
     *     SQL            
     * 
     * @param siteId   ID
     */
    @Query(value = "UPDATE sites s " +
                  "SET average_rating = (SELECT AVG(rating) FROM feedbacks " +
                  "WHERE site_id = :siteId AND is_active = true) " +
                  "WHERE s.id = :siteId", 
           nativeQuery = true)
    void updateAverageRating(@Param("siteId") Long siteId);
    
    /**
     *     （       ）
     *   ViewVisitedSites         ，          
     *         ，   Service         
     * 
     * @param name        （  ）
     * @param category     （  ）
     * @param minRating     （  ）
     * @param maxRating     （  ）
     * @param location        （  ）
     * @return          
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