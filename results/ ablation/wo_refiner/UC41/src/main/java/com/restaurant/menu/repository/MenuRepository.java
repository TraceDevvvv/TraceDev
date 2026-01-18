package com.restaurant.menu.repository;

import com.restaurant.menu.Menu;
import java.util.List;

/**
 * Repository interface for Menu entities.
 */
public interface MenuRepository {
    Menu findById(String id);
    Menu findByDay(String day);
    List<Menu> findAllWeekMenus();
    Menu save(Menu menu);
    void delete(Menu menu);
}