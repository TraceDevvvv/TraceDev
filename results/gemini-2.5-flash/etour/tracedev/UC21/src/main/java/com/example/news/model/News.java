package com.example.news.model;

import com.example.news.dto.NewsFormRequest;

import java.util.Date;
import java.util.UUID;

/**
 * Represents the News entity in the system.
 */
public class News {
    public String id; // Changed from private to public to match class diagram
    public String title; // Changed from private to public to match class diagram
    public String content; // Changed from private to public to match class diagram
    public String authorId; // Changed from private to public to match class diagram
    public Date publicationDate; // Changed from private to public to match class diagram
    public String status; // Changed from private to public to match class diagram

    // Default constructor for persistence frameworks or general use
    public News() {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID upon creation
        this.publicationDate = new Date(); // Set current date as publication date by default
        this.status = "draft"; // Initial status
    }

    public News(String id, String title, String content, String authorId, Date publicationDate, String status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    /**
     * Static factory method to create a News entity from a NewsFormRequest DTO.
     * This aligns with the Sequence Diagram's `Service -> NewsEntity: create(validatedData)`
     * and the Class Diagram's `static create(request : NewsFormRequest) : News`.
     *
     * @param request The data transfer object containing news details.
     * @return A new News entity.
     */
    public static News create(NewsFormRequest request) {
        News news = new News();
        // Access public fields directly
        news.title = request.getTitle();
        news.content = request.getContent();
        news.authorId = request.getAuthorId();
        // Publication date is set in the constructor, status is "draft" initially.
        // It can be updated later by a business logic service.
        return news;
    }

    /**
     * Validates the essential fields of the news entity.
     * This is a basic example; real validation would be more complex.
     *
     * @return true if the news entity is considered valid, false otherwise.
     */
    public boolean isValid() {
        // Access public fields directly
        return title != null && !title.trim().isEmpty() &&
               content != null && !content.trim().isEmpty() &&
               authorId != null && !authorId.trim().isEmpty();
    }

    // Removed all public getters and setters as fields are now public to match class diagram
    // Getters and setters for public fields are redundant if direct access is intended per UML visibility.

    @Override
    public String toString() {
        return "News{" +
               "id='" + id + '\'' +
               ", title='\" + title + '\\'' +" +
               ", content='\" + content + '\\'' +" +
               ", authorId='\" + authorId + '\\'' +" +
               ", publicationDate=" + publicationDate +
               ", status='\" + status + '\\'' +" +
               '}';
    }
}