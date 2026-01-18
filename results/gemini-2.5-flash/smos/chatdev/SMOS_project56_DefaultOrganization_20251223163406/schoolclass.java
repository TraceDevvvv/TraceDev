/**
 * This class represents a School Class.
 * It includes details like class ID, name, the academic year it belongs to, and the teacher assigned.
 */
package model;
import java.util.Objects;
public class SchoolClass {
    private String id;
    private String name;
    private String academicYearId;
    private String teacherId;
    /**
     * Constructs a new SchoolClass object.
     * @param id The unique identifier for the class.
     * @param name The name of the class (e.g., "Mathematics 101").
     * @param academicYearId The ID of the academic year this class belongs to.
     * @param teacherId The ID of the teacher assigned to this class.
     */
    public SchoolClass(String id, String name, String academicYearId, String teacherId) {
        this.id = id;
        this.name = name;
        this.academicYearId = academicYearId;
        this.teacherId = teacherId;
    }
    /**
     * Returns the ID of the class.
     * @return The class's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the class.
     * @param id The new ID for the class.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the name of the class.
     * @return The class's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the class.
     * @param name The new name for the class.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the academic year ID of the class.
     * @return The academic year ID.
     */
    public String getAcademicYearId() {
        return academicYearId;
    }
    /**
     * Sets the academic year ID of the class.
     * @param academicYearId The new academic year ID.
     */
    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }
    /**
     * Returns the teacher ID assigned to the class.
     * @return The teacher's ID.
     */
    public String getTeacherId() {
        return teacherId;
    }
    /**
     * Sets the teacher ID assigned to the class.
     * @param teacherId The new teacher ID.
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    /**
     * Overrides the toString method to provide a meaningful representation of the SchoolClass object.
     * @return A string representation of the class.
     */
    @Override
    public String toString() {
        return "Class Name: " + name + " (ID: " + id + ")";
    }
    /**
     * Compares this SchoolClass object to another object for equality.
     * Two SchoolClass objects are considered equal if their 'id' fields are identical.
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolClass that = (SchoolClass) o;
        return Objects.equals(id, that.id);
    }
    /**
     * Returns a hash code value for the SchoolClass object.
     * The hash code is based on the 'id' field.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}