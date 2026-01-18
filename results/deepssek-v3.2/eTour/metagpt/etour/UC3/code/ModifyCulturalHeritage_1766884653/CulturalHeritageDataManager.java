package workspace.ModifyCulturalHeritage_1766884653;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CulturalHeritageDataManager class simulates a data storage system for CulturalHeritage objects.
 * It provides CRUD operations (Create, Read, Update, Delete) and simulates database persistence.
 * This class includes thread-safe operations to handle concurrent access and simulates
 * server connection interruption scenarios.
 */
public class CulturalHeritageDataManager {
    // In-memory storage simulating a database
    private Map<String, CulturalHeritage> culturalHeritageMap;
    
    // Lock for thread-safe operations
    private final ReentrantLock lock;
    
    // Flag to simulate server connection status
    private boolean serverConnected;
    
    // Counter for simulating operation IDs
    private int operationCounter;
    
    /**
     * Constructor initializes the data manager with sample data
     */
    public CulturalHeritageDataManager() {
        this.culturalHeritageMap = new HashMap<>();
        this.lock = new ReentrantLock();
        this.serverConnected = true; // Start with server connected
        this.operationCounter = 0;
        
        // Initialize with sample data
        initializeSampleData();
    }
    
    /**
     * Initializes the data manager with sample cultural heritage items
     * This simulates pre-existing data in the system
     */
    private void initializeSampleData() {
        // Clear existing data
        culturalHeritageMap.clear();
        
        // Add sample cultural heritage items
        addCulturalHeritage(new CulturalHeritage(
            "CH001",
            "Great Wall of China",
            "An ancient series of walls and fortifications located in northern China.",
            "China",
            220, // 3rd century BC (approximate)
            "Monument",
            CulturalHeritage.STATUS_ACTIVE
        ));
        
        addCulturalHeritage(new CulturalHeritage(
            "CH002",
            "Taj Mahal",
            "An ivory-white marble mausoleum on the right bank of the Yamuna river.",
            "Agra, India",
            1648,
            "Mausoleum",
            CulturalHeritage.STATUS_ACTIVE
        ));
        
        addCulturalHeritage(new CulturalHeritage(
            "CH003",
            "Colosseum",
            "An oval amphitheatre in the centre of the city of Rome, Italy.",
            "Rome, Italy",
            80, // AD 80
            "Amphitheatre",
            CulturalHeritage.STATUS_ACTIVE
        ));
        
        addCulturalHeritage(new CulturalHeritage(
            "CH004",
            "Machu Picchu",
            "A 15th-century Inca citadel located in the Eastern Cordillera of southern Peru.",
            "Peru",
            1450,
            "Archaeological Site",
            CulturalHeritage.STATUS_PENDING
        ));
        
        addCulturalHeritage(new CulturalHeritage(
            "CH005",
            "Pyramids of Giza",
            "Ancient pyramid-shaped masonry structures located in Egypt.",
            "Giza, Egypt",
            -2560, // 2560 BC
            "Pyramid",
            CulturalHeritage.STATUS_ACTIVE
        ));
    }
    
    /**
     * Checks if the server is connected (simulated)
     * 
     * @return true if server is connected, false otherwise
     */
    public boolean isServerConnected() {
        return serverConnected;
    }
    
    /**
     * Sets the server connection status (simulated)
     * 
     * @param connected true to simulate server connection, false to simulate disconnection
     */
    public void setServerConnected(boolean connected) {
        this.serverConnected = connected;
    }
    
    /**
     * Simulates a server connection interruption
     * Randomly disconnects the server for testing purposes
     */
    public void simulateServerInterruption() {
        System.out.println("Simulating server connection interruption...");
        this.serverConnected = false;
    }
    
    /**
     * Restores server connection
     */
    public void restoreServerConnection() {
        System.out.println("Restoring server connection...");
        this.serverConnected = true;
    }
    
