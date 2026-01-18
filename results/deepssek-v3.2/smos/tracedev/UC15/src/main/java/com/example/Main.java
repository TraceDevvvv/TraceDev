package com.example;

import com.example.application.*;
import com.example.infrastructure.ClassRepositoryImpl;
import com.example.infrastructure.SMOSDataSource;
import com.example.ui.ClassDetailsDTO;
import com.example.ui.ClassDetailsView;
import com.example.ui.ErrorDTO;

/**
 * Main class to demonstrate the flow of the View Class Details use case.
 * This simulates the interactions as per the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Simulate entry conditions: user is authenticated and has viewed class list
        AuthenticationService authService = new AuthenticationService();
        if (!authService.isAuthenticated()) {
            System.out.println("User not authenticated. Exiting.");
            return;
        }

        ClassListController classListController = new ClassListController();
        classListController.showClassList();

        // Set up the infrastructure
        SMOSDataSource smosDataSource = new SMOSDataSource();
        ClassRepositoryImpl repository = new ClassRepositoryImpl(smosDataSource);

        // Set up the use case and controller
        ViewClassDetailsUseCase useCase = new ViewClassDetailsUseCaseImpl(repository);
        ViewClassDetailsController controller = new ViewClassDetailsController(useCase);

        // Set up the UI
        ClassDetailsView view = new ClassDetailsView();

        // Simulate the administrator clicking "Show class details" with a class ID
        String classId = "C001";
        System.out.println("\nAdministrator clicks 'Show class details' for class ID: " + classId);

        // m1: Admin -> UI: clicks "Show class details" (classId)
        view.clicksShowClassDetails(classId);

        // Handle the request
        Object result = controller.handleRequest(classId);

        // m8: Repository -> UseCase: return Class object (handled internally)
        // m9: UseCase -> UseCase: create ClassDetailsDTO (handled internally)

        // Render the result
        if (result instanceof ClassDetailsDTO) {
            ClassDetailsDTO dto = (ClassDetailsDTO) result;
            // m12: UI -> UI: render details (name, address, school year)
            view.renderDetails(dto.name, dto.address, dto.schoolYear);
            // m13: UI -> Admin: display class details fencing
            view.displayClassDetailsFencing();
            view.render(dto);
        } else if (result instanceof ErrorDTO) {
            ErrorDTO error = (ErrorDTO) result;
            view.showError(error.message);
        }

        // Simulate a connection failure scenario
        System.out.println("\n--- Simulating connection failure scenario ---");
        // m14: SMOS -> Repository: connection error
        smosDataSource.connectionError();

        // We can simulate by modifying the SMOSDataSource to throw an exception.
        // For simplicity, we create a new use case with a repository that uses a failing data source.
        SMOSDataSource failingDataSource = new SMOSDataSource() {
            @Override
            public java.util.Map<String, String> fetchClassData(String classId) {
                throw new RuntimeException("Connection error");
            }
        };
        ClassRepositoryImpl failingRepository = new ClassRepositoryImpl(failingDataSource);
        ViewClassDetailsUseCase failingUseCase = new ViewClassDetailsUseCaseImpl(failingRepository);
        ViewClassDetailsController failingController = new ViewClassDetailsController(failingUseCase);

        Object failureResult = failingController.handleRequest(classId);
        if (failureResult instanceof ErrorDTO) {
            ErrorDTO error = (ErrorDTO) failureResult;
            // m17: Controller -> UI: error response
            ErrorDTO controllerError = failingController.errorResponse();
            // m18: UI -> Admin: display error message
            view.displayErrorMessage(controllerError.message);
            view.showError(error.message);
        }
    }
}