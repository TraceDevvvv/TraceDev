package com.example.domain;

import java.util.Date;

/**
 * Search criteria for querying cultural objects.
 * Contains various filter fields that can be used for searching.
 */
public class SearchCriteria {
    private String title;
    private String creator;
    private String objectType;
    private Date dateRangeStart;
    private Date dateRangeEnd;

    public SearchCriteria() {
        // Default constructor
    }

    public SearchCriteria(String title, String creator, String objectType, Date dateRangeStart, Date dateRangeEnd) {
        this.title = title;
        this.creator = creator;
        this.objectType = objectType;
        this.dateRangeStart = dateRangeStart;
        this.dateRangeEnd = dateRangeEnd;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public String getObjectType() {
        return objectType;
    }

    public Date getDateRangeStart() {
        return dateRangeStart;
    }

    public Date getDateRangeEnd() {
        return dateRangeEnd;
    }

    /**
     * Checks if any search criteria has been set.
     * @return true if at least one field is non-null and non-empty (for strings)
     */
    public boolean hasCriteria() {
        return (title != null && !title.trim().isEmpty()) ||
                (creator != null && !creator.trim().isEmpty()) ||
                (objectType != null && !objectType.trim().isEmpty()) ||
                dateRangeStart != null ||
                dateRangeEnd != null;
    }

    /**
     * Validates the search criteria.
     * For date range, ensures start is before end if both are provided.
     * @return true if criteria is valid
     */
    public boolean isValid() {
        // Basic validation: if date range provided, start must be before end
        if (dateRangeStart != null && dateRangeEnd != null) {
            return dateRangeStart.before(dateRangeEnd) || dateRangeStart.equals(dateRangeEnd);
        }
        return true;
    }

    // Setters for building criteria
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public void setDateRangeStart(Date dateRangeStart) {
        this.dateRangeStart = dateRangeStart;
    }

    public void setDateRangeEnd(Date dateRangeEnd) {
        this.dateRangeEnd = dateRangeEnd;
    }
}