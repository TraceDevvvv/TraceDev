package com.example.delaysystem;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a delay entry in the system.
 */
public class Delay {
    private String id; // CD-TRACE: id
    private Date date;
    private int durationMinutes; // CD-TRACE: durationMinutes
    private String reason; // CD-TRACE: reason

    /**
     * Default constructor.
     */
    public Delay() {
    }

    /**
     * Constructor with all fields.
     */
    public Delay(String id, Date date, int durationMinutes, String reason) {
        this.id = id;
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.reason = reason;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() { // CD-TRACE: getDuration
        return durationMinutes;
    }

    public void setDuration(int duration) { // CD-TRACE: setDuration
        this.durationMinutes = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Delay{" +
               "id='" + id + '\'' +
               ", date=" + date +
               ", durationMinutes=" + durationMinutes +
               ", reason='" + reason + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delay delay = (Delay) o;
        return durationMinutes == delay.durationMinutes &&
               Objects.equals(id, delay.id) &&
               Objects.equals(date, delay.date) &&
               Objects.equals(reason, delay.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, durationMinutes, reason);
    }
}