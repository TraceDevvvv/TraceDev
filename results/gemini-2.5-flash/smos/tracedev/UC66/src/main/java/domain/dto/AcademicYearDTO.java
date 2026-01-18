package domain.dto;

/**
 * Data Transfer Object for Academic Year information.
 * Used to transfer academic year data between layers, typically from application to presentation.
 */
public class AcademicYearDTO {
    public String id;
    public String year;

    /**
     * Constructs a new AcademicYearDTO.
     * @param id The unique identifier of the academic year.
     * @param year The display name of the academic year (e.g., "2023-2024").
     */
    public AcademicYearDTO(String id, String year) {
        this.id = id;
        this.year = year;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "AcademicYearDTO{" +
               "id='" + id + '\'' +
               ", year='" + year + '\'' +
               '}';
    }
}