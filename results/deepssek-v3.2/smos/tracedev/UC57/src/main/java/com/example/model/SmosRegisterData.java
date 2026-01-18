package com.example.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import com.example.dto.RegisterDto;

/**
 * Data holder for raw SMOS register data with conversion to RegisterDto.
 */
public class SmosRegisterData {
    private Map<String, Object> rawData;

    public SmosRegisterData(Map<String, Object> rawData) {
        this.rawData = rawData;
    }

    public Map<String, Object> getRawData() {
        return rawData;
    }

    public void setRawData(Map<String, Object> rawData) {
        this.rawData = rawData;
    }

    /**
     * Converts raw SMOS data to a structured RegisterDto.
     * This is a simplified conversion; actual implementation would parse the raw map.
     */
    public RegisterDto toRegisterDto() {
        // Assumption: rawData contains entries for className, date, and lists of absences, etc.
        // For demonstration, we create sample data.
        String className = (String) rawData.getOrDefault("className", "Unknown Class");
        Date date = (Date) rawData.getOrDefault("date", new Date());
        List<Absence> absences = (List<Absence>) rawData.getOrDefault("absences", new ArrayList<Absence>());
        List<DisciplinaryNote> disciplinaryNotes = (List<DisciplinaryNote>) rawData.getOrDefault("disciplinaryNotes", new ArrayList<DisciplinaryNote>());
        List<Delay> delays = (List<Delay>) rawData.getOrDefault("delays", new ArrayList<Delay>());
        List<Justification> justifications = (List<Justification>) rawData.getOrDefault("justifications", new ArrayList<Justification>());

        return new RegisterDto(className, date, absences, disciplinaryNotes, delays, justifications);
    }
}