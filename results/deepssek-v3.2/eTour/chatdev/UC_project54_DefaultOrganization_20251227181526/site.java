/**
 * Site class represents a tourist site with basic information.
 * This is included to make the system more complete and realistic.
 */
public class Site {
    private String siteId;
    private String name;
    private String description;
    public Site(String siteId, String name, String description) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
    }
    // Getters
    public String getSiteId() { return siteId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    @Override
    public String toString() {
        return "Site{id='" + siteId + "', name='" + name + 
               "', description='" + description + "'}";
    }
}