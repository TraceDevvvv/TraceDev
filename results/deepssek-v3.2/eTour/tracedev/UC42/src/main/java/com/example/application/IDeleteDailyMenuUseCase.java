package com.example.application;

import com.example.domain.DayOfWeek;

/**
 * Use case interface for deleting a daily menu.
 */
public interface IDeleteDailyMenuUseCase {
    void deleteDailyMenu(DayOfWeek day);
}