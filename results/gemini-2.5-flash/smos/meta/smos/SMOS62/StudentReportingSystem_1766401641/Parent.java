import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a parent user in the student reporting system.
 * A parent can be associated with multiple students.
 */
public class Parent {
    private String parentId;
    private String name;
    private String username; // For login purposes
    private String password; // For login purposes (in a real system, this would be hashed)
    private List<String> associatedStudentIds; // List of student IDs linked to this parent

    /**
     * Constructs a new Parent object.
     *
     * @param parentId The unique identifier for the parent.
     * @param name The full name of the parent.
     * @param username The username for login.
     * @param password The password for login (should be hashed in a production system).
     * @throws IllegalArgumentException if parentId, name, username, or password is null or empty.
     */
    public Parent(String parentId, String name, String username, String password) {
        // Validate inputs
        if (parentId == null || parentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent name cannot be null or empty.");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (password == null || password.isEmpty()) { // Password can be empty string but not null
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        this.parentId = parentId;
        this.name = name;
        this.username = username;
        this.password = password; // In a real application, store a hashed password
        this.associatedStudentIds = new ArrayList<>();
    }

    /**
     * Returns the unique identifier of the parent.
     *
     * @return The parent's ID.
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Returns the full name of the parent.
     *
     * @return The parent's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the username of the parent.
     *
     * @return The parent's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the parent.
     * NOTE: In a real system, this method should not expose the raw password.
     *
     * @return The parent's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns an unmodifiable list of student IDs associated with this parent.
     *
     * @return A list of associated student IDs.
     */
    public List<String> getAssociatedStudentIds() {
        return Collections.unmodifiableList(associatedStudentIds);
    }

    /**
     * Adds a student ID to the list of students associated with this parent.
     *
     * @param studentId The ID of the student to associate.
     * @return true if the student was added, false if already associated.
     * @throws IllegalArgumentException if studentId is null or empty.
     */
    public boolean addAssociatedStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (!associatedStudentIds.contains(studentId.trim())) {
            return associatedStudentIds.add(studentId.trim());
        }
        return false; // Student already associated
    }

    /**
     * Removes a student ID from the list of students associated with this parent.
     *
     * @param studentId The ID of the student to disassociate.
     * @return true if the student was removed, false if not found.
     * @throws IllegalArgumentException if studentId is null or empty.
     */
    public boolean removeAssociatedStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        return associatedStudentIds.remove(studentId.trim());
    }

    /**
     * Authenticates the parent with the given username and password.
     * NOTE: In a real system, this would involve hashing and secure comparison.
     *
     * @param enteredUsername The username provided by the user.
     * @param enteredPassword The password provided by the user.
     * @return true if the credentials match, false otherwise.
     */
    public boolean authenticate(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    /**
     * Overrides the equals method to compare Parent objects based on their parentId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return Objects.equals(parentId, parent.parentId);
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     *
     * @return The hash code for this Parent object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(parentId);
    }

    /**
     * Returns a string representation of the Parent object.
     *
     * @return A string containing the parent's ID, name, and associated student IDs.
     */
    @Override
    public String toString() {
        return "Parent{" +
               "parentId='" + parentId + '\'' +
               ", name='" + name + '\'' +
               ", username='" + username + '\'' +
               ", associatedStudentIds=" + associatedStudentIds +
               '}';
    }
}