package com.example.refreshmentpoint;

/**
 * Data Transfer Object (DTO) for RefreshmentPoint.
 * Used for transferring data between layers, e.g., Controller to Service, Service to Form.
 */
public class RefreshmentPointDTO {
    public String id;
    public String name;
    public String location;
    public String type;
    // DTO for transferring data between layers

    public RefreshmentPointDTO(String id, String name, String location, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
    }

    // Default constructor for convenience in some scenarios
    public RefreshmentPointDTO() {
    }

    @Override
    public String toString() {
        return "RefreshmentPointDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}