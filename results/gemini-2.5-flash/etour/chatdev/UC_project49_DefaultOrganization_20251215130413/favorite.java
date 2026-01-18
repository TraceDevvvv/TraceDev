/**
 * Represents a single favorite site with a name and a URL.
 */
class Favorite {
    private String name; // The display name of the favorite site
    private String url;  // The URL of the favorite site
    /**
     * Constructs a new Favorite object.
     *
     * @param name The name of the favorite site.
     * @param url  The URL of the favorite site.
     */
    public Favorite(String name, String url) {
        this.name = name;
        this.url = url;
    }
    /**
     * Retrieves the name of the favorite site.
     *
     * @return The name of the favorite site.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the favorite site.
     *
     * @param name The new name for the favorite site.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Retrieves the URL of the favorite site.
     *
     * @return The URL of the favorite site.
     */
    public String getUrl() {
        return url;
    }
    /**
     * Sets the URL of the favorite site.
     *
     * @param url The new URL for the favorite site.
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * Returns a string representation of the Favorite object, typically just its name for display purposes.
     *
     * @return The name of the favorite site.
     */
    @Override
    public String toString() {
        return name + " (" + url + ")";
    }
}