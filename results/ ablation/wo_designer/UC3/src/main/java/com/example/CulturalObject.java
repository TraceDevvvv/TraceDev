// Cultural object entity class
public class CulturalObject {
    private String id;
    private String name;
    private String description;
    private String location;
    private String lastModified;
    
    public CulturalObject(String id, String name, String description, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.lastModified = java.time.LocalDateTime.now().toString();
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
        this.lastModified = java.time.LocalDateTime.now().toString();
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
        this.lastModified = java.time.LocalDateTime.now().toString();
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
        this.lastModified = java.time.LocalDateTime.now().toString();
    }
    
    public String getLastModified() {
        return lastModified;
    }
    
    @Override
    public String toString() {
        return "CulturalObject [id=" + id + ", name=" + name + ", location=" + location + "]";
    }
}