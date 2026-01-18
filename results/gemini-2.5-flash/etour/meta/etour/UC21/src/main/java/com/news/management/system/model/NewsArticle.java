package com.news.management.system.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Model class representing a news article.
 * It encapsulates the data related to a news item, including its ID, title, content, author,
 * publication date, categories, and tags.
 */
public class NewsArticle {
    private String id;
    private String title;
    private String content;
    private String author;
    private Date publicationDate;
    private List<String> categories;
    private List<String> tags;

    /**
     * Constructs a new NewsArticle with the specified details.
     * A unique ID is generated automatically for each new article.
     *
     * @param title The title of the news article.
     * @param content The main content of the news article.
     * @param author The author of the news article.
     * @param publicationDate The date when the news article was published.
     * @param categories A list of categories the news article belongs to.
     * @param tags A list of tags associated with the news article.
     */
    public NewsArticle(String title, String content, String author, Date publicationDate, List<String> categories, List<String> tags) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the news article
        this.title = title;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
        this.categories = categories;
        this.tags = tags;
    }

    /**
     * Constructs a new NewsArticle with a given ID. This constructor is typically used
     * when loading an existing news article from a data source.
     *
     * @param id The unique identifier of the news article.
     * @param title The title of the news article.
     * @param content The main content of the news article.
     * @param author The author of the news article.
     * @param publicationDate The date when the news article was published.
     * @param categories A list of categories the news article belongs to.
     * @param tags A list of tags associated with the news article.
     */
    public NewsArticle(String id, String title, String content, String author, Date publicationDate, List<String> categories, List<String> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
        this.categories = categories;
        this.tags = tags;
    }

    /**
     * Returns the unique identifier of the news article.
     * @return The ID of the news article.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the news article.
     * @param id The new ID for the news article.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the title of the news article.
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the news article.
     * @param title The new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the content of the news article.
     * @return The content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the news article.
     * @param content The new content.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the author of the news article.
     * @return The author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the news article.
     * @param author The new author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the publication date of the news article.
     * @return The publication date.
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets the publication date of the news article.
     * @param publicationDate The new publication date.
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Returns the list of categories associated with the news article.
     * @return A list of categories.
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Sets the list of categories for the news article.
     * @param categories The new list of categories.
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * Returns the list of tags associated with the news article.
     * @return A list of tags.
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Sets the list of tags for the news article.
     * @param tags The new list of tags.
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Checks if the news article object contains valid data.
     * This method performs basic validation checks. More comprehensive validation
     * is handled by the ValidationUtil class.
     *
     * @return true if the article is considered valid, false otherwise.
     */
    public boolean isValid() {
        // Basic check for essential fields. More detailed validation is done in ValidationUtil.
        return title != null && !title.trim().isEmpty() &&
               content != null && !content.trim().isEmpty() &&
               author != null && !author.trim().isEmpty() &&
               publicationDate != null;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
               "id='" + id + '\'' +
               ", title='" + title + '\'' +
               ", content='" + (content != null && content.length() > 50 ? content.substring(0, 50) + "..." : content) + '\'' +
               ", author='" + author + '\'' +
               ", publicationDate=" + publicationDate +
               ", categories=" + categories +
               ", tags=" + tags +
               '}';
    }
}