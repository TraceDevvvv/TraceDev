package com.etour.repository;

import com.etour.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for AuditLog entity.
 * Provides CRUD operations and custom queries for audit log data access.
 * Used to track all modifications to tourist accounts for audit and compliance.
 */
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    
    /**
     * Finds audit logs by action type.
     * 
     * @param action Action type (e.g., "UPDATE_TOURIST", "CREATE_TOURIST")
     * @return List of audit logs with the specified action
     */
    List<AuditLog> findByAction(String action);
    
    /**
     * Finds audit logs by entity type.
     * 
     * @param entityType Entity type (e.g., "Tourist", "AgencyOperator")
     * @return List of audit logs for the specified entity type
     */
    List<AuditLog> findByEntityType(String entityType);
    
    /**
     * Finds audit logs by entity ID.
     * 
     * @param entityId Entity ID
     * @return List of audit logs for the specified entity
     */
    List<AuditLog> findByEntityId(Long entityId);
    
    /**
     * Finds audit logs by entity type and entity ID.
     * 
     * @param entityType Entity type
     * @param entityId Entity ID
     * @return List of audit logs for the specified entity
     */
    List<AuditLog> findByEntityTypeAndEntityId(String entityType, Long entityId);
    
    /**
     * Finds audit logs by who performed the action.
     * 
     * @param performedBy Username or identifier of who performed the action
     * @return List of audit logs performed by the specified user
     */
    List<AuditLog> findByPerformedBy(String performedBy);
    
    /**
     * Finds audit logs by success status.
     * 
     * @param successful Success status (true/false)
     * @return List of audit logs with the specified success status
     */
    List<AuditLog> findBySuccessful(Boolean successful);
    
    /**
     * Finds audit logs performed within a date range.
     * 
     * @param startDate Start date (inclusive)
     * @param endDate End date (inclusive)
     * @return List of audit logs performed within the date range
     */
    List<AuditLog> findByPerformedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Finds audit logs by IP address.
     * 
     * @param ipAddress IP address
     * @return List of audit logs from the specified IP address
     */
    List<AuditLog> findByIpAddress(String ipAddress);
    
    /**
     * Complex query to find audit logs with multiple criteria.
     * All parameters are optional - null parameters are ignored in the query.
     * 
     * @param action Action type
     * @param entityType Entity type
     * @param entityId Entity ID
     * @param performedBy Performed by
     * @param successful Success status
     * @param startDate Start date
     * @param endDate End date
     * @return List of matching audit logs
     */
    @Query("SELECT a FROM AuditLog a WHERE " +
           "(:action IS NULL OR a.action = :action) AND " +
           "(:entityType IS NULL OR a.entityType = :entityType) AND " +
           "(:entityId IS NULL OR a.entityId = :entityId) AND " +
           "(:performedBy IS NULL OR a.performedBy = :performedBy) AND " +
           "(:successful IS NULL OR a.successful = :successful) AND " +
           "(:startDate IS NULL OR a.performedAt >= :startDate) AND " +
           "(:endDate IS NULL OR a.performedAt <= :endDate) " +
           "ORDER BY a.performedAt DESC")
    List<AuditLog> searchAuditLogs(
            @Param("action") String action,
            @Param("entityType") String entityType,
            @Param("entityId") Long entityId,
            @Param("performedBy") String performedBy,
            @Param("successful") Boolean successful,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
    
    /**
     * Counts audit logs by action type.
     * 
     * @param action Action type
     * @return Number of audit logs with the specified action
     */
    long countByAction(String action);
    
    /**
     * Counts audit logs by entity type.
     * 
     * @param entityType Entity type
     * @return Number of audit logs for the specified entity type
     */
    long countByEntityType(String entityType);
    
    /**
     * Counts audit logs by success status.
     * 
     * @param successful Success status
     * @return Number of audit logs with the specified success status
     */
    long countBySuccessful(Boolean successful);
    
    /**
     * Counts audit logs by performed by user.
     * 
     * @param performedBy Performed by user
     * @return Number of audit logs performed by the specified user
     */
    long countByPerformedBy(String performedBy);
    
    /**
     * Finds the most recent audit logs for a specific entity.
     * 
     * @param entityType Entity type
     * @param entityId Entity ID
     * @param limit Maximum number of logs to return
     * @return List of recent audit logs for the entity
     */
    @Query("SELECT a FROM AuditLog a WHERE a.entityType = :entityType AND a.entityId = :entityId " +
           "ORDER BY a.performedAt DESC LIMIT :limit")
    List<AuditLog> findRecentByEntity(
            @Param("entityType") String entityType,
            @Param("entityId") Long entityId,
            @Param("limit") int limit);
    
    /**
     * Finds failed audit logs (unsuccessful operations).
     * 
     * @param limit Maximum number of logs to return
     * @return List of recent failed audit logs
     */
    @Query("SELECT a FROM AuditLog a WHERE a.successful = false " +
           "ORDER BY a.performedAt DESC LIMIT :limit")
    List<AuditLog> findRecentFailures(@Param("limit") int limit);
    
    /**
     * Finds audit logs for tourist modifications.
     * Specifically looks for UPDATE_TOURIST actions.
     * 
     * @param touristId Tourist ID
     * @param limit Maximum number of logs to return
     * @return List of modification audit logs for the tourist
     */
    default List<AuditLog> findTouristModifications(Long touristId, int limit) {
        return findRecentByEntity("Tourist", touristId, limit)
                .stream()
                .filter(log -> "UPDATE_TOURIST".equals(log.getAction()))
                .toList();
    }
    
    /**
     * Checks if an entity has been modified within a time period.
     * 
     * @param entityType Entity type
     * @param entityId Entity ID
     * @param sinceDate Check modifications since this date
     * @return true if the entity has been modified since the date, false otherwise
     */
    @Query("SELECT COUNT(a) > 0 FROM AuditLog a WHERE " +
           "a.entityType = :entityType AND a.entityId = :entityId AND " +
           "a.action LIKE '%UPDATE%' AND a.performedAt >= :sinceDate")
    boolean hasBeenModifiedSince(
            @Param("entityType") String entityType,
            @Param("entityId") Long entityId,
            @Param("sinceDate") LocalDateTime sinceDate);
    
    /**
     * Deletes old audit logs before a specific date.
     * Used for audit log rotation and cleanup.
     * 
     * @param cutoffDate Delete logs before this date
     * @return Number of logs deleted
     */
    @Query("DELETE FROM AuditLog a WHERE a.performedAt < :cutoffDate")
    int deleteOldLogs(@Param("cutoffDate") LocalDateTime cutoffDate);
    
    /**
     * Gets audit log statistics by date range.
     * Returns counts grouped by action type.
     * 
     * @param startDate Start date
     * @param endDate End date
     * @return List of Object arrays where [0] = action, [1] = count
     */
    @Query("SELECT a.action, COUNT(a) FROM AuditLog a WHERE " +
           "a.performedAt BETWEEN :startDate AND :endDate " +
           "GROUP BY a.action ORDER BY COUNT(a) DESC")
    List<Object[]> getActionStatistics(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}