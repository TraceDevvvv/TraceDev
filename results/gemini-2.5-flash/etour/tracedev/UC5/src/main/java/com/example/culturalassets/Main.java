package com.example.culturalassets;

import com.example.culturalassets.controller.CulturalAssetController;
import com.example.culturalassets.repository.CulturalAssetRepositoryImpl;
import com.example.culturalassets.repository.ICulturalAssetRepository;
import com.example.culturalassets.service.AuthenticationService;
import com.example.culturalassets.service.DatabaseConnection;
import com.example.culturalassets.usecase.ViewCulturalAssetDetailsUseCase;
import com.example.culturalassets.view.CulturalAssetView;
// Note: SearchCulturalAssetUseCase and SearchCriteria are not directly part of this sequence flow,
// but they are included in the class diagram and have basic implementations.

/**
 * Main application class to demonstrate the flow described in the sequence diagram.
 * This acts as the entry point and orchestrates the creation and interaction of objects.
 */
public class Main {
    public static void main(String[] args) {
        // --- Setup: Instantiate all components and inject dependencies ---

        // Infrastructure/Service Layer
        DatabaseConnection dbConnection = new DatabaseConnection("jdbc:etour_db_prod");
        AuthenticationService authService = new AuthenticationService();

        // Data Access Layer
        ICulturalAssetRepository culturalAssetRepository = new CulturalAssetRepositoryImpl(dbConnection);

        // Application Layer
        ViewCulturalAssetDetailsUseCase viewCulturalAssetDetailsUseCase =
                new ViewCulturalAssetDetailsUseCase(culturalAssetRepository);

        // Presentation Layer
        CulturalAssetView culturalAssetView = new CulturalAssetView();

        // Controller (ties Presentation and Application layers)
        CulturalAssetController culturalAssetController =
                new CulturalAssetController(viewCulturalAssetDetailsUseCase, culturalAssetView, authService);

        // --- Simulate Sequence Diagram Scenarios ---

        // Scenario 1: Unauthenticated user attempt (Optional flow in SD)
        System.out.println("----- SCENARIO 1: Unauthenticated User -----");
        String invalidSessionId = "invalid";
        String assetId_unauth = "A001";
        // AO -> Controller: (Attempt to access protected resource)
        culturalAssetController.handleViewDetailsRequest(assetId_unauth, invalidSessionId);

        System.out.println("\n\n----- SCENARIO 2: Successful retrieval of asset details -----");
        String validSessionId = "user123_session";
        String assetId_success = "A001";
        // Ensure DB is connected for success case
        dbConnection.connect();
        // AO -> Controller: handleViewDetailsRequest(assetId)
        culturalAssetController.handleViewDetailsRequest(assetId_success, validSessionId);
        dbConnection.disconnect();

        System.out.println("\n\n----- SCENARIO 3: Asset not found -----");
        String assetId_notFound = "A999";
        dbConnection.connect();
        culturalAssetController.handleViewDetailsRequest(assetId_notFound, validSessionId);
        dbConnection.disconnect();

        System.out.println("\n\n----- SCENARIO 4: Connection to ETOUR server interrupted (Error flow in SD) -----");
        // Simulate a database connection that will fail during query
        DatabaseConnection failingDbConnection = new DatabaseConnection("jdbc:etour_db_fail");
        failingDbConnection.connect(); // Connect might still succeed, but query will fail due to 'fail' in string
        ICulturalAssetRepository failingCulturalAssetRepository = new CulturalAssetRepositoryImpl(failingDbConnection);
        ViewCulturalAssetDetailsUseCase failingUseCase = new ViewCulturalAssetDetailsUseCase(failingCulturalAssetRepository);
        CulturalAssetController failingController = new CulturalAssetController(failingUseCase, culturalAssetView, authService);

        String assetId_connFailed = "A002";
        // AO -> Controller: handleViewDetailsRequest(assetId)
        failingController.handleViewDetailsRequest(assetId_connFailed, validSessionId);
        failingDbConnection.disconnect();

        System.out.println("\n\n----- All scenarios completed -----");
    }
}