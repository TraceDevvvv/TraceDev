/**
 * This class represents the data model for personal statistics of a Point of Restaurant.
 * It holds various statistical metrics that can be displayed to the operator.
 */
public class StatisticsData {
    private int totalOrders;
    private double totalRevenue;
    private double averageOrderValue;
    private String mostPopularItem;
    private int numberOfCustomers;
    private double averageRating;
    /**
     * Constructs a new StatisticsData object with specified statistical metrics.
     *
     * @param totalOrders The total number of orders processed.
     * @param totalRevenue The total revenue generated.
     * @param averageOrderValue The average value of each order.
     * @param mostPopularItem The name of the most frequently ordered item.
     * @param numberOfCustomers The total number of unique customers served.
     * @param averageRating The average rating received by the restaurant.
     */
    public StatisticsData(int totalOrders, double totalRevenue, double averageOrderValue,
                          String mostPopularItem, int numberOfCustomers, double averageRating) {
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.averageOrderValue = averageOrderValue;
        this.mostPopularItem = mostPopularItem;
        this.numberOfCustomers = numberOfCustomers;
        this.averageRating = averageRating;
    }
    // --- Getters for the statistics data ---
    public int getTotalOrders() {
        return totalOrders;
    }
    public double getTotalRevenue() {
        return totalRevenue;
    }
    public double getAverageOrderValue() {
        return averageOrderValue;
    }
    public String getMostPopularItem() {
        return mostPopularItem;
    }
    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }
    public double getAverageRating() {
        return averageRating;
    }
}