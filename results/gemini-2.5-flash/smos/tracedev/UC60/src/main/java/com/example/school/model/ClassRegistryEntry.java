package com.example.school.model;

import java.util.Date;
import java.io.Serializable;

/**
 * Represents a single entry in a student's class registry,
 * typically retrieved from a data source.
 */
public class ClassRegistryEntry implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization

    private String id;
    private String studentId;
    private Date date;
    private int absences;
    private String disciplinaryNotes;
    private int delays;
    public String justification; // Public as per UML

    /**
     * Constructs a new ClassRegistryEntry.
     *
     * @param id Unique identifier for the entry.
     * @param studentId The ID of the student this entry belongs to.
     * @param date The date of the registry entry.
     * @param absences Number of absences for this entry.
     * @param disciplinaryNotes Any disciplinary notes associated with this entry.
     * @param delays Number of delays for this entry.
     * @param justification Justification for absences or delays.
     */
    public ClassRegistryEntry(String id, String studentId, Date date, int absences, String disciplinaryNotes, int delays, String justification) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delays = delays;
        this.justification = justification;
    }

    // Getters for private attributes
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getDate() {
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

    // Setter for justification as it's public in UML
    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public String toString() {
        return "ClassRegistryEntry{" +
               "id='" + id + '\'' +
               ", studentId='" + studentId + '\'' +
               ", date=" + date +
               ", absences=" + absences +
               ", disciplinaryNotes='" + disciplinaryNotes + '\'' +
               ", delays=" + delays +
               ", justification='" + justification + '\'' +
               '}';
    }
}