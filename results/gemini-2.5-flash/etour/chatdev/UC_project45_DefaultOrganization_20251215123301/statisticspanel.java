import javax.swing.*;
import java.awt.*;
import java.util.Random;
/**
 * This JPanel is responsible for displaying the personal statistics of a Point of Restaurant.
 * It provides a user interface to show various metrics and handles the display of data or error messages.
 */
public class StatisticsPanel extends JPanel {
    // Labels to display the descriptive text for statistics
    private JLabel totalOrdersTextLabel;
    private JLabel totalRevenueTextLabel;
    private JLabel averageOrderValueTextLabel;
    private JLabel mostPopularItemTextLabel;
    private JLabel numberOfCustomersTextLabel;
    private JLabel averageRatingTextLabel;
    // Labels to display the actual statistics values.
    private JLabel totalOrdersLabel;
    private JLabel totalRevenueLabel;
    private JLabel averageOrderValueLabel;
    private JLabel mostPopularItemLabel;
    private JLabel numberOfCustomersLabel;
    private JLabel averageRatingLabel;
    // Labels for status messages
    private JLabel statusTextLabel; // The "Status:" descriptive label
    private JLabel statusLabel;     // For displaying loading/error messages
    /**
     * Constructs a StatisticsPanel, initializing all GUI components.
     * It sets up a grid layout for displaying key-value pairs of statistics.
     */
    public StatisticsPanel() {
        setLayout(new GridLayout(0, 2, 10, 5)); // Grid layout: 0 rows (dynamic), 2 columns, 10px horizontal gap, 5px vertical gap
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        // Initialize and add descriptive labels and their corresponding value labels
        totalOrdersTextLabel = new JLabel("Total Orders:");
        add(totalOrdersTextLabel);
        totalOrdersLabel = new JLabel("N/A");
        add(totalOrdersLabel);
        totalRevenueTextLabel = new JLabel("Total Revenue:");
        add(totalRevenueTextLabel);
        totalRevenueLabel = new JLabel("N/A");
        add(totalRevenueLabel);
        averageOrderValueTextLabel = new JLabel("Average Order Value:");
        add(averageOrderValueTextLabel);
        averageOrderValueLabel = new JLabel("N/A");
        add(averageOrderValueLabel);
        mostPopularItemTextLabel = new JLabel("Most Popular Item:");
        add(mostPopularItemTextLabel);
        mostPopularItemLabel = new JLabel("N/A");
        add(mostPopularItemLabel);
        numberOfCustomersTextLabel = new JLabel("Number of Customers:");
        add(numberOfCustomersTextLabel);
        numberOfCustomersLabel = new JLabel("N/A");
        add(numberOfCustomersLabel);
        averageRatingTextLabel = new JLabel("Average Rating:");
        add(averageRatingTextLabel);
        averageRatingLabel = new JLabel("N/A");
        add(averageRatingLabel);
        // Add the status labels (these should always be visible)
        statusTextLabel = new JLabel("Status:");
        add(statusTextLabel);
        // Initializing the status label with a user-guiding message
        statusLabel = new JLabel("Click 'View Personal Statistics' to load data.");
        statusLabel.setForeground(Color.BLUE);
        add(statusLabel);
        // Initially hide statistics values until loaded
        setStatisticLabelsVisible(false);
    }
    /**
     * Simulates loading statistics data. In a real application, this would
     * involve making an API call or database query. For this example, it
     * generates dummy data or simulates a connection error.
     *
     * @return A StatisticsData object if successful, null if an error occurred.
     */
    public StatisticsData loadStatistics() {
        statusLabel.setText("Loading statistics...");
        statusLabel.setForeground(Color.ORANGE);
        setStatisticLabelsVisible(false); // Hide old data during loading
        try {
            // Simulate network delay or heavy processing
            Thread.sleep(1500);
            // Simulate a random chance of connection interruption (20% chance of failure)
            Random random = new Random();
            if (random.nextDouble() < 0.2) {
                // If a connection interruption is simulated, throw an exception
                throw new Exception("Simulated server connection interruption.");
            }
            // Simulate fetching data for the refreshment point
            // These would typically come from a database or a service
            int totalOrders = 1250;
            double totalRevenue = 45000.75;
            // Handle division by zero for average order value
            double averageOrderValue;
            if (totalOrders > 0) {
                averageOrderValue = totalRevenue / totalOrders;
            } else {
                averageOrderValue = 0.0; // No orders, so average order value is 0
            }
            String mostPopularItem = "Special Burger";
            int numberOfCustomers = 800;
            double averageRating = 4.3;
            return new StatisticsData(totalOrders, totalRevenue, averageOrderValue,
                                      mostPopularItem, numberOfCustomers, averageRating);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            showError("Data loading interrupted: " + e.getMessage());
            return null;
        } catch (Exception e) {
            showError("Failed to load statistics: " + e.getMessage());
            return null;
        }
    }
    /**
     * Displays the provided StatisticsData object on the panel.
     *
     * @param data The StatisticsData object containing the statistics to display.
     */
    public void displayStatistics(StatisticsData data) {
        if (data != null) {
            totalOrdersLabel.setText(String.valueOf(data.getTotalOrders()));
            totalRevenueLabel.setText(String.format("€ %.2f", data.getTotalRevenue()));
            averageOrderValueLabel.setText(String.format("€ %.2f", data.getAverageOrderValue()));
            mostPopularItemLabel.setText(data.getMostPopularItem());
            numberOfCustomersLabel.setText(String.valueOf(data.getNumberOfCustomers()));
            averageRatingLabel.setText(String.format("%.1f / 5.0", data.getAverageRating()));
            statusLabel.setText("Statistics loaded successfully.");
            statusLabel.setForeground(Color.GREEN.darker());
            setStatisticLabelsVisible(true); // Show the data
        } else {
            // If data is null, it means loading failed, an error message should already be set by showError
            setStatisticLabelsVisible(false);
        }
    }
    /**
     * Shows an error message on the status label.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setForeground(Color.RED);
        setStatisticLabelsVisible(false); // Hide previous statistics if an error occurs
    }
    /**
     * Helper method to toggle visibility of statistic value labels and their descriptive text labels.
     * The status labels (`statusTextLabel` and `statusLabel`) are explicitly excluded from this
     * visibility toggle as they should always be visible to provide user feedback.
     *
     * @param visible true to make labels visible, false to hide.
     */
    private void setStatisticLabelsVisible(boolean visible) {
        // Toggle visibility for descriptive text labels
        totalOrdersTextLabel.setVisible(visible);
        totalRevenueTextLabel.setVisible(visible);
        averageOrderValueTextLabel.setVisible(visible);
        mostPopularItemTextLabel.setVisible(visible);
        numberOfCustomersTextLabel.setVisible(visible);
        averageRatingTextLabel.setVisible(visible);
        // Toggle visibility for actual statistics value labels
        totalOrdersLabel.setVisible(visible);
        totalRevenueLabel.setVisible(visible);
        averageOrderValueLabel.setVisible(visible);
        mostPopularItemLabel.setVisible(visible);
        numberOfCustomersLabel.setVisible(visible);
        averageRatingLabel.setVisible(visible);
        // statusTextLabel and statusLabel should always remain visible,
        // so their visibility is NOT toggled here.
    }
}