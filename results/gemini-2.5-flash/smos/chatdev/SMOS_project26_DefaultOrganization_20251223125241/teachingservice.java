/**
 * Manages a collection of Teaching objects, simulating a backend service
 * or database. It provides methods to retrieve and delete teachings.
 * It also simulates an SMOS server disconnection when a teaching is deleted.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
public class TeachingService {
    // Stores teachings, mapping ID to Teaching object for quick lookup
    private ConcurrentHashMap<String, Teaching> teachings;
    /**
     * Constructs a new TeachingService and initializes it with some sample data.
     */
    public TeachingService() {
        teachings = new ConcurrentHashMap<>();
        initTeachings();
    }
    /**
     * Initializes the service with a set of predefined teaching examples.
     */
    private void initTeachings() {
        teachings.put("T001", new Teaching("T001", "Introduction to Programming", "Fundamentals of Java and Python. This teaching covers basic concepts like variables, data types, control structures, and object-oriented programming ideas."));
        teachings.put("T002", new Teaching("T002", "Data Structures & Algorithms", "Advanced topics in data organization and algorithm design. Includes arrays, linked lists, trees, graphs, sorting, and searching algorithms."));
        teachings.put("T003", new Teaching("T003", "Database Management", "SQL, NoSQL, and database system principles. Learn about relational databases, query languages, and data modeling concepts."));
        teachings.put("T004", new Teaching("T004", "Web Development Basics", "HTML, CSS, JavaScript for dynamic web pages. Covers front-end development, responsive design, and basic server-side interactions."));
        teachings.put("T005", new Teaching("T005", "Operating Systems Fundamentals", "Explore the core concepts of operating systems, including process management, memory management, file systems, and concurrency."));
    }
    /**
     * Retrieves a list of all available teachings.
     * @return A list of Teaching objects.
     */
    public List<Teaching> getAllTeachings() {
        // Return a new ArrayList to prevent external modifications to the internal map
        return new ArrayList<>(teachings.values());
    }
    /**
     * Retrieves a specific teaching by its ID.
     * @param id The ID of the teaching to retrieve.
     * @return The Teaching object if found, otherwise null.
     */
    public Teaching getTeachingById(String id) {
        return teachings.get(id);
    }
    /**
     * Deletes a teaching from the archive based on its ID.
     * After deletion, it simulates an interruption to the SMOS server.
     * @param id The ID of the teaching to delete.
     * @return true if the teaching was successfully deleted, false otherwise.
     */
    public boolean deleteTeaching(String id) {
        if (teachings.containsKey(id)) {
            teachings.remove(id);
            simulateSMOSDisconnect(); // Simulate SMOS server interruption as per postcondition
            return true;
        }
        return false;
    }
    /**
     * Simulates the interruption of the connection to the SMOS server.
     * In a real application, this might involve logging, closing network connections, etc.
     * For this simulation, it just prints a message to the console.
     */
    private void simulateSMOSDisconnect() {
        System.out.println("ALERT: Connection to the SMOS server interrupted due to teaching deletion.");
        // In a real scenario, more complex logic for server disconnection would go here.
        // e.g., closing a database connection, invalidating a session, etc.
    }
}