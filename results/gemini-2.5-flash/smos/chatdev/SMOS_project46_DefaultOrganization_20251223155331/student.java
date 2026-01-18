/**
 * A simple Plain Old Java Object (POJO) representing a student.
 * Contains basic information about a student and their associated class.
 */
public class Student {
    private String id;
    private String name;
    private String classId;
    public Student(String id, String name, String classId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
    }
    // Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getClassId() {
        return classId;
    }
    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}