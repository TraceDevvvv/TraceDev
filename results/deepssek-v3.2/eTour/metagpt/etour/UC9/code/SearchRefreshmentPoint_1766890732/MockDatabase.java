import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Mock database class providing sample points of rest for testing.
 * Simulates a data source that could be connected to ETOUR server.
 * In a real application, this would connect to a database or remote service.
 */
public class MockDatabase {
    
    /**
     * Static list of sample points of rest for demonstration.
     * Represents the data that would be retrieved from the ETOUR server.
     */
    private static final List<PointOfRest> SAMPLE_POINTS = createSamplePoints();
    
    /**
     * Creates and returns a list of sample PointOfRest objects.
     * 
     * @return list of sample points
     */
    private static List<PointOfRest> createSamplePoints() {
        List<PointOfRest> points = new ArrayList<>();
        
        // Create 10 sample points with different attributes
        points.add(new PointOfRest(
            1, 
            "City Center Cafe", 
            "123 Main St, City Center", 
            Arrays.asList("WiFi", "Restroom", "Coffee", "Snacks"), 
            4.5, 
            true
        ));
        
        points.add(new PointOfRest(
            2, 
            "Park Rest Area", 
            "Central Park, City Center", 
            Arrays.asList("Restroom", "Picnic Tables", "Water Fountain"), 
            4.2, 
            true
        ));
        
        points.add(new PointOfRest(
            3, 
            "Mall Food Court", 
            "456 Shopping Ave, Downtown", 
            Arrays.asList("WiFi", "Restroom", "Food Court", "Seating"), 
            3.8, 
            true
        ));
        
        points.add(new PointOfRest(
            4, 
            "Highway Rest Stop", 
            "Highway 101, Milepost 45", 
            Arrays.asList("Restroom", "Vending Machines", "Gas Station"), 
            3.5, 
            true
        ));
        
        points.add(new PointOfRest(
            5, 
            "Mountain View Lodge", 
            "Mountain Pass Rd, Rural Area", 
            Arrays.asList("Restroom", "Lodging", "Restaurant"), 
            4.7, 
            true
        ));
        
        points.add(new PointOfRest(
            6, 
            "24/7 Convenience Store", 
            "789 Night St, City Center", 
            Arrays.asList("Snacks", "Drinks", "ATM"), 
            3.0, 
            true
        ));
        
        points.add(new PointOfRest(
            7, 
            "Beachside Pavilion", 
            "Ocean Blvd, Beach Area", 
            Arrays.asList("Restroom", "Showers", "Picnic Tables"), 
            4.0, 
            false  // Closed for renovation
        ));
        
        points.add(new PointOfRest(
            8, 
            "Business Center Lounge", 
            "100 Corporate Dr, Business District", 
            Arrays.asList("WiFi", "Conference Rooms", "Coffee", "Printing"), 
            4.3, 
            true
        ));
        
        points.add(new PointOfRest(
            9, 
            "Train Station Cafe", 
            "Central Station, Railway Area", 
            Arrays.asList("WiFi", "Restroom", "Coffee", "Magazines"), 
            3.9, 
            true
        ));
        
        points.add(new PointOfRest(
            10, 
            "University Food Hall", 
            "Campus Blvd, University District", 
            Arrays.asList("WiFi", "Restroom", "Multiple Cuisines", "Study Areas"), 
            4.1, 
            true
        ));
        
        return points;
    }
    
    /**
     * Returns all sample points of rest.
     * Simulates a database query that retrieves all points.
     * 
     * @return list of all sample points
     */
    public static List<PointOfRest> getAllPoints() {
        return new ArrayList<>(SAMPLE_POINTS); // Return a copy to prevent modification
    }
    
    /**
     * Simulates a connection to the ETOUR server to fetch points.
     * This method might throw an exception to simulate connection interruption.
     * 
     * @return list of points from the "server"
     * @throws RuntimeException if connection to ETOUR server fails
     */
    public static List<PointOfRest> fetchFromEtourServer() throws RuntimeException {
        // Simulate occasional connection failure (10% chance)
        if (Math.random() < 0.1) {
            throw new RuntimeException("Connection to ETOUR server interrupted: Network timeout");
        }
        
        // Simulate network delay (0-3 seconds)
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Connection to ETOUR server interrupted: Thread interrupted");
        }
        
        return getAllPoints();
    }
    
    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private MockDatabase() {
        throw new UnsupportedOperationException("MockDatabase is a utility class and cannot be instantiated");
    }
}