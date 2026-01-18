package com.example.entity;

import com.example.command.InsertNewsCommand;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a news article in the system.
 */
public class NewsEntity {

    private UUID id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Private constructor
    private NewsEntity(UUID id, String title, String content, String author,
                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Factory method to create a NewsEntity from an InsertNewsCommand.
     */
    public static NewsEntity createFromCommand(InsertNewsCommand command) {
        LocalDateTime now = LocalDateTime.now();
        return new NewsEntity(
                command.getId(),
                command.getTitle(),
                command.getContent(),
                command.getAuthor(),
                now,
                now
        );
    }

    // Getters
    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}