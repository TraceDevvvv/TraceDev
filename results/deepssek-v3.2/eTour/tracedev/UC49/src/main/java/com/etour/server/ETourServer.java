package com.etour.server;

import com.etour.dto.BookmarkDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulates an external eTour server that provides bookmark data.
 */
public class ETourServer {
    /**
     * Simulates fetching bookmark data for a user.
     *
     * @param userId the user ID
     * @return a list of bookmarks for that user
     */
    public List<BookmarkDTO> getBookmarkData(int userId) {
        // Simulate a delay or possible interruption (as per sequence diagram).
        // For demonstration, we return some dummy data.
        List<BookmarkDTO> bookmarks = new ArrayList<>();
        bookmarks.add(new BookmarkDTO(1, "Eiffel Tower", "Iconic tower in Paris", "Paris, France"));
        bookmarks.add(new BookmarkDTO(2, "Colosseum", "Ancient amphitheater in Rome", "Rome, Italy"));
        bookmarks.add(new BookmarkDTO(3, "Statue of Liberty", "Famous statue in New York", "New York, USA"));
        return bookmarks;
    }
}