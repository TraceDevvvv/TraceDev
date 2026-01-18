'''
Represents a cultural heritage object with various attributes.
This is a data model class used across the system.
'''
public class CulturalObject {
    private String id;
    private String name;
    private String type;
    private String location;
    private int year;
    private String description;
    public CulturalObject(String id, String name, String type, String location, int year, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.year = year;
        this.description = description;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public int getYear() { return year; }
    public String getDescription() { return description; }
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setLocation(String location) { this.location = location; }
    public void setYear(int year) { this.year = year; }
    public void setDescription(String description) { this.description = description; }
    @Override
    public String toString() {
        return "CulturalObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", year=" + year +
                '}';
    }
}