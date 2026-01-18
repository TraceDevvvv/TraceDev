package com.example.tourist;

/**
 * Main class to demonstrate the complete flow.
 * Creates all components and simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        // Create a simple data source for testing
        DataSource dataSource = new DataSource() {
            @Override
            public Tourist fetchTouristData(String id) {
                Tourist tourist = new Tourist(id, "John Doe", "john.doe@example.com");
                tourist.addBooking("B001");
                tourist.addBooking("B002");
                return tourist;
            }
        };

        // Create repository with data source
        TouristRepository repository = new TouristRepositoryImpl(dataSource);
        
        // Create service with repository
        TouristService service = new TouristServiceImpl(repository);
        
        // Create controller with service
        TouristController controller = new TouristController(service);
        
        // Create view with controller
        TouristCardView view = new TouristCardView(controller);
        
        // Create agency operator and login
        AgencyOperator operator = new AgencyOperator();
        operator.login();
        
        // Simulate the sequence diagram flow
        System.out.println("=== Starting Tourist Card View Flow ===");
        
        // First, test without authentication (operator not logged in)
        operator.logout();
        view.showTouristCard("T123");
        
        // Now with authentication
        System.out.println("\n=== Retrying with authentication ===");
        operator.login();
        view.showTouristCard("T123");
        
        // Test with server connection failure
        System.out.println("\n=== Testing with server connection failure ===");
        ETOURServerConnection server = new ETOURServerConnection();
        server.setConnectionStatus(false);
        // Note: In a real scenario, we would inject this into the repository
        
        System.out.println("=== Flow completed successfully ===");
    }
}