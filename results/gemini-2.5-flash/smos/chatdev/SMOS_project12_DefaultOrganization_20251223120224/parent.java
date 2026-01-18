'''
Represents a Parent entity in the system.
This is a simple model class holding basic information about a parent.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Parent {
    private int id;
    private String name;
    private List<Student> associatedStudents; // Students currently linked to this parent
    /**
     * Constructs a new Parent object.
     * @param id The unique identifier for the parent.
     * @param name The name of the parent.
     */
    public Parent(int id, String name) {
        this.id = id;
        this.name = name;
        this.associatedStudents = new ArrayList<>();
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Student> getAssociatedStudents() {
        return new ArrayList<>(associatedStudents); // Return a copy to prevent external modification
    }
    // Setter for associated students, used by the service layer
    public void setAssociatedStudents(List<Student> associatedStudents) {
        this.associatedStudents = new ArrayList<>(associatedStudents);
    }
    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return id == parent.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}