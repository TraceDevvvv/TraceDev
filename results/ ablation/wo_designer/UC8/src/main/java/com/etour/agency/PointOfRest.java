// Entity class representing a Point of Rest (Refreshment Point)
package com.etour.agency;

public class PointOfRest {
    private int id;
    private String name;
    private String location;
    private String description;
    private int capacity;
    private boolean isActive;

    public PointOfRest() {}

    public PointOfRest(int id, String name, String location, String description, int capacity, boolean isActive) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
        this.isActive = isActive;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    @Override
    public String toString() {
        return "PointOfRest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", isActive=" + isActive +
                '}';
    }
}