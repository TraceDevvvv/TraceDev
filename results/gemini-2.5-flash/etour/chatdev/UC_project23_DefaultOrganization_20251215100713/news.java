/**
 * Represents a single news item with properties such as ID, title, content, author, and publication date.
 * This is a POJO (Plain Old Java Object) used to model the news data.
 */
package com.chatdev.newsapp.models;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
public class News {
    private String id;
    private String title;
    private String content;
    private String author;
    private String date; // Stored as String for simplicity, could be LocalDate
    /**
     * Constructs a new News object.
     * @param id The unique identifier for the news item.
     * @param title The title of the news item.
     * @param content The main content/body of the news item.
     * @param author The author of the news item.
     * @param date The publication date of the news item (e.g., "YYYY-MM-DD").
     */
    public News(String id, String title, String content, String author, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
    }
    /**
     * Default constructor.
     */
    public News() {
        // Default constructor, useful for FXML or deserialization if needed
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * Overrides the toString method to provide a meaningful string representation of the News object.
     * This is useful for displaying News objects directly in lists or tables (e.g., TableView).
     * @return A string representation of the news item, primarily its title and author.
     */
    @Override
    public String toString() {
        return title + " - " + author;
    }
    /**
     * Overrides the equals method to compare News objects based on their ID.
     * Two news items are considered equal if they have the same ID.
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id);
    }
    /**
     * Overrides the hashCode method, consistent with the equals method.
     * @return The hash code of the News object, based on its ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}