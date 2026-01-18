package hec.adapters;

import hec.core.Tag;
import hec.ports.TagRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory implementation of TagRepository.
 * Uses a HashMap for storage; suitable for testing or simple scenarios.
 */
public class InMemoryTagRepository implements TagRepository {
    private final Map<String, Tag> tags;

    /**
     * Constructor initializing the storage map.
     */
    public InMemoryTagRepository() {
        this.tags = new HashMap<>();
    }

    @Override
    public Tag save(Tag tag) {
        tags.put(tag.getId(), tag);
        return tag;
    }

    @Override
    public boolean existsByName(String name) {
        return tags.values().stream().anyMatch(tag -> tag.getName().equals(name));
    }

    @Override
    public Optional<Tag> findById(String id) {
        return Optional.ofNullable(tags.get(id));
    }
}