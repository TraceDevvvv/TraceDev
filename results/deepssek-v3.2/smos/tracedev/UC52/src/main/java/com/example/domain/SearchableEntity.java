package com.example.domain;

/**
 * Marker interface for all searchable entities in the domain.
 * Implementers must provide a method to get their searchable content.
 */
public interface SearchableEntity {
    /**
     * Returns the concatenated searchable content of the entity.
     * @return A string containing all searchable text fields.
     */
    String getSearchableContent();
}