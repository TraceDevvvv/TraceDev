package com.restaurant.menu.dto;

import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object representing a week's menu.
 */
public class WeekMenuDTO {
    private Map<String, MenuDTO> days;

    public WeekMenuDTO() {
        this.days = new HashMap<>();
    }

    public Map<String, MenuDTO> getDays() {
        return days;
    }

    public void setDays(Map<String, MenuDTO> days) {
        this.days = days;
    }

    public MenuDTO getDay(String day) {
        return days.get(day);
    }

    public void setDay(String day, MenuDTO menu) {
        days.put(day, menu);
    }
}