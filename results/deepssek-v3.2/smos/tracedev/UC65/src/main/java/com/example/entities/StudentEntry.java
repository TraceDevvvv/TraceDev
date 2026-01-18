
package com.example.entities;

/**
 * Represents a student's entry in a registry.
 */
public class StudentEntry {
    private String studentId;
    private String attendanceStatus;
    private boolean isDelayed;
    private String justification;
    private String disciplinaryNote;

    public StudentEntry(String studentId, String attendanceStatus, boolean isDelayed, 
                       String justification, String disciplinaryNote) {
        this.studentId = studentId;
        this.attendanceStatus = attendanceStatus;
        this.isDelayed = isDelayed;
        this.justification = justification;
        this.disciplinaryNote = disciplinaryNote;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public boolean isDelayed() {
        return isDelayed;
    }

    public String getJustification() {
        return justification;
    }

    public String getDisciplinaryNote() {
        return disciplinaryNote;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public void setDisciplinaryNote(String disciplinaryNote) {
        this.disciplinaryNote = disciplinaryNote;
    }
}
