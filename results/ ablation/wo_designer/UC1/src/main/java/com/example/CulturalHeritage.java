public class CulturalHeritage {
    private String id;
    private String name;
    private String description;
    
    public CulturalHeritage(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return "CulturalHeritage{id='" + id + "', name='" + name + "', description='" + description + "'}";
    }
}