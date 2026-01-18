'''
This class simulates a repository for cultural objects.
It provides a predefined set of cultural objects and a method to search/filter them.
It also includes a simulation for server connection issues using a custom exception.
'''
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
/**
 * Simulates a data repository for managing CulturalObject instances.
 * This class provides a predefined list of cultural objects and a method
 * to search them based on various criteria. It also includes a simulated
 * connection error mechanism using a custom exception.
 */
public class CulturalObjectRepository {
    private List<CulturalObject> culturalObjects;
    private static final Random RANDOM = new Random();
    private static final double CONNECTION_ERROR_CHANCE = 0.1; // 10% chance out of 1.0 for connection error
    /**
     * Constructor for CulturalObjectRepository.
     * Initializes the list of cultural objects with some sample data.
     */
    public CulturalObjectRepository() {
        // Initialize with sample cultural objects
        culturalObjects = new ArrayList<>(Arrays.asList(
                new CulturalObject("1001", "Mona Lisa", "Painting", "Louvre Museum, Paris", "A half-length portrait painting by Italian artist Leonardo da Vinci."),
                new CulturalObject("1002", "Colosseum", "Amphitheatre", "Rome, Italy", "An elliptical amphitheatre in the centre of the city of Rome, Italy."),
                new CulturalObject("1003", "Statue of David", "Sculpture", "Galleria dell'Accademia, Florence", "A masterpiece of Renaissance sculpture created in marble by Michelangelo."),
                new CulturalObject("1004", "The Starry Night", "Painting", "Museum of Modern Art, New York", "An oil-on-canvas painting by Dutch Post-Impressionist painter Vincent van Gogh."),
                new CulturalObject("1005", "Great Wall of China", "Fortification", "Northern China", "A series of fortifications built along the historical northern borders of ancient Chinese states."),
                new CulturalObject("1006", "Venus de Milo", "Sculpture", "Louvre Museum, Paris", "An ancient Greek statue and one of the most famous works of ancient Greek sculpture."),
                new CulturalObject("1007", "Eiffel Tower", "Monument", "Paris, France", "A wrought-iron lattice tower on the Champ de Mars in Paris, France.")
        ));
    }
    /**
     * Searches for cultural objects based on provided parameters.
     * All parameters are optional; if null or empty, they are ignored in the search.
     * The search is case-insensitive and checks for partial matches.
     *
     * @param name The name or part of the name to search for.
     * @param type The type or category to search for.
     * @param location The location or part of the location to search for.
     * @return A list of cultural objects that match the search criteria.
     * @throws EtourConnectionException if a simulated connection error occurs.
     */
    public List<CulturalObject> searchCulturalObjects(String name, String type, String location) throws EtourConnectionException {
        // Simulate potential server connection issues as per the use case exit condition.
        if (RANDOM.nextDouble() < CONNECTION_ERROR_CHANCE) {
            // Throw the custom exception to simulate a connection interruption.
            throw new EtourConnectionException("ETour Server Connection Interrupted!");
        }
        String finalName = (name != null) ? name.toLowerCase() : "";
        String finalType = (type != null) ? type.toLowerCase() : "";
        String finalLocation = (location != null) ? location.toLowerCase() : "";
        // Filter the cultural objects based on the provided criteria
        List<CulturalObject> results = culturalObjects.stream()
                .filter(obj -> finalName.isEmpty() || obj.getName().toLowerCase().contains(finalName))
                .filter(obj -> finalType.isEmpty() || obj.getType().toLowerCase().contains(finalType))
                .filter(obj -> finalLocation.isEmpty() || obj.getLocation().toLowerCase().contains(finalLocation))
                .collect(Collectors.toList());
        // Simulate a small delay for "The system requirements should return the results within a set time"
        // This is a placeholder for actual backend performance
        try {
            // Simulate 100-300ms delay to mimic network latency or database query time.
            Thread.sleep(100 + RANDOM.nextInt(200));
        } catch (InterruptedException e) {
            // Restore the interrupted status if the thread was interrupted during sleep.
            Thread.currentThread().interrupt();
            System.err.println("Search delay interrupted: " + e.getMessage());
            // It might be reasonable to re-throw as an unchecked exception or wrap in a custom checked exception
            // if an interruption should affect the search outcome or propagation.
        }
        return results;
    }
}