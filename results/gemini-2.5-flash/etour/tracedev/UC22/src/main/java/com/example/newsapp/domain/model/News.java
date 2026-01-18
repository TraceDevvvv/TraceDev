package com.example.newsapp.domain.model;

import java.util.Date;

/**
 * Represents a news article in the domain model.
 */
public class News {
    private String id;
    private String title;
    private String content;
    private Date publicationDate;

    /**
     * Constructs a new News object.
     *
     * @param id The unique identifier of the news.
     * @param title The title of the news.
     * @param content The main content of the news.
     * @param publicationDate The date when the news was published.
     */
    public News(String id, String title, String content, Date publicationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    /**
     * Gets the unique identifier of the news.
     * @return The news ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the title of the news.
     * @return The news title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the content of the news.
     * @return The news content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the publication date of the news.
     * @return The news publication date.
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    @Override
    public String toString() {
        return "News{" +
               "id='" + id + '\'' +
               ", title='" + title + '\'' +
               ", content='" + (content.length() > 50 ? content.substring(0, 50) + "..." : content) + '\'' +
               ", publicationDate=" + publicationDate +
               '}';
    }
}