/**
 * Singleton database simulation for storing Cultural Heritage objects
 * Provides thread-safe operations and duplicate prevention
 */
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class CulturalHeritageDatabase {
    private static CulturalHeritageDatabase instance;
    private final Map<String, CulturalHeritage> heritageMap;
    private static final String DATA_FILE = "cultural_heritage_data.ser";
    private CulturalHeritageDatabase() {
        heritageMap = new ConcurrentHashMap<>();
        loadData();
    }
    public static synchronized CulturalHeritageDatabase getInstance() {
        if (instance == null) {
            instance = new CulturalHeritageDatabase();
        }
        return instance;
    }
    /**
     * Inserts a new cultural heritage object into the database
     * @param heritage The cultural heritage object to insert
     * @return true if insertion successful, false if duplicate exists
     * @throws IOException if there's an error saving to file
     */
    public boolean insertCulturalHeritage(CulturalHeritage heritage) throws IOException {
        // Quality requirement: System will not accept duplicates
        if (heritageMap.containsKey(heritage.getId())) {
            return false; // Duplicate detected
        }
        heritageMap.put(heritage.getId(), heritage);
        saveData();
        return true;
    }
    /**
     * Loads cultural heritage data from persistent storage
     */
    @SuppressWarnings("unchecked")
    private void loadData() {
        File dataFile = new File(DATA_FILE);
        if (!dataFile.exists()) {
            return; // Silently return if no file exists
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                Map<String, CulturalHeritage> loadedMap = (Map<String, CulturalHeritage>) obj;
                heritageMap.clear();
                heritageMap.putAll(loadedMap);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Warning: Could not load existing data: " + e.getMessage());
            // Continue with empty database
        }
    }
    /**
     * Saves cultural heritage data to persistent storage
     * @throws IOException if there's an error saving to file
     */
    private void saveData() throws IOException {
        // Simulate server connection - in real app, this would be remote
        if (!isServerConnected()) {
            throw new IOException("Connection to server ETOUR interrupted");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            // Create a copy to avoid concurrent modification issues
            Map<String, CulturalHeritage> copy = new HashMap<>(heritageMap);
            oos.writeObject(copy);
        }
    }
    /**
     * Checks if all cultural heritage records are valid
     * @return true if all records are valid
     */
    public boolean validateAllRecords() {
        for (CulturalHeritage heritage : heritageMap.values()) {
            try {
                // Revalidate each record
                new CulturalHeritage(
                    heritage.getId(),
                    heritage.getName(),
                    heritage.getType(),
                    heritage.getLocation(),
                    heritage.getYear(),
                    heritage.getDescription()
                );
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    /**
     * Simulates server connection check
     * @return true if server is connected
     */
    private boolean isServerConnected() {
        // In a real application, this would check actual server connectivity
        Random random = new Random();
        // Simulate 10% chance of connection failure
        return random.nextInt(100) >= 10;
    }
    /**
     * Gets all cultural heritage records
     * @return List of all cultural heritage objects
     */
    public List<CulturalHeritage> getAllCulturalHeritage() {
        return new ArrayList<>(heritageMap.values());
    }
    /**
     * Gets the number of cultural heritage records
     * @return count of records
     */
    public int getRecordCount() {
        return heritageMap.size();
    }
    /**
     * Clears all cultural heritage records (for testing purposes)
     */
    public void clearAllRecords() {
        heritageMap.clear();
        try {
            saveData();
        } catch (IOException e) {
            System.err.println("Error clearing records: " + e.getMessage());
        }
    }
}