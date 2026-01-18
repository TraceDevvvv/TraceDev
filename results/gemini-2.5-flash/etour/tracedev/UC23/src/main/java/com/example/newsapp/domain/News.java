package com.example.newsapp.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a News entity in the domain layer.
 * This is a POJO (Plain Old Java Object) used to encapsulate news data.
 */
public class News {
    private String id;
    private String title;
    private String content;
    private Date publicationDate;
    private String author;
    private String status; // e.g., "Draft", "Published", "Archived"

    /**
     * Default constructor.
     */
    public News() {
    }

    /**
     * Parameterized constructor for creating a News object.
     *
     * @param id The unique identifier for the news.
     * @param title The title of the news.
     * @param content The main body content of the news.
     * @param publicationDate The date when the news was published.
     * @param author The author of the news.
     * @param status The current status of the news.
     */
    public News(String id, String title, String content, Date publicationDate, String author, String status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
        this.author = author;
        this.status = status;
    }

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "News{" +
               "id='" + id + '\'' +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
               ", publicationDate=" + publicationDate +
               ", author='" + author + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}