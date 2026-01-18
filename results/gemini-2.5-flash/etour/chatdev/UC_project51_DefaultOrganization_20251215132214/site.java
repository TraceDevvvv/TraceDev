'''
Represents a geographical site that a tourist can visit and bookmark.
'''
public class Site {
    private String name;
    private String location;
    /**
     * Constructs a new Site object.
     * @param name The name of the site.
     * @param location The geographical location of the site.
     */
    public Site(String name, String location) {
        this.name = name;
        this.location = location;
    }
    /**
     * Returns the name of the site.
     * @return The site's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the location of the site.
     * @return The site's location.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Overrides the toString method to provide a user-friendly representation of the Site.
     * @return A string representation of the site.
     */
    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
    /**
     * Overrides the equals method for proper comparison of Site objects.
     * Two sites are considered equal if they have the same name and location.
     * @param obj The object to compare with.
     * @return true if the sites are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Site site = (Site) obj;
        return name.equals(site.name) &&
               location.equals(site.location);
    }
    /**
     * Overrides the hashCode method consistent with the equals method.
     * @return A hash code for the site.
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, location);
    }
}