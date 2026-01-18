'''
Service class that simulates fetching statistics data from a server.
Handles connection interruptions and provides mock data for demonstration.
'''
import java.util.Date;
import java.util.Random;
public class StatisticsService {
    private boolean serverConnected = true;
    private Random random = new Random();
    /**
     * Simulates fetching statistics data for a restaurant operator.
     * Includes simulation of server connection interruption.
     * 
     * @param operatorId The ID of the restaurant operator
     * @return StatisticsData object containing the statistics
     * @throws ServerConnectionException if connection to server fails
     */
    public StatisticsData fetchStatisticsData(String operatorId) throws ServerConnectionException {
        // Simulate server connection with 10% chance of failure
        if (!serverConnected || random.nextInt(100) < 10) {
            serverConnected = false;
            throw new ServerConnectionException("Connection to the server was interrupted.");
        }
        // Generate mock statistics data
        Date currentDate = new Date();
        // Generate realistic-looking random data
        int totalOrders = 100 + random.nextInt(200); // 100-300 orders
        double totalRevenue = totalOrders * (10.0 + random.nextDouble() * 40); // $10-50 per order
        double averageOrderValue = totalRevenue / totalOrders;
        int peakHour = 12 + random.nextInt(6); // 12-17 (12pm-5pm)
        int popularItemId = 1000 + random.nextInt(50);
        String[] popularItems = {"Cheeseburger", "Caesar Salad", "Margherita Pizza", 
                                "Fish & Chips", "Club Sandwich", "Chicken Tikka Masala"};
        String popularItemName = popularItems[random.nextInt(popularItems.length)];
        int satisfactionScore = 70 + random.nextInt(31); // 70-100
        return new StatisticsData(currentDate, totalOrders, totalRevenue, 
                                 averageOrderValue, peakHour, popularItemId,
                                 popularItemName, satisfactionScore);
    }
    /**
     * Simulates reconnecting to the server.
     * @return true if reconnection successful
     */
    public boolean reconnectToServer() {
        // Simulate reconnection with 80% success rate
        serverConnected = random.nextInt(100) < 80;
        return serverConnected;
    }
    /**
     * Checks if server is currently connected.
     * @return connection status
     */
    public boolean isServerConnected() {
        return serverConnected;
    }
    /**
     * Custom exception for server connection issues
     */
    public static class ServerConnectionException extends Exception {
        public ServerConnectionException(String message) {
            super(message);
        }
    }
}