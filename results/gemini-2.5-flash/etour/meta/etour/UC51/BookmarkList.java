// BookmarkList.java
package com.etour.insertpreferencesite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages a list of bookmarked sites for a tourist.
 * This class ensures thread-safe operations for adding and retrieving bookmarks.
 */
public class BookmarkList {
    private final List<Site> bookmarkedSites;

    /**
     * Constructs a new BookmarkList.
     * Initializes an empty, synchronized list to store bookmarked sites.
     */
    public BookmarkList() {
        // Using Collections.synchronizedList to ensure thread-safe operations
        this.bookmarkedSites = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Adds a site to the bookmark list.
     * If the site is already in the list, it will not be added again.
     *
     * @param site The Site object to be added to bookmarks.
     * @return true if the site was added successfully, false if it was already present.
     */
    public boolean addBookmark(Site site) {
        // Synchronize on the list itself to ensure atomicity of check-then-act
        synchronized (bookmarkedSites) {
            if (!bookmarkedSites.contains(site)) {
                bookmarkedSites.add(site);
                System.out.println("Site '" + site.getName() + "' added to bookmarks.");
                return true;
            } else {
                System.out.println("Site '" + site.getName() + "' is already in bookmarks.");
                return false;
            }
        }
    }

    /**
     * Retrieves an unmodifiable list of all bookmarked sites.
     *
     * @return A List of Site objects that are bookmarked.
     */
    public List<Site> getBookmarkedSites() {
        // Return a shallow copy to prevent external modification of the internal list
        synchronized (bookmarkedSites) {
            return new ArrayList<>(bookmarkedSites);
        }
    }

    /**
     * Checks if the bookmark list is empty.
     *
     * @return true if the list contains no sites, false otherwise.
     */
    public boolean isEmpty() {
        synchronized (bookmarkedSites) {
            return bookmarkedSites.isEmpty();
        }
    }

    /**
     * Returns the number of sites currently in the bookmark list.
     *
     * @return The size of the bookmark list.
     */
    public int size() {
        synchronized (bookmarkedSites) {
            return bookmarkedSites.size();
        }
    }

    /**
     * Clears all sites from the bookmark list.
     */
    public void clearBookmarks() {
        synchronized (bookmarkedSites) {
            bookmarkedSites.clear();
            System.out.println("All bookmarks cleared.");
        }
    }

    /**
     * Returns a string representation of the BookmarkList object.
     *
     * @return A string listing all bookmarked sites.
     */
    @Override
    public String toString() {
        synchronized (bookmarkedSites) {
            if (bookmarkedSites.isEmpty()) {
                return "BookmarkList: No sites bookmarked.";
            }
            StringBuilder sb = new StringBuilder("BookmarkList:\n");
            for (int i = 0; i < bookmarkedSites.size(); i++) {
                sb.append("  ").append(i + 1).append(". ").append(bookmarkedSites.get(i).getName()).append("\n");
            }
            return sb.toString();
        }
    }
}