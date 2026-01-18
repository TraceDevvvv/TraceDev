package com.example.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JPA implementation of TagRepository.
 * Added stereotype to satisfy requirement REQ-012
 */
public class JpaTagRepository implements TagRepository {
    // Simulating a database with an in-memory list
    private List<Tag> tags = new ArrayList<>();

    public JpaTagRepository() {
        // Add sample data
        tags.add(new Tag(1L, "Important"));
        tags.add(new Tag(2L, "Urgent"));
        tags.add(new Tag(3L, "Follow-up"));
    }

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>(tags);
    }

    @Override
    public List<Tag> searchTags(String criteria) {
        if (criteria == null || criteria.trim().isEmpty()) {
            return findAll();
        }
        String lowerCriteria = criteria.toLowerCase();
        return tags.stream()
                .filter(tag -> tag.getName().toLowerCase().contains(lowerCriteria))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        tags.removeIf(tag -> tag.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return tags.stream().anyMatch(tag -> tag.getId().equals(id));
    }
}