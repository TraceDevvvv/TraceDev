/**
 * Represents a Cultural Heritage object with all necessary attributes
 * Implements Serializable for database persistence
 */
import java.io.Serializable;
import java.util.Objects;
public class CulturalHeritage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String type;
    private String location;
    private int year;
    private String description;
    public CulturalHeritage(String id, String name, String type, String location, int year, String description) {
        setId(id);
        setName(name);
        setType(type);
        setLocation(location);
        setYear(year);
        setDescription(description);
    }
    // Getters and setters with validation
    public String getId() {
        return id;
    }
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id.trim();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        this.type = type.trim();
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        this.location = location.trim();
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        if (year < 0 || year > currentYear) {
            throw new IllegalArgumentException("Year must be between 0 and " + currentYear);
        }
        this.year = year;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = (description == null) ? "" : description.trim();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CulturalHeritage that = (CulturalHeritage) obj;
        // Quality requirement: System will not accept duplicates
        // We consider cultural heritage duplicate if ID is the same
        return id.equals(that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "CulturalHeritage{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", year=" + year +
                ", description='" + (description.length() > 50 ? description.substring(0, 47) + "..." : description) + '\'' +
                '}';
    }
    /**
     * Returns a formatted string for display in confirmation dialogs
     */
    public String toDisplayString() {
        return String.format(
            "ID: %s\nName: %s\nType: %s\nLocation: %s\nYear: %d\nDescription: %s",
            id, name, type, location, year, 
            description.isEmpty() ? "(No description)" : description
        );
    }
}