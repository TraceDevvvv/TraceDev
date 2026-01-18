package com.example;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a registration request.
 */
public class Registration {
    private String requestId;
    private String associatedUserId;
    private Date submissionDate;

    public Registration(String requestId, String associatedUserId, Date submissionDate) {
        this.requestId = requestId;
        this.associatedUserId = associatedUserId;
        this.submissionDate = submissionDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAssociatedUserId() {
        return associatedUserId;
    }

    public void setAssociatedUserId(String associatedUserId) {
        this.associatedUserId = associatedUserId;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(requestId);
    }
}