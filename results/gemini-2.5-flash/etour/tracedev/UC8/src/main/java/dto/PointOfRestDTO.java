package dto;

import domain.PointOfRest; // Needed for createFromEntity method

/**
 * Data Transfer Objects: DTO for Point of Rest.
 * Used to transfer data between layers, particularly from application to presentation.
 */
public class PointOfRestDTO {
    public String id;
    public String name;
    public String address;
    public String description;
    public String status;

    // No-argument constructor is useful for frameworks (e.g., JSON deserialization)
    public PointOfRestDTO() {}

    /**
     * Static factory method to create a PointOfRestDTO from a PointOfRest entity.
     * Added to satisfy requirement R5.
     * @param entity The PointOfRest entity to convert.
     * @return A new PointOfRestDTO instance.
     */
    public static PointOfRestDTO createFromEntity(PointOfRest entity) {
        System.out.println("[DTO] Creating DTO from PointOfRest entity ID: " + entity.getId());
        PointOfRestDTO dto = new PointOfRestDTO();
        dto.id = entity.getId();
        dto.name = entity.getName();
        dto.address = entity.getAddress();
        dto.description = entity.getDescription();
        dto.status = entity.getStatus();
        return dto;
    }

    @Override
    public String toString() {
        return "PointOfRestDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", description='" + description + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}