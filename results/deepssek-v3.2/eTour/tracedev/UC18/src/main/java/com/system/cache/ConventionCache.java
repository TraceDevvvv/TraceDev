package com.system.cache;

import com.system.entities.RefreshmentPointConvention;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple cache for conventions to improve efficiency.
 * Quality Requirement: Efficient verification to prevent data conflicts.
 */
public class ConventionCache {
    private Map<String, RefreshmentPointConvention> cache = new HashMap<>();

    public RefreshmentPointConvention getConvention(String id) {
        return cache.get(id);
    }

    public void updateConvention(RefreshmentPointConvention convention) {
        cache.put(convention.getId(), convention);
    }

    public void invalidate(String id) {
        cache.remove(id);
    }
}