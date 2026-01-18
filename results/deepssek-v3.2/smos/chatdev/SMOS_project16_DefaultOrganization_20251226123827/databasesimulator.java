'''
Simulates a database/archive for storing class information.
In a real application, this would connect to an actual database.
'''
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DatabaseSimulator {
    private Map<Integer, Class> classes;
    private int nextId;
    public DatabaseSimulator() {
        classes = new HashMap<>();
        nextId = 1;
    }
    /**
     * Initializes the database with sample data for testing.
     */
    public void initializeSampleData() {
        addClass(new Class("Mathematics 101", "Basic Mathematics", "Mr. Smith", "Room 101", 30));
        addClass(new Class("Physics 201", "Advanced Physics", "Dr. Johnson", "Room 202", 25));
        addClass(new Class("Chemistry 301", "Organic Chemistry", "Prof. Davis", "Lab 3", 20));
        addClass(new Class("Biology 401", "Molecular Biology", "Dr. Wilson", "Lab 4", 15));
    }
    /**
     * Adds a new class to the database.
     * @param cls The class to add
     * @return The ID assigned to the class
     */
    public int addClass(Class cls) {
        int id = nextId++;
        cls.setId(id);
        classes.put(id, cls);
        return id;
    }
    /**
     * Retrieves a class by its ID.
     * @param id The class ID
     * @return The class object or null if not found
     */
    public Class getClassById(int id) {
        return classes.get(id);
    }
    /**
     * Deletes a class from the database/archive.
     * Simulates the "Delete the class from the archive" step.
     * @param id The ID of the class to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteClass(int id) {
        if (classes.containsKey(id)) {
            classes.remove(id);
            // Simulate connection interruption to SMOS server (Postcondition)
            System.out.println("Connection to SMOS server interrupted after deletion.");
            return true;
        }
        return false;
    }
    /**
     * Gets all classes in the database.
     * @return List of all classes
     */
    public List<Class> getAllClasses() {
        return new ArrayList<>(classes.values());
    }
    /**
     * Gets a list of classes as an array for display in tables.
     * @return 2D array containing class information
     */
    public Object[][] getClassData() {
        List<Class> classList = getAllClasses();
        Object[][] data = new Object[classList.size()][6];
        for (int i = 0; i < classList.size(); i++) {
            Class cls = classList.get(i);
            data[i][0] = cls.getId();
            data[i][1] = cls.getName();
            data[i][2] = cls.getDescription();
            data[i][3] = cls.getTeacher();
            data[i][4] = cls.getRoom();
            data[i][5] = cls.getMaxStudents();
        }
        return data;
    }
    /**
     * Gets the column names for class display.
     * @return Array of column names
     */
    public String[] getColumnNames() {
        return new String[]{"ID", "Name", "Description", "Teacher", "Room", "Max Students"};
    }
}