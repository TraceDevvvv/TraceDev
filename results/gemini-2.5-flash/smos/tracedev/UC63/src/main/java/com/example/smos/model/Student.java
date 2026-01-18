package com.example.smos.model;

/**
 * Represents a student with their performance metrics.
 * This class corresponds to the 'Student' class in the UML diagram.
 */
public class Student {
    private String studentId;
    private String name;
    private int absencesCount;
    private int notesCount;

    /**
     * Constructs a new Student object.
     *
     * @param studentId A unique identifier for the student.
     * @param name The full name of the student.
     * @param absencesCount The total number of absences for the student.
     * @param notesCount The total number of notes (e.g., positive notes, performance notes) for the student.
     */
    public Student(String studentId, String name, int absencesCount, int notesCount) {
        this.studentId = studentId;
        this.name = name;
        this.absencesCount = absencesCount;
        this.notesCount = notesCount;
    }

    /**
     * Gets the student's ID.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the count of absences.
     * @return The number of absences.
     */
    public int getAbsencesCount() {
        return absencesCount;
    }

    /**
     * Gets the count of notes.
     * @return The number of notes.
     */
    public int getNotesCount() {
        return notesCount;
    }

    /**
     * Checks if the student's absences exceed a given threshold.
     *
     * @param threshold The maximum allowed absences.
     * @return true if absencesCount is greater than the threshold, false otherwise.
     */
    public boolean hasHighAbsences(int threshold) {
        return absencesCount > threshold;
    }

    /**
     * Checks if the student's notes count exceeds a given threshold.
     * Note: "High notes" is interpreted as a significant number of notes, which
     * could imply a need for monitoring (either positive or negative attention).
     *
     * @param threshold The minimum required notes count to be considered "high".
     * @return true if notesCount is greater than the threshold, false otherwise.
     */
    public boolean hasHighNotes(int threshold) {
        return notesCount > threshold;
    }
}