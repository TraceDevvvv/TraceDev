'''
Data Access Object for refreshment points.
Simulates database operations for the purpose of this demo.
In a real application, this would connect to an actual database.
'''
import java.util.ArrayList;
import java.util.List;
public class RefreshmentPointDAO {
    private List<RefreshmentPoint> refreshmentPoints;
    private static int nextId = 1;
    /**
     * Constructor that initializes with some sample data.
     */
    public RefreshmentPointDAO() {
        refreshmentPoints = new ArrayList<>();
        // Add sample refreshment points for demonstration
        refreshmentPoints.add(new RefreshmentPoint(nextId++, "City Cafe", "Main Street 123", "Cafe", 
                "Coffee and light snacks"));
        refreshmentPoints.add(new RefreshmentPoint(nextId++, "Museum Restaurant", "Cultural District 45", "Restaurant", 
                "Fine dining near cultural heritage sites"));
        refreshmentPoints.add(new RefreshmentPoint(nextId++, "Park Refreshment Stand", "Central Park", "Kiosk", 
                "Quick refreshments in the park"));
        refreshmentPoints.add(new RefreshmentPoint(nextId++, "Heritage Snack Bar", "Old Town Square", "Snack Bar", 
                "Traditional snacks near heritage sites"));
        refreshmentPoints.add(new RefreshmentPoint(nextId++, "Tourist Center Cafe", "Tourist Info Center", "Cafe", 
                "Information center with refreshments"));
    }
    /**
     * Retrieve all refreshment points (simulating SearchCulturalHeritage use case)
     * 
     * @return List of all refreshment points
     */
    public List<RefreshmentPoint> getAllRefreshmentPoints() {
        return new ArrayList<>(refreshmentPoints); // Return copy to prevent external modification
    }
    /**
     * Delete a refreshment point by its ID.
     * 
     * @param id The ID of the refreshment point to delete
     * @return true if deletion was successful, false if point not found
     */
    public boolean deleteRefreshmentPoint(int id) {
        for (int i = 0; i < refreshmentPoints.size(); i++) {
            if (refreshmentPoints.get(i).getId() == id) {
                refreshmentPoints.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Find a refreshment point by its ID.
     * 
     * @param id The ID to search for
     * @return The refreshment point or null if not found
     */
    public RefreshmentPoint getRefreshmentPointById(int id) {
        for (RefreshmentPoint point : refreshmentPoints) {
            if (point.getId() == id) {
                return point;
            }
        }
        return null;
    }
    /**
     * Simulate server connection check for ETOUR interruption scenario.
     * 
     * @return true if connection is available, false for interruption
     */
    public boolean checkServerConnection() {
        // In real application, this would check actual server connection
        // For demo, we'll simulate 90% success rate
        return Math.random() > 0.1;
    }
}