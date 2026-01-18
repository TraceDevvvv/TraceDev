package com.example.delay;

import java.util.Date;

/**
 * Command object encapsulating data for changing a delay.
 */
public class ChangeDelayCommand {
    private Date date;
    private int delay;

    /**
     * Constructor.
     * @param date The date of the delay.
     * @param delay The new delay value.
     */
    public ChangeDelayCommand(Date date, int delay) {
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
}