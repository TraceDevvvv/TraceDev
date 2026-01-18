package com.example.dto;

import com.example.model.MenuItem;
import java.util.Date;
import java.util.List;

/**
 * Data Transfer Object for Daily Menu.
 */
public class DailyMenuDTO {
    private Date date;
    private String dayOfWeek;
    private List<MenuItem> items;

    public DailyMenuDTO() {}

    public DailyMenuDTO(Date date, String dayOfWeek, List<MenuItem> items) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}