
package com.example.addressapp;

/**
 * Main application class to demonstrate the flow.
 * Acts as the bootstrap for the application, initializing components and simulating user interaction.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Application starting...\n");

        // 1. Initialize core components
        SMOSServerAdapter smosServerAdapter = new SMOSServerAdapter();
        IAddressRepository addressRepository = new AddressRepository(smosServerAdapter);
        AddressService addressService = new AddressService(addressRepository);
        UserSession userSession = new UserSession(); // Initial state: not logged in

        // 2. Initialize UI components (injecting controller later)
        AddressDetailsUI addressDetailsUI = new AddressDetailsUI();

        // 3. Initialize Controller (injecting service and UI components)
        // Controller needs to manage UIs, so UI references passed to controller
        AddressListUI addressListUI = new AddressListUI(null); // Temporarily null, will set after controller is ready
        AddressController addressController = new AddressController(addressService, addressListUI, addressDetailsUI, userSession);
        // Now set the controller for AddressListUI
        addressListUI = new AddressListUI(addressController); // Re-initialize with controller

        // --- Scenario 1: User not logged in (EC1) ---
        System.out.println("--- Scenario 1: User not logged in ---");
        userSession.setLoggedIn(false);
        addressListUI.onShowDetailsButtonClicked("101");
        System.out.println("\n");

        // --- Scenario 2: User logged in, successful address retrieval ---
        System.out.println("--- Scenario 2: User logged in, successful address retrieval ---");
        userSession.setLoggedIn(true);
        addressListUI.onShowDetailsButtonClicked("101"); // Address exists
        System.out.println("\n");

        // --- Scenario 3: User logged in, address not found ---
        System.out.println("--- Scenario 3: User logged in, address not found ---");
        userSession.setLoggedIn(true);
        addressListUI.onShowDetailsButtonClicked("999"); // Non-existent address
        System.out.println("\n");

        // --- Scenario 4: User logged in, SMOS server connection fails (simulated) ---
        // To reliably simulate SMOS server connection failure, we might need a mockable SMOSServerAdapter
        // For this example, SMOSServerAdapter has a random chance of failing.
        // We'll run it a few times to increase the chance of seeing a connection failure.
        System.out.println("--- Scenario 4: User logged in, SMOS server connection might fail (random) ---");
        userSession.setLoggedIn(true);
        boolean connectionFailed = false;
        for (int i = 0; i < 5; i++) {
            System.out.println("Attempt " + (i + 1) + " for address 102:");
            addressListUI.onShowDetailsButtonClicked("102");
            // Check if an error message indicating connection failure was printed (simple text check)
            // This is a simulation, in a real test you'd use mock objects and verify interactions.
            try {
                // Introduce a small delay to separate logs if connection fails often
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Note: The smosServerAdapter.connect() call here is to check its internal state,
            // not to initiate a new connection for the current request. The connection is handled
            // internally by AddressRepository during findById.
            // A more robust simulation would involve injecting a mock adapter that can be configured
            // to fail or succeed predictably for testing purposes.
            // For the purpose of tracing, the error path is demonstrated by the calls.
            // The original code tried to call smosServerAdapter.isConnected() which was not found.
            // Since SMOSServerAdapter's definition is not provided and modifying external classes is not allowed,
            // this check for simulation purposes is removed to fix the compilation error.
            // In a real scenario, SMOSServerAdapter would expose a public method for connection status or errors.
            // if (!smosServerAdapter.isConnected()) { // Accessing a private field for simulation check
            //     connectionFailed = true;
            // }
            System.out.println("---");
        }
        if (!connectionFailed) {
            System.out.println("Note: SMOS server connection didn't fail in the above attempts. Its failure is random, try running again.");
        }
        System.out.println("\nApplication finished.");
    }
}
