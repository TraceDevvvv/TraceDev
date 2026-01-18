package com.example.model;

import java.util.Date;

/**
 * Represents a justification submitted for an absence or delay.
 */
public class Justification {
    private int justificationId;
    private int recordId;
    private String recordType;
    private String reason;
    private Date submittedDate;
    private String status;

    public Justification(int justificationId, int recordId, String recordType, String reason, Date submittedDate, String status) {
        this.justificationId = justificationId;
        this.recordId = recordId;
        this.recordType = recordType;
        this.reason = reason;
        this.submittedDate = submittedDate;
        this.status = status;
    }

    public int getJustificationId() {
        return justificationId;
    }

    public int getRecordId() {
        return recordId;
    }

    public String getRecordType() {
        return recordType;
    }

    public String getReason() {
        return reason;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public String getStatus() {
        return status;
    }
}