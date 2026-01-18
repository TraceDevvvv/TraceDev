/**
 * A simple POJO (Plain Old Java Object) for representing a tourist Site.
 */
public class Site {
    private int id;
    private String name;
    private String description;
    /**
     * Constructor for Site.
     * @param id Unique identifier for the site.
     * @param name Name of the site.
     * @param description Brief description of the site.
     */
    public Site(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    // --- Getters ---
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    // --- Setters (optional, depending on immutability needs) ---
    public void setId(int id) {
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
        return "Site{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}