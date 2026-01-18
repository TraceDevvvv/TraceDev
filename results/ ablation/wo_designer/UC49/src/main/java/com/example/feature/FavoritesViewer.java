package com.example.feature;

import com.example.model.Bookmark;
import com.example.model.Tourist;
import com.example.service.AuthenticationService;
import com.example.service.BookmarkService;

import java.util.List;

/**
 * Implements the use case: Tourist views list of personal favorites (bookmarks).
 */
public class FavoritesViewer {

    private AuthenticationService authService;
    private BookmarkService bookmarkService;

    public FavoritesViewer(AuthenticationService authService, BookmarkService bookmarkService) {
        this.authService = authService;
        this.bookmarkService = bookmarkService;
    }

    /**
     * Main flow of the use case.
     * 1. Tourist selects the feature to display the list of personal favorites.
     * 2. System uploads the list of bookmarks.
     * 3. System displays the list of bookmarks.
     *
     * @param tourist The Tourist requesting the favorites list
     * @return List of bookmarks if successful, empty list if not authenticated
     */
    public List<Bookmark> viewFavorites(Tourist tourist) {
        // Entry Condition: Tourist HAS successfully authenticated
        if (!authService.isAuthenticated(tourist)) {
            System.out.println("Tourist is not authenticated. Cannot view favorites.");
            return List.of();
        }

        // Flow of Events: System uploads the list of bookmarks.
        List<Bookmark> favorites = bookmarkService.getBookmarksForTourist(tourist);

        // Exit Conditions: The system displays the list of bookmarks.
        displayBookmarks(favorites);
        return favorites;
    }

    /**
     * Displays the bookmarks to the console.
     * In a real application, this would update the UI.
     */
    private void displayBookmarks(List<Bookmark> bookmarks) {
        if (bookmarks.isEmpty()) {
            System.out.println("No bookmarks found.");
        } else {
            System.out.println("Your Favorites:");
            for (Bookmark b : bookmarks) {
                System.out.println(" - " + b.getTitle() + ": " + b.getDescription() + " (" + b.getUrl() + ")");
            }
        }
    }
}