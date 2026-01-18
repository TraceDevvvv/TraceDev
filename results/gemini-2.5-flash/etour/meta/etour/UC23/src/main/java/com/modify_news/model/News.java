package com.modify_news.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a news article with various attributes.
 * This class is a data model for news items in the system.
 */
public class News {
    private String id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime publicationDate;

    /**
     * Constructs a new News object.
     *
     * @param id The unique identifier for the news.
     * @param title The title of the news.
     * @param content The main content of the news.
     * @param author The author of the news.
     * @param publicationDate The date and time when the news was published.
     */
    public News(String id, String title, String content, String author, LocalDateTime publicationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    // Getters
    public String getId() {
        return id;;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "News{" +
               "id='" + id + '\'' +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
               ", author='" + author + '\'' +
               ", publicationDate=" + publicationDate +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}