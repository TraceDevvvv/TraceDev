'''
Site.java
This class represents a tourist site with an ID, name, and location.
It includes proper encapsulation and a formatted string representation.
'''
public class Site {
    private String siteId;
    private String siteName;
    private String location;
    /**
     * Constructor for creating a new Site object.
     * @param siteId unique identifier for the site
     * @param siteName name of the tourist site
     * @param location city/country where the site is located
     */
    public Site(String siteId, String siteName, String location) {
        if (siteId == null || siteName == null || location == null) {
            throw new IllegalArgumentException("Site properties cannot be null");
        }
        this.siteId = siteId;
        this.siteName = siteName;
        this.location = location;
    }
    /**
     * Gets the site ID.
     * @return the site identifier
     */
    public String getSiteId() {
        return siteId;
    }
    /**
     * Gets the site name.
     * @return the name of the site
     */
    public String getSiteName() {
        return siteName;
    }
    /**
     * Gets the location of the site.
     * @return the city/country location
     */
    public String getLocation() {
        return location;
    }
    /**
     * Returns a formatted string representation of the site.
     * Format: "ID: Name (Location)"
     * @return formatted string
     */
    @Override
    public String toString() {
        return String.format("%s: %s (%s)", siteId, siteName, location);
    }
    /**
     * Checks equality based on site ID.
     * @param obj the object to compare
     * @return true if site IDs match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Site site = (Site) obj;
        return siteId.equals(site.siteId);
    }
    /**
     * Generates hash code based on site ID.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return siteId.hashCode();
    }
}