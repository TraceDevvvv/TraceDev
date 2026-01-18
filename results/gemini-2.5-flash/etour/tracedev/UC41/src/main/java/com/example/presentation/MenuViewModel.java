package com.example.presentation;

import com.example.domain.DayOfWeek;
import com.example.domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel for transferring menu data between the controller and views.
 */
public class MenuViewModel {
    private DayOfWeek selectedDay;
    private List<MenuItem> menuItems;
    private String message; // For displaying general messages or errors

    public MenuViewModel() {
        this.menuItems = new ArrayList<>();
    }

    public MenuViewModel(DayOfWeek selectedDay, List<MenuItem> menuItems, String message) {
        this.selectedDay = selectedDay;
        this.menuItems = menuItems != null ? new ArrayList<>(menuItems) : new ArrayList<>();
        this.message = message;
    }

    // Getters
    public DayOfWeek getSelectedDay() {
        return selectedDay;
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems); // Return a copy
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setSelectedDay(DayOfWeek day) {
        this.selectedDay = day;
    }

    public void setMenuItems(List<MenuItem> items) {
        this.menuItems = items != null ? new ArrayList<>(items) : new ArrayList<>();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MenuViewModel{" +
               "selectedDay=" + selectedDay +
               ", menuItems=" + menuItems +
               ", message='" + message + '\'' +
               '}';
    }
}