package com.example.diagram;

/**
 * Main class to demonstrate the system.
 * This simulates the Administrator actor interacting with the system as per the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Digital Register System...");
        // Create components as per the sequence diagram
        SMOSConnectionManager connectionManager = new SMOSConnectionManager();
        AuthorizationService authService = new AuthorizationService();
        ArchiveRepository archiveRepo = new ArchiveRepositoryImpl();
        RegisterRepository registerRepo = new RegisterRepositoryImpl(archiveRepo);
        RegisterService registerService = new RegisterService(registerRepo);
        RegisterUseCaseController controller = new RegisterUseCaseController(authService, registerService, connectionManager);
        RegisterViewModel viewModel = new RegisterViewModel(controller);
        RegisterViewController viewCtrl = new RegisterViewController(viewModel);
        
        // Simulate the Admin actor
        System.out.println("\n--- Sequence Diagram Flow ---");
        // m1: Admin clicks "Digital Register"
        System.out.println("Admin clicks \"Digital Register\"");
        // m3: ViewCtrl shows academic year selection screen
        viewCtrl.showAcademicYearSelection();
        // m4: Admin selects school year
        System.out.println("Admin selects school year: 2023-2024");
        viewCtrl.onAcademicYearSelected("2023-2024");
        
        // Simulate a scenario where authorization fails
        System.out.println("\n--- Alternative Flow: Access Denied ---");
        // We could create a separate controller with a failing auth service, but for simplicity,
        // we'll just show that the exception is thrown and caught in the view controller.
        try {
            AuthorizationService failingAuthService = new AuthorizationService() {
                @Override
                public boolean validateAccess(String userId, String requiredRole) {
                    System.out.println("Authorization failed (simulated).");
                    return false;
                }
            };
            RegisterUseCaseController failingController = new RegisterUseCaseController(failingAuthService, registerService, connectionManager);
            RegisterViewModel failingViewModel = new RegisterViewModel(failingController);
            RegisterViewController failingViewCtrl = new RegisterViewController(failingViewModel);
            failingViewCtrl.onAcademicYearSelected("2023-2024");
        } catch (AccessDeniedException e) {
            // m11: throws AccessDeniedException
            System.out.println("AccessDeniedException thrown: " + e.getMessage());
            // m12: shows error message
            viewCtrl.showErrorMessage(e.getMessage());
        }
        
        // Simulate SMOS connection interruption
        System.out.println("\n--- SMOS Connection Interruption ---");
        connectionManager.setConnected(false);
        try {
            controller.getRegistersByAcademicYear("2023-2024");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException: " + e.getMessage());
            // View controller would display connection error
            viewCtrl.displayConnectionError();
        }
        
        System.out.println("Demo completed.");
    }
}