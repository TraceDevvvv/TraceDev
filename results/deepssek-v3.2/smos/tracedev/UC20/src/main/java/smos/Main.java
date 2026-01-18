package smos;

import smos.system.*;
import smos.presentation.*;
import smos.login.LoginService;
import smos.dettaglio.ViewDettaglizzazioneUseCase;
import smos.server.SMOSServer;

/**
 * Main class to simulate the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Address Deletion Simulation ===\n");

        // Setup
        Database db = new Database();
        AddressRepositoryImpl repo = new AddressRepositoryImpl(db);
        AddressDeletionUseCase useCase = new AddressDeletionUseCase(repo);
        DeleteAddressController controller = new DeleteAddressController(useCase);

        // Simulate entry conditions
        LoginService loginService = new LoginService();
        loginService.login(new Administrator("Admin"));
        ViewDettaglizzazioneUseCase viewUseCase = new ViewDettaglizzazioneUseCase();
        viewUseCase.setTaken(true);

        // Simulate UI layer calling controller
        System.out.println("1. Administrator clicks Delete button (addressId = 'found')");
        DeleteAddressResponse response1 = controller.deleteAddress("found");
        System.out.println("Response: " + response1.isSuccess() + " - " + response1.getMessage());
        if (response1.getUpdatedAddresses() != null) {
            System.out.println("Updated addresses count: " + response1.getUpdatedAddresses().size());
        }

        System.out.println("\n2. Administrator clicks Delete button (addressId = 'hasClasses')");
        DeleteAddressResponse response2 = controller.deleteAddress("hasClasses");
        System.out.println("Response: " + response2.isSuccess() + " - " + response2.getMessage());

        System.out.println("\n3. Administrator clicks Delete button (addressId = 'missing')");
        DeleteAddressResponse response3 = controller.deleteAddress("missing");
        System.out.println("Response: " + response3.isSuccess() + " - " + response3.getMessage());

        // Simulate exit condition: interrupt connection to SMOS server
        System.out.println("\n=== Exit Condition ===");
        SMOSServer smos = new SMOSServer();
        smos.interruptConnection();

        db.close();
        System.out.println("\n=== Simulation Complete ===");
    }
}