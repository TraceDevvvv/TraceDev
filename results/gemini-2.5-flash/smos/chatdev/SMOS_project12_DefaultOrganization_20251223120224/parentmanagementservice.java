'''
This service class handles the business logic for managing parents and students.
It simulates a backend data store and operations like fetching students,
and associating/disassociating students with parents.
In a real application, this would interact with a database or a remote API.
'''
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class ParentManagementService {
    // Simulate a database of all available students
    private static List<Student> allStudents;
    // Simulate a database of parents managed by the system
    private static List<Parent> allParents;
    // Static initializer to populate some dummy data
    static {
        allStudents = new ArrayList<>(Arrays.asList(
                new Student(101, "Alice Smith"),
                new Student(102, "Bob Johnson"),
                new Student(103, "Charlie Brown"),
                new Student(104, "Diana Prince"),
                new Student(105, "Eve Adams"),
                new Student(106, "Frank White")
        ));
        Parent parent1 = new Parent(1, "John Doe");
        parent1.setAssociatedStudents(new ArrayList<>(Arrays.asList(allStudents.get(0), allStudents.get(1)))); // Alice, Bob
        Parent parent2 = new Parent(2, "Jane Doe");
        parent2.setAssociatedStudents(new ArrayList<>(Arrays.asList(allStudents.get(2)))); // Charlie
        allParents = new ArrayList<>(Arrays.asList(parent1, parent2));
    }
    /**
     * Retrieves all students available in the system.
     * @return A list of all students.
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(allStudents);
    }
    /**
     * Retrieves the list of students currently associated with a given parent.
     * @param parent The parent for whom to retrieve associated students.
     * @return A list of students associated with the parent.
     */
    public List<Student> getStudentsForParent(Parent parent) {
        // Find the actual parent object in our simulated storage to get its associated students
        return allParents.stream()
                .filter(p -> p.equals(parent))
                .findFirst()
                .map(Parent::getAssociatedStudents)
                .orElse(new ArrayList<>()); // Return empty list if parent not found
    }
    /**
     * Retrieves the list of students NOT currently associated with a given parent.
     * @param parent The parent for whom to retrieve unassociated students.
     * @return A list of students not associated with the parent.
     */
    public List<Student> getUnassociatedStudentsForParent(Parent parent) {
        List<Student> associated = getStudentsForParent(parent);
        return allStudents.stream()
                .filter(student -> !associated.contains(student))
                .collect(Collectors.toList());
    }
    /**
     * Updates the association of students for a specific parent.
     * This method removes previous associations and sets new ones.
     *
     * @param parent The parent whose student associations are being updated.
     * @param newStudentList The new list of students to be associated with the parent.
     */
    public void updateParentStudents(Parent parent, List<Student> newStudentList) {
        // Find the actual parent object in our simulated storage
        allParents.stream()
                .filter(p -> p.equals(parent))
                .findFirst()
                .ifPresent(p -> {
                    p.setAssociatedStudents(newStudentList);
                    System.out.println("Updated students for parent " + p.getName() + ": " + newStudentList);
                });
    }
    /**
     * Simulates "interrupting the connection to the SMOS server".
     * In a real application, this might involve closing database connections, logging out, etc.
     * This method no longer exits the application immediately.
     */
    public void interruptSMOSConnection() {
        System.out.println("Administrator interrupted connection to SMOS server. (Simulated connection closure/cleanup)");
        // In a real application, this would involve closing database connections,
        // logging out, releasing resources, etc., but not necessarily exiting the JVM.
        // For this simulation, we'll just log and perform no hard exit.
    }
}