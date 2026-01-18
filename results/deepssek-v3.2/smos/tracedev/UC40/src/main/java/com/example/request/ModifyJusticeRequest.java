package com.example.request;

import java.util.Date;

/**
 * Request object for the modify justice use case.
 */
public class ModifyJusticeRequest {
    private int justiceId;
    private Date newDate;
    private String newJustificationText;

    /**
     * Constructor.
     */
    public ModifyJusticeRequest(int justiceId, Date newDate, String newJustificationText) {
        this.justiceId = justiceId;
        this.newDate = newDate;
        this.newJustificationText = newJustificationText;
    }

    /**
     * Gets the justice ID.
     */
    public int getJusticeId() {
        return justiceId;
    }

    /**
     * Gets the new date.
     */
    public Date getNewDate() {
        return newDate;
    }

    /**
     * Gets the new justification text.
     */
    public String getNewJustificationText() {
        return newJustificationText;
    }
}