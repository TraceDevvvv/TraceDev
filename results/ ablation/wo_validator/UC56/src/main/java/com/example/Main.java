package com.example;

import com.example.adapter.GPSAdapter;
import com.example.controller.UseCaseController;
import com.example.dto.SearchDTO;
import com.example.enums.SearchType;
import com.example.repository.GPSRepository;
import com.example.service.SystemInitializationService;

/**
 * Main class to demonstrate the system initialization flow.
 * This simulates the sequence diagram interaction.
 */
public class Main {
    public static void main(String[] args) {
        // Instantiate adapters and repositories as per diagram (using GPS path)
        GPSAdapter gpsAdapter = new GPSAdapter();
        GPSRepository gpsRepository = new GPSRepository(gpsAdapter);
        SystemInitializationService service = new SystemInitializationService(gpsRepository);
        UseCaseController controller = new UseCaseController(service);

        // Create a sample search DTO (as if sent by Tourist)
        SearchDTO searchDTO = new SearchDTO("restaurants", SearchType.BASIC);

        // Tourist calls the controller (simulating the sequence)
        System.out.println("Tourist requesting system initialization...");
        controller.handleInitializeSystem(searchDTO);
        System.out.println("Use case completed.");
    }
}