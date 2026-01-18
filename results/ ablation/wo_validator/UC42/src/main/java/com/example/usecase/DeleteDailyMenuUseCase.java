package com.example.usecase;

/**
 * Use case interface for deleting daily menu.
 */
public interface DeleteDailyMenuUseCase {
    /**
     * Executes the deletion of daily menu for the given day of week.
     * @param dayOfWeek the day of week (e.g., "Monday")
     * @return true if deletion successful, false otherwise
     */
    boolean execute(String dayOfWeek);
}