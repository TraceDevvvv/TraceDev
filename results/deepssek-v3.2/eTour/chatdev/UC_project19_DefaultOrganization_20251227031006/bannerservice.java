/*
 * BannerService.java
 * 
 * Service class that handles all banner data operations including:
 * - Data persistence to file storage
 * - Server connection simulation for ETOUR server
 * - CRUD operations for banners and refreshment points
 * - Connection retry logic as specified in the use case
 * 
 * This class replaces the in-memory arrays with persistent storage
 * to ensure data persists between application sessions.
 */
import java.io.*;
import java.util.*;
import java.nio.file.*;
public class BannerService {
    private static final String DATA_FILE = "banners.dat";
    private Map<String, List<String>> banners;
    private boolean serverConnected;
    /**
     * Constructor that initializes the service by loading existing data
     * or creating default data if no data file exists.
     */
    public BannerService() {
        banners = new HashMap<>();
        serverConnected = true;
        loadBanners();
    }
    /**
     * Loads banner data from the persistence file.
     * If no file exists, initializes with default data and saves it.
     */
    private void loadBanners() {
        try {
            if (Files.exists(Paths.get(DATA_FILE))) {
                // Load from file using object serialization
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                    banners = (Map<String, List<String>>) ois.readObject();
                    System.out.println("Banner data loaded from file.");
                }
            } else {
                // Initialize with default data
                initializeDefaultBanners();
                saveBanners();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading banner data: " + e.getMessage());
            // Initialize with default data as fallback
            initializeDefaultBanners();
            try {
                saveBanners();
            } catch (Exception ex) {
                System.err.println("Failed to save default data: " + ex.getMessage());
            }
        }
    }
    /**
     * Initializes the banners map with default data for demonstration.
     */
    private void initializeDefaultBanners() {
        banners.clear();
        banners.put("Point A", new ArrayList<>(Arrays.asList("Banner 1 at Point A", "Banner 2 at Point A")));
        banners.put("Point B", new ArrayList<>(Arrays.asList("Banner 1 at Point B", "Banner 2 at Point B", "Banner 3 at Point B")));
        banners.put("Point C", new ArrayList<>(Arrays.asList("Banner 1 at Point C")));
        System.out.println("Initialized with default banner data.");
    }
    /**
     * Saves the current banner data to the persistence file.
     */
    private void saveBanners() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(banners);
            oos.flush();
            System.out.println("Banner data saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving banner data: " + e.getMessage());
            throw new RuntimeException("Failed to save banner data", e);
        }
    }
    /**
     * Returns an array of all refreshment point names.
     * @return String array of refreshment point names
     */
    public String[] getRefreshmentPoints() {
        return banners.keySet().toArray(new String[0]);
    }
    /**
     * Returns the list of banners for a specific refreshment point.
     * @param point The refreshment point name
     * @return List of banner strings for the point
     */
    public List<String> getBannersForPoint(String point) {
        if (!banners.containsKey(point)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(banners.get(point));
    }
    /**
     * Deletes a specific banner from a refreshment point.
     * @param point The refreshment point name
     * @param banner The banner text to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteBanner(String point, String banner) {
        // Check if the point exists
        if (!banners.containsKey(point)) {
            return false;
        }
        List<String> pointBanners = banners.get(point);
        boolean removed = pointBanners.remove(banner);
        // If the banner was successfully removed, save changes
        if (removed) {
            // If this was the last banner, remove the point entirely
            if (pointBanners.isEmpty()) {
                banners.remove(point);
            }
            saveBanners(); // Persist the changes
        }
        return removed;
    }
    /**
     * Simulates checking server connection status to ETOUR server.
     * In a real application, this would make an actual network call.
     * @return true if server is reachable, false otherwise
     */
    public boolean checkServerConnection() {
        // Simulate occasional connection failures (20% chance for realism)
        serverConnected = Math.random() > 0.2;
        return serverConnected;
    }
    /**
     * Returns the current server connection status.
     * @return true if server is connected, false otherwise
     */
    public boolean isServerConnected() {
        return serverConnected;
    }
    /**
     * Attempts to establish connection to ETOUR server with retry logic.
     * Handles the "Interruption of the connection to the server ETOUR" requirement.
     * @param maxRetries maximum number of retry attempts
     * @return true if connection successful, false otherwise
     */
    public boolean connectToServer(int maxRetries) {
        for (int i = 0; i < maxRetries; i++) {
            if (checkServerConnection()) {
                System.out.println("Connected to ETOUR server successfully.");
                return true;
            }
            System.out.println("Connection attempt " + (i + 1) + " failed. Retrying...");
            try {
                Thread.sleep(1000); // Wait 1 second before retry
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Connection attempt interrupted.");
                return false;
            }
        }
        System.out.println("All connection attempts failed.");
        return false;
    }
    /**
     * Adds a new refreshment point with no banners (for future extension).
     * @param pointName The name of the new refreshment point
     * @return true if point was added, false if it already exists
     */
    public boolean addRefreshmentPoint(String pointName) {
        if (banners.containsKey(pointName)) {
            return false;
        }
        banners.put(pointName, new ArrayList<>());
        saveBanners();
        return true;
    }
    /**
     * Adds a banner to a refreshment point (for future extension).
     * @param point The refreshment point name
     * @param banner The banner text to add
     * @return true if banner was added, false otherwise
     */
    public boolean addBanner(String point, String banner) {
        if (!banners.containsKey(point)) {
            return false;
        }
        List<String> pointBanners = banners.get(point);
        pointBanners.add(banner);
        saveBanners();
        return true;
    }
}