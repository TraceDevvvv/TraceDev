package com.etour.repository;

import com.etour.entity.AgencyOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for AgencyOperator entity.
 * Provides CRUD operations and custom queries for agency operator data access.
 * Includes methods for authentication and authorization.
 */
@Repository
public interface AgencyRepository extends JpaRepository<AgencyOperator, Long> {
    
    /**
     * Finds an agency operator by username.
     * 
     * @param username Username to search for
     * @return Optional containing AgencyOperator if found
     */
    Optional<AgencyOperator> findByUsername(String username);
    
    /**
     * Finds an agency operator by email.
     * 
     * @param email Email address to search for
     * @return Optional containing AgencyOperator if found
     */
    Optional<AgencyOperator> findByEmail(String email);
    
    /**
     * Finds agency operators by role.
     * 
     * @param role Role to search for (e.g., "ADMIN", "AGENCY_MANAGER", "AGENCY_OPERATOR")
     * @return List of agency operators with the specified role
     */
    List<AgencyOperator> findByRole(String role);
    
    /**
     * Finds agency operators by active status.
     * 
     * @param active Active status (true/false)
     * @return List of agency operators with the specified active status
     */
    List<AgencyOperator> findByActive(Boolean active);
    
    /**
     * Finds agency operators by role and active status.
     * 
     * @param role Role to search for
     * @param active Active status
     * @return List of agency operators matching both criteria
     */
    List<AgencyOperator> findByRoleAndActive(String role, Boolean active);
    
    /**
     * Checks if an agency operator with the given username exists.
     * 
     * @param username Username to check
     * @return true if an operator with the username exists, false otherwise
     */
    boolean existsByUsername(String username);
    
    /**
     * Checks if an agency operator with the given email exists.
     * 
     * @param email Email to check
     * @return true if an operator with the email exists, false otherwise
     */
    boolean existsByEmail(String email);
    
    /**
     * Checks if an agency operator with the given username exists (excluding a specific operator).
     * Used for validation during updates to ensure username uniqueness.
     * 
     * @param username Username to check
     * @param id Operator ID to exclude from the check
     * @return true if another operator with the username exists, false otherwise
     */
    @Query("SELECT COUNT(a) > 0 FROM AgencyOperator a WHERE a.username = :username AND a.id != :id")
    boolean existsByUsernameAndIdNot(@Param("username") String username, @Param("id") Long id);
    
    /**
     * Checks if an agency operator with the given email exists (excluding a specific operator).
     * Used for validation during updates to ensure email uniqueness.
     * 
     * @param email Email to check
     * @param id Operator ID to exclude from the check
     * @return true if another operator with the email exists, false otherwise
     */
    @Query("SELECT COUNT(a) > 0 FROM AgencyOperator a WHERE a.email = :email AND a.id != :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);
    
    /**
     * Finds agency operators who have logged in after a specific date.
     * 
     * @param date The cutoff date
     * @return List of agency operators who logged in after the specified date
     */
    List<AgencyOperator> findByLastLoginAfter(java.time.LocalDateTime date);
    
    /**
     * Finds agency operators who have not logged in since a specific date.
     * 
     * @param date The cutoff date
     * @return List of agency operators who haven't logged in since the specified date
     */
    List<AgencyOperator> findByLastLoginBeforeOrLastLoginIsNull(java.time.LocalDateTime date);
    
    /**
     * Counts agency operators by role.
     * 
     * @param role Role to count
     * @return Number of agency operators with the specified role
     */
    long countByRole(String role);
    
    /**
     * Counts agency operators by active status.
     * 
     * @param active Active status
     * @return Number of agency operators with the specified active status
     */
    long countByActive(Boolean active);
    
    /**
     * Authenticates an agency operator by username and password.
     * This method retrieves the operator by username and then verifies the password.
     * Note: Password verification should be done in the service layer using the entity's authenticate method.
     * 
     * @param username Username to authenticate
     * @return Optional containing AgencyOperator if found (password verification needed separately)
     */
    default Optional<AgencyOperator> authenticate(String username) {
        return findByUsername(username);
    }
    
    /**
     * Finds agency operators with a specific permission.
     * This checks both direct permissions and role-based permissions.
     * 
     * @param permission Permission to check (e.g., "TOURIST_MODIFY", "TOURIST_VIEW")
     * @return List of agency operators with the specified permission
     */
    @Query("SELECT a FROM AgencyOperator a WHERE " +
           "(:permission MEMBER OF a.permissions) OR " +
           "(a.role = 'ADMIN') OR " +
           "(a.role = 'AGENCY_MANAGER' AND :permission LIKE 'TOURIST_%') OR " +
           "(a.role = 'AGENCY_OPERATOR' AND (:permission = 'TOURIST_MODIFY' OR :permission = 'TOURIST_VIEW'))")
    List<AgencyOperator> findByPermission(@Param("permission") String permission);
    
    /**
     * Finds agency operators created within a date range.
     * 
     * @param startDate Start date (inclusive)
     * @param endDate End date (inclusive)
     * @return List of agency operators created within the date range
     */
    List<AgencyOperator> findByCreatedAtBetween(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
    
    /**
     * Updates the last login timestamp for an agency operator.
     * 
     * @param id Operator ID
     * @param lastLogin New last login timestamp
     * @return Number of rows updated (should be 1 if successful)
     */
    @Query("UPDATE AgencyOperator a SET a.lastLogin = :lastLogin WHERE a.id = :id")
    int updateLastLogin(@Param("id") Long id, @Param("lastLogin") java.time.LocalDateTime lastLogin);
}