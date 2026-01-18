package com.example.dto;

import com.example.model.PointOfRest;

import java.util.List;
import java.util.Objects;

/**
 * Data Transfer Object for PointOfRest.
 * Transfers filtered lists and converts to/from entity.
 */
public class PointOfRestDTO {
    public String id;          // public as per class diagram
    public String name;
    public String location;
    public List<String> facilities;

    public PointOfRestDTO() {
    }

    public PointOfRestDTO(String id, String name, String location, List<String> facilities) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.facilities = facilities;
    }

    /**
     * Converts this DTO to a PointOfRest entity.
     * @return PointOfRest entity
     */
    public PointOfRest toPointOfRest() {
        PointOfRest point = new PointOfRest();
        point.setId(this.id);
        point.setName(this.name);
        point.setLocation(this.location);
        point.setFacilities(this.facilities);
        return point;
    }

    // Static method to convert from entity to DTO (used in controller)
    public static PointOfRestDTO fromPointOfRest(PointOfRest point) {
        return new PointOfRestDTO(point.getId(), point.getName(), point.getLocation(), point.getFacilities());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointOfRestDTO that = (PointOfRestDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(facilities, that.facilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, facilities);
    }

    @Override
    public String toString() {
        return "PointOfRestDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", facilities=" + facilities +
                '}';
    }
}