package com.example.infrastructure;

import com.example.domain.SearchPreferences;
import javax.cache.Cache;

/**
 * Repository implementation that caches preferences for better responsiveness.
 */
public class CachedPreferencesRepository implements PreferencesRepository {
    private Cache cache;
    private PreferencesRepository delegate;

    public CachedPreferencesRepository(Cache cache, PreferencesRepository delegate) {
        this.cache = cache;
        this.delegate = delegate;
    }

    @Override
    public SearchPreferences findByTouristId(String touristId) {
        // Check cache first
        SearchPreferences cached = (SearchPreferences) cache.get(touristId);
        if (cached != null) {
            return cached;
        }
        // Delegate to the underlying repository
        SearchPreferences prefs = delegate.findByTouristId(touristId);
        if (prefs != null) {
            cache.put(touristId, prefs);
        }
        return prefs;
    }

    @Override
    public void save(SearchPreferences preferences) {
        // Invalidate cache for this tourist
        cache.remove(preferences.getTouristId());
        // Save via delegate
        delegate.save(preferences);
    }
}