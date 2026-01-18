'''
Manages the list of bookmarked sites for the tourist.
It simulates interaction with a backend server (ETOUR) for adding bookmarks.
'''
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
// Site class is in the same default package, so no explicit import is needed.
public class BookmarkManager {
    private List<Site> favorites;
    private static final double SERVER_FAILURE_PROBABILITY = 0.2; // 20% chance of simulated server failure
    private Random random;
    /**
     * Constructs a new BookmarkManager.
     * Initializes an empty list of favorite sites.
     */
    public BookmarkManager() {
        this.favorites = new ArrayList<>();
        this.random = new Random();
    }
    /**
     * Adds a site to the list of bookmarks.
     * This method simulates a connection to an ETOUR server and its potential failure.
     *
     * @param site The Site object to be added to favorites.
     * @throws ConnectionToServerException If the simulated connection to the ETOUR server fails.
     * @throws IllegalArgumentException If the site is null or already bookmarked.
     */
    public void addBookmark(Site site) throws ConnectionToServerException {
        if (site == null) {
            throw new IllegalArgumentException("Site cannot be null.");
        }
        if (isSiteBookmarked(site)) {
            throw new IllegalArgumentException("Site '" + site.getName() + "' is already in favorites.");
        }
        // Simulate connection to ETOUR server and potential failure
        if (random.nextDouble() < SERVER_FAILURE_PROBABILITY) {
            throw new ConnectionToServerException("Interruption of connection to the server ETOUR.");
        }
        favorites.add(site);
    }
    /**
     * Checks if a given site is already present in the bookmarks list.
     * @param site The Site object to check.
     * @return true if the site is already bookmarked, false otherwise.
     */
    public boolean isSiteBookmarked(Site site) {
        return favorites.contains(site);
    }
    /**
     * Returns an unmodifiable list of the currently bookmarked sites.
     * @return A List of Site objects that are bookmarked.
     */
    public List<Site> getBookmarks() {
        return Collections.unmodifiableList(favorites);
    }
    /**
     * Custom exception for simulating server connection issues.
     */
    public static class ConnectionToServerException extends Exception {
        /**
         * Constructs a new ConnectionToServerException with the specified detail message.
         * @param message The detail message (which is saved for later retrieval by the getMessage() method).
         */
        public ConnectionToServerException(String message) {
            super(message);
        }
    }
}