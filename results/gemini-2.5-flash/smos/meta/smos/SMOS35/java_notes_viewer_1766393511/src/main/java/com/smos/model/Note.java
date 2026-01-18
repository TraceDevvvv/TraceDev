package com.smos.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a note associated with a student in the School Management and Operations System (SMOS).
 * Each note has a unique ID, is linked to a specific student, contains content, and has a creation date.
 */
public class Note {
    private String noteId;
    private String studentId;
    private String content;
    private LocalDate date;

    /**
     * Constructs a new Note object.
     *
     * @param noteId The unique identifier for the note.
     * @param studentId The ID of the student this note is associated with.
     * @param content The textual content of the note.
     * @param date The date when the note was created or recorded.
     * @throws IllegalArgumentException if any of the required parameters (noteId, studentId, content, date) are null or empty.
     */
    public Note(String noteId, String studentId, String content, LocalDate date) {
        // Validate noteId
        if (noteId == null || noteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Note ID cannot be null or empty.");
        }
        // Validate studentId
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        // Validate content
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Note content cannot be null or empty.");
        }
        // Validate date
        if (date == null) {
            throw new IllegalArgumentException("Note date cannot be null.");
        }

        this.noteId = noteId;
        this.studentId = studentId;
        this.content = content;
        this.date = date;
    }

    /**
     * Returns the unique identifier of the note.
     *
     * @return The note's ID.
     */
    public String getNoteId() {
        return noteId;
    }

    /**
     * Sets the unique identifier of the note.
     *
     * @param noteId The new note ID.
     * @throws IllegalArgumentException if the noteId is null or empty.
     */
    public void setNoteId(String noteId) {
        if (noteId == null || noteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Note ID cannot be null or empty.");
        }
        this.noteId = noteId;
    }

    /**
     * Returns the ID of the student associated with this note.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the ID of the student associated with this note.
     *
     * @param studentId The new student ID.
     * @throws IllegalArgumentException if the studentId is null or empty.
     */
    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        this.studentId = studentId;
    }

    /**
     * Returns the textual content of the note.
     *
     * @return The note's content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the textual content of the note.
     *
     * @param content The new note content.
     * @throws IllegalArgumentException if the content is null or empty.
     */
    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Note content cannot be null or empty.");
        }
        this.content = content;
    }

    /**
     * Returns the date when the note was created or recorded.
     *
     * @return The note's date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when the note was created or recorded.
     *
     * @param date The new note date.
     * @throws IllegalArgumentException if the date is null.
     */
    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Note date cannot be null.");
        }
        this.date = date;
    }

    /**
     * Compares this Note object to the specified object. The result is true if and only if
     * the argument is not null and is a Note object that has the same noteId as this object.
     *
     * @param o The object to compare this Note against.
     * @return true if the given object represents a Note equivalent to this note, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(noteId, note.noteId);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(noteId);
    }

    /**
     * Returns a string representation of the Note object.
     *
     * @return A string containing the note's ID, student ID, content, and date.
     */
    @Override
    public String toString() {
        return "Note{" +
               "noteId='" + noteId + '\'' +
               ", studentId='" + studentId + '\'' +
               ", content='" + content + '\'' +
               ", date=" + date +
               '}';
    }
}