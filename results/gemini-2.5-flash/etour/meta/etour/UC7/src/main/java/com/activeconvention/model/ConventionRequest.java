package com.activeconvention.model;

import java.util.Date;

/**
 * ConventionRequest model class representing a request for convention activation.
 * This class holds details about a request made by a refreshment point to activate a convention.
 */
public class ConventionRequest {
    private String requestId;
    private String conventionId;
    private String refreshmentPointName;
    private Date requestDate;
    private String status; // e.g., "Pending Review", "Approved", "Rejected"

    /**
     * Constructs a new ConventionRequest object.
     *
     * @param requestId The unique identifier for this request.
     * @param conventionId The ID of the convention associated with this request.
     * @param refreshmentPointName The name of the refreshment point making the request.
     * @param requestDate The date when the request was made.
     * @param status The current status of the request.
     */
    public ConventionRequest(String requestId, String conventionId, String refreshmentPointName, Date requestDate, String status) {
        this.requestId = requestId;
        this.conventionId = conventionId;
        this.refreshmentPointName = refreshmentPointName;
        this.requestDate = requestDate;
        this.status = status;
    }

    // Getters
    public String getRequestId() {
        return requestId;
    }

    public String getConventionId() {
        return conventionId;
    }

    public String getRefreshmentPointName() {
        return refreshmentPointName;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters (if needed)
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setConventionId(String conventionId) {
        this.conventionId = conventionId;
    }

    public void setRefreshmentPointName(String refreshmentPointName) {
        this.refreshmentPointName = refreshmentPointName;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConventionRequest{" +
               "requestId='" + requestId + '\'' +
               ", conventionId='" + conventionId + '\'' +
               ", refreshmentPointName='" + refreshmentPointName + '\'' +
               ", requestDate=" + requestDate +
               ", status='" + status + '\'' +
               '}';
    }
}