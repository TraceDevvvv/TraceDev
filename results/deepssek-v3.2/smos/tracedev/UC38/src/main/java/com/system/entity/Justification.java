package com.system.entity;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a justification for an absence.
 * Contains details about the justification, its status, and timestamps.
 */
public class Justification {
    private String justificationId;
    private String absenceId;
    private String description;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Justification() {
        // Default constructor
    }

    public Justification(String justificationId, String absenceId, String description, String status, Date createdAt, Date updatedAt) {
        this.justificationId = justificationId;
        this.absenceId = absenceId;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getJustificationId() {
        return justificationId;
    }

    public void setJustificationId(String justificationId) {
        this.justificationId = justificationId;
    }

    public String getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(String absenceId) {
        this.absenceId = absenceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Retrieves the details of this justification as a map.
     * @return Map containing justification details.
     */
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("justificationId", this.justificationId);
        details.put("absenceId", this.absenceId);
        details.put("description", this.description);
        details.put("status", this.status);
        details.put("createdAt", this.createdAt);
        details.put("updatedAt", this.updatedAt);
        return details;
    }

    /**
     * Updates the justification details with the provided map.
     * Only description and status are updatable in this example.
     * @param details Map containing new details.
     * @return true if update was successful.
     */
    public boolean updateDetails(Map<String, Object> details) {
        if (details == null) return false;
        try {
            if (details.containsKey("description")) {
                this.description = (String) details.get("description");
            }
            if (details.containsKey("status")) {
                this.status = (String) details.get("status");
            }
            this.updatedAt = new Date(); // Update timestamp
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Marks the justification as deleted (soft delete by setting status).
     * @return true if deletion was successful.
     */
    public boolean delete() {
        this.status = "DELETED";
        this.updatedAt = new Date();
        return true;
    }
}