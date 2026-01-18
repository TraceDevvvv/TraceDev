package infrastructure.dao;

/**
 * Data Access Object for Academic Year information.
 * Represents the structure of academic year data as stored in the persistence layer.
 */
public class AcademicYearDAO {
    public String id;
    public String year;

    /**
     * Constructs a new AcademicYearDAO.
     * @param id The unique identifier of the academic year.
     * @param year The display name of the academic year (e.g., "2023-2024").
     */
    public AcademicYearDAO(String id, String year) {
        this.id = id;
        this.year = year;
    }

    // Getters for DAO (often not strictly needed if fields are public, but good practice)
    public String getId() {
        return id;
    }

    public String getYear() {
        return year;
    }
}