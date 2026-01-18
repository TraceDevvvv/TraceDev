package com.example.delay;

import java.util.Date;

/**
 * Repository interface for Delay entities.
 */
public interface DelayRepository {
    /**
     * Finds a delay by date.
     * @param date The date to search.
     * @return Delay entity or null if not found.
     */
    Delay findByDate(Date date);

    /**
     * Saves a new delay.
     * @param delay The delay to save.
     */
    void save(Delay delay);

    /**
     * Updates an existing delay.
     * @param delay The delay with updated values.
     */
    void updateDelay(Delay delay);
}