/**
 * Represents the search criteria provided by the user through the search form.
 * This class encapsulates the parameters used to filter cultural heritage sites.
 * It's a simple Plain Old Java Object (POJO) for storing user input.
 */
public class SearchCriteria {
    private String keyword;
    private String location; // Location provided in the search form (might be different from actual user location)
    private String category; // Category selected in the search form
    /**
     * Constructs a new SearchCriteria object.
     * @param keyword The keyword entered by the user to search for.
     * @param location The preferred location entered in the search form.
     * @param category The preferred category selected in the search form.
     */
    public SearchCriteria(String keyword, String location, String category) {
        this.keyword = keyword != null ? keyword.trim() : "";
        this.location = location != null ? location.trim() : "";
        this.category = category != null ? category.trim() : "";
    }
    // --- Getters for all properties ---
    public String getKeyword() {
        return keyword;
    }
    public String getLocation() {
        return location;
    }
    public String getCategory() {
        return category;
    }
}