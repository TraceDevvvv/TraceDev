/*
Represents a single website with its name, URL, and a brief description.
This is a simple model class for storing site information.
*/
public class Site {
    // Name of the website
    private String name;
    // URL address of the website
    private String url;
    // Brief description of the website
    private String description;
    /**
     * Constructor to create a new Site object.
     * @param name The name of the site.
     * @param url The URL of the site.
     * @param description A brief description of the site.
     */
    public Site(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }
    /**
     * Gets the name of the site.
     * @return The site's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the URL of the site.
     * @return The site's URL.
     */
    public String getUrl() {
        return url;
    }
    /**
     * Gets the description of the site.
     * @return The site's description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Provides a string representation of the Site object for easy display.
     * @return A formatted string containing the site's name, URL, and description.
     */
    @Override
    public String toString() {
        return "Name: " + name + "\nURL: " + url + "\nDescription: " + description + "\n";
    }
}