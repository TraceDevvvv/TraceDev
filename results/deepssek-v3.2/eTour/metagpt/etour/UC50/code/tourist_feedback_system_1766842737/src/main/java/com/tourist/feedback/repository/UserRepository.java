package com.tourist.feedback.repository;

import com.tourist.feedback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *          
 *   Spring Data JPA JpaRepository  ，             
 *        、             
 *   Spring Data JPA               
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     *          
     *              
     *   ：            ，          
     * 
     * @param username    
     * @return   Optional  ，         Optional
     */
    Optional<User> findByUsername(String username);
    
    /**
     *         
     *                
     *             ，              
     * 
     * @param email     
     * @return   Optional  ，         Optional
     */
    Optional<User> findByEmail(String email);
    
    /**
     *           
     *                
     *   exists   find     ，              
     * 
     * @param username        
     * @return true        ，false      
     */
    boolean existsByUsername(String username);
    
    /**
     *          
     *               
     *              
     * 
     * @param email       
     * @return true       ，false      
     */
    boolean existsByEmail(String email);
    
    /**
     *     ID         
     *         （     ）
     *     ID         ，         
     * 
     * @param id   ID
     * @param isActive       （true:  ，false:  ）
     * @return        Optional  
     */
    Optional<User> findByIdAndIsActive(Long id, Boolean isActive);
    
    /**
     *               
     *             
     *   ：                ，           
     * 
     * @param username    
     * @param passwordHash      
     * @return   Optional  
     */
    Optional<User> findByUsernameAndPasswordHash(String username, String passwordHash);
    
    /**
     *    JPQL  ：          
     *            
     *   JOIN FETCH      ，  N+1    
     * 
     * @param minFeedbackCount       
     * @return     
     */
    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.feedbacks f WHERE SIZE(u.feedbacks) >= :minFeedbackCount")
    java.util.List<User> findUsersWithMinFeedbackCount(@Param("minFeedbackCount") int minFeedbackCount);
    
    /**
     *      SQL  ：         
     *              
     *       SQL           
     * 
     * @param days      
     * @return   ID  
     */
    @Query(value = "SELECT u.id FROM users u " +
                  "INNER JOIN feedbacks f ON u.id = f.user_id " +
                  "WHERE f.created_at >= DATE_SUB(NOW(), INTERVAL :days DAY) " +
                  "GROUP BY u.id " +
                  "HAVING COUNT(f.id) > 0 " +
                  "ORDER BY MAX(f.created_at) DESC", 
           nativeQuery = true)
    java.util.List<Long> findActiveUserIdsLastNDays(@Param("days") int days);
    
    /**
     *           
     *   count                 
     *                      
     * 
     * @param isActive         ，null        
     * @return     
     */
    long countByIsActive(Boolean isActive);
    
    /**
     *            （     ）
     *                
     *   LIKE LOWER               
     * 
     * @param usernamePrefix      
     * @return           
     */
    java.util.List<User> findByUsernameStartingWithIgnoreCase(String usernamePrefix);
}