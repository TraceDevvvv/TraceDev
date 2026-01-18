'''
@file ETOURServerMock.java
@brief Simulates connection to ETOUR server and possible disconnection.
@details This class mocks server behavior including fetching bookmarks and handling disconnections.
@version 1.0
@date 2023-10-15
'''
package project;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class ETOURServerMock {
    private Random random;
    private boolean connectionActive;
    /**
     * Constructor to initialize the server mock.
     */
    public ETOURServerMock() {
        random = new Random();
        connectionActive = true;
    }
    /**
     * Simulates fetching bookmarks from server.
     * @param username The authenticated user
     * @return List of bookmark names
     * @throws ServerDisconnectedException if connection is interrupted
     */
    public List<String> fetchBookmarks(String username) throws ServerDisconnectedException {
        // Simulate random connection interruption (20% chance)
        if (!connectionActive || random.nextDouble() < 0.2) {
            connectionActive = false;
            throw new ServerDisconnectedException("Connection to ETOUR server interrupted.");
        }
        // Mock data based on username
        if (username.toLowerCase().contains("john")) {
            return Arrays.asList("Eiffel Tower", "Louvre Museum", "Seine River Cruise");
        } else if (username.toLowerCase().contains("mary")) {
            return Arrays.asList("Grand Canyon", "Yellowstone Park");
        } else {
            return Arrays.asList("Colosseum", "Venice Canals", "Roman Forum", "Vatican City");
        }
    }
    /**
     * Attempts to reconnect to the server.
     * @note This method blocks the current thread; it is intended to be called from a background thread.
     */
    public void reconnect() {
        try {
            Thread.sleep(500); // Simulate network delay (called from background)
            connectionActive = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }
    /**
     * Custom exception for server disconnection.
     */
    public static class ServerDisconnectedException extends Exception {
        /**
         * Constructor for ServerDisconnectedException.
         * @param message Error message
         */
        public ServerDisconnectedException(String message) {
            super(message);
        }
    }
}