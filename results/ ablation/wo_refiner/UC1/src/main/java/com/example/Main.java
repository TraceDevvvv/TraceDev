
package com.example;

import com.example.model.AgencyOperator;
import com.example.model.CulturalHeritage;
import com.example.service.AuthenticationService;
import com.example.service.ConfirmationService;
import com.example.view.DeleteCulturalHeritageView;
import com.example.controller.DeleteCulturalHeritageController;
import com.example.interactor.DeleteCulturalHeritageInteractor;
import com.example.repository.CulturalHeritageRepositoryImpl;
import com.example.repository.CulturalHeritageRepository;
import com.example.request.DeleteCulturalHeritageRequest;
import com.example.response.DeleteCulturalHeritageResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Main class to simulate the sequence diagram flow.
 * This is a runnable demonstration.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Delete Cultural Heritage Use Case Simulation ===\n");

        // Setup
        AgencyOperator operator = new AgencyOperator("john_doe");
        operator.login("password123");

        AuthenticationService authService = new AuthenticationService();
        authService.addSession("session123", operator);

        CulturalHeritageRepository repository = new CulturalHeritageRepositoryImpl();
        DeleteCulturalHeritageInteractor interactor = new DeleteCulturalHeritageInteractor(repository);
        DeleteCulturalHeritageController controller = new DeleteCulturalHeritageController(interactor, authService);
        DeleteCulturalHeritageView view = new DeleteCulturalHeritageView();

        // Step 1: View CulturalHeritage list
        List<CulturalHeritage> list = Arrays.asList(
            new CulturalHeritage(1, "Ancient Temple", "A historic temple."),
            new CulturalHeritage(2, "Medieval Castle", "A castle from medieval times.")
        );
        view.displayList(list);
        operator.viewCulturalHeritageList();

        // Step 2: Select CulturalHeritage and activate delete function
        System.out.println("\nOperator selects item with ID 1 for deletion.");
        operator.selectCulturalHeritageAndActivateDeleteFunction();
        controller.selectCulturalHeritageAndActivateDeleteFunction(1);
        view.selectCulturalHeritage(1);
        view.activateDeleteFunction();

        // Step 3: Validate session
        if (!authService.validateSession("session123")) {
            view.showErrorMessage(401, "Please log in first");
            return;
        }

        // Step 4: Generate confirmation token
        ConfirmationService confirmationService = new ConfirmationService();
        String token = confirmationService.generateToken();
        view.showDeleteConfirmation(token);
        view.displayConfirmationDialog(token);

        // Step 5: Simulate user confirmation (in reality, UI would collect token)
        System.out.println("\nUser confirms deletion with token: " + token);
        operator.submitConfirmationWithToken(token);
        controller.submitConfirmationWithToken(token);
        view.submitConfirmation(token);

        // Step 6: Create request and call controller
        DeleteCulturalHeritageRequest request = new DeleteCulturalHeritageRequest(1, token);
        controller.execute(request);

        // Simulate database interactions as per sequence diagram
        repository.queryCulturalHeritage(1);
        CulturalHeritage data = repository.queryCulturalHeritageData(1);
        repository.deleteCulturalHeritage(1);

        // Check token validity
        boolean tokenValid = interactor.isTokenValid(token);
        System.out.println("Token valid? " + tokenValid);

        // Create success/failure responses
        DeleteCulturalHeritageResponse successResponse = interactor.createSuccessResponse();
        DeleteCulturalHeritageResponse failureResponse = interactor.createFailureResponse();

        // In a real application, the controller would inform the view of the response,
        // and the view would display success/error. For simulation, we directly call interactor.
        var response = interactor.execute(request);
        if (response.isSuccess()) {
            view.showSuccessMessage(response.getMessage());
            view.displaySuccessNotification(response.getMessage());
        } else {
            view.showErrorMessage(response.getErrorCode(), response.getMessage());
        }

        // Simulate cancellation
        operator.cancelOperation();
        controller.cancelOperation();
        view.cancelOperation();
        view.displayOperationCancelled();

        System.out.println("\n=== Simulation Complete ===");
    }
}
