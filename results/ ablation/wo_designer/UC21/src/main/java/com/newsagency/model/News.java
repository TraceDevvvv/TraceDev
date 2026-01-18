package com.newsagency.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a News entity.
 * Contains the data fields for a news item.
 */
public class News {
    private int id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime publicationDate;
    private String category;

    public News() {}

    public News(int id, String title, String content, String author, LocalDateTime publicationDate, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
        this.category = category;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public LocalDateTime getPublicationDate() { return publicationDate; }
    public void setPublicationDate(LocalDateTime publicationDate) { this.publicationDate = publicationDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(
                "News{id=%d, title='%s', content='%s', author='%s', publicationDate=%s, category='%s'}",
                id, title, content, author, publicationDate != null ? publicationDate.format(formatter) : null, category
        );
    }
}