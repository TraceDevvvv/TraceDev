package com.example;

import java.util.List;

/**
 * Implementation of MenuRepository.
 */
public class MenuRepositoryImpl implements MenuRepository {
    private Object dataSource; // This would be a real DataSource in production
    private ETOURConnection etourConnection;

    public MenuRepositoryImpl() {
        // Default constructor.
    }

    // Getters and setters for dependencies
    public void setDataSource(Object dataSource) {
        this.dataSource = dataSource;
    }

    public void setEtourConnection(ETOURConnection etourConnection) {
        this.etourConnection = etourConnection;
    }

    @Override
    public DayMenu findByDay(String dayOfWeek) {
        // In a real implementation, this would query the data source.
        // For demo purposes, we return a mock menu.
        DayMenu mockMenu = new DayMenu();
        mockMenu.setDayOfWeek(dayOfWeek);
        mockMenu.setMenuItems(List.of(
            new MenuItem("Burger", "Delicious beef burger", 9.99, "Main"),
            new MenuItem("Salad", "Fresh garden salad", 7.99, "Starter")
        ));
        return mockMenu;
    }

    @Override
    public void save(DayMenu menu) {
        // In a real implementation, this would persist the menu.
        System.out.println("Saving menu for day: " + menu.getDayOfWeek());
    }

    @Override
    public boolean uploadMenuData(String dayOfWeek, MenuDataDTO menuData) {
        // Upload menu data to external system (ETOUR).
        // Here we assume the upload always succeeds.
        System.out.println("Uploading menu data for day: " + dayOfWeek);
        return true;
    }
}