/**
 * This class represents a Teacher in the system.
 * It holds basic information about a teacher such as their ID and name.
 */
package model;
import java.util.Objects;
public class Teacher {
    private String id;
    private String name;
    /**
     * Constructs a new Teacher object.
     * @param id The unique identifier for the teacher.
     * @param name The name of the teacher.
     */
    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Returns the ID of the teacher.
     * @return The teacher's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the teacher.
     * @param id The new ID for the teacher.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the name of the teacher.
     * @return The teacher's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the teacher.
     * @param name The new name for the teacher.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Overrides the toString method to provide a meaningful representation of the Teacher object.
     * @return A string representation of the teacher.
     */
    @Override
    public String toString() {
        return "Teacher{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
    /**
     * Compares this Teacher object to another object for equality.
     * Two Teacher objects are considered equal if their 'id' fields are identical.
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id);
    }
    /**
     * Returns a hash code value for the Teacher object.
     * The hash code is based on the 'id' field.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}