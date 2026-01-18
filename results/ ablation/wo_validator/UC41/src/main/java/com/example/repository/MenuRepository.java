package com.example.repository;

import com.example.entity.Menu;

import java.util.Optional;

/**
 * Repository interface for Menu persistence operations.
 */
public interface MenuRepository {
    Optional<Menu> findByRestaurantIdAndDayOfWeek(Long restaurantId, String dayOfWeek);
    Menu save(Menu menu);
    boolean existsByRestaurantIdAndDayOfWeek(Long restaurantId, String dayOfWeek);
}