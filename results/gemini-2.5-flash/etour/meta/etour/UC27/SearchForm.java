/**
 * Represents the search criteria provided by the user.
 * This includes the search query and an optional specific path to search within.
 */
public class SearchForm {
    private String query;
    private String searchPath; // Optional path to narrow down the search

    /**
     * Constructs an empty SearchForm.
     */
    public SearchForm() {
        // Default constructor
    }

    /**
     * Constructs a SearchForm with a specified query.
     *
     * @param query The search string.
     */
    public SearchForm(String query) {
        this.query = query;
    }

    /**
     * Constructs a SearchForm with a specified query and search path.
     *
     * @param query The search string.
     * @param searchPath The specific path to search within.
     */
    public SearchForm(String query, String searchPath) {
        this.query = query;
        this.searchPath = searchPath;
    }

    /**
     * Returns the search query.
     *
     * @return The search query string.
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the search query.
     *
     * @param query The search query string to set.
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Returns the optional search path.
     *
     * @return The search path string, or null if not specified.
     */
    public String getSearchPath() {
        return searchPath;
    }

    /**
     * Sets the optional search path.
     *
     * @param searchPath The search path string to set.
     */
    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }

    /**
     * Returns a string representation of the SearchForm.
     *
     * @return A formatted string showing the query and search path.
     */
    @Override
    public String toString() {
        return "SearchForm{" +
               "query='" + query + '\'' +
               ", searchPath='" + (searchPath != null ? searchPath : "N/A") + '\'' +
               '}';
    }
}