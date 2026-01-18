package com.example.monitoring.domain;

/**
 * Domain Layer: Represents the core Student entity.
 * Contains student-specific data and domain logic (if any).
 */
public class Student {
    private String studentId;
    private String name;
    private int absences;
    private int notes;

    /**
     * Constructor for the Student entity.
     * @param studentId The unique identifier for the student.
     * @param name The name of the student.
     * @param absences The number of absences for the student.
     * @param notes The number of notes (e.g., behavioral incidents) for the student.
     */
    public Student(String studentId, String name, int absences, int notes) {
        // Basic validation could be added here, e.g., for non-null ID/name, non-negative counts.
        this.studentId = studentId;
        this.name = name;
        this.absences = absences;
        this.notes = notes;
    }

    // Getters for the student attributes

    /**
     * @return The unique identifier of the student.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The number of absences for the student.
     */
    public int getAbsences() {
        return absences;
    }

    /**
     * @return The number of notes for the student.
     */
    public int getNotes() {
        return notes;
    }

    // Setters are typically not exposed for entities if immutability is desired
    // or if state changes should only happen through specific domain methods.
    // For this example, we keep them private or omit for simplicity.
}