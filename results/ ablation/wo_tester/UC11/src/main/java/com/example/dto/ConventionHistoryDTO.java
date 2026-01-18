package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for ConventionHistory.
 * Used to transfer convention history data from Controller to UI.
 */
public class ConventionHistoryDTO {
    private String conventionId;
    private Date conventionDate;
    private String details;

    public ConventionHistoryDTO(String conventionId, Date conventionDate, String details) {
        this.conventionId = conventionId;
        this.conventionDate = conventionDate;
        this.details = details;
    }

    public String getConventionId() {
        return conventionId;
    }

    public Date getConventionDate() {
        return conventionDate;
    }

    public String getDetails() {
        return details;
    }
}