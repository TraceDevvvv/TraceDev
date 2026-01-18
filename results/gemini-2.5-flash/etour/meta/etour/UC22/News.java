package com.example.newsapp;

import java.util.Objects;

/**
 * Represents a news article with a unique ID, title, and content.
 */
public class News {
    private String id;
    private String title;
    private String content;

    /**
     * Constructs a new News object.
     *
     * @param id The unique identifier for the news.
     * @param title The title of the news.
     * @param content The content of the news.
     */
    public News(String id, String title, String content) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("News ID cannot be null or empty.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("News title cannot be null or empty.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("News content cannot be null or empty.");
        }
        this.id = id;
        this.title = title;
        this.content = content;
    }

    /**
     * Returns the unique identifier of the news.
     *
     * @return The news ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the title of the news.
     *
     * @return The news title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the content of the news.
     *
     * @return The news content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the title of the news.
     *
     * @param title The new title for the news.
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("News title cannot be null or empty.");
        }
        this.title = title;
    }

    /**
     * Sets the content of the news.
     *
     * @param content The new content for the news.
     */
    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("News content cannot be null or empty.");
        }
        this.content = content;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: \"" + title + "\"";
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