package com.example.delaysystem;

import java.util.Date;

/**
 * Interface for accessing and managing delay-related data.
 */
public interface IDelayRepository {

    /**
     * Fetches scheduling information for a specific date.
     *
     * @param date The date for which to fetch scheduling information.
     * @return A DTO containing scheduling information for the given date, or null if not found/error.
     */
    SchedulingInfoDTO fetchSchedulingInfoByDate(Date date);

    /**
     * Saves or updates delay data.
     *
     * @param delayDto The DelayDTO containing the data to save.
     * @return true if the delay data was saved successfully, false otherwise.
     */
    boolean saveDelayData(DelayDTO delayDto);
}