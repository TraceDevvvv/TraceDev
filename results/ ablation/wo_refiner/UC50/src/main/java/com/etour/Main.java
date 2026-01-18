package com.etour;

import com.etour.application.usecase.ViewVisitedSitesUseCase;
import com.etour.infrastructure.ConnectionManager;
import com.etour.infrastructure.ExternalDataSource;
import com.etour.infrastructure.repository.VisitedSitesRepositoryImpl;
import com.etour.presentation.AuthenticationServiceImpl;
import com.etour.presentation.ViewVisitedSitesController;

/**
 * Main class to simulate the application run.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        ExternalDataSource dataSource = new ExternalDataSource();
        ConnectionManager connectionManager = new ConnectionManager();
        VisitedSitesRepositoryImpl repository = new VisitedSitesRepositoryImpl(dataSource, connectionManager);

        // Setup application layer
        ViewVisitedSitesUseCase useCase = new ViewVisitedSitesUseCase(repository);

        // Setup presentation layer
        AuthenticationServiceImpl authService = new AuthenticationServiceImpl();
        ViewVisitedSitesController controller = new ViewVisitedSitesController(useCase, authService);

        // Simulate tourist triggering the feature
        System.out.println("Tourist selects 'View Visited Sites' feature.");
        controller.selectFeatureToDisplayVisitedSites();
    }
}