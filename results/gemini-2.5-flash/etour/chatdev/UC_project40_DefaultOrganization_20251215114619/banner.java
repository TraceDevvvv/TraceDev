'''
Represents a banner advertisement with an ID, name, and content.
'''
class Banner {
    private String id;
    private String name;
    private String content;
    /**
     * Constructs a new Banner object.
     *
     * @param id The unique identifier for the banner.
     * @param name The display name of the banner.
     * @param content The actual content/description of the banner.
     */
    public Banner(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }
    /**
     * Returns the ID of the banner.
     *
     * @return The banner's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the name of the banner.
     *
     * @return The banner's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the content of the banner.
     *
     * @return The banner's content.
     */
    public String getContent() {
        return content;
    }
    /**
     * Provides a string representation of the banner, primarily for display in lists.
     *
     * @return A string in the format "ID: Name".
     */
    @Override
    public String toString() {
        return id + ": " + name;
    }
    /**
     * Checks if this Banner object is equal to another object based on its ID.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal (have the same ID), false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Banner banner = (Banner) obj;
        return id.equals(banner.id);
    }
    /**
     * Returns a hash code for this Banner object based on its ID.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}