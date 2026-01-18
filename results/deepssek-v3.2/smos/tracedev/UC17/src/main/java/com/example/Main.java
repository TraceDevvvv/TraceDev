package com.example;

/**
 * Main class to demonstrate the system flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        DataSource dataSource = new DataSource();
        AddressRepository repo = new AddressRepository(dataSource);
        SMOSServer smosServer = new SMOSServer();
        AddressService service = new AddressService(repo, smosServer);
        User admin = new User("admin", "administrator");
        AddressListViewModel viewModel = new AddressListViewModel(service);
        AddressListUseCaseController controller = new AddressListUseCaseController(repo, service, admin);
        AddressListView view = new AddressListView(viewModel, controller);

        // Initialize view
        view.initialize();

        // Simulate administrator clicking the address management button
        System.out.println("\n--- Simulating Administrator Action ---");
        view.onAddressManagementButtonClicked();
    }
}