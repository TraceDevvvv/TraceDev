import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Simulates a database connection for news operations.
 * This class handles connecting to a database, fetching news,
 * deleting news, and simulating server interruptions.
 */
public class DatabaseConnection {
    private boolean connected;
    private List<News> newsList;
    private Random random;

    /**
     * Constructor initializes the database connection simulation.
     * Creates some sample news data for demonstration.
     */
    public DatabaseConnection() {
        this.connected = false;
        this.newsList = new ArrayList<>();
        this.random = new Random();
        
        // Initialize with some sample news for demonstration
        initializeSampleData();
    }

    /**
     * Initializes the database with sample news data.
     * In a real application, this would come from an actual database.
     */
    private void initializeSampleData() {
        newsList.add(new News(1, "Java 21 Released", "Oracle has released Java 21 with new features.", LocalDateTime.now().minusDays(5)));
        newsList.add(new News(2, "AI Breakthrough", "Researchers achieve new milestone in AI reasoning.", LocalDateTime.now().minusDays(3)));
        newsList.add(new News(3, "Cybersecurity Alert", "New vulnerability discovered in popular web framework.", LocalDateTime.now().minusDays(1)));
        newsList.add(new News(4, "Tech Conference 2024", "Annual tech conference announces keynote speakers.", LocalDateTime.now().minusHours(12)));
        newsList.add(new News(5, "Open Source Project", "New open source project aims to simplify microserv.", LocalDateTime.now().minusHours(6)));
    }

    /**
     * Simulates connecting to the ETOUR server.
     * 
     * @return true if connection is successful, false otherwise
     * @throws ServerInterruptedException if server connection is interrupted
     */
    public boolean connect() throws ServerInterruptedException {
        // Simulate connection attempt with potential interruption
        if (simulateInterruption()) {
            throw new ServerInterruptedException("ETOUR server connection interrupted during connect attempt.");
        }
        
        connected = true;
        System.out.println("Connected to ETOUR server successfully.");
        return true;
    }

    /**
     * Disconnects from the database server.
     */
    public void disconnect() {
        if (connected) {
            connected = false;
            System.out.println("Disconnected from ETOUR server.");
        }
    }

    /**
     * Checks if the connection to the database is active.
     * 
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Fetches all news from the database.
     * 
     * @return List of all news
     * @throws ServerInterruptedException if server connection is interrupted
     * @throws IllegalStateException if not connected to database
     */
    public List<News> getAllNews() throws ServerInterruptedException {
        checkConnection();
        
        // Simulate potential server interruption during operation
        if (simulateInterruption()) {
            throw new ServerInterruptedException("ETOUR server connection interrupted while fetching news.");
        }
        
        return new ArrayList<>(newsList); // Return copy to prevent external modification
    }

    /**
     * Deletes a news item by its ID.
     * 
     * @param newsId the ID of the news to delete
     * @return true if deletion was successful, false if news not found
     * @throws ServerInterruptedException if server connection is interrupted
     * @throws IllegalStateException if not connected to database
     */
    public boolean deleteNews(int newsId) throws ServerInterruptedException {
        checkConnection();
        
        // Simulate potential server interruption during operation
        if (simulateInterruption()) {
            throw new ServerInterruptedException("ETOUR server connection interrupted during delete operation.");
        }
        
        for (int i = 0; i < newsList.size(); i++) {
            if (newsList.get(i).getId() == newsId) {
                newsList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a news item with the given ID exists.
     * 
     * @param newsId the ID to check
     * @return true if news exists, false otherwise
     * @throws ServerInterruptedException if server connection is interrupted
     * @throws IllegalStateException if not connected to database
     */
    public boolean newsExists(int newsId) throws ServerInterruptedException {
        checkConnection();
        
        // Simulate potential server interruption during operation
        if (simulateInterruption()) {
            throw new ServerInterruptedException("ETOUR server connection interrupted while checking news existence.");
        }
        
        for (News news : newsList) {
            if (news.getId() == newsId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates that the connection is active.
     * 
     * @throws IllegalStateException if not connected to database
     */
    private void checkConnection() {
        if (!connected) {
            throw new IllegalStateException("Not connected to database. Please connect first.");
        }
    }

    /**
     * Simulates random server interruption (10% chance).
     * This simulates the "Interruption of the connection to the server ETOUR" from the use case.
     * 
     * @return true if interruption occurs, false otherwise
     */
    private boolean simulateInterruption() {
        // 10% chance of server interruption for simulation purposes
        return random.nextInt(10) == 0;
    }

    /**
     * Custom exception for server interruptions.
     * Represents the "Interruption of the connection to the server ETOUR" scenario.
     */
    public static class ServerInterruptedException extends Exception {
        public ServerInterruptedException(String message) {
            super(message);
        }
    }
}