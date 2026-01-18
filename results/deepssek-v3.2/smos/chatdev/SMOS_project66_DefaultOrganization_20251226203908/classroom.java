import java.util.List;
import java.util.ArrayList;
/**
 * Classroom.java
 * Represents a classroom with students
 */
public class Classroom {
    private String id;
    private String name;
    private String gradeLevel;
    private String roomNumber;
    private List<Student> students;
    public Classroom(String id, String name, String gradeLevel, String roomNumber) {
        this.id = id;
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.roomNumber = roomNumber;
        this.students = new ArrayList<>();
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getGradeLevel() {
        return gradeLevel;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    @Override
    public String toString() {
        return name + " - " + roomNumber + " (" + gradeLevel + ")";
    }
}