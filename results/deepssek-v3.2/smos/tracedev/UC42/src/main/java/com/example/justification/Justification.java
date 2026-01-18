package com.example.justification;

import java.util.Date;

/**
 * Entity class representing a Justification.
 */
public class Justification {
    private int id;
    private String reason;
    private Date createdDate;
    private int createdBy;

    public Justification() {
    }

    public Justification(int id, String reason, Date createdDate, int createdBy) {
        this.id = id;
        this.reason = reason;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
}