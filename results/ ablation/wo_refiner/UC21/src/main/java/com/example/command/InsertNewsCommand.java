package com.example.command;

import com.example.entity.NewsEntity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Command object representing the request to insert news.
 * Contains validation logic.
 */
public class InsertNewsCommand {

    private UUID id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime timestamp;

    public InsertNewsCommand(String title, String content, String author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.author = author;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Validates the command data.
     * @return true if valid, false otherwise.
     */
    public boolean validate() {
        return title != null && !title.trim().isEmpty() &&
               content != null && !content.trim().isEmpty() &&
               author != null && !author.trim().isEmpty();
    }

    // Getters
    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public LocalDateTime getTimestamp() { return timestamp; }
}