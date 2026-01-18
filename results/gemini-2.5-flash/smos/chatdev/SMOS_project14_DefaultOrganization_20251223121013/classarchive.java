'''
Represents the archive or repository for SchoolClass objects.
This class is responsible for storing and managing a collection of classes.
For this example, it uses an in-memory ArrayList as its storage mechanism.
In a real-world application, this would typically interact with a database
or other persistent storage.
'''
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ClassArchive {
    // A list to store SchoolClass objects in memory
    private List<SchoolClass> classes;
    /**
     * Constructs a new ClassArchive, initializing its internal storage.
     */
    public ClassArchive() {
        this.classes = new ArrayList<>();
    }
    /**
     * Inserts a new SchoolClass object into the archive.
     * This method performs a basic check to ensure the class is not null
     * and then adds it to the internal list.
     * In a more complex system, this might involve database transactions,
     * checking for duplicates, or other business logic.
     *
     * @param newSchoolClass The SchoolClass object to be inserted.
     * @return true if the class was successfully inserted, false otherwise.
     */
    public boolean insertClass(SchoolClass newSchoolClass) {
        if (newSchoolClass == null) {
            System.err.println("Attempted to insert a null class object.");
            return false;
        }
        // Simulate a successful insertion
        classes.add(newSchoolClass);
        System.out.println("DEBUG: Class added to archive: " + newSchoolClass.getName());
        return true;
    }
    /**
     * Retrieves an immutable list of all classes currently stored in the archive.
     *
     * @return A List of SchoolClass objects, or an empty list if no classes are present.
     */
    public List<SchoolClass> getAllClasses() {
        // Return an unmodifiable list to prevent external modification of the archive's internal state
        return Collections.unmodifiableList(classes);
    }
    /**
     * (Optional) Prints all classes currently in the archive to the console.
     * Useful for debugging and verifying insertions.
     */
    public void printAllClasses() {
        if (classes.isEmpty()) {
            System.out.println("Archive is empty. No classes to display.");
            return;
        }
        System.out.println("--- Current Classes in Archive ---");
        for (SchoolClass c : classes) {
            System.out.println(c);
        }
        System.out.println("----------------------------------");
    }
}