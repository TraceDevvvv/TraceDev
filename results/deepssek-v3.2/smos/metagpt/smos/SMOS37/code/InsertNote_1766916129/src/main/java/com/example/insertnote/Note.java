package com.example.insertnote;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Data class representing a disciplinary note.
 * Contains all necessary fields and validation logic.
 */
public class Note {
    private String student;
    private LocalDate date;
    private String teacher;
    private String description;
    
    /**
     * Default constructor for frameworks that require it.
     */
    public Note() {
        // Default constructor for serialization/deserialization
    }
    
    /**
     * Constructor with all fields.
     * @param student The student's name (cannot be null or empty)
     * @param date The date of the note (cannot be null or in the future)
     * @param teacher The teacher's name (cannot be null or empty)
     * @param description The description of the disciplinary note (cannot be null or empty)
     * @throws IllegalArgumentException if any validation fails
     */
    public Note(String student, LocalDate date, String teacher, String description) {
        setStudent(student);
        setDate(date);
        setTeacher(teacher);
        setDescription(description);
    }
    
    /**
     * Validates and sets the student name.
     * @param student The student's name
     * @throws IllegalArgumentException if student is null or empty
     */
    public void setStudent(String student) {
        if (student == null || student.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        this.student = student.trim();
    }
    
    /**
     * Validates and sets the date.
     * @param date The date of the note
     * @throws IllegalArgumentException if date is null or in the future
     */
    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }
        this.date = date;
    }
    
    /**
     * Validates and sets the teacher name.
     * @param teacher The teacher's name
     * @throws IllegalArgumentException if teacher is null or empty
     */
    public void setTeacher(String teacher) {
        if (teacher == null || teacher.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty");
        }
        this.teacher = teacher.trim();
    }
    
    /**
     * Validates and sets the description.
     * @param description The description of the disciplinary note
     * @throws IllegalArgumentException if description is null or empty
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description.trim();
    }
    
    /**
     * @return The student's name
     */
    public String getStudent() {
        return student;
    }
    
    /**
     * @return The date of the note
     */
    public LocalDate getDate() {
        return date;
    }
    
    /**
     * @return The teacher's name
     */
    public String getTeacher() {
        return teacher;
    }
    
    /**
     * @return The description of the disciplinary note
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Generates a formatted string representation of the note.
     * @return Formatted string with all note details
     */
    @Override
    public String toString() {
        return "Disciplinary Note:\n" +
               "  Student: " + student + "\n" +
               "  Date: " + date + "\n" +
               "  Teacher: " + teacher + "\n" +
               "  Description: " + description;
    }
    
    /**
     * Compares this note with another object for equality.
     * Two notes are equal if all their fields are equal.
     * @param obj The object to compare with
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Note other = (Note) obj;
        return Objects.equals(student, other.student) &&
               Objects.equals(date, other.date) &&
               Objects.equals(teacher, other.teacher) &&
               Objects.equals(description, other.description);
    }
    
    /**
     * Generates a hash code for this note.
     * @return Hash code based on all fields
     */
    @Override
    public int hashCode() {
        return Objects.hash(student, date, teacher, description);
    }
    
    /**
     * Validates all fields of the note.
     * @throws IllegalStateException if any field is invalid
     */
    public void validate() {
        if (student == null || student.trim().isEmpty()) {
            throw new IllegalStateException("Student name is not set");
        }
        if (date == null) {
            throw new IllegalStateException("Date is not set");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalStateException("Date cannot be in the future");
        }
        if (teacher == null || teacher.trim().isEmpty()) {
            throw new IllegalStateException("Teacher name is not set");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalStateException("Description is not set");
        }
    }
    
    /**
     * Creates a copy of this note.
     * @return A new Note instance with the same field values
     */
    public Note copy() {
        return new Note(student, date, teacher, description);
    }
    
    /**
     * Gets the parent email based on student name.
     * In a real application, this would look up the parent's email from a database.
     * For simulation purposes, we generate a mock email.
     * @return Mock parent email address
     */
    public String getParentEmail() {
        // In a real system, this would query a database
        // For simulation, create a mock email based on student name
        String normalizedStudent = student.toLowerCase().replaceAll("[^a-z]", "");
        return "parent." + normalizedStudent + "@school.edu";
    }
}