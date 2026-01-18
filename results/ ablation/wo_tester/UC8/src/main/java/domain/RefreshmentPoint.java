package domain;

import java.util.List;

/**
 * Represents a refreshment point.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String address;
    private Status status;
    private List<String> amenities;

    public RefreshmentPoint(String id, String name, String address, Status status, List<String> amenities) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    /**
     * Checks if the refreshment point is active.
     * @return true if status is ACTIVE, false otherwise.
     */
    public boolean isActive() {
        return Status.ACTIVE.equals(this.status);
    }

    /**
     * Checks if the refreshment point is functional.
     * Assumption: A point is functional if it is not under maintenance.
     * @return true if status is not MAINTENANCE, false otherwise.
     */
    public boolean isFunctional() {
        return !Status.MAINTENANCE.equals(this.status);
    }

    /**
     * Updates the refreshment point attributes from a DTO.
     * @param dto the DTO containing new data.
     */
    public void updateFromDTO(RefreshmentPointDTO dto) {
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.status = Status.valueOf(dto.getStatus());
        this.amenities = dto.getAmenities();
    }
}