package com.example.infrastructure;

import com.example.domain.DailyMenu;
import com.example.domain.DayOfWeek;
import java.util.Optional;

/**
 * Repository interface for daily menu persistence operations.
 */
public interface IDailyMenuRepository {
    Optional<DailyMenu> findByDay(DayOfWeek day);
    void deleteByDay(DayOfWeek day);
    void save(DailyMenu menu);
}