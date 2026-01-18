import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;
/*
 * Manages the storage and business logic for teachings.
 * Simulates an archive where new teachings are inserted.
 */
public class TeachingArchive {
    // Enum to represent the outcome of adding a teaching.
    public enum AddTeachingResult {
        SUCCESS,
        INVALID_DATA,
        DUPLICATE_NAME,
        SMOS_CONNECTION_ERROR
    }
    private Set<String> teachings; // Using a Set to ensure unique teaching names
    private Random random;         // Used to simulate random SMOS connection errors
    /**
     * Constructs a new TeachingArchive instance.
     * Initializes the archive with some predefined teachings for demonstration.
     */
    public TeachingArchive() {
        this.teachings = new HashSet<>();
        this.random = new Random();
        // Pre-populate with some mock data, simulating existing teachings
        teachings.add("Mathematics");
        teachings.add("Physics");
        teachings.add("Chemistry");
    }
    /**
     * Attempts to add a new teaching to the archive.
     * Performs validation and simulates a connection check to an SMOS server.
     * @param teachingName The name of the teaching to add.
     * @return An AddTeachingResult enum indicating the outcome of the operation.
     */
    public AddTeachingResult addTeaching(String teachingName) {
        // 1. Data validity check (Precondition for 'Errodati' use case)
        if (!Teaching.isValidName(teachingName)) {
            return AddTeachingResult.INVALID_DATA;
        }
        String trimmedName = teachingName.trim();
        // Check for duplicate name
        if (teachings.contains(trimmedName)) {
            return AddTeachingResult.DUPLICATE_NAME;
        }
        // 2. Simulate SMOS server connection
        // Simulate a 20% chance of connection error
        if (random.nextInt(100) < 20) { // Changed condition for 20% chance of failure
            return AddTeachingResult.SMOS_CONNECTION_ERROR;
        }
        // If all checks pass, insert the new teaching
        teachings.add(trimmedName);
        return AddTeachingResult.SUCCESS;
    }
    /**
     * Retrieves a list of all teachings currently in the archive.
     * @return A List of teaching names.
     */
    public List<String> getTeachings() {
        return new ArrayList<>(teachings);
    }
}