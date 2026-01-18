package com.example.entities;

import com.example.utils.Date;

/**
 * Represents a convention agreement entity.
 * Implements all attributes and methods from the class diagram.
 */
public class Convention {
    private String id;
    private String restaurantId;
    private String conventionName;
    private Date effectiveDate;
    private Date expirationDate;
    private String terms;

    /**
     * Constructor as per class diagram.
     */
    public Convention(String id, String restaurantId, String name, Date effectiveDate, Date expirationDate, String terms) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.conventionName = name;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.terms = terms;
    }

    public String getId() {
        return id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getConventionName() {
        return conventionName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getTerms() {
        return terms;
    }

    @Override
    public String toString() {
        return "Convention{" +
                "id='" + id + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", conventionName='" + conventionName + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", expirationDate=" + expirationDate +
                ", terms='" + terms + '\'' +
                '}';
    }
}