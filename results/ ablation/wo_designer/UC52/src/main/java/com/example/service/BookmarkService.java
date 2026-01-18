package com.example.service;

import com.example.model.Site;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Service to manage bookmarks and notifications.
 */
public class BookmarkService {
    
    private List<Site> bookmarkedSites = new CopyOnWriteArrayList<>();
    private List<String> notifications = new CopyOnWriteArrayList<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    
    /**
     * Remove a site from bookmarks after confirmation.
     * @param site The site to remove.
     * @param confirm True if the tourist confirms the removal.
     * @return true if removal was successful, false otherwise.
     */
    public boolean removeSiteFromBookmarks(Site site, boolean confirm) {
        if (confirm) {
            Future<Boolean> future = executorService.submit(() -> {
                long startTime = System.currentTimeMillis();
                try {
                    // Step 4: Remove the site from bookmarks.
                    boolean removed = bookmarkedSites.remove(site);
                    if (removed) {
                        // Step 5: Remove the notification for the site.
                        removeNotificationForSite(site.getId());
                    }
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    // Quality Requirement: removal within 2 seconds.
                    if (duration > 2000) {
                        System.err.println("Warning: Removal took longer than 2 seconds: " + duration + " ms");
                    }
                    return removed;
                } catch (Exception e) {
                    System.err.println("Error during site removal: " + e.getMessage());
                    return false;
                }
            });
            try {
                return future.get(3, TimeUnit.SECONDS); // Slightly above 2 seconds for timeout.
            } catch (TimeoutException e) {
                System.err.println("Timeout: Removal operation took too long.");
                return false;
            } catch (Exception e) {
                System.err.println("Error executing removal: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Removal cancelled by Tourist.");
            return false;
        }
    }
    
    /**
     * Remove notification for a specific site.
     * @param siteId The ID of the site.
     */
    private void removeNotificationForSite(String siteId) {
        notifications.removeIf(notification -> notification.contains(siteId));
    }
    
    /**
     * Get all bookmarked sites.
     * @return A list of bookmarked sites.
     */
    public List<Site> getBookmarkedSites() {
        return new ArrayList<>(bookmarkedSites);
    }
    
    /**
     * Add a site to bookmarks.
     * @param site The site to bookmark.
     * @return true if added, false otherwise.
     */
    public boolean addSiteToBookmarks(Site site) {
        if (site != null && !bookmarkedSites.contains(site)) {
            bookmarkedSites.add(site);
            notifications.add("New bookmark for site: " + site.getId());
            return true;
        }
        return false;
    }
    
    /**
     * Get all notifications.
     * @return A list of notifications.
     */
    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }
    
    /**
     * Clean up resources.
     */
    public void shutdown() {
        executorService.shutdown();
    }
}