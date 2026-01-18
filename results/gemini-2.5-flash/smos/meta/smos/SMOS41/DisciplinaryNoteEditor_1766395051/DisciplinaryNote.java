package com.example.disciplinarynote;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a disciplinary note with details such as student, description, teacher, and date.
 * This class is immutable once created, but its fields can be updated via a copy-and-update pattern
 * or by using setters if mutability is desired for editing. For this use case,
 * we'll provide setters to allow direct modification of an existing note object.
 */
public class DisciplinaryNote {
    private String studentName;
    private String description;
    private String teacherName;
    private LocalDate noteDate;
    private String noteId; // Unique identifier for the note

    /**
     * Constructs a new DisciplinaryNote.
     *
     * @param noteId A unique identifier for the note.
     * @param studentName The name of the student involved.
     * @param description The description of the disciplinary incident.
     * @param teacherName The name of the teacher who issued the note.
     * @param noteDate The date when the note was issued.
     * @throws IllegalArgumentException if noteId, studentName, description, or teacherName is null or empty,
     *                                  or if noteDate is null.
     */
    public DisciplinaryNote(String noteId, String studentName, String description, String teacherName, LocalDate noteDate) {
        if (noteId == null || noteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Note ID cannot be null or empty.");
        }
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        if (teacherName == null || teacherName.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty.");
        }
        if (noteDate == null) {
            throw new IllegalArgumentException("Note date cannot be null.");
        }

        this.noteId = noteId;
        this.studentName = studentName;
        this.description = description;
        this.teacherName = teacherName;
        this.noteDate = noteDate;
    }

    /**
     * Returns the unique identifier of the note.
     * @return The note ID.
     */
    public String getNoteId() {
        return noteId;
    }

    /**
     * Returns the name of the student.
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the name of the student.
     * @param studentName The new student name.
     * @throws IllegalArgumentException if studentName is null or empty.
     */
    public void setStudentName(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        this.studentName = studentName;
    }

    /**
     * Returns the description of the disciplinary incident.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the disciplinary incident.
     * @param description The new description.
     * @throws IllegalArgumentException if description is null or empty.
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }

    /**
     * Returns the name of the teacher.
     * @return The teacher's name.
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * Sets the name of the teacher.
     * @param teacherName The new teacher name.
     * @throws IllegalArgumentException if teacherName is null or empty.
     */
    public void setTeacherName(String teacherName) {
        if (teacherName == null || teacherName.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty.");
        }
        this.teacherName = teacherName;
    }

    /**
     * Returns the date of the note.
     * @return The note date.
     */
    public LocalDate getNoteDate() {
        return noteDate;
    }

    /**
     * Sets the date of the note.
     * @param noteDate The new note date.
     * @throws IllegalArgumentException if noteDate is null.
     */
    public void setNoteDate(LocalDate noteDate) {
        if (noteDate == null) {
            throw new IllegalArgumentException("Note date cannot be null.");
        }
        this.noteDate = noteDate;
    }

    @Override
    public String toString() {
        return "DisciplinaryNote{" +
               "noteId='" + noteId + '\'' +
               ", studentName='" + studentName + '\'' +
               ", description='" + description + '\'' +
               ", teacherName='" + teacherName + '\'' +
               ", noteDate=" + noteDate +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplinaryNote that = (DisciplinaryNote) o;
        return Objects.equals(noteId, that.noteId) &&
               Objects.equals(studentName, that.studentName) &&
               Objects.equals(description, that.description) &&
               Objects.equals(teacherName, that.teacherName) &&
               Objects.equals(noteDate, that.noteDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, studentName, description, teacherName, noteDate);
    }
}