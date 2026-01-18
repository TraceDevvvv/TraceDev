package com.etour.infrastructure;

import com.etour.domain.Bookmark;
import com.etour.domain.BookmarkRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In‑memory implementation of BookmarkRepository.
 */
public class BookmarkRepositoryImpl implements BookmarkRepository {
    // Simulates persistent storage
    private final Map<String, Bookmark> dataStore = new ConcurrentHashMap<>();

    @Override
    public Bookmark save(Bookmark bookmark) {
        dataStore.put(bookmark.getId(), bookmark);
        return bookmark;
    }

    @Override
    public Optional<Bookmark> findByTouristIdAndSiteId(String touristId, String siteId) {
        return dataStore.values().stream()
                .filter(b -> b.getTouristId().equals(touristId) && b.getSiteId().equals(siteId))
                .findFirst();
    }

    @Override
    public List<Bookmark> findAllByTouristId(String touristId) {
        List<Bookmark> result = new ArrayList<>();
        for (Bookmark bookmark : dataStore.values()) {
            if (bookmark.getTouristId().equals(touristId)) {
                result.add(bookmark);
            }
        }
        return result;
    }

    // Helper method not exposed by the interface – used internally for ID generation in the domain.
    // (In this design the domain generates its own ID; this method is kept for consistency with the diagram.)
    private String generateId() {
        return "BM" + System.currentTimeMillis() + "_" + dataStore.size();
    }
}