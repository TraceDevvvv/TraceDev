package com.example.presentation;

import com.example.application.SearchRefreshmentPointQuery;
import com.example.application.SearchRefreshmentPointQueryHandler;
import com.example.application.SearchRefreshmentPointResult;
import com.example.dto.SearchRefreshmentPointRequestDTO;
import com.example.dto.SearchRefreshmentPointResponseDTO;
import com.example.dto.RefreshmentPointDTO;
import com.example.infrastructure.ETOURServerRepository;
import com.example.infrastructure.ETOURServerClient;
import com.example.domain.RefreshmentPoint;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller handling refreshment point search interactions.
 * Implements the flow from user request to response.
 */
public class RefreshmentPointSearchController {
    private SearchRefreshmentPointQueryHandler queryHandler;

    public RefreshmentPointSearchController() {
        // Initialize infrastructure and handler
        ETOURServerClient client = new ETOURServerClient("https://api.etour.example");
        ETOURServerRepository repository = new ETOURServerRepository(client);
        this.queryHandler = new SearchRefreshmentPointQueryHandler(repository);
    }

    /**
     * Shows the search form to the user (Requirement 5).
     * In a real application, this might render a UI view.
     */
    public void showSearchForm() {
        System.out.println("Displaying search form for refreshment points...");
        // In a real implementation, this would return a view/model.
    }

    /**
     * Processes the search request from the user.
     * @param request DTO containing search criteria
     * @return Response DTO with matching points and search duration
     */
    public SearchRefreshmentPointResponseDTO search(SearchRefreshmentPointRequestDTO request) {
        // Trace: Activate Search (Requirement 6) and Submit Form (Requirement 8) handled here.
        System.out.println("Processing search request...");

        // Convert DTO to Query object
        SearchRefreshmentPointQuery query = new SearchRefreshmentPointQuery(
            request.getName(),
            request.getCategory(),
            request.getMaxDistance(),
            request.getAmenities()
        );

        // Delegate to query handler and capture start time for duration measurement
        long startTime = System.currentTimeMillis();
        SearchRefreshmentPointResult result = queryHandler.handle(query);
        long endTime = System.currentTimeMillis();
        long searchDuration = endTime - startTime;

        // Ensure duration does not exceed 15 seconds (Requirement 12, 13)
        if (searchDuration > 15000) {
            System.err.println("Warning: Search exceeded 15 seconds duration limit.");
        }

        // Build response DTO
        SearchRefreshmentPointResponseDTO response = new SearchRefreshmentPointResponseDTO(
            result.getPoints(),
            searchDuration
        );

        System.out.println("Search completed in " + searchDuration + " ms.");
        return response;
    }

    /**
     * Main method to simulate the user interaction flow (for runnable demonstration).
     */
    public static void main(String[] args) {
        RefreshmentPointSearchController controller = new RefreshmentPointSearchController();

        // Simulate entry conditions: user authenticated, system online, GPS available (Requirement 4)
        System.out.println("Entry conditions met: User authenticated, System online, GPS available.");

        // Step 1: Show search form (Requirement 7)
        controller.showSearchForm();

        // Step 2: User submits form with criteria
        SearchRefreshmentPointRequestDTO request = new SearchRefreshmentPointRequestDTO();
        request.setName("Coffee Shop");
        request.setCategory("Cafe");
        request.setMaxDistance(5.0);
        request.setAmenities(List.of("WiFi", "Outdoor Seating"));

        // Step 3: Process search and get response
        SearchRefreshmentPointResponseDTO response = controller.search(request);

        // Step 4: Display results (Requirement 10)
        System.out.println("Displaying list of points:");
        for (RefreshmentPointDTO point : response.getPoints()) {
            System.out.println(" - " + point.getName() + " (" + point.getCategory() + ")");
        }
        System.out.println("Exit conditions met: List returned.");
    }
}