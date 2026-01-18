
package com.example;

import com.example.adapters.GpsHardwareAdapter;
import com.example.cache.PositionCache;
import com.example.controllers.PositionController;
import com.example.dto.PositionResult;
import com.example.enums.SearchType;
import com.example.serv.PositionService;
import com.example.strategies.GpsPositionStrategy;
import com.example.strategies.NetworkPositionStrategy;

/**
 * Main application class to demonstrate the system.
 * Simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Tourist Position System Demo ===\n");
        
        // Setup infrastructure components
        GpsHardwareAdapter gpsAdapter = new GpsHardwareAdapter();
        PositionCache cache = new PositionCache();
        
        // Setup strategies
        GpsPositionStrategy gpsStrategy = new GpsPositionStrategy(gpsAdapter);
        NetworkPositionStrategy networkStrategy = new NetworkPositionStrategy(cache);
        
        // Setup service with both primary and fallback strategies
        PositionService positionService = new PositionService(gpsStrategy);
        
        // Setup controller
        PositionController controller = new PositionController(positionService);
        
        // Simulate sequence diagram scenario 1: GPS available
        System.out.println("Scenario 1: GPS Available");
        System.out.println("Tourist -> PositionController: getCurrentPosition(SearchType.BASIC)");
        PositionResult result1 = controller.getCurrentPosition(SearchType.BASIC);
        System.out.println("Controller -> Tourist: " + result1);
        System.out.println();
        
        // Simulate sequence diagram scenario 2: GPS unavailable (fallback to network)
        System.out.println("Scenario 2: GPS Unavailable (Fallback to Network)");
        GpsHardwareAdapter.setGpsAvailable(false); // Make GPS unavailable
        System.out.println("Tourist -> PositionController: getCurrentPosition(SearchType.ADVANCED)");
        PositionResult result2 = controller.getCurrentPosition(SearchType.ADVANCED);
        System.out.println("Controller -> Tourist: " + result2);
        System.out.println();
        
        // Restore GPS availability
        GpsHardwareAdapter.setGpsAvailable(true);
        
        System.out.println("=== Demo Complete ===");
    }
}
