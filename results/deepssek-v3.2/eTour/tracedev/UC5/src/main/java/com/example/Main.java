package com.example;

import com.example.authentication.AuthenticationService;
import com.example.controller.CulturalHeritageCardController;
import com.example.dto.CulturalGoodDTO;
import com.example.model.CulturalGood;
import com.example.repository.ETOURCulturalGoodRepository;
import com.example.service.CulturalGoodService;
import com.example.ui.AgencyOperatorUI;
import java.util.Arrays;
import java.util.List;

/**
 * Main class to demonstrate the runnable system.
 * Simulates the pre-conditions and flow described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Pre-condition 1: Agency Operator is logged in.
        AuthenticationService authService = new AuthenticationService();
        String operatorId = "op123";
        if (!authService.isLoggedIn(operatorId)) {
            System.out.println("Operator not logged in. Exiting.");
            return;
        }
        System.out.println("Operator " + operatorId + " is logged in.");

        // Set up the system as per class diagram relationships.
        ETOURCulturalGoodRepository repository = new ETOURCulturalGoodRepository();
        CulturalGoodService service = new CulturalGoodService(repository);
        CulturalHeritageCardController controller = new CulturalHeritageCardController(service);
        AgencyOperatorUI ui = new AgencyOperatorUI(controller);

        // Pre-condition 2: SearchCulturalHeritage use case executed and results displayed.
        // Simulate some search results (CulturalGoodDTO objects).
        CulturalGoodDTO result1 = new CulturalGoodDTO(new CulturalGood("1", "Ancient Vase", "A beautiful ancient vase from Roman times.", "Rome", "Roman"));
        CulturalGoodDTO result2 = new CulturalGoodDTO(new CulturalGood("2", "Medieval Sword", "A knight's sword from the 12th century.", "London", "Medieval"));
        List<CulturalGoodDTO> searchResults = Arrays.asList(result1, result2);
        ui.displaySearchResults(searchResults);

        // Simulate the operator selecting a cultural good (step 1).
        // Here we choose the first result.
        ui.onCardSelected("1");

        // Run a second selection to possibly trigger connection error (due to random simulation).
        System.out.println("\n--- Second selection (might trigger error) ---");
        ui.onCardSelected("2");
    }
}