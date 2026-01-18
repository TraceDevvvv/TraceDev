package com.example.delay;

import java.util.Date;

/**
 * Entity representing a delay for a specific date.
 */
public class Delay {
    public int id; // In a real system, this would be generated.
    public Date date;
    public int delay; // Delay in minutes (assumption)

    /**
     * Constructor with date and delay.
     * @param date The date.
     * @param delay The delay value.
     */
    public Delay(Date date, int delay) {
        this.date = date;
        this.delay = delay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDelay() {
        return delay;
    }

    /**
     * Updates the delay value.
     * @param newDelay The new delay value.
     */
    public void updateDelay(int newDelay) {
        this.delay = newDelay;
    }
}