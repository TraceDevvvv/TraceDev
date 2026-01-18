'''
Manages operations related to Points of Interest.
This class simulates fetching data from a backend system.
'''
package managers;
import models.PointOfInterest;
import java.util.ArrayList;
import java.util.List;
/**
 * Manages operations related to Points of Interest.
 * This class simulates fetching data from a backend system.
 */
public class PoIManager {
    private List<PointOfInterest> pointsOfInterest; // In-memory storage for PoIs
    /**
     * Constructs a new PoIManager and initializes with sample PointOfInterest data.
     * In a real application, this would fetch data from a database or a service.
     */
    public PoIManager() {
        // Simulate loading data from a database or service
        pointsOfInterest = new ArrayList<>();
        pointsOfInterest.add(new PointOfInterest(101, "Italian Restaurant Venice"));
        pointsOfInterest.add(new PointOfInterest(102, "Coffee Shop Downtown Hub"));
        pointsOfInterest.add(new PointOfInterest(103, "Museum of Modern Art City Center"));
        pointsOfInterest.add(new PointOfInterest(104, "Fashion Boutique Central"));
    }
    /**
     * Retrieves all available Points of Interest.
     * This method simulates a data retrieval operation.
     *
     * @return A list of PointOfInterest objects. Returns an empty list if none are found.
     */
    public List<PointOfInterest> getAllPointsOfInterest() {
        // Return a new ArrayList to prevent external modifications to the internal list.
        return new ArrayList<>(pointsOfInterest);
    }
}