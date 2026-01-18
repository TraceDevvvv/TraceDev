'''
PreferenceManager.java
This class manages the list of bookmarked sites and simulates server connection.
It provides methods for adding, removing, and retrieving sites, as well as
managing the connection state to the ETOUR server.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class PreferenceManager {
    private List<Site> bookmarkedSites;
    private boolean connectionActive;
    /**
     * Constructor initializes an empty list of bookmarked sites
     * and sets the connection to active by default.
     */
    public PreferenceManager() {
        bookmarkedSites = new ArrayList<>();
        connectionActive = true; // Simulating active connection by default
    }
    /**
     * Adds a site to the bookmarks list.
     * @param site the site to add
     */
    public void addSite(Site site) {
        if (site != null) {
            bookmarkedSites.add(site);
        }
    }
    /**
     * Removes a site by its index in the list.
     * Validates the index range before removal.
     * @param index the index of the site to remove
     * @return true if removal successful, false otherwise (invalid index)
     */
    public boolean removeSiteByIndex(int index) {
        if (index >= 0 && index < bookmarkedSites.size()) {
            bookmarkedSites.remove(index);
            return true;
        }
        return false;
    }
    /**
     * Removes a site by its ID.
     * @param siteId the ID of the site to remove
     * @return true if removal successful, false otherwise (site not found)
     */
    public boolean removeSiteById(String siteId) {
        for (int i = 0; i < bookmarkedSites.size(); i++) {
            if (bookmarkedSites.get(i).getSiteId().equals(siteId)) {
                bookmarkedSites.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Gets the current list of bookmarked sites.
     * Returns an unmodifiable copy to prevent external modification.
     * @return list of bookmarked sites
     */
    public List<Site> getBookmarkedSites() {
        return Collections.unmodifiableList(new ArrayList<>(bookmarkedSites));
    }
    /**
     * Gets the number of bookmarked sites.
     * @return count of bookmarked sites
     */
    public int getBookmarkCount() {
        return bookmarkedSites.size();
    }
    /**
     * Checks if the connection to server ETOUR is active.
     * @return true if connection is active, false otherwise
     */
    public boolean isConnectionActive() {
        return connectionActive;
    }
    /**
     * Sets the connection status to the ETOUR server.
     * Used for testing edge cases and simulating connection disruptions.
     * @param active the connection status (true for active, false for disconnected)
     */
    public void setConnectionActive(boolean active) {
        this.connectionActive = active;
    }
    /**
     * Checks if a specific site is already bookmarked.
     * @param siteId the site ID to check
     * @return true if site is bookmarked, false otherwise
     */
    public boolean containsSite(String siteId) {
        for (Site site : bookmarkedSites) {
            if (site.getSiteId().equals(siteId)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Clears all bookmarked sites.
     * Use with caution - removes all bookmarks.
     */
    public void clearAllBookmarks() {
        bookmarkedSites.clear();
    }
}