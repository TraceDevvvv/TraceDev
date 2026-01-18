package com.example.repository;

import com.example.model.DailyMenu;
import java.util.Optional;

/**
 * Repository interface for DailyMenu persistence operations.
 */
public interface MenuRepository {
    Optional<DailyMenu> findByDayOfWeek(String dayOfWeek);
    void delete(DailyMenu menu);
    DailyMenu save(DailyMenu menu);
}