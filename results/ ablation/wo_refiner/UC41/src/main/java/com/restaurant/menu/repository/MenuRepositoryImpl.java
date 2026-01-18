package com.restaurant.menu.repository;

import com.restaurant.menu.Menu;
import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of MenuRepository.
 * For simplicity, uses an in-memory map as data source.
 */
public class MenuRepositoryImpl implements MenuRepository {
    private DataSource dataSource;
    private Map<String, Menu> menuStore; // Simulating data source

    public MenuRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.menuStore = new HashMap<>();
        // Initialize with some dummy data for demonstration
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Assumption: creating sample menus for each day of the week
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days) {
            Menu menu = new Menu(day, new java.util.Date());
            menuStore.put(day, menu);
        }
    }

    @Override
    public Menu findById(String id) {
        for (Menu menu : menuStore.values()) {
            if (menu.getMenuId().equals(id)) {
                return menu;
            }
        }
        return null;
    }

    @Override
    public Menu findByDay(String day) {
        return menuStore.get(day);
    }

    @Override
    public List<Menu> findAllWeekMenus() {
        return new ArrayList<>(menuStore.values());
    }

    @Override
    public Menu save(Menu menu) {
        menuStore.put(menu.getDayOfWeek(), menu);
        return menu;
    }

    @Override
    public void delete(Menu menu) {
        menuStore.remove(menu.getDayOfWeek());
    }
}