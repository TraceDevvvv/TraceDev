'''
Manages the collection of ClassModel objects, simulating database operations
like retrieving and deleting classes. It also simulates server interactions.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // For cleaner handling of a class that might not exist
public class ClassService {
    // In-memory list to simulate a database of classes
    private static List<ClassModel> classes = new ArrayList<>();
    // Static initializer block to populate with some initial data
    static {
        classes.add(new ClassModel("C001", "Mathematics 101", "Introduction to Calculus"));
        classes.add(new ClassModel("C002", "Physics 201", "Advanced Mechanics"));
        classes.add(new ClassModel("C003", "Computer Science 301", "Data Structures and Algorithms"));
        classes.add(new ClassModel("C004", "Biology 101", "Fundamentals of Life Science"));
    }
    /**
     * Retrieves all available classes from the archive.
     * @return A list of ClassModel objects.
     */
    public List<ClassModel> getAllClasses() {
        // Simulate a delay for network/database access
        try {
            Thread.sleep(500); // Increased delay for better SwingWorker demonstration
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Thread interrupted during getAllClasses simulation.");
        }
        // Return a new ArrayList to prevent external modifications to the original list
        return new ArrayList<>(classes);
    }
    /**
     * Deletes a class from the archive given its ID.
     * @param classId The ID of the class to be deleted.
     * @return true if the class was found and deleted, false otherwise.
     */
    public boolean deleteClass(String classId) {
        // Simulate a delay for network/database access
        try {
            Thread.sleep(1000); // Increased delay for better SwingWorker demonstration
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Thread interrupted during deleteClass simulation.");
            return false;
        }
        // Use Iterator to safely remove elements while iterating
        boolean removed = classes.removeIf(c -> c.getId().equals(classId));
        return removed;
    }
    /**
     * Finds a class by its ID.
     * @param classId The ID of the class to find.
     * @return An Optional containing the ClassModel if found, or an empty Optional otherwise.
     */
    public Optional<ClassModel> findClassById(String classId) {
        return classes.stream()
                      .filter(c -> c.getId().equals(classId))
                      .findFirst();
    }
    /**
     * Simulates the interruption of connection to the SMOS server.
     * As per postcondition: "Connection to the interrupted SMOS server".
     * In a real application, this might involve closing database connections,
     * releasing network resources, or logging the event.
     */
    public void simulateSMOSDisconnect() {
        System.out.println("SMOS Server: Connection interrupted after delete operation.");
        // In a real scenario, this might involve:
        // - Closing a database connection
        // - Invalidating a session token
        // - Logging the disconnection event
    }
}