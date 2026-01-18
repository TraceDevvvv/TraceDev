package com.example.domain;

/**
 * Represents a Class entity in the domain.
 * Implements SearchableEntity to allow searching.
 */
public class Class implements SearchableEntity {
    private Long id;
    private String name;
    private String description;
    private String searchableContent;

    // Constructors
    public Class() {
    }

    public Class(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.searchableContent = generateSearchableContent();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return (name != null ? name : "") + " " + (description != null ? description : "");
    }
}