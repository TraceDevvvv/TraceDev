package com.etour.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity representing audit logs for tracking modifications to tourist accounts.
 * Maps to the 'audit_logs' table in the database.
 * Stores detailed information about who changed what, when, and from where.
 */
@Entity
@Table(name = "audit_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "action", nullable = false, length = 50)
    private String action;
    
    @Column(name = "entity_type", nullable = false, length = 100)
    private String entityType;
    
    @Column(name = "entity_id")
    private Long entityId;
    
    @Column(name = "performed_by", length = 100)
    private String performedBy;
    
    @Column(name = "performed_at", nullable = false)
    private LocalDateTime performedAt;
    
    @Lob
    @Column(name = "old_values", columnDefinition = "TEXT")
    private String oldValues;
    
    @Lob
    @Column(name = "new_values", columnDefinition = "TEXT")
    private String newValues;
    
    @Column(name = "ip_address", length = 45) // IPv6 max length
    private String ipAddress;
    
    @Column(name = "user_agent", length = 500)
    private String userAgent;
    
    @Column(name = "successful")
    private Boolean successful;
    
    @Column(name = "error_message", length = 1000)
    private String errorMessage;
    
    /**
     * Creates an audit log entry for a tourist modification action.
     * 
     * @param action The action performed (e.g., "UPDATE_TOURIST", "CREATE_TOURIST")
     * @param entityType The type of entity (e.g., "Tourist")
     * @param entityId The ID of the entity being modified
     * @param performedBy The username or identifier of who performed the action
     * @param oldValues Map of old values before modification
     * @param newValues Map of new values after modification
     * @param ipAddress IP address of the request
     * @param userAgent User agent string from the request
     * @return A new AuditLog instance
     */
    public static AuditLog logAction(String action, String entityType, Long entityId, 
                                     String performedBy, Map<String, Object> oldValues, 
                                     Map<String, Object> newValues, String ipAddress, 
                                     String userAgent) {
        ObjectMapper objectMapper = new ObjectMapper();
        String oldValuesJson = "{}";
        String newValuesJson = "{}";
        
        try {
            if (oldValues != null && !oldValues.isEmpty()) {
                oldValuesJson = objectMapper.writeValueAsString(oldValues);
            }
            if (newValues != null && !newValues.isEmpty()) {
                newValuesJson = objectMapper.writeValueAsString(newValues);
            }
        } catch (JsonProcessingException e) {
            // Fallback to simple string representation if JSON serialization fails
            oldValuesJson = oldValues != null ? oldValues.toString() : "{}";
            newValuesJson = newValues != null ? newValues.toString() : "{}";
        }
        
        return AuditLog.builder()
                .action(action)
                .entityType(entityType)
                .entityId(entityId)
                .performedBy(performedBy)
                .performedAt(LocalDateTime.now())
                .oldValues(oldValuesJson)
                .newValues(newValuesJson)
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .successful(true)
                .build();
    }
    
    /**
     * Creates an audit log for a failed action.
     * 
     * @param action The action that failed
     * @param entityType The type of entity
     * @param entityId The ID of the entity
     * @param performedBy Who attempted the action
     * @param errorMessage Error message describing the failure
     * @param ipAddress IP address of the request
     * @param userAgent User agent string
     * @return A new AuditLog instance marked as unsuccessful
     */
    public static AuditLog logFailure(String action, String entityType, Long entityId, 
                                      String performedBy, String errorMessage, 
                                      String ipAddress, String userAgent) {
        return AuditLog.builder()
                .action(action)
                .entityType(entityType)
                .entityId(entityId)
                .performedBy(performedBy)
                .performedAt(LocalDateTime.now())
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .successful(false)
                .errorMessage(errorMessage)
                .build();
    }
    
    /**
     * Generates a human-readable summary of the changes.
     * 
     * @return Summary string describing what changed
     */
    public String getChangeSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Action: ").append(action)
               .append(" on ").append(entityType);
                
        if (entityId != null) {
            summary.append(" ID: ").append(entityId);
        }
        
        summary.append(" by ").append(performedBy != null ? performedBy : "Unknown")
               .append(" at ").append(performedAt);
        
        if (successful != null && !successful) {
            summary.append(" (FAILED: ").append(errorMessage).append(")");
        } else {
            summary.append(" (SUCCESS)");
        }
        
        return summary.toString();
    }
    
    /**
     * Parses old values from JSON string to Map.
     * 
     * @return Map of old values, or empty map if parsing fails
     */
    public Map<String, Object> getOldValuesMap() {
        return parseJsonToMap(oldValues);
    }
    
    /**
     * Parses new values from JSON string to Map.
     * 
     * @return Map of new values, or empty map if parsing fails
     */
    public Map<String, Object> getNewValuesMap() {
        return parseJsonToMap(newValues);
    }
    
    /**
     * Helper method to parse JSON string to Map.
     * 
     * @param json JSON string to parse
     * @return Map representation of JSON, or empty map on failure
     */
    private Map<String, Object> parseJsonToMap(String json) {
        if (json == null || json.trim().isEmpty() || "{}".equals(json.trim())) {
            return new HashMap<>();
        }
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            // Return empty map if parsing fails
            return new HashMap<>();
        }
    }
    
    /**
     * Checks if this audit log contains actual changes.
     * 
     * @return true if oldValues and newValues are different, false otherwise
     */
    public boolean hasChanges() {
        if (oldValues == null && newValues == null) {
            return false;
        }
        if (oldValues == null || newValues == null) {
            return true; // One is null, other is not
        }
        return !oldValues.equals(newValues);
    }
    
    /**
     * Pre-persist hook to set default values.
     */
    @PrePersist
    public void prePersist() {
        if (performedAt == null) {
            performedAt = LocalDateTime.now();
        }
        if (successful == null) {
            successful = true;
        }
    }
}