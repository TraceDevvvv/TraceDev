'''
Model class representing statistical data for a restaurant.
Contains various metrics that would be displayed to the operator.
'''
import java.util.Date;
public class StatisticsData {
    private Date reportDate;
    private int totalOrders;
    private double totalRevenue;
    private double averageOrderValue;
    private int peakHour;
    private int mostPopularItemId;
    private String mostPopularItemName;
    private int customerSatisfactionScore; // 1-100
    public StatisticsData(Date reportDate, int totalOrders, double totalRevenue, 
                         double averageOrderValue, int peakHour, int mostPopularItemId,
                         String mostPopularItemName, int customerSatisfactionScore) {
        this.reportDate = reportDate;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.averageOrderValue = averageOrderValue;
        this.peakHour = peakHour;
        this.mostPopularItemId = mostPopularItemId;
        this.mostPopularItemName = mostPopularItemName;
        this.customerSatisfactionScore = customerSatisfactionScore;
    }
    public Date getReportDate() {
        return reportDate;
    }
    public int getTotalOrders() {
        return totalOrders;
    }
    public double getTotalRevenue() {
        return totalRevenue;
    }
    public double getAverageOrderValue() {
        return averageOrderValue;
    }
    public int getPeakHour() {
        return peakHour;
    }
    public int getMostPopularItemId() {
        return mostPopularItemId;
    }
    public String getMostPopularItemName() {
        return mostPopularItemName;
    }
    public int getCustomerSatisfactionScore() {
        return customerSatisfactionScore;
    }
    @Override
    public String toString() {
        return String.format("Statistics Report - Date: %s\nTotal Orders: %d\nTotal Revenue: $%.2f\n" +
                           "Average Order Value: $%.2f\nPeak Hour: %d:00\nMost Popular Item: %s (ID: %d)\n" +
                           "Customer Satisfaction: %d/100",
                           reportDate, totalOrders, totalRevenue, averageOrderValue,
                           peakHour, mostPopularItemName, mostPopularItemId, customerSatisfactionScore);
    }
}