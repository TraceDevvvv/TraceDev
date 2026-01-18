package com.example.model;

import java.util.Date;

/**
 * Represents the association between an Address and a Teaching.
 * Based on the UML class diagram.
 */
public class AddressTeaching {
    private String addressId;
    private String teachingId;
    private Date associationDate;

    public AddressTeaching(String addressId, String teachingId, Date associationDate) {
        this.addressId = addressId;
        this.teachingId = teachingId;
        this.associationDate = associationDate;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getTeachingId() {
        return teachingId;
    }

    public Date getAssociationDate() {
        return associationDate;
    }

    // Setters
    public void setAddressId(String addressId) { this.addressId = addressId; }
    public void setTeachingId(String teachingId) { this.teachingId = teachingId; }
    public void setAssociationDate(Date associationDate) { this.associationDate = associationDate; }
}