package com.example.delaysystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Represents scheduling information for a specific date, including any associated delays.
 */
public class SchedulingInformation {
    private Date date;
    private List<Delay> delays;

    /**
     * Default constructor.
     */
    public SchedulingInformation() {
        this.delays = new ArrayList<>();
    }

    /**
     * Constructor with date.
     */
    public SchedulingInformation(Date date) {
        this.date = date;
        this.delays = new ArrayList<>();
    }

    // Getters and Setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Delay> getDelays() {
        return new ArrayList<>(delays); // Return a copy to prevent external modification of the internal list
    }

    // Note: No setter for delays as per UML. Delays are managed via addDelay.
    // For DTO conversion, we might need a setter or add a collection in constructor.
    // Assuming internal management for this domain object.

    /**
     * Adds a new delay to the scheduling information.
     *
     * @param delay The delay to add.
     */
    public void addDelay(Delay delay) {
        if (delay != null) {
            this.delays.add(delay);
        }
    }

    @Override
    public String toString() {
        return "SchedulingInformation{" +
               "date=" + date +
               ", delays=" + delays +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingInformation that = (SchedulingInformation) o;
        return Objects.equals(date, that.date) &&
               Objects.equals(delays, that.delays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, delays);
    }
}