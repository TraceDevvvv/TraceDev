/**
 * Model class representing a search tag in the system
 */
public class Tag {
    private String id;
    private String name;
    private String description;
    private String createdDate;
    public Tag(String id, String name, String description, String createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    @Override
    public String toString() {
        return "Tag{id='" + id + "', name='" + name + "', description='" + description + "'}";
    }
}