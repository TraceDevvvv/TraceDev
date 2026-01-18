/**
 * Represents a single student's attendance record for a specific day,
 * including their status, lateness, justification, and disciplinary notes.
 */
package com.chatdev.register.model;
public class AttendanceEntry {
    private Student student;
    private AttendanceStatus status;
    private boolean isLate;
    private String justification;
    private String disciplinaryNote;
    /**
     * Constructs a new AttendanceEntry.
     *
     * @param student The student for this entry.
     * @param status The attendance status (PRESENT, ABSENT, LATE).
     * @param isLate True if the student was late, false otherwise.
     * @param justification A justification for absence or lateness (can be null/empty).
     * @param disciplinaryNote A disciplinary note for the student (can be null/empty).
     */
    public AttendanceEntry(Student student, AttendanceStatus status, boolean isLate, String justification, String disciplinaryNote) {
        this.student = student;
        this.status = status;
        this.isLate = isLate;
        this.justification = justification;
        this.disciplinaryNote = disciplinaryNote;
    }
    /**
     * Returns the student associated with this entry.
     * @return The Student object.
     */
    public Student getStudent() {
        return student;
    }
    /**
     * Returns the attendance status.
     * @return The AttendanceStatus enum.
     */
    public AttendanceStatus getStatus() {
        return status;
    }
    /**
     * Sets the attendance status.
     * @param status The new AttendanceStatus.
     */
    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
    /**
     * Checks if the student was late.
     * @return True if late, false otherwise.
     */
    public boolean isLate() {
        return isLate;
    }
    /**
     * Sets the lateness flag.
     * @param late True if late, false otherwise.
     */
    public void setLate(boolean late) {
        isLate = late;
    }
    /**
     * Returns the justification for absence or lateness.
     * @return The justification string.
     */
    public String getJustification() {
        return justification;
    }
    /**
     * Sets the justification.
     * @param justification The new justification string.
     */
    public void setJustification(String justification) {
        this.justification = justification;
    }
    /**
     * Returns the disciplinary note.
     * @return The disciplinary note string.
     */
    public String getDisciplinaryNote() {
        return disciplinaryNote;
    }
    /**
     * Sets the disciplinary note.
     * @param disciplinaryNote The new disciplinary note string.
     */
    public void setDisciplinaryNote(String disciplinaryNote) {
        this.disciplinaryNote = disciplinaryNote;
    }
}