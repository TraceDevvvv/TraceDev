import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a parent in the school management system.
 * Each parent has a unique ID, a name, and can be associated with multiple students.
 */
public class Parent {
    private final String parentId;
    private final String name;
    private final Set<Student> associatedStudents; // Using a Set to store unique students

    /**
     * Constructs a new Parent object.
     *
     * @param parentId The unique identifier for the parent.
     * @param name The full name of the parent.
     */
    public Parent(String parentId, String name) {
        // Validate inputs to ensure they are not null or empty
        if (parentId == null || parentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent name cannot be null or empty.");
        }
        this.parentId = parentId;
        this.name = name;
        this.associatedStudents = new HashSet<>(); // Initialize the set of associated students
    }

    /**
     * Returns the unique ID of the parent.
     *
     * @return The parent's ID.
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Returns the name of the parent.
     *
     * @return The parent's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Associates a student with this parent.
     *
     * @param student The Student object to associate.
     * @return true if the student was successfully added, false if the student was already associated.
     * @throws IllegalArgumentException if the student is null.
     */
    public boolean addAssociatedStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student to associate cannot be null.");
        }
        return associatedStudents.add(student);
    }

    /**
     * Removes an association between this parent and a student.
     *
     * @param student The Student object to remove.
     * @return true if the student was successfully removed, false if the student was not associated.
     * @throws IllegalArgumentException if the student is null.
     */
    public boolean removeAssociatedStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student to remove cannot be null.");
        }
        return associatedStudents.remove(student);
    }

    /**
     * Returns an unmodifiable set of students associated with this parent.
     * This prevents external modification of the internal student list.
     *
     * @return An unmodifiable Set of Student objects.
     */
    public Set<Student> getAssociatedStudents() {
        return Collections.unmodifiableSet(associatedStudents);
    }

    /**
     * Checks if a specific student is associated with this parent.
     *
     * @param student The student to check for.
     * @return true if the student is associated, false otherwise.
     */
    public boolean isStudentAssociated(Student student) {
        if (student == null) {
            return false; // A null student cannot be associated
        }
        return associatedStudents.contains(student);
    }

    /**
     * Overrides the toString method to provide a meaningful string representation of the Parent object.
     *
     * @return A string containing the parent's ID, name, and the number of associated students.
     */
    @Override
    public String toString() {
        return "Parent [ID: " + parentId + ", Name: " + name + ", Associated Students: " + associatedStudents.size() + "]";
    }

    /**
     * Compares this Parent object with the specified object for equality.
     * Two Parent objects are considered equal if they have the same parentId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return parentId.equals(parent.parentId);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the parentId, ensuring consistency with the equals method.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(parentId);
    }
}