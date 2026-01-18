package model;
/*
 * Encapsulates the criteria for an advanced tourist site search.
 * This object holds all user-defined parameters for the search.
 */
public class SearchCriteria {
    private final String keyword;
    private final String category;
    private final int maxPrice; // Simulated price point, e.g., 0 for free, 1 for cheap, 2 for moderate, etc.
    private final String touristLocation; // User's current geographical location for contextual search
    /**
     * Constructs a new SearchCriteria object.
     *
     * @param keyword General keyword for the search (e.g., "beach", "historical").
     * @param category Specific category of sites (e.g., "Museum", "Park", "Restaurant").
     * @param maxPrice Maximum simulated price level.
     * @param touristLocation The reported current location of the tourist.
     */
    public SearchCriteria(String keyword, String category, int maxPrice, String touristLocation) {
        this.keyword = keyword != null ? keyword.trim() : "";
        this.category = category != null ? category.trim() : "";
        this.maxPrice = maxPrice;
        this.touristLocation = touristLocation != null ? touristLocation.trim() : "";
    }
    /**
     * @return The search keyword.
     */
    public String getKeyword() {
        return keyword;
    }
    /**
     * @return The search category.
     */
    public String getCategory() {
        return category;
    }
    /**
     * @return The maximum simulated price level.
     */
    public int getMaxPrice() {
        return maxPrice;
    }
    /**
     * @return The tourist's current location string.
     */
    public String getTouristLocation() {
        return touristLocation;
    }
    /**
     * Provides a string representation of the search criteria, useful for logging or debugging.
     *
     * @return A formatted string showing all search parameters.
     */
    @Override
    public String toString() {
        return "SearchCriteria{" +
               "keyword='" + keyword + '\'' +
               ", category='" + category + '\'' +
               ", maxPrice=" + maxPrice +
               ", touristLocation='" + touristLocation + '\'' +
               '}';
    }
}