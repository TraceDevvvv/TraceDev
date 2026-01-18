'''
Simulates a data service for retrieving class and register information.
In a real application, this would interact with a database or a backend API.
For this example, it provides hardcoded sample data.
The use case mentions "Connection to the interrupted SMOS server." This class implicitly handles
the data retrieval, and the "interrupted" part would be handled by error handling
if a real connection existed, but here it's simplified as successful data retrieval.
A simulateDisconnect() method has been added to explicitly address the postcondition.
'''
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DataService {
    // Simulates a database of classes taught by the professor
    private static final List<ClassInfo> PROFESSOR_CLASSES = new ArrayList<>();
    // Simulates a map where class IDs map to their respective register entries.
    private static final Map<String, List<StudentRegisterEntry>> CLASS_REGISTERS = new HashMap<>();
    static {
        // Initialize sample class data
        ClassInfo class1 = new ClassInfo("CS101", "Introduction to Programming", "Computer Science", "Fall 2023");
        ClassInfo class2 = new ClassInfo("MA201", "Calculus I", "Mathematics", "Fall 2023");
        ClassInfo class3 = new ClassInfo("PH305", "Quantum Mechanics", "Physics", "Spring 2024");
        PROFESSOR_CLASSES.add(class1);
        PROFESSOR_CLASSES.add(class2);
        PROFESSOR_CLASSES.add(class3);
        // Initialize sample register data for each class
        CLASS_REGISTERS.put(class1.getClassId(), Arrays.asList(
            new StudentRegisterEntry("2023-10-23", "Alice Smith", false, null, false, null),
            new StudentRegisterEntry("2023-10-23", "Bob Johnson", true, null, false, "Flu"),
            new StudentRegisterEntry("2023-10-23", "Charlie Brown", false, "Late for 10 min", true, "Traffic"),
            new StudentRegisterEntry("2023-10-24", "Alice Smith", false, null, false, null),
            new StudentRegisterEntry("2023-10-24", "Bob Johnson", false, null, false, null),
            new StudentRegisterEntry("2023-10-24", "Charlie Brown", true, "Unexcused", false, null),
            new StudentRegisterEntry("2023-10-25", "Alice Smith", false, null, false, null),
            new StudentRegisterEntry("2023-10-25", "Bob Johnson", false, null, false, null),
            new StudentRegisterEntry("2023-10-25", "Charlie Brown", false, null, false, null)
        ));
        CLASS_REGISTERS.put(class2.getClassId(), Arrays.asList(
            new StudentRegisterEntry("2023-10-22", "David Lee", false, null, false, null),
            new StudentRegisterEntry("2023-10-22", "Eve Davis", true, null, false, "Doctor appointment"),
            new StudentRegisterEntry("2023-10-22", "Frank White", false, null, false, null),
            new StudentRegisterEntry("2023-10-23", "David Lee", false, "Talking during lecture", false, null),
            new StudentRegisterEntry("2023-10-23", "Eve Davis", false, null, false, null)
        ));
        CLASS_REGISTERS.put(class3.getClassId(), Arrays.asList(
            new StudentRegisterEntry("2024-03-01", "Grace Hall", false, null, false, null),
            new StudentRegisterEntry("2024-03-01", "Henry King", true, null, false, null),
            new StudentRegisterEntry("2024-03-02", "Grace Hall", false, null, false, null)
        ));
    }
    /**
     * Retrieves a list of classes taught by the professor.
     * In a real system, this would filter by the logged-in professor's ID.
     * @return A list of ClassInfo objects.
     */
    public List<ClassInfo> getProfessorClasses() {
        // Returns a copy to prevent external modification of the internal list.
        return new ArrayList<>(PROFESSOR_CLASSES); 
    }
    /**
     * Retrieves the register entries for a specific class.
     * @param classId The unique ID of the class.
     * @return A list of StudentRegisterEntry objects for the given class, or an empty list if not found.
     */
    public List<StudentRegisterEntry> getClassRegister(String classId) {
        // Returns a copy or an empty list if no data for the class ID.
        return CLASS_REGISTERS.getOrDefault(classId, new ArrayList<>());
    }
    /**
     * Simulates the interruption or closing of the connection to the SMOS server.
     * In a real application, this would involve releasing network resources or closing sessions.
     * Here, it provides a console message to explicitly address the postcondition.
     */
    public void simulateDisconnect() {
        System.out.println("--- SMOS server connection simulated as interrupted/closed. ---");
    }
}