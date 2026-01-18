package com.example.infrastructure;

import com.example.domain.DailyMenu;
import com.example.domain.DayOfWeek;
import com.example.domain.NetworkConnectionException;

/**
 * Interface for repository operations related to DailyMenu.
 * Methods may throw NetworkConnectionException to satisfy R19.
 */
public interface IMenuRepository {
    /**
     * Finds a DailyMenu by its day of the week.
     * @param day The DayOfWeek to search for.
     * @return The DailyMenu if found, null otherwise.
     * @throws NetworkConnectionException if a network issue prevents access to the data source.
     */
    DailyMenu findByDay(DayOfWeek day) throws NetworkConnectionException;

    /**
     * Saves a DailyMenu. This can be an insert or an update.
     * @param dailyMenu The DailyMenu to save.
     * @return true if the operation was successful, false otherwise.
     * @throws NetworkConnectionException if a network issue prevents access to the data source.
     */
    boolean save(DailyMenu dailyMenu) throws NetworkConnectionException;
}