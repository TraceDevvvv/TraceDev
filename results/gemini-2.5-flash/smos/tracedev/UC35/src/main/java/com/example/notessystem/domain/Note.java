package com.example.notessystem.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a core business entity: a Note associated with a student.
 */
public class Note {
    private String id;
    private String studentId; // Kept as foreign key for data retrieval, consistent with INoteRepository
    private String content;
    private LocalDate date;
    private String author;

    /**
     * Constructs a new Note object.
     * @param id The unique identifier for the note.
     * @param studentId The ID of the student this note belongs to.
     * @param content The content of the note.
     * @param date The date the note was created.
     * @param author The author of the note.
     */
    public Note(String id, String studentId, String content, LocalDate date, String author) {
        this.id = id;
        this.studentId = studentId;
        this.content = content;
        this.date = date;
        this.author = author;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    // --- Setters (if mutation is allowed in the domain) ---
    // For simplicity, making them public. In a real-world scenario, setters might be private
    // or part of specific domain methods to ensure business rules.
    public void setId(String id) {
        this.id = id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Note{" +
               "id='" + id + '\'' +
               ", studentId='" + studentId + '\'' +
               ", content='" + content + '\'' +
               ", date=" + date +
               ", author='" + author + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}