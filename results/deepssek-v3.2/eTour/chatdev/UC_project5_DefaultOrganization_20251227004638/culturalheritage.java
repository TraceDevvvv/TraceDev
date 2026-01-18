"""
Represents a cultural heritage item with all relevant properties.
This is the data model used throughout the application.
"""
/**
 * Represents a cultural heritage item with all relevant properties.
 */
class CulturalHeritage {
    private String id;
    private String name;
    private String type;
    private String location;
    private String period;
    private String status;
    private List<String> images;
    private String description;
    private String historicalSignificance;
    private String conservationStatus;
    public CulturalHeritage(String id, String name, String type, String location, 
                           String period, String status, List<String> images,
                           String description, String historicalSignificance, 
                           String conservationStatus) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.period = period;
        this.status = status;
        this.images = images;
        this.description = description;
        this.historicalSignificance = historicalSignificance;
        this.conservationStatus = conservationStatus;
    }
    // Getter methods
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public String getPeriod() { return period; }
    public String getStatus() { return status; }
    public List<String> getImages() { return images; }
    public String getDescription() { return description; }
    public String getHistoricalSignificance() { return historicalSignificance; }
    public String getConservationStatus() { return conservationStatus; }
}