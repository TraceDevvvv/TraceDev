package com.example.notessystem.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for displaying student notes in the UI.
 * This class represents a simplified view of a Note, tailored for presentation.
 */
public class NoteDisplayDto {
    private String noteId;
    private LocalDate date;
    private String content;
    private String author;

    /**
     * Constructs a new NoteDisplayDto.
     * @param noteId The unique identifier of the note.
     * @param date The date when the note was created.
     * @param content The actual content of the note.
     * @param author The author of the note.
     */
    public NoteDisplayDto(String noteId, LocalDate date, String content, String author) {
        this.noteId = noteId;
        this.date = date;
        this.content = content;
        this.author = author;
    }

    // --- Getters ---
    public String getNoteId() {
        return noteId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "NoteDisplayDto{" +
               "noteId='" + noteId + '\'' +
               ", date=" + date +
               ", content='" + content + '\'' +
               ", author='" + author + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDisplayDto that = (NoteDisplayDto) o;
        return Objects.equals(noteId, that.noteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId);
    }
}