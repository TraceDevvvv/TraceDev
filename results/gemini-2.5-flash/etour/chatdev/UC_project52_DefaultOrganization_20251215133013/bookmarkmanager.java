'''
Manages the collection of bookmarked tourist sites for a tourist.
Provides functionality to add and remove sites from the bookmarks.
Can simulate server connection issues during site removal.
'''
package com.chatdev.tourism;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
public class BookmarkManager {
    private List<Site> bookmarks;
    private boolean simulateConnectionError = false; // Flag to simulate server connection problems
    /**
     * Constructs a new BookmarkManager with an empty list of bookmarks.
     */
    public BookmarkManager() {
        this.bookmarks = new ArrayList<>();
    }
    /**
     * Adds a site to the list of bookmarks.
     * @param site The Site object to add.
     * @return true if the site was added successfully, false if a site with the same ID already exists.
     */
    public boolean addSite(Site site) {
        if (!bookmarks.contains(site)) {
            bookmarks.add(site);
            return true;
        }
        return false;
    }
    /**
     * Removes a site from the list of bookmarks based on its ID.
     * Simulates a server connection error if `simulateConnectionError` is true.
     * @param siteId The ID of the site to remove.
     * @return true if the site was found and removed, false otherwise.
     * @throws ServerConnectionException If a simulated server connection error occurs.
     */
    public boolean removeSite(String siteId) throws ServerConnectionException {
        // Simulate server connection interruption
        if (simulateConnectionError) {
            throw new ServerConnectionException("Connection to ETOUR server interrupted during removal.");
        }
        Iterator<Site> iterator = bookmarks.iterator();
        while (iterator.hasNext()) {
            Site site = iterator.next();
            if (site.getId().equals(siteId)) {
                iterator.remove();
                return true;
            }
        }
        return false; // Site not found
    }
    /**
     * Retrieves an unmodifiable list of all currently bookmarked sites.
     * @return A list of Site objects.
     */
    public List<Site> getAllBookmarks() {
        return Collections.unmodifiableList(bookmarks);
    }
    /**
     * Sets the flag to simulate server connection errors.
     * @param simulateConnectionError True to simulate errors, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }
}