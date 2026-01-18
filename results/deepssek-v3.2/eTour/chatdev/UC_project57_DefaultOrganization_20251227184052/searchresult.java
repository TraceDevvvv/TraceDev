/**
 * SearchResult represents a single search result item.
 * Contains information about a tourist site or attraction.
 */
class SearchResult {
    private String name;
    private String category;
    private String address;
    private double rating;
    private int reviewCount;
    public SearchResult(String name, String category, String address, 
                       double rating, int reviewCount) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getAddress() { return address; }
    public double getRating() { return rating; }
    public int getReviewCount() { return reviewCount; }
    @Override
    public String toString() {
        return String.format("%-30s | %-20s | Rating: %.1f/5.0 (%d reviews)%nAddress: %s",
            name, category, rating, reviewCount, address);
    }
}