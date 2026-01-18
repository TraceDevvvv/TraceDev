/*
 * DOCSTRING: Defines the Student data model class.
 * This class holds basic student information, including a reference to their parent.
 */
package model;
import java.util.Objects;
public class Student {
    private final int id;
    private final String name;
    private final int parentId; // ID of the parent associated with this student
    /**
     * Constructor for a Student.
     *
     * @param id The unique ID of the student.
     * @param name The name of the student.
     * @param parentId The ID of the student's parent.
     */
    public Student(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
    // --- Getters ---
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getParentId() {
        return parentId;
    }
    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", parentId=" + parentId +
               '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}