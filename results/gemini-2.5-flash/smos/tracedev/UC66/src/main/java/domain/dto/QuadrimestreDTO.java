package domain.dto;

/**
 * Data Transfer Object for Quadrimestre information.
 * Used to transfer quadrimestre period data between layers.
 */
public class QuadrimestreDTO {
    public String id;
    public String name;

    /**
     * Constructs a new QuadrimestreDTO.
     * @param id The unique identifier of the quadrimestre.
     * @param name The name of the quadrimestre (e.g., "Q1", "First Quarter").
     */
    public QuadrimestreDTO(String id, String name) {
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
        return "QuadrimestreDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}