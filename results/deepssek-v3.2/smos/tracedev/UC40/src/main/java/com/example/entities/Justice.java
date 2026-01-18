package com.example.entities;

import java.util.Date;

/**
 * Represents a Justice entity with its attributes and methods.
 */
public class Justice {
    private int id;
    private Date date;
    private String justificationText;

    /**
     * Constructor for Justice.
     */
    public Justice(int id, Date date, String justificationText) {
        this.id = id;
        this.date = date;
        this.justificationText = justificationText;
    }

    /**
     * Gets the justice ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the justice date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the justification text.
     */
    public String getJustificationText() {
        return justificationText;
    }

    /**
     * Sets a new date for the justice.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets a new justification text for the justice.
     */
    public void setJustificationText(String text) {
        this.justificationText = text;
    }
}