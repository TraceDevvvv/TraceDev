package com.example;

import java.util.List;

/**
 * WeeklyFormData class to hold data for the weekly form.
 * Added to satisfy requirement Flow of Events: 2.
 */
public class WeeklyFormData {
    private List<String> days;

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}