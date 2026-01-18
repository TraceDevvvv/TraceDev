package com.example;

import com.example.model.Tourist;
import com.example.adapters.SearchController;
import com.example.adapters.GPSLocationAdapter;
import com.example.adapters.LocationRepositoryImpl;
import com.example.external.GPSDevice;
import com.example.external.Database;
import com.example.service.LocationServiceImpl;
import com.example.system.SystemInitialization;
import com.example.model.Location;

/**
 * Main class to demonstrate the system.
 * This sets up the components and runs a sample search request.
 */
public class Main {
    public static void main(String[] args) {
        // Setup external dependencies
        GPSDevice gpsDevice = new GPSDevice("Garmin", "GPSMAP 66");
        Database database = new Database();

        // Create adapters
        GPSLocationAdapter gpsAdapter = new GPSLocationAdapter(gpsDevice);
        LocationRepositoryImpl repository = new LocationRepositoryImpl(database);

        // Create core service
        LocationServiceImpl service = new LocationServiceImpl(gpsAdapter, repository);

        // Create controller and register as observer
        SearchController controller = new SearchController(service);
        service.addObserver(controller);

        // System initialization (requirement REQ-003)
        SystemInitialization systemInit = new SystemInitialization();
        systemInit.initializeSystem();

        // Simulate a tourist making a search request
        Tourist tourist = new Tourist("T001", "John Doe");
        Location location = controller.handleSearchRequest(tourist.getTouristId());

        // Output result
        if (location != null) {
            System.out.println("Tourist " + tourist.getName() + " position received:");
            System.out.println("  Latitude: " + location.getLatitude());
            System.out.println("  Longitude: " + location.getLongitude());
            System.out.println("  Accuracy: " + location.getAccuracy());
        } else {
            System.out.println("Failed to retrieve location.");
        }
    }
}