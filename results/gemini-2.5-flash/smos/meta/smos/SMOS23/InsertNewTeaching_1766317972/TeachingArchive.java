import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Manages the storage and retrieval of Teaching objects.
 * This class acts as an in-memory archive for teachings.
 * It ensures that teaching names are unique.
 */
public class TeachingArchive {
    // Using a Set to automatically handle uniqueness of Teaching objects based on their name
    // (as defined by Teaching's equals and hashCode methods).
    private final Set<Teaching> teachings;

    /**
     * Constructs a new TeachingArchive.
     */
    public TeachingArchive() {
        this.teachings = new HashSet<>();
    }

    /**
     * Adds a new teaching to the archive.
     * Before adding, it checks if a teaching with the same name already exists.
     *
     * @param teaching The Teaching object to add.
     * @return true if the teaching was added successfully (i.e., no teaching with the same name existed),
     *         false otherwise.
     * @throws IllegalArgumentException if the provided teaching object is null.
     */
    public boolean addTeaching(Teaching teaching) {
        if (teaching == null) {
            throw new IllegalArgumentException("Teaching cannot be null.");
        }
        // HashSet's add method returns false if the element already exists (based on equals/hashCode).
        return teachings.add(teaching);
    }

    /**
     * Retrieves a teaching from the archive by its name.
     *
     * @param name The name of the teaching to retrieve.
     * @return An Optional containing the Teaching object if found, or an empty Optional if not found.
     * @throws IllegalArgumentException if the provided name is null or empty.
     */
    public Optional<Teaching> getTeaching(String name) {
        if (!TeachingValidator.isValidTeachingName(name)) {
            throw new IllegalArgumentException("Teaching name cannot be null or empty for retrieval.");
        }
        // Iterate through the set to find a teaching with the matching name.
        // This is less efficient than a Map, but sufficient for a small archive simulation.
        for (Teaching t : teachings) {
            if (t.getName().equalsIgnoreCase(name.trim())) {
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns an unmodifiable set of all teachings currently in the archive.
     *
     * @return A Set of Teaching objects.
     */
    public Set<Teaching> getAllTeachings() {
        return Collections.unmodifiableSet(teachings);
    }

    /**
     * Checks if a teaching with the given name already exists in the archive.
     *
     * @param name The name of the teaching to check.
     * @return true if a teaching with the given name exists, false otherwise.
     * @throws IllegalArgumentException if the provided name is null or empty.
     */
    public boolean containsTeachingName(String name) {
        if (!TeachingValidator.isValidTeachingName(name)) {
            throw new IllegalArgumentException("Teaching name cannot be null or empty for checking existence.");
        }
        // Create a temporary Teaching object to leverage the Set's contains method,
        // which uses the equals and hashCode methods of Teaching.
        // This assumes that the Teaching constructor handles null/empty names correctly,
        // which TeachingValidator.isValidTeachingName already checks.
        try {
            return teachings.contains(new Teaching(name));
        } catch (IllegalArgumentException e) {
            // This catch block should ideally not be reached if isValidTeachingName is true.
            // It's a safeguard.
            return false;
        }
    }

    /**
     * Clears all teachings from the archive.
     * This method is primarily for testing or resetting the archive state.
     */
    public void clearArchive() {
        this.teachings.clear();
    }
}