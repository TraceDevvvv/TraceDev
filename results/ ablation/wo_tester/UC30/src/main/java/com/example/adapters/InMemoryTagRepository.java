package com.example.adapters;

import com.example.application.interfaces.TagRepository;
import com.example.domain.entities.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory implementation of TagRepository for demonstration.
 */
public class InMemoryTagRepository implements TagRepository {
    private Map<String, Tag> tags = new HashMap<>();

    @Override
    public void save(Tag tag) {
        tags.put(tag.getName(), tag);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return Optional.ofNullable(tags.get(name));
    }
}