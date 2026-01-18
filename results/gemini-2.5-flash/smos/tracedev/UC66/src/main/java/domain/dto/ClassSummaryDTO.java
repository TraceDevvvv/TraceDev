package domain.dto;

/**
 * Data Transfer Object for a summary of Class information.
 * Used to transfer basic class data between layers.
 */
public class ClassSummaryDTO {
    public String id;
    public String name;

    /**
     * Constructs a new ClassSummaryDTO.
     * @param id The unique identifier of the class.
     * @param name The name of the class (e.g., "10A", "Grade 5 Math").
     */
    public ClassSummaryDTO(String id, String name) {
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
        return "ClassSummaryDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}