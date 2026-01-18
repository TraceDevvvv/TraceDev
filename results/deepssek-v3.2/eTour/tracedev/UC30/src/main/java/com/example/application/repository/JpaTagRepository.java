package com.example.application.repository;

import com.example.application.entity.TagEntity;

/**
 * JPA implementation of TagRepository.
 */
public class JpaTagRepository implements TagRepository {
    @Override
    public boolean exists(String tag) {
        // Simulate database check for existing tag
        // In a real implementation, this would query the database
        return false; // Assume tag does not exist for this example
    }

    @Override
    public void save(TagEntity tag) {
        // Simulate saving tag to database
        System.out.println("Saving tag: " + tag.getTag());
    }
}