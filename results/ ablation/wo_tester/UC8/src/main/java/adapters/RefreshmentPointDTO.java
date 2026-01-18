package adapters;

import domain.RefreshmentPoint;
import domain.Status;
import java.util.List;

/**
 * Data Transfer Object for refreshment points.
 */
public class RefreshmentPointDTO {
    private String id;
    private String name;
    private String address;
    private String status;
    private List<String> amenities;

    public RefreshmentPointDTO() {
    }

    public RefreshmentPointDTO(String id, String name, String address, String status, List<String> amenities) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
        this.amenities = amenities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    /**
     * Populates DTO from entity.
     * @param entity the RefreshmentPoint entity.
     */
    public void fromEntity(RefreshmentPoint entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.status = entity.getStatus().name();
        this.amenities = entity.getAmenities();
    }

    /**
     * Converts DTO to entity.
     * @return the RefreshmentPoint entity.
     */
    public RefreshmentPoint toEntity() {
        // Assumption: Status is valid enum value.
        Status statusEnum = Status.valueOf(this.status);
        return new RefreshmentPoint(this.id, this.name, this.address, statusEnum, this.amenities);
    }
}