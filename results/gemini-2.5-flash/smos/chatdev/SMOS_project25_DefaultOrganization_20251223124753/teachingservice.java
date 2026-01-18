/**
 * Provides service functionalities for managing Teaching objects,
 * simulating a data archive (in-memory storage).
 * Implements a Singleton pattern to ensure a single instance.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
public class TeachingService {
    private static TeachingService instance;
    private List<Teaching> teachings;
    /**
     * Private constructor to enforce Singleton pattern.
     * Initializes with some dummy data.
     */
    private TeachingService() {
        teachings = new ArrayList<>();
        // Add some initial dummy data
        teachings.add(new Teaching(UUID.randomUUID().toString(), "Object-Oriented Programming", "Fundamentals of OOP using Java.", "Dr. Jane Doe", 6));
        teachings.add(new Teaching(UUID.randomUUID().toString(), "Data Structures and Algorithms", "Advanced data structures and algorithmic efficiency.", "Prof. John Smith", 5));
        teachings.add(new Teaching(UUID.randomUUID().toString(), "Web Development Basics", "Introduction to HTML, CSS, JavaScript, and backend frameworks.", "Ms. Emily White", 4));
    }
    /**
     * Returns the singleton instance of TeachingService.
     * @return The single instance of TeachingService.
     */
    public static synchronized TeachingService getInstance() {
        if (instance == null) {
            instance = new TeachingService();
        }
        return instance;
    }
    /**
     * Adds a new teaching to the archive.
     * @param teaching The Teaching object to add.
     * @return An OperationResult indicating success or failure and a corresponding message.
     */
    public OperationResult addTeaching(Teaching teaching) {
        // Simulate potential connection interruption for consistency, though not explicitly required
        // for add in the use case description, it adds robustness.
        if (Math.random() < 0.1) { // 10% chance of simulated interruption for demonstration
             System.err.println("Simulated: Connection to the SMOS server interrupted during add. Add failed for teaching name: " + teaching.getName());
             return new OperationResult(false, "Simulated: Connection to the SMOS server interrupted during add.");
        }
        teachings.add(teaching);
        return new OperationResult(true, "Teaching '" + teaching.getName() + "' added successfully.");
    }
    /**
     * Retrieves a teaching by its unique ID.
     * @param id The ID of the teaching to retrieve.
     * @return The Teaching object if found, null otherwise.
     */
    public Teaching getTeachingById(String id) {
        return teachings.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    /**
     * Updates an existing teaching in the archive.
     * Finds the teaching by ID and replaces its details with the provided updatedTeaching object's details.
     * @param updatedTeaching The Teaching object containing the updated information (ID must match an existing teaching).
     * @return An OperationResult indicating success or failure and a corresponding message.
     */
    public OperationResult updateTeaching(Teaching updatedTeaching) {
        // Simulate potential connection interruption as per use case "Connection to the SMOS server interrupted"
        if (Math.random() < 0.2) { // 20% chance of simulated interruption for demonstration
            System.err.println("Simulated: Connection to the SMOS server interrupted during update. Update failed for teaching ID: " + updatedTeaching.getId());
            return new OperationResult(false, "Simulated: Connection to the SMOS server interrupted during update.");
        }
        for (int i = 0; i < teachings.size(); i++) {
            if (teachings.get(i).getId().equals(updatedTeaching.getId())) {
                teachings.set(i, updatedTeaching); // Replace the old object with the updated one
                return new OperationResult(true, "Teaching '" + updatedTeaching.getName() + "' updated successfully.");
            }
        }
        return new OperationResult(false, "Teaching with ID " + updatedTeaching.getId() + " not found for update.");
    }
    /**
     * Deletes a teaching by its unique ID.
     * @param id The ID of the teaching to delete.
     * @return True if the teaching was found and deleted, false otherwise.
     */
    public boolean deleteTeaching(String id) {
        boolean removed = teachings.removeIf(t -> t.getId().equals(id));
        if (removed) {
            System.out.println("Teaching deleted with ID: " + id);
        }
        return removed;
    }
    /**
     * @return A list of all teachings currently in the archive.
     */
    public List<Teaching> getAllTeachings() {
        // Return a new ArrayList to prevent external modifications to the original list
        return new ArrayList<>(teachings);
    }
}