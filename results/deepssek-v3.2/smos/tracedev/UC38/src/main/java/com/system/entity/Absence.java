package com.system.entity;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents an absence record with an associated status color.
 * The color "green" indicates the absence is in a state that can be justified.
 */
public class Absence {
    private String absenceId;
    private String color;
    private String status;

    public Absence() {
        // Default constructor
    }

    public Absence(String absenceId, String color, String status) {
        this.absenceId = absenceId;
        this.color = color;
        this.status = status;
    }

    public String getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(String absenceId) {
        this.absenceId = absenceId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the details of this absence as a map.
     * @return Map containing absence details.
     */
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("absenceId", this.absenceId);
        details.put("color", this.color);
        details.put("status", this.status);
        return details;
    }
}