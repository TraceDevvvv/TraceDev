'''
Represents a Student entity in the system.
This is a simple model class holding basic information about a student.
'''
import java.util.Objects;
public class Student {
    private int id;
    private String name;
    /**
     * Constructs a new Student object.
     * @param id The unique identifier for the student.
     * @param name The name of the student.
     */
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
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