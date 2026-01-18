
package com.example;

import com.example.controller.AddressController;
import com.example.model.AddressDto;
import com.example.repository.AddressRepositoryImpl;
import com.example.service.AddressService;
import com.example.view.AddressUI;
import javax.sql.DataSource;

/**
 * Main application class that sets up dependencies and runs a demo.
 */
public class Main {
    public static void main(String[] args) {
        // Setup data source (using H2 in-memory for demonstration)
        DataSource dataSource = createDataSource();
        
        // Create repository with data source
        AddressRepositoryImpl repository = new AddressRepositoryImpl(dataSource);
        
        // Create service with repository
        AddressService service = new AddressService(repository);
        
        // Create controller with service
        AddressController controller = new AddressController(service);
        
        // Create UI with controller
        AddressUI ui = new AddressUI(controller);

        // Simulate the sequence diagram flow
        System.out.println("=== Starting Address Management Demo ===");
        
        // Precondition: Administrator is authenticated (simulated)
        System.out.println("Administrator logged in.");
        
        // Entry condition: System is displaying the list of addresses
        System.out.println("\n--- Step 1: View address list ---");
        ui.simulateViewAddressList();
        
        // Administrator clicks "New address" button (m1 from sequence diagram)
        System.out.println("\n--- Step 2: Click 'New address' (m1) ---");
        ui.clicksNewAddressButton();
        
        // Controller returns address form (m3 from sequence diagram)
        System.out.println("\n--- Step 3: Return address form (m3) ---");
        controller.returnAddressForm();
        
        // UI displays empty form (m4 from sequence diagram)
        System.out.println("\n--- Step 4: Display empty form (m4) ---");
        ui.displayEmptyForm();
        
        // Administrator fills and submits form (m5 from sequence diagram)
        System.out.println("\n--- Step 5: Fill and submit form (m5) ---");
        ui.fillsAndSubmitsForm("Main Street");
        
        // Repository inserts address (m11 from sequence diagram)
        System.out.println("\n--- Step 6: INSERT address (m11) ---");
        AddressDto dto = new AddressDto();
        dto.setName("Main Street");
        service.insertAddress(dto);
        
        // Repository confirms insertion
        System.out.println("\n--- Step 7: Confirm insertion ---");
        repository.confirmInsertion();
        
        // Service returns persisted address (m12 from sequence diagram)
        System.out.println("\n--- Step 8: Return persisted address (m12) ---");
        service.persistedAddress(null);
        
        // UI shows success notification (m15 from sequence diagram)
        System.out.println("\n--- Step 9: Show success notification (m15) ---");
        ui.showSuccessNotification();
        
        // Simulate invalid submission
        System.out.println("\n--- Step 10: Submit invalid address ---");
        ui.simulateSubmitForm("");
        
        // UI shows error notification (m18 from sequence diagram)
        System.out.println("\n--- Step 11: Show error notification (m18) ---");
        ui.showErrorNotification("Address name is required");
        
        // Simulate cancellation (m19 from sequence diagram)
        System.out.println("\n--- Step 12: Cancel operation (m19) ---");
        ui.cancelOperation();
        
        // UI sends cancel request (m20 from sequence diagram)
        System.out.println("\n--- Step 13: Cancel request (m20) ---");
        ui.cancelRequest("session123");
        
        // Operation terminated (m21 from sequence diagram)
        System.out.println("\n--- Step 14: Operation terminated (m21) ---");
        ui.operationTerminated();
        
        System.out.println("\n=== Demo Completed ===");
    }

    private static DataSource createDataSource() {
        // Using H2 in-memory database for demonstration
        // Simulating a DataSource creation without H2 dependency
        return new javax.sql.DataSource() {
            @Override
            public java.sql.Connection getConnection() throws java.sql.SQLException {
                return null;
            }
            
            @Override
            public java.sql.Connection getConnection(String username, String password) throws java.sql.SQLException {
                return null;
            }
            
            @Override
            public java.io.PrintWriter getLogWriter() throws java.sql.SQLException {
                return null;
            }
            
            @Override
            public void setLogWriter(java.io.PrintWriter out) throws java.sql.SQLException {
            }
            
            @Override
            public void setLoginTimeout(int seconds) throws java.sql.SQLException {
            }
            
            @Override
            public int getLoginTimeout() throws java.sql.SQLException {
                return 0;
            }
            
            @Override
            public java.util.logging.Logger getParentLogger() throws java.sql.SQLFeatureNotSupportedException {
                return null;
            }
            
            @Override
            public <T> T unwrap(Class<T> iface) throws java.sql.SQLException {
                return null;
            }
            
            @Override
            public boolean isWrapperFor(Class<?> iface) throws java.sql.SQLException {
                return false;
            }
        };
    }
}
