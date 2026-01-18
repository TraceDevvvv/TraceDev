package domain.dto;

/**
 * Data Transfer Object for a summary of Student information.
 * Used to transfer basic student data between layers.
 */
public class StudentSummaryDTO {
    public String id;
    public String name;

    /**
     * Constructs a new StudentSummaryDTO.
     * @param id The unique identifier of the student.
     * @param name The full name of the student.
     */
    public StudentSummaryDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StudentSummaryDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}