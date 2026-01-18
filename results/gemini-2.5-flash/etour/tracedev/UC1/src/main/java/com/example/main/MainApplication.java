package com.example.main;

import com.example.agency.presenter.DeleteCulturalObjectPresenter;
import com.example.agency.view.AgencyOperatorViewImpl;
import com.example.repository.JpaCulturalObjectRepository;
import com.example.usecase.DeleteCulturalObjectUseCase;

/**
 * Main application class to demonstrate the delete cultural object use case.
 * This class sets up the dependency injection and simulates the user interaction.
 */
public class MainApplication {

    public static void main(String[] args) {
        // 1. Initialize Repository
        JpaCulturalObjectRepository culturalObjectRepository = new JpaCulturalObjectRepository();

        // 2. Initialize Use Case with Repository
        DeleteCulturalObjectUseCase deleteCulturalObjectUseCase = new DeleteCulturalObjectUseCase(culturalObjectRepository);

        // 3. Initialize View
        AgencyOperatorViewImpl agencyOperatorView = new AgencyOperatorViewImpl();

        // 4. Initialize Presenter with View and Use Case
        DeleteCulturalObjectPresenter deleteCulturalObjectPresenter =
                new DeleteCulturalObjectPresenter(agencyOperatorView, deleteCulturalObjectUseCase);

        // 5. Link View to Presenter (for delegation of UI events)
        agencyOperatorView.setPresenter(deleteCulturalObjectPresenter);

        // Precondition: Agency Operator is logged in. (R1.3)
        System.out.println("--- System Initialized ---");
        System.out.println("Agency Operator is logged in (simulated, R1.3).");

        // Display current cultural objects
        agencyOperatorView.displayCulturalObjects(culturalObjectRepository.findAll());

        // Simulate Agency Operator interaction (Sequence Diagram start point)
        // Scenario 1: Successful deletion
        System.out.println("\n--- Scenario 1: User confirms deletion of CO001 ---");
        agencyOperatorView.selectCulturalObject("CO001");
        agencyOperatorView.displayCulturalObjects(culturalObjectRepository.findAll()); // Show updated list

        // Scenario 2: User cancels deletion
        System.out.println("\n--- Scenario 2: User cancels deletion of CO002 ---");
        agencyOperatorView.selectCulturalObject("CO002");
        agencyOperatorView.displayCulturalObjects(culturalObjectRepository.findAll()); // List should be unchanged

        // Scenario 3: Attempt to delete non-existent object or simulated failure
        System.out.println("\n--- Scenario 3: User tries to delete non-existent object (CO999) ---");
        agencyOperatorView.selectCulturalObject("CO999");
        agencyOperatorView.displayCulturalObjects(culturalObjectRepository.findAll()); // List should be unchanged

        // Scenario 4: Deleting an object that might fail due to simulated interruption (CO003)
        System.out.println("\n--- Scenario 4: Attempting to delete CO003 (might fail due to simulated error) ---");
        agencyOperatorView.selectCulturalObject("CO003");
        agencyOperatorView.displayCulturalObjects(culturalObjectRepository.findAll());

        System.out.println("\n--- Application End ---");
    }
}