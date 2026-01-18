package com.example;

import com.example.application.repositories.SiteRepository;
import com.example.application.usecases.GetSiteDetailsUseCase;
import com.example.infrastructure.database.Database;
import com.example.infrastructure.repositories.SiteRepositoryImpl;
import com.example.presentation.SiteDetailsController;
import com.example.presentation.SiteDetailsView;

/**
 * Main class to demonstrate the flow as per sequence diagram.
 * Simulates the tourist selecting a site and the system processing the request.
 */
public class Main {
    public static void main(String[] args) {
        // Setup the dependency chain as per Clean Architecture
        Database database = new Database();
        SiteRepository repository = new SiteRepositoryImpl(database);
        GetSiteDetailsUseCase useCase = new GetSiteDetailsUseCase(repository);
        SiteDetailsController controller = new SiteDetailsController(useCase);
        SiteDetailsView view = new SiteDetailsView(controller);

        // Simulate the tourist selecting a site card (as in sequence diagram)
        System.out.println("=== Tourist selects site card with id 'site123' ===");
        view.selectSiteCard("site123");

        // Simulate a failure case (optional)
        // System.out.println("\n=== Simulating database failure ===");
        // We could simulate by making Database.connect() throw an exception.
    }
}