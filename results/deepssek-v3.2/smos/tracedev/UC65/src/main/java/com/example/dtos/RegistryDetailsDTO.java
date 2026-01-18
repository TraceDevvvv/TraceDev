package com.example.dtos;

import java.util.List;

/**
 * Data Transfer Object for registry details.
 */
public class RegistryDetailsDTO {
    private String academicYear;
    private String className;
    private List<DailyRegistryDTO> timeline;

    public RegistryDetailsDTO(String academicYear, String className, List<DailyRegistryDTO> timeline) {
        this.academicYear = academicYear;
        this.className = className;
        this.timeline = timeline;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getClassName() {
        return className;
    }

    public List<DailyRegistryDTO> getTimeline() {
        return timeline;
    }

    public void addDailyRegistry(DailyRegistryDTO day) {
        timeline.add(day);
    }
}