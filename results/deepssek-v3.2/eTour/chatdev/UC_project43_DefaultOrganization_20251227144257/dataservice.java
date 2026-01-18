"""
Service class for data operations
Simulates server connection and data persistence
"""
public class DataService {
    private boolean connected = true;
    private RefreshmentPoint currentPoint;
    public DataService() {
        // Initialize with sample data
        initializeSampleData();
    }
    private void initializeSampleData() {
        // Create sample refreshment point
        String[] sampleRefreshments = {
            "Coffee",
            "Tea",
            "Sandwiches",
            "Pastries",
            "Soft Drinks",
            "Water"
        };
        currentPoint = new RefreshmentPoint(
            1,
            "Main Cafeteria",
            "Building A, Floor 1",
            50,
            sampleRefreshments
        );
    }
    /**
     * Gets the current refreshment point data
     * Simulates fetching from database
     */
    public RefreshmentPoint getCurrentRefreshmentPoint() {
        // In real application, this would fetch from database
        return currentPoint;
    }
    /**
     * Gets refreshment point data from server
     * Simulates uploading data
     */
    public RefreshmentPoint getRefreshmentPointData() {
        // Check server connection
        if (!isConnected()) {
            throw new RuntimeException("Connection to server ETOUR interrupted");
        }
        // Simulate network delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return currentPoint;
    }
    /**
     * Saves the modified refreshment point data
     * Simulates storing to database
     */
    public boolean saveRefreshmentPoint(RefreshmentPoint point) {
        // Check server connection
        if (!isConnected()) {
            throw new RuntimeException("Connection to server ETOUR interrupted");
        }
        // Validate point is not null
        if (point == null) {
            return false;
        }
        // Simulate network delay and save operation
        try {
            Thread.sleep(1000);
            // In real application, this would save to database
            currentPoint = point;
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    /**
     * Checks if connected to server
     */
    public boolean isConnected() {
        // Simulate occasional connection issues
        if (Math.random() < 0.1) { // 10% chance of connection failure
            connected = false;
        }
        return connected;
    }
    /**
     * Simulate reconnection to server
     */
    public void reconnect() {
        connected = true;
    }
}