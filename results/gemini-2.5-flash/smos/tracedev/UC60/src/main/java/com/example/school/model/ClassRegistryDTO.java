package com.example.school.model;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) for displaying class registry information.
 * This class holds simplified and formatted data intended for presentation layers.
 */
public class ClassRegistryDTO implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization

    public String date;
    public int absences;
    public String disciplinaryNotes;
    public int delays;
    public String justification;

    /**
     * Constructs a new ClassRegistryDTO.
     *
     * @param date Formatted date string (e.g., "YYYY-MM-DD").
     * @param absences Number of absences.
     * @param disciplinaryNotes Any disciplinary notes.
     * @param delays Number of delays.
     * @param justification Justification for absences/delays.
     */
    public ClassRegistryDTO(String date, int absences, String disciplinaryNotes, int delays, String justification) {
        this.date = date;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delays = delays;
        this.justification = justification;
    }

    // Getters for all fields, as they are public, but good practice to include them for DTOs.
    public String getDate() {
        return date;
    }

    public int getAbsences() {
        return absences;
    }

    public String getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    public int getDelays() {
        return delays;
    }

    public String getJustification() {
        return justification;
    }

    @Override
    public String toString() {
        return "ClassRegistryDTO{" +
               "date='" + date + '\'' +
               ", absences=" + absences +
               ", disciplinaryNotes='" + disciplinaryNotes + '\'' +
               ", delays=" + delays +
               ", justification='" + justification + '\'' +
               '}';
    }
}