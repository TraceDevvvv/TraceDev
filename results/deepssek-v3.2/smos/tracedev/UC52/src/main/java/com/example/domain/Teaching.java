package com.example.domain;

/**
 * Represents a Teaching entity in the domain.
 * Implements SearchableEntity to allow searching.
 */
public class Teaching implements SearchableEntity {
    private Long id;
    private String title;
    private String subject;
    private String instructor;
    private String searchableContent;

    // Constructors
    public Teaching() {
    }

    public Teaching(Long id, String title, String subject, String instructor) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.instructor = instructor;
        this.searchableContent = generateSearchableContent();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.searchableContent = generateSearchableContent();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.searchableContent = generateSearchableContent();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
        this.searchableContent = generateSearchableContent();
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
        this.searchableContent = generateSearchableContent();
    }

    // SearchableEntity implementation
    @Override
    public String getSearchableContent() {
        if (searchableContent == null) {
            searchableContent = generateSearchableContent();
        }
        return searchableContent;
    }

    /**
     * Generates the searchable content string from relevant fields.
     * @return concatenated searchable fields.
     */
    private String generateSearchableContent() {
        return (title != null ? title : "") + " " +
               (subject != null ? subject : "") + " " +
               (instructor != null ? instructor : "");
    }
}