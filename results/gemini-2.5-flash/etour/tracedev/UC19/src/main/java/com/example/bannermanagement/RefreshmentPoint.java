package com.example.bannermanagement;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Refreshment Point where banners can be displayed.
 * This class corresponds to the 'RefreshmentPoint' entity in the UML Class Diagram.
 */
public class RefreshmentPoint {
    public String id;
    public String name;
    public String description;

    /**
     * Constructs a new RefreshmentPoint.
     * @param id The unique identifier for the refreshment point.
     * @param name The name of the refreshment point.
     * @param description A brief description of the refreshment point.
     */
    public RefreshmentPoint(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Retrieves a list of banners associated with this refreshment point.
     * In the current architecture, banners are typically fetched via a service layer (e.g., BannerManagementService)
     * using the refreshmentPointId, rather than directly by the RefreshmentPoint entity itself.
     * This method is included to satisfy the class diagram specification but will return an empty list
     * as the RefreshmentPoint object does not intrinsically hold or fetch its associated banners.
     * @return An empty list of Banner objects.
     */
    public List<Banner> getBanners() {
        // This method's implementation is a placeholder to satisfy the class diagram.
        // In a typical layered architecture, associated entities are fetched via repositories or serv.
        return new ArrayList<>();
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshmentPoint that = (RefreshmentPoint) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}