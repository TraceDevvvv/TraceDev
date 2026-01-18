package infrastructure.dao;

/**
 * Data Access Object for Class information.
 * Represents the structure of class data as stored in the persistence layer.
 */
public class ClassDAO {
    public String id;
    public String name;
    public String academicYearId; // Foreign key to AcademicYear

    /**
     * Constructs a new ClassDAO.
     * @param id The unique identifier of the class.
     * @param name The name of the class (e.g., "10A").
     * @param academicYearId The ID of the academic year this class belongs to.
     */
    public ClassDAO(String id, String name, String academicYearId) {
        this.id = id;
        this.name = name;
        this.academicYearId = academicYearId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }
}