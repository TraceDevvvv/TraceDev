'''
ClassRegistry.java
Manages a collection of RegisterEntry objects for a specific class.
Simulates the data storage for a class register.
'''
import java.util.ArrayList;
import java.util.List;
public class ClassRegistry {
    private String className;
    private List<RegisterEntry> entries;
    /**
     * Constructor for a class registry.
     * @param className Name of the class.
     */
    public ClassRegistry(String className) {
        this.className = className;
        this.entries = new ArrayList<>();
        // Populate with sample data for demonstration
        initializeSampleData();
    }
    /**
     * Initializes the registry with sample data for demonstration.
     */
    private void initializeSampleData() {
        entries.add(new RegisterEntry("2023-10-01", "2", "None", "1", "Traffic"));
        entries.add(new RegisterEntry("2023-10-02", "0", "Disruptive behavior", "0", "N/A"));
        entries.add(new RegisterEntry("2023-10-03", "1", "None", "3", "Public transport delay"));
        entries.add(new RegisterEntry("2023-10-04", "0", "Late submission", "2", "Family emergency"));
        entries.add(new RegisterEntry("2023-10-05", "3", "None", "0", "Medical appointment"));
    }
    /**
     * Retrieves all register entries for this class.
     * @return List of RegisterEntry objects.
     */
    public List<RegisterEntry> getAllEntries() {
        return entries;
    }
    /**
     * Gets the class name.
     * @return Name of the class.
     */
    public String getClassName() {
        return className;
    }
}