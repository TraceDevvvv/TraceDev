package com.activeconvention.model;

/**
 * Convention model class representing a convention's details.
 * This class holds the data structure for a convention, including its ID,
 * the refreshment point associated with it, dates, terms, and current status.
 */
public class Convention {
    private String id;
    private String refreshmentPointName;
    private String startDate;
    private String endDate;
    private String terms;
    private String status; // e.g., "Pending", "Activated", "Cancelled"

    /**
     * Constructs a new Convention object.
     *
     * @param id The unique identifier for the convention.
     * @param refreshmentPointName The name of the refreshment point associated with the convention.
     * @param startDate The start date of the convention (e.g., "YYYY-MM-DD").
     * @param endDate The end date of the convention (e.g., "YYYY-MM-DD").
     * @param terms The terms and conditions of the convention.
     * @param status The current status of the convention.
     */
    public Convention(String id, String refreshmentPointName, String startDate, String endDate, String terms, String status) {
        this.id = id;
        this.refreshmentPointName = refreshmentPointName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.terms = terms;
        this.status = status;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getRefreshmentPointName() {
        return refreshmentPointName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTerms() {
        return terms;
    }

    public String getStatus() {
        return status;
    }

    // Setters (if needed, for status updates for example)
    public void setId(String id) {
        this.id = id;
    }

    public void setRefreshmentPointName(String refreshmentPointName) {
        this.refreshmentPointName = refreshmentPointName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Convention{" +
               "id='" + id + '\'' +
               ", refreshmentPointName='" + refreshmentPointName + '\'' +
               ", startDate='" + startDate + '\'' +
               ", endDate='" + endDate + '\'' +
               ", terms='" + terms + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}