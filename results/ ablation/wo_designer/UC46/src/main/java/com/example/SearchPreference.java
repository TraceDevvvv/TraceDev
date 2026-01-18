import java.util.List;

public class SearchPreference {
    private double maxPrice;
    private double maxDistance;
    private List<String> categories;
    private int minRating;

    public SearchPreference(double maxPrice, double maxDistance, List<String> categories, int minRating) {
        this.maxPrice = maxPrice;
        this.maxDistance = maxDistance;
        this.categories = categories;
        this.minRating = minRating;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public int getMinRating() {
        return minRating;
    }

    public void setMinRating(int minRating) {
        this.minRating = minRating;
    }

    // Data validation as per quality requirement
    public boolean validate() {
        if (maxPrice < 0) {
            System.err.println("Max price cannot be negative.");
            return false;
        }
        if (maxDistance < 0) {
            System.err.println("Max distance cannot be negative.");
            return false;
        }
        if (minRating < 1 || minRating > 5) {
            System.err.println("Rating must be between 1 and 5.");
            return false;
        }
        if (categories == null || categories.isEmpty()) {
            System.err.println("At least one category must be specified.");
            return false;
        }
        for (String cat : categories) {
            if (cat == null || cat.trim().isEmpty()) {
                System.err.println("Category cannot be empty.");
                return false;
            }
        }
        return true;
    }
}