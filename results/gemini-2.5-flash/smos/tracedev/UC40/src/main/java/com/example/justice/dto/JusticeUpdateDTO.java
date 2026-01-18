package com.example.justice.dto;

import java.util.Date;

/**
 * Data Transfer Object for updating a Justice record.
 * Contains only the fields relevant for the update operation specified in the use case.
 */
public class JusticeUpdateDTO {
    private String id;
    private Date dateJustification;
    private String otherUpdatedField; // Added as per class diagram

    /**
     * Constructor for JusticeUpdateDTO.
     * As specified in the class diagram, it takes 'id', 'dateJustification', and 'otherUpdatedField'.
     *
     * @param id The ID of the Justice record to be updated.
     * @param dateJustification The new date of justification.
     * @param otherUpdatedField An additional field that can be updated.
     */
    public JusticeUpdateDTO(String id, Date dateJustification, String otherUpdatedField) { // Modified constructor
        this.id = id;
        this.dateJustification = dateJustification;
        this.otherUpdatedField = otherUpdatedField; // Initialize new field
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public Date getDateJustification() {
        return dateJustification;
    }

    /**
     * Returns the value of otherUpdatedField.
     * @return The other updated field.
     */
    public String getOtherUpdatedField() { // Added getter for otherUpdatedField
        return otherUpdatedField;
    }

    @Override
    public String toString() {
        return "JusticeUpdateDTO{" +
               "id='" + id + '\'' +
               ", dateJustification=" + dateJustification +
               ", otherUpdatedField='" + otherUpdatedField + '\'' + // Include in toString
               '}';
    }
}