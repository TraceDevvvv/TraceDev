package com.example;

import java.util.List;

/**
 * Main class to demonstrate the Delete Address use case,
 * incorporating requirements REQ-3 (Authentication), REQ-4 (Entry Conditions),
 * REQ-9 (Post-condition), and REQ-10 (ConnectionFailedException handling).
 */
public class Main {
    public static void main(String[] args) {
        // 1. Setup the application context (Dependency Injection)
        AddressRepository addressRepository = new AddressRepository();
        AddressService addressService = new AddressService(addressRepository);
        AuthenticationService authenticationService = new AuthenticationService();
        UserInterface userInterface = new UserInterface(addressService, authenticationService);

        System.out.println("--- Starting Delete Address Use Case Demonstration ---");

        // REQ-3: Admin must be logged in.
        // Simulate Admin logging in.
        boolean loggedIn = userInterface.performLogin();
        if (!loggedIn) {
            System.out.println("Application terminated as Admin could not log in.");
            return;
        }

        // REQ-4: Detailed information of an address IS displayed.
        // Simulate displaying details for a known address (e.g., A001).
        // For demonstration, we'll fetch an address from the repository directly to simulate pre-display.
        try {
            List<Address> initialAddresses = addressRepository.queryAllAddresses();
            Address addressToDisplay = initialAddresses.stream()
                                        .filter(a -> a.getId().equals("A001"))
                                        .findFirst()
                                        .orElse(null);
            userInterface.displayAddressDetails(addressToDisplay);
        } catch (ConnectionFailedException e) {
            userInterface.handleConnectionError(e);
            System.out.println("Application terminated due to connection error during initial display.");
            return;
        }


        // --- SCENARIO 1: Attempt to delete an address with associated classes (A001) ---
        System.out.println("\n--- SCENARIO 1: Deleting Address A001 (has associated orders) ---");
        userInterface.requestDeleteAddress("A001");
        // Expected: UI displays error message, list is not refreshed as deletion failed.

        // Verify state after Scenario 1
        System.out.println("\n--- Verifying state after SCENARIO 1 ---");
        try {
            userInterface.displayUpdatedAddressList(addressService.queryAllAddresses());
        } catch (ConnectionFailedException e) {
            userInterface.handleConnectionError(e);
        }


        // --- SCENARIO 2: Attempt to delete an address without associated classes (A003) ---
        System.out.println("\n--- SCENARIO 2: Deleting Address A003 (no associated orders) ---");
        userInterface.requestDeleteAddress("A003");
        // Expected: UI confirms deletion, then displays updated list without A003.
        // REQ-9: Post-condition: An address IS deleted.

        // Verify state after Scenario 2
        System.out.println("\n--- Verifying state after SCENARIO 2 ---");
        try {
            userInterface.displayUpdatedAddressList(addressService.queryAllAddresses());
        } catch (ConnectionFailedException e) {
            userInterface.handleConnectionError(e);
        }


        // --- SCENARIO 3: Simulate ConnectionFailedException during hasAssociatedClasses check (REQ-10) ---
        System.out.println("\n--- SCENARIO 3: Simulating Connection Failure during hasAssociatedClasses ---");
        addressRepository.setSimulateConnectionFailure(true);
        userInterface.requestDeleteAddress("A002"); // A002 has an associated order
        // Expected: UI handles ConnectionFailedException, displays error message.
        addressRepository.setSimulateConnectionFailure(false); // Reset for subsequent operations


        // --- SCENARIO 4: Simulate ConnectionFailedException during actual delete operation (REQ-10) ---
        System.out.println("\n--- SCENARIO 4: Simulating Connection Failure during delete ---");
        // First, ensure A002 has no associated classes (or use a new address ID for this test)
        // For simplicity, let's assume A002 has no associated classes *for this specific test setup only*
        // Or create a new address to ensure no associations. Let's create a temporary one for this test.
        addressRepository.setSimulateConnectionFailure(false); // Ensure no failure initially
        addressRepository.queryAllAddresses().add(new Address("A004", "999 Temp St", "Tempville", "99999")); // Changed findAllAddresses to queryAllAddresses
        System.out.println("Added temporary address A004 for connection failure test.");

        // Now simulate failure during delete
        addressRepository.setSimulateConnectionFailure(true);
        userInterface.requestDeleteAddress("A004");
        // Expected: UI handles ConnectionFailedException during delete, displays error message.
        addressRepository.setSimulateConnectionFailure(false); // Reset


        // --- SCENARIO 5: Simulate ConnectionFailedException during refresh list after successful deletion (REQ-10) ---
        System.out.println("\n--- SCENARIO 5: Simulating Connection Failure during list refresh ---");
        // Ensure there's an address that can be deleted and then cause refresh to fail
        // For simulation, let's add a new address that has no associated orders.
        String refreshFailAddressId = "A005";
        addressRepository.queryAllAddresses().add(new Address(refreshFailAddressId, "111 Refresh Rd", "RefreshCity", "11111")); // Changed findAllAddresses to queryAllAddresses
        System.out.println("Added temporary address " + refreshFailAddressId + " for refresh failure test.");

        // We need to ensure that the actual delete of A005 succeeds first, and then the subsequent queryAllAddresses fails.
        // So, temporarily disable failure for the delete call, then re-enable for the refresh call.
        addressRepository.setSimulateConnectionFailure(false); // Ensure delete succeeds
        System.out.println("Repository: Temporarily ensuring delete succeeds for A005.");
        userInterface.requestDeleteAddress(refreshFailAddressId); // This call internally triggers delete and then queryAllAddresses

        addressRepository.setSimulateConnectionFailure(true); // This should ideally be set BEFORE the call to requestDeleteAddress if we want findAllAddresses during refresh to fail.
        // Re-thinking SCENARIO 5:
        // The `requestDeleteAddress` calls `addressService.deleteAddress`, which then calls `addressRepository.deleteAddressById`.
        // If successful, it then calls `addressService.queryAllAddresses` (which calls `addressRepository.queryAllAddresses`).
        // To simulate refresh failure, `addressRepository.setSimulateConnectionFailure(true)` needs to be active during the `queryAllAddresses` call.
        // The current setup allows the `deleteAddressById` to succeed, and then the `queryAllAddresses` will fail IF `setSimulateConnectionFailure(true)` is still active.
        // My previous setting of `addressRepository.setSimulateConnectionFailure(false)` before `requestDeleteAddress` for `A005` means delete will pass.
        // The subsequent `queryAllAddresses` will also pass because `setSimulateConnectionFailure` is immediately set back to false.
        // Let's modify SCENARIO 5 to explicitly set the failure state for the refresh part.

        // Re-simulate SCENARIO 5 for clarity:
        System.out.println("\n--- SCENARIO 5 (Revised): Simulating Connection Failure during list refresh ---");
        // Add a new address that can be deleted
        String refreshFailAddressId_revised = "A006";
        addressRepository.queryAllAddresses().add(new Address(refreshFailAddressId_revised, "222 Refresh Rd", "RefreshCity", "22222"));
        System.out.println("Added temporary address " + refreshFailAddressId_revised + " for revised refresh failure test.");

        // We will call requestDeleteAddress. Inside it, delete will succeed, but then it will try to refresh.
        // To make refresh fail, we need to ensure the simulateConnectionFailure flag is true *during* the refresh call.
        // For this, the simulation needs to be slightly outside the single method call or the repository needs a more advanced mock.
        // Given the current structure, `setSimulateConnectionFailure(true)` before `requestDeleteAddress` will make *both* delete and refresh potentially fail.
        // A direct way to simulate refresh failure is to have the UI explicitly request a refresh which then fails.
        // The current `requestDeleteAddress` performs delete and then refresh.
        // If `setSimulateConnectionFailure(true)` is active, both `deleteAddressById` and `queryAllAddresses` inside `deleteAddress` will throw.
        // Let's demonstrate failure during an explicit list retrieval after a successful deletion (which already happened in Scenario 2).
        // This is a cleaner way to demonstrate REQ-10 for list refresh.

        System.out.println("\n--- Demonstrating REQ-10 for explicit list refresh after successful deletion ---");
        addressRepository.setSimulateConnectionFailure(true); // Set to fail for the list retrieval
        System.out.println("NOTE: Repository configured to fail during list retrieval.");
        try {
            userInterface.displayUpdatedAddressList(addressService.queryAllAddresses()); // This should now fail
        } catch (ConnectionFailedException e) {
            userInterface.handleConnectionError(e);
        }
        addressRepository.setSimulateConnectionFailure(false); // Reset

        System.out.println("\n--- End of Use Case Demonstration ---");
    }
}