package ViewFeedback_1765890483;

/**
 * Represents a site in the system.
 * Each site has a unique ID and a name.
 */
public class Site {
    private String id;
    private String name;

    /**
     * Constructs a new Site object.
     *
     * @param id   The unique identifier for the site.
     * @param name The name of the site.
     */
    public Site(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the site.
     *
     * @return The site ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the site.
     *
     * @return The site name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the Site object.
     *
     * @return A string containing the site ID and name.
     */
    @Override
    public String toString() {
        return "Site ID: " + id + ", Name: " + name;
    }
}