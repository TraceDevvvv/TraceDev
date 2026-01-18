/**
 * Represents a single search result, containing the path and type of the found item.
 */
public class SearchResult {
    private final String path;
    private final String type; // e.g., "File", "Directory"

    /**
     * Constructs a new SearchResult.
     *
     * @param path The full path of the found item.
     * @param type The type of the found item (e.g., "File", "Directory").
     */
    public SearchResult(String path, String type) {
        this.path = path;
        this.type = type;
    }

    /**
     * Returns the path of the found item.
     *
     * @return The path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the type of the found item.
     *
     * @return The type.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns a string representation of the SearchResult.
     *
     * @return A formatted string showing the type and path.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", type, path);
    }

    /**
     * Compares this SearchResult to the specified object. The result is true if and only if
     * the argument is not null and is a SearchResult object that represents the same
     * path and type as this object.
     *
     * @param obj The object to compare this SearchResult against.
     * @return true if the given object represents a SearchResult equivalent to this SearchResult, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SearchResult that = (SearchResult) obj;
        return path.equals(that.path) && type.equals(that.type);
    }

    /**
     * Returns a hash code for this SearchResult.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = path.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}