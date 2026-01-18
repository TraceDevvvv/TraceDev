package com.example;

/**
 * Main application class to demonstrate the flow.
 * This class sets up all dependencies and simulates user interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Application Starting...");

        // 1. Initialize Infrastructure / Data Access Layer
        SMOSServerAdapter smosServerAdapter = new SMOSServerAdapter("http://smos.example.com/api");
        IAcademicRepository academicRepository = new AcademicRepositoryImpl(smosServerAdapter);

        // 2. Initialize Cache Layer
        AcademicYearCache academicYearCache = new AcademicYearCache();
        ClassCache classCache = new ClassCache();

        // 3. Initialize Application / Service Layer
        AcademicService academicService = new AcademicService(academicRepository, academicYearCache, classCache);

        // 4. Initialize Presentation Layer (MVC components)
        ClassListViewModel classListViewModel = new ClassListViewModel();
        ProfessorView professorView = new ProfessorView();
        AuthenticationService authenticationService = new AuthenticationService(); // EC1

        // 5. Initialize Controller
        ProfessorController professorController = new ProfessorController(academicService, professorView, classListViewModel, authenticationService);

        String professorId = authenticationService.getLoggedInProfessorId(); // Get logged in prof ID

        // --- Scenario 1: Successful Data Retrieval ---
        System.out.println("\n--- RUNNING SCENARIO 1: SUCCESSFUL DATA RETRIEVAL ---");
        smosServerAdapter.setSimulateConnectionError(false); // Ensure no errors
        authenticationService.setLoginStatus(professorId, true); // Ensure logged in

        // Simulate user clicking "Digital Log" button
        professorView.clicksDigitalLogButton(professorId);

        // Simulate user selecting an academic year (this will happen automatically for the first year in initializeProfessorView)
        // professorView.selectAcademicYear("AY2023"); // This would be explicitly called by the view if the user clicked

        // --- Scenario 2: Simulate Cache Hit for Academic Years ---
        // (This will happen automatically if Scenario 1 ran first and cached data)
        System.out.println("\n--- RUNNING SCENARIO 2: CACHE HIT FOR ACADEMIC YEARS & CLASSES ---");
        professorView.clicksDigitalLogButton(professorId); // Re-trigger with cached data

        // Simulate selecting a different year to test class cache
        System.out.println("\n--- RUNNING SCENARIO 2b: CACHE MISS for new year, then CACHE HIT ---");
        professorView.selectAcademicYear("AY2022"); // This will be a cache miss for classes initially
        professorView.selectAcademicYear("AY2022"); // This will be a cache hit for classes

        // --- Scenario 3: Simulate Connection Error for Academic Years ---
        System.out.println("\n--- RUNNING SCENARIO 3: CONNECTION ERROR FOR ACADEMIC YEARS ---");
        smosServerAdapter.setSimulateConnectionError(true); // Introduce error
        professorView.clicksDigitalLogButton(professorId); // This should trigger an error message

        // --- Scenario 4: Simulate Connection Error for Classes (after years load successfully) ---
        System.out.println("\n--- RUNNING SCENARIO 4: CONNECTION ERROR FOR CLASSES ---");
        smosServerAdapter.setSimulateConnectionError(false); // Reset for academic years
        // First, load academic years successfully
        professorView.clicksDigitalLogButton(professorId);

        // Now, set error flag for class fetching
        smosServerAdapter.setSimulateConnectionError(true);
        professorView.selectAcademicYear("AY2023"); // This should trigger an error for classes

        // --- Scenario 5: Authentication Error (EC1) ---
        System.out.println("\n--- RUNNING SCENARIO 5: AUTHENTICATION ERROR ---");
        smosServerAdapter.setSimulateConnectionError(false); // Reset error
        authenticationService.setLoginStatus("unknownUser", false); // Simulate not logged in
        professorView.clicksDigitalLogButton("prof123"); // Attempt to access with a different ID while not logged in

        System.out.println("\nApplication Finished.");
    }
}