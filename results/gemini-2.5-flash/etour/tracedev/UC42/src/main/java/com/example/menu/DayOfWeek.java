package com.example.menu;

import java.util.Arrays;
import java.util.List;

/**
 * REQ-R5: Enum representing the days of the week for daily menu management.
 * Provides a static method to get all defined DayOfWeek values.
 */
public enum DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    /**
     * REQ-R5: Returns a list of all DayOfWeek enum values.
     * This clarifies the source of the list of days used in the UI.
     *
     * @return A List containing all DayOfWeek enum constants.
     */
    public static List<DayOfWeek> getValues() {
        return Arrays.asList(DayOfWeek.values());
    }
}