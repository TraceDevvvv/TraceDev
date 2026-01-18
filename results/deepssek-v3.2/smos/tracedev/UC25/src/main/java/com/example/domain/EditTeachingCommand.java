package com.example.domain;

import java.time.LocalDateTime;

/**
 * Command object containing data for editing a teaching.
 */
public class EditTeachingCommand {
    private final String teachingId;
    private final String title;
    private final String description;
    private final LocalDateTime schedule;

    public EditTeachingCommand(String teachingId, String title, String description, LocalDateTime schedule) {
        this.teachingId = teachingId;
        this.title = title;
        this.description = description;
        this.schedule = schedule;
    }

    public String getTeachingId() {
        return teachingId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getSchedule() {
        return schedule;
    }
}