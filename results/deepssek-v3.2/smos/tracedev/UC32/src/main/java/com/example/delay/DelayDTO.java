package com.example.delay;

import java.util.Date;

/**
 * Data Transfer Object for delay information.
 */
public class DelayDTO {
    private Date date;
    private int delay;

    /**
     * Constructor.
     * @param date The date.
     * @param delay The delay value.
     */
    public DelayDTO(Date date, int delay) {
        this.date = date;
        this.delay = delay;
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

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "DelayDTO{date=" + date + ", delay=" + delay + "}";
    }
}