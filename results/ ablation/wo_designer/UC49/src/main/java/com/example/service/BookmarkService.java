package com.example.service;

import com.example.model.Bookmark;
import com.example.model.Tourist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to manage bookmarks (favorites) for Tourists.
 */
public class BookmarkService {

    // In-memory storage for bookmarks. In a real app, this would be a database.
    private List<Bookmark> bookmarkStorage = new ArrayList<>();

    public BookmarkService() {
        // Initialize with some sample bookmarks for demonstration.
        bookmarkStorage.add(new Bookmark("1", "tourist1", "Eiffel Tower", "Famous landmark in Paris", "https://example.com/eiffel"));
        bookmarkStorage.add(new Bookmark("2", "tourist1", "Louvre Museum", "World's largest art museum", "https://example.com/louvre"));
        bookmarkStorage.add(new Bookmark("3", "tourist2", "Grand Canyon", "Steep-sided canyon in Arizona", "https://example.com/canyon"));
    }

    /**
     * Retrieves all bookmarks for a given Tourist.
     * Assumes the Tourist is authenticated (caller should verify).
     *
     * @param tourist The authenticated Tourist
     * @return List of bookmarks belonging to the Tourist
     */
    public List<Bookmark> getBookmarksForTourist(Tourist tourist) {
        if (tourist == null) {
            return new ArrayList<>();
        }
        return bookmarkStorage.stream()
                .filter(b -> b.getTouristId().equals(tourist.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Adds a new bookmark for a Tourist.
     * (Not required by the use case, but included for completeness.)
     */
    public void addBookmark(Bookmark bookmark) {
        bookmarkStorage.add(bookmark);
    }
}