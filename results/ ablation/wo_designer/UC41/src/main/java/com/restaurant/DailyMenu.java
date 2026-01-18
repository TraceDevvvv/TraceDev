package com.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the menu for a specific day of the week.
 */
public class DailyMenu {
    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private DayOfWeek day;
    private List<MenuItem> items;

    public DailyMenu(DayOfWeek day) {
        this.day = day;
        this.items = new ArrayList<>();
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    @Override
    public String toString() {
        return "DailyMenu{" +
                "day=" + day +
                ", items=" + items +
                '}';
    }
}