    /**
     * Gets all cultural heritage items in the system
     * 
     * @return List of all CulturalHeritage objects
     * @throws DataManagerException if server is not connected
     */
    public List<CulturalHeritage> getAllCulturalHeritage() throws DataManagerException {
        checkServerConnection();
        
        lock.lock();
        try {
            return new ArrayList<>(culturalHeritageMap.values());
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Finds a cultural heritage item by its ID
     * 
     * @param id The ID of the cultural heritage to find
     * @return The CulturalHeritage object, or null if not found
     * @throws DataManagerException if server is not connected
     */
    public CulturalHeritage getCulturalHeritageById(String id) throws DataManagerException {
        checkServerConnection();
        
        if (id == null || id.trim().isEmpty()) {
            throw new DataManagerException("ID cannot be null or empty");
        }
        
        lock.lock();
        try {
            return culturalHeritageMap.get(id);
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Adds a new cultural heritage item to the system
     * 
     * @param culturalHeritage The CulturalHeritage object to add
     * @return true if added successfully, false if item with same ID already exists
     * @throws DataManagerException if server is not connected or culturalHeritage is invalid
     */
    public boolean addCulturalHeritage(CulturalHeritage culturalHeritage) throws DataManagerException {
        checkServerConnection();
        
        if (culturalHeritage == null) {
            throw new DataManagerException("CulturalHeritage cannot be null");
        }
        
        if (!culturalHeritage.isValid()) {
            throw new DataManagerException("Invalid CulturalHeritage data: " + culturalHeritage.getValidationErrors());
        }
        
        lock.lock();
        try {
            String id = culturalHeritage.getId();
            if (culturalHeritageMap.containsKey(id)) {
                return false; // Item with same ID already exists
            }
            
            culturalHeritageMap.put(id, culturalHeritage);
            operationCounter++;
            System.out.println("Added cultural heritage with ID: " + id + " (Operation #" + operationCounter + ")");
            return true;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Updates an existing cultural heritage item
     * 
     * @param culturalHeritage The updated CulturalHeritage object
     * @return true if updated successfully, false if item not found
     * @throws DataManagerException if server is not connected or culturalHeritage is invalid
     */
    public boolean updateCulturalHeritage(CulturalHeritage culturalHeritage) throws DataManagerException {
        checkServerConnection();
        
        if (culturalHeritage == null) {
            throw new DataManagerException("CulturalHeritage cannot be null");
        }
        
        if (!culturalHeritage.isValid()) {
            throw new DataManagerException("Invalid CulturalHeritage data: " + culturalHeritage.getValidationErrors());
        }
        
        lock.lock();
        try {
            String id = culturalHeritage.getId();
            if (!culturalHeritageMap.containsKey(id)) {
                return false; // Item not found
            }
            
            CulturalHeritage oldItem = culturalHeritageMap.get(id);
            if (oldItem.equals(culturalHeritage)) {
                System.out.println("No changes detected for cultural heritage with ID: " + id);
                return true; // No actual changes, but operation is considered successful
            }
            
            culturalHeritageMap.put(id, culturalHeritage);
            operationCounter++;
            System.out.println("Updated cultural heritage with ID: " + id + " (Operation #" + operationCounter + ")");
            return true;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Deletes a cultural heritage item by ID
     * 
     * @param id The ID of the cultural heritage to delete
     * @return true if deleted successfully, false if item not found
     * @throws DataManagerException if server is not connected
     */
    public boolean deleteCulturalHeritage(String id) throws DataManagerException {
        checkServerConnection();
        
        if (id == null || id.trim().isEmpty()) {
            throw new DataManagerException("ID cannot be null or empty");
        }
        
        lock.lock();
        try {
            if (!culturalHeritageMap.containsKey(id)) {
                return false; // Item not found
            }
            
            culturalHeritageMap.remove(id);
            operationCounter++;
            System.out.println("Deleted cultural heritage with ID: " + id + " (Operation #" + operationCounter + ")");
            return true;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Searches for cultural heritage items by name (case-insensitive partial match)
     * 
     * @param namePart Part of the name to search for
     * @return List of matching CulturalHeritage objects
     * @throws DataManagerException if server is not connected
     */
    public List<CulturalHeritage> searchByName(String namePart) throws DataManagerException {
        checkServerConnection();
        
        if (namePart == null || namePart.trim().isEmpty()) {
            return getAllCulturalHeritage();
        }
        
        lock.lock();
        try {
            List<CulturalHeritage> results = new ArrayList<>();
            String searchTerm = namePart.toLowerCase().trim();
            
            for (CulturalHeritage item : culturalHeritageMap.values()) {
                if (item.getName().toLowerCase().contains(searchTerm)) {
                    results.add(item);
                }
            }
            
            return results;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Checks the server connection and throws an exception if not connected
     * 
     * @throws DataManagerException if server is not connected
     */
    private void checkServerConnection() throws DataManagerException {
        if (!serverConnected) {
            throw new DataManagerException("Server connection interrupted. Please try again later.");
        }
    }
    
    /**
     * Gets the count of cultural heritage items in the system
     * 
     * @return Number of cultural heritage items
     */
    public int getItemCount() {
        lock.lock();
        try {
            return culturalHeritageMap.size();
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Gets the next operation ID (for simulation purposes)
     * 
     * @return Next operation ID
     */
    public int getNextOperationId() {
        lock.lock();
        try {
            return operationCounter + 1;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * DataManagerException is a custom exception class for data management errors
     */
    public static class DataManagerException extends Exception {
        public DataManagerException(String message) {
            super(message);
        }
        
        public DataManagerException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * Prints a summary of all cultural heritage items in the system
     */
    public void printSummary() {
        lock.lock();
        try {
            System.out.println("\n=== Cultural Heritage Database Summary ===");
            System.out.println("Total items: " + culturalHeritageMap.size());
            System.out.println("Server connected: " + (serverConnected ? "Yes" : "No"));
            System.out.println("Total operations performed: " + operationCounter);
            System.out.println("Items:");
            
            for (CulturalHeritage item : culturalHeritageMap.values()) {
                System.out.println("  - " + item);
            }
            
            System.out.println("========================================\n");
        } finally {
            lock.unlock();
        }
    }
}