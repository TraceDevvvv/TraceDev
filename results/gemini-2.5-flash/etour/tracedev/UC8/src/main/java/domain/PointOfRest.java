package domain;

import dto.PointOfRestDTO; // Needed for updateDetails method

/**
 * Domain Layer: Entity representing a Point of Rest.
 * Contains core business logic and state.
 */
public class PointOfRest {
    private String id;
    private String name;
    private String address;
    private String description;
    private String status; // e.g., "Active", "Inactive", "Pending"

    // Constructor
    public PointOfRest(String id, String name, String address, String description, String status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.status = status;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Updates the details of the Point of Rest using data from a DTO.
     * This method encapsulates the business rule for updating an entity.
     * @param dto The DTO containing the updated data.
     */
    public void updateDetails(PointOfRestDTO dto) {
        System.out.println("[Domain] Updating PointOfRest entity ID: " + this.id);
        // Basic validation or business rules can be applied here before updating.
        // For simplicity, we directly assign.
        if (dto.name != null && !dto.name.trim().isEmpty()) {
            this.name = dto.name;
        }
        if (dto.address != null && !dto.address.trim().isEmpty()) {
            this.address = dto.address;
        }
        if (dto.description != null) { // Description can be empty
            this.description = dto.description;
        }
        if (dto.status != null && !dto.status.trim().isEmpty()) {
            this.status = dto.status;
        }
        System.out.println("[Domain] PointOfRest entity ID " + this.id + " details updated.");
    }

    /**
     * Checks if the Point of Rest entity is in a valid state.
     * Implements domain-specific validation rules.
     * @return true if the entity is valid, false otherwise.
     */
    public boolean isValid() {
        // Example validation rules
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        // Status should be one of predefined values
        return "Active".equals(status) || "Inactive".equals(status) || "Pending".equals(status);
    }

    @Override
    public String toString() {
        return "PointOfRest{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", description='" + description + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}