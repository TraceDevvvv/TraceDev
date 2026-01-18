package hec.adapters;

import hec.core.Tag;
import hec.ports.TagRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Cache decorator for TagRepository.
 * Implements caching to improve performance as per requirement.
 */
public class CacheRepository implements TagRepository {
    private final Map<String, Tag> cache;
    private final TagRepository delegate;

    /**
     * Constructor that wraps a delegate repository.
     *
     * @param delegate the actual repository to delegate to
     */
    public CacheRepository(TagRepository delegate) {
        this.cache = new HashMap<>();
        this.delegate = delegate;
    }

    @Override
    public Tag save(Tag tag) {
        // Delegate to actual repository.
        Tag savedTag = delegate.save(tag);
        // Update cache.
        cache.put(savedTag.getId(), savedTag);
        return savedTag;
    }

    @Override
    public boolean existsByName(String name) {
        // Check cache first (if we stored by name, would be more complex).
        // For simplicity, delegate.
        return delegate.existsByName(name);
    }

    @Override
    public Optional<Tag> findById(String id) {
        // Check cache first.
        if (cache.containsKey(id)) {
            return Optional.of(cache.get(id));
        }
        // Delegate if not in cache.
        Optional<Tag> tag = delegate.findById(id);
        tag.ifPresent(t -> cache.put(t.getId(), t));
        return tag;
    }

    /**
     * Checks cache status.
     *
     * @return true if cache is not empty (simulated)
     */
    public boolean checkCache() {
        return !cache.isEmpty();
    }
}