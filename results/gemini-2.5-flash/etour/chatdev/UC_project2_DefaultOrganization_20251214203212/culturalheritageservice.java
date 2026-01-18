/**
 * This class acts as a service layer for managing CulturalHeritage objects.
 * It handles the business logic such as adding new objects and checking for duplicates.
 * In a real-world application, this would interact with a database or a remote API.
 * For this example, it stores data in an in-memory Set to simulate storage and uniqueness.
 * It also includes a simulated network error for the ETOUR exit condition.
 */
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
public class CulturalHeritageService {
    // Using a Set to automatically handle uniqueness based on CulturalHeritage's equals/hashCode.
    private final Set<CulturalHeritage> culturalHeritageObjects;
    /**
     * Constructor for CulturalHeritageService.
     * Initializes the in-memory storage for cultural heritage objects.
     */
    public CulturalHeritageService() {
        culturalHeritageObjects = new HashSet<>();
        // Add some dummy data for testing purposes
        culturalHeritageObjects.add(new CulturalHeritage("Eiffel Tower", "Iconic iron lattice tower", "Paris, France"));
        culturalHeritageObjects.add(new CulturalHeritage("Great Wall of China", "Series of fortifications", "Northern China"));
    }
    /**
     * Adds a new cultural heritage object to the system.
     * It checks for duplicates and simulates network errors.
     *
     * @param culturalHeritage The CulturalHeritage object to be added.
     * @return true if the object was successfully added, false if it's a duplicate.
     * @throws IllegalArgumentException if the culturalHeritage object itself is null or invalid data.
     * @throws NetworkErrorException if a simulated network error occurs (e.g., name "ETOUR").
     */
    public boolean addCulturalHeritage(CulturalHeritage culturalHeritage) throws NetworkErrorException {
        if (culturalHeritage == null) {
            throw new IllegalArgumentException("Cultural heritage object cannot be null.");
        }
        // Simulate network error if the name contains "ETOUR" for the use case's exit condition.
        if (culturalHeritage.getName().toUpperCase().contains("ETOUR")) {
            simulateNetworkError(); // This will throw NetworkErrorException
        }
        // Check for duplicates based on the 'name' attribute.
        if (isDuplicate(culturalHeritage)) {
            return false; // Object with the same name already exists.
        }
        // Add the new cultural heritage object to the collection.
        culturalHeritageObjects.add(culturalHeritage);
        System.out.println("Added new cultural heritage: " + culturalHeritage.getName());
        return true;
    }
    /**
     * Checks if a cultural heritage object with the same 'name' already exists in the system.
     *
     * @param culturalHeritage The object to check for duplication.
     * @return true if a duplicate exists, false otherwise.
     */
    private boolean isDuplicate(CulturalHeritage culturalHeritage) {
        // The Set's add method implicitly checks for duplicates using equals() and hashCode().
        // However, this method explicitly checks existence.
        return culturalHeritageObjects.contains(culturalHeritage);
    }
    /**
     * Simulates a network connection interruption for the "ETOUR" exit condition.
     *
     * @throws NetworkErrorException always.
     */
    private void simulateNetworkError() throws NetworkErrorException {
        throw new NetworkErrorException("Connection to server ETOUR interrupted. Please try again later.");
    }
    /**
     * Returns an unmodifiable set of all cultural heritage objects currently stored.
     * This is useful for displaying all objects or for external inspection.
     * @return An unmodifiable Set of CulturalHeritage objects.
     */
    public Set<CulturalHeritage> getAllCulturalHeritages() {
        return Collections.unmodifiableSet(culturalHeritageObjects);
    }
}