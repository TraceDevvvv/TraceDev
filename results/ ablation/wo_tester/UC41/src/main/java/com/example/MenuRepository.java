package com.example;

/**
 * Repository interface for menu persistence.
 */
public interface MenuRepository {
    DailyMenu findByDay(String dayOfWeek);
    void save(DailyMenu menu); // Transactional for reliability
}