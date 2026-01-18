'''
CulturalHeritage class represents a cultural heritage object in the system.
Contains properties like ID, name, description, and location.
'''
public class CulturalHeritage {
    private int id;
    private String name;
    private String description;
    private String location;
    private String type;
    public CulturalHeritage(int id, String name, String description, String location, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return name + " (" + type + ") - " + location;
    }
}