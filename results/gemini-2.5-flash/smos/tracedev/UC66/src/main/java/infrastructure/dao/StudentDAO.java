package infrastructure.dao;

/**
 * Data Access Object for Student information.
 * Represents the structure of student data as stored in the persistence layer.
 */
public class StudentDAO {
    public String id;
    public String name;
    public String classId; // Foreign key to Class

    /**
     * Constructs a new StudentDAO.
     * @param id The unique identifier of the student.
     * @param name The full name of the student.
     * @param classId The ID of the class the student belongs to.
     */
    public StudentDAO(String id, String name, String classId) {
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
}