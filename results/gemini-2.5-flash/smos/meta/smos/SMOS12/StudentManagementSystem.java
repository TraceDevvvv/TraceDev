import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Manages all students and parents within the school system.
 * This class provides functionalities to add, retrieve, and manage associations
 * between parents and students, specifically allowing administrators to assign
 * or remove students from a parent.
 */
public class StudentManagementSystem {
    // Stores all students, mapped by their unique student ID.
    private final Map<String, Student> students;
    // Stores all parents, mapped by their unique parent ID.
    private final Map<String, Parent> parents;

    /**
     * Constructs a new StudentManagementSystem.
     * Initializes the internal data structures for students and parents.
     */
    public StudentManagementSystem() {
        this.students = new HashMap<>();
        this.parents = new HashMap<>();
    }

    /**
     * Adds a student to the system.
     *
     * @param student The Student object to add.
     * @return true if the student was added successfully, false if a student with the same ID already exists.
     * @throws IllegalArgumentException if the student object is null.
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (students.containsKey(student.getStudentId())) {
            System.out.println("Error: Student with ID " + student.getStudentId() + " already exists.");
            return false;
        }
        students.put(student.getStudentId(), student);
        System.out.println("Student added: " + student.getName() + " (ID: " + student.getStudentId() + ")");
        return true;
    }

    /**
     * Adds a parent to the system.
     *
     * @param parent The Parent object to add.
     * @return true if the parent was added successfully, false if a parent with the same ID already exists.
     * @throws IllegalArgumentException if the parent object is null.
     */
    public boolean addParent(Parent parent) {
        if (parent == null) {
            throw new IllegalArgumentException("Parent cannot be null.");
        }
        if (parents.containsKey(parent.getParentId())) {
            System.out.println("Error: Parent with ID " + parent.getParentId() + " already exists.");
            return false;
        }
        parents.put(parent.getParentId(), parent);
        System.out.println("Parent added: " + parent.getName() + " (ID: " + parent.getParentId() + ")");
        return true;
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param studentId The ID of the student to retrieve.
     * @return The Student object if found, null otherwise.
     */
    public Student getStudentById(String studentId) {
        return students.get(studentId);
    }

    /**
     * Retrieves a parent by their ID.
     *
     * @param parentId The ID of the parent to retrieve.
     * @return The Parent object if found, null otherwise.
     */
    public Parent getParentById(String parentId) {
        return parents.get(parentId);
    }

    /**
     * Returns an unmodifiable set of all students in the system.
     *
     * @return A Set of all Student objects.
     */
    public Set<Student> getAllStudents() {
        return Collections.unmodifiableSet(new HashSet<>(students.values()));
    }

    /**
     * Returns an unmodifiable set of all parents in the system.
     *
     * @return A Set of all Parent objects.
     */
    public Set<Parent> getAllParents() {
        return Collections.unmodifiableSet(new HashSet<>(parents.values()));
    }

    /**
     * Assigns a set of students to a specific parent.
     * This operation requires an administrator to be logged in.
     *
     * @param admin The Administrator performing the action.
     * @param parentId The ID of the parent to whom students will be assigned.
     * @param studentIdsToAssign A set of student IDs to be associated with the parent.
     * @return true if all specified students were successfully assigned (or already assigned), false otherwise.
     * @throws IllegalStateException if the administrator is not logged in.
     * @throws IllegalArgumentException if parentId or studentIdsToAssign is null or empty.
     */
    public boolean assignStudentsToParent(Administrator admin, String parentId, Set<String> studentIdsToAssign) {
        // Precondition: Administrator must be logged in.
        if (admin == null || !admin.isLoggedIn()) {
            throw new IllegalStateException("Administrator must be logged in to assign students.");
        }
        if (parentId == null || parentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent ID cannot be null or empty.");
        }
        if (studentIdsToAssign == null || studentIdsToAssign.isEmpty()) {
            System.out.println("No students provided for assignment to parent " + parentId + ".");
            return true; // No students to assign, so it's "successful" in that nothing failed.
        }

        Parent parent = parents.get(parentId);
        if (parent == null) {
            System.out.println("Error: Parent with ID " + parentId + " not found.");
            return false;
        }

        boolean allAssignedSuccessfully = true;
        System.out.println("Attempting to assign students to parent: " + parent.getName() + " (ID: " + parent.getParentId() + ")");
        for (String studentId : studentIdsToAssign) {
            Student student = students.get(studentId);
            if (student == null) {
                System.out.println("Warning: Student with ID " + studentId + " not found. Skipping assignment.");
                allAssignedSuccessfully = false;
                continue;
            }
            if (parent.addAssociatedStudent(student)) {
                System.out.println("Successfully assigned student " + student.getName() + " (ID: " + student.getStudentId() + ").");
            } else {
                System.out.println("Student " + student.getName() + " (ID: " + student.getStudentId() + ") was already assigned to parent " + parent.getName() + ".");
            }
        }
        return allAssignedSuccessfully;
    }

    /**
     * Removes a set of students from a specific parent.
     * This operation requires an administrator to be logged in.
     *
     * @param admin The Administrator performing the action.
     * @param parentId The ID of the parent from whom students will be removed.
     * @param studentIdsToRemove A set of student IDs to be disassociated from the parent.
     * @return true if all specified students were successfully removed (or already not associated), false otherwise.
     * @throws IllegalStateException if the administrator is not logged in.
     * @throws IllegalArgumentException if parentId or studentIdsToRemove is null or empty.
     */
    public boolean removeStudentsFromParent(Administrator admin, String parentId, Set<String> studentIdsToRemove) {
        // Precondition: Administrator must be logged in.
        if (admin == null || !admin.isLoggedIn()) {
            throw new IllegalStateException("Administrator must be logged in to remove students.");
        }
        if (parentId == null || parentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent ID cannot be null or empty.");
        }
        if (studentIdsToRemove == null || studentIdsToRemove.isEmpty()) {
            System.out.println("No students provided for removal from parent " + parentId + ".");
            return true; // No students to remove, so it's "successful" in that nothing failed.
        }

        Parent parent = parents.get(parentId);
        if (parent == null) {
            System.out.println("Error: Parent with ID " + parentId + " not found.");
            return false;
        }

        boolean allRemovedSuccessfully = true;
        System.out.println("Attempting to remove students from parent: " + parent.getName() + " (ID: " + parent.getParentId() + ")");
        for (String studentId : studentIdsToRemove) {
            Student student = students.get(studentId);
            if (student == null) {
                System.out.println("Warning: Student with ID " + studentId + " not found. Skipping removal.");
                allRemovedSuccessfully = false;
                continue;
            }
            if (parent.removeAssociatedStudent(student)) {
                System.out.println("Successfully removed student " + student.getName() + " (ID: " + student.getStudentId() + ").");
            } else {
                System.out.println("Student " + student.getName() + " (ID: " + student.getStudentId() + ") was not associated with parent " + parent.getName() + ".");
            }
        }
        return allRemovedSuccessfully;
    }

    /**
     * Displays the details of a specific parent, including their associated students.
     * This method simulates the "viewdetTailsente" use case precondition.
     *
     * @param parentId The ID of the parent to display details for.
     * @return The Parent object if found, null otherwise.
     */
    public Parent viewParentDetails(String parentId) {
        Parent parent = parents.get(parentId);
        if (parent == null) {
            System.out.println("Parent with ID " + parentId + " not found.");
            return null;
        }
        System.out.println("\n--- Parent Details ---");
        System.out.println("Parent ID: " + parent.getParentId());
        System.out.println("Parent Name: " + parent.getName());
        System.out.println("Associated Students (" + parent.getAssociatedStudents().size() + "):");
        if (parent.getAssociatedStudents().isEmpty()) {
            System.out.println("  No students associated.");
        } else {
            for (Student student : parent.getAssociatedStudents()) {
                System.out.println("  - " + student.getName() + " (ID: " + student.getStudentId() + ")");
            }
        }
        System.out.println("----------------------");
        return parent;
    }

    /**
     * Displays all available students in the system.
     * This helps the administrator to select students for assignment/removal.
     */
    public void displayAllAvailableStudents() {
        System.out.println("\n--- All Available Students ---");
        if (students.isEmpty()) {
            System.out.println("No students registered in the system.");
            return;
        }
        for (Student student : students.values()) {
            System.out.println("  - " + student.getName() + " (ID: " + student.getStudentId() + ")");
        }
        System.out.println("------------------------------");
    }
}