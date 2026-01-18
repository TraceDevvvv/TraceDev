package com.example.application.interfaces;

import com.example.domain.entities.Tag;

import java.util.Optional;

/**
 * Repository interface for Tag persistence operations.
 */
public interface TagRepository {
    /**
     * Saves a tag.
     */
    void save(Tag tag);

    /**
     * Finds a tag by its name.
     * Ensures no duplicate tags by checking for existing tags before creation.
     */
    Optional<Tag> findByName(String name);
}