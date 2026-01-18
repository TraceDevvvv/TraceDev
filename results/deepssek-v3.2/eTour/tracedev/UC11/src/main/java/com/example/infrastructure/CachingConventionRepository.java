package com.example.infrastructure;

import com.example.domain.Convention;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Decorator that adds caching to a ConventionRepository for performance.
 */
public class CachingConventionRepository implements ConventionRepository {

    private final Map<String, List<Convention>> cache = new HashMap<>();
    private final ConventionRepository decoratedRepository;

    public CachingConventionRepository(ConventionRepository decoratedRepository) {
        this.decoratedRepository = decoratedRepository;
    }

    @Override
    public List<Convention> findByPointOfRestId(String pointOfRestId) {
        // Cache hit
        if (cache.containsKey(pointOfRestId)) {
            return cache.get(pointOfRestId);
        }
        // Cache miss: delegate to decorated repository
        List<Convention> conventions = decoratedRepository.findByPointOfRestId(pointOfRestId);
        if (conventions != null && !conventions.isEmpty()) {
            cache.put(pointOfRestId, conventions);
        }
        return conventions;
    }
}